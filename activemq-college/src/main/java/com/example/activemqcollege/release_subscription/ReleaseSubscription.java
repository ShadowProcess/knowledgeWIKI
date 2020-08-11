package com.example.activemqcollege.release_subscription;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class ReleaseSubscription {


    //发布订阅  一个消息可以被多个接收者接收到
    @Test
    public void testTopicProducer() throws JMSException {
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

        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);//消息生产者

        TextMessage message = session.createTextMessage("topic hello");
        producer.send(message);

        producer.close();
        session.close();
        connection.close();
    }


    //发布订阅  接收者[不能接收到其没有上线前的消息]
    @Test
    public void testTopicConsumer() throws JMSException, IOException {
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

        Topic topic = session.createTopic("test-topic");

        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage text = (TextMessage) message;

                String s = null;
                try {
                    s = text.getText();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println(s);
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
