package com.aqm.staf.framework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends WebPage {

	private PageElement cwissCoreInsuranceTitle;
	private PageElement myPreferencePage;
	private PageElement productPage;
	private PageElement securityPage;
	private PageElement partyPage;
	private PageElement policyPage;
	private PageElement claimPage;
	private PageElement accountingPage;
	private PageElement auditPage;
	private PageElement reInsurancePage;
	private PageElement batchPage;
	private PageElement documentPage;
	private PageElement reportsPage;
	private PageElement logoutPage;
	private PageElement accountingPagetitle;
	private PageElement accountingPageProceedButton;


	public BasePage(WebDriver driver, String pageName) {
		super(driver, pageName);

		cwissCoreInsuranceTitle = new PageElement(By.xpath("//td//font/b[contains(text(),'CWISS - CORE INSURANCE')]"), " CWISS - CORE INSURANCE Page Title", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		myPreferencePage = new PageElement(By.linkText("My Preference"), "My Preference Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		productPage = new PageElement(By.linkText("Product"), "Product Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		securityPage = new PageElement(By.linkText("Security"), "Security Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		partyPage = new PageElement(By.linkText("Party"), "Party Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		policyPage = new PageElement(By.linkText("Policy"), "Policy Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		claimPage=new PageElement(By.linkText("Claim"), "Claim Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		accountingPage=new PageElement(By.linkText("Accounting"), "Account Page", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
		auditPage=new PageElement(By.linkText("Audit"), "Audit Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		reInsurancePage=new PageElement(By.linkText("Reinsurance"), "Reinsurance Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		batchPage=new PageElement(By.linkText("Batch"), "Batch Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		documentPage=new PageElement(By.linkText("Document"), "Document Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		reportsPage=new PageElement(By.linkText("Reports"), "Reports Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10); 
		logoutPage=new PageElement(By.xpath("//a//b[contains(text(),'Logout')]"), "Logout Page", false, WaitType.WAITFORELEMENTTOBEEENABLED, 10);
		accountingPagetitle=new PageElement(By.xpath("//div//b[contains(text(),'Company Selection')]"), "Accounting Page Title", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
		accountingPageProceedButton=new PageElement(By.name("Proceed"), "Proceed button in Accounting page", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
	}
	public BasePage(){
		super();
	}
	public void navigateToMyPreferencePage()
	{
		click(myPreferencePage);
	}

	public void navigateToProductPage()
	{
		click(productPage);
	}

	public void navigateToSecurityPage()
	{
		click(securityPage);
	}

	public void navigateToPartyPage(){
		switchToFrame("head");
		click(partyPage);
	}

	public void navigateToPolicyPage() 
	{
		switchToFrame("head");
		click(policyPage);
	}

	public void navigateToClaimPage()
	{
		switchToFrame("head");
		click(claimPage);
	}

	public void navigateToAccountingPage()
	{
		switchToFrame("head");
		click(accountingPage);
	}
	public void navigateToAuditPage()
	{
		click(auditPage);
	}

	public void navigateToReinsurancePage()
	{
		click(reInsurancePage);
	}

	public void navigateToBatchPage()
	{
		click(batchPage);
	}

	public void navigateToDocumentPage()
	{
		click(documentPage);
	}

	public void navigateToReportsPage()
	{
		click(reportsPage);
	}

	public void logout(WebDriver driver){
		switchToFrame("head",driver);
		click(logoutPage);
	}
}
