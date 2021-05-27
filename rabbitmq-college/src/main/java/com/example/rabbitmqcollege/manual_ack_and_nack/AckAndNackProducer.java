package com.example.rabbitmqcollege.manual_ack_and_nack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class AckAndNackProducer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_ack_exchange";
        String routingKey = "item.update";

        String msg = "this is ack msg";
        for (int i = 0; i < 5; i++) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("num", i);

            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .headers(headers)
                    .build();
            String tem = msg + ":" + i;
            channel.basicPublish(exchangeName, routingKey, true, properties, tem.getBytes());
            System.out.println("Send message:" + msg);
        }

        channel.close();
        connection.close();
    }
}
