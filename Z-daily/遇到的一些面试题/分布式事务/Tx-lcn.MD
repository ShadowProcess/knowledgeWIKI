#采用TCC方案
1.锁定事务单元（lock）
2.确认事务模块状态(confirm)
3.通知事务(notify)

原理：
① LCN客户端（发起方和参与方都必须注册到事务协调者tx-manager）建立一个长连接（优点：减少带宽传输，弊端：占内存）
② 订单服务（发起方）调用库存服务接口（参与方）之前会向tx-manager事务协调者创建一个事务的分组id
③ 订单服务（发起方）调用库存服务接口（参与方）的时候，会在请求头中存放该事务的分组id，给库存服务
④ 如果库存服务获取到请求头中有对应的事务分组id，库存服务业务逻辑代码执行完毕，会采用假关闭，不会提交该事务（实际等发起方代码执行完毕才会提交事务）

启动类分别加入注解：@EnableDistributedTransaction
- 订单接口加入注解@LcnTransaction @Transactional
- 库存接口加入注解@TxcTransaction @Transactional