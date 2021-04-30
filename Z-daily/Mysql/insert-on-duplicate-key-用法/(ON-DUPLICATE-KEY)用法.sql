insert into testMfc values
(null, 3,'3',300),
(null, 4,'4',400),
(null, 5,'5',500),
(null, 2,'2',200)
    on duplicate key update num = values(num);


-- 注意： num = values(num) ，此处的格式，必须是 values(列名)，才能更新到对应的行。
-- 上面语句的意思是：当和表中记录产生：主键或者唯一键冲突时，更新num的值
