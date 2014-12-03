package me.chengtx.java8.time;

import java.time.Duration;
import java.time.Instant;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 12/3/2014
 */
public class Main {

    public static void main(String[] args) {
        Instant start = Instant.now();
        System.out.println(start);
        Instant end = Instant.now();
        System.out.println(end);

        Duration dur = Duration.between(start, end);
        System.out.println(dur);
    }
}
