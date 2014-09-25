package me.chengtx.java8.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 *
 */
public class Main2 {

	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String line1 = stdin.readLine();
		String line2 = stdin.readLine();

		// the longest length of common substring
		int longest = 0;

		String[] str1 = line1.split(" ");
		String[] str2 = line2.split(" ");

		int size1 = Integer.parseInt(str1[0]) - 1;
		int size2 = Integer.parseInt(str2[0]) - 1;

		if (size1 == -1 || size2 == -1) {
			longest = 0;
			System.out.println(longest);
			return;
		}

		if (size1 == 0 || size2 == 0) {
			longest = 1;
			System.out.println(longest);
			return;
		}

		int[] o1 = new int[size1];
		int[] o2 = new int[size2];

		for (int i = 0; i < size1; i++) {
			o1[i] = Integer.parseInt(str1[i + 2])
					- Integer.parseInt(str1[i + 1]);
		}
		for (int i = 0; i < size2; i++) {
			o2[i] = Integer.parseInt(str2[i + 2])
					- Integer.parseInt(str2[i + 1]);
		}

		// using LCS algorithm to caculate the longest common substring between
		// o1 and o2
		int indices[] = { 0, 0 };
		int sizes[] = { size1, size2 };

		// shift strings to find the longest common substring
		for (int index = 0; index < 2; ++index) {
			indices[0] = 0;
			indices[1] = 0;

			int value = indices[index];
			int size = sizes[index];

			// this is tricky to skip comparing strings both start with 0 for
			// second loop
			value = index;
			for (; value < size; ++value) {
				int m = indices[0];
				int n = indices[1];
				int length = 0;

				// with following check to reduce some more comparisons
				if (size1 - m <= longest || size2 - n <= longest)
					break;

				while (m < size1 && n < size2) {
					if (o1[m] != o2[n]) {
						length = 0;
					} else {
						++length;
						if (longest < length) {
							longest = length;
						}
					}

					++m;
					++n;
				}
			}
		}
		System.out.println(longest + 1);
	}

}
