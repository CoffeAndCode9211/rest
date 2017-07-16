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
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.dto.EmployeeTO;
import com.webservice.model.Property;
import com.webservice.service.PropertyEJBIf;

@Stateless
public class PropertyImpl implements PropertyIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(PropertyImpl.class);

	@EJB
	private PropertyEJBIf empEJbIf;

	
	@Inject
	private Validator validator;

	public List<EmployeeTO> getEmployeeDetails( String lastName, 
			String firstName, String email, String phone) {
		List<EmployeeTO> lstEmpTo = null;
		try{
			Property emp = new Property();
			emp.setEmail(email);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setPhone(phone);
			List<Property>  lstEmployee = empEJbIf.getPropertysByFilter(emp);
			if(lstEmployee != null && !lstEmployee.isEmpty()){
				lstEmpTo = new ArrayList<EmployeeTO>();
				Iterator<Property > itr = lstEmployee.iterator();
				while(itr.hasNext()){
					logger.info("next Data");
					Property e = itr.next();
					lstEmpTo.add(Common.transformToEmployeeTO(e));
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
			Property emp = empEJbIf.getPropertyById(id);
			if(emp != null ){
				employeeTO = Common.transformToEmployeeTO(emp);
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
			Property emp = Common.transformToEmployeeee(empTo);
			Boolean response = empEJbIf.addProperty(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Property saved successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while saving Property");
				return Response.noContent().entity(responseObj).build();
			}

		}catch (ConstraintViolationException ce) {
			logger.error("There is an ConstraintViolationException");
			builder = Common.createViolationResponse(ce.getConstraintViolations());
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

			Property emp = Common.transformToEmployeeee(empTo);
//			emp.setId(id);
			Boolean response = empEJbIf.updateProperty(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Property updated successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while updating Property");
				return Response.noContent().entity(responseObj).build();
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response deleteEmployee(int id) {
		try{

			Property emp = empEJbIf.getPropertyById(id);
			Map<String, String> responseObj = new HashMap<String, String>();
			Boolean response = empEJbIf.deleteProperty(emp);
			if(response){
				responseObj.put("Success", "Property deleted successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while deleting Property");
				return Response.noContent().entity(responseObj).build();
			}

		}catch(Exception e){
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("Error", "Error while deleting Property");
			return Response.noContent().entity(responseObj).build();
		}
	}


	public void validateEmployee(EmployeeTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<EmployeeTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}


}
