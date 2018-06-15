package com.atguigu.spring.ref;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;

import com.atguigu.spring.helloworld.Car;
import com.atguigu.spring.helloworld.User;

public class UserBean implements FactoryBean<User>{

	/**
	 * 返回的 bean 的实例
	 */
	@Override
	public User getObject() throws Exception {
		User user = new User();
		user.setUserName("abc");
		user.setWifeName("ABC");
		
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("ShangHai", "BuiKe", 180, 300000));
		cars.add(new Car("ShangHai", "CRUZE", 130, 150000));
		
		user.setCars(cars);
		return user;
	}

	/**
	 * 返回的 bean 的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	/**
	 * 返回的 bean 是否为单例的
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
