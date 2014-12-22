package me.chengtx.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chengt4
 */
public class Compare {

    public static void doCompare() {
        List<Apple> apples = new ArrayList<>();
        Collections.sort(apples, (o1, o2) -> {
            return o1.hashCode() - o2.hashCode();
        });
    }
}
