package com.example.rabbitmqcollege.manual_confirm_and_return;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 但是在某些情况下，如果我们在发送消息的时候，当前的 exchange不存在或者指定的路由 key路由不到，
 * 这个时候如果我们需要监听这种不可达的消息, Confirm监听不到, 就要使用 ReturnListener !
 */
public class ReturnListeningProducer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_return_exchange";
        String routingKey = "item.update";
        String errRoutingKey = "error.update";

        //指定消息的投递模式：confirm 确认模式
        channel.confirmSelect();

        //发送
        for (int i = 0; i < 3; i++) {
            String msg = "this is return——listening msg ";
            //@param mandatory 设置为 true 路由不可达的消息会被监听到，不会被自动删除
            if (i == 0) {
                channel.basicPublish(exchangeName, errRoutingKey, true, null, msg.getBytes());
            } else {
                channel.basicPublish(exchangeName, routingKey, true, null, msg.getBytes());
            }
            System.out.println("Send message : " + msg);
        }


        //添加一个确认监听， 这里就不关闭连接了，为了能保证能收到监听消息
        channel.addConfirmListener(new ConfirmListener() {
            /**
             * 返回成功的回调函数
             */
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("success ack");
            }

            /**
             * 返回失败的回调函数
             */
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("defeat ack");
            }
        });


        /**
         * 但是在某些情况下，如果我们在发送消息的时候，当前的 exchange不存在或者指定的路由 key路由不到，
         * 这个时候如果我们需要监听这种不可达的消息, Confirm监听不到, 就要使用 ReturnListener !
         */
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("return relyCode: " + replyCode);
                System.out.println("return replyText: " + replyText);
                System.out.println("return exchange: " + exchange);
                System.out.println("return routingKey: " + routingKey);
                System.out.println("return properties: " + properties);
                System.out.println("return body: " + new String(body));
            }
        });
    }
}
