package com.example.Practice1.domain;

public class SqsMessage {
    String name;
    String email;
    int waitingNumber;

    public SqsMessage(String name, String email, int waitingNumber){
        this.name = name;
        this.email = email;
        this.waitingNumber = waitingNumber;
    }
}
