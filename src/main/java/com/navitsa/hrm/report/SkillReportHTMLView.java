package com.navitsa.hrm.report;

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
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Component("mno")
public class SkillReportHTMLView extends AbstractView{

	private JasperReport jasperReport;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 response.setContentType("text/html");
	        
	        //data source
	        List<SkillReportBean> rates = (List<SkillReportBean>) model.get("skill");
	        
	    
	        JRDataSource dataSource = getDataSource(rates);
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
	
    private JRDataSource getDataSource(List<SkillReportBean> rates) {	
    	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rates);
        return dataSource;
    }
    
    public JasperReport getReport() throws JRException {
        if (jasperReport == null) {//compile only once lazily
            InputStream stream = getClass().getResourceAsStream("/skillReport.jrxml");
            jasperReport = JasperCompileManager.compileReport(stream);
        }
        return jasperReport;
    }

}
