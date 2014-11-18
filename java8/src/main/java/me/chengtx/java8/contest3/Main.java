package me.chengtx.java8.contest3;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengt4 (tingxian.cheng@emc.com)
 *
 */
public class Main {

	public static int main(String[] args) {

		BufferedReader reader = null;
		try {

			// InputStream in = System.in;

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			for (String s : args) {
				out.write(s.getBytes());
				out.write(System.lineSeparator().getBytes());
			}
			InputStream in = new ByteArrayInputStream(out.toByteArray());

			// start the process
			reader = new BufferedReader(new InputStreamReader(in));
			String line = reader.readLine();
			int n_file = Integer.parseInt(line);

			int result = 0;

			if (n_file == 1) {
				// 60% case (N=1)
				line = reader.readLine();
				String[] temp = line.split(" ");
				int n_slot = Integer.parseInt(temp[0]);
				int max = 0;
				Map<Integer, Integer> rank = new HashMap<Integer, Integer>();
				for (int j = 1; j < temp.length; j++) {
					int n = Integer.parseInt(temp[j]);
					int k = n - j;
					int v = 0;
					if (rank.containsKey(k)) {
						v = rank.get(k);
					}
					rank.put(k, ++v);
					if (v > max) {
						max = v;
					}
				}
				result = (n_slot - max);
				return result;
			} else {
				// 40% case (N>1)
				int[] files = new int[n_file + 1];
				// mark this position in file
				reader.mark(0);
				for (int i = 0; i < n_file; i++) {
					line = reader.readLine();
					String[] temp = line.split(" ");
					int n = Integer.parseInt(temp[0]);
					files[i + 1] = files[i] + n;
				}
				// init the disk space
				int blocks = Integer.parseInt(reader.readLine().split(" ")[0]);
				int[] disks = new int[blocks + 1];
				// reset to the position
				reader.reset();
				for (int i = 0; i < n_file; i++) {
					line = reader.readLine();
					String[] temp = line.split(" ");
					int pre = files[i];
					for (int j = 1; j < temp.length; j++) {
						int n = Integer.parseInt(temp[j]);
						disks[n] = j + pre;
					}
				}

				/*
				 * for (int i = 0; i < n_file; i++) { line = reader.readLine();
				 * String[] temp = line.split(" "); int n_slot =
				 * Integer.parseInt(temp[0]); int max = 0; Map<Integer, Integer>
				 * rank = new HashMap<Integer, Integer>(); for (int j = 1; j <
				 * temp.length; j++) { int n = Integer.parseInt(temp[j]); int k
				 * = n - j; int v = 0; if (rank.containsKey(k)) { v =
				 * rank.get(k); } rank.put(k, ++v); if (v > max) { max = v; } }
				 * result += (n_slot - max); }
				 */
				return result;
			}
			// System.out.println(result);
		} catch (IOException ex) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
		return 0;
	}
}
