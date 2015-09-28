package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR01Rule;

public class PSFACR01RuleTest extends JunitMock {

	@Before
	public void setUp() throws Exception {

		listExpectedDates();
		actualDates = new ArrayList<LocalDate>();
		PSFACR01Rule psfacro1rule = new PSFACR01Rule(2015,listHolidays());
		for(CalendarDay cal : psfacro1rule.getFinalDates()){
			if(!cal.isHoliday())
			actualDates.add(cal.getCalDay());
		}
	}

	@Test
	public void testGetFinalDates() {
		assertTrue("Actual Dates must have January 8 2015",actualDates.containsAll(expectedDates));
		assertTrue("Actual Dates total must be 12",actualDates.size()==12);
	}
	
	@Override
	public void listExpectedDates() {
		expectedDates = new ArrayList<LocalDate>();
		expectedDates.add(new LocalDate(2015,1,8));
	}


}
