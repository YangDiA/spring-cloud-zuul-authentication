/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-04 18:40:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_apirule
-- ----------------------------
DROP TABLE IF EXISTS `role_apirule`;
CREATE TABLE `role_apirule` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  `ruleId` varchar(50) NOT NULL COMMENT 'api规则权限主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色api数据权限关联表';

-- ----------------------------
-- Records of role_apirule
-- ----------------------------
