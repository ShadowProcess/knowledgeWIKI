set autocommit = 0;
start transaction;
insert into t1(`id`,`name`) values(1,'alex');

-- ON DUPLICATE KEY UPDATE `name`='alex';