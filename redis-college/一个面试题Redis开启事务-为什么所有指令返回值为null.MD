注意：redis如果开启事务,如果事务中有些redis操作有返回值,那么事务中的redis操作返回值将为null
原因：redis操作在事务之间的时候，全部返回null
总结：使用redis事务需谨慎，可以使用，但是在使用的过程中获取数据都是获取不到的

```[如下代码,value是null,因为是在事务中进行的操作]
redisTemplate.setEnableTransactionSupport(true);
redisTemplate.multi();
String value = redisTemplate.opsForValue().get("key");
redisTemplate.delete("key");
redisTemplate.exec();
``` 
 
Redis事务
　　Redis事务以MULTI开始，中间添加多种命令，[这些命令不会立即执行，而是被放入到一个队列中，当执行EXEC时，队列中的所有命令被依次执行]。
　　当命令放在MULTI中，但还未执行EXEC时，每个命令返回值为QUEUED，[从源码中get方法可以看出,当get时,判断是否为队列,如果是队列那么返回null]
   Redis事务将多个命令使用MULTI包括起来，调用EXEC一起执行，减少与客户端之间通信往返次数，提升执行多个命令时的性能
    
>在2.6.5版本之前，执行MULTI之后，EXEC之前发生错误时，当执行EXEC命令，redis忽略掉错误命令，执行正确的命令。
　　在EXEC执行之后发生错误，其他正确的命令会被执行，redis不会回滚

>从2.6.5版本开始，在执行EXEC之前，redis命令在加入队列时，如果出现错误（一般为语法错误），则执行EXEC时，该事务不会被执行，并自动丢弃


###Redis不支持事务回滚，官网给出了两点理由
1、redis命令只有两个错误会出现，一个为错误的语法结构，一个为对数据类型使用错误的方法处理，如上例中使用LPUSH操作字符串类型的myKey。这两个问题在开发环境自测的时候就能够发现。

2、redis内部的结构简单，而且速度更快，因为它不需要回滚功能。
　　DISCARD命令可用于终止当前事务并丢弃，也即是清空队列中的命令。该命令必须应用在MULTI命令中    