package me.chengtx.java8.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));

		String line1 = stdin.readLine();
		String line2 = stdin.readLine();

		// use array native function to parse token
		int length = Integer.parseInt(line1);
		String[] toSort = line2.split(" ");
		Set<Long> sorted = new TreeSet<Long>();

		// insert into TreeSet
		for (int i = 0; i < Math.min(length, toSort.length); i++) {
			String temp = toSort[i];
			try {
				long e = Long.parseLong(temp);
				sorted.add(e);
			} catch (Exception ex) {
			}
		}

		StringBuilder sb = new StringBuilder();
		Iterator<Long> it = sorted.iterator();

		if (!it.hasNext()) {
			return;
		}
		for (;;) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(" ");
			} else {
				break;
			}
		}

		System.out.println(sorted.size());
		System.out.println(sb.toString());
	}

}
