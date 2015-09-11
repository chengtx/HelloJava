package me.chengtx.java8;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 4/9/2015
 */
public class Mail {
    public static void main(String[] args) {
        int count = 0;
        long start = System.currentTimeMillis();
        for (; ; ) {
            if (count == 10) {
                break;
            }
            count++;
            Email email = new SimpleEmail();
            email.setHostName("10.32.127.155");
//        email.setHostName("10.62.43.187");
//        email.setHostName("10.32.127.106");
//        email.setHostName("10.8.85.148");
            email.setSmtpPort(25);
            try {
                email.setFrom("from@shngis.dctmlabs.com");
                email.setSubject("TestMail");
                email.setMsg("Thanks! ... :-)");
//            email.addTo("tingxian.cheng@emc.com");
                email.addTo("perfuser1@shngis.dctmlabs.com");
//            email.addTo("felix.g.hu@emc.com");
                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
