package me.chengtx.java8.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author chengt4
 *
 */
public class LRUCache {

	private Map<Integer, Integer> cache;

	public LRUCache(final int capacity) {

		cache = new LinkedHashMap<Integer, Integer>(capacity + 1, 1, true) {

			private static final long serialVersionUID = 3353408081822947649L;

			@Override
			protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
				return size() > capacity;
			}

		};

	}

	public int get(int key) {
		return cache.containsKey(key) ? cache.get(key) : -1;
	}

	public void set(int key, int value) {
		cache.put(key, value);
	}

}
