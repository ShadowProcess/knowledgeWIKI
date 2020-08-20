package com.atguigu.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsreService {
	
	@Autowired
	private UserDao userDao;
	
	public void addNew(){
		System.out.println("添加新用户");
		userDao.save();
	}
	
}
