package com.example.basic.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Spring默认设定了日期类型转换器 2015/09/08 如果使用这种方式，那么我们不需要写类型转换器，spring会自动转换
public class MyConverter implements Converter<String, Date> {

    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    //s代表配置文件中的  <value>2020-09-08</value>
    //return 当把Date返回后，spring自动将日期赋值   基于接口回调
    @Override
    public Date convert(String s) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
