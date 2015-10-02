package com.webservice.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webservice.dto.EmployeeTO;

@Path("/employee")
public interface EmployeeIf {

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
	
	@PUT
	@Path("/logout")
	@Produces({MediaType.APPLICATION_JSON})
	public void logout(@Context HttpServletRequest req);
	
	
	@GET
	@Path("/employeeReport")
	@Produces({"application/pdf"})
	public Response getEmployeeReport();
	
}
