package com.epam.automation.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	private static Logger logger = LogManager.getLogger();

	public static void takeScreenshot(WebDriver driver, String screenshotFilePath) {
		logger.debug("Taking screenshot...");
		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourceFile, new File(screenshotFilePath));
		} catch (IOException e) {
			logger.error("Failed to take screenshot!");
			e.printStackTrace();
		}
	}

}
