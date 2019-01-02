package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_Modules")

public class Module {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "mod_ID", unique=true)
	private String mod_ID;
	
	@Column(name = "mod_Reference", unique = true)
	private String mod_Reference;
	
	@Column(name = "mod_Description")
	private String mod_Description;	

	@Column(name = "mod_SerialNumber")
	private int mod_SerialNumber;	

	@Column(name = "mod_ProjectID")
	private String mod_ProjectID;	

	@Column(name = "mod_ProjectReference")
	private String mod_ProjectReference;

	@Column(name = "mod_ApplicationID")
	private String mod_ApplicationID;	

	@Column(name = "mod_ApplicationReference")
	private String mod_ApplicationReference;

	@Column(name = "mod_DateCreated")
	private Date mod_DateCreated;	

	@Column(name = "mod_DateUpdated")
	private Date mod_DateUpdated;	

	@Column(name = "mod_Deleted")
	private boolean mod_Deleted;

	public String getModuleID() {
		return mod_ID;
	}

	public void setModuleID(String mod_ID) {
		this.mod_ID = mod_ID;
	}

	public String getModuleReference() {
		return mod_Reference;
	}

	public void setModuleReference(String mod_Reference) {
		this.mod_Reference = mod_Reference;
	}

	public String getModuleDescription() {
		return mod_Description;
	}

	public void setModuleDescription(String mod_Description) {
		this.mod_Description = mod_Description;
	}

	public int getModuleSerialNumber() {
		return mod_SerialNumber;
	}

	public void setModuleSerialNumber(int mod_SerialNumber) {
		this.mod_SerialNumber = mod_SerialNumber;
	}

	public String getModuleProjectID() {
		return mod_ProjectID;
	}

	public void setModuleProjectID(String mod_ProjectID) {
		this.mod_ProjectID = mod_ProjectID;
	}

	public String getModuleProjectReference() {
		return mod_ProjectReference;
	}

	public void setModuleProjectReference(String mod_ProjectReference) {
		this.mod_ProjectReference = mod_ProjectReference;
	}

	public String getModuleApplicationID() {
		return mod_ApplicationID;
	}

	public void setModuleApplicationID(String mod_ApplicationID) {
		this.mod_ApplicationID = mod_ApplicationID;
	}

	public String getModuleApplicationReference() {
		return mod_ApplicationReference;
	}

	public void setModuleApplicationReference(String mod_ApplicationReference) {
		this.mod_ApplicationReference = mod_ApplicationReference;
	}

	public Date getModuleDateCreated() {
		return mod_DateCreated;
	}

	public void setModuleDateCreated(Date mod_DateCreated) {
		this.mod_DateCreated = mod_DateCreated;
	}

	public Date getModuleDateUpdated() {
		return mod_DateUpdated;
	}

	public void setModuleDateUpdated(Date mod_DateUpdated) {
		this.mod_DateUpdated = mod_DateUpdated;
	}

	public boolean isModuleDeleted() {
		return mod_Deleted;
	}

	public void setModuleDeleted(boolean mod_Deleted) {
		this.mod_Deleted = mod_Deleted;
	}	
}
