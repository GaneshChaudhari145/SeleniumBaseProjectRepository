package com.aqm.staf.framework.core.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_TestPlan")
public class TestPlan {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "testPlan_ID", unique=true)
	private String testPlan_ID;
	
	@Column(name = "testPlan_Reference", unique = true)
	private String testPlan_Reference;
	
	@Column(name = "testPlan_Description")
	private String testPlan_Description;	

	@Column(name = "testPlan_StartDate")
	private Date testPlan_StartDate;	

	@Column(name = "testPlan_EndDate")
	private Date testPlan_EndDate;	

	@Column(name = "testPlan_CurrentExecutionMasterTestSuiteID")
	private String testPlan_CurrentExecutionMasterTestSuiteID;	

	@Column(name = "testPlan_CurrentExecutionMasterTestSuiteReference")
	private String testPlan_CurrentExecutionMasterTestSuiteReference;

	@Column(name = "testPlan_CurrentExecutionTestSuiteID")
	private String testPlan_CurrentExecutionTestSuiteID;	

	@Column(name = "testPlan_CurrentExecutionTestSuiteReference")
	private String testPlan_CurrentExecutionTestSuiteReference;

	@Column(name = "testPlan_CurrentBusinessDate")
	private Date testPlan_CurrentBusinessDate;	

	@Column(name = "testPlan_TargetExecutionResultsSummaryDBConnectionString")
	private String testPlan_TargetExecutionResultsSummaryDBConnectionString;
	
	@Column(name = "testPlan_TargetManualTestCaseSummaryDBConnectionString")
	private String testPlan_TargetManualTestCaseSummaryDBConnectionString;

	@Column(name = "testPlan_CurrentExecutionResultsFolder")
	private String testPlan_CurrentExecutionResultsFolder;

	@Column(name = "testPlan_CurrentScenarioExecutionResultsFolder")
	private String testPlan_CurrentScenarioExecutionResultsFolder;

	@Column(name = "testPlan_CurrentTestNGExecutionResultsFolder")
	private String testPlan_CurrentTestNGExecutionResultsFolder;

	@Column(name = "testPlan_PlatformToTestOn")
	private String testPlan_PlatformToTestOn;

	@Column(name = "testPlan_BrowserToTestOn")
	private String testPlan_BrowserToTestOn;

	@Column(name = "testPlan_BrowserVersionToTestOn")
	private String testPlan_BrowserVersionToTestOn;

	@Column(name = "testPlan_DateCreated")
	private Date testPlan_DateCreated;	

	@Column(name = "testPlan_DateUpdated")
	private Date testPlan_DateUpdated;	

	@Column(name = "testPlan_Deleted")
	private boolean testPlan_Deleted;

	public String getTestPlanID() {
		return testPlan_ID;
	}

	public void setTestPlanID(String testPlan_ID) {
		this.testPlan_ID = testPlan_ID;
	}

	public String getTestPlanReference() {
		return testPlan_Reference;
	}

	public void setTestPlanReference(String testPlan_Reference) {
		this.testPlan_Reference = testPlan_Reference;
	}

	public String getTestPlanDescription() {
		return testPlan_Description;
	}

	public void setTestPlanDescription(String testPlan_Description) {
		this.testPlan_Description = testPlan_Description;
	}

	public Date getTestPlanStartDate() {
		return testPlan_StartDate;
	}

	public void setTestPlanStartDate(Date testPlan_StartDate) {
		this.testPlan_StartDate = testPlan_StartDate;
	}

	public Date getTestPlanEndDate() {
		return testPlan_EndDate;
	}

	public void setTestPlanEndDate(Date testPlan_EndDate) {
		this.testPlan_EndDate = testPlan_EndDate;
	}

	public String getTestPlanCurrentExecutionMasterTestSuiteID() {
		return testPlan_CurrentExecutionMasterTestSuiteID;
	}

	public void setTestPlanCurrentExecutionMasterTestSuiteID(
			String testPlan_CurrentExecutionMasterTestSuiteID) {
		this.testPlan_CurrentExecutionMasterTestSuiteID = testPlan_CurrentExecutionMasterTestSuiteID;
	}

	public String getTestPlanCurrentExecutionMasterTestSuiteReference() {
		return testPlan_CurrentExecutionMasterTestSuiteReference;
	}

	public void setTestPlanCurrentExecutionMasterTestSuiteReference(
			String testPlan_CurrentExecutionMasterTestSuiteReference) {
		this.testPlan_CurrentExecutionMasterTestSuiteReference = testPlan_CurrentExecutionMasterTestSuiteReference;
	}

	public String getTestPlanCurrentExecutionTestSuiteID() {
		return testPlan_CurrentExecutionTestSuiteID;
	}

	public void setTestPlanCurrentExecutionTestSuiteID(
			String testPlan_CurrentExecutionTestSuiteID) {
		this.testPlan_CurrentExecutionTestSuiteID = testPlan_CurrentExecutionTestSuiteID;
	}

	public String getTestPlanCurrentExecutionTestSuiteReference() {
		return testPlan_CurrentExecutionTestSuiteReference;
	}

	public void setTestPlanCurrentExecutionTestSuiteReference(
			String testPlan_CurrentExecutionTestSuiteReference) {
		this.testPlan_CurrentExecutionTestSuiteReference = testPlan_CurrentExecutionTestSuiteReference;
	}

	public Date getTestPlanCurrentBusinessDate() {
		return testPlan_CurrentBusinessDate;
	}

	public void setTestPlanCurrentBusinessDate(Date testPlan_CurrentBusinessDate) {
		this.testPlan_CurrentBusinessDate = testPlan_CurrentBusinessDate;
	}

	public String getTestPlanTargetExecutionResultsSummaryDBConnectionString() {
		return testPlan_TargetExecutionResultsSummaryDBConnectionString;
	}

	public void setTestPlanTargetExecutionResultsSummaryDBConnectionString(
			String testPlan_TargetExecutionResultsSummaryDBConnectionString) {
		this.testPlan_TargetExecutionResultsSummaryDBConnectionString = testPlan_TargetExecutionResultsSummaryDBConnectionString;
	}

	public String getTestPlanTargetManualTestCaseSummaryDBConnectionString() {
		return testPlan_TargetManualTestCaseSummaryDBConnectionString;
	}

	public void setTestPlanTargetManualTestCaseSummaryDBConnectionString(
			String testPlan_TargetManualTestCaseSummaryDBConnectionString) {
		this.testPlan_TargetManualTestCaseSummaryDBConnectionString = testPlan_TargetManualTestCaseSummaryDBConnectionString;
	}
	
	public String getTestPlanCurrentExecutionResultsFolder() {
		return testPlan_CurrentExecutionResultsFolder;
	}

	public void setTestPlanCurrentExecutionResultsFolder(
			String testPlan_CurrentExecutionResultsFolder) {
		this.testPlan_CurrentExecutionResultsFolder = testPlan_CurrentExecutionResultsFolder;
	}

	public String getTestPlanCurrentScenarioExecutionResultsFolder() {
		return testPlan_CurrentScenarioExecutionResultsFolder;
	}

	public void setTestPlanCurrentScenarioExecutionResultsFolder(
			String testPlan_CurrentScenarioExecutionResultsFolder) {
		this.testPlan_CurrentScenarioExecutionResultsFolder = testPlan_CurrentScenarioExecutionResultsFolder;
	}

	public String getTestPlanCurrentTestNGExecutionResultsFolder() {
		return testPlan_CurrentTestNGExecutionResultsFolder;
	}

	public void setTestPlanCurrentTestNGExecutionResultsFolder(
			String testPlan_CurrentTestNGExecutionResultsFolder) {
		this.testPlan_CurrentTestNGExecutionResultsFolder = testPlan_CurrentTestNGExecutionResultsFolder;
	}

	public String getTestPlanPlatformToTestOn() {
		return testPlan_PlatformToTestOn;
	}

	public void setTestPlanPlatformToTestOn(String testPlan_PlatformToTestOn) {
		this.testPlan_PlatformToTestOn = testPlan_PlatformToTestOn;
	}

	public String getTestPlanBrowserToTestOn() {
		return testPlan_BrowserToTestOn;
	}

	public void setTestPlanBrowserToTestOn(String testPlan_BrowserToTestOn) {
		this.testPlan_BrowserToTestOn = testPlan_BrowserToTestOn;
	}

	public String getTestPlanBrowserVersionToTestOn() {
		return testPlan_BrowserVersionToTestOn;
	}

	public void setTestPlanBrowserVersionToTestOn(
			String testPlan_BrowserVersionToTestOn) {
		this.testPlan_BrowserVersionToTestOn = testPlan_BrowserVersionToTestOn;
	}

	public Date getTestPlanDateCreated() {
		return testPlan_DateCreated;
	}

	public void setTestPlanDateCreated(Date testPlan_DateCreated) {
		this.testPlan_DateCreated = testPlan_DateCreated;
	}

	public Date getTestPlanDateUpdated() {
		return testPlan_DateUpdated;
	}

	public void setTestPlanDateUpdated(Date testPlan_DateUpdated) {
		this.testPlan_DateUpdated = testPlan_DateUpdated;
	}

	public boolean isTestPlanDeleted() {
		return testPlan_Deleted;
	}

	public void setTestPlanDeleted(boolean testPlan_Deleted) {
		this.testPlan_Deleted = testPlan_Deleted;
	}
}
