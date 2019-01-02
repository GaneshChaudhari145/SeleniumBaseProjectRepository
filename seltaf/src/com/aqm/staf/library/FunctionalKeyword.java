package com.aqm.staf.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aqm.staf.framework.FrameworkServices;
import com.aqm.staf.framework.core.BasePageOrangeHRM;
import com.aqm.staf.framework.core.CustomAssert;
import com.aqm.staf.framework.core.ScenarioTestData;
import com.aqm.staf.framework.core.ScenarioTestData;
import com.aqm.staf.framework.core.entity.ExecutionMasterTestSuites;
import com.aqm.staf.framework.core.entity.ExecutionTestScenarios;
import com.aqm.staf.framework.core.entity.ExecutionTestSteps;
import com.aqm.staf.framework.core.entity.ExecutionTestSuites;
import com.aqm.staf.framework.core.entity.MasterTestSuite;
import com.aqm.staf.framework.core.entity.TestPlan;
import com.aqm.staf.framework.core.entity.TestScenario;
import com.aqm.staf.framework.core.entity.TestSuite;
import com.orangeHRM.pages.LoginPage;

public class FunctionalKeyword extends KeywordHelper{
	private  String stepGroup;
	protected void executeStep(
			TestPlan testPlan, ExecutionMasterTestSuites executionMasterTestSuite, MasterTestSuite masterTestSuite, ExecutionTestSuites executionTestSuite, TestSuite testSuite,
			ExecutionTestScenarios executionTestScenario, TestScenario testScenario, ExecutionTestSteps executionTestStep, ScenarioTestData testData, 
			WebDriver driver, File scenarioExecutionFolderReference, CustomAssert assertReference, boolean allStepExecutionMode) throws FileNotFoundException, IOException {
		String keyword = executionTestStep.getExecutionTestStepMasterAutomationScriptStepKeyword();
		String configData = executionTestStep.getExecutionTestStepMasterAutomationScriptStepConfigData();
		int stepNumber= executionTestStep.getExecutionTestStepMasterAutomationScriptStepExecutionSequence();
		stepGroup = executionTestStep.getExecutionTestStepMasterAutomationScriptStepStepGroup();
		FrameworkServices.logMessage("<B><u>StepNumber-</u>"+stepNumber+"<i><Font Color = /'#00008B/'> Executing :"+keyword+" </Font></i></B>");
		Properties configProperties = FrameworkServices.getConfigProperties();
		KeywordHelper keywordHelper=new KeywordHelper();
		//int skipStepsInExecution= Integer.parseInt(configProperties.getProperty("skipSteps"));
		switch (keyword) {
		case "LoginUser":
			loginHelper(testData, executionTestStep, assertReference, driver, configData);
			break;
		case "AddUser":
			userHelper(testData, executionTestStep, assertReference, driver, configData);
		default:
			break;
		}

	}
}

