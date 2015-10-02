package com.webservice.report;

import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;


public class ReportEvent implements Serializable {

	private static final long serialVersionUID = 8025997823211936893L;

	private String uniqueNumber;
	private Map<String,Object> params;
	private JRDataSource jrd;
	private String reportFileName;
	private String basePath;


	public ReportEvent() {

	}


	public String getUniqueNumber() {
		return uniqueNumber;
	}


	public void setUniqueNumber(String uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}


	public Map<String, Object> getParams() {
		return params;
	}


	public void setParams(Map<String, Object> params) {
		this.params = params;
	}


	public JRDataSource getJrd() {
		return jrd;
	}


	public void setJrd(JRDataSource jrd) {
		this.jrd = jrd;
	}


	public String getReportFileName() {
		return reportFileName;
	}


	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}


	public String getBasePath() {
		return basePath;
	}


	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}


