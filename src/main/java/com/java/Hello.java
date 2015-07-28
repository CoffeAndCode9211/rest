package com.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {

    @GET
    @Path("/{pathParameter}")
    public Response responseMsg( @PathParam("pathParameter") String pathParameter
            ) {

        String response = "Hello from: " + pathParameter;

        return Response.status(200).entity(response).build();
    }
}
