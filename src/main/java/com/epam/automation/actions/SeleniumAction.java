package com.epam.automation.actions;

import org.openqa.selenium.WebElement;

public class SeleniumAction implements ActionStrategy{
	
	WebElement element;
	
	public SeleniumAction(WebElement element) {
		this.element = element;
	}

	public void click() {
		element.click();
		
	}


}
