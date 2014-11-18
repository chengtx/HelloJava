package me.chengtx.java8.ngis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chengt4
 *
 */
public class App {

	private static final String ROOT = "C:\\chengtx\\Perforce\\InformationServer";

	private static final Set<String> exclude = new HashSet<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("com.emc.ngis.platform.federation");
			add("com.emc.ngis.logging.impl.AggregatedLoggerTest");
		}
	};

	public static void main(String[] args) {
		Path root = Paths.get(ROOT);
		try {
			Files.walk(root)
					// get folder whose name start with "com.com..."
					.filter(s -> {
						Path p = s.getFileName();
						return p.toString().startsWith("com.emc")
								&& Files.isDirectory(s);
					})
					// get folder name
					.map(s -> {
						return s.getFileName();
					})
					// exclude some special folder name
					.filter(s -> {
						return !exclude.contains(s.toString());
					})
					// sort in nature order and display
					.sorted().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
