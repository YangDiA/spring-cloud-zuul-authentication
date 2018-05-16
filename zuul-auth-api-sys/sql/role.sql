/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-16 18:41:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_PERSONAL', 'psersonal项目角色', 'psersonal项目角色', '2018-05-09 11:45:47');
INSERT INTO `role` VALUES ('2', 'ROLE_BOSS', '超级管理员', '超级管理员', '2018-05-09 11:44:17');
INSERT INTO `role` VALUES ('3', 'ROLE_MANAGER', '普通管理员', '普通管理员', '2018-05-09 11:57:03');
INSERT INTO `role` VALUES ('4', 'ROLE_EMPLOYEE', '测试角色', '测试角色', '2018-05-09 11:57:05');
INSERT INTO `role` VALUES ('5', 'ROLE_SYS', 'sys项目管理', 'sys项目管理', '2018-05-09 14:35:14');
INSERT INTO `role` VALUES ('21', null, '2', '2', '2018-05-11 17:17:54');
INSERT INTO `role` VALUES ('29', null, 'd ', 'd', '2018-05-11 17:31:56');
INSERT INTO `role` VALUES ('30', null, 'd2', 'sf', '2018-05-11 17:32:23');
INSERT INTO `role` VALUES ('31', null, 'ew ', 'sd ', '2018-05-11 17:32:53');
