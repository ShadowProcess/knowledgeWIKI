package com.atguigu.spring.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		//1. 创建 IOC 容器
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2. 从 IOC 容器中获取 bean 实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		
		//3. 调用 bean 的方法
		helloWorld.hello();
		
		HelloWorld helloWorld2 = (HelloWorld) ctx.getBean("helloWorld");
		System.out.println(helloWorld == helloWorld2);
		
		//4. 关闭容器
		ctx.close();
	}
	
}
