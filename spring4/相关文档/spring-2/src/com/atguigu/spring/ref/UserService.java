package com.atguigu.spring.ref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void addNew(){
		System.out.println("addNew...");
		userDao.save();
	}
	
}
