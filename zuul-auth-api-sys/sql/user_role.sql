/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-16 18:41:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) unsigned DEFAULT NULL,
  `roleId` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
INSERT INTO `user_role` VALUES ('3', '1', '3');
INSERT INTO `user_role` VALUES ('4', '1', '4');
INSERT INTO `user_role` VALUES ('5', '2', '3');
INSERT INTO `user_role` VALUES ('6', '2', '4');
INSERT INTO `user_role` VALUES ('7', '2', '1');
INSERT INTO `user_role` VALUES ('8', '3', '1');
INSERT INTO `user_role` VALUES ('9', '3', '4');
INSERT INTO `user_role` VALUES ('10', '4', '1');
INSERT INTO `user_role` VALUES ('11', '4', '4');
INSERT INTO `user_role` VALUES ('12', '5', '1');
INSERT INTO `user_role` VALUES ('13', '1', '5');
