package com.atguigu.spring.annotation.generic;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	@Autowired
	private BaseDao<T> dao;
	
	public void addNew(T entity){
		System.out.println("addNew by " + dao);
		dao.save(entity);
	}
	
}
