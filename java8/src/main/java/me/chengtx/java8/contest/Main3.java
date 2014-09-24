package me.chengtx.java8.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 */
public class Main3 {

	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String line1 = stdin.readLine();
		String line2 = stdin.readLine();

		// String line1 = "15";
		// String line2 = "9999999999 90 90 5 8 8 1234 55 89 8 34 1 0 99 3";

		// use array native function to parse token
		int length = Integer.parseInt(line1);

		if (length == 0) {
			System.out.println("0");
			System.out.println("");
			return;
		}

		String[] toSort = line2.split(" ");

		int c1 = 0;
		StringBuffer sb = new StringBuffer();
		final int N = 1000000000;
		final int LOOP = 10;
		BitSet[] bs = new BitSet[10];
		for (int i = 0; i < LOOP; i++) {
			bs[i] = new BitSet();
		}
		for (int i = 0; i < length; i++) {
			String temp = toSort[i];
			try {
				long e = Long.parseLong(temp);
				int exp = (int) (e / N);
				int pos = (int) (e % N);
				bs[exp].set(pos);
			} catch (Exception ex) {
			}
		}

		for (int i = 0; i < LOOP; i++) {
			for (int j = bs[i].nextSetBit(0); j >= 0; j = bs[i]
					.nextSetBit(j + 1)) {
				c1++;
				sb.append(' ');
				if (i > 0) {
					sb.append(i);
				}
				sb.append(j);
			}
		}
		System.out.println(c1);
		System.out.println(sb.toString().substring(1));
	}
}
