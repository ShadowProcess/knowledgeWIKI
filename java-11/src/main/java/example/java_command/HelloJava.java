package example.java_command;

/**
 * 以前：
 * javac xxx.java 先编译
 * java xxx 再运行
 *
 * 现在：
 * java xx.java 直接执行java程序
 *
 * 注意点：
 * 1.执行源文件中的第一个类，第一个类必须包含主方法
 * 2.并且不可以使用别源文件中的自定义类，本文件中的自定义类是可以使用的
 */

public class HelloJava {
	
	public static void main(String[] args) {
		System.out.println("Hello Java Simple");
		Teacher t = new Teacher();
	}
}

class Teacher {
	private String name;
	private int age;
}

class Test2 {
	public static void main(String[] args) {
		System.out.println("Test2. main");
	}
}

