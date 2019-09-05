package calendar;

import java.time.LocalDate;

public class Date {

	private int mm;
	private int dd;
	private int yyyy;
	private int dayNumber;
	private String dayName;
	private String zodiacSign;

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public int getYyyy() {
		return yyyy;
	}

	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public String getZodiacSign() {
		return zodiacSign;
	}

	public void setZodiacSign(String zodiacSign) {
		this.zodiacSign = zodiacSign;
	}

	public Date(int dd, int mm, int yyyy) {
		this.mm = mm;
		this.dd = dd;
		this.yyyy = yyyy;
	}

	public void initializeAttributes() {
		this.dayNumber = dateToDayNumber();
		this.dayName = dateToDayName();
		this.zodiacSign = zodiacSign();
	}

	// Returns the date in the following format:
	// <dayName>, <mm>/<dd>/<yyyy>, is the <dayNumber> of the year and the zodiac
	// sign is <zodiacSign>
	public String toString() {
		return this.getDayName() + ", " + this.getMm() + "/" + this.getDd() + "/" + this.getYyyy() + ", is the "
				+ this.getDayNumber() + " of the year and the zodiac sign is " + this.getZodiacSign();
	}

	public boolean validRangeForDay() {
		if ((getDd() >= 1) && (getDd() <= 31)) {
			System.out.println("Day = " + getDd());
			return true;
		} else {
			if (getDd() < 1) {
				System.out.println("Day = " + getDd() + " is below minimum.");
			} else if (getDd() > 31) {
				System.out.println("Day = " + getDd() + " is above maximum.");
			}
		}
		return false;
	}

	public boolean validRangeForMonth() {
		if ((getMm() >= 1) && (getMm() <= 12)) {
			System.out.println("Month = " + getMm());
			return true;
		} else {
			if (getMm() < 1) {
				System.out.println("Month = " + getMm() + " is below minimum.");
			} else if (getMm() > 12) {
				System.out.println("Month = " + getMm() + " is above maximum.");
			}
		}
		return false;
	}

	// validRangeForYear will return true if the parameter thisYear is in the valid
	// range
	public boolean validRangeForYear() {
		if ((getYyyy() >= 1700) && (getYyyy() <= 2018)) {
			System.out.println("Year = " + getYyyy());
			return true;
		} else {
			if (getYyyy() < 1700) {
				System.out.println("Year = " + getYyyy() + " is below minimum.");
			} else if (getYyyy() > 2018) {
				System.out.println("Year = " + getYyyy() + " is above maximum.");
			}
		}
		return false;
	}

	// validCombination will return true if the parameters are a valid combination
	// 1 3 5 7 10 12 have 31 days
	// 2 4 6 8 9 11 do not
	public boolean validCombination() {

		if (getMm() == 2) {
			if ((isLeap() && getDd() == 29) || (!isLeap() && getDd() == 28)) {
				return true;
			}
		}

		else if (getMm() == 11) {
			if (getDd() == 29) {
				return true;
			}
		}

		else if (getMm() == 4 || getMm() == 6 || getMm() == 8 || getMm() == 9) {
			if (getDd() == 30) {
				return true;
			}
		}

		else if (getDd() == 31) {
			return true;
		}
		return false;
	}

	// Returns true if the combination of the parameters is valid
	public boolean isValidDate() {
		if (!validRangeForDay()) {
			return false;
		}
		if (!validRangeForMonth()) {
			return false;
		}
		if (!validRangeForYear()) {
			return false;
		}
		return true;
	}

	// A year is a leap year if it is divisible by 4,
	// unless it is a century year.
	// Century years are leap years only if they
	// are multiples of 400 (Inglis, 1961);
	// thus, 1992, 1996, and 2000 are leap years,
	// while the year 1900 is not a leap year
	public boolean isLeap() {
		if ((getYyyy() % 4 == 0) && (getYyyy() % 100 != 0) || (getYyyy() % 400 == 0)) {
			return true;
		}
		return false;
	}

	public String zodiacSign() {
		if ((getMm() == 12 && getDd() >= 22 && getDd() <= 30) || (getMm() == 1 && getDd() >= 1 && getDd() <= 19))
			return "Capricorn";
		else if ((getMm() == 1 && getDd() >= 20 && getDd() <= 31) || (getMm() == 2 && getDd() >= 1 && getDd() <= 17))
			return "Aquarius";
		else if ((getMm() == 2 && getDd() >= 18 && getDd() <= 28) || (getMm() == 3 && getDd() >= 1 && getDd() <= 19))
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
		else if ((getMm() == 9 && getDd() >= 23 && getDd() <= 30) || (getMm() == 10 && getDd() >= 1 && getDd() <= 21))
			return "Libra";
		else if ((getMm() == 10 && getDd() >= 23 && getDd() <= 31) || (getMm() == 11 && getDd() >= 1 && getDd() <= 21))
			return "Scorpio";
		else if ((getMm() == 11 && getDd() >= 22 && getDd() <= 30) || (getMm() == 12 && getDd() >= 1 && getDd() <= 21))
			return "Sagittarius";
		else
			return null;
	}

	public String dateToDayName() {
		if (isValidDate()) {
			return LocalDate.of(getYyyy(), getMm(), getDd()).getDayOfWeek().toString();
		}
		return null;
	}

	public int dateToDayNumber() {
		return getDd();
	}

	public int lastDayOfMonth() {
		if (isLeap()) {
			if (getMm() == 2) {
				return 29;
			}
		}
		if (getMm() == 1 || getMm() == 3 || getMm() == 5 || getMm() == 7 || getMm() == 10 || getMm() == 12) {
			return 31;
		} else {
			if (getMm() == 4 || getMm() == 6 || getMm() == 8 || getMm() == 9) {
				return 30;
			} else if (getMm() == 11) {
				return 29;
			} else {
				return 28;
			}
		}
	}
}
