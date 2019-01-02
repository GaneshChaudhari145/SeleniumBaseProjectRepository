
package com.aqm.staf.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aqm.staf.framework.core.EnvironmentParameter;
import com.aqm.staf.framework.core.WebDriverWrapper;
import com.aqm.staf.framework.core.exceptions.FrameworkExecutionException;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import com.aqm.staf.library.databin.*;

//import org.openqa.selenium.WebDriverBackedSelenium;
@SuppressWarnings("deprecation")
public class FrameworkServices {
	static Properties configProp;
	static FileInputStream configInput;

	public static Properties getConfigProperties() throws FileNotFoundException, IOException {
		if (configProp==null) {
			configProp = new Properties();
			configInput = new FileInputStream("config.properties");
			configProp.load(configInput);
		}
		return configProp;
	}

	public static final boolean DEBUGLEVEL = true;
	public static File screenShotFolderPath;
	//static Logger log = Logger.getLogger(FrameworkServices.class.getName());
	String Status;
	public synchronized static String getUniqueFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYYhhmmss");
		java.util.Date date = new java.util.Date();
		return (sdf.format(date));
	}

	public static WebDriver getWebDriverInstance(EnvironmentParameter environmentParameter) throws FileNotFoundException, IOException, InterruptedException {
		DesiredCapabilities capability = new DesiredCapabilities();

		if (environmentParameter.getBrowserName().equalsIgnoreCase("ie")) {
			// IE Driver Pre-requiste system setting
			IESystemSettings();
			//File IEDriverPath = new File("../Grid/IEDriverServer.exe");
			//File IEDriverPath = new File("D:\\NIATestAutomation\\Grid\\IEDriverServer.exe");
			File IEDriverPath = new File("\\\\10.13.13.152\\Grid\\IEDriverServer.exe");
			System.out.println("IEDriver Path = "+IEDriverPath.getCanonicalPath());
			System.setProperty("webdriver.ie.driver",IEDriverPath.getCanonicalPath());
			capability = DesiredCapabilities.internetExplorer();
			//capability.setCapability("ignoreZoomSetting", true);
			//capability.setCapability("nativeEvents",false);
			//capability.setVersion(environmentParameter.getBrowserVersion());

			//capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			//capability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		} 
		else if (environmentParameter.getBrowserName().equalsIgnoreCase("firefox")) {
			capability = DesiredCapabilities.firefox();
		}
		else if (environmentParameter.getBrowserName().equalsIgnoreCase("chrome")) {
			//File chromeDriverPath = new File("../Grid/chromedriver.exe");
			File chromeDriverPath = new File("\\\\localhost\\Grid\\chromedriver.exe");
			//File chromeDriverPath = new File("D:\\NIATestAutomation\\Grid\\chromedriver.exe");
			System.out.println("ChromeDriver Path = "+chromeDriverPath.getCanonicalPath());
			System.setProperty("webdriver.chrome.driver",chromeDriverPath.getCanonicalPath());
			capability = DesiredCapabilities.chrome();
			capability.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			ArrayList<String> excludeSwitches = new ArrayList<String>();
			excludeSwitches.add("ignore-certificate-errors"); 
			options.setExperimentalOption("excludeSwitches", excludeSwitches);		
			capability.setCapability(ChromeOptions.CAPABILITY, options);
		} 
		else if (environmentParameter.getBrowserName().equalsIgnoreCase("phantomjs")) {
			capability = DesiredCapabilities.phantomjs();
			capability.setCapability("phantomjs.binary.path", "D:/Grid/phantomjs.exe"); //NBS - needs change in code
		} 
		else if (environmentParameter.getBrowserName().equalsIgnoreCase("opera")) {
			capability = DesiredCapabilities.opera();
		}
		else if (environmentParameter.getBrowserName().equalsIgnoreCase("safari")) {
			capability = DesiredCapabilities.safari();
		}
		else {
			throw new FrameworkExecutionException ("Browser Name: " + environmentParameter.getBrowserName()
					+ " is Not valid Try any one of following \n1 ie ,2 firefox,3 chrome 4 opera");
		}

		if (environmentParameter.getPlatform().equalsIgnoreCase("windows"))
			capability.setPlatform(Platform.WINDOWS);
		else if (environmentParameter.getPlatform().equalsIgnoreCase("linux"))
			capability.setPlatform(Platform.LINUX);
		else if (environmentParameter.getPlatform().equalsIgnoreCase("mac"))
			capability.setPlatform(Platform.MAC);
		else
			throw new FrameworkExecutionException("Platform Name: " + environmentParameter.getPlatform()
					+ " is Not valid Try any one of following\n1 windows ,2 linux,3 mac");

		boolean enableProxySettings = Boolean.parseBoolean(getConfigProperties().getProperty("EnableProxySettings"));
		String proxyUrl = getConfigProperties().getProperty("ProxyServer");
		String proxyServerPort = getConfigProperties().getProperty("ProxyServerPort");
		String proxyServerUsername = getConfigProperties().getProperty("ProxyServerUsername");
		String proxyServerPassword = getConfigProperties().getProperty("ProxyServerPassword");

		//0 - Direct connection (or) no proxy. 
		//1 - Manual proxy configuration
		//2 - Proxy auto-configuration (PAC).
		//4 - Auto-detect proxy settings.
		//5 - Use system proxy settings. 
		if(enableProxySettings){
			Proxy proxy = new Proxy();
			proxy.setProxyType(Proxy.ProxyType.MANUAL);
			proxy.setHttpProxy(proxyUrl+":"+proxyServerPort);
			proxy.setSslProxy(proxyUrl+":"+proxyServerPort);
			proxy.setFtpProxy(proxyUrl+":"+proxyServerPort);
			//proxy.setSocksUsername(proxyServerUsername);
			//proxy.setSocksPassword(proxyServerPassword);
			capability.setCapability(CapabilityType.PROXY, proxy);
		}
		else {
			capability.setCapability(CapabilityType.PROXY, new Proxy().setProxyType(Proxy.ProxyType.DIRECT));
		}

		String gridURL ="";
		Boolean jenkinsExecution = Boolean.parseBoolean(getConfigProperties().getProperty("JenkinsExecution"));
		if (jenkinsExecution){
			gridURL = getConfigProperties().getProperty("JenkinsGridURL");
		}else{
			gridURL = getConfigProperties().getProperty("GridURL");
		}

		String applicationURL = getConfigProperties().getProperty("ApplicationURL");
		WebDriver driver = new RemoteWebDriver(new URL(gridURL), capability);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public static DefaultSelenium getWebDriverBackedSeleniumInstance(WebDriver driver) throws FileNotFoundException, IOException {
		String applicationURL = getConfigProperties().getProperty("ApplicationURL");
		DefaultSelenium selenium = new WebDriverBackedSelenium(driver, applicationURL);
		return selenium;
	}

	public static List<Object> getEnvironmentParameterListFromEnvironmentString(String environmentString) {
		List<Object> environmentParameterList = new ArrayList<Object>();
		EnvironmentParameter environmentParameter = new EnvironmentParameter();
		StringTokenizer environmentTokens = new StringTokenizer(environmentString, ";");
		while (environmentTokens.hasMoreTokens()) {
			String environmentToken = environmentTokens.nextToken();
			StringTokenizer environmentValuesTokens = new StringTokenizer(environmentToken, ",");
			while (environmentValuesTokens.hasMoreTokens()) {
				String environmentValuesToken = environmentValuesTokens.nextToken();
				String environmentKey = environmentValuesToken.split(":")[0];
				String environmentValue = environmentValuesToken.split(":")[1];

				if (environmentKey.equalsIgnoreCase("browserName")) {
					environmentParameter.setBrowserName(environmentValue);
				} else if (environmentKey.equalsIgnoreCase("browserVersion")) {
					environmentParameter.setBrowserName(environmentValue);
				} else if (environmentKey.equalsIgnoreCase("platform")) {
					environmentParameter.setBrowserName(environmentValue);
				}
			}
			environmentParameterList.add(new Object[]{environmentParameter});
		}
		return environmentParameterList;
	}

	/*@BeforeSuite(alwaysRun = true)
	public void setUpFrameworkForExecution() throws Throwable {
		try {
			screenShotFolderPath = new File("ExecutionScreenshot").getCanonicalFile();
			if (screenShotFolderPath.exists()) {
				screenShotFolderPath.delete();
			}
			screenShotFolderPath.mkdir();

			ConfigurationProperties configurationProperties = new ConfigurationProperties();
			PropertyConfigurator.configure("config/log4j.properties");
			UploadTestDataToDataBase uploadTestDataToDataBase = new UploadTestDataToDataBase();
			if (configurationProperties.getProperty(ConfigurationProperties.uploadCompleteDataSetToDbFlag).equalsIgnoreCase("true")) {
				uploadTestDataToDataBase.readTestDataFromExcelAndUploadItToDatabase();
			} 
			else if (configurationProperties.getProperty(
					ConfigurationProperties.uploadTestDataToDbFlag).equalsIgnoreCase("true")) {
				uploadTestDataToDataBase.uploadModifiedDataToDataBase();
			} 
			else
				log.info("Test Data not uploaded as flag in property file was false");
		} catch (Exception e) {
			log.fatal("Unable to upload data into database and system exited with error--" + e.getMessage());
			System.out
			.println("Unable to upload data into database and system exited with error--" + e.getMessage());
			System.exit(1);
		}
	}
	 */

	public void sendEmailNotification(String screenShotPath, String fileName, String exceptionMessage, String tescaseName, List<String> reporterOutPutList) throws FileNotFoundException, IOException {
		boolean enableEmailSettings = Boolean.parseBoolean(getConfigProperties().getProperty("EnableEmailSettings"));
		if (enableEmailSettings) {
			String emailServer = getConfigProperties().getProperty("EmailServer");
			String emailUsername = getConfigProperties().getProperty("EmailUsername");
			String emailPassword = getConfigProperties().getProperty("EmailPassword");
			String fromEmailAddress = getConfigProperties().getProperty("FromEmailAddress");
			String[] toEmailAddresses = getConfigProperties().getProperty("ToEmailAddresses").split(",");

			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", emailServer);
			properties.setProperty("mail.user", emailUsername);
			properties.setProperty("mail.password", emailPassword);

			Session session = Session.getDefaultInstance(properties);

			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmailAddress));
				InternetAddress[] addressTo = new InternetAddress[toEmailAddresses.length];
				for (int i = 0; i < toEmailAddresses.length; i++) {
					addressTo[i] = new InternetAddress(toEmailAddresses[i]);
				}
				message.setRecipients(RecipientType.TO, addressTo);
				//message.setSubject("Automation Result TestCase -> " + tescaseName + " Failed on " + 
				//	Reporter.getCurrentTestResult().getTestContext().getSuite().getXmlSuite().getParameter("browsername").toUpperCase());
				message.setSubject("Automation Result TestCase -> " + tescaseName + " Failed. ");

				BodyPart messageBodyPart = new MimeBodyPart();
				String mailBodyText = "<P>Log output::" + "<BR>";
				int i = 1;
				for (String reporterOutput : reporterOutPutList) {
					if (reporterOutput.contains("Failled"))
						mailBodyText = mailBodyText + "<h5 style=\"color:#FF0000\">" + i + ". " + reporterOutput + "</h5>" + "<Br>";
					else
						mailBodyText = mailBodyText + i + ". " + reporterOutput + "<Br>";
					i++;
				}

				mailBodyText = mailBodyText + "<Br>" + "Exception Message::" + "<Br>" + exceptionMessage + "</P>";
				messageBodyPart.setContent(mailBodyText, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(screenShotPath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileName);
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);
				Transport.send(message);
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {

			}
		}
	}

	public static void logMessage(String message) {
		//public void logMessage(String message, Logger logger) {
		//logger.info(message);
		Reporter.log(message);
	}

	/*
	public void handleException(WebDriver driver, Exception exception, Logger logger) throws Throwable {
		try {
			WebDriverWrapper webDriverWrapper = new WebDriverWrapper(driver);
			FrameworkServices frameworkService = new FrameworkServices();
			frameworkService.logMessage(exception.getMessage(), logger);
			String fileName = webDriverWrapper.captureScreenShot(FrameworkServices.screenShotFolderPath);
			frameworkService.sendEmailNotification(FrameworkServices.screenShotFolderPath + File.separator + fileName, fileName,
					exception.getMessage(), Reporter.getCurrentTestResult().getMethod().getMethodName(),
					Reporter.getOutput(Reporter.getCurrentTestResult()));
			Reporter.log("<br> <img src=\"" + FrameworkServices.screenShotFolderPath
					+ File.separator + fileName + "\" alt=\"ScreenShot Not Available\" height=\"400\" width=\"400\"> <br>");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(exception);

		}
	}


	public void handleException(WebDriver driver, AssertionError assertionError, Logger logger) throws Throwable {
		try {
			WebDriverWrapper webDriverWrapper = new WebDriverWrapper(driver);
			FrameworkServices frameworkService = new FrameworkServices();
			frameworkService.logMessage(assertionError.getMessage(), logger);
			String fileName = webDriverWrapper.captureScreenShot(FrameworkServices.screenShotFolderPath);
			frameworkService.sendEmailNotification(FrameworkServices.screenShotFolderPath + File.separator + fileName, fileName,
					assertionError.getMessage(), Reporter.getCurrentTestResult().getMethod().getMethodName(),
					Reporter.getOutput(Reporter.getCurrentTestResult()));
			Reporter.log("<br> <img src=\"" + FrameworkServices.screenShotFolderPath + File.separator
					+ fileName + "\" alt=\"Smiley face\" height=\"400\" width=\"400\"> <br>");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception (assertionError);
		}
	}
	 */

	public void handleException(WebDriver driver, Exception exception, File snapshotFolder,String ScenarioID) throws Exception  {
		try {

			if(exception==null){
				Status="---PASSED";
			}else{
				Status= "---FAILED";
			}
			WebDriverWrapper webDriverWrapper = new WebDriverWrapper(driver);
			FrameworkServices frameworkService = new FrameworkServices();
			frameworkService.logMessage("<Br> <B>"+"["+"ScenarioID:::"+ScenarioID+"-->"+Status+ "]" +"</B>"+ exception.getMessage()+"</Br>");

			WebDriverWait wait = new WebDriverWait(driver, 3 );
			if(wait.until(ExpectedConditions.alertIsPresent())!=null){
				String popupMesage= driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();

				frameworkService.logMessage("<Br> <B> Unexpected Alert Open & its Text:::</B>"+ popupMesage+"</Br>");
				

			}
			String fileName = webDriverWrapper.captureScreenShot(snapshotFolder);
			frameworkService.sendEmailNotification(snapshotFolder + File.separator + fileName, fileName,
					exception.getMessage(), Reporter.getCurrentTestResult().getMethod().getMethodName(),
					Reporter.getOutput(Reporter.getCurrentTestResult()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(exception);
		}
	}

	public void handleException(WebDriver driver, AssertionError assertionError, File snapshotFolder,String ScenarioID) throws Exception {
		try {
			if(assertionError==null){
				Status="---PASSED";
			}else{
				Status= "---FAILED";
			}
			WebDriverWrapper webDriverWrapper = new WebDriverWrapper(driver);
			FrameworkServices frameworkService = new FrameworkServices();
			frameworkService.logMessage(assertionError.getMessage());
			frameworkService.logMessage("<Br> <B>"+"["+"ScenarioID:::"+ScenarioID+Status+ "]" +"</B>"+ assertionError+"</Br>");
			String fileName = webDriverWrapper.captureScreenShot(snapshotFolder);
			frameworkService.sendEmailNotification(snapshotFolder + File.separator + fileName, fileName,
					assertionError.getMessage(), Reporter.getCurrentTestResult().getMethod().getMethodName(),
					Reporter.getOutput(Reporter.getCurrentTestResult()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception (assertionError);
		}
	}	

	public boolean assertCollectionsEqual(List<?> actualValues, List<?> expectedValues) {
		for (Object actualValue : actualValues) {
			boolean isObjectFound = false;
			for (Object expectedValue : expectedValues)
				try {
					if (expectedValue.equals(actualValue)) {
						isObjectFound = true;
					}
				} catch (AssertionError assertionError) {

				}
			if (!isObjectFound) {
				throw new AssertionError("; List" + expectedValues + " Does not contain: " + actualValue);
			}
		}

		for (Object expectedValue : expectedValues) {
			boolean isObjectFound = false;
			for (Object actualValue : actualValues)
				try {
					if (actualValue.equals(expectedValue)) {
						isObjectFound = true;
					}
				} catch (AssertionError assertionError) {

				}
			if (!isObjectFound) {
				throw new AssertionError("Actual List" + actualValues + " Does not contain: " + expectedValue);
			}
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public Date getCurrentSystemTime() {
		java.util.Date date = new java.util.Date();
		return new Date(date.getYear(), date.getMonth(), date.getDay());
	}

	public static <T> ArrayList<T> getObjectByPrimaryKey(List<T> list,
			String key) {
		String[] keys = key.split(",");
		ArrayList<T> filteredList = new ArrayList<T>();
		for (int i = 0; i < keys.length; i++) {
			for (T t : list) {
				if (((GenericEntity) t).getReference().equalsIgnoreCase(keys[i].trim())) {
					filteredList.add(t);
					break;
				}
			}
		}
		return filteredList;
	}

	//Added by PKP
	public static void IESystemSettings() throws InterruptedException {
		// IE Protected Mode 
		try {
			System.out.println("Executing ProtectedModeDisable.vbs");
			//File protectedModeDisableFile = new File("../Grid/ProtectedModeDisable.vbs");
			File protectedModeDisableFile = new File("D:\\NIATestAutomation\\Grid\\ProtectedModeDisable.vbs");
			Runtime.getRuntime().exec( "wscript "+ protectedModeDisableFile.getCanonicalPath());
			//System.out.println(protectedModeDisableFile.getCanonicalPath()); 

			//Registry Setting
			//Requires Admin rights
			/*System.out.println("Executing ie11_win32SettingFile.reg");
			   File ie11_win32SettingFile = new File("D:/NIATestAutomation/Grid/Grid/ie11_win32.reg");
			   String regFilePath = ie11_win32SettingFile.getCanonicalPath();
			   String[] cmd = {"regedit","/s",regFilePath};
			   Process p = Runtime.getRuntime().exec(cmd);
			   p.waitFor();*/
		}
		catch( IOException e ) {
			System.out.println(e);
			System.exit(0);
		}
	}
	public static void pageWait(int value) throws InterruptedException{
		try{
			Thread.sleep(value);
		}catch(Exception e){

		}

	}



}

