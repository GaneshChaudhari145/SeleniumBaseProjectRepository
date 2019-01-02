package com.aqm.tests;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.tools.ant.taskdefs.LoadProperties;
import org.hibernate.Session;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aqm.TestLauncher;
import com.aqm.staf.framework.FrameworkServices;
import com.aqm.staf.framework.core.CustomAssert;
import com.aqm.staf.framework.core.EnvironmentParameter;
import com.aqm.staf.framework.core.LoginUserFromSyncMap;
import com.aqm.staf.framework.core.MapOfUserIDAssignedToAllocation;
import com.aqm.staf.framework.core.ScenarioTestData;
import com.aqm.staf.framework.core.TestManagerDBInterface;
import com.aqm.staf.framework.core.Uploader;
import com.aqm.staf.framework.core.entity.ExecutionMasterTestSuites;
import com.aqm.staf.framework.core.entity.ExecutionTestScenarios;
import com.aqm.staf.framework.core.entity.ExecutionTestSteps;
import com.aqm.staf.framework.core.entity.ExecutionTestSuites;
import com.aqm.staf.framework.core.entity.MasterTestSuite;
import com.aqm.staf.framework.core.entity.TestPlan;
import com.aqm.staf.framework.core.entity.TestScenario;
import com.aqm.staf.framework.core.entity.TestSuite;
import com.aqm.staf.framework.core.exceptions.ScriptExecutionException;
import com.aqm.staf.framework.core.exceptions.UndefinedKeywordException;
import com.aqm.staf.framework.core.hibernate.CustomizableEntity;
import com.aqm.staf.framework.core.hibernate.TestDataHibernateUtil;
import com.aqm.staf.framework.core.hibernate.TestManagerHibernateUtil;
import com.aqm.staf.framework.core.scenarioResult.ScenarioResultCollection;
import com.aqm.staf.framework.core.scenarioResult.ScenarioResultObject;
import com.aqm.staf.library.databin.LoginEntity;
import com.aqm.staf.library.FunctionalKeyword;

public class AutomationDriverScript extends FunctionalKeyword {

	public static File scenarioExecutionFolderReference = null;
	public static ScenarioResultObject sroForReference;
	//TODO Remove
	@Parameters({ 
		"platform", "browsername", "browserversion", 
		"testPlan_Reference", 
		"executionMasterTestSuite_Reference", "executionMasterTestSuite_MasterTestSuiteReference", 
		"executionTestSuite_Reference",  "executionTestSuite_TestSuiteReference", 
		"executionTestScenario_Reference", "executionTestScenario_TestScenarioReference","executionTestScenario_Description"/*,"executionTestScenario_Lob","executionTestScenario_Product","executionTestScenario_Scripter"*/})
	@Test (groups = { "functional" })

