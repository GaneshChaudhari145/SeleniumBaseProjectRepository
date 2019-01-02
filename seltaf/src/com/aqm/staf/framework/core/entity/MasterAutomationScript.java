package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_MasterAutomationScripts")

public class MasterAutomationScript {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "masterAutomationScript_ID", unique=true)
	private String masterAutomationScript_ID;
	
	@Column(name = "masterAutomationScript_Reference", unique = true)
	private String masterAutomationScript_Reference;
	
	@Column(name = "masterAutomationScript_Description")
	private String masterAutomationScript_Description;	

	@Column(name = "masterAutomationScript_SerialNumber")
	private int masterAutomationScript_SerialNumber;	

	@Column(name = "masterAutomationScript_DateCreated")
	private Date masterAutomationScript_DateCreated;	

	@Column(name = "masterAutomationScript_DateUpdated")
	private Date masterAutomationScript_DateUpdated;	

	@Column(name = "masterAutomationScript_Deleted")
	private boolean masterAutomationScript_Deleted;

	public String getMasterAutomationScriptID() {
		return masterAutomationScript_ID;
	}

	public void setMasterAutomationScriptID(String masterAutomationScript_ID) {
		this.masterAutomationScript_ID = masterAutomationScript_ID;
	}

	public String getMasterAutomationScriptReference() {
		return masterAutomationScript_Reference;
	}

	public void setMasterAutomationScriptReference(
			String masterAutomationScript_Reference) {
		this.masterAutomationScript_Reference = masterAutomationScript_Reference;
	}

	public String getMasterAutomationScriptDescription() {
		return masterAutomationScript_Description;
	}

	public void setMasterAutomationScriptDescription(String masterAutomationScript_Description) {
		this.masterAutomationScript_Description = masterAutomationScript_Description;
	}

	public int getMasterAutomationScriptSerialNumber() {
		return masterAutomationScript_SerialNumber;
	}

	public void setMasterAutomationScriptSerialNumber(
			int masterAutomationScript_SerialNumber) {
		this.masterAutomationScript_SerialNumber = masterAutomationScript_SerialNumber;
	}

	public Date getMasterAutomationScriptDateCreated() {
		return masterAutomationScript_DateCreated;
	}

	public void setMasterAutomationScriptDateCreated(
			Date masterAutomationScript_DateCreated) {
		this.masterAutomationScript_DateCreated = masterAutomationScript_DateCreated;
	}

	public Date getMasterAutomationScriptDateUpdated() {
		return masterAutomationScript_DateUpdated;
	}

	public void setMasterAutomationScriptDateUpdated(
			Date masterAutomationScript_DateUpdated) {
		this.masterAutomationScript_DateUpdated = masterAutomationScript_DateUpdated;
	}

	public boolean isMasterAutomationScriptDeleted() {
		return masterAutomationScript_Deleted;
	}

	public void setMasterAutomationScriptDeleted(
			boolean masterAutomationScript_Deleted) {
		this.masterAutomationScript_Deleted = masterAutomationScript_Deleted;
	}
}
