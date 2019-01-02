package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmentable;

import com.aqm.staf.framework.core.BasePage;
import com.aqm.staf.framework.core.CustomAssert;
import com.aqm.staf.framework.core.PageElement;
import com.aqm.staf.framework.core.WaitType;
import com.aqm.staf.library.databin.LoginEntity;

public class LoginPage extends BasePage{
	private PageElement userName;
	private PageElement password;
	private PageElement submitLogin;

	public LoginPage(WebDriver driver,String pageName){
		super(driver,pageName);
		userName=new PageElement(By.id("txtUsername"), "UserName", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		password=new PageElement(By.id("txtPassword"), "Password", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		submitLogin=new PageElement(By.id("btnLogin"), "Submit Button", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
	}
	public void fillAndSubmitLogin(LoginEntity loginEntity,CustomAssert customAssert){
		if(loginEntity.getAction().equalsIgnoreCase("add")){
			if (loginEntity.getBooleanValueForField("ConfigUserName")){
				clearAndSendKeys(userName, loginEntity.getStringValueForField("UserName"));
			}
			if (loginEntity.getBooleanValueForField("ConfigPassword")){
				clearAndSendKeys(password, loginEntity.getStringValueForField("Password"));
			}
			if (loginEntity.getBooleanValueForField("ConfigSubmit")){
				click(submitLogin);
			}
		}
	}
}
