package com.example.rabbitmqcollege.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * RabbitMQ】三种Exchange模式——订阅、路由、通配符模式
 * .direct 默认的(一对一)
 * .topic  模糊匹配
 * .fanout 发布订阅模式
 * <p>
 * 演示RabbitMQ接收端
 * <p>
 * <p>
 * *       消息接收者
 * *
 * *         1、@RabbitListener bindings:绑定队列
 * *
 * *         2、@QueueBinding
 * *         value:绑定队列的名称、exchange:配置交换器、key:路由键routing-key绑定队列和交换器
 * *
 * *         3、@Queue value:配置队列名称、autoDelete:是否是一个可删除的临时队列
 * *
 * *         4、@Exchange value:为交换器起个名称、type:指定具体的交换器类型
 */
@Component
public class Receiver {

    /**
     * 电脑类接收消息
     * autoDelete的意思是：如果为true的话，那么没有消费者订阅的队列，会被自动删除。
     *
     * @param hello
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "myElectronics", durable = "true"),  //交换机默认就是持久化
            key = "computer",
            value = @Queue(value = "computerElectronics", durable = "true"),   //声明队列持久化
            arguments = @Argument(name = "x-message-ttl", value = "6000")
            //这条消息如果在TTL设置的时间内没有被消费，则会成为“死信” [如果设置了队列的TTL属性，那么一旦消息过期，就会被队列丢弃]
    ))
    public void processComputer(String hello) {
        System.out.println("C-Receiver:" + hello);
    }

    /**
     * 手机类接收消息
     * autoDelete的意思是：如果为true的话，那么没有消费者订阅的队列，会被自动删除。
     *
     * @param hello
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "myElectronics", durable = "true"),      //交换机默认就是持久化的
            key = "phone",
            value = @Queue(value = "phoneElectronics", durable = "true")    //声明队列持久化
    ))
    public void processPhone(String hello) {
        System.out.println("P-Receiver:" + hello);
    }

    /**
     * 什么时候用Queue，什么时候用Exchange？
     * 情况方法4方法5
     *
     * 两个的exchange是一样的
     *
     * key和value不一样，
     *
     * 发送方：
     *  @Test
     * public void send3(){
     *    amqpTemplate.convertAndSend("myOrder","computer","now "+new Date());
     * }
     *  @Test
     * public void send4(){
     *    amqpTemplate.convertAndSend("myOrder","fruit","now "+new Date());
     * }
     * 将信息发送到不同的队列
     *
     */
}
