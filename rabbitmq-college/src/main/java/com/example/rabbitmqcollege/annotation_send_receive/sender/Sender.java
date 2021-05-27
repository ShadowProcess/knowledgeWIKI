package com.example.rabbitmqcollege.annotation_send_receive.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;


    @Test
    public void sendDirect() {
        String content = "发送信息到direct模式交换机，当前时间：" + new Date();
        //参数1:交换机     参数2:路由key    参数3:发送的数据
        this.rabbitmqTemplate.convertAndSend("DirectExchangeDemo", "computer", content);
    }


    @Test
    public void sendFanout() {
        String content = "发送信息到fanout模式交换机，当前时间：" + new Date();
        //参数1:交换机     参数2:路由key    参数3:发送的数据
        this.rabbitmqTemplate.convertAndSend("FanoutExchangeDemo", "phone", content);
    }


    @Test
    public void sendTopic() {
        String content = "发送信息到fanout模式交换机，当前时间：" + new Date();
        //参数1:交换机     参数2:路由key    参数3:发送的数据
        this.rabbitmqTemplate.convertAndSend("TopicExchangeDemo", "1.girl.2", content);
        this.rabbitmqTemplate.convertAndSend("TopicExchangeDemo", "1.boy.2", content);
    }





    //发送持久化消息
    public void sendMessage(final String exchange, final String routingKey, final String content)
            throws AmqpException {
        /**
         * RabbitMQ消息持久化
         * 使用convertAndSend方式发送消息，消息默认就是持久化的.
         * new MessageProperties() --> DEFAULT_DELIVERY_MODE = MessageDeliveryMode.PERSISTENT --> deliveryMode = 2;
         */
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
        messageProperties.setDeliveryMode(MessageProperties.DEFAULT_DELIVERY_MODE); //设置消息持久化
        Message message = new Message(content.getBytes(), messageProperties);

        //三个参数依次是交换机，路由key,内容
        this.rabbitmqTemplate.send(exchange, routingKey, message);
    }
}
