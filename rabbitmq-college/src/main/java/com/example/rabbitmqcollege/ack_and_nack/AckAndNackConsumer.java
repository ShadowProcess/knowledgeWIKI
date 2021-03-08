package com.example.rabbitmqcollege.ack_and_nack;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AckAndNackConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_ack_exchange";
        String queueName = "test_ack_queue";
        String routingKey = "item.#";
        /**
         * 创建Exchange。指定名称和类型。topic类型允许模糊匹配。
         * 生产者发送给topic类型的Exchange消息时，routing_key一般是句点分割的多个单词，这些单词一般会是描述某种事物的关联词语。最多不超过256个字节。
         * 绑定键也是一样的格式。它支持两种特殊格式：* 表示一个单词，# 代表任意多个单词。如果不使用这两类符号，则Exchange的表现与类型direct一致。
         * 单独使用 # 时，会接收所有的消息，这与类型 fanout一致。
         */
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, false, false, false, null);

        //一般不用代码绑定，在管理员界面手动绑定
        channel.queueBind(queueName, exchangeName, routingKey);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("[X] Received' " + message + "'");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * 参考 api#
                 * void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException;
                 * void basicAck(long deliveryTag, boolean multiple) throws IOException;
                 */
                if ((Integer) properties.getHeaders().get("num") == 0) {
                    channel.basicNack(envelope.getDeliveryTag(), false, true); //第三个参数：是否重回队列
                } else {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        //6. 设置 Channel 消费者绑定队列
        channel.basicConsume(queueName, false, consumer);
    }
}

/**
 * 我们此处只关心消费端输出，可以看到第 0 条消费失败重新回到队列尾部消费。
 *
 * Copy
 *  [x] Received 'this is ack msg:1'
 *  [x] Received 'this is ack msg:2'
 *  [x] Received 'this is ack msg:3'
 *  [x] Received 'this is ack msg:4'
 *  [x] Received 'this is ack msg:0'
 *  [x] Received 'this is ack msg:0'
 *  [x] Received 'this is ack msg:0'
 *  [x] Received 'this is ack msg:0'
 *  [x] Received 'this is ack msg:0'
 *
 *
 *
 */
