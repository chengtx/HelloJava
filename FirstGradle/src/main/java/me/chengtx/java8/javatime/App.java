/**
 * 
 */
package me.chengtx.java8.javatime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author chengt4
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Instant now = Instant.now();
		System.out.println(now);

		LocalDateTime local = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:MM:SS.SSS");

		System.out.println(format.format(local));

		System.out.println(ZoneId.getAvailableZoneIds());

		String dt1 = "2014-10-11T07:00:17.897Z";
		String dt2 = "2014-10-11T15:10:00.007";

		System.out.println(Instant.parse(dt1));
		System.out.println(LocalDateTime.parse(dt2));

	}

}
