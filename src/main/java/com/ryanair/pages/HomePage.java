package com.ryanair.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.automation.ConfigProperties;
import com.epam.automation.PageBase;
import com.epam.automation.elements.DecoratedElement;
import com.google.common.base.Function;

public class HomePage extends PageBase {
	private static Logger logger = LogManager.getLogger();

	//Custom approach in building the page elements for the page object
	private WebElement fromElement = DecoratedElement.getElement(By.xpath("(//*[@name='departureAirportName'])[1]"));

	private WebElement toElement = DecoratedElement.getElement(By.xpath("(//*[@name='destinationAirportName'])[1]"));

	private WebElement addPassengersDropdown = DecoratedElement.getElement(By.xpath("//div[@class='dropdown-handle']"));

	private WebElement teenPlusButton = DecoratedElement.getElement(By.xpath("(//*[@icon-id='glyphs.plus-circle'])[2]/.."));

	private WebElement letsGoButton = DecoratedElement.getElement(By.xpath("//button/span[text()=\"Let's go! \"]"));

	public HomePage open(String url) {
		logger.info("Lauching Ryanair home page...");
		driver.get(url);
		return this;
	}

	public HomePage selectFromCountry(final String Country) {
		logger.info("Selecting From Country..." + Country);
		fromElement.click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		WebElement fromCountry;
		try {
			fromCountry = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("//*[text()='" + Country + "']"));
				}
			});
			fromCountry.click();
		} catch (Exception e) {
			logger.error("Failed to select From Country!");
			e.printStackTrace();
		}

		return this;

	}

	public HomePage selectFromAirport(final String Airport) {
		logger.info("Selecting From Airport..." + Airport);
		WebElement fromAirport;
		try {
			fromAirport = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("//*[text()='" + Airport + "']"));
				}
			});
			fromAirport.click();
		} catch (Exception e) {
			logger.error("Failed to select From Airport!");
			e.printStackTrace();
		}
		return this;
	}

	public String getSelectedFromValue() {
		return fromElement.getAttribute("value");
	}

	public HomePage selectToCountry(final String Country) {
		logger.info("Selecting To Country:" + Country);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		try {
			WebElement toCountry = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("//*[text()='" + Country + "']"));
				}
			});
			toCountry.click();
		} catch (Exception e) {
			logger.error("Failed to select To Country!");
			e.printStackTrace();
		}
		return this;

	}

	public HomePage selectToAirport(final String Airport) {
		logger.info("Selecting To Airport:" + Airport);
		try {
			WebElement toAirport  = (new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("//*[text()='" + Airport + "']"));
				}
			});
			toAirport.click();
		} catch (Exception e) {
			logger.error("Failed to select To Airport!");
			e.printStackTrace();
		}
		return this;

	}

	public String getSelectedToValue() {
		try {
			new WebDriverWait(driver, ConfigProperties.EXPLICIT_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(toElement, "Manchester"));
			return toElement.getAttribute("value");
		} catch (Exception e) {
			logger.error("Failed to retrieve selected To Value!");
			throw e;
		}
	}

	public HomePage selectFromCalendar(String StartDate, String startMonth) {

		logger.info("Selecting... " + StartDate + "-" + startMonth);
		int i = 1;
		try {
			while (i < 11) {
				WebElement Month = driver.findElement(By.xpath("(//h1[@class='month-name'])[" + i + "]"));
				if (Month.getText().toLowerCase().contains(startMonth.toLowerCase())) {
					WebElement Calendar = driver.findElement(By.xpath("(//core-datepicker//ul[@class='days'])[" + i + "]"));
					List<WebElement> Days = Calendar
							.findElements(By.xpath("(//core-datepicker//ul[@class='days'])[" + i + "]/li"));
					for (WebElement day : Days) {
						String str = day.getAttribute("data-id").substring(0, 2);
						if ((str.substring(0, 1).equals("0") ? str.substring(1) : str).equals(StartDate)) {
							day.click();
							return this;
						}
					}
				} else {
					driver.findElement(By.xpath("//button[@class='arrow right']")).click();
					i++;
				}
			}
		} catch (Exception e) {
			logger.error("Failed to select From Calendar!");
			e.printStackTrace();
		}

		return this;
	}

	public HomePage addTeen() {
		addPassengersDropdown.click();
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(ConfigProperties.EXPLICIT_WAIT_TIME, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(WebDriverException.class);
			teenPlusButton =  wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath("(//*[@icon-id='glyphs.plus-circle'])[2]/.."));
				}
			});
			teenPlusButton.click();
		} catch (Exception e) {
			logger.error("Failed to add teen!");
			e.printStackTrace();
		}
		return this;
	}

	public SearchResultPage search() {
		letsGoButton.click();
		return new SearchResultPage(driver);
	}

}