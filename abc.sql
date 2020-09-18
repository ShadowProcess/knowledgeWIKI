CREATE TABLE `pre_request_logs_20180524`
(
    `id`          int(11)          NOT NULL AUTO_INCREMENT,
    `ip`          char(16)         NOT NULL COMMENT '代理IP',
    `port`        int(8)           NOT NULL COMMENT '端口号',
    `status`      enum ('成功','失败') NOT NULL COMMENT '状态',
    `create_time` datetime         NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_port` (`port`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8 COMMENT ='代理IP请求日志';