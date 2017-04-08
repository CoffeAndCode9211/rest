package com.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/taskNotification")
public interface ChatIf {
        @GET
        @Path("/receive/{assignedTo}")
        @Produces("text/plain")
        public void hangUp(@Suspended AsyncResponse asyncResp, @PathParam("assignedTo") String assignedTo);

        @GET
        @Path("/send/{taskId}")
        @Produces("text/plain")
        public String sendMessage(final @PathParam("taskId") String nick, final String message);
        
}

