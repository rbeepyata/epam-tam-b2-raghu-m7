package com.epam.automation.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.automation.utilities.ScreenshotUtil;
import com.epam.automation.utilities.TextUtil;

public class ScreenshotOnFailListener implements ITestListener{
	private static Logger logger = LogManager.getLogger();

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String screenshotName = "screenshot" + TextUtil.getCurrentDateTimeStamp("-") + ".jpg"; 
		String screenshotFilePath = ".//target//screenshots//" + screenshotName;
		ScreenshotUtil.takeScreenshot(Driver.getInstance(), screenshotFilePath);
		logger.debug("Screenshot " + screenshotName + "saved.");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

}
