package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aqm.staf.framework.core.BasePageOrangeHRM;
import com.aqm.staf.framework.core.PageElement;
import com.aqm.staf.framework.core.WaitType;
import com.aqm.staf.library.databin.UserEntity;

public class UserSearchPage extends BasePageOrangeHRM{
	private PageElement addButton;
	private PageElement userNameTextField;
	public UserSearchPage(WebDriver driver,String pageName){
		super(driver, pageName);
		addButton=new PageElement(By.id("btnAdd"), "Add Button", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
		userNameTextField=new PageElement(By.id("searchSystemUser_userName"), "Search User Namne text field", true, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
	}
	public void clickAddButton(UserEntity userEntity){
		if(userEntity.getBooleanValueForField("ConfigAdd")){
			click(addButton);
		}
	}
	public void fillAndSearchUser(UserEntity userEntity){
		
	}
}
