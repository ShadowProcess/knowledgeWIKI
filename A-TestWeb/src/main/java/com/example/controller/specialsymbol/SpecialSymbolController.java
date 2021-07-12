package com.example.controller.specialsymbol;

import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*
使用get方式时参数中不能有特殊字符，如：\,空格,%,#,&,=等等;
=====================================================
解决办法：《用URL转义符替换》
+      URL 中+号表示空格                      %2B
空格    URL中的空格可以用+号或者编码             %20
/      分隔目录和子目录                        %2F
?      分隔实际的URL和参数                     %3F
%      指定特殊字符                           %25
#      表示书签                              %23
&      URL 中指定的参数间的分隔符               %26
=      URL 中指定参数的值                      %3D
=====================================================
 */

/**
 * 前段时间遇到个问题，后端返回一个url，我需要加参数直接访问，但由于参数中有的含有特殊字符（#），导致浏览器识别不到#后面的参数内容；
 * URL中存在特殊字符问题解决方案：
 * 浏览器访问：http://localhost:8080/symbol1?s=特殊%23字符，后端可以收到 s=特殊#字符
 */

@RestController
public class SpecialSymbolController {


    @GetMapping(value = "symbol1")
    public String s1(String s){
        System.out.println(s);
        return s;
    }


    @PostMapping(value = "symbol2")
    public String s2(String s){
        System.out.println(s);
        return s;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        val encode = URLEncoder.encode("http://localhost:8080/symbol1?s==", "UTF-8");
        val decode = URLDecoder.decode(encode, "UTF-8");
        System.out.println(encode);
        System.out.println(decode);
    }
}
