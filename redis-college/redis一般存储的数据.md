#用户信息
使用Hash存储
>key(用户id)  hashKey(用户年龄) value(用户年龄值)
>key(用户id)  hashKey(用户性别) value(用户性别值)
>key(用户id)  hashKey(用户住址) value(用户住址值)

#菜单数据
使用String存储
>key 门店店号
>value 门店菜单json数据


#排名相关业务
使用ZSet存储
>key(此次排名业务id) value(张三) scope(张三分数)
>key(此次排名业务id) value(李四) scope(李四分数)
>key(此次排名业务id) value(王五) scope(王五分数)
>key(此次排名业务id) value(赵六) scope(赵六分数)

#计数器
#一个手机号一天限制发送5条短信、一个接口一分钟限制多少请求、一个接口一天限制调用多少次等等。
#使用Redis的Incr自增命令可以轻松实现以上需求。以一个接口一天限制调用次数为例：
>redisTemplate.opsForValue().set("zhangsan",0);
>redisTemplate.opsForValue().increment("zhangsan", 1);
>redisTemplate.opsForValue().get("zhangsan")
>返回结果为1




