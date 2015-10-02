package com.webservice.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.model.Employee;
import com.webservice.report.ReportEvent;
import com.webservice.report.ReportGenerator;

@Stateless
public class ReportEJBImpl implements ReportEJBIf {

	
	@javax.annotation.Resource
	private SessionContext sctx;
	
	@PersistenceContext(unitName = "webUnit" )
	private EntityManager em;


	private static final Logger logger = LoggerFactory.getLogger(ReportEJBImpl.class);

	public ByteArrayOutputStream getReport(List<Employee> lstEmp) {
		ByteArrayOutputStream reportData = null;
		String jasperFile = "receipReport.jasper";
		try{
		
			ReportEvent reportEvent = new ReportEvent();
			
			JRDataSource jrd = new JRBeanCollectionDataSource(lstEmp,false);
			reportEvent.setJrd(jrd);
			reportEvent.setReportFileName(jasperFile);
			
			ReportGenerator portal = new ReportGenerator();
			reportData = portal.generateReport(reportEvent, "pdf");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return reportData;
	}
		
}
