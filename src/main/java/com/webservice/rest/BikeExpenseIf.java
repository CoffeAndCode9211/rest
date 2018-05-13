package com.webservice.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;


import com.webservice.dto.BikeExpenseTO;

@Path("/bikeexpense")
public interface BikeExpenseIf {

	@GET
	@Path("/")
	@Timed
	@Produces({MediaType.APPLICATION_JSON})
	public List<BikeExpenseTO> getBikeExpenseDetails(@QueryParam("fromDate")String fromDate, 
			@QueryParam("toDate") String toDate);

	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public BikeExpenseTO getBikeExpenseById(@PathParam("id") int id);

	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addBikeExpense( BikeExpenseTO empTo);

	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateBikeExpense(BikeExpenseTO sm, @PathParam("id") int id);

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteBikeExpense(@PathParam("id") int id);

	
}
