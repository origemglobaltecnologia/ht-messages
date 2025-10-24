package com.htmessages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HtMessagesApplication.class)
class WebSocketConfigTest {

    @Autowired
    private WebSocketConfig webSocketConfig;

    @Test
    void contextLoads() {
        assertNotNull(webSocketConfig, "WebSocketConfig should be loaded in the Spring context");
    }
}
