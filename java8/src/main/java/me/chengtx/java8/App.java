package me.chengtx.java8;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(App.class.getSimpleName())
				.forks(1).build();

		new Runner(opt).run();
	}

	@Benchmark
	public static void wellHelloThere() {
		// this method was intentionally left blank.
		System.out.println("Hello There!");
	}
}
