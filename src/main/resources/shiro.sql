/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2018-11-09 18:18:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '菜单名',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '1：第一级；2：第二级，以此类推',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '如果是第一级，父类ID为0；',
  `code` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '例如admin:delete样式',
  `status` varchar(1) CHARACTER SET utf8 NOT NULL COMMENT '0：不可用；1：可用',
  `create_time` datetime NOT NULL COMMENT '第一次创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `icon` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '界面映射图标',
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '连接地址',
  `sorter` int(2) NOT NULL DEFAULT '0' COMMENT '菜单栏显示排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '管理员管理', '0', '0', 'admin', '1', '2017-11-14 14:59:14', '2018-07-25 19:47:41', 'idcard', 'admin', '2');
INSERT INTO `sys_menu` VALUES ('2', '权限管理', '0', '0', 'role', '1', '2017-11-14 14:59:47', '2018-07-25 19:47:48', 'setting', 'auth', '3');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', '1', '2', 'menu', '1', '2017-11-14 15:00:22', '2018-07-25 20:16:32', '&#xe63c;', 'menu/list', '1');
INSERT INTO `sys_menu` VALUES ('4', '资源权限', '1', '2', 'auth', '1', '2017-11-14 15:00:56', '2018-07-25 20:16:45', '&#xe63c;', 'permission/list', '2');
INSERT INTO `sys_menu` VALUES ('5', '系统用户', '1', '1', 'admin', '1', '2017-11-24 16:48:59', '2018-07-19 14:54:01', '&#xe613;', 'list', '0');
INSERT INTO `sys_menu` VALUES ('6', '角色管理', '1', '2', 'admin', '1', '2017-11-24 16:48:59', '2018-07-25 20:16:54', '&#xe63c;', 'role/list', '3');
INSERT INTO `sys_menu` VALUES ('7', '角色分配', '1', '2', 'admin', '1', '2017-11-24 16:48:59', '2018-07-25 20:17:04', '&#xe63c;', 'role/allocation', '4');
INSERT INTO `sys_menu` VALUES ('76', 'DashBoard', '0', '0', 'dashBoard', '1', '2018-07-19 16:52:59', '2018-07-26 12:34:34', 'desktop', 'home', '1');
INSERT INTO `sys_menu` VALUES ('77', '博客管理', '0', '0', '#', '1', '2018-08-16 19:42:07', '2018-08-16 19:42:07', 'folder', 'blog', '6');
INSERT INTO `sys_menu` VALUES ('79', '博客分类', '1', '77', '#', '1', '2018-08-17 10:12:56', '2018-08-17 10:12:56', 'appstore-o', 'category', '0');
INSERT INTO `sys_menu` VALUES ('80', '文章管理', '1', '77', '#', '1', '2018-08-24 10:27:05', '2018-08-24 10:30:49', ' ', 'list', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '功能名称',
  `code` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'url地址\r\n例如/admin/delete样式',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '0：不可用；1：可用',
  `descr` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '说明',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '1：第一级；2：第二级，以此类推',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '如果是第一级，父类ID为0；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '权限菜单', '#', '1', '权限菜单', '0', '0');
INSERT INTO `sys_permission` VALUES ('2', '资源权限', '#', '1', '资源权限', '0', '0');
INSERT INTO `sys_permission` VALUES ('3', '角色管理', '#', '1', '角色管理', '0', '0');
INSERT INTO `sys_permission` VALUES ('93', '菜单修改', 'menu:update*,save*,delete*,add*', '1', '菜单修改', '1', '1');
INSERT INTO `sys_permission` VALUES ('94', '菜单查看', 'menu:get*,page*,list*,view*', '1', '菜单查看', '1', '1');
INSERT INTO `sys_permission` VALUES ('95', '权限修改', 'permission:update*,save*,delete*', '1', '权限修改', '1', '2');
INSERT INTO `sys_permission` VALUES ('96', '权限查看', 'permission:get*,page*,list*,view*', '1', '权限查看', '1', '2');
INSERT INTO `sys_permission` VALUES ('97', '角色修改', 'role:update*,save*,delete*', '1', '角色修改', '1', '3');
INSERT INTO `sys_permission` VALUES ('98', '角色查看', 'role:get*,page*,list*,view*', '1', '角色查看', '1', '3');
INSERT INTO `sys_permission` VALUES ('111', '系统用户', '#', '1', '系统用户', '0', '0');
INSERT INTO `sys_permission` VALUES ('112', '系统用户查看', 'admin:get*,page*,list*,view*', '1', '系统用户查看', '1', '111');
INSERT INTO `sys_permission` VALUES ('113', '系统用户修改', 'admin:update*,save*,delete*', '1', '系统用户修改', '1', '111');
INSERT INTO `sys_permission` VALUES ('119', '菜单所有操作', 'menu:*', '1', '菜单所有操作', '1', '1');
INSERT INTO `sys_permission` VALUES ('128', '权限所有操作', 'permission:*', '1', '权限所有操作', '1', '2');
INSERT INTO `sys_permission` VALUES ('132', '角色所有操作', 'role:*', '1', '角色所有操作', '1', '3');
INSERT INTO `sys_permission` VALUES ('138', '系统用户所有操作', 'admin:*', '1', '系统用户所有操作', '1', '111');
INSERT INTO `sys_permission` VALUES ('146', '图片管理', '#', '1', '图片管理', '0', '0');
INSERT INTO `sys_permission` VALUES ('147', '图片操作', 'upload:*', '1', '图片操作', '1', '146');
INSERT INTO `sys_permission` VALUES ('149', '用户管理', '#', '1', '用户管理', '0', '0');
INSERT INTO `sys_permission` VALUES ('150', '用户查看', 'user:get*,page*,list*,view*', '1', '用户查看', '1', '149');
INSERT INTO `sys_permission` VALUES ('151', '用户修改', 'user:update*,save*,delete*', '1', '用户修改', '1', '149');
INSERT INTO `sys_permission` VALUES ('152', '用户所有操作', 'user:*', '1', '用户所有操作', '1', '149');
INSERT INTO `sys_permission` VALUES ('174', '广告管理', '#', '1', '广告管理', '0', '0');
INSERT INTO `sys_permission` VALUES ('175', '广告查看', 'advs:get*,page*,list*,view*', '1', '广告查看', '1', '174');
INSERT INTO `sys_permission` VALUES ('176', '广告修改', 'advs:update*,save*,delete*,add*,edit*', '1', '广告修改', '1', '174');
INSERT INTO `sys_permission` VALUES ('177', '广告位所有操作', 'advsPosition:*', '1', '广告位所有操作', '1', '174');
INSERT INTO `sys_permission` VALUES ('178', '博客管理', '#', '1', '博客管理', '0', '0');
INSERT INTO `sys_permission` VALUES ('179', '博客操作', 'blog:*', '1', '博客操作', '1', '178');
INSERT INTO `sys_permission` VALUES ('180', '通知管理', '#', '1', '通知管理', '0', '0');
INSERT INTO `sys_permission` VALUES ('181', '通知操作', 'notice:*', '1', '通知操作', '1', '180');
INSERT INTO `sys_permission` VALUES ('182', '测试管理', '#', '1', '测试', '0', '0');
INSERT INTO `sys_permission` VALUES ('183', '测试所有操作', 'test:*', '1', '测试所有操作', '1', '182');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) DEFAULT NULL COMMENT '角色代码',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `status` varchar(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '系统管理员', '1');
INSERT INTO `sys_role` VALUES ('2', 'user', '普通用户', '1');
INSERT INTO `sys_role` VALUES ('20', '000003', '查阅用户', '1');
INSERT INTO `sys_role` VALUES ('29', 'guest', '无查询权限', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('31', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('32', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('73', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('138', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('141', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('142', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('157', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('158', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('160', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('161', '1', '80');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `perm_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `perm_id` (`perm_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('79', '20', '96');
INSERT INTO `sys_role_permission` VALUES ('80', '20', '98');
INSERT INTO `sys_role_permission` VALUES ('86', '20', '94');
INSERT INTO `sys_role_permission` VALUES ('130', '1', '128');
INSERT INTO `sys_role_permission` VALUES ('131', '1', '132');
INSERT INTO `sys_role_permission` VALUES ('145', '1', '119');
INSERT INTO `sys_role_permission` VALUES ('154', '2', '94');
INSERT INTO `sys_role_permission` VALUES ('157', '1', '147');
INSERT INTO `sys_role_permission` VALUES ('158', '1', '152');
INSERT INTO `sys_role_permission` VALUES ('171', '1', '175');
INSERT INTO `sys_role_permission` VALUES ('172', '1', '176');
INSERT INTO `sys_role_permission` VALUES ('173', '1', '177');
INSERT INTO `sys_role_permission` VALUES ('176', '1', '150');
INSERT INTO `sys_role_permission` VALUES ('177', '1', '151');
INSERT INTO `sys_role_permission` VALUES ('192', '1', '146');
INSERT INTO `sys_role_permission` VALUES ('193', '1', '149');
INSERT INTO `sys_role_permission` VALUES ('195', '1', '174');
INSERT INTO `sys_role_permission` VALUES ('207', '29', '150');
INSERT INTO `sys_role_permission` VALUES ('208', '1', '178');
INSERT INTO `sys_role_permission` VALUES ('209', '1', '179');
INSERT INTO `sys_role_permission` VALUES ('210', '1', '180');
INSERT INTO `sys_role_permission` VALUES ('211', '1', '181');
INSERT INTO `sys_role_permission` VALUES ('214', '1', '138');
INSERT INTO `sys_role_permission` VALUES ('215', '1', '182');
INSERT INTO `sys_role_permission` VALUES ('216', '1', '183');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '登录帐号',
  `nickname` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(12) NOT NULL COMMENT '联系帐号|登录帐号',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '1:有效，0:禁止',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'admin@shop.com', '12345678958', '5a55d78af3a2929cfad9545fe087825d0d29ff270ca34cd6676cfe7e38175998b84318077c9f97757f2ca11a6eae4f255b8e7d9945ac18e3dd35d6f717d3b1e9', '2017-06-16 11:15:33', '2018-07-20 14:36:26', '2018-11-09 10:15:32', '1');
INSERT INTO `sys_user` VALUES ('71', 'zt', 'zt1', 'zt@qq.com', '12365987458', '2f2fa5372063be3d6401c40c065645a5faaacd2ad71c4684f13b5b0408b79989a6fbf27f343fe31c378554d99b2cca479ccfcd2896ba8b8a7cd00f7de5c08b3d', '2018-02-06 17:57:25', '2018-07-20 15:39:28', '2018-07-20 14:42:29', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('59', '2', '71');
INSERT INTO `sys_user_role` VALUES ('60', '1', '1');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '小张');
INSERT INTO `test` VALUES ('2', 'asdf');
