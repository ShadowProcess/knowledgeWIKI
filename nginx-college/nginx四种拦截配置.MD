#例子：
假设nginx服务器地址192.168.1.102，监听端口8080,
```text
location = / {
    [ configuration A ]
}

location / {
    [ configuration B ]
}

location /documents/ {
    [ configuration C ]
}

location ^~ /images/ {
    [ configuration D ]
}

location ~* \.(gif|jpg|jpeg)$ {
    [ configuration E ]
}
```

#如上配置
```text
请求 http://192.168.1.102:8080/                           执行配置A
请求 http://192.168.1.102:8080/test.html                  执行配置B，也就是说 / 匹配字符串，匹配任何URI
请求 http://192.168.1.102:8080/documents/document.html    执行配置C
请求 http://192.168.1.102:8080/images/1.gif               执行配置D
请求 http://192.168.1.102:8080/documents/1.jpg            执行配置E
```


#总结：
```text
=   prefix_match_string 表示要求URI和prefix_match_string精确匹配，如果匹配成功，则停止搜索并用当前location处理此请求

~   regular_expression  表示正则表达式regular_expression同URI正则匹配，并且区分大小写

~*  regular_expression  表示正则表达式regular_expression同URI正则匹配，但不区分大小写

^~  prefix_match_string 表示要求URI和prefix_match_string“模糊”匹配找到最匹配location，则使用该location处理此请求，并不再进行正则匹配
```


#语法规则： location [=|~|~*|^~] /uri/ { … }
```text
=   开头表示精确匹配

^~  开头表示uri以某个常规字符串开头，理解为匹配url路径即可。nginx不对url做编码，因此请求为/static/20%/aa，可以被规则^~ /static/ /aa匹配到（注意是空格）。用在以xx开头的情况,例如【^~ /static/】

~   开头表示区分大小写的正则匹配                  用在以xx结尾的情况,例如【~ \.(gif|jpg|png|js|css)$】

~*  开头表示不区分大小写的正则匹配                用在以xx结尾的情况,例如【~* \.png$】

!~  开头表示区分大小写不匹配
 
!~* 开头表示不区分大小写不匹配的正则

/   开头表示通用匹配，任何请求都会匹配到。
```
