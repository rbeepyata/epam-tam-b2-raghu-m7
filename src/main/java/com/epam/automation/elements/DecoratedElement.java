package com.epam.automation.elements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Custom implementation of the WebElement, basically a wrapper on the webdriver actions.
 * @author Raghunandan_Beepyata
 *
 */
public class DecoratedElement implements WebElement{
	private static Logger logger = LogManager.getRootLogger();

	
	private static WebDriver driver;
	private By by;
	
	public DecoratedElement(By by) {
		this.by = by;
	}
	
	public static DecoratedElement getElement (By by) {
		return new DecoratedElement(by);
	}
	
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return driver.findElement(by).getScreenshotAs(target);
	}

	public void click() {
		logger.info("Clicking on the element located by [" + by.toString() + "]");
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			logger.error("Failed to click on the element located by [" + by.toString() + "]");
			e.printStackTrace();
		}
	}

	public void submit() {
		driver.findElement(by).submit();		
	}

	public void sendKeys(CharSequence... keysToSend) {
		driver.findElement(by).sendKeys(keysToSend);		
	}

	public void clear() {
		driver.findElement(by).clear();		
	}

	public String getTagName() {
		return driver.findElement(by).getTagName();
	}

	public String getAttribute(String name) {
		return driver.findElement(by).getAttribute(name);
	}

	public boolean isSelected() {
		return driver.findElement(by).isSelected();
	}

	public boolean isEnabled() {
		return driver.findElement(by).isEnabled();
	}

	public String getText() {
		return driver.findElement(by).getText();
	}

	public List<WebElement> findElements(By by) {
		return driver.findElement(this.by).findElements(by);
	}

	public WebElement findElement(By by) {
		return driver.findElement(this.by).findElement(by);
	}

	public boolean isDisplayed() {
		return driver.findElement(by).isDisplayed();
	}

	public Point getLocation() {
		return driver.findElement(by).getLocation();
	}

	public Dimension getSize() {
		return driver.findElement(by).getSize();
	}

	public Rectangle getRect() {
		return driver.findElement(by).getRect();
	}

	public String getCssValue(String propertyName) {
		return driver.findElement(by).getCssValue(propertyName);
	}


	public By getBy() {
		return by;
	}


	public void setBy(By by) {
		this.by = by;
	}

	public static void setDriver(WebDriver driver) {
		logger.debug("Setting WebDriver context to Decorated Element...");
		DecoratedElement.driver = driver;
	}
	

}
