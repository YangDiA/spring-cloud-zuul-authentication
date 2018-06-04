/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-04 18:40:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apirule
-- ----------------------------
DROP TABLE IF EXISTS `apirule`;
CREATE TABLE `apirule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruleName` varchar(50) DEFAULT NULL COMMENT '规则名称',
  `ruleUrl` varchar(200) DEFAULT NULL COMMENT '权限Url',
  `cateId` int(11) DEFAULT NULL COMMENT '相关分类',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apirule
-- ----------------------------
INSERT INTO `apirule` VALUES ('1', 'apirule列表11', '/sys-api/sys/apirule/page', '1', '2018-05-31 17:20:43');
INSERT INTO `apirule` VALUES ('2', 'api规则删除接口', '/sys-api/sys/apirule/delete', '1', '2018-06-04 18:36:59');
INSERT INTO `apirule` VALUES ('3', '订单明细列表', '/order-api/order/orderinfo/select', '2', '2018-06-04 18:38:12');
