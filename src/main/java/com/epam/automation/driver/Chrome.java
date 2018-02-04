package com.epam.automation.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome extends AbstractDriver{
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public WebDriver initialize() {
		logger.debug("Setting system property for chrome driver to " + "lib//driver//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "lib//driver//chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
}
