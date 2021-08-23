package com.example.Practice1.domain;

public class Messages {
    String Body;

    public Messages(){
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
