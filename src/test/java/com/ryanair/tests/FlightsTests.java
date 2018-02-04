package com.ryanair.tests;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.epam.automation.ConfigProperties;
import com.epam.automation.TestBase;
import com.epam.automation.driver.ScreenshotOnFailListener;
import com.ryanair.businessobjects.flights.FlightSchedule;
import com.ryanair.businessobjects.flights.FlightTimeSchedule;
import com.ryanair.pages.HomePage;
import com.ryanair.pages.SearchResultPage;

@Listeners (ScreenshotOnFailListener.class)
public class FlightsTests extends TestBase {
	private static Logger logger = LogManager.getLogger();

	@Test
	public void testFlights() {
		logger.info("Starting Flights test...");

		HomePage homePage = new HomePage();
		
		homePage.open(ConfigProperties.URL);
		Assert.assertEquals(driver.getTitle(),
				"Official Ryanair website | Book direct for the lowest fares | Ryanair.com");
		
		FlightSchedule schedule = new FlightSchedule.Builder()
				.fromCountry(" Belgium").fromAirport("Brussels Charleroi")
				.toCountry(" United Kingdom").toAirport("Manchester").build();
		
		homePage.selectFromCountry(schedule.getFromCountry())
				.selectFromAirport(schedule.getFromAirport());
		Assert.assertEquals(homePage.getSelectedFromValue(), schedule.getFromAirport());

		homePage.selectToCountry(schedule.getToCountry())
				.selectToAirport(schedule.getToAirport());
		Assert.assertEquals(homePage.getSelectedToValue(), schedule.getToAirport());

		FlightTimeSchedule timeSchedule = new FlightTimeSchedule(LocalDateTime.now());
		homePage.selectFromCalendar(timeSchedule.getStartDate(), timeSchedule.getStartMonth())
				.selectFromCalendar(timeSchedule.getToDate(), timeSchedule.getToMonth())
				.addTeen();
		SearchResultPage searchResultPage = homePage.search();
		Assert.assertTrue(searchResultPage.isFlightDisplayed());
		
		String startResultDate = searchResultPage.startResultDateText().toLowerCase();
		Assert.assertTrue(
				startResultDate.toLowerCase().contains((timeSchedule.getStartDate() + " " + timeSchedule.getStartMonth().substring(0, 3)).toLowerCase()));

		String endResultDate = searchResultPage.endResultDateText().toLowerCase();
		Assert.assertTrue(endResultDate.toLowerCase().contains((timeSchedule.getToDate() + " " + timeSchedule.getToMonth().substring(0, 3)).toLowerCase()));
		
		searchResultPage.printFlightDetails();
		
		logger.info("Failing the test for demo purpose");
		Assert.fail();
		logger.info("Test Completed...");
	}

	
}
