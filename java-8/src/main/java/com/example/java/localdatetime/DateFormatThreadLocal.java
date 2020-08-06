package com.example.java.localdatetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 以前使用ThreadLocal保证该变量多线程安全问题
 */
public class DateFormatThreadLocal {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue(){
			return new SimpleDateFormat("yyyyMMdd");
		}
		
	};
	public static final Date convert(String source) throws ParseException{
		return df.get().parse(source);
	}

}
