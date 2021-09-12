/**
 * 
 */
package com.ss.jb5.one;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Elliot
 *
 */
public class DateTimeAPI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// for a given year, report the length of each month within that year
		System.out.println("Days per month in 2020:");
		Year year = Year.of(2020);
		for (Month month : Month.values()) {
			System.out.println(month + " " + year.atMonth(month).lengthOfMonth());
		}
		System.out.println();
		
		// for a given month of the current year, list all Mondays in that month
		System.out.println("Mondays in April 2021:");
		LocalDate date = Year.now().atMonth(4).atDay(1).// this April
				with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		Month month = date.getMonth();
		Month tempMonth = date.getMonth();
		while (tempMonth == month) {
			System.out.println(date);
			date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			tempMonth = date.getMonth();
		}
		System.out.println();

		// test whether a given date occurs on Friday the 13th
		System.out.println("Checking if given day is Friday the 13th:");
		LocalDate date1 = Year.now().atMonth(8).atDay(13);
		DayOfWeek day = date1.getDayOfWeek();

		if (day.getValue() == 5 && date1.getDayOfMonth() == 13) System.out.println("This day is Friday the 13th");
		else System.out.println("This day is not Friday the 13th");
	}
}
