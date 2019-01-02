package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_TestSuites")

public class TestSuite {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "testSuite_ID", unique=true)
	private String testSuite_ID;
	
	@Column(name = "testSuite_Reference", unique = true)
	private String testSuite_Reference;
	
	@Column(name = "testSuite_Description")
	private String testSuite_Description;	

	@Column(name = "testSuite_SerialNumber")
	private int testSuite_SerialNumber;	

	@Column(name = "testSuite_ProjectID")
	private String testSuite_ProjectID;	

	@Column(name = "testSuite_ProjectReference")
	private String testSuite_ProjectReference;

	@Column(name = "testSuite_ApplicationID")
	private String testSuite_ApplicationID;	

	@Column(name = "testSuite_ApplicationReference")
	private String testSuite_ApplicationReference;

	@Column(name = "testSuite_ModuleID")
	private String testSuite_ModuleID;

	@Column(name = "testSuite_ModuleReference")
	private String testSuite_ModuleReference;
	
	@Column(name = "testSuite_MasterTestSuiteID")
	private String testSuite_MasterTestSuiteID;

	@Column(name = "testSuite_MasterTestSuiteReference")
	private String testSuite_MasterTestSuiteReference;
	
	@Column(name = "testSuite_RepositoryFile")
	private String testSuite_RepositoryFile;
	
	@Column(name = "testSuite_RepositoryAbsoluteFolderPath")
	private String testSuite_RepositoryAbsoluteFolderPath;

	@Column(name = "testSuite_ExecutionPriority")
	private int testSuite_ExecutionPriority;	
	
	@Column(name = "testSuite_ExecutionMode")
	private String testSuite_ExecutionMode;
	
	@Column(name = "testSuite_DateCreated")
	private Date testSuite_DateCreated;	

	@Column(name = "testSuite_DateUpdated")
	private Date testSuite_DateUpdated;	

	@Column(name = "testSuite_Deleted")
	private boolean testSuite_Deleted;

	public String getTestSuiteID() {
		return testSuite_ID;
	}

	public void setTestSuiteID(String testSuite_ID) {
		this.testSuite_ID = testSuite_ID;
	}

	public String getTestSuiteReference() {
		return testSuite_Reference;
	}

	public void setTestSuiteReference(String testSuite_Reference) {
		this.testSuite_Reference = testSuite_Reference;
	}

	public String getTestSuiteDescription() {
		return testSuite_Description;
	}

	public void setTestSuiteDescription(String testSuite_Description) {
		this.testSuite_Description = testSuite_Description;
	}

	public int getTestSuiteSerialNumber() {
		return testSuite_SerialNumber;
	}

	public void setTestSuiteSerialNumber(int testSuite_SerialNumber) {
		this.testSuite_SerialNumber = testSuite_SerialNumber;
	}

	public String getTestSuiteProjectID() {
		return testSuite_ProjectID;
	}

	public void setTestSuiteProjectID(String testSuite_ProjectID) {
		this.testSuite_ProjectID = testSuite_ProjectID;
	}

	public String getTestSuiteProjectReference() {
		return testSuite_ProjectReference;
	}

	public void setTestSuiteProjectReference(String testSuite_ProjectReference) {
		this.testSuite_ProjectReference = testSuite_ProjectReference;
	}

	public String getTestSuiteApplicationID() {
		return testSuite_ApplicationID;
	}

	public void setTestSuiteApplicationID(String testSuite_ApplicationID) {
		this.testSuite_ApplicationID = testSuite_ApplicationID;
	}

	public String getTestSuiteApplicationReference() {
		return testSuite_ApplicationReference;
	}

	public void setTestSuiteApplicationReference(
			String testSuite_ApplicationReference) {
		this.testSuite_ApplicationReference = testSuite_ApplicationReference;
	}

	public String getTestSuiteModuleID() {
		return testSuite_ModuleID;
	}

	public void setTestSuiteModuleID(String testSuite_ModuleID) {
		this.testSuite_ModuleID = testSuite_ModuleID;
	}

	public String getTestSuiteModuleReference() {
		return testSuite_ModuleReference;
	}

	public void setTestSuiteModuleReference(String testSuite_ModuleReference) {
		this.testSuite_ModuleReference = testSuite_ModuleReference;
	}

	public String getTestSuiteMasterTestSuiteID() {
		return testSuite_MasterTestSuiteID;
	}

	public void setTestSuiteMasterTestSuiteID(String testSuite_MasterTestSuiteID) {
		this.testSuite_MasterTestSuiteID = testSuite_MasterTestSuiteID;
	}

	public String getTestSuiteMasterTestSuiteReference() {
		return testSuite_MasterTestSuiteReference;
	}

	public void setTestSuiteMasterTestSuiteReference(
			String testSuite_MasterTestSuiteReference) {
		this.testSuite_MasterTestSuiteReference = testSuite_MasterTestSuiteReference;
	}

	public String getTestSuiteRepositoryFile() {
		return testSuite_RepositoryFile;
	}

	public void setTestSuiteRepositoryFile(String testSuite_RepositoryFile) {
		this.testSuite_RepositoryFile = testSuite_RepositoryFile;
	}

	public String getTestSuiteRepositoryAbsoluteFolderPath() {
		return testSuite_RepositoryAbsoluteFolderPath;
	}

	public void setTestSuiteRepositoryAbsoluteFolderPath(
			String testSuite_RepositoryAbsoluteFolderPath) {
		this.testSuite_RepositoryAbsoluteFolderPath = testSuite_RepositoryAbsoluteFolderPath;
	}

	public int getTestSuiteExecutionPriority() {
		return testSuite_ExecutionPriority;
	}

	public void setTestSuiteExecutionPriority(int testSuite_ExecutionPriority) {
		this.testSuite_ExecutionPriority = testSuite_ExecutionPriority;
	}

	public String getTestSuiteExecutionMode() {
		return testSuite_ExecutionMode;
	}

	public void setTestSuiteExecutionMode(String testSuite_ExecutionMode) {
		this.testSuite_ExecutionMode = testSuite_ExecutionMode;
	}

	public Date getTestSuiteDateCreated() {
		return testSuite_DateCreated;
	}

	public void setTestSuiteDateCreated(Date testSuite_DateCreated) {
		this.testSuite_DateCreated = testSuite_DateCreated;
	}

	public Date getTestSuiteDateUpdated() {
		return testSuite_DateUpdated;
	}

	public void setTestSuiteDateUpdated(Date testSuite_DateUpdated) {
		this.testSuite_DateUpdated = testSuite_DateUpdated;
	}

	public boolean isTestSuiteDeleted() {
		return testSuite_Deleted;
	}

	public void setTestSuiteDeleted(boolean testSuite_Deleted) {
		this.testSuite_Deleted = testSuite_Deleted;
	}
}
