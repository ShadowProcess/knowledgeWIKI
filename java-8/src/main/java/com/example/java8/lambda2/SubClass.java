package com.example.java8.lambda2;

/**
 * 1.父类和接口具有同名方法，类优先原则
 * 2.两个父接口具有同名方法，你要指明那么方法
 */

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface{

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

}
