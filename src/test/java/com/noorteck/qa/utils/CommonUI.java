package com.noorteck.qa.utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.github.bonigarcia.wdm.WebDriverManager;
import static com.noorteck.qa.setup.ObjInitialize.getInstance;

/**
 * The CommonUI class provides a set of common methods for interacting with web
 * pages using Selenium WebDriver. The class uses the EnvObjects singleton to
 * get the WebDriver instance and the ObjectMap for finding web elements.
 */
public class CommonUI {
	/**
	 * Initializes a new instance of a WebDriver with the specified browser and sets
	 * it as the driver for the current Selenium session.
	 *
	 * @param browser a string representing the browser to use (e.g. "chrome",
	 *                "firefox", "ie").
	 * @throws Exception if an invalid browser type is provided or if there is an
	 *                   error initializing the driver.
	 */
	public void openBrowser(String browser) throws Exception {
		try {
			WebDriver driver = createDriver(browser);
			getInstance().setDriver(driver);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error initializing browser: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Creates a new instance of the specified browser driver.
	 * 
	 * @param browser the name of the browser to create the driver for, e.g.
	 *                "chrome", "firefox", "ie"
	 * @return the new WebDriver instance
	 * @throws Exception if an invalid browser name is provided or if an error
	 *                   occurs while creating the driver
	 */
	private WebDriver createDriver(String browser) throws Exception {
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		case "edge":
			WebDriverManager.iedriver().setup();
			return new EdgeDriver();
		default:
			throw new Exception("Invalid browser type: " + browser);
		}
	}

	/**
	 * Waits for the specified element to be visible on the web page.
	 *
	 * @param objectName the name of the web element to wait for
	 * @return the WebElement object that was found
	 * @throws NoSuchElementException if the element is not found after a timeout or
	 *                                if the locator is invalid
	 */
	public WebElement waitForElement(String objectName) throws NoSuchElementException {
		try {
			By locator = getInstance().getObjMapInstance().getLocator(objectName);
			WebDriverWait wait = new WebDriverWait(getInstance().getDriver(), Constants.DEFAULT_WAIT_TIME);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw new NoSuchElementException(
					"Element " + objectName + " not found within " + Constants.DEFAULT_WAIT_TIME + " seconds");
		}
	}

	/**
	 * Navigates to the specified URL.
	 *
	 * @param url the URL to navigate to
	 */
	public void navigateTo(String url) {
		WebDriver driver = getInstance().getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
		driver.get(url);
	}

	/**
	 * Clicks the element with the specified object name.
	 *
	 * @param objectName the name of the object to click
	 */
	public void click(String objectName) {
		WebElement element = waitForElement(objectName);
		element.click();
	}

	/**
	 * Enters the specified text into the element with the specified object name.
	 *
	 * @param objectName the name of the object to enter text into
	 * @param text       the text to enter into the element
	 */
	public void enter(String objectName, String text) {
		WebElement element = waitForElement(objectName);
		element.clear();
		
		element.sendKeys(text);
	}

	/**
	 * Returns the text of the element with the specified object name.
	 *
	 * @param objectName the name of the object to retrieve the text from
	 * @return the text of the element with the specified object name
	 */
	public String getText(String objectName) {
		WebElement element = waitForElement(objectName);
		return element.getText();
	}

	/**
	 * Returns the value of the specified attribute of the element with the
	 * specified object name.
	 *
	 * @param objectName the name of the object to retrieve the attribute value from
	 * @param attribute  the name of the attribute to retrieve
	 * @return the value of the specified attribute of the element with the
	 *         specified object name
	 */
	public String getAttribute(String objectName, String attribute) {
		WebElement element = waitForElement(objectName);
		return element.getAttribute(attribute);
	}

	/**
	 * Returns the title of the current web page.
	 *
	 * @return the title of the current web page
	 */
	public String getTitle() {
		return getInstance().getDriver().getTitle();
	}

	/**
	 * Maximizes the current web page window.
	 */
	public void maximize() {
		getInstance().getDriver().manage().window().maximize();
	}

	/**
	 * Refreshes the current web page.
	 */
	public void refresh() {
		getInstance().getDriver().navigate().refresh();
	}

	/**
	 * Switches the focus of the WebDriver to the frame with the specified name or
	 * ID.
	 *
	 * @param frameNameOrId the name or ID of the frame to switch to
	 * @throws NoSuchFrameException if the specified frame does not exist
	 */
	public void switchToFrame(String frameNameOrId) throws NoSuchFrameException {
		try {
			getInstance().getDriver().switchTo().frame(frameNameOrId);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebDriverException("Error switching to frame: " + e.getMessage());
		}
	}

	/**
	 * Switches the focus of the WebDriver to the specified frame element.
	 *
	 * @param frameElement the frame element to switch to
	 * @throws NoSuchFrameException if the specified frame does not exist
	 */
	public void switchToFrame(WebElement frameElement) throws NoSuchFrameException {
		try {
			getInstance().getDriver().switchTo().frame(frameElement);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebDriverException("Error switching to frame: " + e.getMessage());
		}
	}

	/**
	 * Switches the focus of the WebDriver to the frame at the specified index.
	 *
	 * @param frameIndex the index of the frame to switch to
	 * @throws NoSuchFrameException if the specified frame does not exist
	 */
	public void switchToFrame(int frameIndex) throws NoSuchFrameException {
		try {
			getInstance().getDriver().switchTo().frame(frameIndex);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebDriverException("Error switching to frame: " + e.getMessage());
		}
	}

	/**
	 * Selects an option in a dropdown by either its visible text, value or index.
	 *
	 * @param objectName the name of the dropdown element to select an option from
	 * @param value      the text, value or index of the option to select
	 * @param method     the selection method (text, value, or index)
	 * @throws Exception if an error occurs while selecting the dropdown option
	 */
	public void selectDropdownOption(String objectName, String value, String method) throws Exception   {
		try {
			WebElement element = waitForElement(objectName);
			Select select = new Select(element);

			switch (method.toLowerCase()) {
			case "text":
				select.selectByVisibleText(value);
				break;
			case "value":
				select.selectByValue(value);
				break;
			case "index":
				select.selectByIndex(Integer.parseInt(value));
				break;
			default:
				throw new Exception("Invalid selection method: " + method);
			}
		} catch (Exception e) {
			throw new Exception("Error selecting dropdown option for: " + objectName, e);
		}
	}

	/**
	 * Retrieves all available options in a dropdown.
	 *
	 * @param objectName the name of the dropdown element to retrieve options from
	 * @return a List of WebElements representing the options in the dropdown
	 * @throws Exception if an error occurs while getting the dropdown options
	 */
	public List<WebElement> getDropdownOptions(String objectName) throws Exception {
		try {
			WebElement dropdown = waitForElement(objectName);
			Select select = new Select(dropdown);
			return select.getOptions();
		} catch (Exception e) {
			throw new Exception("Error getting dropdown options for: " + objectName, e);
		}
	}

	/**
	 * Handles the alert based on the specified action.
	 * 
	 * @param action The action to perform on the alert. Accept, dismiss, get text,
	 *               or send keys.
	 */
	public void handleAlert(String action) {
		Alert alert = null;
		try {
			alert = getInstance().getDriver().switchTo().alert();
			switch (action.toLowerCase()) {
			case "accept":
				alert.accept();
				System.out.println("Alert accepted.");
				break;
			case "dismiss":
				alert.dismiss();
				System.out.println("Alert dismissed.");
				break;
			case "gettext":
				String alertText = alert.getText();
				System.out.println("Alert text: " + alertText);
				break;
			case "sendkeys":
				alert.sendKeys("example text");
				System.out.println("Text sent to alert.");
				break;
			default:
				System.out.println("Invalid action specified: " + action);
			}
		} catch (NoAlertPresentException e) {
			System.err.println("Error: No alert present. " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error handling alert: " + e.getMessage());
		}
	}

	/**
	 * Checks if an element is displayed on the page.
	 * 
	 * @param objectName the name of the element to check
	 * @return true if the element is displayed, false otherwise
	 */
	public boolean isElementDisplayed(String objectName) {

		try {
			WebElement element = waitForElement(objectName);
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	/**
	 * Checks if an element is enabled on the page.
	 * 
	 * @param objectName the name of the element to check
	 * @return true if the element is enabled, false otherwise
	 */
	public boolean isElementEnabled(String objectName) {

		WebElement element = waitForElement(objectName);
		return element.isEnabled();

	}

	/**
	 * Checks if an element is selected on the page.
	 * 
	 * @param objectName the name of the element to check
	 * @return true if the element is selected, false otherwise
	 */
	public boolean isElementSelected(String objectName) {
		WebElement element = waitForElement(objectName);
		return element.isSelected();
	}

	/**
	 * Quits the current WebDriver instance and closes all associated windows.
	 */
	public void quitBrowser() {
		try {
			Thread.sleep(3000);
			if (getInstance().getDriver() != null) {
				getInstance().getDriver().quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error while quitting browser: " + e.getMessage());
		}
	}

	/**
	 * Closes the current browser window and all associated tabs.
	 */
	public void closeBrowser() {
		try {
			if (getInstance().getDriver() != null) {
				getInstance().getDriver().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error while closing browser: " + e.getMessage());
		}
	}

	/**
	 * Drags and drops the source element onto the target element on the web page.
	 *
	 * @param sourceObjectName the name of the source web element to drag
	 * @param targetObjectName the name of the target web element to drop onto
	 */
	public void dragAndDrop(String sourceObjectName, String targetObjectName) {

		WebElement sourceElement = waitForElement(sourceObjectName);
		WebElement targetElement = waitForElement(targetObjectName);
		Actions actions = new Actions(getInstance().getDriver());
		actions.dragAndDrop(sourceElement, targetElement).perform();

	}

	/**
	 * Double-clicks the specified element on the web page.
	 *
	 * @param objectName the name of the web element to double-click
	 */
	public void doubleClick(String objectName) {

		WebElement element = waitForElement(objectName);
		Actions actions = new Actions(getInstance().getDriver());
		actions.doubleClick(element).perform();

	}

	/**
	 * Uploads the specified file to the specified web element on the web page using
	 * the Robot class.
	 *
	 * @param objectName the name of the web element to upload the file to
	 * @param filePath   the path of the file to upload
	 * @throws Exception if an error occurs while uploading the file
	 */
	public void uploadFileUsingRobot(String objectName, String filePath) throws Exception {
		try {
			WebElement element = waitForElement(objectName);
			element.click();

			// Copy the file path to the clipboard
			StringSelection stringSelection = new StringSelection(filePath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);

			// Use the Robot class to perform a paste operation
			Robot robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			throw new Exception("Error uploading file to element: " + objectName, e);
		}
	}

	/**
	 * Scrolls to the specified web element on the web page.
	 *
	 * @param objectName the name of the web element to scroll to
	 */
	public void scrollToElement(String objectName) {

		WebElement element = waitForElement(objectName);
		JavascriptExecutor js = (JavascriptExecutor) getInstance().getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	/**
	 * Scrolls the web page by the specified amount.
	 *
	 * @param x the horizontal scroll amount
	 * @param y the vertical scroll amount
	 */
	public void scroll(int x, int y) throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) getInstance().getDriver();
		js.executeScript("window.s crollBy(" + x + ", " + y + ")");

	}
	
	
	public void takeScreenshot() {
		try {
			TakesScreenshot screenshot = (TakesScreenshot) getInstance().getDriver();
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			String screenshotPath = "src/screenshots/" + System.currentTimeMillis() + ".png";

			FileUtils.copyFile(srcFile, new File(screenshotPath));
			System.out.println("Screenshot saved to: " + screenshotPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	



}
