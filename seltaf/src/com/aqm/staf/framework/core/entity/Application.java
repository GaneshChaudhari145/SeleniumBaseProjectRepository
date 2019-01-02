package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_Applications")
public class Application {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "app_ID", unique=true)
	private String app_ID;
	
	@Column(name = "app_Reference", unique = true)
	private String app_Reference;
	
	@Column(name = "app_Description")
	private String app_Description;	

	@Column(name = "app_SerialNumber")
	private int app_SerialNumber;	

	@Column(name = "app_ProjectID")
	private String app_ProjectID;	

	@Column(name = "app_ProjectReference")
	private String app_ProjectReference;

	@Column(name = "app_DateCreated")
	private Date app_DateCreated;	

	@Column(name = "app_DateUpdated")
	private Date app_DateUpdated;	

	@Column(name = "app_Deleted")
	private boolean app_Deleted;
	
	public String getApplicationID() {
		return app_ID;
	}

	public void setApplicationID(String app_ID) {
		this.app_ID = app_ID;
	}

	public String getApplicationReference() {
		return app_Reference;
	}

	public void setApplicationReference(String app_Reference) {
		this.app_Reference = app_Reference;
	}

	public String getApplicationDescription() {
		return app_Description;
	}

	public void setApplicationDescription(String app_Description) {
		this.app_Description = app_Description;
	}

	public int getApplicationSerialNumber() {
		return app_SerialNumber;
	}

	public void setApplicationSerialNumber(int app_SerialNumber) {
		this.app_SerialNumber = app_SerialNumber;
	}

	public String getApplicationProjectID() {
		return app_ProjectID;
	}

	public void setApplicationProjectID(String app_ProjectID) {
		this.app_ProjectID = app_ProjectID;
	}

	public String getApplicationProjectReference() {
		return app_ProjectReference;
	}

	public void setApplicationProjectReference(String app_ProjectReference) {
		this.app_ProjectReference = app_ProjectReference;
	}

	public Date getApplicationDateCreated() {
		return app_DateCreated;
	}

	public void setApplicationDateCreated(Date app_DateCreated) {
		this.app_DateCreated = app_DateCreated;
	}

	public Date getApplicationDateUpdated() {
		return app_DateUpdated;
	}

	public void setApplicationDateUpdated(Date app_DateUpdated) {
		this.app_DateUpdated = app_DateUpdated;
	}

	public boolean isApplicationDeleted() {
		return app_Deleted;
	}

	public void setApplicationDeleted(boolean app_Deleted) {
		this.app_Deleted = app_Deleted;
	}
}
