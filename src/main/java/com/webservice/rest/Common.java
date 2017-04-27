package com.webservice.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

import com.webservice.dto.BikeExpenseTO;
import com.webservice.dto.EmployeeTO;
import com.webservice.model.BikeExpense;
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
	

	private static final String DATE_TIME_FORMAT="dd-MMM-yyyy";

	public static Date convertStringToDate(String srcDate) throws ParseException{
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.parse(srcDate);
	}

	public static String convertDateToString(Date srcDate){
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.format(srcDate);
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
	

	public static BikeExpenseTO transformToBikeExpenseTO(BikeExpense bikeExpense){
		BikeExpenseTO bikeExpenseTO = new BikeExpenseTO();
		bikeExpenseTO.setAmount(bikeExpense.getAmount());
		if(bikeExpense.getEventDate() != null){
			bikeExpenseTO.setEventDate(convertDateToString(bikeExpense.getEventDate()));
		}
		bikeExpenseTO.setId(bikeExpense.getId());
		if(bikeExpense.getMeterReading() != null){
			bikeExpenseTO.setMeterReading(bikeExpense.getMeterReading().toString());
		}
		
		if(bikeExpense.getPetrolQty() != null){
			bikeExpenseTO.setPetrolQty(String.format("%.2f", bikeExpense.getPetrolQty()));
		}
		if(bikeExpense.getPricePerLtr() != null){
			bikeExpenseTO.setPricePerLtr(String.format("%.2f", bikeExpense.getPricePerLtr()));
		}
		
		bikeExpenseTO.setReason(bikeExpense.getReason());
		return bikeExpenseTO;
	}
	
	
	public static Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		Map<String, String> responseObj = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

}
