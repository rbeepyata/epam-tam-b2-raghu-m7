package com.epam.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.automation.driver.Driver;

public class TestBase {
	private static Logger logger = LogManager.getRootLogger();
	
	protected WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		logger.info("Launching the "+ConfigProperties.BROWSER.toString() +" browser...");
		driver = Driver.getInstance();
		PageBase.initBasePage(driver);
		logger.info("Maximizing the browser...");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
