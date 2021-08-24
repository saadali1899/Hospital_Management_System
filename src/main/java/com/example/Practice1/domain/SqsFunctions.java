package com.example.Practice1.domain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

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
                String[] features = message.split(" ");
                String name = features[0];
                String email = features[1];
                String number = features[2];
                SesService.sendEmail(email, name, number);
                System.out.println("Patient Checkup Time Has Been Started..");
                System.out.println("Process: " + name);
                Thread.sleep(answer*60*1000);
                System.out.println(name + " checkup has been completed.. Total Time: " + answer + " mins :)");
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
        /* obj.start();
         obj1.start();
         obj2.start();*/

        //System.out.println(getMessageCount(System.getenv("docA")));
        //System.out.println(getMessageCount(System.getenv("docB")));
        //System.out.println(getMessageCount(System.getenv("docC")));

    }
}