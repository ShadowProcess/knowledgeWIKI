package com.atguigu.spring.ref;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		//1. ´´½¨ IOC ÈÝÆ÷
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		
		UserAction userAction = (UserAction) ctx.getBean("userAction");
		userAction.execute();
	}
	
}
