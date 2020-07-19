#!/bin/bash

echo $* 与 $@ 区别：

echo 相同点：都是引用所有参数。
echo 不同点：只有在双引号中体现出来。
echo 假设在脚本运行时写了三个参数 1、2、3，，
echo 则 " * " 等价于 "1 2 3"（传递了一个参数），
echo 而 "@" 等价于 "1" "2" "3"（传递了三个参数）。


echo "-- $* 演示输入参数的脚本，可输入任意个数参数---"
for i in "$*"; do
	echo $i
done


for i in "$@"; do
	echo $i
done


