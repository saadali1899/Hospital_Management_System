package com.example.Practice1.domain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

public class SqsFunctions extends Thread {
    String name;
    String queueUrl;
    static AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();

    public SqsFunctions(String name, String queueUrl) {
        this.name = name;
        this.queueUrl=queueUrl;
    }

    public static String sendMessage(String queueUrl, String name, String email, int waiting_number) {
        try {
            SqsMessage obj = new SqsMessage(name,email,waiting_number);
            SendMessageRequest send_msg_req = new SendMessageRequest().withQueueUrl(queueUrl)
                    .withMessageBody(obj.messageBody())
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

    public String readMessage() {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(1);
            //ReceiveMessageResult message = sqs.receiveMessage(req);
            List<Message> messages = sqs.receiveMessage(req).getMessages();
            for (Message m : messages) {
                System.out.println("Message Received!!");
                //System.out.println(m.getBody());
                return m.getBody();
            }
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

    public void deleteMessage() {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(20).withMaxNumberOfMessages(1);
            List<Message> messages = sqs.receiveMessage(req).getMessages();
            for (Message m : messages) {
                System.out.println("Removing patient from the appointment list!");
                DeleteMessageResult res = sqs.deleteMessage(queueUrl, m.getReceiptHandle());
                //System.out.println(res.getSdkHttpMetadata());
                System.out.println("Patient removed Successfully!");
            }
        } catch (Exception exp) {
            System.out.println("Failed!!");
        }
    }

    public void execution(){
        try {
            if (readMessage() != null) {
                Random rn = new Random();
                int answer = rn.nextInt(10 - 5 + 1) + 5;
                String message = readMessage();
                System.out.println(message);
                SqsMessage obj = new Gson().fromJson(message, SqsMessage.class);
                SesService.sendEmail(obj.getEmail(), obj.name, obj.getWaitingNumber());
                System.out.println("Patient Checkup Time Has Been Started..");
                System.out.println("Process: " + name);
                Thread.sleep(answer*60*1000);
                System.out.println(obj.getName() + " checkup has been completed.. Total Time: " + answer + " mins :)");
                deleteMessage();
            } else {
                System.out.println(name + " Checking for new appointments...");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Failed!!");
        }
    }

    @Override
    public void run() {

        while (true) {
            execution();
        }
    }

    public static void main(String[] args) {
       SqsFunctions obj = new SqsFunctions("docA",System.getenv("docA"));
       SqsFunctions obj1 = new SqsFunctions("docB",System.getenv("docB"));
       SqsFunctions obj2 = new SqsFunctions("docC",System.getenv("docC"));
        System.out.println("---Queues Status---");
        System.out.println("DocA: " + getMessageCount(System.getenv("docA")));
        System.out.println("DocB: " + getMessageCount(System.getenv("docB")));
        System.out.println("DocB: " + getMessageCount(System.getenv("docC")));
         obj.start();
         obj1.start();
         obj2.start();


    }
}