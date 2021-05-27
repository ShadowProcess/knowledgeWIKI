package com.example.rabbitmqcollege.manual_transactional;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConsumerShiWu {

    private final static String QUEUE_NAME = "myQueue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /***
         * 下面代码的作用是让程序一直运行，除非CTRL+C或者自己主动中断，为消息提供异步接收支持
         * 因为生产者会异步地向我们发送消息，所以我们以对象的形式提供一个回调，它将缓冲消息，直到我们准备好使用它们
         */
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };


        //设置回调
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("接收到的消息是：" + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}
