package com.webservice.report;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;



public class ReportGenerator {
	
	public ByteArrayOutputStream generateReport(ReportEvent rptEvent, String reportFormatType) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {


			JasperReport jasperReport;
			JasperPrint jasperPrint;

			InputStream jasperFile = this.getClass().getResourceAsStream("../../../../jrxml/"+rptEvent.getReportFileName());

			System.out.println("Jasper path :"+jasperFile.toString());

			jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);       
			jasperPrint=JasperFillManager.fillReport(jasperReport, rptEvent.getParams(), rptEvent.getJrd());

			if(reportFormatType.equals("pdf")){
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
				exporter.exportReport();
			}else if(reportFormatType.equals("html")){
				JRHtmlExporter jrHtmlExporter = new JRHtmlExporter();
				jrHtmlExporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "UTF-8");
				jrHtmlExporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
				jrHtmlExporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
				jrHtmlExporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
				jrHtmlExporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
				jrHtmlExporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, baos);
				jrHtmlExporter.exportReport();
			}else if(reportFormatType.equals("csv")){
				JRCsvExporter csvExporter = new JRCsvExporter();
				csvExporter.setParameter(JRCsvExporterParameter.CHARACTER_ENCODING,"UTF-8");
				csvExporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ",");
				csvExporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");
				csvExporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
				csvExporter.setParameter(JRCsvExporterParameter.IGNORE_PAGE_MARGINS, true);
				csvExporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);
				csvExporter.exportReport(); 
			}
		}
		catch(Exception qe) {
			qe.printStackTrace();
			return null;
		}

		return baos;
	}
}