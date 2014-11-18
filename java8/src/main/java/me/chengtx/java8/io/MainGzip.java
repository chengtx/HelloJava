/**
 * 
 */
package me.chengtx.java8.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author chengt4
 *
 */
public class MainGzip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String filename = "PEM141029N000000001_7";

		File input = new File(filename);
		File output = new File("test.gz");

		try {

			InputStream in = new BufferedInputStream(new FileInputStream(input));
			OutputStream outtemp = new BufferedOutputStream(
					new FileOutputStream(output));

			OutputStream out = new GZIPOutputStream(outtemp, true);

			IOUtils.copyLarge(in, out);
		} catch (IOException ex) {
		} finally {
		}

	}
}
