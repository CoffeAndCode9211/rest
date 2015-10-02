package com.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webservice.dto.MessageTO;

@Path("/empservlet")
public interface EmpServletIf {

	

	@GET
	@Path("/")
	public void getEmployee(@QueryParam("txtEmpLastName")String lastName, 
			@QueryParam("txtEmpFirstName") String firstName, 
			@QueryParam("txtEmpEmail") String email, 
			@QueryParam("txtEmpPhone") String phone);
	
	@POST
	@Path("/sendMail")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEmployee(MessageTO msgTo);
	
	
}
