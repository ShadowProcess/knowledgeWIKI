package com.example.string;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;


public class StringTest {


    @Test
    public void _0(){
        String str = "hello world";

        // isEmpty和isBlank的区别在于isEmpty不会忽略空格,
        // " "<--对于这样的字符串isEmpty会认为不是空,
        // 而isBlank会认为是空,isBlank更常用
        StringUtils.isEmpty(str);
        StringUtils.isNotEmpty(str);
        boolean blank = StringUtils.isBlank("  ");
        StringUtils.isNotBlank(str);



        // strip      --> 去除两端的aa
        // stripStart --> 去除开始位置的hell
        // stripEnd   --> 去除结尾位置的orld
        StringUtils.strip(str, "aa");
        StringUtils.stripStart(str, "hell");
        StringUtils.stripEnd(str, "orld");



        // 返回字符串在第三次出现A的位置
        StringUtils.ordinalIndexOf(str, "A", 3);



        // 获取str 开始为hello结尾为orld中间的字符串
        // 注意此方法返回字符串      -->substringBetween
        // 注意此方法返回字符串数组(多了个s) --> substringsBetween
        StringUtils.substringBetween(str, "hell", "orld");
        StringUtils.substringsBetween(str, "hell", "orld");



        // 将str重复3次并拼接
        StringUtils.repeat(str, 3);
        // 将str重复2次并拼接，用haha拼接
        StringUtils.repeat(str, "haha", 2);


        // 统计参数2在字符串中出现的次数
        StringUtils.countMatches(str, "l");


        // 判断字符串是否全小写或大写
        StringUtils.isAllLowerCase(str);
        StringUtils.isAllUpperCase(str);



        // isAlpha          -->全部由字母组成返回true
        // isNumeric        -->全部由数字组成返回true
        // isAlphanumeric   -->全部由字母或数字组成返回true
        // isAlphaSpace     -->全部由字母或空格组成返回true
        // isWhitespace     -->全部由空格组成返回true
        StringUtils.isAlpha(str);
        StringUtils.isNumeric(str);
        StringUtils.isAlphanumeric(str);
        StringUtils.isAlphaSpace(str);
        StringUtils.isWhitespace(str);


        // 缩进字符串,第二参数最低为 4,要包含...
        // 现在Hello World输出为H...
        StringUtils.abbreviate(str, 4); //H...


        // 首字母大写或小写
        StringUtils.capitalize(str);
        StringUtils.uncapitalize(str);


        // 将字符串数组转变为一个字符串,通过","拼接,支持传入iterator和collection
        StringUtils.join(new String[] { "Hello", "World" }, ",");



        /*
         * 经常性要把后台的字符串传递到前台作为html代码进行解释,
         * 可以使用以下方法进行转换,以下方法输出为
         * <p>Hello</p>
         */
        StringEscapeUtils.escapeHtml4("Hello");
    }


    @Test
    public void _2(){
        String str = "22sda22";
        String abbreviate = StringUtils.abbreviate(str, 4);
        System.out.println(abbreviate);
    }
}
