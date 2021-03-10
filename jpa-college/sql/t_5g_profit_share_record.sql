create table `t_5g_profit_share_record`
(
    `id`           bigint   not null auto_increment primary key,
    `mobile`       bigint   not null,
    `vip_profit`   bigint   not null,
    `date_created` datetime not null,
    `last_updated` datetime not null,
    constraint `vip` foreign key (`vip_profit`)
        references `t_5g_act_vip_profit` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;