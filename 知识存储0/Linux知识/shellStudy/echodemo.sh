#!/bin/sh
echo "请输入一个字符"
read name
echo "$name it is a test"

echo "-e 意思是开启转义"

#\n换行
echo -e "ok! \n"

#\c不换行
echo -e "ok! \c"
