搜索镜像
#docker search redis

拉取镜像
#docker pull redis:5.0.9

 
#创建redis容器（指定配置文件）
```
docker run \
    -d \
    -p 6379:6379 \
    -v /usr/redis/redis.conf:/etc/redis/redis.conf \
    -v /usr/redis/data:/data \
    --name redis5 redis:5.0.9 redis-server /etc/redis/redis.conf
```

> 参数说明：
-d                                     //容器以后台模式运行，以守护进程的方式启动容器
-p 6379:6379　　                        //容器redis端口6379映射宿主主机6379
--restart always                       //开机启动
--privileged=true                      //提升容器内权限
--name redis5　　                       //容器名字为redis,可以随便取
-v /usr/local/redis/conf:/etc/redis    //docker镜像redis默认无配置文件，在宿主主机/usr/local/redis/conf下创建redis.conf配置文件，会将宿主机的配置文件复制到docker中
-v /root/redis/redis01/data:/data　　   //容器/data映射到宿主机 /usr/local/redis/data下
redis-server /etc/redis/redis.conf     //redis将以 /etc/redis/redis.conf为配置文件启动
--requirepass "123456"                 //redis密码设置 
--appendonly yes　　                    //开启redis的AOF持久化，默认为false，不持久化


>
