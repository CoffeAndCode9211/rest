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
		/**
		 * @return the uniqueNumber
		 */
		public String getUniqueNumber() {
			return uniqueNumber;
		}
		/**
		 * @param uniqueNumber the uniqueNumber to set
		 */
		public void setUniqueNumber(String uniqueNumber) {
			this.uniqueNumber = uniqueNumber;
		}
		/**
		 * @return the params
		 */
		public Map<String, Object> getParams() {
			return params;
		}
		/**
		 * @param params the params to set
		 */
		public void setParams(Map<String, Object> params) {
			this.params = params;
		}
		/**
		 * @return the jrd
		 */
		public JRDataSource getJrd() {
			return jrd;
		}
		/**
		 * @param jrd the jrd to set
		 */
		public void setJrd(JRDataSource jrd) {
			this.jrd = jrd;
		}
		/**
		 * @return the reportFileName
		 */
		public String getReportFileName() {
			return reportFileName;
		}
		/**
		 * @param reportFileName the reportFileName to set
		 */
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


