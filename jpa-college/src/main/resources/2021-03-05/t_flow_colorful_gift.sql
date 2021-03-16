create table `t_flow_colorful_gift`
(
    `id`           bigint       not null auto_increment primary key,
    `name`         varchar(255) not null comment '礼品名',
    `img`          varchar(255) not null comment '礼品背景图',
    `icon`         varchar(255) not null comment '礼品图标',
    `price`        decimal(6,2)      not null comment '价格',
    `type`         int          not null comment '礼品类型',
    `enable`       bit          not null default 1 comment '礼品状态',
    `remark`       varchar(255) null comment '礼品说明',
    `date_created` datetime     not null comment '创建时间',
    `last_updated` datetime     not null comment '最后更新时间'
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;

alter table `t_flow_colorful_gift` modify column `price` decimal(6,2);


