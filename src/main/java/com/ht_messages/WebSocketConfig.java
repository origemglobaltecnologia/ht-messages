// com.htmessages.WebSocketConfig
package com.ht_messages;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 1. Habilita o servidor WebSocket e o broker de mensagens STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configura o broker de mensagens (roteamento de mensagens).
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        
        // 2. enableSimpleBroker: Prefixo para as mensagens ENVIADAS pelo servidor (o destinatário assina)
        // O cliente assinará: /topic/public.messages (para "TODOS") ou /user/queue/messages (privado)
        config.enableSimpleBroker("/topic", "/user");
        
        // 3. setApplicationDestinationPrefixes: Prefixo para as mensagens RECEBIDAS pelo servidor
        // O cliente enviará a mensagem para: /app/sendAudio
        config.setApplicationDestinationPrefixes("/app");
        
        // 4. setUserDestinationPrefix: Define o prefixo para roteamento de mensagens privadas para usuários específicos
        config.setUserDestinationPrefix("/user");
    }

    /**
     * Registra os endpoints HTTP/WebSocket que o cliente Vue.js usará para se conectar.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        
        // 5. addEndpoint("/ws"): O caminho que o cliente usa para o handshake inicial
        // .setAllowedOriginPatterns("*"): Permite conexões de qualquer origem (essencial para frontend/backend separados)
        // .withSockJS(): Adiciona suporte a SockJS, que fornece opções de fallback para navegadores que não suportam WebSocket nativo.
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}

