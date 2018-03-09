#docker pull 镜像:版本号
#如果不指定版本号，默认拉下来是最新的
```
docker pull mysql:8.0.13
```

#2. 运行容器 -v表示数据卷  注意：  -d 容器以后台模式运行
```
docker run \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=123456 \
    -v /home/data/mysql/data:/var/lib/mysql:rw \
    -v /home/data/mysql/log:/var/log/mysql:rw \
    -v /home/data/mysql/config/my.cnf:/etc/mysql/my.cnf:rw \
    -v /etc/localtime:/etc/localtime:ro \
    --name mysql8 \
    --restart=always \
    -d \ 
    mysql:8.0.13     
```

> 提前要在提定的位置（我的位置是：/home/data）创建以下文件夹或文件：
> mysql/data  是数据库文件存放的地方。必须要挂载到容器外，否则容器重启一切数据消失。
> mysql/log   是数据库主生的log。建议挂载到容器外。
> mysql/config/my.cnf                 是数据库的配置文件，在下面会放出来。
> /etc/localtime:/etc/localtime:ro    是让容器的时钟与宿主机时钟同步，避免时区的问题，ro是read only的意思，就是只读。 

#3. 配置文件 (这个文件是我进容器里面抄出来的，因为内需要改动一点点) 
default_authentication_plugin= mysql_native_password （这个是因应mysql8的安全机制升级而需要修改的配置，不配置的话将无法登录管理）
```
# Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; version 2 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA

#
# The MySQL  Server configuration file.
#
# For explanations see
# http://dev.mysql.com/doc/mysql/en/server-system-variables.html

[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

# Custom config should go here
!includedir /etc/mysql/conf.d/

default_authentication_plugin= mysql_native_password

#Encoding Setting
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake

[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
```  

如果你没有配置好就启动容器，容器初始化完成后，第3点的配置是不会生效的。你需要进入容器进行以下操作：

其间按提示会要求你输入root用户的password，就是上面我们设置的参数 “MYSQL_ROOT_PASSWORD”
```
[root@centos ~]# docker exec -it mysql8 /bin/sh

# mysql -uroot -p

mysql> ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
``` 