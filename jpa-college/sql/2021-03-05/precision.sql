create table `t_flow_colorful_gift`
(
    `id`           bigint        not null auto_increment primary key,
    `name`         varchar(255)  not null comment '礼品名',
    `img`          varchar(255)  not null comment '礼品背景图',
    `icon`         varchar(255)  not null comment '礼品图标',
    `price`        decimal(6, 2) not null comment '价格',
    `type`         int           not null comment '礼品类型',
    `enable`       bit           not null default 1 comment '礼品状态',
    `remark`       varchar(255)  null comment '礼品说明',
    `date_created` datetime      not null comment '创建时间',
    `last_updated` datetime      not null comment '最后更新时间'
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;


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
    `last_updated`     datetime     not null comment '最后更新时间'
    -- constraint `c_g_i` foreign key (`colorful_gift_id`) references `t_flow_colorful_gift` (`id`)
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;

create index `index_mj` on `t_flow_colorful_gift_join_record` (`mobile`, `join_date`);


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
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;

create index `index_cd` on `t_flow_colorful_gift_strategy` (`colorful_gift_id`, `daily_count`);


create table `t_flow_colorful_gift_take_record`
(
    `id`                        bigint       not null auto_increment primary key,
    `colorful_gift_id`          bigint       not null comment '礼品表id',
    `colorful_gift_join_record` bigint       not null comment '对应领取记录ID',
    `openid`                    varchar(255) null comment 'openid',
    `mobile`                    varchar(255) not null comment '手机号',
    `region`                    varchar(255) not null comment '地区',
    `detail_address`            varchar(255) not null comment '详细地址',
    `express_num`               varchar(255) null comment '快递单号',
    `send`                      bit          not null default 0 comment '发货状态',
    `remark`                    varchar(255) null comment '说明',
    `date_created`              datetime     not null comment '创建时间',
    `last_updated`              datetime     not null comment '最后更新时间'
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;