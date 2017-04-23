package com.webservice.rest;

import java.io.IOException;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.common.EmailUtilities;
import com.webservice.dto.EmployeeTO;
import com.webservice.dto.MessageTO;
import com.webservice.model.Employee;
import com.webservice.service.EmployeeEJBIf;
import com.webservice.service.ReportEJBIf;

@Stateless
public class EmpServletImpl implements EmpServletIf{

	@EJB
	private EmployeeEJBIf empEJbIf;

	@EJB
	private ReportEJBIf repEJbIf;

	@Inject
	private Validator validator;

	private static HttpServletRequest request;
	private static HttpServletResponse response;
	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(EmpServletImpl.class);


	EmpServletImpl(){
		request = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
		response = ResteasyProviderFactory.getContextData(HttpServletResponse.class);
	}

	public void getEmployee(String lastName, String firstName,
			String email, String phone) {

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
					Employee e = itr.next();
					lstEmpTo.add(Common.transformToEmployeeTO(e));
				}
			}
			request.setAttribute("employee", lstEmpTo);
			request.getRequestDispatcher("../pages/sample.ftl").forward(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Response getEmployee(MessageTO msgTo) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try{
			validateEmail(msgTo);
			Boolean status = EmailUtilities.sendEmail(msgTo);
			if(status){
				responseObj.put("Success", "Email send Successfully");
				return Response.ok().entity(responseObj).build();
			}else{
				responseObj.put("Error", "Someting went wrong");
				return Response.noContent().entity(responseObj).build();
			}
		}catch (ConstraintViolationException ce) {
			logger.error("There is an ConstraintViolationException");
			builder = Common.createViolationResponse(ce.getConstraintViolations());
			return builder.build();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public void validateEmail(MessageTO emailTO) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<MessageTO>> violations = validator.validate(emailTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

}
