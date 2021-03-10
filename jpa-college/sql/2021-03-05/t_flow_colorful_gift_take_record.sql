create table `t_flow_colorful_gift_strategy`
(
    `id`               bigint       not null auto_increment primary key,
    `colorful_gift_id` bigint       not null comment '礼品表id',
    `openid`           varchar(255) null comment 'openid',
    `mobile`           varchar(255) not null comment '手机号',
    `region`           varchar(255) not null comment '地区',
    `detail_address`   varchar(255) not null comment '详细地址',
    `express_num`      varchar(255) null comment '快递单号',
    `send`             bit          not null default 0 comment '发货状态',
    `remark`           varchar(255) null comment '说明',
    `date_created`     datetime     not null comment '创建时间',
    `last_updated`     datetime     not null comment '最后更新时间',
    constraint `c_g_i` foreign key (`colorful_gift_id`) references `t_flow_colorful_gift` (`id`)
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;