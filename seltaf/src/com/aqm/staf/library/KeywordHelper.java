package com.aqm.staf.library;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aqm.staf.framework.core.BasePageOrangeHRM;
import com.aqm.staf.framework.core.CustomAssert;
import com.aqm.staf.framework.core.ScenarioTestData;
import com.aqm.staf.framework.core.ScenarioTestData;
import com.aqm.staf.framework.core.entity.ExecutionTestSteps;
import com.aqm.staf.library.databin.LoginEntity;
import com.orangeHRM.pages.AddUserPage;
import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.pages.UserSearchPage;
import com.aqm.staf.library.databin.UserEntity;

public class KeywordHelper extends BasePageOrangeHRM{
	public void loginHelper(ScenarioTestData testData,ExecutionTestSteps executionTestStep,CustomAssert assertReference,WebDriver driver,String stepGroup){
		List<LoginEntity> loginEntityList=testData.getLoginEntityRecords();
		for(LoginEntity loginData:loginEntityList){
			LoginPage loginPage=new LoginPage(driver, "LoginPage");
			loginPage.fillAndSubmitLogin(loginData, assertReference);
		}
	}
	public void userHelper(ScenarioTestData testData,ExecutionTestSteps executionTestStep,CustomAssert assertReference,WebDriver driver,String stepGroup){
		List<UserEntity>userEntitylist=testData.getUserEntityRecords();
		for(UserEntity userEntityData:userEntitylist){
			if(userEntityData.getAction().equalsIgnoreCase("add")&& userEntityData.getStepGroup().equalsIgnoreCase(stepGroup)){
				UserSearchPage userSearchPage=new UserSearchPage(driver, "User Search Page");
				navigateToUsers();
				userSearchPage.clickAddButton(userEntityData);
				AddUserPage addUserPage=new AddUserPage(driver, "User Add page");
				addUserPage.fillAndSubmitUser(userEntityData,assertReference);
			}
		}
	}
	public void verifyUserHelper(ScenarioTestData testData,ExecutionTestSteps executionTestStep,CustomAssert assertReference,WebDriver driver,String stepGroup){
		List<UserEntity>userEntitylist=testData.getUserEntityRecords();
		for(UserEntity userEntityData:userEntitylist){
			UserSearchPage userSearchPage=new UserSearchPage(driver, "User Search Page");
			navigateToUsers();
		}
	}
}
