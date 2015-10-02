package com.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/empservlet")
public interface EmpServletIf {

	

	@GET
	@Path("/")
	public void getEmployee(@QueryParam("txtEmpLastName")String lastName, 
			@QueryParam("txtEmpFirstName") String firstName, 
			@QueryParam("txtEmpEmail") String email, 
			@QueryParam("txtEmpPhone") String phone);
	
	
}
