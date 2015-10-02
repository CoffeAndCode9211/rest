package com.webservice.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

import com.webservice.dto.EmployeeTO;
import com.webservice.model.Employee;

public class Common {
	
	public static EmployeeTO transformToEmployeeTO(Employee emp){
		EmployeeTO employeeTO = new EmployeeTO();
		employeeTO.setLastName(emp.getLastName());
		employeeTO.setFirstName(emp.getFirstName());
		employeeTO.setEmail(emp.getEmail());
		employeeTO.setPhone(emp.getPhone());
		if(emp.getId() != null){
			employeeTO.setId(emp.getId());
		}
		return employeeTO;
	}

	public static Employee transformToEmployee(EmployeeTO empTo){
		Employee employee = new Employee();
		employee.setLastName(empTo.getLastName());
		employee.setFirstName(empTo.getFirstName());
		employee.setEmail(empTo.getEmail());
		employee.setPhone(empTo.getPhone());
		if(empTo.getId() != null){
			employee.setId(empTo.getId());
		}

		return employee;
	}
	
	
	
	public static Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		Map<String, String> responseObj = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

}
