// src/test/java/com/htmessages/AudioMessageTest.java
package com.htmessages;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AudioMessageTest {

    @Test
    void testDefaultConstructorAndSetters() {
        AudioMessage msg = new AudioMessage();
        msg.setSenderId(1L);
        msg.setRecipientIdentifier("TODOS");
        msg.setAudioData(new byte[]{1, 2, 3});
        msg.setTimestamp(123456L);

        assertEquals(1L, msg.getSenderId());
        assertEquals("TODOS", msg.getRecipientIdentifier());
        assertArrayEquals(new byte[]{1, 2, 3}, msg.getAudioData());
        assertEquals(123456L, msg.getTimestamp());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        byte[] data = {9, 9, 9};
        AudioMessage msg = new AudioMessage(10L, "5", data, 111L);

        assertEquals(10L, msg.getSenderId());
        assertEquals("5", msg.getRecipientIdentifier());
        assertArrayEquals(data, msg.getAudioData());
        assertEquals(111L, msg.getTimestamp());
    }

    @Test
    void testToStringAndEqualsHashCode() {
        AudioMessage m1 = new AudioMessage(1L, "X", new byte[]{1}, 10L);
        AudioMessage m2 = new AudioMessage(1L, "X", new byte[]{1}, 10L);

        assertEquals(m1, m2);
        assertTrue(m1.toString().contains("senderId=1"));
        assertEquals(m1.hashCode(), m2.hashCode());
    }
}
