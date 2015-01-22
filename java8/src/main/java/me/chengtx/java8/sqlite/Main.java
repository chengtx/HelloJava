package me.chengtx.java8.sqlite;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

import java.io.File;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 12/22/2014
 */
public class Main {
    public static void main(String[] args) throws SQLiteException {
        System.out.println("Hello Sqlite!");

        SQLiteConnection db = new SQLiteConnection(new File("database"));
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

    public static enum RESULT {
        OPTION1, OPTION2, OPTION3;
    }
}
