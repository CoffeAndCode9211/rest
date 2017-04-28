package com.webservice.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public interface CommonIf {
	
	@GET
	@Path("/fitbitlogin")
	public String getAuthURL(@Context HttpServletRequest req);
	
	@GET
	@Path("/fitbitdetails")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDetails(@QueryParam(value = "code") String code);
	
	
}
