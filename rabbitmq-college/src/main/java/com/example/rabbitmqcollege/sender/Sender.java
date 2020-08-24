package com.example.rabbitmqcollege.sender;

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


/**
 * @author Alex
 * <p>
 * 演示RabbitMQ发送端
 *
 * RabbitMQ】三种Exchange模式——订阅、路由、通配符模式
 * .direct 默认的(一对一)
 * .topic  模糊匹配
 * .fanout 发布订阅模式
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;


    @Test
    public void sendComputer() {
        for (int i = 0; i < 30; i++) {
            String content = "发送信息到电脑队列，当前时间：" + new Date();
            this.rabbitmqTemplate.convertAndSend("myElectronics", "computer", content);
        }
    }

    /**
     * RabbitMQ消息持久化
     * 使用convertAndSend方式发送消息，消息默认就是持久化的.
     * new MessageProperties() --> DEFAULT_DELIVERY_MODE = MessageDeliveryMode.PERSISTENT --> deliveryMode = 2;
     */

    @Test
    public void sendPhone() {
        for (int i = 0; i < 30; i++) {
            String content = "发送信息到手机队列，当前时间：" + new Date();
            this.rabbitmqTemplate.convertAndSend("myElectronics", "phone", content);
        }
    }


    //发送持久化消息
    public void sendMessage(final String exchange, final String routingKey, final String content)
            throws AmqpException {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
        messageProperties.setDeliveryMode(MessageProperties.DEFAULT_DELIVERY_MODE); //设置消息持久化
        Message message = new Message(content.getBytes(), messageProperties);

        //三个参数依次是交换机，路由key,内容
        this.rabbitmqTemplate.send(exchange, routingKey, message);
    }
}
