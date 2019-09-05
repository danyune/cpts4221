package calendar.tests;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import static org.mockito.Mockito.*;

import calendar.Date;

public class DateTest {

	@Test
	public void testStringStuff() {

	}

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testDate() {

		Date d = new Date(1, 2, 2000);

		assertEquals(1, d.getDd());
		assertEquals(2, d.getMm());
		assertEquals(2000, d.getYyyy());

	}

	@Test
	public void testInitializeAttributes() {
		Date d = new Date(1, 1, 2019);
		assertEquals(d.dateToDayNumber(), 1);
		assertTrue(d.dateToDayName().equalsIgnoreCase("Tuesday"));
		assertTrue(d.zodiacSign().equalsIgnoreCase("Capricorn"));
	}

	@Test
	public void testInvalidDate() {
		Date d = new Date(13,13,2019);
		assertFalse(d.isValidDate());
	}
	
	@Test
	public void testValidDate()
	{
		Date d = new Date(1,1,2019);
		assertTrue(d.isValidDate());
	}

	@Test
	public void testIsLeap() {
		// divisible by 4:
		Date date = new Date(1, 1, 2012);
		assertTrue(date.isLeap());

		// not divisible by 4
		date.setYyyy(2007);
		assertFalse(date.isLeap());

		// century, not divisible by 400
		date.setYyyy(1900);
		assertFalse(date.isLeap());

		// century, divisible by 400
		date.setYyyy(2000);
		assertTrue(date.isLeap());
	}

	@Mock
	Date mockDate = mock(Date.class);

//	@Rule
//	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void testToString() {

		doReturn("Tuesday").when(mockDate).dateToDayName();
		assertEquals("Tuesday", mockDate.dateToDayName());

//		Date spyDate2  = spy(new Date(30,8,2017));
//		
//		doReturn("Wednesday").when(spyDate2).getDayName();
//		doReturn(8).when(spyDate2).getMm();
//		doReturn(30).when(spyDate2).getDd();
//		doReturn(2017).when(spyDate2).getYyyy();
//		doReturn(242).when(spyDate2).getDayNumber();
//		doReturn("Virgo").when(spyDate2).getZodiacSign();
//		
//		assertEquals("Wednesday, 8/30/2017, is the 242 of "
//				+ "the year and the zodiac sign is Virgo",spyDate2.toString());

	}

