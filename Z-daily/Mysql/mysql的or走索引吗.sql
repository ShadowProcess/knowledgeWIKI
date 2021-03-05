select * from `student` where `name`= 1 or `age` = 2;

-- 如果name和age都是索引,那么此条语句才走索引
-- 如果name和age两个字段,只有一个是索引，另外一个不是，那么该语句不走索引