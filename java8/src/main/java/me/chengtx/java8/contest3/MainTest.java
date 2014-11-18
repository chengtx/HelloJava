/**
 * 
 */
package me.chengtx.java8.contest3;

import static me.chengtx.java8.contest3.Main.main;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author chengt4
 *
 */
public class MainTest {

	@Test
	public void test0() {
		String[] args = { "1", "8 1 2 3 5 4 6 7 8", "10" };
		int expect = 2;
		int result = main(args);
		assertEquals(expect, result);
	}

	@Test
	public void test1() {
		String[] args = { "2", "7 3 4 5 6 8 9 10", "3 17 16 15", "20" };
		int expect = 5;
		int result = main(args);
		assertEquals(expect, result);
	}

	@Test
	public void test2() {
		String[] args = { "2", "4 11 12 13 20", "11 1 2 3 4 5 6 7 8 9 10 100",
				"100" };
		int expect = 4;
		int result = main(args);
		assertEquals(expect, result);
	}

	@Test
	public void test3() {
		String[] args = { "3", "7 34 35 36 37 38 40 41",
				"8 19 20 25 26 27 28 29 30", "5 1 31 32 33 100", "100" };
		int expect = 8;
		int result = main(args);
		assertEquals(expect, result);
	}

	@Test
	public void test4() {
		String[] args = { "3", "7 34 35 36 37 38 40 41",
				"8 19 20 25 26 27 28 29 30", "5 1 31 32 33 50", "50" };
		int expect = 8;
		int result = main(args);
		assertEquals(expect, result);
	}

}
