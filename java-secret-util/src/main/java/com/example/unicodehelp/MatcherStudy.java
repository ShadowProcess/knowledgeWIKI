package com.example.unicodehelp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherStudy {

    public static void main(String[] args) throws Exception {
        String regEx = "count(\\d+)(df)";
        String s = "count000df";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group(2));
        }
        System.out.println(matcher.group());  //全部
        System.out.println(matcher.group(1)); //第一个括号中内容
        System.out.println(matcher.group(2)); //第二个括号中内容
        System.out.println(matcher.group(3)); //第三个括号中内容,正则中没有第三个括号，那么将报错
    }

    /**
     * mat.group()  输出为 count000df
     * mat.group(1) 输出为 000
     * mat.group(2) 输出为 df
     * 如果没有括号会有异常。这就是 group() 的作用。
     */
}


/**
 * 源码group方法译文:
 *
 * 返回给定组在前一个匹配操作中捕获的输入子序列。
 * 对于matcher m，输入序列s和组索引g，表达式m.group(g)和s.substring(m.start(g)， m.end(g))是等价的。
 * 捕获组从左到右建立索引，从1开始。组0表示整个模式，因此表达式m.group(0)等价于m.group()。
 * 如果匹配成功，但指定的组未能匹配输入序列的任何部分，则返回null。注意，有些组(例如(a*))匹配空字符串。
 * 当一个组成功匹配输入中的空字符串时，此方法将返回空字符串。
 */
