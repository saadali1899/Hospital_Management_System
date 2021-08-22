package com.example.Practice1.domain;
import java.io.IOException;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class SES_Service {
    static final String FROM = "saadaliyounas@gmail.com";
    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
    public static void sendEmail(String email,String name,String waiting_number){
        try {
             final String TEXTBODY = "Hello " + name + " your checkup time has just arrived and your waiting number is " + waiting_number;
                    AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
                    .standard().withEndpointConfiguration
                            (new  AwsClientBuilder.EndpointConfiguration
                                    ("http://localhost:4566", "eu-west-1"))
                    .build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);
            client.sendEmail(request);
            System.out.println("Email has been sent successfully!!");
            //System.out.println(TEXTBODY);
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {
        //sendEmail("saad@gmail.com","Saad", "5");

    }
}
