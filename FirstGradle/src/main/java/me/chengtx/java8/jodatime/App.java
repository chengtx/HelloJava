/**
 * 
 */
package me.chengtx.java8.jodatime;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;

/**
 * @author chengt4
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTime dt = new DateTime();
		String monthName = dt.monthOfYear().getAsText();
		String frenchShortName = dt.monthOfYear().getAsShortText(Locale.CHINA);
		System.out.println(monthName);
		System.out.println(frenchShortName);
	}

	public boolean isAfterPayDay(DateTime datetime) {
		if (datetime.getMonthOfYear() == 2) { // February is month 2!!
			return datetime.getDayOfMonth() > 26;
		}
		return datetime.getDayOfMonth() > 28;
	}

	public Days daysToNewYear(LocalDate fromDate) {
		LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
		return Days.daysBetween(fromDate, newYear);
	}

	public boolean isRentalOverdue(DateTime datetimeRented) {
		Period rentalPeriod = new Period().withDays(2).withHours(12);
		return datetimeRented.plus(rentalPeriod).isBeforeNow();
	}

	public String getBirthMonthText(LocalDate dateOfBirth) {
		return dateOfBirth.monthOfYear().getAsText(Locale.ENGLISH);
	}

}
