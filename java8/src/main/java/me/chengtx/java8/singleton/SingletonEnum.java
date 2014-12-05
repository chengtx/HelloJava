/**
 * 
 */
package me.chengtx.java8.singleton;

/**
 * @author chengt4
 *
 */
public enum SingletonEnum {
	
	INSTANCE;
	
	private String message = "Java";
	
	public void sayHello() {
		System.out.println("Hello "+ message);
	}

}
