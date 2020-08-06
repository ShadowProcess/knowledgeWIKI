package com.example.java.interface_new;

import com.example.java.interface_new.MyFun;
import com.example.java.interface_new.MyInterface;

/**
 * 1.父类和接口具有同名方法，类优先原则
 * 2.两个父接口具有同名方法，你要指明那么方法
 */

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface {

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

}
