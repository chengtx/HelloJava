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

	public static void start() {

		// O(NlgN)+O(N)
		// set to reduce the duplication and provide self sort
		// export result to file
 		preSort();

		System.out.println("Start!");
		long start = System.currentTimeMillis();

		sort();
		
		postSort();
		
		long end = System.currentTimeMillis();
		long elapse = end - start;
		System.out.println("Finished! " + elapse);
	}

}