//	@Test
//	public void testZodiacSign() {
//		// "Capricorn": December 22 - January 19
//		// normal and border cases:
//		assertEquals("Capricorn", Date.zodiacSign(12,22));
//		assertEquals("Capricorn", Date.zodiacSign(1,19));
//		// more border cases based on the implementation:
//		assertEquals("Capricorn", Date.zodiacSign(12,31));
//		assertEquals("Capricorn", Date.zodiacSign(1,1));
//
//		// "Aquarius": January 20 - February 18
//		assertEquals("Aquarius",Date.zodiacSign(1,20));
//		assertEquals("Aquarius",Date.zodiacSign(1,31));
//		assertEquals("Aquarius",Date.zodiacSign(2,1));
//		assertEquals("Aquarius",Date.zodiacSign(2,18));
//
//		// "Pisces": February 19 - March 20
//		assertEquals("Pisces",Date.zodiacSign(2,19));
//		assertEquals("Pisces",Date.zodiacSign(2,29));
//		assertEquals("Pisces",Date.zodiacSign(3,1));
//		assertEquals("Pisces",Date.zodiacSign(3,20));
//
//		// "Aries": March 21 - April 19
//		assertEquals("Aries",Date.zodiacSign(3,21));
//		assertEquals("Aries",Date.zodiacSign(3,31));
//		assertEquals("Aries",Date.zodiacSign(4,1));
//		assertEquals("Aries",Date.zodiacSign(4,19));
//
//		// "Taurus": April 20 - May 20
//		assertEquals("Taurus",Date.zodiacSign(4,20));
//		assertEquals("Taurus",Date.zodiacSign(4,30));
//		assertEquals("Taurus",Date.zodiacSign(5,1));
//		assertEquals("Taurus",Date.zodiacSign(5,20));
//
//		// "Gemini": May 21 - June 20
//		assertEquals("Gemini",Date.zodiacSign(5,21));
//		assertEquals("Gemini",Date.zodiacSign(5,31));
//		assertEquals("Gemini",Date.zodiacSign(6,1));
//		assertEquals("Gemini",Date.zodiacSign(6,20));
//
//		// "Cancer": June 21 - July 22
//		assertEquals("Cancer",Date.zodiacSign(6,21));
//		assertEquals("Cancer",Date.zodiacSign(6,30));
//		assertEquals("Cancer",Date.zodiacSign(7,1));
//		assertEquals("Cancer",Date.zodiacSign(7,20));
//
//		// "Leo": July 23 - August 22
//		assertEquals("Leo",Date.zodiacSign(7,23));
//		assertEquals("Leo",Date.zodiacSign(7,31));
//		assertEquals("Leo",Date.zodiacSign(8,1));
//		assertEquals("Leo",Date.zodiacSign(8,22));
//
//		// "Virgo": August 23 - September 22
//		assertEquals("Virgo",Date.zodiacSign(8,23));
//		assertEquals("Virgo",Date.zodiacSign(8,31));
//		assertEquals("Virgo",Date.zodiacSign(9,1));
//		assertEquals("Virgo",Date.zodiacSign(9,22));
//
//		// "Libra": September 23 - October 22
//		assertEquals("Libra",Date.zodiacSign(9,23));
//		assertEquals("Libra",Date.zodiacSign(9,30));
//		assertEquals("Libra",Date.zodiacSign(10,1));
//		assertEquals("Libra",Date.zodiacSign(10,22));
//
//		// "Scorpio": October 23 - November 21
//		assertEquals("Scorpio",Date.zodiacSign(10,23));
//		assertEquals("Scorpio",Date.zodiacSign(10,31));
//		assertEquals("Scorpio",Date.zodiacSign(11,1));
//		assertEquals("Scorpio",Date.zodiacSign(11,21));
//
//		// "Sagittarius": November 22 - December 21
//		assertEquals("Sagittarius",Date.zodiacSign(11,23));
//		assertEquals("Sagittarius",Date.zodiacSign(11,30));
//		assertEquals("Sagittarius",Date.zodiacSign(12,1));
//		assertEquals("Sagittarius",Date.zodiacSign(12,21));
//	}
//
//	@Test
//	public void testValidRangeForDay() {
//		// valid days
//		assertTrue(Date.validRangeForDay(1));
//		assertTrue(Date.validRangeForDay(31));
//
//		// not valid:
//		assertFalse(Date.validRangeForDay(0));
//		assertFalse(Date.validRangeForDay(32));
//		assertFalse(Date.validRangeForDay(-5));
//	}
//
//	@Test
//	public void testValidRangeForMonth() {
//		// valid months
//		assertTrue(Date.validRangeForMonth(1));
//		assertTrue(Date.validRangeForMonth(12));
//
//		// not valid:
//		assertFalse(Date.validRangeForMonth(0));
//		assertFalse(Date.validRangeForMonth(13));
//		assertFalse(Date.validRangeForMonth(-5));
//	}
//
//	@Test
//	public void testValidRangeForYear() {
//		// Valid year: >= 1700 && <= 2017
//		assertTrue(Date.validRangeForYear(1700));
//		assertTrue(Date.validRangeForYear(2017));
//		// not valid years:
//		assertFalse(Date.validRangeForYear(2020));
//		assertFalse(Date.validRangeForYear(-1));
//		assertFalse(Date.validRangeForYear(1699));
//	}

	// The rest of the tests
	// ...

}
