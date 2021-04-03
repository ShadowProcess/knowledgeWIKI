/*
Navicat MySQL Data Transfer

Source Server         : localHost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : mybatisplus

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2021-04-03 12:13:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'asd', 'sad');
INSERT INTO `user` VALUES ('2', 'asd2', 'sad2');
INSERT INTO `user` VALUES ('3', 'askk', 'sad3');
