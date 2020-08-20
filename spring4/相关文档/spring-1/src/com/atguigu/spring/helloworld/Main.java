package com.atguigu.spring.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setUser("Tom");
//		helloWorld.hello(); 
		
		//1. 创建 Spring 的 IOC 容器
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		
		//2. 从 IOC 容器中获取 bean 的实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld3");
		
		//根据类型来获取 bean 的实例: 要求在  IOC 容器中只有一个与之类型匹配的 bean, 若有多个则会抛出异常. 
		//一般情况下, 该方法可用, 因为一般情况下, 在一个 IOC 容器中一个类型对应的 bean 也只有一个. 
//		HelloWorld helloWorld1 = ctx.getBean(HelloWorld.class);
		
		//3. 使用 bean
		helloWorld.hello();
		
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		
		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);
		
		//4. 测试使用集合属性
		User user = (User) ctx.getBean("user5");
		System.out.println(user);
	}
	
}
