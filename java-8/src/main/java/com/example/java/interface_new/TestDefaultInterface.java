package com.example.java.interface_new;

public class TestDefaultInterface {
	
	public static void main(String[] args) {
		SubClass sc = new SubClass();
		System.out.println(sc.getName());
		
		MyInterface.show(); //该方法来自哪里呢
	}

}
