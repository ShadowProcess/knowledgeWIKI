package com.example.activemqcollege.point_to_point;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class ActiveMqTest {


    //点对点方式发送消息
    @Test
    public void test1() throws JMSException {

        //连接到ActiveMq
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.131:61616");
        //从factory中获取连接
        Connection connection = factory.createConnection();
        //连接到ActiveMq
        connection.start();

        /**
         * 第一个参数表示是否使用事务，如果使用事务(在分布式中使用事务)，那么第二个参数无效
         * 不使用事务，第二个参数有效，表示应答方式 ：自动应答和手动应答
         */
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建消息发送的目的地Destination
        Queue queue = session.createQueue("myQueue");
        //创建消息的发送者，生产者
        MessageProducer producer = session.createProducer(queue);
        //创建发送的内容
        TextMessage textMessage = session.createTextMessage("hello,你好");

        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

    //点对点接收消息 [可以接收到其没有上线前的消息]
    @Test
    public void test2() throws JMSException, IOException {
        //连接到ActiveMq
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.131:61616");
        //从factory中获取连接
        Connection connection = factory.createConnection();
        //连接到ActiveMq
        connection.start();

        /**
         * 第一个参数表示是否使用事务，如果使用事务(在分布式中使用事务)，那么第二个参数无效
         * 不使用事务，第二个参数有效，表示应答方式 ：自动应答和手动应答
         */
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建接收消息的目的地  名字要与发送的一致
        Queue queue = session.createQueue("myQueue");
        //创建消息接收者
        MessageConsumer consumer = session.createConsumer(queue);

        //接收消息： 消息什么时候过来不知道,所以需要一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage text = (TextMessage) message;
                try {
                    String s = text.getText();
                    System.out.println(s);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read(); //该方法是一个阻塞方法，避免方法直接结束
        consumer.close();
        session.close();
        connection.close();
    }
}
