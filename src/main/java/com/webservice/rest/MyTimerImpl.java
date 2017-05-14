package com.webservice.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

import com.webservice.dto.MyTimerTO;
import com.webservice.model.MyTimer;
import com.webservice.service.MyTimerEJBIf;

@Stateless
public class MyTimerImpl implements MyTimerIf{

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(MyTimerImpl.class);

	@EJB
	private MyTimerEJBIf myTimerEJbIf;

	@Inject
	private Validator validator;

	public List<MyTimerTO> getMyTimerDetails(String reason) {
		List<MyTimerTO> lstMyTimerTo = new ArrayList<MyTimerTO>();
		try{
			MyTimer myTimer = new MyTimer();
			myTimer.setReason(reason);
			List<MyTimer>  lstMyTimer = myTimerEJbIf.getMyTimersByFilter(myTimer);
			if(lstMyTimer != null && !lstMyTimer.isEmpty()){
				for(MyTimer myTimerrr : lstMyTimer){
					lstMyTimerTo.add(Common.transformToMyTimerTO(myTimerrr));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lstMyTimerTo;
	}

	public MyTimerTO getMyTimerById(int id) {
		MyTimerTO employeeTO = null;
		try{
			MyTimer emp = myTimerEJbIf.getMyTimerById(id);
			if(emp != null ){
				employeeTO = Common.transformToMyTimerTO(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeTO;
	}

	public Response addUpdDelMyTimer(MyTimerTO empTo) {
		try{
			logger.info(empTo.toString());
			validateMyTimer(empTo);
			MyTimer myTimer = Common.transformToMyTimer(empTo);
			Boolean response = myTimerEJbIf.addUpdateDelMyTimer(myTimer);
			Map<String, String> responseObj = new HashMap<String, String>();
			if(response){
				responseObj.put("Success", "MyTimer saved successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Error while saving MyTimer");
				return Response.noContent().entity(responseObj).build();
			}

		}catch (ConstraintViolationException ce) {
			logger.error("There is an ConstraintViolationException");
			builder = Common.createViolationResponse(ce.getConstraintViolations());
			return builder.build();
		} catch(Exception e){
			e.printStackTrace();
			return Response.noContent().entity(e.getMessage()).build();
		}
	}


	public void validateMyTimer(MyTimerTO mytimetTo) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<MyTimerTO>> violations = validator.validate(mytimetTo);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
