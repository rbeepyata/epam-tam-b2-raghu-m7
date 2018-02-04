package com.ryanair.businessobjects.flights;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class FlightTimeSchedule {

	private String startDate;
	private String toDate;
	private String startMonth;
	private String toMonth;

	private void generateTimeSchedule(LocalDateTime now) {

		LocalDate startingDate = LocalDate.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		long start = startingDate.toEpochDay();
		LocalDate endingDate = LocalDate.of(now.getYear(), now.getMonthValue() + 7, now.getDayOfMonth());
		long end = endingDate.toEpochDay();
		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		long randomEpochDay2 = ThreadLocalRandom.current().longs(randomEpochDay, end).findAny().getAsLong();
		startDate = LocalDate.ofEpochDay(randomEpochDay).getDayOfMonth() + "";
		toDate = LocalDate.ofEpochDay(randomEpochDay2).getDayOfMonth() + "";
		startMonth = LocalDate.ofEpochDay(randomEpochDay).getMonth().name();
		toMonth = LocalDate.ofEpochDay(randomEpochDay2).getMonth().name();

	}

	public FlightTimeSchedule(LocalDateTime now) {
		generateTimeSchedule(now);
	}

	public String getStartDate() {
		return startDate;
	}

	public String getToDate() {
		return toDate;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public String getToMonth() {
		return toMonth;
	}

}
