package com.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public interface CommonIf {
	
	@GET
	@Path("/send")
	public String getAuthURL();
	
	@GET
	@Path("/details")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDetails(@QueryParam(value = "code") String code);
	
	
}
