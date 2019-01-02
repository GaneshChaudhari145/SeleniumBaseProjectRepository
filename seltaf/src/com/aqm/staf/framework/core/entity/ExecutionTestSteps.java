package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_ExecutionTestSteps")

public class ExecutionTestSteps {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "executionTestStep_ID", unique=true)
	private String executionTestStep_ID;
	
	@Column(name = "executionTestStep_Reference", unique = true)
	private String executionTestStep_Reference;
	
	@Column(name = "executionTestStep_Description")
	private String executionTestStep_Description;
	
	@Column(name = "executionTestStep_ExecutionTestScenarioID")
	private String executionTestStep_ExecutionTestScenarioID;	

	@Column(name = "executionTestStep_ExecutionTestScenarioReference")
	private String executionTestStep_ExecutionTestScenarioReference;
	
	@Column(name = "executionTestStep_TestPlanID")
	private String executionTestStep_TestPlanID;	

	@Column(name = "executionTestStep_TestPlanReference")
	private String executionTestStep_TestPlanReference;
	
	@Column(name = "executionTestStep_MasterTestSuiteID")
	private String executionTestStep_MasterTestSuiteID;	

	@Column(name = "executionTestStep_MasterTestSuiteReference")
	private String executionTestStep_MasterTestSuiteReference;
	
	@Column(name = "executionTestStep_TestSuiteID")
	private String executionTestStep_TestSuiteID;	

	@Column(name = "executionTestStep_TestSuiteReference")
	private String executionTestStep_TestSuiteReference;
	
	@Column(name = "executionTestStep_TestScenarioID")
	private String executionTestStep_TestScenarioID;	

	@Column(name = "executionTestStep_TestScenarioReference")
	private String executionTestStep_TestScenarioReference;
	
	@Column(name = "executionTestStep_MasterAutomationScriptID")
	private String executionTestStep_MasterAutomationScriptID;
	
	@Column(name = "executionTestStep_MasterAutomationScriptReference")
	private String executionTestStep_MasterAutomationScriptReference;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepID")
	private String executionTestStep_MasterAutomationScriptStepID;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepReference")
	private String executionTestStep_MasterAutomationScriptStepReference;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepKeyword")
	private String executionTestStep_MasterAutomationScriptStepKeyword;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepConfigData")
	private String executionTestStep_MasterAutomationScriptStepConfigData;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepStepGroup")
	private String executionTestStep_MasterAutomationScriptStepStepGroup;

	@Column(name = "executionTestStep_MasterAutomationScriptStepExecutionSequence")
	private int executionTestStep_MasterAutomationScriptStepExecutionSequence;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepSkipStep")
	private String executionTestStep_MasterAutomationScriptStepSkipStep;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepToBeExecutedOnMachine")
	private String executionTestStep_MasterAutomationScriptStepToBeExecutedOnMachine;
	
	@Column(name = "executionTestStep_MasterAutomationScriptStepLog")
	private String executionTestStep_MasterAutomationScriptStepLog;

	@Column(name = "executionTestStep_ExecutionStatus")
	private int executionTestStep_ExecutionStatus;	
	
	@Column(name = "executionTestStep_LogMessage")
	private String executionTestStep_LogMessage;
	
	@Column(name = "executionTestStep_NextExecutionBusinessDate")
	private Date executionTestStep_NextExecutionBusinessDate;	
	
	@Column(name = "executionTestStep_StartTime")
	private Date executionTestStep_StartTime;	
	
	@Column(name = "executionTestStep_EndTime")
	private Date executionTestStep_EndTime;	

	@Column(name = "executionTestStep_DateCreated")
	private Date executionTestStep_DateCreated;	

	@Column(name = "executionTestStep_DateUpdated")
	private Date executionTestStep_DateUpdated;	

	@Column(name = "executionTestStep_Deleted")
	private boolean executionTestStep_Deleted;
	
	public String getExecutionTestStepID() {
		return executionTestStep_ID;
	}

	public void setExecutionTestStepID(String executionTestStep_ID) {
		this.executionTestStep_ID = executionTestStep_ID;
	}

	public String getExecutionTestStepReference() {
		return executionTestStep_Reference;
	}

	public void setExecutionTestStepReference(String executionTestStep_Reference) {
		this.executionTestStep_Reference = executionTestStep_Reference;
	}

	public String getExecutionTestStepDescription() {
		return executionTestStep_Description;
	}

	public void setExecutionTestStepDescription(
			String executionTestStep_Description) {
		this.executionTestStep_Description = executionTestStep_Description;
	}

	public String getExecutionTestStepExecutionTestScenarioID() {
		return executionTestStep_ExecutionTestScenarioID;
	}

	public void setExecutionTestStepExecutionTestScenarioID(
			String executionTestStep_ExecutionTestScenarioID) {
		this.executionTestStep_ExecutionTestScenarioID = executionTestStep_ExecutionTestScenarioID;
	}

	public String getExecutionTestStepExecutionTestScenarioReference() {
		return executionTestStep_ExecutionTestScenarioReference;
	}

	public void setExecutionTestStepExecutionTestScenarioReference(
			String executionTestStep_ExecutionTestScenarioReference) {
		this.executionTestStep_ExecutionTestScenarioReference = executionTestStep_ExecutionTestScenarioReference;
	}

	public String getExecutionTestStepTestPlanID() {
		return executionTestStep_TestPlanID;
	}

	public void setExecutionTestStepTestPlanID(String executionTestStep_TestPlanID) {
		this.executionTestStep_TestPlanID = executionTestStep_TestPlanID;
	}

	public String getExecutionTestStepTestPlanReference() {
		return executionTestStep_TestPlanReference;
	}

	public void setExecutionTestStepTestPlanReference(
			String executionTestStep_TestPlanReference) {
		this.executionTestStep_TestPlanReference = executionTestStep_TestPlanReference;
	}

	public String getExecutionTestStepMasterTestSuiteID() {
		return executionTestStep_MasterTestSuiteID;
	}

	public void setExecutionTestStepMasterTestSuiteID(
			String executionTestStep_MasterTestSuiteID) {
		this.executionTestStep_MasterTestSuiteID = executionTestStep_MasterTestSuiteID;
	}

	public String getExecutionTestStepMasterTestSuiteReference() {
		return executionTestStep_MasterTestSuiteReference;
	}

	public void setExecutionTestStepMasterTestSuiteReference(
			String executionTestStep_MasterTestSuiteReference) {
		this.executionTestStep_MasterTestSuiteReference = executionTestStep_MasterTestSuiteReference;
	}

	public String getExecutionTestStepTestSuiteID() {
		return executionTestStep_TestSuiteID;
	}

	public void setExecutionTestStepTestSuiteID(
			String executionTestStep_TestSuiteID) {
		this.executionTestStep_TestSuiteID = executionTestStep_TestSuiteID;
	}

	public String getExecutionTestStepTestSuiteReference() {
		return executionTestStep_TestSuiteReference;
	}

	public void setExecutionTestStepTestSuiteReference(
			String executionTestStep_TestSuiteReference) {
		this.executionTestStep_TestSuiteReference = executionTestStep_TestSuiteReference;
	}

	public String getExecutionTestStepTestScenarioID() {
		return executionTestStep_TestScenarioID;
	}

	public void setExecutionTestStepTestScenarioID(
			String executionTestStep_TestScenarioID) {
		this.executionTestStep_TestScenarioID = executionTestStep_TestScenarioID;
	}

	public String getExecutionTestStepTestScenarioReference() {
		return executionTestStep_TestScenarioReference;
	}

	public void setExecutionTestStepTestScenarioReference(
			String executionTestStep_TestScenarioReference) {
		this.executionTestStep_TestScenarioReference = executionTestStep_TestScenarioReference;
	}

	public String getExecutionTestStepMasterAutomationScriptID() {
		return executionTestStep_MasterAutomationScriptID;
	}

	public void setExecutionTestStepMasterAutomationScriptID(
			String executionTestStep_MasterAutomationScriptID) {
		this.executionTestStep_MasterAutomationScriptID = executionTestStep_MasterAutomationScriptID;
	}

	public String getExecutionTestStepMasterAutomationScriptReference() {
		return executionTestStep_MasterAutomationScriptReference;
	}

	public void setExecutionTestStepMasterAutomationScriptReference(
			String executionTestStep_MasterAutomationScriptReference) {
		this.executionTestStep_MasterAutomationScriptReference = executionTestStep_MasterAutomationScriptReference;
	}

	public String getExecutionTestStepMasterAutomationScriptStepID() {
		return executionTestStep_MasterAutomationScriptStepID;
	}

	public void setExecutionTestStepMasterAutomationScriptStepID(
			String executionTestStep_MasterAutomationScriptStepID) {
		this.executionTestStep_MasterAutomationScriptStepID = executionTestStep_MasterAutomationScriptStepID;
	}

	public String getExecutionTestStepMasterAutomationScriptStepReference() {
		return executionTestStep_MasterAutomationScriptStepReference;
	}

	public void setExecutionTestStepMasterAutomationScriptStepReference(
			String executionTestStep_MasterAutomationScriptStepReference) {
		this.executionTestStep_MasterAutomationScriptStepReference = executionTestStep_MasterAutomationScriptStepReference;
	}

	public String getExecutionTestStepMasterAutomationScriptStepKeyword() {
		return executionTestStep_MasterAutomationScriptStepKeyword;
	}

	public void setExecutionTestStepMasterAutomationScriptStepKeyword(
			String executionTestStep_MasterAutomationScriptStepKeyword) {
		this.executionTestStep_MasterAutomationScriptStepKeyword = executionTestStep_MasterAutomationScriptStepKeyword;
	}

	public String getExecutionTestStepMasterAutomationScriptStepConfigData() {
		return executionTestStep_MasterAutomationScriptStepConfigData;
	}

	public void setExecutionTestStepMasterAutomationScriptStepConfigData(
			String executionTestStep_MasterAutomationScriptStepConfigData) {
		this.executionTestStep_MasterAutomationScriptStepConfigData = executionTestStep_MasterAutomationScriptStepConfigData;
	}
	
	public String getExecutionTestStepMasterAutomationScriptStepStepGroup() {
		return executionTestStep_MasterAutomationScriptStepStepGroup;
	}

	public void setExecutionTestStepMasterAutomationScriptStepStepGroup(
			String executionTestStep_MasterAutomationScriptStepStepGroup) {
		this.executionTestStep_MasterAutomationScriptStepStepGroup = executionTestStep_MasterAutomationScriptStepStepGroup;
	}

	public int getExecutionTestStepMasterAutomationScriptStepExecutionSequence() {
		return executionTestStep_MasterAutomationScriptStepExecutionSequence;
	}

	public void setExecutionTestStepMasterAutomationScriptStepExecutionSequence(
			int executionTestStep_MasterAutomationScriptStepExecutionSequence) {
		this.executionTestStep_MasterAutomationScriptStepExecutionSequence = executionTestStep_MasterAutomationScriptStepExecutionSequence;
	}
	
	public String getExecutionTestStepMasterAutomationScriptStepSkipStep() {
		return executionTestStep_MasterAutomationScriptStepSkipStep;
	}

	public void setExecutionTestStepMasterAutomationScriptStepSkipStep(
			String executionTestStep_MasterAutomationScriptStepSkipStep) {
		this.executionTestStep_MasterAutomationScriptStepSkipStep = executionTestStep_MasterAutomationScriptStepSkipStep;
	}

	public String getExecutionTestStepMasterAutomationScriptStepToBeExecutedOnMachine() {
		return executionTestStep_MasterAutomationScriptStepToBeExecutedOnMachine;
	}

	public void setExecutionTestStepMasterAutomationScriptStepToBeExecutedOnMachine(
			String executionTestStep_MasterAutomationScriptStepToBeExecutedOnMachine) {
		this.executionTestStep_MasterAutomationScriptStepToBeExecutedOnMachine = executionTestStep_MasterAutomationScriptStepToBeExecutedOnMachine;
	}
	
	public String getExecutionTestStepMasterAutomationScriptStepLog() {
		return executionTestStep_MasterAutomationScriptStepLog;
	}

	public void setExecutionTestStepMasterAutomationScriptStepLog(
			String executionTestStep_MasterAutomationScriptStepLog) {
		this.executionTestStep_MasterAutomationScriptStepLog = executionTestStep_MasterAutomationScriptStepLog;
	}

	public int getExecutionTestStepExecutionStatus() {
		return executionTestStep_ExecutionStatus;
	}

	public void setExecutionTestStepExecutionStatus(
			int executionTestStep_ExecutionStatus) {
		this.executionTestStep_ExecutionStatus = executionTestStep_ExecutionStatus;
	}

	public String getExecutionTestStepLogMessage() {
		return executionTestStep_LogMessage;
	}

	public void setExecutionTestStepLogMessage(String executionTestStep_LogMessage) {
		this.executionTestStep_LogMessage = executionTestStep_LogMessage;
	}

	public Date getExecutionTestStepNextExecutionBusinessDate() {
		return executionTestStep_NextExecutionBusinessDate;
	}

	public void setExecutionTestStepNextExecutionBusinessDate(
			Date executionTestStep_NextExecutionBusinessDate) {
		this.executionTestStep_NextExecutionBusinessDate = executionTestStep_NextExecutionBusinessDate;
	}

	public Date getExecutionTestStepStartTime() {
		return executionTestStep_StartTime;
	}

	public void setExecutionTestStepStartTime(Date executionTestStep_StartTime) {
		this.executionTestStep_StartTime = executionTestStep_StartTime;
	}

	public Date getExecutionTestStepEndTime() {
		return executionTestStep_EndTime;
	}

	public void setExecutionTestStepEndTime(Date executionTestStep_EndTime) {
		this.executionTestStep_EndTime = executionTestStep_EndTime;
	}

	public Date getExecutionTestStepDateCreated() {
		return executionTestStep_DateCreated;
	}

	public void setExecutionTestStepDateCreated(Date executionTestStep_DateCreated) {
		this.executionTestStep_DateCreated = executionTestStep_DateCreated;
	}

	public Date getExecutionTestStepDateUpdated() {
		return executionTestStep_DateUpdated;
	}

	public void setExecutionTestStepDateUpdated(Date executionTestStep_DateUpdated) {
		this.executionTestStep_DateUpdated = executionTestStep_DateUpdated;
	}

	public boolean isExecutionTestStepDeleted() {
		return executionTestStep_Deleted;
	}

	public void setExecutionTestStepDeleted(boolean executionTestStep_Deleted) {
		this.executionTestStep_Deleted = executionTestStep_Deleted;
	}
}
