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

import com.webservice.dto.BikeExpenseTO;
import com.webservice.model.BikeExpense;
import com.webservice.service.BikeExpenseEJBIf;

@Stateless
public class BikeExpenseImpl implements BikeExpenseIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(BikeExpenseImpl.class);

	@EJB
	private BikeExpenseEJBIf bikeExpenseEJBIf;

	
	@Inject
	private Validator validator;

	public List<BikeExpenseTO> getBikeExpenseDetails( String lastName, 
			String firstName) {
		List<BikeExpenseTO> lstEmpTo = null;
		try{
			BikeExpenseTO emp = new BikeExpenseTO();
			
			List<BikeExpense>  lstEmployee = bikeExpenseEJBIf.getBikeExpensesByFilter(emp);
			if(lstEmployee != null && !lstEmployee.isEmpty()){
				lstEmpTo = new ArrayList<BikeExpenseTO>();
				Iterator<BikeExpense > itr = lstEmployee.iterator();
				while(itr.hasNext()){
					logger.info("next Data");
					BikeExpense e = itr.next();
					lstEmpTo.add(Common.transformToBikeExpenseTO(e));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return lstEmpTo;
	}

	public BikeExpenseTO getBikeExpenseById(int id) {
		BikeExpenseTO BikeExpenseTO = null;
		try{
			logger.info("id is :"+id);
			BikeExpense emp = bikeExpenseEJBIf.getBikeExpenseById(id);
			if(emp != null ){
				BikeExpenseTO = Common.transformToBikeExpenseTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return BikeExpenseTO;
	}

	public Response addBikeExpense(BikeExpenseTO empTo) {
		try{
			logger.info(empTo.toString());
			validateEmployee(empTo);
			BikeExpense emp = null;
			Boolean response = bikeExpenseEJBIf.addBikeExpense(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Employee saved successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while saving Employee");
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

	public Response updateBikeExpense(BikeExpenseTO empTo, int id) {
		try{

			BikeExpense emp = new BikeExpense();
			emp.setId(id);
			Boolean response = bikeExpenseEJBIf.updateBikeExpense(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Employee updated successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while updating Employee");
				return Response.noContent().entity(responseObj).build();
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}

	public Response deleteBikeExpense(int id) {
		try{

			BikeExpense emp = bikeExpenseEJBIf.getBikeExpenseById(id);
			Boolean response = bikeExpenseEJBIf.deleteBikeExpense(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "Employee deleted successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while deleting Employee");
				return Response.noContent().entity(responseObj).build();
			}

		}catch(Exception e){
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("Error", "Error while deleting Employee");
			return Response.noContent().entity(responseObj).build();
		}
	}

	
	
	
	public void validateEmployee(BikeExpenseTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<BikeExpenseTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
