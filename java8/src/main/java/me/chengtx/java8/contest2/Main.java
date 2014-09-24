package me.chengtx.java8.contest2;

import java.io.IOException;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 *
 */
public class Main {

	// record how many comparisons the solution did;
	// it can be used to know which algorithm is better
	private static int comparisons = 0;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// BufferedReader stdin = new BufferedReader(new InputStreamReader(
		// System.in));
		// String line1 = stdin.readLine();
		// String line2 = stdin.readLine();

		String line1 = "8 1 2 3 4 5 7 7 7";
		String line2 = "9 2 3 4 5 6 2 3 4 5";

		int result = -1;

		String[] str1 = line1.split(" ");
		String[] str2 = line2.split(" ");

		int len1 = Integer.parseInt(str1[0]);
		int len2 = Integer.parseInt(str2[0]);

		if (len1 < 2 || len2 < 2) {
			result = 1;
			System.out.println(result);
			return;
		}

		int[] o1 = new int[len1 - 1];
		int[] o2 = new int[len2 - 1];

		for (int i = 0; i < len1 - 1; i++) {
			o1[i] = Integer.parseInt(str1[i + 2])
					- Integer.parseInt(str1[i + 1]);
		}
		for (int i = 0; i < len2 - 1; i++) {
			o2[i] = Integer.parseInt(str2[i + 2])
					- Integer.parseInt(str2[i + 1]);
		}

		long start = System.currentTimeMillis();

		// using LCS algorithm to caculate the longest common substring between
		// o1 and o2
		result = longestCommonSubstring_n2_1(o1, o2) + 1;

		System.out.println(result);
		System.out.println("Time: " + (System.currentTimeMillis() - start)
				+ " Compare: " + comparisons);
	}

	private static int longestCommonSubstring_n2_1(int[] o1, int[] o2) {
		int size1 = o1.length;
		int size2 = o2.length;
		if (size1 == 0 || size2 == 0)
			return 0;

		// the start position of substring in original string
		// int start1 = -1;
		// int start2 = -1;

		// the longest length of common substring
		int longest = 0;

		int indices[] = { 0, 0 };
		int sizes[] = { size1, size2 };

		// shift strings to find the longest common substring
		for (int index = 0; index < 2; ++index) {
			indices[0] = 0;
			indices[1] = 0;

			// i is reference to the value in array
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
					++comparisons;
					if (o1[m] != o2[n]) {
						length = 0;
					} else {
						++length;
						if (longest < length) {
							longest = length;
							// start1 = m - longest + 1;
							// start2 = n - longest + 1;
						}
					}

					++m;
					++n;
				}
			}
		}

		return longest;
	}
}
