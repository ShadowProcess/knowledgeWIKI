##1.先使用top命令找到占用CPU高的java进程的[pid] 
```shell script
top
```
![avatar](image/top.png)



##2.执行命令，将该进程对应的所有线程都列出来，看看那个线程的占用CPU最多，然后将这个线程的pid转换为16进制
```shell script
top -H -p [CPU占用高的pid]
```
![avatar](image/top%20-H%20-p%20pid.png)



##3.使用jstack 占用CPU高的java进程的[pid]  命令导出栈信息
```shell script
jstack [pid]
```


##4.根据16进制的线程pid 在栈信息中找到对应的描述信息 【java的栈信息中的线程id是用16进制表示的】
```shell script
cat stack.txt | grep "16进制pid" -C 10
```
