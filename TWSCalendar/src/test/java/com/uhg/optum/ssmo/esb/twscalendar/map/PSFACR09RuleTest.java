package com.uhg.optum.ssmo.esb.twscalendar.map;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import com.uhg.optum.ssmo.esb.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft.PSFACR09Rule;

public class PSFACR09RuleTest extends JunitMock {

	@Before
	public void setUp() throws Exception {
		listExpectedDates();
		actualDates = new ArrayList<LocalDate>();
		PSFACR09Rule psfacro1rule = new PSFACR09Rule(2015, listHolidays());
		for (CalendarDay cal : psfacro1rule.getFinalDates()) {
			if (!cal.isHoliday())
				actualDates.add(cal.getCalDay());
		}
	}

	@Test
	public void testGetFinalDates() {
		assertTrue(
				"Actual Dates must have January 23,27 2015 ; September 23,25 2015",
				actualDates.containsAll(expectedDates));
		System.out.println(actualDates.size());
		assertTrue("Actual Dates total must be 24",
				actualDates.size() == listSize);
		
	}

	@Override
	public void listExpectedDates() {
		expectedDates = new ArrayList<LocalDate>();
		expectedDates.add(new LocalDate(2015, 1, 27));
		expectedDates.add(new LocalDate(2015, 2, 24));
		expectedDates.add(new LocalDate(2015, 9, 25));
		listSize = 13;

	}

}
