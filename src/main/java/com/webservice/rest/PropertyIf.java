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

import com.webservice.dto.EmployeeTO;

@Path("/property")
public interface PropertyIf {

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public List<EmployeeTO> getEmployeeDetails(@QueryParam("txtEmpLastName")String lastName, 
			@QueryParam("txtEmpFirstName") String firstName, 
			@QueryParam("txtEmpEmail") String email, 
			@QueryParam("txtEmpPhone") String phone);

	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public EmployeeTO getEmployeeById(@PathParam("id") int id);

	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addEmployee( EmployeeTO empTo);

	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateEmployee(EmployeeTO sm, @PathParam("id") int id);

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteEmployee(@PathParam("id") int id);
	
	
	
}
