package com.epam.automation.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox extends AbstractDriver{
	private static Logger logger = LogManager.getLogger();

	@Override
	public WebDriver initialize() {
		logger.debug("Setting system property for chrome driver to " + "lib//driver//geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", "lib//driver//geckodriver.exe");
		driver = new FirefoxDriver();
		return driver;
	}
}
