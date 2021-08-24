package com.example.Practice1.domain;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class SesServiceTest {

    @Test
    void sendEmail() {
        SesService.sendEmail("abc@abc.com","saad","5");
    }
}