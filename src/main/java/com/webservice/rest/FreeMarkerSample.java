package com.webservice.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.dto.EmployeeTO;
import com.webservice.model.Employee;
import com.webservice.service.EmployeeEJBIf;
import com.webservice.service.ReportEJBIf;

public class FreeMarkerSample extends HttpServlet {

	private static final long serialVersionUID = 1L;


	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);

	@EJB
	private EmployeeEJBIf empEJbIf;

	@EJB
	private ReportEJBIf repEJbIf;



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<EmployeeTO> lstEmpTo = null;
		try{
			Employee emp = new Employee();
			List<Employee>  lstEmployee = empEJbIf.getEmployeesByFilter(emp);
			if(lstEmployee != null && !lstEmployee.isEmpty()){
				lstEmpTo = new ArrayList<EmployeeTO>();
				Iterator<Employee > itr = lstEmployee.iterator();
				while(itr.hasNext()){
					Employee e = itr.next();
					lstEmpTo.add(transformToEmployeeTO(e));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("employee", lstEmpTo);
		request.getRequestDispatcher("/sample.ftl").forward(request, response);
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Employee emp = new Employee();
		emp.setEmail(email);
		emp.setFirstName(firstname);
		emp.setLastName(lastname);
		emp.setPhone(phone);
		try {
			empEJbIf.addEmployee(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}


}
