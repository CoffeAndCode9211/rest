package com.webservice.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;

import com.webservice.dto.EmployeeTO;

@Path("/employee")
public interface EmployeeIf {

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public List<EmployeeTO> getEmployeeDetails(@Form EmployeeTO sm);

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public EmployeeTO getEmployeeById(@PathParam("id") int id);

	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addEmployee(@Form EmployeeTO empTo);

	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateEmployee(@Form EmployeeTO sm, @PathParam("id") int id);

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response deleteEmployee(@PathParam("id") int id);

}
