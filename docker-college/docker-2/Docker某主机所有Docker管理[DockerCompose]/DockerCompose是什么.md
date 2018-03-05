Docker Compose 是一个工具，命令行工具。
这个工具可以通过yml文件定义多容器的docker应用
通过一条命令就可以根据yml文件的定义去创建或者管理这多个容器


docker-compose.yml 介绍
#services
一个service代表一个container，这个container可以从dockerhub的image来创建，或者从本地的Dockerfile build出来的image来创建。
service的启动类似docker run，我们可以给其指定network和volme，所以可以给service指定network和volume的引用
#建立一个networks
networks:
  my-bridge:
    driver: bridge
#volumes
挂载数据卷到宿主机
volumes:
    - "data/log:/var/data/log"     
    
#############################################################
1.docker-compose up        用于部署一个 Compose 应用。 ...
2.docker-compose stop      停止Compose 应用相关的所有容器,...
3.docker-compose rm        用于删除已停止的 Compose 应用。 ...
4.docker-compose restart   重启已停止的 Compose 应用。 ...
5.docker-compose ps        用于列出 Compose 应用中的各个容器。...

##将此文件保存为docker-compose.yml您想要的任何位置