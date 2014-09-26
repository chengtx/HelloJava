/**
 * 
 */
package me.chengtx.java8.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.google.common.io.CountingOutputStream;

public class BufferDiff {
	public static void main(String args[]) throws IOException {

		FileOutputStream unbufStream;
		BufferedOutputStream bufStream;
		CountingOutputStream countStream;
		
		FileOutputStream unbufStream2;

		unbufStream = new FileOutputStream("test.one");
		bufStream = new BufferedOutputStream(new FileOutputStream("test.two"));
		countStream = new CountingOutputStream(
				new FileOutputStream("test.three"));
		unbufStream2 = new FileOutputStream("test.four");

		System.out
				.println("Write file unbuffered: " + time(unbufStream) + "ms");
		System.out.println("Write file buffered: " + time(bufStream) + "ms");
		System.out
				.println("Write file countStream: " + time(countStream) + "ms");
		System.out.println("Write file byteStream: " + time(unbufStream2) + "ms");
	}

	static int time(OutputStream os) throws IOException {
		Date then = new Date();
		for (int i = 0; i < 5000000; i++) {
			os.write(1);
		}
		os.close();
		return (int) ((new Date()).getTime() - then.getTime());
	}

	static int time2(OutputStream os) throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		BufferedOutputStream bufStream = new BufferedOutputStream(byteOut);
		Date then = new Date();
		for (int i = 0; i < 5000000; i++) {
			bufStream.write(1);
		}
		bufStream.close();
		byteOut.writeTo(os);
		byteOut.close();
		os.close();
		return (int) ((new Date()).getTime() - then.getTime());
	}
}