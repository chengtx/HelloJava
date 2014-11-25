package me.chengtx.idea;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServiceTest {

    private static Service s;

    @BeforeClass
    public static void setUp() throws Exception {
        s = new Service();
    }

    @Test
    public void testWelcome() throws Exception {
        Assert.assertEquals("Hello, Java", s.welcome("Java"));
    }
}