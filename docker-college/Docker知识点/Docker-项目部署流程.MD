###### 1.Docker开启远程访问
```shell script
[root@alex docker]# vim /usr/lib/systemd/system/docker.service
#修改ExecStart这行
ExecStart=/usr/bin/dockerd  -H tcp://0.0.0.0:2375  -H unix:///var/run/docker.sock
#重新加载配置文件
[root@alex docker]# systemctl daemon-reload    
#重启服务
[root@alex docker]# systemctl restart docker.service 
#查看端口是否开启
[root@alex docker]# netstat -nlpt
#直接curl看是否生效
[root@alex docker]# curl http://127.0.0.1:2375/info
```

###### 2.Intellij-IDEA安装Docker插件

###### 3.在IDEA-Setting中配置Docker

###### 4.编写项目代码，并在pom文件中添加properties，添加plugin
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>docker-college</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>docker-college</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>

        <!--这就是dockerHub的用户名-->
        <docker.image.prefix>306881085</docker.image.prefix>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!--用来做maven和docker一键部署功能-->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.0.0</version>

                <configuration>
                    <!--镜像名称  306881085/docker-demo-->
                    <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                    <!--指定标签-->
                    <imageTags>
                        <imageTag>latest</imageTag>
                    </imageTags>
                    <!--基础镜像 jdk1.8-->
                    <baseImage>java</baseImage>
                    <!--制作者提供本人信息-->
                    <maintainer>alex jiaHangSocket@163.com</maintainer>
                    <!--切换到/ROOT目录-->
                    <workdir>/ROOT</workdir>
                    <cmd>["java","-version"]</cmd>
                    <entryPoint>["java","-jar","${project.build.finalName}.jar"]</entryPoint>

                    <!--指定Dockerfile路径-->
                    <!--<dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>-->

                    <!--指定远程 docker api地址-->
                    <dockerHost>http://106.14.8.234:2375</dockerHost>
                    <!--这里是复制jar包到docker容器指定目录配置-->
                    <resources>
                        <resource>
                            <targetPath>/ROOT</targetPath>
                            <!--用于指定需要复制的根目录，${project.build.directory} 表示target目录-->
                            <directory>${project.build.directory}</directory>
                            <!--用于指定需要复制的文件。${project.build.finalName}.jar指定的是打包后的jar文件-->
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
            <!-- -->
        </plugins>
    </build>

</project>

```

###### 5.在项目根目录创建Dockerfile文件，内容如下：
```
#指定基础镜像，在其上进行定制
FROM java:8

#维护者信息
MAINTAINER alex <jiahangSocket@gmail.com>

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp

#复制上下文目录下的target/docker-college-0.0.1-SNAPSHOT.jar 到容器里
COPY target/docker-college-0.0.1-SNAPSHOT.jar docker-college-0.0.1-SNAPSHOT.jar

#bash方式执行，使docker-college-0.0.1-SNAPSHOT.jar可访问
#RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
RUN bash -c "touch /docker-college-0.0.1-SNAPSHOT.jar"

#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 8080

#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java","-jar","docker-college-0.0.1-SNAPSHOT.jar"]
```

###### 6.执行Dockerfile文件就构建了镜像