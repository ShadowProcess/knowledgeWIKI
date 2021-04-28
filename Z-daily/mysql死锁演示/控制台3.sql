-- 因为控制台1和2，产生了死锁，并且条件未使用索引，导致锁表，其它记录已不能往表中插入记录
insert into t1(`id`,`name`) values(5,'zhangsan');