package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_ExecutionMasterTestSuites")

public class ExecutionMasterTestSuites {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "executionMasterTestSuite_ID", unique=true)
	private String executionMasterTestSuite_ID;
	
	@Column(name = "executionMasterTestSuite_Reference", unique = true)
	private String executionMasterTestSuite_Reference;
	
	@Column(name = "executionMasterTestSuite_Description")
	private String executionMasterTestSuite_Description;
	
	@Column(name = "executionMasterTestSuite_TestPlanID")
	private String executionMasterTestSuite_TestPlanID;	

	@Column(name = "executionMasterTestSuite_TestPlanReference")
	private String executionMasterTestSuite_TestPlanReference;
	
	@Column(name = "executionMasterTestSuite_MasterTestSuiteID")
	private String executionMasterTestSuite_MasterTestSuiteID;	

	@Column(name = "executionMasterTestSuite_MasterTestSuiteReference")
	private String executionMasterTestSuite_MasterTestSuiteReference;
	
	@Column(name = "executionMasterTestSuite_SerialNumber")
	private int executionMasterTestSuite_SerialNumber;	

	@Column(name = "executionMasterTestSuite_ExecutionStatus")
	private int executionMasterTestSuite_ExecutionStatus;	
	
	@Column(name = "executionMasterTestSuite_NextExecutionBusinessDate")
	private Date executionMasterTestSuite_NextExecutionBusinessDate;	
	
	@Column(name = "executionMasterTestSuite_StartTime")
	private Date executionMasterTestSuite_StartTime;	
	
	@Column(name = "executionMasterTestSuite_EndTime")
	private Date executionMasterTestSuite_EndTime;	

	@Column(name = "executionMasterTestSuite_DateCreated")
	private Date executionMasterTestSuite_DateCreated;	

	@Column(name = "executionMasterTestSuite_DateUpdated")
	private Date executionMasterTestSuite_DateUpdated;	

	@Column(name = "executionMasterTestSuite_Deleted")
	private boolean executionMasterTestSuite_Deleted;

	public String getExecutionMasterTestSuiteID() {
		return executionMasterTestSuite_ID;
	}

	public void setExecutionMasterTestSuiteID(String executionMasterTestSuite_ID) {
		this.executionMasterTestSuite_ID = executionMasterTestSuite_ID;
	}

	public String getExecutionMasterTestSuiteReference() {
		return executionMasterTestSuite_Reference;
	}

	public void setExecutionMasterTestSuiteReference(
			String executionMasterTestSuite_Reference) {
		this.executionMasterTestSuite_Reference = executionMasterTestSuite_Reference;
	}

	public String getExecutionMasterTestSuiteDescription() {
		return executionMasterTestSuite_Description;
	}

	public void setExecutionMasterTestSuiteDescription(
			String executionMasterTestSuite_Description) {
		this.executionMasterTestSuite_Description = executionMasterTestSuite_Description;
	}

	public String getExecutionMasterTestSuiteTestPlanID() {
		return executionMasterTestSuite_TestPlanID;
	}

	public void setExecutionMasterTestSuiteTestPlanID(
			String executionMasterTestSuite_TestPlanID) {
		this.executionMasterTestSuite_TestPlanID = executionMasterTestSuite_TestPlanID;
	}

	public String getExecutionMasterTestSuiteTestPlanReference() {
		return executionMasterTestSuite_TestPlanReference;
	}

	public void setExecutionMasterTestSuiteTestPlanReference(
			String executionMasterTestSuite_TestPlanReference) {
		this.executionMasterTestSuite_TestPlanReference = executionMasterTestSuite_TestPlanReference;
	}

	public String getExecutionMasterTestSuiteMasterTestSuiteID() {
		return executionMasterTestSuite_MasterTestSuiteID;
	}

	public void setExecutionMasterTestSuiteMasterTestSuiteID(
			String executionMasterTestSuite_MasterTestSuiteID) {
		this.executionMasterTestSuite_MasterTestSuiteID = executionMasterTestSuite_MasterTestSuiteID;
	}

	public String getExecutionMasterTestSuiteMasterTestSuiteReference() {
		return executionMasterTestSuite_MasterTestSuiteReference;
	}

	public void setExecutionMasterTestSuiteMasterTestSuiteReference(
			String executionMasterTestSuite_MasterTestSuiteReference) {
		this.executionMasterTestSuite_MasterTestSuiteReference = executionMasterTestSuite_MasterTestSuiteReference;
	}

	public int getExecutionMasterTestSuiteSerialNumber() {
		return executionMasterTestSuite_SerialNumber;
	}

	public void setExecutionMasterTestSuiteSerialNumber(
			int executionMasterTestSuite_SerialNumber) {
		this.executionMasterTestSuite_SerialNumber = executionMasterTestSuite_SerialNumber;
	}

	public int getExecutionMasterTestSuiteExecutionStatus() {
		return executionMasterTestSuite_ExecutionStatus;
	}

	public void setExecutionMasterTestSuiteExecutionStatus(
			int executionMasterTestSuite_ExecutionStatus) {
		this.executionMasterTestSuite_ExecutionStatus = executionMasterTestSuite_ExecutionStatus;
	}

	public Date getExecutionMasterTestSuiteNextExecutionBusinessDate() {
		return executionMasterTestSuite_NextExecutionBusinessDate;
	}

	public void setExecutionMasterTestSuiteNextExecutionBusinessDate(
			Date executionMasterTestSuite_NextExecutionBusinessDate) {
		this.executionMasterTestSuite_NextExecutionBusinessDate = executionMasterTestSuite_NextExecutionBusinessDate;
	}

	public Date getExecutionMasterTestSuiteStartTime() {
		return executionMasterTestSuite_StartTime;
	}

	public void setExecutionMasterTestSuiteStartTime(
			Date executionMasterTestSuite_StartTime) {
		this.executionMasterTestSuite_StartTime = executionMasterTestSuite_StartTime;
	}

	public Date getExecutionMasterTestSuiteEndTime() {
		return executionMasterTestSuite_EndTime;
	}

	public void setExecutionMasterTestSuiteEndTime(
			Date executionMasterTestSuite_EndTime) {
		this.executionMasterTestSuite_EndTime = executionMasterTestSuite_EndTime;
	}

	public Date getExecutionMasterTestSuiteDateCreated() {
		return executionMasterTestSuite_DateCreated;
	}

	public void setExecutionMasterTestSuiteDateCreated(
			Date executionMasterTestSuite_DateCreated) {
		this.executionMasterTestSuite_DateCreated = executionMasterTestSuite_DateCreated;
	}

	public Date getExecutionMasterTestSuiteDateUpdated() {
		return executionMasterTestSuite_DateUpdated;
	}

	public void setExecutionMasterTestSuiteDateUpdated(
			Date executionMasterTestSuite_DateUpdated) {
		this.executionMasterTestSuite_DateUpdated = executionMasterTestSuite_DateUpdated;
	}

	public boolean isExecutionMasterTestSuiteDeleted() {
		return executionMasterTestSuite_Deleted;
	}

	public void setExecutionMasterTestSuiteDeleted(
			boolean executionMasterTestSuite_Deleted) {
		this.executionMasterTestSuite_Deleted = executionMasterTestSuite_Deleted;
	}

	
}
