package com.webservice.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.webservice.model.Employee;


public interface ReportEJBIf {

		
	public ByteArrayOutputStream getReport(List<Employee> lstEmp);

	

	
}
