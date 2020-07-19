package com;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConverterUtils implements Converter {

    /**
     * 字符串转日期
     *
     * @param type
     * @param value
     * @return
     */
    @Override
    public Object convert(Class type, Object value) {
        // 输入的字符串，转换成日期类型，返回
        String dDate = (String) value;
        // 把字符串转成日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = sdf.parse(dDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
