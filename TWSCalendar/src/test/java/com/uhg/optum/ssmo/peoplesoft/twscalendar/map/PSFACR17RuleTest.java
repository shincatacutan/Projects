package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;

public class PSFACR17RuleTest extends JunitMock {

	@Before
	public void setUp() throws Exception {
		listExpectedDates();
		actualDates = new ArrayList<LocalDate>();
		PSFACR17Rule psfacro1rule = new PSFACR17Rule(2015,listHolidays());
		for(CalendarDay cal : psfacro1rule.getFinalDates()){
			if(!cal.isHoliday())
				actualDates.add(cal.getCalDay());
		}
	}

	@Test
	public void testGetFinalDates() {
		assertTrue(actualDates.containsAll(expectedDates));
		assertFalse(actualDates.containsAll(notExpectedDates));
	}

	@Override
	public void listExpectedDates() {
		expectedDates = new ArrayList<LocalDate>();
		expectedDates.add(new LocalDate(2015,1,21));
		expectedDates.add(new LocalDate(2015,1,22));
		expectedDates.add(new LocalDate(2015,1,7));
		
		notExpectedDates = new ArrayList<LocalDate>();
		notExpectedDates.add(new LocalDate(2015,1,8));
		for(int i =1;i<=12;i++){
			notExpectedDates.add(new LocalDate(2015,i,18));
			notExpectedDates.add(new LocalDate(2015,i,1));
		}
		notExpectedDates.add(new LocalDate(2015,12,25));
		notExpectedDates.add(new LocalDate(2015,6,20));

	}

}
