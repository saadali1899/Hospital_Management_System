package com.example.Practice1.domain;

public class SQS_Message {
    String Body;

    public SQS_Message(){
        super();
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    @Override
    public String toString() {
        return "SQS_Message{" +
                "Body='" + Body + '\n' +
                '}';
    }
}
