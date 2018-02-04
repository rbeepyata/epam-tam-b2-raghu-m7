package com.epam.automation.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
	private static Logger logger = LogManager.getLogger();
	
	public static WebDriver getDriver(BrowserType browser){
		logger.debug("Initializing driver for " + browser.toString() + "...");
		switch(browser){
		case Chrome:
			return new Chrome().initialize();
		case Firefox:
			return new Firefox().initialize();
		default:
			return new Chrome().initialize();
		}
	}
}
