我们进行正常声明交换机、队列、绑定，只不过我们需要在队列加上一个参数即可：
arguments.put(" x-dead-letter-exchange"，"你声明的死信队列名称");
这样消息在过期、requeue、 队列在达到最大长度时，消息就可以直接路由到死信队列！


死信队列：没有被及时消费的消息存放的队列，消息没有被及时消费有以下几点原因：
```
a.消息被拒绝（basic.reject/ basic.nack）并且不再重新投递 requeue=false
b.TTL(time-to-live) 消息超时未消费
c.达到最大队列长度
```

消息变成死信后，会被重新投递（publish）到另一个交换机上（Exchange）,
这个交换机往往被称为DLX(dead-letter-exchange)“死信交换机”，
然后交换机根据绑定规则转发到对应的队列上，监听该队列就可以被重新消费。


```java
public class DeadLetter{
    @RabbitListener(bindings = @QueueBinding(
                exchange = @Exchange(value = "myElectronics", durable = "true"),  //交换机默认就是持久化
                key = "computer",                                                 //声明路由key
                value = @Queue(value = "computerElectronics", durable = "true"),  //声明队列持久化
                arguments = {
                        //队列消息如果在TTL设置的时间内没有被消费，则会成为“死信” [如果设置了队列的TTL属性，那么一旦消息过期，就会被队列丢弃]
                        @Argument(name = "x-message-ttl", value = "6000"),
                        
                        //指定死信交换机和死信路由
                        @Argument(name = "x-dead-letter-exchange", value = "死信交换机"),
                        @Argument(name = "x-dead-letter-routing-key",value = "死信路由")
                }
        ))
    public void processComputer(String hello) {
        System.out.println("C-Receiver:" + hello);
    }
    /**
     * 死信队列处理死信
     * @param hello
     */
    @RabbitListener(bindings = @QueueBinding(
                exchange = @Exchange(value = "死信交换机", durable = "true"),       
                key = "死信路由",                                                
                value = @Queue(value = "死信队列", durable = "true")  
        ))
    public void processDeadLetter(String hello) {
        System.out.println("处理死信:" + hello);
    }

}
```