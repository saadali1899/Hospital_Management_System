package com.example.Practice1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    Patient obj = new Patient("saad", "test@test.com", "Pain", "bcsf18m543", "22:43 to 22:48", 'A', 23, 1200, 1);

    @Test
    void getPatient_name() {
        assertEquals("saad", obj.getPatient_name());
    }

    @Test
    void getEmail() {
        assertEquals("test@test.com", obj.getEmail());
    }

    @Test
    void getReason() {
        assertEquals("Pain", obj.getReason());
    }

    @Test
    void getId() {
        assertEquals("bcsf18m543", obj.getId());
    }

    @Test
    void getEstimated_time() {
        assertEquals("22:43 to 22:48", obj.getEstimated_time());
    }

    @Test
    void getDoctor_code() {
        assertEquals('A', obj.getDoctor_code());
    }

    @Test
    void getAge() {
        assertEquals(23, obj.getAge());
    }

    @Test
    void getFees() {
        assertEquals(1200, obj.getFees());
    }

    @Test
    void getWaiting_number() {
        assertEquals(1, obj.getWaiting_number());
    }

    @Test
    void calculateEstimateTime() {
        String abc = Patient.calculateEstimateTime(1);
        assertEquals("22:43 to 22:48", abc);
    }

    @Test
    void receipt() {
        String str = "{" +
                "patient_name='" + obj.getPatient_name() + '\n' +
                ", id='" + obj.getId() + '\n' +
                ", estimated_time='" + obj.getEstimated_time() + '\n' +
                ", doctor_code=" + obj.getDoctor_code() + '\n' +
                ", age=" + obj.getAge() + '\n' +
                ", waiting_number=" + obj.getWaiting_number() +
                '}';
        assertEquals(str, obj.Receipt());
    }

    @Test
    void testToString() {
        String str = "Patient{" +
                "patient_name='" + obj.getPatient_name() + '\n' +
                ", email='" + obj.getEmail() + '\n' +
                ", reason='" + obj.getReason() + '\n' +
                ", id='" + obj.getId() + '\n' +
                ", estimated_time='" + obj.getEstimated_time() + '\n' +
                ", doctor_code=" + obj.getDoctor_code() + '\n' +
                ", age=" + obj.getAge() + '\n' +
                ", fees=" + obj.getFees() + '\n' +
                ", waiting_number=" + obj.getWaiting_number() + '\n' +
                '}';
        assertEquals(str, obj.toString());
    }

    @Test
    void setId() {
        obj.setId("543");
        assertEquals("543", obj.getId());
    }

    @Test
    void setEstimated_time() {
        obj.setEstimated_time("1:00 to 1:10");
        assertEquals("1:00 to 1:10", obj.getEstimated_time());
    }

    @Test
    void setWaiting_number() {
        obj.setWaiting_number(5);
        assertEquals(5, obj.getWaiting_number());
    }

    @Test
    void checkCode() {
        obj.checkCode('A');
    }

    @Test
    void validate() {
        assertEquals(true,obj.validate());
    }

    @Test
    void setServices() {
        Patient abc = new Patient("ali","abc@abc.com","pain","543","1:00 to 1:10",'B',23,1200,5);
         obj.setServices(abc);
    }
}

