create table `t_flow_colorful_gift_join_record`
(
    `id`               bigint       not null auto_increment primary key,
    `colorful_gift_id` bigint       not null comment '礼品表ID',
    `mobile`           varchar(11)  not null comment '手机号',
    `openid`           varchar(255) null comment 'openid',
    `remark`           varchar(255) null comment '描述',
    `take`             bit          not null comment '领取状态',
    `join_date`        date         not null comment '领取日期',
    `take_end`         datetime     not null comment '领取截止时间',
    `date_created`     datetime     not null comment '创建时间',
    `last_updated`     datetime     not null comment '最后更新时间',
    constraint `c_g_i` foreign key (`colorful_gift_id`) references `t_flow_colorful_gift` (`id`)
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;

create index `index_mj` on `t_flow_colorful_gift_join_record` (`mobile`, `join_date`)