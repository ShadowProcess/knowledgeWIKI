1.Docker开启远程访问  [默认是不支持的]
```
#修改该Docker服务文件
vi /usr/lib/systemd/system/docker.service
#修改ExecStart这行 
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
```

将文件内的ExeStart注释，新增如上行。
[#]ExecStart=/usr/bin/dockerd -H fd:// 
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
-------------------------------------------------------------------------------------

2.
```
#重新加载配置文件
systemctl daemon-reload
#重启服务
systemctl restart docker.service
#查看端口是否开启
netstat -tunlp #如果找不到netstat命令，可进行安装， yum install net-tools
#直接curl看是否生效
curl http://127.0.0.1:2375/info 
```

