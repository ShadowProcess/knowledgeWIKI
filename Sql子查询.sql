select book_id from
    (select * from book) as b
where book_id = 1


-- 如果子句不加别名，那么将会报错
-- Every derived table must have its own alias （MYSQL错误）
-- 每个派生出来的表都必须有一个自己的别名
-- 进行嵌套查询的时候子查询出来的的结果是作为一个派生表来进行上一级的查询的，所以子查询的结果必须要有一个别名
-- as b 语句是必须要有的