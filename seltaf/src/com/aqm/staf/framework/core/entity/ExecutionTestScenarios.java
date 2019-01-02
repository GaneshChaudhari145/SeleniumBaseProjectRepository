package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_ExecutionTestScenarios")

public class ExecutionTestScenarios {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "executionTestScenario_ID", unique=true)
	private String executionTestScenario_ID;
	
	@Column(name = "executionTestScenario_Reference", unique = true)
	private String executionTestScenario_Reference;
	
	@Column(name = "executionTestScenario_Description")
	private String executionTestScenario_Description;
	
	@Column(name = "executionTestScenario_ExecutionTestSuiteID")
	private String executionTestScenario_ExecutionTestSuiteID;	

	@Column(name = "executionTestScenario_ExecutionTestSuiteReference")
	private String executionTestScenario_ExecutionTestSuiteReference;
	
	@Column(name = "executionTestScenario_TestPlanID")
	private String executionTestScenario_TestPlanID;	

	@Column(name = "executionTestScenario_TestPlanReference")
	private String executionTestScenario_TestPlanReference;
	
	@Column(name = "executionTestScenario_MasterTestSuiteID")
	private String executionTestScenario_MasterTestSuiteID;	

	@Column(name = "executionTestScenario_MasterTestSuiteReference")
	private String executionTestScenario_MasterTestSuiteReference;
	
	@Column(name = "executionTestScenario_TestSuiteID")
	private String executionTestScenario_TestSuiteID;	

	@Column(name = "executionTestScenario_TestSuiteReference")
	private String executionTestScenario_TestSuiteReference;
	
	@Column(name = "executionTestScenario_TestScenarioID")
	private String executionTestScenario_TestScenarioID;	
	
	@Column(name = "executionTestScenario_Product")
	private String executionTestScenario_Product;
	
	@Column(name = "executionTestScenario_Scripter")
	private String executionTestScenario_Scripter;
	
	@Column(name = "executionTestScenario_TestScenarioReference")
	private String executionTestScenario_TestScenarioReference;
	
	@Column(name = "executionTestScenario_SerialNumber")
	private int executionTestScenario_SerialNumber;	
	
	@Column(name = "executionTestScenario_ExecutionStatus")
	private int executionTestScenario_ExecutionStatus;	
	
	@Column(name = "executionTestScenario_LogFile")
	private String executionTestScenario_LogFile;	

	@Column(name = "executionTestScenario_SnapShotFolder")
	private String executionTestScenario_SnapShotFolder;	

	@Column(name = "executionTestScenario_NextSnapShotNumber")
	private int executionTestScenario_NextSnapShotNumber;	
	
	@Column(name = "executionTestScenario_ErrorMessage")
	private String executionTestScenario_ErrorMessage;	

	@Column(name = "executionTestScenario_NextExecutionAutomationScriptStepToBeExecutedByMachine")
	private String executionTestScenario_NextExecutionAutomationScriptStepToBeExecutedByMachine;	

	@Column(name = "executionTestScenario_NextExecutionAutomationScriptID")
	private String executionTestScenario_NextExecutionAutomationScriptID;	

	@Column(name = "executionTestScenario_NextExecutionAutomationScriptReference")
	private String executionTestScenario_NextExecutionAutomationScriptReference;	

	@Column(name = "executionTestScenario_NextExecutionAutomationScriptStepID")
	private String executionTestScenario_NextExecutionAutomationScriptStepID;	

	@Column(name = "executionTestScenario_NextExecutionAutomationScriptStepReference")
	private String executionTestScenario_NextExecutionAutomationScriptStepReference;	

	@Column(name = "executionTestScenario_NextExecutionAutomationScriptStepExecutionSequence")
	private int executionTestScenario_NextExecutionAutomationScriptStepExecutionSequence;	
	
	@Column(name = "executionTestScenario_NextExecutionBusinessDate")
	private Date executionTestScenario_NextExecutionBusinessDate;	
	
	@Column(name = "executionTestScenario_StartTime")
	private Date executionTestScenario_StartTime;	
	
	@Column(name = "executionTestScenario_EndTime")
	private Date executionTestScenario_EndTime;	

	@Column(name = "executionTestScenario_DateCreated")
	private Date executionTestScenario_DateCreated;	

	@Column(name = "executionTestScenario_DateUpdated")
	private Date executionTestScenario_DateUpdated;	

	@Column(name = "executionTestScenario_Deleted")
	private boolean executionTestScenario_Deleted;

	public String getExecutionTestScenarioID() {
		return executionTestScenario_ID;
	}

	public void setExecutionTestScenarioID(String executionTestScenario_ID) {
		this.executionTestScenario_ID = executionTestScenario_ID;
	}

	public String getExecutionTestScenarioReference() {
		return executionTestScenario_Reference;
	}

	public void setExecutionTestScenarioReference(
			String executionTestScenario_Reference) {
		this.executionTestScenario_Reference = executionTestScenario_Reference;
	}

	public String getExecutionTestScenario_Product() {
		return executionTestScenario_Product;
	}

	public void setExecutionTestScenario_Product(
			String executionTestScenario_Product) {
		this.executionTestScenario_Product = executionTestScenario_Product;
	}

	public String getExecutionTestScenario_Scripter() {
		return executionTestScenario_Scripter;
	}

	public void setExecutionTestScenario_Scripter(
			String executionTestScenario_Scripter) {
		this.executionTestScenario_Scripter = executionTestScenario_Scripter;
	}

	public String getExecutionTestScenarioDescription() {
		return executionTestScenario_Description;
	}

	public void setExecutionTestScenarioDescription(
			String executionTestScenario_Description) {
		this.executionTestScenario_Description = executionTestScenario_Description;
	}

	public String getExecutionTestScenarioExecutionTestSuiteID() {
		return executionTestScenario_ExecutionTestSuiteID;
	}

	public void setExecutionTestScenarioExecutionTestSuiteID(
			String executionTestScenario_ExecutionTestSuiteID) {
		this.executionTestScenario_ExecutionTestSuiteID = executionTestScenario_ExecutionTestSuiteID;
	}

	public String getExecutionTestScenarioExecutionTestSuiteReference() {
		return executionTestScenario_ExecutionTestSuiteReference;
	}

	public void setExecutionTestScenarioExecutionTestSuiteReference(
			String executionTestScenario_ExecutionTestSuiteReference) {
		this.executionTestScenario_ExecutionTestSuiteReference = executionTestScenario_ExecutionTestSuiteReference;
	}

	public String getExecutionTestScenarioTestPlanID() {
		return executionTestScenario_TestPlanID;
	}

	public void setExecutionTestScenarioTestPlanID(
			String executionTestScenario_TestPlanID) {
		this.executionTestScenario_TestPlanID = executionTestScenario_TestPlanID;
	}

	public String getExecutionTestScenarioTestPlanReference() {
		return executionTestScenario_TestPlanReference;
	}

	public void setExecutionTestScenarioTestPlanReference(
			String executionTestScenario_TestPlanReference) {
		this.executionTestScenario_TestPlanReference = executionTestScenario_TestPlanReference;
	}

	public String getExecutionTestScenarioMasterTestSuiteID() {
		return executionTestScenario_MasterTestSuiteID;
	}

	public void setExecutionTestScenarioMasterTestSuiteID(
			String executionTestScenario_MasterTestSuiteID) {
		this.executionTestScenario_MasterTestSuiteID = executionTestScenario_MasterTestSuiteID;
	}

	public String getExecutionTestScenarioMasterTestSuiteReference() {
		return executionTestScenario_MasterTestSuiteReference;
	}

	public void setExecutionTestScenarioMasterTestSuiteReference(
			String executionTestScenario_MasterTestSuiteReference) {
		this.executionTestScenario_MasterTestSuiteReference = executionTestScenario_MasterTestSuiteReference;
	}

	public String getExecutionTestScenarioTestSuiteID() {
		return executionTestScenario_TestSuiteID;
	}

	public void setExecutionTestScenarioTestSuiteID(
			String executionTestScenario_TestSuiteID) {
		this.executionTestScenario_TestSuiteID = executionTestScenario_TestSuiteID;
	}

	public String getExecutionTestScenarioTestSuiteReference() {
		return executionTestScenario_TestSuiteReference;
	}

	public void setExecutionTestScenarioTestSuiteReference(
			String executionTestScenario_TestSuiteReference) {
		this.executionTestScenario_TestSuiteReference = executionTestScenario_TestSuiteReference;
	}

	public String getExecutionTestScenarioTestScenarioID() {
		return executionTestScenario_TestScenarioID;
	}

	public void setExecutionTestScenarioTestScenarioID(
			String executionTestScenario_TestScenarioID) {
		this.executionTestScenario_TestScenarioID = executionTestScenario_TestScenarioID;
	}

	public String getExecutionTestScenarioTestScenarioReference() {
		return executionTestScenario_TestScenarioReference;
	}

	public void setExecutionTestScenarioTestScenarioReference(
			String executionTestScenario_TestScenarioReference) {
		this.executionTestScenario_TestScenarioReference = executionTestScenario_TestScenarioReference;
	}

	public int getExecutionTestScenarioSerialNumber() {
		return executionTestScenario_SerialNumber;
	}

	public void setExecutionTestScenarioSerialNumber(
			int executionTestScenario_SerialNumber) {
		this.executionTestScenario_SerialNumber = executionTestScenario_SerialNumber;
	}

	public int getExecutionTestScenarioExecutionStatus() {
		return executionTestScenario_ExecutionStatus;
	}

	public void setExecutionTestScenarioExecutionStatus(
			int executionTestScenario_ExecutionStatus) {
		this.executionTestScenario_ExecutionStatus = executionTestScenario_ExecutionStatus;
	}

	public String getExecutionTestScenarioLogFile() {
		return executionTestScenario_LogFile;
	}

	public void setExecutionTestScenarioLogFile(
			String executionTestScenario_LogFile) {
		this.executionTestScenario_LogFile = executionTestScenario_LogFile;
	}

	public String getExecutionTestScenarioSnapShotFolder() {
		return executionTestScenario_SnapShotFolder;
	}

	public void setExecutionTestScenarioSnapShotFolder(
			String executionTestScenario_SnapShotFolder) {
		this.executionTestScenario_SnapShotFolder = executionTestScenario_SnapShotFolder;
	}

	public int getExecutionTestScenarioNextSnapShotNumber() {
		return executionTestScenario_NextSnapShotNumber;
	}

	public void setExecutionTestScenarioNextSnapShotNumber(
			int executionTestScenario_NextSnapShotNumber) {
		this.executionTestScenario_NextSnapShotNumber = executionTestScenario_NextSnapShotNumber;
	}

	public String getExecutionTestScenarioErrorMessage() {
		return executionTestScenario_ErrorMessage;
	}

	public void setExecutionTestScenarioErrorMessage(
			String executionTestScenario_ErrorMessage) {
		this.executionTestScenario_ErrorMessage = executionTestScenario_ErrorMessage;
	}

	public String getExecutionTestScenarioNextExecutionAutomationScriptStepToBeExecutedByMachine() {
		return executionTestScenario_NextExecutionAutomationScriptStepToBeExecutedByMachine;
	}

	public void setExecutionTestScenarioNextExecutionAutomationScriptStepToBeExecutedByMachine(
			String executionTestScenario_NextExecutionAutomationScriptStepToBeExecutedByMachine) {
		this.executionTestScenario_NextExecutionAutomationScriptStepToBeExecutedByMachine = executionTestScenario_NextExecutionAutomationScriptStepToBeExecutedByMachine;
	}

	public String getExecutionTestScenarioNextExecutionAutomationScriptID() {
		return executionTestScenario_NextExecutionAutomationScriptID;
	}

	public void setExecutionTestScenarioNextExecutionAutomationScriptID(
			String executionTestScenario_NextExecutionAutomationScriptID) {
		this.executionTestScenario_NextExecutionAutomationScriptID = executionTestScenario_NextExecutionAutomationScriptID;
	}

	public String getExecutionTestScenarioNextExecutionAutomationScriptReference() {
		return executionTestScenario_NextExecutionAutomationScriptReference;
	}

	public void setExecutionTestScenarioNextExecutionAutomationScriptReference(
			String executionTestScenario_NextExecutionAutomationScriptReference) {
		this.executionTestScenario_NextExecutionAutomationScriptReference = executionTestScenario_NextExecutionAutomationScriptReference;
	}

	public String getExecutionTestScenarioNextExecutionAutomationScriptStepID() {
		return executionTestScenario_NextExecutionAutomationScriptStepID;
	}

	public void setExecutionTestScenarioNextExecutionAutomationScriptStepID(
			String executionTestScenario_NextExecutionAutomationScriptStepID) {
		this.executionTestScenario_NextExecutionAutomationScriptStepID = executionTestScenario_NextExecutionAutomationScriptStepID;
	}

	public String getExecutionTestScenarioNextExecutionAutomationScriptStepReference() {
		return executionTestScenario_NextExecutionAutomationScriptStepReference;
	}

	public void setExecutionTestScenarioNextExecutionAutomationScriptStepReference(
			String executionTestScenario_NextExecutionAutomationScriptStepReference) {
		this.executionTestScenario_NextExecutionAutomationScriptStepReference = executionTestScenario_NextExecutionAutomationScriptStepReference;
	}

	public int getExecutionTestScenarioNextExecutionAutomationScriptStepExecutionSequence() {
		return executionTestScenario_NextExecutionAutomationScriptStepExecutionSequence;
	}

	public void setExecutionTestScenarioNextExecutionAutomationScriptStepExecutionSequence(
			int executionTestScenario_NextExecutionAutomationScriptStepExecutionSequence) {
		this.executionTestScenario_NextExecutionAutomationScriptStepExecutionSequence = executionTestScenario_NextExecutionAutomationScriptStepExecutionSequence;
	}

	public Date getExecutionTestScenarioNextExecutionBusinessDate() {
		return executionTestScenario_NextExecutionBusinessDate;
	}

	public void setExecutionTestScenarioNextExecutionBusinessDate(
			Date executionTestScenario_NextExecutionBusinessDate) {
		this.executionTestScenario_NextExecutionBusinessDate = executionTestScenario_NextExecutionBusinessDate;
	}

	public Date getExecutionTestScenarioStartTime() {
		return executionTestScenario_StartTime;
	}

	public void setExecutionTestScenarioStartTime(
			Date executionTestScenario_StartTime) {
		this.executionTestScenario_StartTime = executionTestScenario_StartTime;
	}

	public Date getExecutionTestScenarioEndTime() {
		return executionTestScenario_EndTime;
	}

	public void setExecutionTestScenarioEndTime(Date executionTestScenario_EndTime) {
		this.executionTestScenario_EndTime = executionTestScenario_EndTime;
	}

	public Date getExecutionTestScenarioDateCreated() {
		return executionTestScenario_DateCreated;
	}

	public void setExecutionTestScenarioDateCreated(
			Date executionTestScenario_DateCreated) {
		this.executionTestScenario_DateCreated = executionTestScenario_DateCreated;
	}

	public Date getExecutionTestScenarioDateUpdated() {
		return executionTestScenario_DateUpdated;
	}

	public void setExecutionTestScenarioDateUpdated(
			Date executionTestScenario_DateUpdated) {
		this.executionTestScenario_DateUpdated = executionTestScenario_DateUpdated;
	}

	public boolean isExecutionTestScenarioDeleted() {
		return executionTestScenario_Deleted;
	}

	public void setExecutionTestScenarioDeleted(
			boolean executionTestScenario_Deleted) {
		this.executionTestScenario_Deleted = executionTestScenario_Deleted;
	}
}
