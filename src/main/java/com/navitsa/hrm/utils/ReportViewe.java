package com.navitsa.hrm.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class ReportViewe {

	public void pdfReportViewfileDownload(String jasperName, String reportName, Collection<?> rates,
			Map<String, Object> params, HttpServletResponse response) throws Exception {

		InputStream jasperStream = getClass().getResourceAsStream("/" + jasperName);
		JasperPrint jasperPrint = null;
		if (rates != null) {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rates);
			jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);

		} else {
			jasperPrint = JasperFillManager.fillReport(jasperStream, params, new JREmptyDataSource());

		}

//		JRPdfExporter pdfExporter = new JRPdfExporter();
//		pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
//		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
//		pdfExporter.exportReport();
//
//		response.setContentType("application/pdf");
//		response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
//		response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
//
//		OutputStream responseOutputStream = response.getOutputStream();
//
//		responseOutputStream.write(pdfReportStream.toByteArray());
//		responseOutputStream.close();
//		pdfReportStream.close();
		
		
		
		
		JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[] { "sheet1" });
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader("Content-Disposition", "attachment;filename=jasperReport.xlsx");
        response.setContentType("application/octet-stream");
        exporter.exportReport();
		
		
		
		

	}

	public String pdfReportViewInlineSystemOpen(String jasperName, String reportName, Collection<?> rates,
			Map<String, Object> params, HttpServletResponse response) throws Exception {
		InputStream jasperStream = getClass().getResourceAsStream("/hrmreport/" + jasperName);
		System.out.println("Report : " + jasperStream);
		JasperPrint jasperPrint = null;
		if (rates != null) {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rates);
			jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);

		} else {
			jasperPrint = JasperFillManager.fillReport(jasperStream, params, new JREmptyDataSource());

		}
		File destFile = new File(reportName + ".pdf");

		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
		pdfExporter.exportReport();

		String pdfCode = Base64.getEncoder().encodeToString(pdfReportStream.toByteArray());
		pdfReportStream.close();
		return pdfCode;
	}

	public void htmlReportView(String jasperName, String reportName, Collection<?> rates, Map<String, Object> params,
			HttpServletResponse response) throws Exception {

		InputStream jasperStream = this.getClass().getResourceAsStream("/" + jasperName);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rates);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);
		response.setContentType("text/html");
		response.setHeader("Content-disposition", "inline; filename=" + reportName + ".pdf");

		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}
}
