package example.string;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.junit.Test;

/**
 * String 底层从原来的char[] ---> 修改为byte[]
 */
public class StringTest {



	//读取文件，并按照（行）遍历
	@Test
	public void testName3() throws Exception {
		FileInputStream fis = new FileInputStream("src/example/string/StringTest.java");
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		String string = new String(buffer);
		string.lines().forEach(System.out::println); //按照行分隔，并遍历
	}


	//获取字符串行数
	@Test
	public void getLineCount(){
		String s = "a\nb\nm";
		long count = s.lines().count(); //3
	}


	@Test
	public void testName2() throws Exception {
		String string = "Java";
		String string2 = string.repeat(5); //字符串重复5次，并拼接到一起返回
		System.out.println(string2);
	}

	@Test
	public void testName() throws Exception {
		String string = " \t  \r\n ";
		System.out.println(string.isBlank()); //true 判断字符串是否都是空白
		
		System.out.println("**************************");
		
		string = " \t  \r\n abc \t "; //最后的空格为一个汉字的空格
		String string2 = string.strip(); // 去除字符串首尾的空白，包括英文和其它语言中的空白字符
		System.out.println(string2); //abc
		System.out.println(string2.length()); //3
		
		String string3 = string.trim(); // 去除字符串首尾的空白字符，只能去除码值小于等于32的空白字符
		System.out.println(string3); //abc
		System.out.println(string3.length()); //6
		
		System.out.println("**************************");
		String string4 = string.stripLeading(); // 去除首部空白字符
		System.out.println(string4);
		System.out.println(string4.length());
		
		String string5 = string.stripTrailing(); // 去除尾部空白字符
		System.out.println(string5);
		System.out.println(string5.length());
	}
}	
