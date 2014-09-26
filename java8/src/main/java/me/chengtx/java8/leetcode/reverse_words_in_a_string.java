package me.chengtx.java8.leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author chengt4
 *
 */
public class reverse_words_in_a_string {

	public String reverseWords(String s) {

		StringBuilder result = new StringBuilder();
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				word.append(s.charAt(i));
			} else {
				if (word.length() > 0) {
					result.insert(0, word.toString());
					result.insert(0, " ");
					word = new StringBuilder();
				}
			}
		}
		if (word.length() > 0) {
			result.insert(0, word.toString());
		}
		if (result.length() > 0 && result.charAt(0) == ' ') {
			return result.substring(1);
		}
		return result.toString();
	}

	@Test
	public void testReverseWords() {

		String g1 = "the sky is blue";
		String e1 = "blue is sky the";

		String g2 = " ";
		String e2 = "";

		String g3 = "1 ";
		String e3 = "1";

		String g4 = " a   b ";
		String e4 = "b a";

		assertEquals(e1, reverseWords(g1));
		assertEquals(e2, reverseWords(g2));
		assertEquals(e3, reverseWords(g3));
		assertEquals(e4, reverseWords(g4));
	}

}
