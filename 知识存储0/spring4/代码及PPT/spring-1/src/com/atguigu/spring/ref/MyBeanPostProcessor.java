package com.atguigu.spring.ref;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.atguigu.spring.helloworld.User;

public class MyBeanPostProcessor implements BeanPostProcessor {

	//该方法在 init 方法之后被调用
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg1.equals("boy")){
			System.out.println("postProcessAfterInitialization..." + arg0 + "," + arg1);
			User user = (User) arg0;
			user.setUserName("李大齐");
		}
		return arg0;
	}

	//该方法在 init 方法之前被调用
	//可以工作返回的对象来决定最终返回给 getBean 方法的对象是哪一个, 属性值是什么
	/**
	 * @param arg0: 实际要返回的对象
	 * @param arg1: bean 的 id 值
	 */
	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg1.equals("boy"))
			System.out.println("postProcessBeforeInitialization..." + arg0 + "," + arg1);
		return arg0;
	}

}
