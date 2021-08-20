package com.example.Practice1.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Patient {
    String patient_name;
    String email;
    String reasons;
    String id;
    String estimated_time;
    char doctor_code;
    int age;
    int fees;
    int waiting_number;

    public Patient(){
        super();
    }

    public Patient(String patient_name, String email, String reasons, String id, String estimated_time, char doctor_code, int age, int fees, int waiting_number) {
        this.patient_name = patient_name;
        this.email = email;
        this.reasons = reasons;
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

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
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

    public void setDoctor_code(char doctor_code) {
        this.doctor_code = doctor_code;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getWaiting_number()  {
        return waiting_number;
    }

    public void setWaiting_number(int waiting_number) {
        this.waiting_number = waiting_number;
    }

    public static String calculateEstimateTime(int number) {
        String start;
        String end;
        String time;
        int max=(number)*10;
        int min=(number)*5;
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
                ", age=" + age + '\n'+
                ", waiting_number=" + waiting_number +
                '}';
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_name='" + patient_name + '\'' +
                ", email='" + email + '\'' +
                ", reasons='" + reasons + '\'' +
                ", id='" + id + '\'' +
                ", estimated_time='" + estimated_time + '\'' +
                ", doctor_code=" + doctor_code +
                ", age=" + age +
                ", fees=" + fees +
                ", waiting_number=" + waiting_number +
                '}';
    }

    public static void main(String[] args) {
        calculateEstimateTime(5);
    }
}
