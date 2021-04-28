/*
 Navicat Premium Data Transfer

 Source Server         : 测试库
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : 192.168.2.26:3306
 Source Schema         : play

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 28/04/2021 18:49:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t1
-- ----------------------------
DROP TABLE IF EXISTS `t1`;
CREATE TABLE `t1`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
