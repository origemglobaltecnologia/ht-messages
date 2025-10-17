// com.htmessages.MessageController
package com.htmessages;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    // SimpMessagingTemplate é a ferramenta do Spring que permite ENVIAR mensagens 
    // para destinos específicos do WebSocket (clientes conectados).
    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Rota de recebimento de áudio do cliente.
     * Mapeado para /app/sendAudio (veja o WebSocketConfig).
     * Esta função é acionada toda vez que o frontend envia um CHUNK de áudio.
     */
    @MessageMapping("/sendAudio")
    public void handleAudioMessage(AudioMessage message) {
        
        String recipient = message.getRecipientIdentifier();
        
        if ("TODOS".equalsIgnoreCase(recipient)) {
            // 1. Roteamento para Grupo (TODOS): Broadcast
            // Envia o chunk para o tópico que todos os clientes devem assinar.
            // Destino: /topic/public.messages
            messagingTemplate.convertAndSend("/topic/public.messages", message);
            
        } else {
            // 2. Roteamento para um Usuário Específico (Um para Um)
            try {
                // Tentamos rotear para um ID de usuário específico
                String destination = "/queue/messages"; 
                
                // Envia para o destino privado do usuário.
                // O prefixo /user/ é adicionado automaticamente pelo Spring.
                // Destino final para o cliente com ID 'X': /user/X/queue/messages
                messagingTemplate.convertAndSendToUser(
                    recipient, 
                    destination, 
                    message
                );
            } catch (Exception e) {
                // Em um projeto real, você registraria esse erro (ex: destinatário desconectado)
                System.err.println("Erro ao rotear mensagem privada para " + recipient + ": " + e.getMessage());
            }
        }
        
        // **IMPORTANTE:** A chave para o tempo real é: NADA de lógica de negócio complexa aqui. 
        // Apenas recebe e retransmite o mais rápido possível!
    }
}

