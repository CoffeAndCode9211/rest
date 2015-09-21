package com.webservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;


public class EmployeeTO {

	private Integer id;

	@NotNull
	@Size(min = 1, message = "Please select First Name")
	@FormParam("txtEmpFirstName")
	private String firstName;

	@NotNull
	@Size(min = 1, message = "Please select Last Name")
	@FormParam("txtEmpLastName")
	private String lastName;

	@NotNull
	@Size(min = 1, message = "Please select Email")
	@FormParam("txtEmpEmail")
	private String email;

	@NotNull
	@Size(min = 1, message = "Please select Phone")
	@FormParam("txtEmpPhone")
	private String phone;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return lastName+" : "+firstName+" : "+email+" : "+phone;
	}
}
