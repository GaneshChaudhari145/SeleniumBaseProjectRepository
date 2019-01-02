package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_MasterTestSuites")

public class MasterTestSuite {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "masterTestSuite_ID", unique=true)
	private String masterTestSuite_ID;
	
	@Column(name = "masterTestSuite_Reference", unique = true)
	private String masterTestSuite_Reference;
	
	@Column(name = "masterTestSuite_Description")
	private String masterTestSuite_Description;	

	@Column(name = "masterTestSuite_SerialNumber")
	private int masterTestSuite_SerialNumber;	

	@Column(name = "masterTestSuite_ProjectID")
	private String masterTestSuite_ProjectID;	

	@Column(name = "masterTestSuite_ProjectReference")
	private String masterTestSuite_ProjectReference;

	@Column(name = "masterTestSuite_ApplicationID")
	private String masterTestSuite_ApplicationID;	

	@Column(name = "masterTestSuite_ApplicationReference")
	private String masterTestSuite_ApplicationReference;
	
	@Column(name = "masterTestSuite_RepositoryFilePath")
	private String masterTestSuite_RepositoryFilePath;
	
	@Column(name = "masterTestSuite_RepositoryAbsoluteFolderPath")
	private String masterTestSuite_RepositoryAbsoluteFolderPath;

	@Column(name = "masterTestSuite_DateCreated")
	private Date masterTestSuite_DateCreated;	

	@Column(name = "masterTestSuite_DateUpdated")
	private Date masterTestSuite_DateUpdated;	

	@Column(name = "masterTestSuite_Deleted")
	private boolean masterTestSuite_Deleted;

	public String getMasterTestSuiteID() {
		return masterTestSuite_ID;
	}

	public void setMasterTestSuiteID(String masterTestSuite_ID) {
		this.masterTestSuite_ID = masterTestSuite_ID;
	}
	
	public String getMasterTestSuiteReference() {
		return masterTestSuite_Reference;
	}

	public void setMasterTestSuiteReference(String masterTestSuite_Reference) {
		this.masterTestSuite_Reference = masterTestSuite_Reference;
	}

	public String getMasterTestSuiteDescription() {
		return masterTestSuite_Description;
	}

	public void setMasterTestSuiteDescription(String masterTestSuite_Description) {
		this.masterTestSuite_Description = masterTestSuite_Description;
	}

	public int getMasterTestSuiteSerialNumber() {
		return masterTestSuite_SerialNumber;
	}

	public void setMasterTestSuiteSerialNumber(int masterTestSuite_SerialNumber) {
		this.masterTestSuite_SerialNumber = masterTestSuite_SerialNumber;
	}

	public String getMasterTestSuiteProjectID() {
		return masterTestSuite_ProjectID;
	}

	public void setMasterTestSuiteProjectID(String masterTestSuite_ProjectID) {
		this.masterTestSuite_ProjectID = masterTestSuite_ProjectID;
	}

	public String getMasterTestSuiteProjectReference() {
		return masterTestSuite_ProjectReference;
	}

	public void setMasterTestSuiteProjectReference(
			String masterTestSuite_ProjectReference) {
		this.masterTestSuite_ProjectReference = masterTestSuite_ProjectReference;
	}

	public String getMasterTestSuiteApplicationID() {
		return masterTestSuite_ApplicationID;
	}

	public void setMasterTestSuiteApplicationID(
			String masterTestSuite_ApplicationID) {
		this.masterTestSuite_ApplicationID = masterTestSuite_ApplicationID;
	}

	public String getMasterTestSuiteApplicationReference() {
		return masterTestSuite_ApplicationReference;
	}

	public void setMasterTestSuiteApplicationReference(
			String masterTestSuite_ApplicationReference) {
		this.masterTestSuite_ApplicationReference = masterTestSuite_ApplicationReference;
	}
	
	public String getMasterTestSuiteRepositoryFilePath() {
		return masterTestSuite_RepositoryFilePath;
	}

	public void setMasterTestSuiteRepositoryFilePath(
			String masterTestSuite_RepositoryFilePath) {
		this.masterTestSuite_RepositoryFilePath = masterTestSuite_RepositoryFilePath;
	}

	public String getMasterTestSuiteRepositoryAbsoluteFolderPath() {
		return masterTestSuite_RepositoryAbsoluteFolderPath;
	}

	public void setMasterTestSuiteRepositoryAbsoluteFolderPath(
			String masterTestSuite_RepositoryAbsoluteFolderPath) {
		this.masterTestSuite_RepositoryAbsoluteFolderPath = masterTestSuite_RepositoryAbsoluteFolderPath;
	}

	public Date getMasterTestSuiteDateCreated() {
		return masterTestSuite_DateCreated;
	}

	public void setMasterTestSuiteDateCreated(Date masterTestSuite_DateCreated) {
		this.masterTestSuite_DateCreated = masterTestSuite_DateCreated;
	}

	public Date getMasterTestSuiteDateUpdated() {
		return masterTestSuite_DateUpdated;
	}

	public void setMasterTestSuiteDateUpdated(Date masterTestSuite_DateUpdated) {
		this.masterTestSuite_DateUpdated = masterTestSuite_DateUpdated;
	}

	public boolean isMasterTestSuiteDeleted() {
		return masterTestSuite_Deleted;
	}

	public void setMasterTestSuiteDeleted(boolean masterTestSuite_Deleted) {
		this.masterTestSuite_Deleted = masterTestSuite_Deleted;
	}
}
