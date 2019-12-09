package com.atguigu.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 如何使用外部Servlet容器：外面安装tomcat--应用war包的方式打包
 *
 * 1.必须创建一个war项目 [利用idea创建好目录结构]
 * 2.将嵌入式的tomcat指定为provided
 * <dependency>
 * 			<groupId>org.springframework.boot</groupId>
 * 			<artifactId>spring-boot-starter-tomcat</artifactId>
 * 			<scope>provided</scope>
 * 	</dependency>
 * 	​ scope=provided的情况，则可以认为这个provided是目标容器已经provide这个jar。
 * 	换句话说，它只影响到编译，测试阶段。在编译测试阶段，我们需要这个app对应的jar包在classpath中，
 * 	而在运行阶段，假定目标的容器（比如我们这里的tomcat容器）已经提供了这个jar包，所以无需我们打包对应的jar包了。
 * 	在运行阶段，app可以直接使用容器提供的jar。
 *
 * 3.必须编写一个SpringBootServletInitializer的子类，并调用configure方法   [传入springboot应用的主程序]
 *
 *
 * 原理：
 * jar包：执行springboot主类的main方法，启动IOC容器，创建嵌入式的Servlet容器；
 *
 * war包：启动服务器，服务器启动Springboot应用【SpringBootServletInitializer】，启动IOC容器；
 *
 * servlet3.0有一个规范：
 * 1)服务器启动(web应用启动)会创建当前web应用里面每一个jar包里边的ServletContainerInitializer实例
 * 2)ServletContainerInitializer的实现放在jar包的META-INF/services文件夹，
 * 		有一个名为javax.servlet.ServletContainerInitializer的文件，内容就是ServletContainerInitializer的实现类的全类名
 * 3)还可以使用@HandlerTypes，在应用启动的时候加载我们感兴趣的类
 *
 *
 * 流程：
 * 1) 启动tomcat
 *
 * 2)C:\Users\Alex\.m2\repository\org\springframework\spring-web\5.2.2.RELEASE\spring-web-5.2.2.RELEASE.jar!\META-INF\services\javax.servlet.ServletContainerInitializer
 * spring的web模块里边就有和这个文件
 *
 * 3)SpringServletContainerInitializer将@HandlesTypes(WebApplicationInitializer.class)标注的所有这个类型的
 * 类都传入到onStartup方法的Set<Class<?>;为这些WebApplicationInitializer类型的类创建实例
 *
 * 4)每一个WebApplicationInitializer都调用自己的onStartup方法
 *
 * 5）相当于SpringBootServletInitializer[该类是WebApplicationInitializer的实现类]的类会被创建对象，
 * 并执行onStartup方法
 *
 * 6) spring应用就启动了，创建IOC容器
 */


public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//传入springboot应用的主程序
		return application.sources(SpringBoot04WebJspApplication.class);
	}

}
