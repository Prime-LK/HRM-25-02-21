package com.prime.hrm.report;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Component("contactView")
public class EmployeeContactSummaryhtmlView extends AbstractView{
	
	  private JasperReport contactSummaryReport;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		  response.setContentType("text/html");
		  
		  //data source
		  List<EmployeeContactSummaryReportBean> contact = (List<EmployeeContactSummaryReportBean>)model.get("empcontacts");
		  
		  JRDataSource dataSource = getDataSource(contact);
		//compile jrxml template and get report
	        JasperReport report = getReport();
	        //fill the report with data source objects
	        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
	        //export to html
	        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
	        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
	        exporter.exportReport();
		
	}

	
	private JRDataSource getDataSource(List<EmployeeContactSummaryReportBean> contact) {
		// TODO Auto-generated method stub
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contact);
        return dataSource;
		
	}
	
	private JasperReport getReport() throws JRException {
		 if (contactSummaryReport == null) {//compile only once lazily
//			  InputStream stream=this.getClass().getClassLoader().getResourceAsStream("/EmployeeContactSummaryReport.jrxml");
	            InputStream stream = getClass().getResourceAsStream("/EmployeeContactSummaryReport.jrxml");
	            contactSummaryReport = JasperCompileManager.compileReport(stream);
	            
	        }
		return contactSummaryReport;
	}


}
