package com.atguigu.spring.ref;

public class Dao {

	private String dataSource = "dbcp";
	
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(){
		System.out.println("save by " + dataSource);
	}
	
	public Dao() {
		System.out.println("Dao's Constructor...");
	}
	
}
