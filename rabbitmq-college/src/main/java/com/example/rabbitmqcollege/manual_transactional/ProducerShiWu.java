package com.example.rabbitmqcollege.manual_transactional;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMq事务确认模式：
 *
 * 主要是生产者产生消息的时候发生异常进行回滚
 */

public class ProducerShiWu {

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory.newConnection();
    }

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = getConnection();
            channel = connection.createChannel();
            /**
             * 声明一个队列。
             * 参数一：队列名称
             * 参数二：是否持久化
             * 参数三：是否排外  如果排外则这个队列只允许有一个消费者
             * 参数四：是否自动删除队列，如果为true表示没有消息也没有消费者连接自动删除队列
             * 参数五：队列的附加属性
             * 注意：
             * 1.声明队列时，如果已经存在则放弃声明，如果不存在则会声明一个新队列；
             * 2.队列名可以任意取值，但需要与消息接收者一致。
             * 3.下面的代码可有可无，一定在发送消息前确认队列名称已经存在RabbitMQ中，否则消息会发送失败。
             */
            channel.queueDeclare("myQueue", true, false, false, null);

            // 启动事务,必须用txCommit()或者txRollback()回滚
            channel.txSelect();

            // 假设这里处理业务逻辑
            String message = "hello,message！";
            /**
             * 发送消息到MQ
             * 参数一：交换机名称，为""表示不用交换机
             * 参数二:为队列名称或者routingKey.当指定了交换机就是routingKey
             * 参数三：消息的属性信息
             * 参数四：消息内容的字节数组
             */
            channel.basicPublish("", "myQueue", null, message.getBytes());

            // 提交事务
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    //TODO 回滚。如果没有异常会提交事务，此时回滚无影响
                    channel.txRollback();
                    channel.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ignore) {
                // ignore
            }
        }
    }
}
