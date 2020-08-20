package com.atguigu.spring.annotation;

import org.springframework.stereotype.Service;

@Service
public class UserDao {
	
	public void save(){
		System.out.println("保存新用户");
	}
	
}
