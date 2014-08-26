package me.chengtx.java8.contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 */
public class Sort {

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
//				System.out.println(result.toString());

	}

	public static void start() {

		// O(NlgN)+O(N)
		// set to reduce the duplication and provide self sort
		// export result to file
		preSort();

		System.out.println("Start!");
		long start = System.currentTimeMillis();
		sort2();
		long end1 = System.currentTimeMillis();
		postSort();
		long end2 = System.currentTimeMillis();
		long elapse1 = end1 - start;
		long elapse2 = end2 - start;
		System.out.println("Finished! " + elapse1);
		System.out.println("Finished to file! " + elapse2);
	}

}
