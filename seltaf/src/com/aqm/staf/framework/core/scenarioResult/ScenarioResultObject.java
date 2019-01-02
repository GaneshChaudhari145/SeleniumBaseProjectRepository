package com.aqm.staf.framework.core.scenarioResult;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class ScenarioResultObject implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	private static Integer serialNumberCounter=0;
	private String serialNumber;
	private String platformName;
	private String browserNameVersion;
	private String scenarioID;
	private Calendar dateTimeOfExecution;
	private long durationOfExecution;
	private String scenarioStatus;
	private String reasonIfFailed;
	private String typeOfFailure;

	public static Integer generateExecutionSerialNumber(){
		return ++serialNumberCounter;
	}

	public String getSerialNumber() {
		setSerialNumber((ScenarioResultObject.generateExecutionSerialNumber()).toString());
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getBrowserNameVersion() {
		return browserNameVersion;
	}
	public void setBrowserNameVersion(String browserNameVersion) {
		this.browserNameVersion = browserNameVersion;
	}

	public String getScenarioID() {
		return scenarioID;
	}
	public void setScenarioID(String scenarioID) {
		this.scenarioID = scenarioID;
	}

	public String getDateTimeOfExecution() {
		String dateFormat = DateFormatUtils.format(dateTimeOfExecution.getTime(), "yyyy-MM-dd HH:mm:ss");
		return dateFormat;
	}
	public void setDateTimeOfExecution(Calendar dateTimeOfExecution) {
		this.dateTimeOfExecution = dateTimeOfExecution;
	}
	public String getDurationOfExecution() {
		return DurationFormatUtils.formatDurationWords(durationOfExecution,true,true);
	}
	public void setDurationOfExecution(long durationOfExecution) {
		this.durationOfExecution = durationOfExecution;
	}
	public String getScenarioStatus() {
		return scenarioStatus;
	}
	public void setScenarioStatus(String scenarioStatus) {
		this.scenarioStatus = scenarioStatus;
	}
	public String getReasonIfFailed() {
		return reasonIfFailed;
	}
	public void setReasonIfFailed(String reasonIfFailed) {
		this.reasonIfFailed = reasonIfFailed;
	}
	public String getTypeOfFailure() {
		return typeOfFailure;
	}
	public void setTypeOfFailure(String typeOfFailure) {
		this.typeOfFailure = typeOfFailure;
	}

}
