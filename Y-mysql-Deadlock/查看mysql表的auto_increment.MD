#0.查看MySQL5版本的，auto_increment数值
```sql
select auto_increment from information_schema.tables where table_schema='数据库名' and table_name='表名';
```


#1.查看MySQL8版本的，auto_increment数值
```sql
show create table 表名;
```
[通过以下AUTO_INCREMENT查看下一个自增ID值]
```
CREATE TABLE `t1` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID，自增',
`uid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户uid',
`name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户昵称',
PRIMARY KEY (`id`),
UNIQUE KEY `u_idx_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='测试replace into'
```


#2.查看指定表的最大id值
```sql
select max(id) from 表名;
-- 因为：如果手动删除了最新的数据，使用 max(id)查询的结果是当前剩下数据中最大的记录，而新插入数据则不一定从这个数字开始计数,
-- 所以为了准确的获取下一条插入记录的id，应该查询的是auto_increment
```


#3.修改MySQL的，auto_increment数值
```sql
alter table '表名' auto_increment=数值;
```


#4.删除MySQL主键id
```sql
alter TABLE test drop COLUMN `id`;
```


#5.自增id的三种模式
```sql
select @@innodb_autoinc_lock_mode;

-- 0：traditional （每次都会产生表锁）
-- 1：consecutive （mysql的默认模式，会产生一个轻量锁，simple insert会获得批量的锁，保证连续插入）
-- 2：interleaved （不会锁表，来一个处理一个，并发最高）
```

