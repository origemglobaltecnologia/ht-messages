// src/test/java/com/htmessages/WebSocketConfigTest.java
package com.htmessages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebSocketConfigTest {

    @Autowired
    private WebSocketConfig config;

    @Test
    void testConfigureMessageBroker() {
        MessageBrokerRegistry registry = new MessageBrokerRegistry();
        config.configureMessageBroker(registry);
        assertNotNull(config);
    }

    @Test
    void testRegisterStompEndpoints() {
        StompEndpointRegistry registry = new StompEndpointRegistry(null, null);
        assertDoesNotThrow(() -> config.registerStompEndpoints(registry));
    }
}
