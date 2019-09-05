package main;
//package test422.test422_wk3;

import java.time.LocalDate;

import calendar.Date;

public class Date {

	// attributes etc.
	private int mm;
	private int dd;
	private int yyyy;
	private int dayNumber;
	private String dayName;
	private String zodiacSign;

	public Date(int dd, int mm, int yyyy) throws IllegalDateException {
		if (!isValidDate(dd, mm, yyyy)) {
			throw new IllegalDateException();
		}
		this.mm = mm;
		this.dd = dd;
		this.yyyy = yyyy;
	}

	public int getMm() {
		return this.mm;
	}

	public void setMm(int m) {
		this.mm = m;
	}

	public int getDd() {
		return this.dd;
	}

	public void setDd(int d) {
		this.dd = d;
	}

	public int getYyyy() {
		return this.yyyy;
	}

	public void setYyyy(int y) {
		this.yyyy = y;
	}

	public void initializeAttributes() {
		this.dayName = dateToDayName();
		this.dayNumber = dateToDayNumber();
		this.zodiacSign = zodiacSign();
	}

	// Returns the day of the week
	public String dateToDayName() {
		if (isValidDate(dd, mm, yyyy)) {
			return LocalDate.of(getYyyy(), getMm(), getDd()).getDayOfWeek().toString();
		}
		return null;
	}

	public int dateToDayNumber() {
		if (isValidDate(dd, mm, yyyy)) {
			int days = 0;
			for (int i = 1; i < getMm(); i++) {
				days += new Date(1, i, getYyyy()).lastDayOfMonth();
			}
			days += getDd();
			return days;
		} else {
			return 0;
		}
	}

	public int lastDayOfMonth() {
		// TODO
		return 0;
	}

	// Returns the zodiac sign
	public String zodiacSign() {
		if ((getMm() == 12 && getDd() >= 22 && getDd() <= 31) || (getMm() == 1 && getDd() >= 1 && getDd() <= 19))
			return "Capricorn";
		else if ((getMm() == 1 && getDd() >= 20 && getDd() <= 31) || (getMm() == 2 && getDd() >= 1 && getDd() <= 18))
			return "Aquarius";
		else if ((getMm() == 2 && getDd() >= 19 && getDd() <= 29) || (getMm() == 3 && getDd() >= 1 && getDd() <= 20))
			return "Pisces";
		else if ((getMm() == 3 && getDd() >= 20 && getDd() <= 31) || (getMm() == 4 && getDd() >= 1 && getDd() <= 19))
			return "Aries";
		else if ((getMm() == 4 && getDd() >= 20 && getDd() <= 30) || (getMm() == 5 && getDd() >= 1 && getDd() <= 20))
			return "Taurus";
		else if ((getMm() == 5 && getDd() >= 21 && getDd() <= 31) || (getMm() == 6 && getDd() >= 1 && getDd() <= 20))
			return "Gemini";
		else if ((getMm() == 6 && getDd() >= 21 && getDd() <= 30) || (getMm() == 7 && getDd() >= 1 && getDd() <= 22))
			return "Cancer";
		else if ((getMm() == 7 && getDd() >= 23 && getDd() <= 31) || (getMm() == 8 && getDd() >= 1 && getDd() <= 22))
			return "Leo";
		else if ((getMm() == 8 && getDd() >= 23 && getDd() <= 31) || (getMm() == 9 && getDd() >= 1 && getDd() <= 22))
			return "Virgo";
		else if ((getMm() == 9 && getDd() >= 23 && getDd() <= 30) || (getMm() == 10 && getDd() >= 1 && getDd() <= 22))
			return "Libra";
		else if ((getMm() == 10 && getDd() >= 23 && getDd() <= 31) || (getMm() == 11 && getDd() >= 1 && getDd() <= 21))
			return "Scorpio";
		else if ((getMm() == 11 && getDd() >= 22 && getDd() <= 30) || (getMm() == 12 && getDd() >= 1 && getDd() <= 21))
			return "Sagittarius";
		else
			return null;
	}

	// the rest of the methods
	// Returns true if the combination of the parameters is valid
	public static boolean isValidDate(int thisDay, int thisMonth, int thisYear) {
		if (!validRangeForDay(thisDay)) {
			return false;
		}
		if (!validRangeForMonth(thisMonth)) {
			return false;
		}
		if (!validRangeForYear(thisYear)) {
			return false;
		}
		return true;
	}

	// validRangeForDay will return true if the parameter thisDay is in the valid
	// range
	public static boolean validRangeForDay(int thisDay) {
		if ((thisDay >= 1) && (thisDay <= 31)) {
			System.out.println("Day = " + thisDay);
			return true;
		} else {
			if (thisDay < 1) {
				System.out.println("Day = " + thisDay + " is below minimum.");
			} else if (thisDay > 32) {
				System.out.println("Day = " + thisDay + " is above maximum.");
			}
		}
		return false;
	}

	// validRangeForMonth will return true if the parameter thisMonth is in the
	// valid range
	public static boolean validRangeForMonth(int thisMonth) {
		if ((thisMonth >= 1) || (thisMonth <= 12)) {
			System.out.println("Month = " + thisMonth);
			return true;
		} else {
			if (thisMonth < 1) {
				System.out.println("Month = " + thisMonth + " is below minimum.");
			} else if (thisMonth > 12) {
				System.out.println("Month = " + thisMonth + " is above maximum.");
			}
		}
		return false;
	}

	// validRangeForYear will return true if the parameter thisYear is in the valid
	// range
	public static boolean validRangeForYear(int thisYear) {
		if ((thisYear >= 1700) && (thisYear <= 2020)) {
			System.out.println("Year = " + thisYear);
			return true;
		} else {
			if (thisYear < 1700) {
				System.out.println("Year = " + thisYear + " is below minimum.");
			} else if (thisYear > 2020) {
				System.out.println("Year = " + thisYear + " is above maximum.");
			}
		}
		return false;
	}

	// validCombination will return true if the parameters are a valid combination
	public static boolean validCombination(int thisDay, int thisMonth, int thisYear) {
		if (thisMonth == 2) {
			if ((isLeap(thisYear) && thisDay == 29) || (!isLeap(thisYear) && thisDay == 28)) {
				return true;
			}
		}

		else if (thisMonth == 11) {
			if (thisDay == 29) {
				return true;
			}
		}

		else if (thisMonth == 4 || thisMonth == 6 || thisMonth == 8 || thisMonth == 9) {
			if (thisDay == 30) {
				return true;
			}
		}

		else if (thisDay == 31) {
			return true;
		}
		return false;
	}

	public static boolean isLeap(int thisYear) {
		return ((thisYear % 4 == 0) && (thisYear % 100 != 0) || (thisYear % 400 == 0));
	}

}
