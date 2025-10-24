// src/test/java/com/htmessages/WebSocketConfigTest.java
package com.htmessages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebSocketConfigTest {

    @Autowired
    private WebSocketConfig webSocketConfig;

    @Test
    void contextLoads() {
        // Verifica se o bean foi carregado pelo Spring
        assertNotNull(webSocketConfig, "WebSocketConfig should be loaded in the Spring context");
    }
}
