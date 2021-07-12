package com.example.controller.specialsymbol;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;

/*
使用get方式时参数中不能有特殊字符，如：\,空格,%,#,&,=等等;
=====================================================
解决办法：《用URL转义符替换》
+      URL中+号表示空格                      %2B
空格    URL中的空格可以用+号或者编码            %20
/      分隔目录和子目录                       %2F
?      分隔实际的URL和参数                    %3F
%      指定特殊字符                          %25
#      表示书签                              %23
&      URL中指定的参数间的分隔符               %26
=      URL中指定参数的值                      %3D
=====================================================
 */

/**
 * 在RFC2396标准列明了";" | "?" | ":" | "@" | "&" | "=" | "+" | "$" | "," 这些字符为保留字符，需要转译后才能出现在uri中。
 *
 * 前段时间遇到个问题，后端返回一个url，我需要加参数直接访问，但由于参数中有的含有特殊字符（#），导致浏览器识别不到#后面的参数内容；
 * URL中存在特殊字符问题前端解决方案：http://localhost:8080/symbol1?s=特殊%23字符，后端可以收到 s=特殊#字符
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String url = "http://localhost:8080/symbol1";

        //TODO 以下方式可以解决大部分特殊符号问题，但是+是解决不了的。
        //TODO 可以解决 #、空格、=、&、%、？、/ ,不能解决 +
        //TODO 想解决+,只能通过转义解决，或者post请求。
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("s", "s#+=&%?/");
        URI uri = builder.build().encode().toUri();

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                String.class);
        System.out.println(response);
    }
}
