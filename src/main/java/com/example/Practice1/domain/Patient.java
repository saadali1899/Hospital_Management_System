package com.example.Practice1.domain;

public class Patient {
    String name;
    String email;
    String reasons;
    String p_id;
    String estimated_time;
    int age;
    int fees;
    int waiting_number;

    public Patient(){
        super();
    }

    public Patient(String name, String email, String reasons, String p_id, String estimated_time, int age, int fees, int waiting_number) {
        this.name = name;
        this.email = email;
        this.reasons = reasons;
        this.p_id = p_id;
        this.estimated_time = estimated_time;
        this.age = age;
        this.fees = fees;
        this.waiting_number = waiting_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(String estimated_time) {
        this.estimated_time = estimated_time;
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

    public int getWaiting_number() {
        return waiting_number;
    }

    public void setWaiting_number(int waiting_number) {
        this.waiting_number = waiting_number;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\n' +
                ", email='" + email + '\n' +
                ", reasons='" + reasons + '\n' +
                ", p_id='" + p_id + '\n' +
                ", estimated_time='" + estimated_time + '\n' +
                ", age=" + age + '\n' +
                ", fees=" + fees + '\n' +
                ", waiting_number=" + waiting_number +
                '}';
    }
}
