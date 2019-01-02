package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_MasterAutomationScriptSteps")

public class MasterAutomationScriptStep {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "masterAutomationScriptStep_ID", unique=true)
	private String masterAutomationScriptStep_ID;
	
	@Column(name = "masterAutomationScriptStep_Reference", unique = true)
	private String masterAutomationScriptStep_Reference;
	
	@Column(name = "masterAutomationScriptStep_AutomationScriptID")
	private String masterAutomationScriptStep_AutomationScriptID;	
	
	@Column(name = "masterAutomationScriptStep_AutomationScriptReference")
	private String masterAutomationScriptStep_AutomationScriptReference;	

	@Column(name = "masterAutomationScriptStep_StepKeyword")
	private String masterAutomationScriptStep_StepKeyword;	

	@Column(name = "masterAutomationScriptStep_ConfigData")
	private String masterAutomationScriptStep_ConfigData;	

	@Column(name = "masterAutomationScriptStep_StepGroup")
	private String masterAutomationScriptStep_StepGroup;	

	@Column(name = "masterAutomationScriptStep_SerialNumber")
	private int masterAutomationScriptStep_SerialNumber;
	
	@Column(name = "masterAutomationScriptStep_ExecutionSequence")
	private int masterAutomationScriptStep_ExecutionSequence;
	
	@Column(name = "masterAutomationScriptStep_SkipStep")
	private String masterAutomationScriptStep_SkipStep;

	@Column(name = "masterAutomationScriptStep_ToBeExecutedByMachine")
	private String masterAutomationScriptStep_ToBeExecutedByMachine;	

	@Column(name = "masterAutomationScriptStep_DateCreated")
	private Date masterAutomationScriptStep_DateCreated;	

	@Column(name = "masterAutomationScriptStep_DateUpdated")
	private Date masterAutomationScriptStep_DateUpdated;	

	@Column(name = "masterAutomationScriptStep_Deleted")
	private boolean masterAutomationScriptStep_Deleted;

	public String getMasterAutomationScriptStepID() {
		return masterAutomationScriptStep_ID;
	}

	public void setMasterAutomationScriptStepID(
			String masterAutomationScriptStep_ID) {
		this.masterAutomationScriptStep_ID = masterAutomationScriptStep_ID;
	}

	public String getMasterAutomationScriptStepReference() {
		return masterAutomationScriptStep_Reference;
	}

	public void setMasterAutomationScriptStepReference(
			String masterAutomationScriptStep_Reference) {
		this.masterAutomationScriptStep_Reference = masterAutomationScriptStep_Reference;
	}

	public String getMasterAutomationScriptStepAutomationScriptID() {
		return masterAutomationScriptStep_AutomationScriptID;
	}

	public void setMasterAutomationScriptStepAutomationScriptID(
			String masterAutomationScriptStep_AutomationScriptID) {
		this.masterAutomationScriptStep_AutomationScriptID = masterAutomationScriptStep_AutomationScriptID;
	}

	public String getMasterAutomationScriptStepAutomationScriptReference() {
		return masterAutomationScriptStep_AutomationScriptReference;
	}

	public void setMasterAutomationScriptStepAutomationScriptReference(
			String masterAutomationScriptStep_AutomationScriptReference) {
		this.masterAutomationScriptStep_AutomationScriptReference = masterAutomationScriptStep_AutomationScriptReference;
	}

	public String getMasterAutomationScriptStepStepKeyword() {
		return masterAutomationScriptStep_StepKeyword;
	}

	public void setMasterAutomationScriptStepStepKeyword(
			String masterAutomationScriptStep_StepKeyword) {
		this.masterAutomationScriptStep_StepKeyword = masterAutomationScriptStep_StepKeyword;
	}

	public String getMasterAutomationScriptStepConfigData() {
		return masterAutomationScriptStep_ConfigData;
	}

	public void setMasterAutomationScriptStepConfigData(
			String masterAutomationScriptStep_ConfigData) {
		this.masterAutomationScriptStep_ConfigData = masterAutomationScriptStep_ConfigData;
	}

	public String getMasterAutomationScriptStepStepGroup() {
		return masterAutomationScriptStep_StepGroup;
	}

	public void setMasterAutomationScriptStepStepGroup(
			String masterAutomationScriptStep_StepGroup) {
		this.masterAutomationScriptStep_StepGroup = masterAutomationScriptStep_StepGroup;
	}

	public int getMasterAutomationScriptStepSerialNumber() {
		return masterAutomationScriptStep_SerialNumber;
	}

	public void setMasterAutomationScriptStepSerialNumber(
			int masterAutomationScriptStep_SerialNumber) {
		this.masterAutomationScriptStep_SerialNumber = masterAutomationScriptStep_SerialNumber;
	}

	public int getMasterAutomationScriptStepExecutionSequence() {
		return masterAutomationScriptStep_ExecutionSequence;
	}

	public void setMasterAutomationScriptStepExecutionSequence(
			int masterAutomationScriptStep_ExecutionSequence) {
		this.masterAutomationScriptStep_ExecutionSequence = masterAutomationScriptStep_ExecutionSequence;
	}

	public String getMasterAutomationScriptStepSkipStep() {
		return masterAutomationScriptStep_SkipStep;
	}

	public void setMasterAutomationScriptStepSkipStep(
			String masterAutomationScriptStep_SkipStep) {
		this.masterAutomationScriptStep_SkipStep = masterAutomationScriptStep_SkipStep;
	}

	public String getMasterAutomationScriptStepToBeExecutedByMachine() {
		return masterAutomationScriptStep_ToBeExecutedByMachine;
	}

	public void setMasterAutomationScriptStepToBeExecutedByMachine(
			String masterAutomationScriptStep_ToBeExecutedByMachine) {
		this.masterAutomationScriptStep_ToBeExecutedByMachine = masterAutomationScriptStep_ToBeExecutedByMachine;
	}

	public Date getMasterAutomationScriptStepDateCreated() {
		return masterAutomationScriptStep_DateCreated;
	}

	public void setMasterAutomationScriptStepDateCreated(
			Date masterAutomationScriptStep_DateCreated) {
		this.masterAutomationScriptStep_DateCreated = masterAutomationScriptStep_DateCreated;
	}

	public Date getMasterAutomationScriptStepDateUpdated() {
		return masterAutomationScriptStep_DateUpdated;
	}

	public void setMasterAutomationScriptStepDateUpdated(
			Date masterAutomationScriptStep_DateUpdated) {
		this.masterAutomationScriptStep_DateUpdated = masterAutomationScriptStep_DateUpdated;
	}

	public boolean isMasterAutomationScriptStepDeleted() {
		return masterAutomationScriptStep_Deleted;
	}

	public void setMasterAutomationScriptStepDeleted(
			boolean masterAutomationScriptStep_Deleted) {
		this.masterAutomationScriptStep_Deleted = masterAutomationScriptStep_Deleted;
	}

}
