/**
 * 
 */
package me.chengtx.java8;

/**
 * @author chengt4
 *
 */
public class BMW {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean result = drive(11, x -> {
			if (x > 10) {
				return true;
			}
			return false;
		});
		System.out.println(Thread.currentThread() + " " + result);

		// another try
		new Thread(() -> {

			System.out.println(Thread.currentThread()
					+ " I am a runnable task!");

		}).start();

	}

	public static boolean drive(int dis, Car car) {
		return car.go(dis);
	}

}
