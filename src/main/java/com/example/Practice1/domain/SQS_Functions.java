package com.example.Practice1.domain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.List;

public class SQS_Functions extends Thread {
    String name;
    static AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();
    public SQS_Functions(){
        super();
    }

    public SQS_Functions(String name) {
        this.name = name;
    }

    public static String SendMessage(String queueUrl, String name, String email, int waiting_number) {
        try {
            SendMessageRequest send_msg_req = new SendMessageRequest().withQueueUrl(queueUrl)
                    .withMessageBody(name + " " + email + " " + waiting_number)
                    .withDelaySeconds(0);
            SendMessageResult send_msg_rslt = sqs.sendMessage(send_msg_req);
            System.out.println("Message Sent Successfully!");
            String id = send_msg_rslt.getMessageId();
            System.out.println(id);
            return id;
        } catch (Exception e) {
            System.out.println("Failed!!");
        }

        return null;
    }

    public static String ReadMessage(String queueUrl) {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(1);
            ReceiveMessageResult message = sqs.receiveMessage(req);
            System.out.println(message);
            System.out.println("Message Received!!");
            return message.toString();
        } catch (Exception exp) {
            System.out.println("Failed!!");
        }
        return null;
    }

    public static int getMessageCount(String queueUrl) {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(10);
            List<Message> messages = sqs.receiveMessage(req).getMessages();
            //System.out.println("Count: " + messages.size());
            return messages.size();
        } catch (Exception exp) {
            System.out.println("Failed!!");
            return 0;
        }
    }

    public static void DeleteMessage(String queueUrl) {
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

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            try {

                System.out.println("Hello" + name);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //System.out.println(getMessageCount(System.getenv("docA")));
       SQS_Functions obj = new SQS_Functions("abc");
       SQS_Functions obj1 = new SQS_Functions("xyz");
       obj.start();
       obj1.start();
    }

}