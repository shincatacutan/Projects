package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;

public abstract class JunitMock {

	public List<LocalDate> actualDates;
	public List<LocalDate> expectedDates;
	public int listSize;
	private Set<Holiday> holidays = new HashSet<Holiday>();
	
	public Set<Holiday> listHolidays(){
		
		holidays.add(new Holiday("New Year's Day", new LocalDate(2015, 1, 1)));
		holidays.add(new Holiday("Birthday of Martin Luther King, Jr.", new LocalDate(2015, 1, 19)));
		holidays.add(new Holiday("Memorial Day", new LocalDate(2015, 5, 25)));
		holidays.add(new Holiday("Independence Day", new LocalDate(2015, 7, 3)));
		holidays.add(new Holiday("Labor Day", new LocalDate(2015, 9, 7)));
		holidays.add(new Holiday("Thanksgiving Day", new LocalDate(2015, 11, 26)));
		holidays.add(new Holiday("Day after Thanksgiving ", new LocalDate(2015, 11, 27)));
		holidays.add(new Holiday("Christmas Day", new LocalDate(2015, 12, 25)));
		
		return holidays;
	}
	
	public abstract void listExpectedDates();

}
