package com.aqm.staf.framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_RuntimeConfigParameters")
public class RuntimeConfigParameter {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "runtimeConfigParameters_ID", unique=true)
	private String runtimeConfigParameters_ID;
	
	@Column(name = "runtimeConfigParameters_Reference", unique = true)
	private String runtimeConfigParameters_Reference;
	
	@Column(name = "runtimeConfigParameters_KeyData")
	private String runtimeConfigParameters_KeyData;

	public String getRuntimeConfigParametersID() {
		return runtimeConfigParameters_ID;
	}

	public void setRuntimeConfigParametersID(String runtimeConfigParameters_ID) {
		this.runtimeConfigParameters_ID = runtimeConfigParameters_ID;
	}

	public String getRuntimeConfigParametersReference() {
		return runtimeConfigParameters_Reference;
	}

	public void setRuntimeConfigParametersReference(
			String runtimeConfigParameters_Reference) {
		this.runtimeConfigParameters_Reference = runtimeConfigParameters_Reference;
	}

	public String getRuntimeConfigParametersKeyData() {
		return runtimeConfigParameters_KeyData;
	}

	public void setRuntimeConfigParametersKeyData(
			String runtimeConfigParameters_KeyData) {
		this.runtimeConfigParameters_KeyData = runtimeConfigParameters_KeyData;
	}	
}
