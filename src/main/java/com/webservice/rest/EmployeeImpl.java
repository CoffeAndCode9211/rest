package com.webservice.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.dto.EmployeeTO;
import com.webservice.model.Employee;
import com.webservice.service.EmployeeEJBIf;

@Stateless
public class EmployeeImpl implements EmployeeIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);

	@EJB
	private EmployeeEJBIf empEJbIf;

	@Inject
	private Validator validator;


	public List<EmployeeTO> getEmployeeDetails( String lastName, 
				String firstName, String email, String phone) {
		List<EmployeeTO> lstEmpTo = null;
		try{
			Employee emp = new Employee();
			emp.setEmail(email);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setPhone(phone);
			List<Employee>  lstEmployee = empEJbIf.getEmployeesByFilter(emp);
			if(lstEmployee != null && !lstEmployee.isEmpty()){
				lstEmpTo = new ArrayList<EmployeeTO>();
				Iterator<Employee > itr = lstEmployee.iterator();
				while(itr.hasNext()){
					logger.info("next Data");
					Employee e = itr.next();
					lstEmpTo.add(transformToEmployeeTO(e));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return lstEmpTo;
	}

	public EmployeeTO getEmployeeById(int id) {
		EmployeeTO employeeTO = null;
		try{
			logger.info("id is :"+id);
			if(empEJbIf == null){
				logger.info("hola");
			}
			Employee emp = empEJbIf.getEmployeeById(id);
			if(emp != null ){
				employeeTO = transformToEmployeeTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeTO;
	}

	public Response addEmployee(EmployeeTO empTo) {
		try{
			logger.info(empTo.toString());
			validateEmployee(empTo);
			Employee emp = transformToEmployee(empTo);
			Boolean response = empEJbIf.addEmployee(emp);
			if(response){
				return Response.ok().entity("Employee saved successfully").build();
			}else{
				return Response.noContent().entity("Error while saving Employee").build();
			}

		}catch (ConstraintViolationException ce) {
			logger.error("There is an ConstraintViolationException");
			builder = createViolationResponse(ce.getConstraintViolations());
			return builder.build();
		} catch (ValidationException e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("code", "Code Exist");
			logger.error("There is an ValidationException");
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			return builder.build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response updateEmployee(EmployeeTO empTo, int id) {
		try{

			Employee emp = transformToEmployee(empTo);
			emp.setId(id);
			Boolean response = empEJbIf.updateEmployee(emp);
			if(response){
				return Response.ok().entity("Employee updated successfully").build();
			}else{
				return Response.noContent().entity("Error while updating Employee").build();
			}

		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response deleteEmployee(int id) {
		try{

			Employee emp = empEJbIf.getEmployeeById(id);
			Boolean response = empEJbIf.deleteEmployee(emp);
			if(response){
				return Response.ok().entity("Employee deleted successfully").build();
			}else{
				return Response.noContent().entity("Error while deleting Employee").build();
			}

		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity("Error while updating Employee").build();
		}
	}

	private EmployeeTO transformToEmployeeTO(Employee emp){
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

	private Employee transformToEmployee(EmployeeTO empTo){
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


	private void validateEmployee(EmployeeTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<EmployeeTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}


	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		Map<String, String> responseObj = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}
}
