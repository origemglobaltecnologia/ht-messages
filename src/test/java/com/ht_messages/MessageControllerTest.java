// src/test/java/com/htmessages/MessageControllerTest.java
package com.htmessages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

class MessageControllerTest {

    private SimpMessagingTemplate template;
    private MessageController controller;

    @BeforeEach
    void setUp() {
        template = Mockito.mock(SimpMessagingTemplate.class);
        controller = new MessageController(template);
    }

    @Test
    void testHandleAudioMessage_BroadcastToAll() {
        AudioMessage msg = new AudioMessage(1L, "TODOS", new byte[]{1, 2, 3}, 100L);

        controller.handleAudioMessage(msg);

        verify(template, times(1))
                .convertAndSend("/topic/public.messages", msg);
        verifyNoMoreInteractions(template);
    }

    @Test
    void testHandleAudioMessage_PrivateMessage() {
        AudioMessage msg = new AudioMessage(1L, "2", new byte[]{4, 5, 6}, 200L);

        controller.handleAudioMessage(msg);

        verify(template, times(1))
                .convertAndSendToUser("2", "/queue/messages", msg);
        verifyNoMoreInteractions(template);
    }

    @Test
    void testHandleAudioMessage_ErrorHandling() {
        // Simula erro ao enviar
        doThrow(new RuntimeException("erro"))
                .when(template)
                .convertAndSendToUser(eq("10"), anyString(), any());

        AudioMessage msg = new AudioMessage(1L, "10", new byte[]{1}, 10L);

        controller.handleAudioMessage(msg);

        // Não deve lançar exceção — apenas logar
        verify(template, times(1))
                .convertAndSendToUser("10", "/queue/messages", msg);
    }
}
