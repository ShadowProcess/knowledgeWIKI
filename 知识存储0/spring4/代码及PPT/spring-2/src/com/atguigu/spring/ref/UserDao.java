package com.atguigu.spring.ref;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	public void save(){
		System.out.println("UserDao's save...");
	}
	
}
