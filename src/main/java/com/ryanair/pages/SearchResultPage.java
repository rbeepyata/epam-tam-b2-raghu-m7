package com.ryanair.pages;

import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.automation.ConfigProperties;

public class SearchResultPage {
	private static Logger logger = LogManager.getLogger();

	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "(//div[@class='slide active']/div/div[@class='date'])[1]")
	private WebElement startResultDateElement;

	@FindBy(how = How.XPATH, using = "(//div[@class='slide active']/div/div[@class='date'])[2]")
	private WebElement endResultDateElement;

	@FindBy(how = How.XPATH, using = "(//flights-table)[1]//flights-table-header")
	private List<WebElement> flights;

	@FindBy(how = How.XPATH, using = "(//flights-table)[1]//flights-table-header//div[@class='start-time']")
	private List<WebElement> departure_StartTime;
	
	@FindBy(how = How.XPATH, using = "(//flights-table)[1]//flights-table-header//div[@class='end-time']")
	private List<WebElement> departure_EndTime;
	
	@FindBy(how = How.XPATH, using = "(//flights-table)[1]//flights-table-header//div[@class='meta-row flight-number-wrapper']//span[@class='flight-number']")
	private List<WebElement> departure_FlightNumber;
	
	@FindBy(how = How.XPATH, using = "(//flights-table)[1]//flights-table-header//strong")
	private List<WebElement> departure_FlightTime;

	@FindBy(how = How.XPATH, using = "(//flights-table)[2]//flights-table-header")
	private List<WebElement> returnFlights;

	@FindBy(how = How.XPATH, using = "(//flights-table)[2]//flights-table-header//div[@class='start-time']")
	private List<WebElement> arriving_StartTime;
	
	@FindBy(how = How.XPATH, using = "(//flights-table)[2]//flights-table-header//div[@class='end-time']")
	private List<WebElement> arriving_EndTime;
	
	@FindBy(how = How.XPATH, using = "(//flights-table)[2]//flights-table-header//div[@class='meta-row flight-number-wrapper']//span[@class='flight-number']")
	private List<WebElement> arriving_FlightNumber;
	
	@FindBy(how = How.XPATH, using = "(//flights-table)[2]//flights-table-header//strong")
	private List<WebElement> arriving_FlightTime;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isFlightDisplayed() {
		logger.info("Verify if search results page for flights is displayed...");
		boolean flightDisplayed = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return d.findElement(By.xpath("((//flights-table)[1]//flights-table-header)[1]")).isDisplayed();
			}
		});

		return flightDisplayed;
	}

	public String startResultDateText() {
		startResultDateElement = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath("(//div[@class='slide active']/div/div[@class='date'])[1]"));
			}
		});
		return startResultDateElement.getText();
	}

	public String endResultDateText() {
		endResultDateElement = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//div[@class='slide active']/div/div[@class='date'])[2]")));

		return endResultDateElement.getText();
	}

	public SearchResultPage printFlightDetails() {
		logger.info("Printing Search details...");
		if (flights.size() >= 1) {
			ListIterator<WebElement> itr1 = departure_StartTime.listIterator();
			ListIterator<WebElement> itr2 = departure_EndTime.listIterator();
			ListIterator<WebElement> itr3 = departure_FlightNumber.listIterator();
			ListIterator<WebElement> itr4 = departure_FlightTime.listIterator();

			logger.info("\nTo Flight");
			while (itr1.hasNext()) {
				logger.info("\nFlight Number:" + itr3.next().getText());
				logger.info("\tStart Time:" + itr1.next().getText());
				logger.info("\tEnd Time:" + itr2.next().getText());
				logger.info("\tFlightTime:" + itr4.next().getText());
			}

		} else {
			logger.info("No To Flights Available");
		}
		if (returnFlights.size() >= 1) {
			ListIterator<WebElement> itr1 = arriving_StartTime.listIterator();
			ListIterator<WebElement> itr2 = arriving_EndTime.listIterator();
			ListIterator<WebElement> itr3 = arriving_FlightNumber.listIterator();
			ListIterator<WebElement> itr4 = arriving_FlightTime.listIterator();

			logger.info("\nReturn Flight");
			while (itr1.hasNext()) {
				logger.info("\nFlight Number:" + itr3.next().getText());
				logger.info("\tStart Time:" + itr1.next().getText());
				logger.info("\tEnd Time:" + itr2.next().getText());
				logger.info("\tFlightTime:" + itr4.next().getText());

			}

		} else {
			logger.info("No Return Flights Available");
		}
		return this;
	}

}
