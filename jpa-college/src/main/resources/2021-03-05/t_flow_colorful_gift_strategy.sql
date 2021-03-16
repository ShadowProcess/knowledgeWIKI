create table `t_flow_colorful_gift_strategy`
(
    `id`               bigint       not null auto_increment primary key,
    `colorful_gift_id` bigint       not null comment '礼品表id',
    `daily_count`      int          not null comment '每日总数',
    `start`            time         null comment '每日开始时间',
    `end`              time         null comment '每日结束时间',
    `user_tag_group`   varchar(255) null comment '用户组',
    `state`            int          null comment '状态',
    `date_created`     datetime     not null comment '创建时间',
    `last_updated`     datetime     not null comment '最后更新时间'
    -- constraint `c_g_i` foreign key (`colorful_gift_id`) references `t_flow_colorful_gift` (`id`)
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;

create index `index_cd` on `t_flow_colorful_gift_strategy` (`colorful_gift_id`, `daily_count`)