package com.example.Practice1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqsMessageTest {
SqsMessage obj = new SqsMessage("saad","test@test.com",5);
    @Test
    void messageBody() {
    String out = "{" +
            "name='" + "saad" + '\'' +
            ", email='" + "test@test.com" + '\'' +
            ", waitingNumber=" + 5 +
            '}';
    assertEquals(out,obj.messageBody());
    }

    @Test
    void getName() {
        assertEquals("saad",obj.getName());
    }

    @Test
    void getEmail() {
        assertEquals("test@test.com",obj.getEmail());
    }

    @Test
    void getWaitingNumber() {
        assertEquals(5,obj.getWaitingNumber());
    }
}