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
		// String line2 = "100 90 90 5 8 8 1234 55 89 8 34 1 0 99 3";

		// use array native function to parse token
		int length = Integer.parseInt(line1);
		String[] toSort = line2.split(" ");

		// totoal size is 10GB
		// final long TOTAL = 10000000L;
		// chunk size is 64MB
		final int CHUNK = 64000000;
		// loop is 16
		final int LOOP = 16;
		// help array
		final int[] help = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048,	4096, 8192, 16384, 32768 };

		StringBuilder[] sbs = new StringBuilder[LOOP];
		for (int i = 0; i < LOOP; i++) {
			sbs[i] = new StringBuilder();
		}

		int[] array = new int[CHUNK];

		int count = 0;

		for (int i = 0; i < length; i++) {
			String temp = toSort[i];
			try {
				long e = Long.parseLong(temp);
				int exp = (int) e / CHUNK;
				int pos = (int) e % CHUNK;
				int value = (int) help[exp];

				if (array[pos] == 0) {
					array[pos] = value;
				} else {
					int var = array[pos];
					for (int k = LOOP; k > 0; k++) {
						if (var >= help[k - 1]) {
							if (value == help[k - 1]) {
								// contain the value
								array[pos] += value;
							}
							var -= help[k - 1];
						} else {
							// do not contain this value
							break;
						}
					}
				}
			} catch (Exception ex) {
			}
		}

		for (int i = 0; i < CHUNK; i++) {

			int temp = array[i];
			for (int j = LOOP; j > 0; j--) {
				if (temp >= help[j - 1]) {
					// count ++;
					count++;
					sbs[j - 1].append(i + (j - 1) * CHUNK);
					sbs[j - 1].append(' ');
					temp -= help[j - 1];
				}
			}
		}

		System.out.println(count);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < LOOP; i++) {
			result.append(sbs[i]);
		}
		System.out.println(result.toString());
	}
}
