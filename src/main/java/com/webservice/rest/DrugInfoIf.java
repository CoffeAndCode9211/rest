package com.webservice.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webservice.dto.DrugInfoTO;

@Path("/druginfo")
public interface DrugInfoIf {

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public List<DrugInfoTO> getDrugInfoDetails(@QueryParam("name")String lastName);

	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public DrugInfoTO getDrugInfoById(@PathParam("id") int id);

	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addDrugInfo( List<DrugInfoTO> lstDrugInfoTO);

	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateDrugInfo(DrugInfoTO sm, @PathParam("id") int id);

	
	
}
