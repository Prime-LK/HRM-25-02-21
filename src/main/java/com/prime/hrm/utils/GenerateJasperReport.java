package com.prime.hrm.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class GenerateJasperReport {

	private static GenerateJasperReport generateJasperReport;

	public JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException {
		System.out.println(
				"path " + request.getSession().getServletContext().getRealPath("/WEB-INF/classes/hrmreport/" + fileName + ".jasper"));
		File reportFile = new File(
				request.getSession().getServletContext().getRealPath("/WEB-INF/classes/hrmreport/" + fileName + ".jasper"));
		// If compiled file is not found, then compile XML template
		if (!reportFile.exists()) {
			JasperCompileManager.compileReportToFile(
					request.getSession().getServletContext().getRealPath("/WEB-INF/classes/hrmreport/" + fileName + ".jrxml"),
					request.getSession().getServletContext().getRealPath("/WEB-INF/classes/hrmreport/" + fileName + ".jasper"));
		}
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}

	public void generateReportHtml(JasperPrint jasperPrint, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, JRException {

		HtmlExporter exporter = new HtmlExporter();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		jasperPrintList.add(jasperPrint);
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(resp.getWriter()));
		SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}

	public void generatePDFReportFromDBConnection(HttpServletResponse resp, Map<String, Object> parameters,
			JasperReport jasperReport, Connection connection, String fileName)
			throws JRException, NamingException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, connection);
		resp.reset();
		resp.resetBuffer();
		resp.setContentType("application/pdf");
		resp.setContentLength(bytes.length);
		resp.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
		ServletOutputStream ouputStream = resp.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
	}

	public void generateDownloadablePDFReportFromBeanCollection(HttpServletResponse resp,
			Map<String, Object> parameters, JasperReport jasperReport, JRDataSource dataSource, String fileName)
			throws JRException, NamingException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, dataSource);
		resp.reset();
		resp.resetBuffer();
		resp.setContentType("application/pdf");
		resp.setContentLength(bytes.length);
		resp.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
		ServletOutputStream ouputStream = resp.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
	}

	public String generatePDFReportFromBeanCollection(HttpServletResponse resp, Map<String, Object> parameters,
			JasperReport jasperReport, JRDataSource dataSource, String fileName)
			throws JRException, NamingException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, dataSource);
		String pdfCode = Base64.getEncoder().encodeToString(bytes);
		return pdfCode;
	}

	public String generateReportInSystemView(String fileName, String reportName, Collection<?> list,
			Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) throws Exception {
		// InputStream jasperStream = getClass().getResourceAsStream("/" + jasperName);
		JasperReport jasperReport = getCompiledFile(fileName, request);
		JasperPrint jasperPrint = null;
		if (list != null) {

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		} else {
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

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

	public String pdfReportViewInlineSystemOpen(String jasperName, String reportName, Collection<?> rates,
			Map<String, Object> params) throws Exception {

		InputStream jasperStream = getClass().getResourceAsStream("/" + jasperName);

		System.out.println("jasperStream=" + jasperStream);
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
	
	public String pdfReportOpenInSystem(String fileName, String reportName, Collection<?> list,
			Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) throws Exception {
		//InputStream jasperStream = getClass().getResourceAsStream("/hrmreport/" + jasperName);
		JasperReport jasperReport = getCompiledFile(fileName, request);
		JasperPrint jasperPrint = null;
		if (list != null) {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		} else {
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

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

	public static GenerateJasperReport getInstance() {
		return (generateJasperReport == null) ? (generateJasperReport = new GenerateJasperReport())
				: generateJasperReport;
	}
}
