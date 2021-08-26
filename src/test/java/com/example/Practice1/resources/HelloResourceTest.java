package com.example.Practice1.resources;

import com.example.Practice1.domain.Patient;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HelloResourceTest {

    @Test
    void hello() {
        try {
            String resp = new HelloResource().hello();
            assertEquals("Welcome to hospital clients management system :)",resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void postJSON() {
        Response resp = null;
        try {
            resp = new HelloResource().postJSON("{\n" +
                    "\"patient_name\": \"Test1\",\n" +
                    "\"doctor_code\": \"A\",\n" +
                    "\"age\": 28,\n" +
                    "\"email\": “saadaliyounas@gmail.com”,\n" +
                    "\"reason\":\"diseases description\",\n" +
                    "\"fees\":1500.00\n" +
                    "}");
            assertEquals(200,resp.getStatus());
            resp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}