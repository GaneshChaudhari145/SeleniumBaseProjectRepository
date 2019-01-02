package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_TestScenarios")

public class TestScenario {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "testScenario_ID", unique=true)
	private String testScenario_ID;
	
	@Column(name = "testScenario_Reference", unique=true)
	private String testScenario_Reference;
	
	@Column(name = "testScenario_Description")
	private String testScenario_Description;
	
	/*@Column(name = "testScenario_Lob")
	private String testScenario_Lob;*/
	
	@Column(name = "testScenario_Product")
	private String testScenario_Product;
	
	@Column(name = "testScenario_Scripter")
	private String testScenario_Scripter;

	@Column(name = "testScenario_SerialNumber")
	private int testScenario_SerialNumber;

	@Column(name = "testScenario_ProjectID")
	private String testScenario_ProjectID;

	@Column(name = "testScenario_ProjectReference")
	private String testScenario_ProjectReference;

	@Column(name = "testScenario_ApplicationID")
	private String testScenario_ApplicationID;

	@Column(name = "testScenario_ApplicationReference")
	private String testScenario_ApplicationReference;

	@Column(name = "testScenario_MasterTestSuiteID")
	private String testScenario_MasterTestSuiteID;

	@Column(name = "testScenario_MasterTestSuiteReference")
	private String testScenario_MasterTestSuiteReference;

	@Column(name = "testScenario_TestSuiteID")
	private String testScenario_TestSuiteID;

	@Column(name = "testScenario_TestSuiteReference")
	private String testScenario_TestSuiteReference;

	@Column(name = "testScenario_PrerequisiteTestSuiteID")
	private String testScenario_PrerequisiteTestSuiteID;

	@Column(name = "testScenario_PrerequisiteTestSuiteReference")
	private String testScenario_PrerequisiteTestSuiteReference;

	@Column(name = "testScenario_PrerequisiteTestScenarioID")
	private String testScenario_PrerequisiteTestScenarioID;

	@Column(name = "testScenario_PrerequisiteTestScenarioReference")
	private String testScenario_PrerequisiteTestScenarioReference;

	@Column(name = "testScenario_MasterAutomationScriptID")
	private String testScenario_MasterAutomationScriptID;

	@Column(name = "testScenario_MasterAutomationScriptReference")
	private String testScenario_MasterAutomationScriptReference;

	@Column(name = "testScenario_DateCreated")
	private Date testScenario_DateCreated;

	@Column(name = "testScenario_DateUpdated")
	private Date testScenario_DateUpdated;	

	@Column(name = "testScenario_Deleted")
	private boolean testScenario_Deleted;

	public String getTestScenarioID() {
		return testScenario_ID;
	}

	public void setTestScenarioID(String testScenario_ID) {
		this.testScenario_ID = testScenario_ID;
	}

	public String getTestScenarioReference() {
		return testScenario_Reference;
	}

	public void setTestScenarioReference(String testScenario_Reference) {
		this.testScenario_Reference = testScenario_Reference;
	}

	public String getTestScenarioDescription() {
		return testScenario_Description;
	}

	public void setTestScenarioDescription(String testScenario_Description) {
		this.testScenario_Description = testScenario_Description;
	}

	/*public String getTestScenario_Lob() {
		return testScenario_Lob;
	}

	public void setTestScenario_Lob(String testScenario_Lob) {
		this.testScenario_Lob = testScenario_Lob;
	}
*/
	public String getTestScenario_Product() {
		return testScenario_Product;
	}

	public void setTestScenario_Product(String testScenario_Product) {
		this.testScenario_Product = testScenario_Product;
	}

	public String getTestScenario_Scripter() {
		return testScenario_Scripter;
	}

	public void setTestScenario_Scripter(String testScenario_Scripter) {
		this.testScenario_Scripter = testScenario_Scripter;
	}

	public int getTestScenarioSerialNumber() {
		return testScenario_SerialNumber;
	}

	public void setTestScenarioSerialNumber(int testScenario_SerialNumber) {
		this.testScenario_SerialNumber = testScenario_SerialNumber;
	}

	public String getTestScenarioProjectID() {
		return testScenario_ProjectID;
	}

	public void setTestScenarioProjectID(String testScenario_ProjectID) {
		this.testScenario_ProjectID = testScenario_ProjectID;
	}

	public String getTestScenarioProjectReference() {
		return testScenario_ProjectReference;
	}

	public void setTestScenarioProjectReference(
			String testScenario_ProjectReference) {
		this.testScenario_ProjectReference = testScenario_ProjectReference;
	}

	public String getTestScenarioApplicationID() {
		return testScenario_ApplicationID;
	}

	public void setTestScenarioApplicationID(String testScenario_ApplicationID) {
		this.testScenario_ApplicationID = testScenario_ApplicationID;
	}

	public String getTestScenarioApplicationReference() {
		return testScenario_ApplicationReference;
	}

	public void setTestScenarioApplicationReference(
			String testScenario_ApplicationReference) {
		this.testScenario_ApplicationReference = testScenario_ApplicationReference;
	}

	public String getTestScenarioMasterTestSuiteID() {
		return testScenario_MasterTestSuiteID;
	}

	public void setTestScenarioMasterTestSuiteID(
			String testScenario_MasterTestSuiteID) {
		this.testScenario_MasterTestSuiteID = testScenario_MasterTestSuiteID;
	}

	public String getTestScenarioMasterTestSuiteReference() {
		return testScenario_MasterTestSuiteReference;
	}

	public void setTestScenarioMasterTestSuiteReference(
			String testScenario_MasterTestSuiteReference) {
		this.testScenario_MasterTestSuiteReference = testScenario_MasterTestSuiteReference;
	}

	public String getTestScenarioTestSuiteID() {
		return testScenario_TestSuiteID;
	}

	public void setTestScenarioTestSuiteID(String testScenario_TestSuiteID) {
		this.testScenario_TestSuiteID = testScenario_TestSuiteID;
	}

	public String getTestScenarioTestSuiteReference() {
		return testScenario_TestSuiteReference;
	}

	public void setTestScenarioTestSuiteReference(
			String testScenario_TestSuiteReference) {
		this.testScenario_TestSuiteReference = testScenario_TestSuiteReference;
	}

	public String getTestScenarioPrerequisiteTestSuiteID() {
		return testScenario_PrerequisiteTestSuiteID;
	}

	public void setTestScenarioPrerequisiteTestSuiteID(
			String testScenario_PrerequisiteTestSuiteID) {
		this.testScenario_PrerequisiteTestSuiteID = testScenario_PrerequisiteTestSuiteID;
	}

	public String getTestScenarioPrerequisiteTestSuiteReference() {
		return testScenario_PrerequisiteTestSuiteReference;
	}

	public void setTestScenarioPrerequisiteTestSuiteReference(
			String testScenario_PrerequisiteTestSuiteReference) {
		this.testScenario_PrerequisiteTestSuiteReference = testScenario_PrerequisiteTestSuiteReference;
	}

	public String getTestScenarioPrerequisiteTestScenarioID() {
		return testScenario_PrerequisiteTestScenarioID;
	}

	public void setTestScenarioPrerequisiteTestScenarioID(
			String testScenario_PrerequisiteTestScenarioID) {
		this.testScenario_PrerequisiteTestScenarioID = testScenario_PrerequisiteTestScenarioID;
	}

	public String getTestScenarioPrerequisiteTestScenarioReference() {
		return testScenario_PrerequisiteTestScenarioReference;
	}

	public void setTestScenarioPrerequisiteTestScenarioReference(
			String testScenario_PrerequisiteTestScenarioReference) {
		this.testScenario_PrerequisiteTestScenarioReference = testScenario_PrerequisiteTestScenarioReference;
	}

	public String getTestScenarioMasterAutomationScriptID() {
		return testScenario_MasterAutomationScriptID;
	}

	public void setTestScenarioMasterAutomationScriptID(
			String testScenario_MasterAutomationScriptID) {
		this.testScenario_MasterAutomationScriptID = testScenario_MasterAutomationScriptID;
	}

	public String getTestScenarioMasterAutomationScriptReference() {
		return testScenario_MasterAutomationScriptReference;
	}

	public void setTestScenarioMasterAutomationScriptReference(
			String testScenario_MasterAutomationScriptReference) {
		this.testScenario_MasterAutomationScriptReference = testScenario_MasterAutomationScriptReference;
	}

	public Date getTestScenarioDateCreated() {
		return testScenario_DateCreated;
	}

	public void setTestScenarioDateCreated(Date testScenario_DateCreated) {
		this.testScenario_DateCreated = testScenario_DateCreated;
	}

	public Date getTestScenarioDateUpdated() {
		return testScenario_DateUpdated;
	}

	public void setTestScenarioDateUpdated(Date testScenario_DateUpdated) {
		this.testScenario_DateUpdated = testScenario_DateUpdated;
	}

	public boolean isTestScenarioDeleted() {
		return testScenario_Deleted;
	}

	public void setTestScenarioDeleted(boolean testScenario_Deleted) {
		this.testScenario_Deleted = testScenario_Deleted;
	}
}
