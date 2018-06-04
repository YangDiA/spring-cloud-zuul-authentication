/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-04 18:40:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `menuName` varchar(50) NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单连接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `deep` int(11) DEFAULT NULL COMMENT '深度',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `dataResource` varchar(50) DEFAULT NULL COMMENT '数据权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '会员管理', '0', null, '&#xe6b8;', '0', '1', '01', '');
INSERT INTO `menu` VALUES ('10', '分类管理', '8', './pages/article/category.html', '&#xe6a7;', '9', '2', '0202', null);
INSERT INTO `menu` VALUES ('11', '订单管理', '0', null, '&#xe723;', '10', '1', '03', null);
INSERT INTO `menu` VALUES ('12', '订单列表1', '11', './pages/order/list.html', '&#xe6a7;', '11', '2', '0301', null);
INSERT INTO `menu` VALUES ('13', '管理员管理', '0', null, '&#xe726;', '12', '1', '04', null);
INSERT INTO `menu` VALUES ('14', '管理员列表', '13', './pages/admin/list.html', '&#xe6a7;', '13', '2', '0401', null);
INSERT INTO `menu` VALUES ('15', '角色管理', '13', './pages/admin/role.html', '&#xe6a7;', '14', '2', '0402', null);
INSERT INTO `menu` VALUES ('16', '用户管理', '13', './pages/admin/user/list.html', '&#xe6a7;', '15', '2', '0403', null);
INSERT INTO `menu` VALUES ('17', '菜单管理', '13', './pages/admin/menu/list.html', '&#xe6a7;', '16', '2', '0404', null);
INSERT INTO `menu` VALUES ('18', '角色菜单', '13', './pages/admin/role/list.html', '&#xe6a7;', '17', '2', '0405', null);
INSERT INTO `menu` VALUES ('19', '权限分类', '13', './pages/admin/dataauthority/cate.html', '&#xe6a7;', '18', '2', '0406', null);
INSERT INTO `menu` VALUES ('2', '会员列表', '1', './pages/member/list.html', '&#xe6a7;', '1', '2', '0101', null);
INSERT INTO `menu` VALUES ('20', '权限管理', '13', './pages/admin/dataauthority/apirule.html', '&#xe6a7;', '19', '2', '0407', null);
INSERT INTO `menu` VALUES ('21', '系统统计', '0', null, '&#xe6ce;', '20', '1', '05', null);
INSERT INTO `menu` VALUES ('22', '拆线图', '21', './pages/echarts/echarts1.html', '&#xe6a7;', '21', '2', '0501', null);
INSERT INTO `menu` VALUES ('23', '柱状图', '21', './pages/echarts/echarts2.html', '&#xe6a7;', '22', '2', '0502', null);
INSERT INTO `menu` VALUES ('24', '地图', '21', './pages/echarts/echarts3.html', '&#xe6a7;', '23', '2', '0503', null);
INSERT INTO `menu` VALUES ('25', '饼图', '21', './pages/echarts/echarts4.html', '&#xe6a7;', '24', '2', '0504', null);
INSERT INTO `menu` VALUES ('26', '雷达图', '21', './pages/echarts/echarts5.html', '&#xe6a7;', '25', '2', '0505', null);
INSERT INTO `menu` VALUES ('27', 'k线图', '21', './pages/echarts/echarts6.html', '&#xe6a7;', '26', '2', '0506', null);
INSERT INTO `menu` VALUES ('28', '热力图', '21', './pages/echarts/echarts7.html', '&#xe6a7;', '27', '2', '0507', null);
INSERT INTO `menu` VALUES ('29', '仪表图', '21', './pages/echarts/echarts8.html', '&#xe6a7;', '28', '2', '0508', null);
INSERT INTO `menu` VALUES ('3', '会员删除', '1', './pages/member/del.html', '&#xe6a7;', '2', '2', '0102', null);
INSERT INTO `menu` VALUES ('30', '地图DIY实例', '21', './pages/echarts/echarts9.html', '&#xe6a7;', '29', '2', '0509', null);
INSERT INTO `menu` VALUES ('4', '会员管理', '1', null, '&#xe70b;', '3', '2', '0103', null);
INSERT INTO `menu` VALUES ('5', '输入框操作', '4', './pages/member/addInput.html', '&#xe6a7;', '4', '3', '010301', null);
INSERT INTO `menu` VALUES ('6', '会员删除', '4', './pages/404.html', '&#xe6a7;', '5', '3', '010302', null);
INSERT INTO `menu` VALUES ('7', '等级管理', '4', './pages/404.html', '&#xe6a7;', '6', '3', '010303', null);
INSERT INTO `menu` VALUES ('8', '文章管理', '0', null, '&#xe705;', '7', '1', '02', null);
INSERT INTO `menu` VALUES ('9', '文章列表', '8', './pages/article/list.html', '&#xe6a7;', '8', '2', '0201', null);
