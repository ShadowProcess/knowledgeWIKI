create table if not exists `t_order_5g_share`
(
    `id`           int auto_increment,
    `mobile_num`   varchar(13),
    `open_id`      varchar(255),
    `order_id`     varchar(255),
    `date_created` datetime,
    `last_updated` datetime,
    primary key (`id`)
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;

create index `index_mobile` on `t_order_5g_share` (`mobile_num`)