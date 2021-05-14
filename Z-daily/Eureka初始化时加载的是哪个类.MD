```
EurekaServerAutoConfiguration配置类是通过SpringBoot自动装配机制进行加载的，
在eureka-server包下META-INF/spring.factories文件中有其相关配置，
如下所示，在SpringBoot进行初始化的时候，会加载EurekaServerAutoConfiguration配置类。

org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
	org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration	
```
