
-- ☆先新建两个数据库shard_one和shard_two，然后两个数据库添加如下表user_info_0和user_info_1


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info_0
-- ----------------------------
DROP TABLE IF EXISTS `user_info_0`;
CREATE TABLE `user_info_0`
(
    `id`          bigint(20)                                                    NOT NULL COMMENT '主键',
    `available`   int(1)                                                        NULL DEFAULT 1 COMMENT '是否可用，1 可用，0 不可用',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `deleted`     int(1)                                                        NULL DEFAULT 0 COMMENT '是否删除，0 未删除， 1 删除',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `phone`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `salt`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
    `sex`         int(1)                                                        NULL DEFAULT NULL COMMENT '性别 0未知 1女 2男',
    `user_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info_1
-- ----------------------------
DROP TABLE IF EXISTS `user_info_1`;
CREATE TABLE `user_info_1`
(
    `id`          bigint(20)                                                    NOT NULL COMMENT '主键',
    `available`   int(1)                                                        NULL DEFAULT 1 COMMENT '是否可用，1 可用，0 不可用',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `deleted`     int(1)                                                        NULL DEFAULT 0 COMMENT '是否删除，0 未删除， 1 删除',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `phone`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `salt`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
    `sex`         int(1)                                                        NULL DEFAULT NULL COMMENT '性别 0未知 1女 2男',
    `user_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;