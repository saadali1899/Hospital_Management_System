package com.example.Practice1.domain;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class SqsFunctionsTest {
    SqsFunctions obj = new SqsFunctions("abc", System.getenv("docA"));

    @Test
    void sendMessage() {
        SqsFunctions obj = new SqsFunctions("abc", System.getenv("docA"));
        String resp = obj.sendMessage(System.getenv("docA"), "abc", "abc", 2);
        assertNotNull(resp);
    }

    @Test
    void readMessage() {
        String resp = obj.readMessage();
        assertNotNull(resp);
    }

    @Test
    void getMessageCount() {
        int num = SqsFunctions.getMessageCount(System.getenv("docA"));
        assertNotNull(num);
    }


    @Test
    void deleteMessage() {
        obj.deleteMessage();
    }

    @Test
    void execution() {
        obj.execution();
    }

    @Test
    void main() {
        SqsFunctions.main(new String[] {"arg1", "arg2", "arg3"});
    }


    @Test
    void execution2() {
        SqsFunctions obj1 = new SqsFunctions("abc", System.getenv("docB"));
        obj1.execution();
    }
}