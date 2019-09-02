package edu.wsu.everett.cpts422;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

class DateTest {

	void test() {
		// fail("Not yet implemented");
	}

	@Test
	public void testIsLeap() {
		int thisYear = 2001;
		assertFalse(Date.isLeap(thisYear));

		thisYear = 2004;
		assertTrue(Date.isLeap(thisYear));

		thisYear = 2000;
		assertTrue(Date.isLeap(thisYear));

		thisYear = 1900;
		assertFalse(Date.isLeap(thisYear));
	}

	@Test
	public void testIsLastDayOfMonth() {
		Date date = new Date(31, 1, 2000);

		// Long month
		assertEquals(date.lastDayOfMonth(01, 2000), 31);

		// Leap year, February
		assertEquals(date.lastDayOfMonth(2, 2000), 29);

		// Not Leap year, February
		assertEquals(date.lastDayOfMonth(2, 1999), 28);

		// November
		assertEquals(date.lastDayOfMonth(11, 1999), 29);
	}

	@Test
	public void testDateToDayNumber() {
		Date date = new Date(1, 1, 2000);

		assertEquals(date.dateToDayNumber(15, 1, 2000), 15);
		assertEquals(date.dateToDayNumber(1, 1, 2000), 1);
	}

	@Test
	public void testDateToDayName() {
		Date date = new Date(1, 1, 2000);
		// January 1, 2019 is a Tuesday
		assertTrue(date.dateToDayName(1, 1, 2019).equalsIgnoreCase("Tuesday"));
		
		// Dr Zeng says this is accurate
		assertTrue(date.dateToDayName(1, 1, 2017).equalsIgnoreCase("Sunday"));
	}

//	@Test
//	public void testValidRangeForMonth1()
//	{
//		int m = 8;
//		assertTrue(Date.validRangeForMonth(m));
//	}

//	@ParameterizedTest
//	@ValueSource(ints = { -1, 0, 13, 14 })
//	public void testValidRangeForMonth2(int m)
//	{
//		assertFalse(Date.validRangeForMonth(m));
//		//assertFalse(Date.validRangeForMonth(0));
//	}
}
