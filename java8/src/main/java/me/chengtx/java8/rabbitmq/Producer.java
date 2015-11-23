package me.chengtx.java8.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 9/11/2015
 */
public class Producer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {

        try {

            // connect to the rabbit server
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("10.32.127.132");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // send message
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World, Tingxian!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            // close connection
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException ex) {

        } finally {

        }

    }
}
