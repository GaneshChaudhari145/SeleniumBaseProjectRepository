package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aqm.staf.framework.core.AssertionType;
import com.aqm.staf.framework.core.BasePageOrangeHRM;
import com.aqm.staf.framework.core.CustomAssert;
import com.aqm.staf.framework.core.PageElement;
import com.aqm.staf.framework.core.WaitType;
import com.aqm.staf.library.databin.UserEntity;

public class AddUserPage extends BasePageOrangeHRM {
	private PageElement essRoleDropDown;
	private PageElement superViserRoleDropDown;
	private PageElement adminRoleDropDown;
	private PageElement employeeNameTextfield;
	private PageElement userNameTextfield;
	private PageElement statusDropDown;
	private PageElement passwordTextfield;
	private PageElement confirmPasswordTextfield;
	private PageElement saveButton;
	public AddUserPage(WebDriver driver,String pageName){
		super(driver, pageName);
		essRoleDropDown=new PageElement(By.id("systemUser_essRole"), "ESS role", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		superViserRoleDropDown=new PageElement(By.id("systemUser_supervisorRole"), "Supervisor role", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		adminRoleDropDown=new PageElement(By.id("systemUser_adminRole"), "Admin role", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		employeeNameTextfield=new PageElement(By.id("systemUser_employeeName_empName"), "Employee Name", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		userNameTextfield=new PageElement(By.id("systemUser_userName"), "User Name", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		statusDropDown=new PageElement(By.id("systemUser_status"), "Status", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		passwordTextfield=new PageElement(By.id("systemUser_password"), "password", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		confirmPasswordTextfield=new PageElement(By.id("systemUser_confirmPassword"), "Confirm password", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		saveButton=new PageElement(By.id("btnSave"), "Button Save", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
	}

	public void fillAddUserPage(UserEntity userEntity,CustomAssert assertReference ){
		if(userEntity.getAction().equalsIgnoreCase("add")){
			if(userEntity.getBooleanValueForField("ConfigEssRole")){
				selectValueFromList(essRoleDropDown, userEntity.getStringValueForField("EssRole"));
			}
			if(userEntity.getBooleanValueForField("ConfigSuperviserRole")){
				selectValueFromList(superViserRoleDropDown, userEntity.getStringValueForField("SuperviserRole"));
			}
			if(userEntity.getBooleanValueForField("ConfigAdminRole")){
				selectValueFromList(adminRoleDropDown, userEntity.getStringValueForField("AdminRole"));
			}
			if(userEntity.getBooleanValueForField("ConfigEmployeeName")){
				clearAndSendAutoPopulateText(employeeNameTextfield, userEntity.getStringValueForField("EmployeeName"));
			}
			if(userEntity.getBooleanValueForField("ConfigUserName")){
				clearAndSendKeys(userNameTextfield, userEntity.getStringValueForField("UserName"));
			}
			if(userEntity.getBooleanValueForField("ConfigStatus")){
				selectValueFromList(statusDropDown, userEntity.getStringValueForField("Status"));
			}
			if(userEntity.getBooleanValueForField("ConfigPassword")){
				clearAndSendKeys(passwordTextfield, userEntity.getStringValueForField("Password"));
			}
			if(userEntity.getBooleanValueForField("ConfigConfirmPassword")){
				clearAndSendKeys(confirmPasswordTextfield, userEntity.getStringValueForField("ConfirmPassword"));
			}
		}
		else if (userEntity.getAction().equalsIgnoreCase("verify")) {
			if(userEntity.getBooleanValueForField("ConfigEssRole")){
				assertReference.assertEquals(userEntity.getStringValueForField("EssRole"), fetchValueFromList(essRoleDropDown), AssertionType.WARNING);
			}
			if(userEntity.getBooleanValueForField("ConfigSuperviserRole")){
				assertReference.assertEquals(userEntity.getStringValueForField("SuperviserRole"),fetchValueFromList(superViserRoleDropDown),AssertionType.WARNING);
			}
			if(userEntity.getBooleanValueForField("ConfigAdminRole")){
				assertReference.assertEquals(userEntity.getStringValueForField("AdminRole"),fetchValueFromList(adminRoleDropDown),AssertionType.WARNING);
			}
			if(userEntity.getBooleanValueForField("ConfigEmployeeName")){
				assertReference.assertEquals(userEntity.getStringValueForField("EmployeeName"),fetchValueFromTextFields(employeeNameTextfield),AssertionType.WARNING);
			}
			if(userEntity.getBooleanValueForField("ConfigUserName")){
				assertReference.assertEquals(userEntity.getStringValueForField("UserName"),fetchValueFromTextFields(userNameTextfield),AssertionType.WARNING);
			}
			if(userEntity.getBooleanValueForField("ConfigStatus")){
				assertReference.assertEquals(userEntity.getStringValueForField("Status"),fetchValueFromList(statusDropDown),AssertionType.WARNING);
			}
		}
	}
	public void submit(UserEntity userEntity){
		if(userEntity.getAction().equalsIgnoreCase("add")){
			if(userEntity.getBooleanValueForField("ConfigSave")){
				click(saveButton);
			}
		}
	}
	public void fillAndSubmitUser(UserEntity userEntity,CustomAssert assertReference){
		fillAddUserPage(userEntity,assertReference);
		submit(userEntity);
	}
}
