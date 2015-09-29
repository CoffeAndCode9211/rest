package com.webservice.service;

import java.io.ByteArrayOutputStream;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ReportEJBImpl implements ReportEJBIf {

	
	@javax.annotation.Resource
	private SessionContext sctx;
	
	

	private static final Logger logger = LoggerFactory.getLogger(ReportEJBImpl.class);



	public ByteArrayOutputStream getReport() {
		return null;
	}

	
//	@Override
//	public ByteArrayOutputStream getPatientAccBalanceReport( ) {
//		ByteArrayOutputStream reportData = null;
//		String jasperFile = "data.jasper";
//		try{
//		
//			ReportEvent reportEvent = new ReportEvent();
//			Map<String, Object> params = new HashMap<String, Object>();	 
//			params.put("now", dateTime);
//			reportEvent.setParams(params);	
//			
//			JRDataSource jrd = new JRBeanCollectionDataSource(colClaimReceiptsTO,false);
//			reportEvent.setJrd(jrd);
//			
//			reportEvent.setReportFileName(jasperFile);
//			
//			ReportGenerator portal = new ReportGenerator();
//			reportData = portal.generateReport(reportEvent, "pdf");
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return reportData;
//	}

		
}
