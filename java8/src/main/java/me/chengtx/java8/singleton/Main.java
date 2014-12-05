/**
 * 
 */
package me.chengtx.java8.singleton;

/**
 * @author chengt4
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        System.out.println(SingletonInnerClass.getInstance());
        SingletonEnum.INSTANCE.sayHello();
	}

}
