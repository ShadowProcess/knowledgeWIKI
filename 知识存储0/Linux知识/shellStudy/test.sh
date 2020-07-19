#!/bin/bash
 ##第一个程序
echo "hello world !"

 ##第二个程序
your_name="runoob.com"
echo $your_name

## 第三个 输出unoo 
string="runoob is a great site"
echo ${string:1:4}

## 数组
array_name={1 2 3 4 5}
echo $array_name[0]
echo $array_name[4]

:<<!
这是多行注释
!

:<<EOF
注释内容
注释内容
注释内容
EOF

:<<'
这也是多行注释
'






