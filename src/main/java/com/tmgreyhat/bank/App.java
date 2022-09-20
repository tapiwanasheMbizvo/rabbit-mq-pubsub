package com.tmgreyhat.bank;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class App {

    private final  static  String QUEUE_NAME = "JSON_MSG_IN";

    public static void main( String[] args ) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();


        Map<String , String> map = new HashMap<>();

        map.put("account_number", "5965585");
        map.put("transaction_amount", "782.5");
        map.put("transaction_type", "d");


        String JSON_TEXT = JSONValue.toJSONString(map);
        factory.setHost("localhost");
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        String message = "product details";
        channel.basicPublish("", QUEUE_NAME, null, JSON_TEXT.getBytes());
        channel.close();
        connection.close();
        System.out.println( "Hello World!" );

        for (int i = 0 ; i < 1000; i ++){


        }
    }
}
