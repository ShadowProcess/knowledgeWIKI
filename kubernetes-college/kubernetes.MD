#K8s简介
​K8s简介
Kubernetes是Google 2014年创建管理的，是Google 10多年大规模容器管理技术Borg的开源版本。它是容器集群管理系统，是一个开源的平台，可以实现容器集群的自动化部署、自动扩缩容、维护等功能。

通过Kubernetes你可以：
- 快速部署应用
- 快速扩展应用
- 无缝对接新的应用功能
- 节省资源，优化硬件资源的使用

Kubernetes 特点:
- 可移植: 支持公有云，私有云，混合云，多重云（multi-cloud）
- 可扩展: 模块化, 插件化, 可挂载, 可组合
- 自动化: 自动部署，自动重启，自动复制，自动伸缩/扩展

K8s总体架构

K8s集群由两节点组成：Master和Node。在Master上运行etcd,Api Server,Controller Manager和Scheduler四个组件。
后三个组件构成了K8s的总控中心，负责对集群中所有资源进行管控和调度.在每个node上运行kubectl,proxy和docker daemon三个组件,
负责对节点上的Pod的生命周期进行管理，以及实现服务代理的功能。另外所有节点上都可以运行kubectl命令行工具。

API Server作为集群的核心，负责集群各功能模块之间的通信。集群内的功能模块通过Api Server将信息存入到etcd,
其他模块通过Api Server读取这些信息，从而实现模块之间的信息交互。Node节点上的Kubelet每隔一个时间周期，
通过Api Server报告自身状态，Api Server接收到这些信息后，将节点信息保存到etcd中。
Controller Manager中 的node controller通过Api server定期读取这些节点状态信息，并做响应处理。
Scheduler监听到某个Pod创建的信息后，检索所有符合该pod要求的节点列表，并将pod绑定到节点列表中最符合要求的节点上。
如果scheduler监听到某个Pod被删除，则调用api server删除该Pod资源对象。kubelet监听pod信息，如果监听到pod对象被删除，
则删除本节点上的相应的pod实例，如果监听到修改Pod信息，则会相应地修改本节点的Pod实例。

![avatar](K8架构图.png)
Kubernetes主要由以下几个核心组件组成：

- etcd保存了整个集群的状态；
- apiserver提供了资源操作的唯一入口，并提供认证、授权、访问控制、API注册和发现等机制；
- controller manager负责维护集群的状态，比如故障检测、自动扩展、滚动更新等；
- scheduler负责资源的调度，按照预定的调度策略将Pod调度到相应的机器上；
- kubelet负责本Node节点上的Pod的创建、修改、监控、删除等生命周期管理，同时Kubelet定时“上报”本Node的状态信息到Api Server里；
- Container runtime负责镜像管理以及Pod和容器的真正运行（CRI）；
- kube-proxy负责为Service提供cluster内部的服务发现和负载均衡；

Kubernetes可以做什么？
使用Web服务，用户希望应用程序能够7*24小时全天运行，开发人员希望每天多次部署新的应用版本。
通过应用容器化可以实现这些目标，使应用简单、快捷的方式更新和发布，也能实现热更新、迁移等操作。
使用Kubernetes能确保程序在任何时间、任何地方运行，还能扩展更多有需求的工具/资源。
Kubernetes积累了Google在容器化应用业务方面的经验，以及社区成员的实践，是能在生产环境使用的开源平台。

