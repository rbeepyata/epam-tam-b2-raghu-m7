package com.epam.automation;

import com.epam.automation.driver.BrowserType;
import com.epam.automation.utilities.PropertyUtil;

public abstract class ConfigProperties {
	
	private static final PropertyUtil PROPERTIES_UTIL = new PropertyUtil(".\\src\\main\\resources\\config.properties");

	public static final BrowserType BROWSER =  BrowserType.getBrowserType(PROPERTIES_UTIL.getProperty("browser"));
    public static final String URL =  PROPERTIES_UTIL.getProperty("url");
    public static final String USERNAME = PROPERTIES_UTIL.getProperty("username");
    public static final String PASSWORD = PROPERTIES_UTIL.getProperty("password");
    public static final Long EXPLICIT_WAIT_TIME = Long.valueOf(PROPERTIES_UTIL.getProperty("explicitWaitTime"));
}
