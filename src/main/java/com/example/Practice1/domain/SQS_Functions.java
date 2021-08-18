package com.example.Practice1.domain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.List;

public class SQS_Functions extends Thread{
    static AmazonSQS sqs= AmazonSQSClientBuilder.standard().build();
    static String queueUrl="http://localhost:4566/000000000000/test_queue";

    public static String getQueueUrl() {
        return queueUrl;
    }

    public static void setQueueUrl(String queueUrl) {
        SQS_Functions.queueUrl = queueUrl;
    }

    public static void SendMessage(){
        try {
            SendMessageRequest send_msg_req = new SendMessageRequest().withQueueUrl(queueUrl)
                    .withMessageBody("Hello Message its a test message again!!")
                    .withDelaySeconds(0);
            SendMessageResult send_msg_rslt = sqs.sendMessage(send_msg_req);
            System.out.println("Message Sent Successfully!");
            System.out.println(send_msg_rslt.getMessageId());
        }
        catch(Exception e) {
            System.out.println("Failed!!");
        }

    }

    public static void ReadMessage() {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(1);
            ReceiveMessageResult message = sqs.receiveMessage(req);
            System.out.println(message);
            System.out.println("Message Received!!");

        } catch (Exception exp) {
            System.out.println("Failed!!");
        }
    }

    public static int getMessageCount() {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(10);
            List<Message> messages = sqs.receiveMessage(req).getMessages();
            return messages.size();
        } catch (Exception exp) {
            System.out.println("Failed!!");
            return 0;
        }
    }

    public static void DeleteMessage() {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(20).withMaxNumberOfMessages(1);
            List<Message> messages = sqs.receiveMessage(req).getMessages();
            for (Message m : messages) {
                System.out.println("Deleting Message!");
                DeleteMessageResult res = sqs.deleteMessage(queueUrl, m.getReceiptHandle());
                System.out.println(res.getSdkHttpMetadata());
                System.out.println("Message Deleted Successfully!");
            }
        } catch (Exception exp) {
            System.out.println("Failed!!");
        }
    }



}
