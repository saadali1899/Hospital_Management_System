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

    public String messageBody () {
        return "{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", waitingNumber=" + waitingNumber +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }
}
