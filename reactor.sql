create table `t_domain_group`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT,
    `custom_identify` varchar(255) NOT NULL COMMENT '客户唯一标识',
    `group`           varchar(255) NOT NULL COMMENT '域名所属组',
    `custom_domain`   varchar(255) NOT NULL COMMENT '客户域名',
    `create_time`     datetime DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_group_custom_domain` (`group`,`custom_domain`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='域名群组表';


ALTER TABLE `users` ADD `manage_domain_group` varchar(255) DEFAULT NULL COMMENT '用户管理域名组';
