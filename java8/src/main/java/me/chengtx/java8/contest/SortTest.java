package me.chengtx.java8.contest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.Test;

/**
 * @author chengt4
 *
 */
public class SortTest {

	private static Path input_path = Paths.get("input.txt");

	private static final int NUMBER = 500000;
	// private static final long UPPER = 10000000000L;
	private static final Random rnd = new Random();

	@Test
	public void initInput() {

		try (BufferedWriter bw = Files.newBufferedWriter(input_path);) {
			bw.write(String.valueOf(NUMBER));
			bw.newLine();

			for (int i = 0; i < NUMBER; i++, bw.write(" ")) {
				bw.write(String.valueOf(rnd.nextLong()));
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
	}

	@Test
	public void test() {
		Sort.start();
	}

}
