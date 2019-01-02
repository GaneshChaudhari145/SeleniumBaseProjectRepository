package com.aqm.staf.library.databin;

import com.aqm.staf.framework.core.hibernate.CustomizableEntity;

public class GenericEntity extends CustomizableEntity {
	
	public GenericEntity(String runningSheetName) {
		super(runningSheetName);
	}
	
	public static String runningReference="";

	private String testScenario;
	private String reference;
	private int rowNumber;
	private String action;
	private String stepGroup;
	private String parentKey;
	private String childKey;
	private String configExecute;
	private String MasterReference;
	
	
	public String getTestScenario() {
		return testScenario;
	}
	
	public void setTestScenario(String testScenario) {
		this.testScenario = testScenario;
	}
	
	public String getReference() {
		return reference;
	}
	

	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
	
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getStepGroup() {
		return stepGroup;
	}
	
	public void setStepGroup(String stepGroup) {
		this.stepGroup = stepGroup;
	}
	
	public String getParentKey() {
		return parentKey;
	}
	
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	
	public String getChildKey() {
		return childKey;
	}
	
	public void setChildKey(String childKey) {
		this.childKey = childKey;
	}

	public String getConfigExecute() {
		return configExecute;
	}

	public void setConfigExecute(String configExecute) {
		this.configExecute = configExecute;
	}

	public String getMasterReference() {
		return MasterReference;
	}

	public void setMasterReference(String masterReference) {
		MasterReference = masterReference;
	}
	
}