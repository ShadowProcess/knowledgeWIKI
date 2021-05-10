-- 新建数据库2：db_user
-- 然后创建如下表

CREATE TABLE `t_user`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `account` decimal(10, 2) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


insert into `t_user`(`account`) values(100.00);