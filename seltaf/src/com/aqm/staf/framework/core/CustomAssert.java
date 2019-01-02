package com.aqm.staf.framework.core;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aqm.staf.framework.FrameworkServices;
import com.aqm.tests.AutomationDriverScript;

public class CustomAssert extends Assert {
	private static final Boolean False = null;
	boolean allChecks;
	boolean warning;
	static int counter=0;
	WebDriver driver;
	
	
	public CustomAssert(WebDriver driver) {
		allChecks = true;
		warning = false;
		
		this.driver=driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isAllChecks() {
		return allChecks;
	}

	public void setAllChecks(boolean allChecks) {
		this.allChecks = allChecks;
	}

	public boolean isWarning() {
		return warning;
	}

	public void setWarning(boolean warning) {
		this.warning = warning;
	}

	public void assertEquals (String expected, String actual, AssertionType assertType) {
		try { 
			counter++;
			if (actual == null) actual = "DEFAULTED TO NULL OBJECT";
			if (expected == null) expected = "DEFAULTED TO NULL OBJECT";
			assertEquals(actual,expected);
			FrameworkServices.logMessage("<B>"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; PASSED </B>");
		}
		catch(AssertionError error) {
			String ErrorMessage;
			ErrorMessage="Excel value"+"-"+ error.getMessage()+"-"+"at Application";
			switch (assertType) {
			case ERROR:
				allChecks = allChecks && false;
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			case WARNING:
				warning = true;
				// Added by PKP on 13-10-2015 : Yellow indicates Warning
				FrameworkServices.logMessage("<B><Font Color = \"yellow\">"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </Font></B>");
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			}
		}
	}
	
	public void assertEquals (Boolean expected, Boolean actual, AssertionType assertType) {
		try {
			counter++;
			if (actual == null) actual = false ;
			if (expected == null) expected = false;
			assertEquals(actual,expected);
			FrameworkServices.logMessage("<B>"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; PASSED </B>");
		}
		catch(AssertionError error) {
			String ErrorMessage;
			ErrorMessage= error.toString();
			switch (assertType) {
			case ERROR:
				allChecks = allChecks && false;
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			case WARNING:
				warning = true;
				//FrameworkServices.logMessage("<B>"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </B>");
				FrameworkServices.logMessage("<B><Font Color = \"yellow\">"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </Font></B>");
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			}
		}
	}
	public void assertEquals (int expected, int actual, AssertionType assertType) {
		try {
			counter++;
			assertEquals(actual,expected);
		}
		catch(AssertionError error) {
			String ErrorMessage;
			ErrorMessage= error.toString();
			switch (assertType) {
			case ERROR:
				allChecks = allChecks && false;
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			case WARNING:
				warning = true;
				FrameworkServices.logMessage("<B><Font Color = \"yellow\">"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </Font></B>");
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			}
		}
	}
	public void assertEquals (Float expected, Float actual, AssertionType assertType) {
		try {
			assertEquals(actual,expected);
		}
		catch(AssertionError error) {
			String ErrorMessage;
			ErrorMessage= error.toString();
			switch (assertType) {
			case ERROR:
				allChecks = allChecks && false;
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			case WARNING:
				warning = true;
				FrameworkServices.logMessage("<B><Font Color = \"yellow\">"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </Font></B>");
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			}
		}
	}
	public void assertEquals (Double expected, Double actual, AssertionType assertType) {
		try {
			assertEquals(actual,expected);
		}
		catch(AssertionError error) {
			String ErrorMessage;
			ErrorMessage= error.toString();
			switch (assertType) {
			
			case ERROR:
				allChecks = allChecks && false;
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			case WARNING:
				warning = true;
				FrameworkServices.logMessage("<B><Font Color = \"yellow\">"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </Font></B>");
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			}
		}
	}
	public void assertEquals (Long expected, Long actual, AssertionType assertType) {
		try {
			assertEquals(actual,expected);
		}
		catch(AssertionError error) {
			String ErrorMessage;
			ErrorMessage= error.toString();
			switch (assertType) {
			case ERROR:
				allChecks = allChecks && false;
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			case WARNING:
				warning = true;
				FrameworkServices.logMessage("<B><Font Color = \"yellow\">"+counter+".  Actual= "+actual+";  Expected= "+expected+" ; FAILED </Font></B>");
				captureScreenShotOnAssertionException(AutomationDriverScript.scenarioExecutionFolderReference,ErrorMessage);
				break;
			}
		}
	}
	
	public void captureScreenShotOnAssertionException(File snapshotFolder,String error){
		
		
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper(driver);
		try {
			String fileName = webDriverWrapper.captureAssertionScreenShot(snapshotFolder,error);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
