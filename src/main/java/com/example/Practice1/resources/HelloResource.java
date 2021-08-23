package com.example.Practice1.resources;

import com.example.Practice1.domain.Patient;
import com.example.Practice1.domain.SQS_Functions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/hospital")
public class HelloResource {

    //-------------------Get Request----------------------

    @GET
    @Produces("text/plain")
    public String hello() throws Exception {
        return "Welcome to hospital clients management system :)";
    }

    //-------------------Post Request-----------------------

    @POST
    @Path("/entry")
    public Response postJSON(String payload) throws Exception {
        Patient obj = new Gson().fromJson(payload, Patient.class);
        if (obj.getDoctor_code() == 'A') {
            obj.setWaiting_number(SQS_Functions.getMessageCount(System.getenv("docA")) + 1);
        } else if (obj.getDoctor_code() == 'B') {
            obj.setWaiting_number(SQS_Functions.getMessageCount(System.getenv("docB")) + 1);
        } else if (obj.getDoctor_code() == 'C') {
            obj.setWaiting_number(SQS_Functions.getMessageCount(System.getenv("docC")) + 1);
        } else {
            obj.setWaiting_number(0);
        }
        obj.setEstimated_time(Patient.calculateEstimateTime(obj.getWaiting_number()));
        if (obj.getDoctor_code() == 'A') {
            obj.setId(SQS_Functions.SendMessage(System.getenv("docA"),obj.getPatient_name(),obj.getEmail(),obj.getWaiting_number()));
        } else if (obj.getDoctor_code() == 'B') {
            obj.setId(SQS_Functions.SendMessage(System.getenv("docB"),obj.getPatient_name(),obj.getEmail(),obj.getWaiting_number()));
        } else if (obj.getDoctor_code() == 'C') {
            obj.setId(SQS_Functions.SendMessage(System.getenv("docC"),obj.getPatient_name(),obj.getEmail(),obj.getWaiting_number()));
        } else {
            obj.setId("");
        }
        //System.out.println(obj.toString());
        return Response.ok(obj.Receipt()).build();
    }


}