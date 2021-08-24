package com.example.Practice1.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Patient {
    String patient_name;
    String email;
    String reason;
    String id;
    String estimated_time;
    char doctor_code;
    int age;
    int fees;
    int waiting_number;


    public Patient(String patient_name, String email, String reason, String id, String estimated_time, char doctor_code, int age, int fees, int waiting_number) {
        this.patient_name = patient_name;
        this.email = email;
        this.reason = reason;
        this.id = id;
        this.estimated_time = estimated_time;
        this.doctor_code = doctor_code;
        this.age = age;
        this.fees = fees;
        this.waiting_number = waiting_number;
    }

    public String getPatient_name() {
        return patient_name;
    }



    public String getEmail() {
        return email;
    }


    public String getReason() {
        return reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(String estimated_time) {
        this.estimated_time = estimated_time;
    }

    public char getDoctor_code() {
        return doctor_code;
    }


    public int getAge() {
        return age;
    }


    public int getFees() {
        return fees;
    }


    public int getWaiting_number() {
        return waiting_number;
    }

    public void setWaiting_number(int waiting_number) {
        this.waiting_number = waiting_number;
    }

    public static String calculateEstimateTime(int number) {
        String start;
        String end;
        String time;
        int max = (number) * 10;
        int min = (number) * 5;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long timeInSecs = date.getTimeInMillis();
        Date startingTime = new Date(timeInSecs + (min * 60 * 1000));
        start = new String(sdf.format(startingTime));
        //long timeInSecs = date.getTimeInMillis();
        Date endingTime = new Date(timeInSecs + (max * 60 * 1000));
        end = new String(sdf.format(endingTime));
        time = start + " to " + end;
        System.out.println(time);
        return time;
    }

    public String Receipt() {
        return "Patient{" +
                "patient_name='" + patient_name + '\n' +
                ", id='" + id + '\n' +
                ", estimated_time='" + estimated_time + '\n' +
                ", doctor_code=" + doctor_code + '\n' +
                ", age=" + age + '\n' +
                ", waiting_number=" + waiting_number +
                '}';
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_name='" + patient_name + '\n' +
                ", email='" + email + '\n' +
                ", reason='" + reason + '\n' +
                ", id='" + id + '\n' +
                ", estimated_time='" + estimated_time + '\n' +
                ", doctor_code=" + doctor_code + '\n' +
                ", age=" + age + '\n' +
                ", fees=" + fees + '\n' +
                ", waiting_number=" + waiting_number + '\n' +
                '}';
    }

}
