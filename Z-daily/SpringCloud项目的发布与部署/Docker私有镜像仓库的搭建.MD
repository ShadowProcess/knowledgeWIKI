启动私有 Registry
启动一个私有仓库也非常简单，在服务器上执行命令
```shell script
docker run -d -p 5000:5000 --name="docker-registry" --restart=always -v /root/docker/registry/:/var/lib/registry/ registry 
```
即后台启动 registry 镜像构建出来的容器，并命名为 docker-registry，端口号映射为 5000 到 5000。
--restart=always 代表当容器因为某些原因停止时，不管退出码是什么都自动重启。除了 always 还有 on-failure 代表只有退出码不为 0 时才重启，并且接受重启次数参数：--restart=on-failture:5
-v 指定将宿主机的 /root/docker/registry/ 目录挂载到容器的 /var/lib/registry/ 目录。这样我们不用进入容器，在宿主机上就能访问到容器内我们感兴趣的目录了。

为什么是 /var/lib/registry/ 目录?
仓库默认存放镜像等信息在容器的 /var/lib/registry/docker 目录下，可以进入该目录查看已上传镜像信息。

#===============================================================================================================================================================================
如果不想用Docker自带的Registry，那么也可以用第三方的私有镜像管理软件，Harbor一个带管理界面的软件
- 下载并安装
- 使用浏览器访问: ip/harbor/users进入管理界面

#例如我们想推一个镜像到私有镜像服务器上去可以这么操作：
```shell script
root@runoob:~# docker images  
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
redis                   5.0.9               5120d23bad51        10 months ago       98.3MB
mysql                   8.0.13              102816b1ee7d        2 years ago         486MB
java                    8                   d23bdf5b1b1b        4 years ago         643MB
```

```shell script
1.打tag
docker   tag    java:8    127.0.0.1:5000/java:v5 

2.上传镜像到私库
docker    push    127.0.0.1:5000/java:v5
```



push如果遇到这个错误“server gave HTTP response to HTTPS client”
在”/etc/docker/“目录下，创建”daemon.json“文件（如果已有编辑即可）。在文件中写入：(ip为你要推送的仓库的ip地址)
```
{ "insecure-registries":["ip:5000"] }
```
保存退出后，重启docker即可。
```shell script
systemctl restart docker
```

