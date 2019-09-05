package main;
//package test422.test422_wk3;
import java.util.Scanner;

//import calendar.Date;

public class Calendar {

	private static Date date = null;
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Day: ");
		int dd = Integer.parseInt(scanner.nextLine());
		System.out.println("Month: ");
		int mm = Integer.parseInt(scanner.nextLine());
		System.out.println("Year: ");
		int yyyy = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
		if(Date.isValidDate(dd, mm, yyyy))
			try {
				date = new Date(mm, dd, yyyy);
			} catch (IllegalDateException e) {
				System.err.println("This is not a valid date...");
				e.printStackTrace();
			}
		System.out.println(date.toString());

	}
}
