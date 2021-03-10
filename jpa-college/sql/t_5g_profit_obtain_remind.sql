create table `t_5g_profit_obtain_remind`
(
    `id`           bigint   not null auto_increment,
    `mobile`       bigint   not null,
    `remind_date`  date     not null,
    `date_created` datetime not null,
    `last_updated` datetime not null,
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;