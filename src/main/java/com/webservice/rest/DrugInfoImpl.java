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

import com.webservice.dto.DrugInfoTO;
import com.webservice.model.DrugInfo;
import com.webservice.service.DrugInfoEJBIf;

@Stateless
public class DrugInfoImpl implements DrugInfoIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(DrugInfoImpl.class);

	@EJB
	private DrugInfoEJBIf drugInfoEJBIf;

	
	
	@Inject
	private Validator validator;

	public List<DrugInfoTO> getDrugInfoDetails(String name) {
		List<DrugInfoTO> lstEmpTo = null;
		try{
			DrugInfo emp = new DrugInfo();
			emp.setName(name);
			List<DrugInfo>  lstEmployee = drugInfoEJBIf.getDrugInfosByFilter(name);
			if(lstEmployee != null && !lstEmployee.isEmpty()){
				lstEmpTo = new ArrayList<DrugInfoTO>();
				Iterator<DrugInfo > itr = lstEmployee.iterator();
				while(itr.hasNext()){
					DrugInfo e = itr.next();
					lstEmpTo.add(Common.transformToDrugTO(e));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return lstEmpTo;
	}

	public DrugInfoTO getDrugInfoById(int id) {
		DrugInfoTO employeeTO = null;
		try{
			logger.info("id is :"+id);
			DrugInfo emp = drugInfoEJBIf.getDrugInfoById(id);
			if(emp != null ){
				employeeTO = Common.transformToDrugTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeTO;
	}

	public Response addDrugInfo(List<DrugInfoTO> lstDrugInfoTO){
		try{
			Boolean response = true;
			if(lstDrugInfoTO != null && !lstDrugInfoTO.isEmpty()){
				for(DrugInfoTO row : lstDrugInfoTO){
					validateEmployee(row);
					DrugInfo emp = Common.transformToDrugInfo(row);
					try {
						drugInfoEJBIf.addDrugInfo(emp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "DrugInfo saved successfully");
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

	public Response updateDrugInfo(DrugInfoTO empTo, int id) {
		try{

			DrugInfo emp = Common.transformToDrugInfo(empTo);
			emp.setId(id);
			Boolean response = drugInfoEJBIf.updateDrugInfo(emp);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "DrugInfo updated successfully");
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

	
	
	
	
	public void validateEmployee(DrugInfoTO empTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<DrugInfoTO>> violations = validator.validate(empTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	

}
