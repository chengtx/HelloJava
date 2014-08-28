package me.chengtx.java8.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 */
public class Main2 {

	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String line1 = stdin.readLine();
		String line2 = stdin.readLine();

		// String line1 = "15";
		// String line2 =
		// "9999999999 100 90 90 5 8 8 32000000 32000001 89 8 34 1 0 3";

		// use array native function to parse token
		int length = Integer.parseInt(line1);
		String[] toSort = line2.split(" ");

		// chunk size is 153 MB
		// then chunk * loop is about 10 GB
		final int CHUNK = 153 * 1024 * 1024;
		// loop is 63
		final int LOOP = 63;
		// help array
		final long[] help = new long[LOOP];
		help[0] = 1;
		for (int i = 1; i < LOOP; i++) {
			help[i] = help[i - 1] << 1;
		}

		StringBuilder[] sbs = new StringBuilder[LOOP];
		for (int i = 0; i < LOOP; i++) {
			sbs[i] = new StringBuilder();
		}

		long[] array = new long[CHUNK];
		int c1 = 0;

		for (int i = 0; i < length; i++) {
			String temp = toSort[i];
			try {
				long e = Long.parseLong(temp);
				int exp = (int) (e / CHUNK);
				int pos = (int) (e % CHUNK);
				long value = help[exp];
				long var = array[pos];
				if ((var >> exp) % 2 == 0) {
					// do not contain this value, add value into array
					array[pos] += value;
					c1++;
				}
			} catch (Exception ex) {
			}
		}

		for (int i = 0; i < CHUNK; i++) {
			if (array[i] == 0) {
				continue;
			}
			for (int j = 0; j < LOOP; j++) {
				if (array[i] % 2 == 1) {
					sbs[j].append((long) i + (long) j * CHUNK);
					sbs[j].append(' ');
				}
				array[i] = array[i] >> 1;
				if (array[i] == 0) {
					break;
				}
			}
		}

		System.out.println(c1);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < LOOP; i++) {
			result.append(sbs[i]);
		}
		System.out.println(result.toString());
	}
}
