cmd输入:
netstat -aon | findstr 8080 (8080为被占用的端口号)

查看到占用的进程号为6428，可在任务管理器中找到进程，结束运行；

或者cmd输入:
taskkill /f /t /im 6428 (6248为进程号)