package com.example.rabbitmqcollege.annotation_send_receive.receiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ常用的Exchange Type有三种：direct、fanout、topic。
 * ①direct:把消息投递到那些binding key与routing key完全匹配的队列中。
 * ②fanout:把所有发送到该Exchange的消息投递到所有与它绑定的队列中。
 * ③topic:将消息路由到binding key与routing key模式匹配的队列中。
 *
 * @RabbitListener  bindings:绑定队列
 * @QueueBinding    exchange:配置交换器、key:路由键routing-key绑定队列和交换器、value:绑定队列的名称
 * @Queue           value:配置队列名称、autoDelete:是否是一个可删除的临时队列(队列在不使用时会被删除)
 * @Exchange        value:为交换器起个名称、type:指定具体的交换器类型
 */
@Component
public class Receiver {


    //===================================Direct模式(以及死信队列的用法)===================================================//

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "DirectExchangeDemo", durable = "true", type = ExchangeTypes.DIRECT),  //交换机默认就是持久化
            key = "computer",                                                 //声明路由key
            value = @Queue(value = "computerElectronics", durable = "true"),   //声明队列持久化
            arguments = {
                    @Argument(name = "x-message-ttl", value = "6000"),
                    @Argument(name = "x-dead-letter-exchange", value = "死信交换机"),
                    @Argument(name = "x-dead-letter-routing-key", value = "死信路由")
            }
            //这条消息如果在TTL设置的时间内没有被消费，则会成为“死信” [如果设置了队列的TTL属性，那么一旦消息过期，就会被队列丢弃]
    ))
    public void processComputer(String hello) {
        System.out.println("C-Receiver:" + hello);
    }

    /**
     * 死信队列处理死信
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "死信交换机", durable = "true"),
            key = "死信路由",
            value = @Queue(value = "死信队列", durable = "true")
    ))
    public void processDeadLetter(String hello) {
        System.out.println("死信队列处理死信:" + hello);
    }




    //=============================================Fanout模式==========================================================//
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "FanoutExchangeDemo", durable = "true", type = ExchangeTypes.FANOUT),//交换机默认就是持久化的
            key = "phone",                                                  //声明路由key
            value = @Queue(value = "phoneElectronics1", durable = "true")    //声明队列持久化
    ))
    public void processPhone1(String hello) {
        System.out.println("消费者1:" + hello);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "FanoutExchangeDemo", durable = "true", type = ExchangeTypes.FANOUT),//交换机默认就是持久化的
            key = "phone",                                                  //声明路由key
            value = @Queue(value = "phoneElectronics2", durable = "true")    //声明队列持久化
    ))
    public void processPhone2(String hello) {
        System.out.println("消费者2:" + hello);
    }



    //=============================================Topic模式==========================================================//
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "TopicExchangeDemo", type = ExchangeTypes.TOPIC), //声明交换机
            key = "*.girl.*",                                                  //声明路由key
            value = @Queue(value = "girlElectronics", durable = "true")    //声明队列持久化
    ))
    public void processGirl(String hello) {
        System.out.println("Girl:" + hello);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "TopicExchangeDemo", type = ExchangeTypes.TOPIC), //声明交换机
            key = "*.boy.*",                                                  //声明路由key
            value = @Queue(value = "boyElectronics", durable = "true")    //声明队列持久化
    ))
    public void processBoy(String hello) {
        System.out.println("Boy:" + hello);
    }


}
