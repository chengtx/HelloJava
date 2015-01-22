package me.chengtx.java8;


import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp() throws SQLiteException {
        Assert.assertTrue(true);

        System.out.println("Hello Sqlite!");

        SQLiteConnection db = new SQLiteConnection(new File("result.db"));
        db.open(true);
//        ...
        SQLiteStatement st = db.prepare("SELECT order_id FROM orders WHERE quantity >= ?");
        try {
            int minimumQuantity = 5;
            st.bind(1, minimumQuantity);
            while (st.step()) {
//                orders.add(st.columnLong(0));
            }
        } finally {
            st.dispose();
        }
//        ...
        db.dispose();


    }

    @Test
    public void testImport(){

        Assert.assertTrue(true);

        boolean flag = true;


    }
}
