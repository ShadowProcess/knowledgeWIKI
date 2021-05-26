SpringBoot自动配置原理：
```
@SpringBootApplication注解，点进去有一个@EnableAutoConfiguration

@EnableAutoConfiguration注解，点击去有一个@Import(AutoConfigurationImportSelector.class)

而AutoConfigurationImportSelector的getCandidateConfigurations()方法，会去META-INF/spring.factories加载你配置的类
```



Java SPI 规范
要使用Java SPI，需要遵循如下约定：
~~~
1、当服务提供者提供了接口的一种具体实现后，在jar包的META-INF/services目录下创建一个以“接口全限定名”为命名的文件，内容为实现类的全限定名；
2、接口实现类所在的jar包放在主程序的classpath中；
3、主程序通过java.util.ServiceLoder动态装载实现模块，它通过扫描META-INF/services目录下的配置文件找到实现类的全限定名，把类加载到JVM；
4、SPI的实现类必须携带一个不带参数的构造方法；  
————————————————
