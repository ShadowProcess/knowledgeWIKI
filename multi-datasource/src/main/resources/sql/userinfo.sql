CREATE TABLE IF NOT EXISTS `user_info`(
`id` int AUTO_INCREMENT,
`name` varchar(20) not null,
primary key ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into `user_info`(`name`) values('alex1');

insert into `user_info`(`name`) values('alex2');

insert into `user_info`(`name`) values('alex3');

