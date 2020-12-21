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
 * find方法解释:
 *
 * public boolean find()尝试查找与该模式匹配的输入序列的下一个子序列。
 * 此方法从匹配器区域的开头开始，如果该方法的前一次调用成功了并且从那时开始匹配器没有被重置，
 * 则从以前匹配操作没有匹配的第一个字符开始。
 * 如果匹配成功，则可以通过 start、end 和 group 方法获取更多信息。
 *
 * matcher.start() 返回匹配到的子字符串在字符串中的索引位置.
 * matcher.end()返回匹配到的子字符串的最后一个字符在字符串中的索引位置.
 * matcher.group()返回匹配到的子字符串
 * 返回：
 * 当且仅当输入序列的子序列匹配此匹配器的模式时才返回 true。
 *
 *
 *
 * 源码group方法译文:
 *
 * 返回给定组在前一个匹配操作中捕获的输入子序列。
 * 对于matcher m，输入序列s和组索引g，表达式m.group(g)和s.substring(m.start(g)， m.end(g))是等价的。
 * 捕获组从左到右建立索引，从1开始。组0表示整个模式，因此表达式m.group(0)等价于m.group()。
 * 如果匹配成功，但指定的组未能匹配输入序列的任何部分，则返回null。注意，有些组(例如(a*))匹配空字符串。
 * 当一个组成功匹配输入中的空字符串时，此方法将返回空字符串。
 */
