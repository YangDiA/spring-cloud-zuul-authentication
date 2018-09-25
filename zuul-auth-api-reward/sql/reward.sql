/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : db1

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-09-25 18:01:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for funds
-- ----------------------------
DROP TABLE IF EXISTS `funds`;
CREATE TABLE `funds` (
  `funds_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '奖励ID',
  `user_id` int(10) NOT NULL COMMENT '会员ID',
  `amount` int(11) DEFAULT NULL COMMENT '金额',
  `type` int(2) DEFAULT NULL COMMENT '类型（1充值，2提现，3推荐奖金，4团队奖金）',
  `status` int(2) DEFAULT NULL COMMENT '流水状态（1等待审核，2审核成功）',
  `recommended_id` int(10) DEFAULT NULL COMMENT '被推荐会员ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`funds_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='资金流水表';

-- ----------------------------
-- Records of funds
-- ----------------------------
INSERT INTO `funds` VALUES ('1', '1', '2', '1', '2', null, 'remark', '2018-09-25 17:13:16');

-- ----------------------------
-- Table structure for reward
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `reward_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '奖励ID',
  `user_id` int(10) NOT NULL COMMENT '会员ID',
  `reward_amount` int(11) DEFAULT NULL COMMENT '奖励金额',
  `all_amount` int(11) DEFAULT NULL COMMENT '团队上月总利润',
  `user_level` int(1) DEFAULT NULL COMMENT '会员级别',
  `reward_rate` int(2) DEFAULT NULL COMMENT '费率',
  `senddate` int(11) DEFAULT NULL COMMENT '发奖日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`reward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='团队建设月奖表';

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES ('1', '1', '2', '2', '1', '1', '20180925', '2018-09-25 16:33:44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `name` varchar(50) DEFAULT NULL COMMENT '会员姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '会员密码',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `eamail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` int(2) DEFAULT '0' COMMENT '会员状态(0正常，1冻结，2失效)',
  `recommend_id` int(10) DEFAULT NULL COMMENT '推荐会员ID',
  `level` int(1) DEFAULT NULL COMMENT '会员级别',
  `amount` int(11) DEFAULT NULL COMMENT '账户金额',
  `freeze_amount` int(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL COMMENT '代理商收货地址',
  `bank_id` int(20) DEFAULT NULL COMMENT '银行卡号',
  `bank_address` varchar(100) DEFAULT NULL COMMENT '开户行地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='代理商表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1name', '1', '13560005194', '11231@qq.com', '0', null, '1', '1', '1', '深圳市', '6217000', '深圳市', '2018-09-25 16:29:29', '2018-09-25 16:29:32');
