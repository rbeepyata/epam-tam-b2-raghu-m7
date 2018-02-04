package com.epam.automation.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.epam.automation.ConfigProperties;

/**
 * Singleton
 * @author Raghunandan_Beepyata
 *
 */
public class Driver {

    private static WebDriver driver;
    private static Logger logger = LogManager.getLogger();

    private Driver() {}

    public static WebDriver getInstance() {
        if (null == driver) {
            driver = DriverFactory.getDriver(ConfigProperties.BROWSER);
            logger.debug("Driver Singleton initialized.");
        }
        return driver;
    }
}
