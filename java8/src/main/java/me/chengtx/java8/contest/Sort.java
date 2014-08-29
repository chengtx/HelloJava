package me.chengtx.java8.contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 */
public class Sort {

	private static long start;

	// constant for file path
	private static Path input_path = Paths.get("input.txt");
	private static Path output_path = Paths.get("output.txt");

	public static void setPath(String input, String output) {
		input_path = Paths.get(input);
		output_path = Paths.get(input);
	}

	@SuppressWarnings("unused")
	private static String line1;
	private static String line2;
	// container to hold all element and sort
	private static int length;
	private static String[] toSort;
	private static Set<Long> sorted = new TreeSet<>();

	public static void preSort() {
		try (BufferedReader br = Files.newBufferedReader(input_path);) {
			String line = "";
			int lineNum = 0;
			while ((line = br.readLine()) != null) {
				lineNum++;
				// System.out.println(line);
				switch (lineNum) {
				case 1:
					line1 = line;
					break;
				case 2:
					line2 = line;
					break;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
	}

	public static void postSort() {

		try (BufferedWriter bw = Files.newBufferedWriter(output_path);) {

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
			bw.write(o1);
			bw.newLine();
			bw.write(o2);
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}

	}

	public static void sort() {

		// use array native function to parse token
		toSort = line2.split(" ");
		length = toSort.length;

		// insert into TreeSet
		for (int i = 0; i < length; i++) {
			String temp = toSort[i];
			try {
				long e = Long.parseLong(temp);
				sorted.add(e);
			} catch (Exception ex) {

			}
		}
	}

	public static void sort2() {

		// use array native function to parse token
		toSort = line2.split(" ");
		length = toSort.length;
		System.out.println(length);

		// chunk size is 153 MB
		// then chunk * loop is about 10 GB ( > 9,999,999,999)
		final int CHUNK = 153 * 1024 * 1024;
		// loop is 16
		final int LOOP = 63;
		// help array
		final long[] help = new long[LOOP];
		help[0] = 1L;
		for (int i = 1; i < LOOP; i++) {
			help[i] = help[i - 1] * 2;
		}
		System.out.print("1: ");
		System.out.println(System.currentTimeMillis() - start);

		StringBuffer[] sbs = new StringBuffer[LOOP];
		for (int i = 0; i < LOOP; i++) {
			sbs[i] = new StringBuffer();
		}

		System.out.print("1.5: ");
		System.out.println(System.currentTimeMillis() - start);

		long[] array = new long[CHUNK];
		int c1 = 0;
		System.out.print("2: ");
		System.out.println(System.currentTimeMillis() - start);

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

		System.out.print("3: ");
		System.out.println(System.currentTimeMillis() - start);

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

		System.out.print("4: ");
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(c1);
		// StringBuilder result = new StringBuilder();
		for (int i = 0; i < LOOP; i++) {
			// result.append(sbs[i]);
		}
		// System.out.println(result.toString());

	}

	public static void sort3() {
		// use array native function to parse token
		toSort = line2.split(" ");
		length = toSort.length;
		System.out.println(length);

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
		// pause();
		System.out.println(c1);
		// System.out.println(sb.toString().substring(1));
		System.out.println(System.currentTimeMillis() - start);

	}

	public static void start() {

		// O(NlgN)+O(N)
		// set to reduce the duplication and provide self sort
		// export result to file
		preSort();
		System.out.println("Start!");
		start = System.currentTimeMillis();
		sort3();
		long end1 = System.currentTimeMillis();
		postSort();
		long end2 = System.currentTimeMillis();
		long elapse1 = end1 - start;
		long elapse2 = end2 - start;
		System.out.println("Finished! " + elapse1);
		System.out.println("Finished to file! " + elapse2);
	}

	public static void pause() {
		Object lock = new Object();
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
