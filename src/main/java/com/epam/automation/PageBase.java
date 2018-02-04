package com.epam.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.epam.automation.elements.DecoratedElement;

public abstract class PageBase {
	private static Logger logger = LogManager.getRootLogger();
	
	protected static WebDriver driver;
	
	public static void initBasePage(WebDriver driver) {
		logger.debug("Initializing BasePage with WebDriver context...");
		PageBase.driver = driver;
		DecoratedElement.setDriver(driver);
	}

}
