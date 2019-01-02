package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_ExecutionTestSuites")

public class ExecutionTestSuites {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "executionTestSuite_ID", unique=true)
	private String executionTestSuite_ID;
	
	@Column(name = "executionTestSuite_Reference", unique = true)
	private String executionTestSuite_Reference;
	
	@Column(name = "executionTestSuite_Description")
	private String executionTestSuite_Description;
	
	@Column(name = "executionTestSuite_ExecutionMasterTestSuiteID")
	private String executionTestSuite_ExecutionMasterTestSuiteID;	

	@Column(name = "executionTestSuite_ExecutionMasterTestSuiteReference")
	private String executionTestSuite_ExecutionMasterTestSuiteReference;
	
	@Column(name = "executionTestSuite_TestPlanID")
	private String executionTestSuite_TestPlanID;	

	@Column(name = "executionTestSuite_TestPlanReference")
	private String executionTestSuite_TestPlanReference;
	
	@Column(name = "executionTestSuite_MasterTestSuiteID")
	private String executionTestSuite_MasterTestSuiteID;	

	@Column(name = "executionTestSuite_MasterTestSuiteReference")
	private String executionTestSuite_MasterTestSuiteReference;
	
	@Column(name = "executionTestSuite_TestSuiteID")
	private String executionTestSuite_TestSuiteID;	

	@Column(name = "executionTestSuite_TestSuiteReference")
	private String executionTestSuite_TestSuiteReference;
	
	@Column(name = "executionTestSuite_TestSuiteRepositoryFile")
	private String executionTestSuite_TestSuiteRepositoryFile;
	
	@Column(name = "executionTestSuite_TestSuiteExecutionPriority")
	private int executionTestSuite_TestSuiteExecutionPriority;	

	@Column(name = "executionTestSuite_TestSuiteExecutionMode")
	private String executionTestSuite_TestSuiteExecutionMode;
	
	@Column(name = "executionTestSuite_TestSuiteSerialNumber")
	private int executionTestSuite_TestSuiteSerialNumber;	
	
	@Column(name = "executionTestSuite_ExecutionStatus")
	private int executionTestSuite_ExecutionStatus;	
	
	@Column(name = "executionTestSuite_StartTime")
	private Date executionTestSuite_StartTime;	
	
	@Column(name = "executionTestSuite_EndTime")
	private Date executionTestSuite_EndTime;	

	@Column(name = "executionTestSuite_DateCreated")
	private Date executionTestSuite_DateCreated;	

	@Column(name = "executionTestSuite_DateUpdated")
	private Date executionTestSuite_DateUpdated;	

	@Column(name = "executionTestSuite_Deleted")
	private boolean executionTestSuite_Deleted;

	public String getExecutionTestSuiteID() {
		return executionTestSuite_ID;
	}

	public void setExecutionTestSuiteID(String executionTestSuite_ID) {
		this.executionTestSuite_ID = executionTestSuite_ID;
	}

	public String getExecutionTestSuiteReference() {
		return executionTestSuite_Reference;
	}

	public void setExecutionTestSuiteReference(String executionTestSuite_Reference) {
		this.executionTestSuite_Reference = executionTestSuite_Reference;
	}

	public String getExecutionTestSuiteDescription() {
		return executionTestSuite_Description;
	}

	public void setExecutionTestSuiteDescription(
			String executionTestSuite_Description) {
		this.executionTestSuite_Description = executionTestSuite_Description;
	}

	public String getExecutionTestSuiteExecutionMasterTestSuiteID() {
		return executionTestSuite_ExecutionMasterTestSuiteID;
	}

	public void setExecutionTestSuiteExecutionMasterTestSuiteID(
			String executionTestSuite_ExecutionMasterTestSuiteID) {
		this.executionTestSuite_ExecutionMasterTestSuiteID = executionTestSuite_ExecutionMasterTestSuiteID;
	}

	public String getExecutionTestSuiteExecutionMasterTestSuiteReference() {
		return executionTestSuite_ExecutionMasterTestSuiteReference;
	}

	public void setExecutionTestSuiteExecutionMasterTestSuiteReference(
			String executionTestSuite_ExecutionMasterTestSuiteReference) {
		this.executionTestSuite_ExecutionMasterTestSuiteReference = executionTestSuite_ExecutionMasterTestSuiteReference;
	}

	public String getExecutionTestSuiteTestPlanID() {
		return executionTestSuite_TestPlanID;
	}

	public void setExecutionTestSuiteTestPlanID(
			String executionTestSuite_TestPlanID) {
		this.executionTestSuite_TestPlanID = executionTestSuite_TestPlanID;
	}

	public String getExecutionTestSuiteTestPlanReference() {
		return executionTestSuite_TestPlanReference;
	}

	public void setExecutionTestSuiteTestPlanReference(
			String executionTestSuite_TestPlanReference) {
		this.executionTestSuite_TestPlanReference = executionTestSuite_TestPlanReference;
	}

	public String getExecutionTestSuiteMasterTestSuiteID() {
		return executionTestSuite_MasterTestSuiteID;
	}

	public void setExecutionTestSuiteMasterTestSuiteID(
			String executionTestSuite_MasterTestSuiteID) {
		this.executionTestSuite_MasterTestSuiteID = executionTestSuite_MasterTestSuiteID;
	}

	public String getExecutionTestSuiteMasterTestSuiteReference() {
		return executionTestSuite_MasterTestSuiteReference;
	}

	public void setExecutionTestSuiteMasterTestSuiteReference(
			String executionTestSuite_MasterTestSuiteReference) {
		this.executionTestSuite_MasterTestSuiteReference = executionTestSuite_MasterTestSuiteReference;
	}

	public String getExecutionTestSuiteTestSuiteID() {
		return executionTestSuite_TestSuiteID;
	}

	public void setExecutionTestSuiteTestSuiteID(
			String executionTestSuite_TestSuiteID) {
		this.executionTestSuite_TestSuiteID = executionTestSuite_TestSuiteID;
	}

	public String getExecutionTestSuiteTestSuiteReference() {
		return executionTestSuite_TestSuiteReference;
	}

	public void setExecutionTestSuiteTestSuiteReference(
			String executionTestSuite_TestSuiteReference) {
		this.executionTestSuite_TestSuiteReference = executionTestSuite_TestSuiteReference;
	}

	public String getExecutionTestSuiteTestSuiteRepositoryFile() {
		return executionTestSuite_TestSuiteRepositoryFile;
	}

	public void setExecutionTestSuiteTestSuiteRepositoryFile(
			String executionTestSuite_TestSuiteRepositoryFile) {
		this.executionTestSuite_TestSuiteRepositoryFile = executionTestSuite_TestSuiteRepositoryFile;
	}

	public int getExecutionTestSuiteTestSuiteExecutionPriority() {
		return executionTestSuite_TestSuiteExecutionPriority;
	}

	public void setExecutionTestSuiteTestSuiteExecutionPriority(
			int executionTestSuite_TestSuiteExecutionPriority) {
		this.executionTestSuite_TestSuiteExecutionPriority = executionTestSuite_TestSuiteExecutionPriority;
	}

	public String getExecutionTestSuiteTestSuiteExecutionMode() {
		return executionTestSuite_TestSuiteExecutionMode;
	}

	public void setExecutionTestSuiteTestSuiteExecutionMode(
			String executionTestSuite_TestSuiteExecutionMode) {
		this.executionTestSuite_TestSuiteExecutionMode = executionTestSuite_TestSuiteExecutionMode;
	}

	public int getExecutionTestSuiteTestSuiteSerialNumber() {
		return executionTestSuite_TestSuiteSerialNumber;
	}

	public void setExecutionTestSuiteTestSuiteSerialNumber(
			int executionTestSuite_TestSuiteSerialNumber) {
		this.executionTestSuite_TestSuiteSerialNumber = executionTestSuite_TestSuiteSerialNumber;
	}

	public int getExecutionTestSuiteExecutionStatus() {
		return executionTestSuite_ExecutionStatus;
	}

	public void setExecutionTestSuiteExecutionStatus(
			int executionTestSuite_ExecutionStatus) {
		this.executionTestSuite_ExecutionStatus = executionTestSuite_ExecutionStatus;
	}

	public Date getExecutionTestSuiteStartTime() {
		return executionTestSuite_StartTime;
	}

	public void setExecutionTestSuiteStartTime(Date executionTestSuite_StartTime) {
		this.executionTestSuite_StartTime = executionTestSuite_StartTime;
	}

	public Date getExecutionTestSuiteEndTime() {
		return executionTestSuite_EndTime;
	}

	public void setExecutionTestSuiteEndTime(Date executionTestSuite_EndTime) {
		this.executionTestSuite_EndTime = executionTestSuite_EndTime;
	}

	public Date getExecutionTestSuiteDateCreated() {
		return executionTestSuite_DateCreated;
	}

	public void setExecutionTestSuiteDateCreated(
			Date executionTestSuite_DateCreated) {
		this.executionTestSuite_DateCreated = executionTestSuite_DateCreated;
	}

	public Date getExecutionTestSuiteDateUpdated() {
		return executionTestSuite_DateUpdated;
	}

	public void setExecutionTestSuiteDateUpdated(
			Date executionTestSuite_DateUpdated) {
		this.executionTestSuite_DateUpdated = executionTestSuite_DateUpdated;
	}

	public boolean isExecutionTestSuiteDeleted() {
		return executionTestSuite_Deleted;
	}

	public void setExecutionTestSuiteDeleted(boolean executionTestSuite_Deleted) {
		this.executionTestSuite_Deleted = executionTestSuite_Deleted;
	}
}
