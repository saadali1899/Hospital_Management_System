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
        if (number == 1) {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            long timeInSecs = date.getTimeInMillis();
            Date startingTime = new Date(timeInSecs);
            start = new String(sdf.format(startingTime));
            Date endingTime = new Date(timeInSecs + (10 * 60 * 1000));
            end = new String(sdf.format(endingTime));
            time = start + " to " + end;

            return time;

        } else {
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
    }

    public String Receipt() {
        return "{" +
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

    public boolean checkCode(char code){
        if(code == 'A' || code == 'B' || code == 'C')
            return true;
        return false;
    }

    public boolean validate() {
       if((patient_name != null) &&
               (checkCode(doctor_code)==true) &&
               (age != 0) &&
               (email != null) &&
               (reason != null) &&
               (fees != 0)) {
           return true;
       }
       return false;
    }

    public void setServices(Patient obj){
        if (obj.getDoctor_code() == 'A') {
            obj.setWaiting_number(SqsFunctions.getMessageCount(System.getenv("docA")) + 1);
        } else if (obj.getDoctor_code() == 'B') {
            obj.setWaiting_number(SqsFunctions.getMessageCount(System.getenv("docB")) + 1);
        } else if (obj.getDoctor_code() == 'C') {
            obj.setWaiting_number(SqsFunctions.getMessageCount(System.getenv("docC")) + 1);
        }  else {
            obj.setWaiting_number(0);
        }
        obj.setEstimated_time(Patient.calculateEstimateTime(obj.getWaiting_number()));
        if (obj.getDoctor_code() == 'A') {
            obj.setId(SqsFunctions.sendMessage(System.getenv("docA"), obj.getPatient_name(), obj.getEmail(), obj.getWaiting_number()));
        } else if (obj.getDoctor_code() == 'B') {
            obj.setId(SqsFunctions.sendMessage(System.getenv("docB"), obj.getPatient_name(), obj.getEmail(), obj.getWaiting_number()));
        } else if (obj.getDoctor_code() == 'C') {
            obj.setId(SqsFunctions.sendMessage(System.getenv("docC"), obj.getPatient_name(), obj.getEmail(), obj.getWaiting_number()));
        } else {
            obj.setId("");
        }
    }

}
