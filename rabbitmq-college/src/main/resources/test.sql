create table score(
    `id` int primary key auto_increment,
    `name` varchar(20),
    `subject` varchar(20),
    `score` int
);

insert into score
values(null,'张三','语文',81),
      (null,'张三','数学',75),
      (null,'李四','语文',76),
      (null,'李四','数学',90),
      (null,'王五','语文',81),
      (null,'王五','数学',100),
      (null,'王五','英语',90)
;