package com.epam.automation.driver;


public enum BrowserType {
	Chrome,
	Firefox;
	
	public static BrowserType getBrowserType(String browserType){
		switch (browserType.toLowerCase()) {
		case "chrome":
			return Chrome;
		case "firefox":
		case "ff":
			return Firefox;
		default:
			return Chrome;
		}
	}
}
