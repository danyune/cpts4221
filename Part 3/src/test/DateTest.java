package test;
//package test422.test422_wk3;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import main.Date;
import main.IllegalDateException;

import static org.mockito.Mockito.*;

//import calendar.Date;
//import calendar.IllegalDateException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Date.class)
public class DateTest {

//	@Test
//	public void testValidCombination() {
//		PowerMockito.spy(Date.class);
//		PowerMockito.when("isLeap").the
//		boolean returnValue = Date.isLeap(2012);
//		PowerMockito.verifyStatic(Date.class);
//		Date.isLeap(2012);
//		assertEquals(true, returnValue);
//		assertTrue(Date.validCombination(29, 2, 2012));
//		PowerMockito.when(Date.isLeap(2017)).thenReturn(false);
//		returnValue = Date.isLeap(2017);
//		PowerMockito.verifyStatic(Date.class);
//		Date.isLeap(2017);
//		assertEquals(false, returnValue);
//		assertFalse(Date.validCombination(29, 2, 2017));
//		assertTrue(Date.validCombination(31, 1, 2017));
//		assertFalse(Date.validCombination(31, 6, 2017));
//	}

	@Test
	public void testDateValid() {

		Date d;
		try {
			d = new Date(1, 2, 2000);
			assertEquals(1, d.getDd());
			assertEquals(2, d.getMm());
			assertEquals(2000, d.getYyyy());

		} catch (IllegalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = IllegalDateException.class)
	public void testDateNotValid() throws IllegalDateException {
		new Date(-1, 2, 2000);
	}

	@Test
	public void testInitializeAttributes() {
		Date spyDate;
		try {
			spyDate = spy(new Date(1, 2, 2000));

			doReturn(32).when(spyDate).dateToDayNumber();
			doReturn("Tuesday").when(spyDate).dateToDayName();
			doReturn("Aquarius").when(spyDate).zodiacSign();

			spyDate.initializeAttributes();

			verify(spyDate).dateToDayNumber();
			verify(spyDate).dateToDayName();
			verify(spyDate).zodiacSign();

			assertEquals(32, spyDate.dateToDayNumber());
			assertEquals("Tuesday", spyDate.dateToDayName());
			assertEquals("Aquarius", spyDate.zodiacSign());

			verify(spyDate, never()).setDd(1);

		} catch (IllegalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testZodiacSign() {

		Date spyDate;

		try {
			// "Capricorn": December 22 - January 19
			// normal and border cases:
			spyDate = spy(new Date(22, 12, 2017));
			doReturn(12).when(spyDate).getMm();
			doReturn(22).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			spyDate = spy(new Date(1, 1, 2017));
			doReturn(1).when(spyDate).getMm();
			doReturn(1).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			spyDate = spy(new Date(19, 1, 2017));
			doReturn(1).when(spyDate).getMm();
			doReturn(19).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			// more border cases based on the implementation:
			spyDate = spy(new Date(31, 12, 2017));
			doReturn(12).when(spyDate).getMm();
			doReturn(31).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			// etc. for the rest of the signs
			// ...

		} catch (IllegalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// the rest of the tests...

}
