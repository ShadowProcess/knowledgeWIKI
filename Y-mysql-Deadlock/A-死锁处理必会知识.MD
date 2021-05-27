#执行insert时，锁申请顺序(非常重要必须会)
```text
会话1插入记录的时候，(先)申请表锁IX，(再)申请gap锁，(然后)申请插入意向锁, 插入成功后，释放了插入意向锁，(最后)增加了插入记录的记录锁(rec lock)。
（理论上插入意向锁在事务未提交，不应该释放，但是看截图是释放了的，估计记录锁也能够保证插入意向锁的功能）

会话2插入记录的时候，(先)申请表锁IX，(再)申请gap锁，(然后)申请意向锁发生了等待，因为会话1持有了gap锁。
```


#mysql可以打开锁统计，通过以下语句打开mysql的锁的统计,可以输出锁详细日志,该类监控机制默认是关闭状态，分析问题需要查看监控日志时再开启。 
#建议分析问题后将监控关闭，否则，每15秒输出一次INNODB运行状态信息到错误日志，会使用日志变得特别大。
```sql
set GLOBAL innodb_status_output_locks=ON;
set GLOBAL innodb_status_output=ON;
```


#设置获取锁等待时间：
```sql
set innodb_lock_wait_timeout=1500; --只影响当前Session。
set global innodb_lock_wait_timeout=1500; --作为全局的修改方式，只会影响修改之后打开的Session，而不能改变当前Session。
```


#查看Mysql状态日志：
```sql
show engine innodb status;
```


#知识点：Mysql监控日志锁名词解释(上下结合看)
监控日志中以下两句:
lock_mode X [locks rec but not gap] 什么意思? 
lock_mode X [locks gap before rec] 什么意思?
```(解释)
locks gap before rec    表示为gap锁：            lock->type_mode & LOCK_GAP
locks rec but not gap   表示为记录锁,非gap锁：    lock->type_mode & LOCK_REC_NOT_GAP
insert intention        表示为插入意向锁：        lock->type_mode & LOCK_INSERT_INTENTION
waiting                 表示锁等待：             lock->type_mode & LOCK_WAIT

lock->type_mode可以是lock_mode X 或者 lock_model S
```


#Mysql主要有四种锁算法(也叫锁机制)
```
1. Record锁：这种锁会在索引上加锁，比如sql为select column_1 from table where column_1=1 for update，且column_1上有索引，则会把colunm_1为1的行都加排它锁，其他事务禁止对此行读和写。
2. Gap锁（间隙锁）：这种锁作用在索引记录之间。目的只需要记住：他是为防止其他事务插入间隙（包括防止insert方式插入新数据到间隙，以及update方式将其他行变更到此间隙）。Gap锁可以有效的防止”幻读“（因为这些间隙都被上了锁，其他事务不可能再插入数据到这些间隙中，于是当前事务在连续进行”当前读“时，每次读到的都是相同的记录）。虽然Gap锁只作用在隔离级别为RR及以上的数据库上，但是不意味着隔离等级为RC级别的不会使用，在RC级别，在进行外键约束检测和唯一键约束检测的时候，会使用到Gap锁，而正是这个duplicate-key checking导致了上文出现的死锁发生。关于Gap锁到底是如何加锁的，可以参阅这篇文章[https://www.cnblogs.com/crazylqy/p/7821481.html?spm=a2c6h.12873639.0.0.47a21bf4dumfON]。
3. Next-Key锁：本质上就是Gap锁和Record锁的结合，锁住索引外还要锁住索引的间隙。再具体一些就是，一个record锁，加上，位于此索引记录前的第一个间隙处的间隙锁。举个简单的例子就是，如果现在有一个索引包含三个值1，3，5，则next-key lock锁，可能锁住的范围就有(-∞,1],(1,3],(3,5],(5,+∞]。同样在next-key lock一般作用在RR隔离等级的数据库，但是当出现在insert时候，检测到唯一键冲突的时候，会在冲突所在唯一索引出和之前的间隙处加Next-key lock.
4. Insert Intention锁（插入意向锁）：顾名思义，这个锁是在数据插入之前会加此锁。它是一种轻量的Gap锁，同事也是意向排他锁的一种。它的存在使得多个事务在写入不同数据到统一索引间隙的时候，不会发生锁等待。另外由于它是一种意向插入锁，所以当排他锁已经处于间隙上的时候，根据锁的兼容矩阵，可以知道，意向插入锁必须等待此间隙上的排它锁释放，才能获取。
```


#Mysql锁属性
```
MySQL的锁系统：shared lock和exclusive lock(共享锁和排他锁，也叫读锁和写锁，即read lock和write lock)

共享锁【S锁】
又称读锁，若事务T对数据对象A加上S锁，则事务T可以读A但不能修改A，其他事务只能再对A加S锁，而不能加X锁，直到T释放A上的S锁。
这保证了其他事务可以读A，但在T释放A上的S锁之前不能对A做任何修改。

排他锁【X锁】
又称写锁。若事务T对数据对象A加上X锁，事务T可以读A也可以修改A，其他事务不能再对A加任何锁，直到T释放A上的锁。
这保证了其他事务在T释放A上的锁之前不能再读取和修改A。
```



#查看mysql所有事务状态:
```sql
select * from information_schema.innodb_trx;
-- trx_state：事务状态，有以下几种状态：RUNNING、LOCK WAIT、ROLLING BACK 和 COMMITTING
kill trx_mysql_thread_id; 
```

#查看Mysql有哪些线程在运行
```text
show processlist和show full processlist
processlist命令的输出结果显示了有哪些线程在运行，不仅可以查看当前所有的连接数，还可以查看当前的连接状态帮助识别出有问题的查询语句等。
如果是root帐号，能看到所有用户的当前连接。如果是其他普通帐号，则只能看到自己占用的连接。show processlist只能列出当前100条。如果想全部列出，可以使用SHOW FULL PROCESSLIST命令

各个列的含义：
①.id列，用户登录mysql时，系统分配的"connection_id"，可以使用函数connection_id()查看
②.user列，显示当前用户。如果不是root，这个命令就只显示用户权限范围的sql语句
③.host列，显示这个语句是从哪个ip的哪个端口上发的，可以用来跟踪出现问题语句的用户
④.db列，显示这个进程目前连接的是哪个数据库
⑤.command列，显示当前连接的执行的命令，一般取值为休眠（sleep），查询（query），连接（connect）等
⑥.time列，显示这个状态持续的时间，单位是秒
⑦.state列，显示使用当前连接的sql语句的状态，很重要的列。state描述的是语句执行中的某一个状态。一个sql语句，以查询为例，可能需要经过copying to tmp table、sorting result、sending data等状态才可以完成
⑧.info列，显示这个sql语句，是判断问题语句的一个重要依据
```



