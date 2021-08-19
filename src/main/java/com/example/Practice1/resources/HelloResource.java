package com.example.Practice1.resources;

import com.example.Practice1.domain.Patient;
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
    public Response postJSON(String payload) throws Exception{
        Patient obj = new Gson().fromJson(payload, Patient.class);
        //System.out.println(obj.toString());
        return Response.ok("Post Done!!" + obj.toString()).build();
    }


}