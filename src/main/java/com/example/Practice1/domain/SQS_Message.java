package com.example.Practice1.domain;

public class SQS_Message {
    String MessageId;
    String ReceiptHandle;
    String MD5OfBody;
    String Body;

    public SQS_Message(String messageId, String receiptHandle, String MD5OfBody, String body) {
        MessageId = messageId;
        ReceiptHandle = receiptHandle;
        this.MD5OfBody = MD5OfBody;
        Body = body;
    }

    public SQS_Message(){
        super();
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public String getReceiptHandle() {
        return ReceiptHandle;
    }

    public void setReceiptHandle(String receiptHandle) {
        ReceiptHandle = receiptHandle;
    }

    public String getMD5OfBody() {
        return MD5OfBody;
    }

    public void setMD5OfBody(String MD5OfBody) {
        this.MD5OfBody = MD5OfBody;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageId='" + MessageId + '\n' +
                ", ReceiptHandle='" + ReceiptHandle + '\n' +
                ", MD5OfBody='" + MD5OfBody + '\n' +
                ", Body='" + Body + '\n' +
                '}';
    }
}
