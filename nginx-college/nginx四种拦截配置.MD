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