	//TODO Remove
	public void scriptExecutioner(String platform, String browserName, String browserVersion, String testPlan_Reference, String executionMasterTestSuite_Reference, 
			String executionMasterTestSuite_MasterTestSuiteReference, String executionTestSuite_Reference, String executionTestSuite_TestSuiteReference,
			String executionTestScenario_Reference, String executionTestScenario_TestScenarioReference, String executionTestScenario_Description/*String executionTestScenario_Lob,String executionTestScenario_Product,String executionTestScenario_Scripter*/) throws Exception {

		ScenarioResultObject sro = new ScenarioResultObject();
		sroForReference=sro;
		sro.setTypeOfFailure("Test Data related issue");

		sro.setPlatformName(platform);
		sro.setBrowserNameVersion(browserName+"-"+browserVersion);
		//TODO Remove
		/*sro.setLobName(executionTestScenario_Lob);
		sro.setProductName(executionTestScenario_Product);
		sro.setScripterName(executionTestScenario_Scripter);*/

		sro.setScenarioID(executionTestScenario_TestScenarioReference);
		sro.setDateTimeOfExecution(new GregorianCalendar());



		StopWatch ScenarioWatch = new StopWatch();
		ScenarioWatch.reset();
		ScenarioWatch.start();
		CustomizableEntity.runningElement="";
		CustomizableEntity.runningRowReference="";
		CustomizableEntity.runningSheetName="";
		boolean AssertError=false;
		FrameworkServices frameworkService = new FrameworkServices();

		Date executionTestScenario_StartTime = frameworkService.getCurrentSystemTime();
		Date executionTestScenario_EndTime = null;
		int executionTestScenario_ExecutionStatus = 0;

		Session sessionTestManagerData = TestManagerHibernateUtil.getInstance().getNewSession();
		Session sessionTestData = TestDataHibernateUtil.getInstance().getNewSession();

		ScenarioTestData testData = new ScenarioTestData (sessionTestData, executionTestScenario_TestScenarioReference);

		TestManagerDBInterface testManagerDBInterface = new TestManagerDBInterface(sessionTestManagerData);
		WebDriver driver = null;
		Properties configProperties = null;

		TestPlan testPlan = null;
		ExecutionMasterTestSuites executionMasterTestSuite = null;
		MasterTestSuite masterTestSuite = null;
		ExecutionTestSuites executionTestSuite = null;
		TestSuite testSuite = null;
		ExecutionTestScenarios executionTestScenario = null;
		TestScenario testScenario = null;
		List<ExecutionTestSteps> executionTestSteps = null;		

		String snapshotLogFolderPath; 


		try {
			EnvironmentParameter environmentParameters = new EnvironmentParameter();
			environmentParameters.setPlatform(platform);
			environmentParameters.setBrowserName(browserName);
			environmentParameters.setBrowserVersion(browserVersion);


			testPlan = testManagerDBInterface.getTestPlanByReference(testPlan_Reference);

			executionMasterTestSuite = testManagerDBInterface.getExecutionMasterTestSuiteByReference(executionMasterTestSuite_Reference);
			masterTestSuite = testManagerDBInterface.getMasterTestSuiteByReference(executionMasterTestSuite_MasterTestSuiteReference);

			executionTestSuite = testManagerDBInterface.getExecutionTestSuiteByReference(executionTestSuite_Reference);
			testSuite = testManagerDBInterface.getTestSuiteByReference(executionTestSuite_TestSuiteReference);

			executionTestScenario = testManagerDBInterface.getExecutionTestScenarioByReference(executionTestScenario_Reference);
			testScenario = testManagerDBInterface.getTestScenarioByReference(executionTestScenario_TestScenarioReference);


			snapshotLogFolderPath = testPlan.getTestPlanCurrentScenarioExecutionResultsFolder() + "\\\\" 
					+ masterTestSuite.getMasterTestSuiteReference() + "_" + testSuite.getTestSuiteReference() + "_" + testScenario.getTestScenarioReference();
			scenarioExecutionFolderReference = new File(snapshotLogFolderPath);
			scenarioExecutionFolderReference.mkdirs();


			FrameworkServices.logMessage("<B><Font Color = 'White'> <u>Scenario Description</u> :"+ executionTestScenario_Description +" </Font></B>");
			configProperties = FrameworkServices.getConfigProperties();
			driver = FrameworkServices.getWebDriverInstance(environmentParameters);
			String applicationURL = configProperties.getProperty("ApplicationURL");		
			driver.get(applicationURL);

			boolean allStepExecutionMode = Boolean.parseBoolean(configProperties.getProperty("AllStepExecutionMode"));

			CustomAssert assertReference = new CustomAssert(driver);
			executionTestSteps = testManagerDBInterface.getAllExecutionTestStepsByExecutionTestScenarioReference(executionTestScenario.getExecutionTestScenarioReference());
			for (ExecutionTestSteps executionTestStep : executionTestSteps) {
				Date executionTestStep_StartTime = frameworkService.getCurrentSystemTime();
				Date executionTestStep_EndTime = null;
				int testStep_ExecutionStatus = 0;



				try {
					//executeStep(testPlan, executionMasterTestSuite, masterTestSuite, executionTestSuite, testSuite, executionTestScenario, testScenario, executionTestStep, testData, driver, scenarioExecutionFolderReference);
					//testStep_ExecutionStatus = 1;
					assertReference.setWarning(false);
					executeStep(testPlan, executionMasterTestSuite, masterTestSuite, executionTestSuite, testSuite, executionTestScenario, testScenario, executionTestStep, testData, driver, scenarioExecutionFolderReference, assertReference, allStepExecutionMode);
					if (assertReference.isAllChecks()) {
						if (assertReference.isWarning()) {
							testStep_ExecutionStatus = 2;
						}						
						else {
							testStep_ExecutionStatus = 1;
						}
					}                            
					else {
						sro.setTypeOfFailure("Assertion Failures");
						new ScriptExecutionException("Step failed with Assertion Failures. ");

					}
				}
				catch(ScriptExecutionException see){

					testStep_ExecutionStatus = 3;
					throw see;
				}
				catch (UndefinedKeywordException uke) {
					testStep_ExecutionStatus = 3;
				}
				finally {
					executionTestStep_EndTime = frameworkService.getCurrentSystemTime();
				}

				executionTestStep.setExecutionTestStepStartTime(executionTestStep_StartTime);
				executionTestStep.setExecutionTestStepEndTime(executionTestStep_EndTime);
				executionTestStep.setExecutionTestStepExecutionStatus(testStep_ExecutionStatus);
				testManagerDBInterface.addUpdateExecutionTestStep(executionTestStep);

				if (testStep_ExecutionStatus == 1) {
					if (executionTestScenario_ExecutionStatus == 1 || executionTestScenario_ExecutionStatus == 0) {
						executionTestScenario_ExecutionStatus = 1; 
					}
					else if (executionTestScenario_ExecutionStatus == 2) {
						executionTestScenario_ExecutionStatus = 2; 
					}
				}
				else if (testStep_ExecutionStatus == 2) {
					if (executionTestScenario_ExecutionStatus == 1 || executionTestScenario_ExecutionStatus == 0) {
						executionTestScenario_ExecutionStatus = 2; 
					}
					else if (executionTestScenario_ExecutionStatus == 2) {
						executionTestScenario_ExecutionStatus = 2; 
					}
				}
				else if (testStep_ExecutionStatus == 3) {
					executionTestScenario_ExecutionStatus = 3;
					break;
				}
			}

			executionTestScenario_EndTime = frameworkService.getCurrentSystemTime();

			executionTestScenario.setExecutionTestScenarioStartTime(executionTestScenario_StartTime);
			executionTestScenario.setExecutionTestScenarioEndTime(executionTestScenario_EndTime);
			executionTestScenario.setExecutionTestScenarioExecutionStatus(executionTestScenario_ExecutionStatus);

			testManagerDBInterface.addUpdateExecutionTestScenario(executionTestScenario);
			if(executionTestScenario_ExecutionStatus == 3){
				sro.setTypeOfFailure("Web Page related issue");
				throw new ScriptExecutionException ("Script Step Failed in Execution.");
			}
			//Added by PKP on 09-10-2015
			if(executionTestScenario_ExecutionStatus == 2){
				sro.setTypeOfFailure("Assertion Failures");
				throw new ScriptExecutionException ("Step failed with Assertion Failures.");

			}
			AssertError=true;
		} 
		catch (ScriptExecutionException exception) {
			sro.setReasonIfFailed(exception.getMessage());
			frameworkService.handleException(driver, exception, scenarioExecutionFolderReference,executionTestScenario_TestScenarioReference);
			//frameworkService.handleException(driver, exception, logger);
			throw exception;
		} 
		catch (Exception exception) {
			sro.setReasonIfFailed(exception.getMessage());
			frameworkService.handleException(driver, exception, scenarioExecutionFolderReference,executionTestScenario_TestScenarioReference);
			//frameworkService.handleException(driver, exception, logger);
			//Releasing Login User

			throw exception;
		} 

		finally {
			ScenarioWatch.stop();
			System.out.println();
			String timeMessage="Scenario ID: "+executionTestScenario_TestScenarioReference+" Execution Completed in "+DurationFormatUtils.formatDurationWords(ScenarioWatch.getTime(),true,true);
			System.out.println(timeMessage);
			sro.setDurationOfExecution(ScenarioWatch.getTime());
			System.out.println("Following Data Sheet were used in Execution of "+executionTestScenario_TestScenarioReference);

			int count=1;
			for(String sheetName:CustomizableEntity.listOfSheetName){
				System.out.println(count+". "+sheetName);
				count++;
			}

			System.out.println();
			FrameworkServices.logMessage("<h3><B>"+timeMessage+"</h3></B>");


			String user="";
			if(MapOfUserIDAssignedToAllocation.listOfUserIDForExecution.containsValue(executionTestScenario_TestScenarioReference))
				user = LoginUserFromSyncMap.getKeyByValue(MapOfUserIDAssignedToAllocation.listOfUserIDForExecution,executionTestScenario_TestScenarioReference);
			MapOfUserIDAssignedToAllocation.listOfUserIDForExecution.put(user,"Free");
			System.out.println("");
			System.out.println("Execution Done for: "+executionTestScenario_TestScenarioReference+" User ID: "+user+" released");
			System.out.println("After release of USER ID Updated__MAP shows as below");
			System.out.println("Status wise MAP::"+MapOfUserIDAssignedToAllocation.listOfUserIDForExecution.toString());
			System.out.println("");

			if (!(driver == null)) {
				try {
/*
					LoginPage loginPage=new LoginPage(driver, "Login Page");
					loginPage.logOut();*/
				}
				catch(Exception e) {

				}
				driver.close();
				driver.quit();

				//Releasing Login User
			/*	List<LoginMasterEntity> loginMasterEntityList = testData.getLoginMasterRecordsForScenario(executionTestScenario.getExecutionTestScenarioTestScenarioReference());
				for (LoginMasterEntity loginMasterEntity : loginMasterEntityList){
					loginMasterEntity.setUsedByTestScenario("NotUsed");
					testData.updateRecordsForCriteria(loginMasterEntity);
				}*/

			}
			if(AssertError==true){
				frameworkService.logMessage("<Br> <B>"+"["+"ScenarioID:::"+executionTestScenario_TestScenarioReference+"-->"+"PASSED" +"]" +"</B>"+ "</Br>");
				sro.setScenarioStatus("PASSED");
				sro.setTypeOfFailure("");
				sro.setReasonIfFailed("");

			}else{
				frameworkService.logMessage("<Br> <B>"+"["+"ScenarioID:::"+executionTestScenario_TestScenarioReference+"-->"+"FAILED" +"]" +"</B>"+ "</Br>");
				sro.setScenarioStatus("FAILED");
			}


			TestManagerHibernateUtil.getInstance().closeSession(sessionTestManagerData);
			TestManagerHibernateUtil.getInstance().closeSession(sessionTestData);
			ScenarioResultCollection.getInstance().addIntoResultCollection(sro);
			System.out.println("Done");
		}

	}
}
