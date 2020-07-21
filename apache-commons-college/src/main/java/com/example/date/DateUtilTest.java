package com.example.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtilTest {

    @Test
    public void _32(){
        Date date = new Date();
        /*
         * 由于Apache的DateUtils和DateFormatUtils并没有Joda强大,
         *  所以在这里只作简单的示例
         */
        //增加一天
        Date date1 = DateUtils.addDays(date, 1);

        //减少一年
        Date date2 = DateUtils.addYears(date, -1);

        //格式化时间 第三参数为国际化,表示按美国时间显示
        DateFormatUtils.format(date,"yyyy-MM-dd", Locale.CHINESE);
    }

    @Test
    public void _2() throws Exception{
        Date date1 = DateUtils.parseDate("2018-05-27 16:32:51", "yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtils.parseDate("2018-05-27 16:32:51","yyyy-MM-dd HH:mm:ss");
        DateUtils.addDays(date2,1);// date 为：2018-05-28 16:32:51
        DateUtils.addHours(date2,1);// date 为：2018-05-28 17:32:51


        Date date=DateUtils.parseDate("2018-05-27 16:32:51","yyyy-MM-dd HH:mm:ss");
        DateUtils.truncate(date, Calendar.DATE);// 2018-05-27 00:00:00
        DateUtils.truncate(date, Calendar.HOUR);// 2018-05-27 16:00:00
        // 如果按照 Date 截断时间，那么 Date 后面小时，分钟等全部置为0，相当于舍弃了 Date 之后的时间。
    }

}
