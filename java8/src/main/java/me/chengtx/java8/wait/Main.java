/**
 * 
 */
package me.chengtx.java8.wait;

/**
 * @author chengt4
 *
 */
public class Main {

	 private static Object lock = new Object();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		synchronized(lock){
			try {
				lock.wait(3);
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("Success!");
		
		synchronized(lock){
			lock.notify();
		}
		
		System.out.println("Success2!");
	}

}
