#指定基础镜像，在其上进行定制
FROM java:8

#维护者信息
MAINTAINER alex <jiahangSocket@gmail.com>

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
#VOLUME /tmp

#复制上下文目录下的target/docker-college-0.0.1-SNAPSHOT.jar 到容器根目录里
COPY target/docker-college-0.0.1-SNAPSHOT.jar /docker-college-0.0.1-SNAPSHOT.jar

#bash方式执行，使docker-college-0.0.1-SNAPSHOT.jar可访问
#RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
RUN bash -c "touch /docker-college-0.0.1-SNAPSHOT.jar"

#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 8080

#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java","-jar","docker-college-0.0.1-SNAPSHOT.jar"]