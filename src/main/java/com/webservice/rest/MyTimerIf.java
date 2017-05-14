package com.webservice.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webservice.dto.MyTimerTO;

@Path("/mytimer")
public interface MyTimerIf {

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MyTimerTO> getMyTimerDetails(@QueryParam("reason")String reason);

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public MyTimerTO getMyTimerById(@PathParam("id") int id);

	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addUpdDelMyTimer( MyTimerTO empTo);

	
}
