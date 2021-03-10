-- OpenId福利表
create table `t_flow_openid_benefit_record`
(
    `id`              bigint       not null auto_increment primary key,
    `openid`          varchar(255) not null comment '微厅用户openid',
    `benefit_name`    varchar(255) not null comment '福利名称',
    `pkg_id`          bigint       not null comment '商品表id',
    `benefit_status`  int          not null comment '福利状态',
    `benefit_expire`  datetime     not null comment '福利过期时间',
    `benefit_receive` datetime     null comment '福利被领取时间',
    `use_mobile`      bigint       null comment '福利使用设备号',
    `date_created`    datetime     not null comment '创建时间',
    `last_updated`    datetime     not null comment '最后更新时间',
    `ctdev`           bit          not null default 0 comment '是否异网',
    constraint `pkg_of_id` foreign key (`pkg_id`)
        references `t_flow_package` (`id`)
) ENGINE = InnoDb
  DEFAULT CHARSET = utf8;