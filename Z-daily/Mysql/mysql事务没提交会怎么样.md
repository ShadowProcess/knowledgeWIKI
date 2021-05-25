使用Navicat打开一个查询窗口，执行以下SQL，不提交；
```sql
set autocommit = 0;
start transaction;
insert into test(`id`,`name`) values(1,'alex')
-- COMMIT;
```

使用cmd执行下面SQL，可以看到该事务未提交，如果关闭上面查询窗口(也就是关闭session)，事务会自动回滚，再次使用以下语句查询未提交事务，已经没有了；
```sql
select * from information_schema.innodb_trx;
```

