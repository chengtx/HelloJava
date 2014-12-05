package me.chengtx.java8.singleton;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 12/5/2014
 */
public class SingletonInnerClass {

    private static class InnerHolder{
        private static String message = "Hello Java";
    }

    public static String getInstance(){
        return InnerHolder.message;
    }
}
