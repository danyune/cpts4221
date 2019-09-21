package main;
//package test422.test422_wk3;

import java.time.LocalDate;

public class Date {

	// attributes etc.
	private int mm;
	private int dd;
	private int yyyy;
	private int dayNumber;
	private String dayName;
	private String zodiacSign;

	public Date(int dd, int mm, int yyyy) throws IllegalDateException {
		if (!isValidDate()) {
			throw new IllegalDateException();
		}
		this.mm = mm;
		this.dd = dd;
		this.yyyy = yyyy;
		initializeAttributes();
	}

	public String getZodiacSign() {
		return this.zodiacSign;
	}

	public void setZodiacSign(String n) {
		this.zodiacSign = n;
	}

	public String getDayName() {
		return this.dayName;
	}

	public void setDayName(String n) {
		this.dayName = n;
	}

	public int getDayNumber() {
		return this.dayNumber;
	}

	public void setDayNumber(int n) {
		this.dayNumber = n;
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
		if (isValidDate()) {
			return LocalDate.of(getYyyy(), getMm(), getDd()).getDayOfWeek().toString();
		}
		return null;
	}

	public int dateToDayNumber() {
		if (isValidDate()) {
			int days = 0;
			for (int i = 1; i < getMm(); i++) {
				try {
					days += new Date(1, i, getYyyy()).lastDayOfMonth();
				} catch (IllegalDateException e) {
					e.printStackTrace();
				}
			}
			days += getDd();
			return days;
		} else {
			return 0;
		}
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

	// dd mm yyyy
	public int[] nextDay() {
		int[] date = new int[3];
		if (getMm() == 12) {
			if (getDd() == lastDayOfMonth()) {
				date[0] = 1;
				date[1] = 1;
				date[2] = getYyyy() + 1;
			}
		} else if (getDd() == lastDayOfMonth()) {
			// FINISH
			date[0] = 1;
			date[1] = getMm() + 1;
			date[2] = getYyyy();
		} else {
			date[0] = getDd() + 1;
			date[1] = getMm();
			date[2] = getYyyy();
		}
		return date;
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

	// validRangeForDay will return true if the parameter thisDay is in the valid
	// range
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

	// validRangeForMonth will return true if the parameter thisMonth is in the
	// valid range
	public boolean validRangeForMonth() {
		if ((getMm() >= 1) || (getMm() <= 12)) {
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

	// validRangeForYear will return true if the parameter getYyyy() is in the valid
	// range
	public boolean validRangeForYear() {
		if ((getYyyy() >= 1700) && (getYyyy() <= 2020)) {
			System.out.println("Year = " + getYyyy());
			return true;
		} else {
			if (getYyyy() < 1700) {
				System.out.println("Year = " + getYyyy() + " is below minimum.");
			} else if (getYyyy() > 2020) {
				System.out.println("Year = " + getYyyy() + " is above maximum.");
			}
		}
		return false;
	}

	// validCombination will return true if the parameters are a valid combination
	public boolean validCombination() {
		if (isValidDate()) {
			if (getMm() == 2) {
				if ((isLeap() && getDd() <= 29) || (!isLeap() && getDd() <= 28)) {
					return true;
				}
			}

			else if (getMm() == 11) {
				if (getDd() <= 29) {
					return true;
				}
			}

			else if (getMm() == 4 || getMm() == 6 || getMm() == 8 || getMm() == 9) {
				if (getDd() <= 30) {
					return true;
				}
			}

			else if (getDd() <= 31) {
				return true;
			}
		}
		return false;
	}

	public boolean isLeap() {
		return ((getYyyy() % 4 == 0) && (getYyyy() % 100 != 0) || (getYyyy() % 400 == 0));
	}

}
