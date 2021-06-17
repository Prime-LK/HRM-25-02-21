package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAttachments;
import com.navitsa.hrm.entity.EmployeeAttachmentsPK;
import com.navitsa.hrm.entity.EmployeeID;
import com.navitsa.hrm.entity.EmployeeIDPK;
import com.navitsa.hrm.entity.EmployeeIdDocument;
import com.navitsa.hrm.service.EmployeeIdDocumentService;
import com.navitsa.hrm.service.EmployeeService;

@Controller
public class EmployeeIdDocumentController {

	@Autowired
	private EmployeeIdDocumentService edService;

	@Autowired
	private EmployeeService empService;

	@RequestMapping(value = "/EmployeeDocuments", method = RequestMethod.GET)
	public String loadPage(Map<String, Object> map) {
		map.put("employeeDocumentForm", new EmployeeIdDocument());
		EmployeeIdDocument ed = new EmployeeIdDocument();
		ed.setDocTypeId("00000".substring(edService.maxEdID().length()) + edService.maxEdID());
		map.put("employeeDocumentForm", ed);
		return "hrm/employeeIdDocument";
	}

	@ModelAttribute("allEd")
	public List<EmployeeIdDocument> getAllEmployeeIdDocumentByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return edService.getAllEmployeeIdDocumentByCompany(companyId);
	}

	@RequestMapping(value = "/saveEmployeeDocument", method = RequestMethod.POST)
	public String saveEmpDoc(@Valid @ModelAttribute("employeeDocumentForm") EmployeeIdDocument ed, BindingResult br) {

		if (br.hasErrors()) {
			return "hrm/employeeIdDocument";
		} else {
			edService.saveEmpDoc(ed);
			return "redirect:/EmployeeDocuments";
		}

	}

	@RequestMapping(value = "/UpdateEmployeeDocument", method = RequestMethod.GET)
	public ModelAndView updateEmployeeDocument(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/employeeIdDocument");
		EmployeeIdDocument ed = edService.getEmpDoc(id);
		mav.addObject("employeeDocumentForm", ed);
		return mav;
	}

	// load doc_type_ID
	@ModelAttribute("docList")
	public List<EmployeeIdDocument> getAllEmployeeIdDocumentByCompany2(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return edService.getAllEmployeeIdDocumentByCompany(companyId);
	}

	// load doc_type_ID
	@ModelAttribute("emp")
	public List<Employee> getEmployees() {
		List<Employee> eList = empService.getAllEmp();
		return eList;
	}

	// save employeeID Doc
	@RequestMapping(value = "/empIDACT", method = RequestMethod.POST)
	public String savePmaster(@ModelAttribute("employeeID") EmployeeID eid) {
		try {
			edService.saveEmpIDDoc(eid);

		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/EmployeeDocument";
	}

	// load Employee id data combo box
	@ModelAttribute("DList")
	public List<EmployeeID> getDList() {
		List<EmployeeID> dlist = edService.getALLEIDDOC();
		return dlist;
	}

	// edit data
	@RequestMapping("/updateEid")
	public ModelAndView editDesignationDAta(@RequestParam("eid") String eid, @RequestParam("empdoc") String empdoc,
			@RequestParam("docid") String docid) {
		ModelAndView mav = new ModelAndView("hrm/employeeID");
		EmployeeID eID = edService.getEmpIDDataByID(eid, empdoc, docid);
		mav.addObject("employeeID", eID);
		return mav;
	}

	// employee Attachment jsp load
	@RequestMapping(value = "/employeeAttach", method = RequestMethod.GET)
	public String employeeAttachloadPage(Map<String, Object> map) {
		map.put("employeeAttachments", new EmployeeAttachments());

		EmployeeAttachmentsPK employeeAttachmentsPK = new EmployeeAttachmentsPK();
		employeeAttachmentsPK.setAttachmentID("00000".substring(edService.maxEmployeeAttachmentsID().length())
				+ edService.maxEmployeeAttachmentsID());

		map.put("maxid", employeeAttachmentsPK);
		return "hrm/employeeAttachments";
	}

	// save employee atachment
	@RequestMapping(value = "/empAttachAct", method = RequestMethod.POST)
	public String saveemployeeAttach(@ModelAttribute("employeeAttachments") EmployeeAttachments emp) {

		edService.saveEmpattach(emp);

		return "redirect:/hrm/employeeAttach";
	}

	// load saved employee attachment document
	@RequestMapping(value = "/empAttach", method = RequestMethod.GET)
	public ModelAndView getManualView(@RequestParam("empID") String empID,
			@RequestParam("attachmentID") String attachmentID) {
		ModelAndView mav = new ModelAndView("hrm/employeeAttachmentsView");
		EmployeeAttachments employeeAttachments = null;
		try {
			employeeAttachments = edService.getEmpAttachmentsDataByID(empID, attachmentID);
			mav.addObject("employeeAttachments", employeeAttachments);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mav;
	}

	// load saved employee attachment data
	@ModelAttribute("allempAttach")
	public List<EmployeeAttachments> getAllEmpAttach() {
		return edService.getALLemployeeAttach();
	}

	// edit saved empoyee attachment data
	@RequestMapping("/empAttachedit")
	public ModelAndView editEmpAttachModel(@RequestParam("empID") String empID,
			@RequestParam("attachmentID") String attachmentID) {
		ModelAndView mav = new ModelAndView("hrm/employeeAttachments");
		EmployeeAttachments employeeAttachments = null;

		try {
			employeeAttachments = edService.getEmpAttachmentsDataByID(empID, attachmentID);

			mav.addObject("employeeAttachments", employeeAttachments);

		} catch (Exception e) {
			System.out.println(e);
		}

		return mav;
	}

	// load emp related acct
	@RequestMapping(value = "/loadEmp", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeAttachments> getEmps(@RequestParam String empID) {
		List<EmployeeAttachments> ea = edService.getEmps(empID);
		return ea;
	}

	// load empoyeeID jsp
	@RequestMapping(value = "/EmployeeDocument", method = RequestMethod.GET)
	public String loademployeeID(Map<String, Object> map) {
		map.put("employeeID", new EmployeeID());

		EmployeeIDPK employeeIDPK = new EmployeeIDPK();
		employeeIDPK.setDocid(
				"0000000000".substring(edService.maxEmployeeDocMAXID().length()) + edService.maxEmployeeDocMAXID());
		map.put("emaxid", employeeIDPK);
		return "hrm/employeeID";
	}

//		//load employee attachment jsp with emp id
//				 @RequestMapping(value="/employeeAttachwithid", method = RequestMethod.GET)
//					public String employeeAttachloadPagewithid(Map<String,Object> map,@RequestParam("eid") String eid) {
//						map.put("employeeAttachments", new EmployeeAttachments());
//						map.put("eid", eid);
//						EmployeeAttachmentsPK employeeAttachmentsPK = new EmployeeAttachmentsPK();
//						employeeAttachmentsPK.setAttachmentID("0000000000".substring(edService.maxEmployeeAttachmentsID().length()) + edService.maxEmployeeAttachmentsID());
//						
//						map.put("maxid", employeeAttachmentsPK);
//						return "employeeAttachments";
//					}

	// load saved employee id details jsp document
	@RequestMapping(value = "/empdocView", method = RequestMethod.GET)
	public ModelAndView getDocumenetView(@RequestParam("eid") String eid, @RequestParam("empdoc") String empdoc,
			@RequestParam("docid") String docid) {
		ModelAndView mav = new ModelAndView("hrm/employeeDocumentsView");
		EmployeeID employeeID = null;
		try {
			employeeID = edService.getEmpIDDataByID(eid, empdoc, docid);
			mav.addObject("employeeID", employeeID);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mav;
	}

	@RequestMapping(value = "/geteidDtails", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeID> comboTable(@RequestParam("empID") String empID) {
		List<EmployeeID> listEmployeeID = edService.searchEmployeeIDEtails(empID);
		return listEmployeeID;
	}
}
