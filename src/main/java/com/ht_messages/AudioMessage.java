// com.htmessages.AudioMessage
package com.htmessages;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO que representa um CHUNK (pedaço) de áudio enviado via WebSocket em tempo real.
 * Usado para o streaming contínuo no estilo walkie-talkie (HT).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioMessage {
    
    // ID do usuário que está enviando o chunk de áudio
    private Long senderId;
    
    // Identificador do Destinatário: 
    // - ID de um usuário específico (ex: "123")
    // - A string "TODOS" para enviar para todos
    private String recipientIdentifier; 
    
    // O conteúdo principal: um pequeno pacote (chunk) do áudio em formato binário.
    // O frontend enviará múltiplos destes em sequência.
    private byte[] audioData;
    
    // Opcional: O timestamp de quando este chunk foi gerado/enviado
    private Long timestamp = System.currentTimeMillis();
}

