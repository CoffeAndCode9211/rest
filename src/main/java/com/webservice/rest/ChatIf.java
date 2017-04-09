package com.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/")
public interface ChatIf {
	@GET
	@Path("/add/{userName}")
	@Produces("text/plain")
	public void addUser(@Suspended AsyncResponse asyncResp, @PathParam("userName") String userName);

	@GET
	@Path("/broadcast/{fromuserName}")
	@Produces("text/plain")
	public String sendMessage(final @PathParam("fromuserName") String fromuserName, 
			final @QueryParam("message") String message,
			final @QueryParam("sendtoall") String sendtoall,
			final @QueryParam("tousername") String tousername);

}

