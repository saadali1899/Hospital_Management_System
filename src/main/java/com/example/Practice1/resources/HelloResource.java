package com.example.Practice1.resources;

import com.example.Practice1.domain.Patient;
import com.example.Practice1.domain.SqsFunctions;
import com.google.gson.Gson;

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
        if(obj.validate()) {
            obj.setServices(obj);
        }
        else{
            System.out.println("Invalid Data!!");
        }
        //System.out.println(obj.toString());
        return Response.ok(obj.Receipt()).build();
    }


}