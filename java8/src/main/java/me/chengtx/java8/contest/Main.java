/**
 * 
 */
package me.chengtx.java8.contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		Set<Long> sorted = new TreeSet<>();

		// insert into TreeSet
		for (int i = 0; i < length; i++) {
			String temp = toSort[i];
			try {
				long e = Long.parseLong(temp);
				sorted.add(e);
			} catch (Exception ex) {

			}
		}

		BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(
				System.out));

		String o1 = String.valueOf(sorted.size());
		StringBuilder sb = new StringBuilder();
		Iterator<Long> it = sorted.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(" ");
			}
		}
		String o2 = sb.toString();
		stdout.write(o1);
		stdout.newLine();
		stdout.write(o2);
	}

}
