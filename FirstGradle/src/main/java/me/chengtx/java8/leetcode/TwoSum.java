/**
 * 
 */
package me.chengtx.java8.leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author chengt4
 *
 */
public class TwoSum {

	public int[] twoSum(int[] numbers, int target) {

		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(target - numbers[i])) {
				result[0] = map.get(target - numbers[i]) + 1;
				result[1] = i + 1;
			}
			map.put(numbers[i], i);
		}
		return result;
	}

	@Test
	public void test() {

		int[] numbers = { 2, 7, 11, 15 };
		int[] expecteds = { 1, 2 };
		int target = 9;

		assertArrayEquals(expecteds, twoSum(numbers, target));

	}

}
