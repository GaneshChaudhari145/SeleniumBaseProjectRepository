package com.aqm.staf.framework.core;

import com.aqm.staf.framework.FrameworkServices;
import com.google.common.base.Predicate;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class WebDriverWrapper {
	//static Logger logger = Logger.getLogger(WebDriverWrapper.class.getName());
	WebDriver driver;
	static int counter=0;
	public WebDriverWrapper(WebDriver driver) {
		this.driver = driver;
	}

	public static String getUniqueValue() {
		// get current date time with Date() to create unique file name
		char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		Random r = new Random(System.currentTimeMillis());
		char[] id = new char[9];
		for (int i = 0; i < 9; i++) {
			id[i] = chars[r.nextInt(chars.length)];
		}
		return new String(id);
	}

	public String getCurrentBrowserName() {
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		return cp.getBrowserName();
	}

	public static String getCurrentBrowserName(WebDriver driver) {
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		return cp.getBrowserName();
	}

	//Old
	/*public String captureScreenShot(File destinationFilePathLocation) throws IOException {
		String fileName = new String();

		WebDriver augmentedDriver = driver;
		if (!(driver instanceof InternetExplorerDriver))
			augmentedDriver = new Augmenter().augment(driver);

		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		Date date = new Date();
		fileName = driver.getTitle().replace(":", "").replace("-", "") + dateFormat.format(date) + ".png";
		File destinationFilePath = new File((destinationFilePathLocation + File.separator + fileName));
		FileUtils.copyFile(screenshot, destinationFilePath);
		
		// Added by PKP on 20-10-2015
//		String strImg = "<img src = \"" + destinationFilePath + "\">" ;  
//		Reporter.log(strImg);
		
		return fileName;
	}*/

	public String captureScreenShot(File destinationFilePathLocation) throws HeadlessException, AWTException, IOException{
		String fileName = new String();
		//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		WebDriver augmentedDriver = driver;
		if (!(driver instanceof InternetExplorerDriver))
			augmentedDriver = new Augmenter().augment(driver);

		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		Date date = new Date();
		fileName = driver.getTitle().replace(":", "").replace("-", "") + dateFormat.format(date) + ".png";
		File destinationFilePath = new File((destinationFilePathLocation + File.separator + fileName));
		FileUtils.copyFile(screenshot, destinationFilePath);
		//ImageIO.write(image, "png", destinationFilePath);

		Reporter.log("<br> <a target = \"_blank\" href=\"" + destinationFilePath +"\">"+
				"<img src=\""
				+ destinationFilePath 
				+ "\" alt=\"ScreenShot Not Available\" height=\"400\" width=\"400\"> </a>");
		return fileName;
	}
	
	public String captureAssertionScreenShot(File destinationFilePathLocation,String error) throws HeadlessException, AWTException, IOException{
		String fileName = new String();
		//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		WebDriver augmentedDriver = driver;
		if (!(driver instanceof InternetExplorerDriver))
			augmentedDriver = new Augmenter().augment(driver);

		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		Reporter.log("<B>"+"TestCase Failed Due to:::"+error+"</B>");
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		Date date = new Date();
		fileName = driver.getTitle().replace(":", "").replace("-", "") + dateFormat.format(date) + ".png";
		File destinationFilePath = new File((destinationFilePathLocation + File.separator + fileName));
		FileUtils.copyFile(screenshot, destinationFilePath);
		//ImageIO.write(image, "png", destinationFilePath);

		Reporter.log("<br> <a target = \"_blank\" href=\"" + destinationFilePath +"\">"+
				"<img src=\""
				+ destinationFilePath 
				+ "\" alt=\"ScreenShot Not Available\" height=\"400\" width=\"400\"> </a>");
		return fileName;
	}
	
	public String getUniqueTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public Capabilities getCurrentCapability() {
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		return cp;
	}

	@Deprecated
	public void sendEnterKey(WebElement element, Keys key) {
		if (getCurrentBrowserName().equalsIgnoreCase("internet explorer")) {
			element.sendKeys(key);
		} else {
			element.click();
		}
	}

	@Deprecated
	public void clearAndSendKeysInTextBox(WebElement textbox, String value) {
		try {
			textbox.clear();
			textbox.sendKeys(value);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(
					"Could not clear and type the value in textbox");
		}
	}

	@Deprecated
	public void moveToElement(WebElement element) {
		Actions moveToElementAction = new Actions(driver);
		moveToElementAction.moveToElement(element).perform();
	}

	public WebElement waitForElementToBeDisplayed(final WebElement element, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					if (element.isDisplayed())
						return element;
					else
						return null;
				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public WebElement waitForElementToBeDisplayed(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isDisplayed())
						return element;
					else
						return null;
				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public WebElement waitForElementToBeClickable(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isEnabled() && element.isDisplayed())
						return element;
					else
						return null;
				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public WebElement waitForElementToBeClickable(final WebElement element, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					if (element.isEnabled() && element.isDisplayed())
						return element;
					else
						return null;
				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public WebElement waitForElementToBeEnabled(final WebElement element, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					if (element.isEnabled())
						return element;
					else
						return null;
				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public WebElement waitForElementToBeEnabled(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isEnabled())
						return element;
					else
						return null;
				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public WebElement waitForOptionToBePopulatedInList(final WebElement dropdownList, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					List<WebElement> options = dropdownList.findElements(By
							.tagName("option"));
					if (options.size() > 1) {
						return dropdownList;
					} 
					else
						return null;

				} 
				catch (NoSuchElementException ex) {
					return null;
				} 
				catch (StaleElementReferenceException ex) {
					return null;
				} 
				catch (NullPointerException ex) {
					return null;
				}
			}
		});
	}

	public void waitForElementToDisapper(final By by, int timeOutPeriod) {
		FluentWait<By> fluentWait = new FluentWait<By>(by);
		fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		fluentWait.withTimeout(20, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					return !driver.findElement(by).isDisplayed();

				} 
				catch (NoSuchElementException ex) {
					return true;
				} 
				catch (StaleElementReferenceException ex) {
					return true;
				}
			}
		});
	}

	public void bringElementInView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void waitForElementToDisapper(final WebElement element, int timeOutPeriod) {
		FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(element);
		fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		fluentWait.withTimeout(timeOutPeriod, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<WebElement>() {
			public boolean apply(WebElement element) {
				try {
					return !element.isDisplayed();
				} 
				catch (NoSuchElementException ex) {
					return true;
				} 
				catch (StaleElementReferenceException ex) {
					return true;
				}
			}
		});
	}

	public void waitForAlert(int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class).pollingEvery(10, TimeUnit.MILLISECONDS).until(ExpectedConditions.alertIsPresent());
	}

	public String acceptAlert(int timeOutPeriod) {
		waitForAlert(timeOutPeriod);
		Alert alert = driver.switchTo().alert();
		String AlertMessage = alert.getText();
		FrameworkServices frameworkServices = new FrameworkServices();
		frameworkServices.logMessage(AlertMessage);
		//frameworkServices.logMessage(AlertMessage, logger);
		alert.accept();
		return AlertMessage;
	}

	public String dismissAlert(int timeOutPeriod) {
		waitForAlert(timeOutPeriod);
		Alert alert = driver.switchTo().alert();
		String AlertMessage = alert.getText();
		FrameworkServices frameworkServices = new FrameworkServices();
		frameworkServices.logMessage(AlertMessage);
		//frameworkServices.logMessage(AlertMessage, logger);
		alert.dismiss();
		return alert.getText();
	}

	public void acceptAlertForFirefox() {
		if (getCurrentBrowserName().equalsIgnoreCase("firefox")) {
			try {
				waitForAlert(20);
				driver.switchTo().alert().accept();
			} 
			catch (Exception e) {
			}
		}
	}

	@Deprecated
	public void selectVisibleElementFromDropdown(WebElement element, String text) {
		Select selectType = new Select(element);
		selectType.selectByVisibleText(text);
	}

	@Deprecated
	public void selectByIndexFromDropdown(WebElement element, String index) {
		Select selectType = new Select(element);
		selectType.selectByIndex(Integer.parseInt(index));
	}

	public static void explicitWait(int waitTime) {
		try {
			Thread.sleep(waitTime * 1000);
		} 
		catch (InterruptedException e) {
		}
	}

	@Deprecated
	public String getSelectedValueFromList(WebElement webelement) {
		Select selectType = new Select(webelement);
		return selectType.getFirstSelectedOption().getText();
	}

	@Deprecated
	public ArrayList<String> getAllOptionsInList(WebElement webList) {
		Select selectList = new Select(webList);
		ArrayList<String> optionList = new ArrayList<String>();
		for (WebElement option : selectList.getOptions()) {
			optionList.add(option.getText().trim());
		}
		return optionList;
	}
}
