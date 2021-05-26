Redis的list可以用来实现消息队列，
key用来表示队列名称，客户端向对应的key代表的队列发消息。value代表队列中的消息。

发消息采用rpush，lpush实现，
取消息采用rpop或lpop实现。
```
1.lpush
从左往右添加元素
在key 对应 list的头部添加字符串元素

2.rpush
从右到左添加元素
在key 对应 list 的尾部添加字符串元素

3.lpop
弹出最左边的元素

4.rpop
弹出最右边的元素
```