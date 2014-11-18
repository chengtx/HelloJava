package me.chengtx.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author chengt4
 *
 */
public class Compare {

	public static void doCompare(){
		
		List<Apple> apples = new ArrayList<>();
		
		
		Collections.sort(apples, new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
	}

}
