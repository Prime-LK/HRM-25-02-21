package com.navitsa.hrm.entity;

import java.io.IOException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "emp_attachments")
public class EmployeeAttachments {

	@EmbeddedId
	private EmployeeAttachmentsPK employeeAttachmentsPK;
	
	@Column(name="Attachment")
	private byte[] attachment;
	
	
	public EmployeeAttachments() {
		
	}
	
	
	

	public EmployeeAttachmentsPK getEmployeeAttachmentsPK() {
		return employeeAttachmentsPK;
	}

	public void setEmployeeAttachmentsPK(EmployeeAttachmentsPK employeeAttachmentsPK) {
		this.employeeAttachmentsPK = employeeAttachmentsPK;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(MultipartFile attachment) throws IOException {
		if(attachment.isEmpty()) {
			attachment=null;
		}else {
		this.attachment = attachment.getBytes();
		}
	}

	public String getattachmentView() {
		return Base64.getEncoder().encodeToString(this.attachment);
	}


	public EmployeeAttachments(EmployeeAttachmentsPK employeeAttachmentsPK, MultipartFile attachment) throws IOException {
		
		this.employeeAttachmentsPK = employeeAttachmentsPK;
		this.attachment = attachment.getBytes();
	}




	

	
	
	
	
	
	
}
