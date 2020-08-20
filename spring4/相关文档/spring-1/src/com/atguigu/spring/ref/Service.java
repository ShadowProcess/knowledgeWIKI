package com.atguigu.spring.ref;

public class Service {

	private Dao dao;
	
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public Dao getDao() {
		return dao;
	}
	
	public void save(){
		System.out.println("Service's save");
		dao.save();
	}
	
}
