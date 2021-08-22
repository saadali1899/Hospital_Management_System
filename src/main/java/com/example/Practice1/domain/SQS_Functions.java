package com.example.Practice1.domain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.google.gson.Gson;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Random;

public class SQS_Functions extends Thread {
    String name;
    String queueUrl;
    static AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();
    public SQS_Functions(){
        super();
    }

    public SQS_Functions(String name,String queueUrl) {
        this.name = name;
        this.queueUrl=queueUrl;
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

    public String ReadMessage() {
        try {
            ReceiveMessageRequest req = new ReceiveMessageRequest().withQueueUrl(queueUrl).withVisibilityTimeout(0).withWaitTimeSeconds(10).withMaxNumberOfMessages(1);
            //ReceiveMessageResult message = sqs.receiveMessage(req);
            List<Message> messages = sqs.receiveMessage(req).getMessages();
            for (Message m : messages) {
                System.out.println("Message Received!!");
                System.out.println(m.getBody());
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

    public void DeleteMessage() {
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

        while(true) {
            try {
                if(ReadMessage() != null) {
                    Random rn = new Random();
                    int answer = rn.nextInt(10 - 5 + 1) + 5;
                    String message = ReadMessage();
                    System.out.println(message);
                    String[] features = message.split(" ");
                    String name = features[0];
                    String email = features[1];
                    String number = features[2];
                    SES_Service.sendEmail(email, name, number);
                    System.out.println("Patient Checkup Time Has Been Started..");
                    System.out.println("Process: " + name);
                    Thread.sleep(answer*60*1000);
                    System.out.println("Times up, waiting for next patient on the list...");
                    DeleteMessage();
                }
                else{
                    break;
                }
                System.out.println("No more appointments on the list..." + name);
            } catch (InterruptedException e) {
                System.out.println("Failed!!");
            }
        }
    }


    public static void main(String[] args) {
       SQS_Functions obj = new SQS_Functions("docA",System.getenv("docA"));
       SQS_Functions obj1 = new SQS_Functions("docB",System.getenv("docB"));
       SQS_Functions obj2 = new SQS_Functions("docC",System.getenv("docC"));
         obj.start();
         obj1.start();
         obj2.start();
        //System.out.println(getMessageCount(System.getenv("docA")));
        //System.out.println(getMessageCount(System.getenv("docB")));
        //System.out.println(getMessageCount(System.getenv("docC")));


    }

}