package com.aqm.staf.framework.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "app_Projects")

public class Project {
	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(name = "prj_ID", unique=true)
	private String prj_ID;
	
	@Column(name = "prj_Reference", unique = true)
	private String prj_Reference;
	
	@Column(name = "prj_Description")
	private String prj_Description;	

	@Column(name = "prj_SerialNumber")
	private int prj_SerialNumber;	

	@Column(name = "prj_DateCreated")
	private Date prj_DateCreated;	

	@Column(name = "prj_DateUpdated")
	private Date prj_DateUpdated;	

	@Column(name = "prj_Deleted")
	private boolean prj_Deleted;

	public String getProjectID() {
		return prj_ID;
	}
	
	public void setProjectID(String prj_ID) {
		this.prj_ID = prj_ID;
	}
	
	public String getProjectReference() {
		return prj_Reference;
	}
	
	public void setProjectReference(String prj_Reference) {
		this.prj_Reference = prj_Reference;
	}
	
	public String getProjectDescription() {
		return prj_Description;
	}
	
	public void setProjectDescription(String prj_Description) {
		this.prj_Description = prj_Description;
	}
	
	public int getProjectSerialNumber() {
		return prj_SerialNumber;
	}
	
	public void setProjectSerialNumber(int prj_SerialNumber) {
		this.prj_SerialNumber = prj_SerialNumber;
	}
	
	public Date getProjectDateCreated() {
		return prj_DateCreated;
	}
	
	public void setProjectDateCreated(Date prj_DateCreated) {
		this.prj_DateCreated = prj_DateCreated;
	}
	
	public Date getProjectDateUpdated() {
		return prj_DateUpdated;
	}
	
	public void setProjectDateUpdated(Date prj_DateUpdated) {
		this.prj_DateUpdated = prj_DateUpdated;
	}
	
	public boolean isProjectDeleted() {
		return prj_Deleted;
	}
	
	public void setProjectDeleted(boolean prj_Deleted) {
		this.prj_Deleted = prj_Deleted;
	}
}
