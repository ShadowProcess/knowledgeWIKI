jhat是用来分析jmap生成dump文件的命令，jhat内置了应用服务器，可以通过网页查看dump文件分析结果，jhat一般是用在离线分析上。

命令格式：
jhat [option] [dumpfile]

option参数解释：
- stack false: 关闭对象分配调用堆栈的跟踪
- refs false: 关闭对象引用的跟踪
- port <port>: HTTP服务器端口，默认是7000
- debug <int>: debug级别
　　 0: 无debug输出
　　 1: Debug hprof file parsing
　　 2: Debug hprof file parsing, no server
- version 分析报告版本


常用示例：
jhat dump.hprof