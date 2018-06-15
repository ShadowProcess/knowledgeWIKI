package com.atguigu.spring.ref;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.helloworld.User;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-auto.xml");
		Action action = ctx.getBean(Action.class);
		
		action.execute();
		
		//测试 bean 的作用域
		Dao dao1 = (Dao) ctx.getBean("dao2");
		Dao dao2 = (Dao) ctx.getBean("dao2");
		
		System.out.println(dao1 == dao2);
		
		//测试使用外部属性文件
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
		
		//测试 spEL
		User boy = (User) ctx.getBean("boy");
		System.out.println(boy.getUserName() + ":" + boy.getWifeName());
		
//		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat dateFormat = (DateFormat) ctx.getBean("dateFormat");
		System.out.println(dateFormat.format(new Date()));
		
		Date date = (Date) ctx.getBean("datetime");
		System.out.println(date);
		
		User user = (User) ctx.getBean("user");
		System.out.println(user);
		
		ctx.close();
	}
	
}
