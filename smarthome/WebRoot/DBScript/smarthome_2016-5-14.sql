/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : smarthome

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-05-14 01:49:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hy_devices
-- ----------------------------
DROP TABLE IF EXISTS `hy_devices`;
CREATE TABLE `hy_devices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceNum` varchar(10) DEFAULT NULL COMMENT '自定义num',
  `deviceName` varchar(255) DEFAULT NULL COMMENT '设备名称可以自定义',
  `status` varchar(255) DEFAULT NULL COMMENT '设备的状态',
  `homeID` int(11) NOT NULL COMMENT '房屋id',
  `category` varchar(200) DEFAULT NULL COMMENT '类别',
  `userID` int(11) NOT NULL,
  `recordTime` varchar(200) DEFAULT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='设备表';

-- ----------------------------
-- Records of hy_devices
-- ----------------------------
INSERT INTO `hy_devices` VALUES ('22', '333', '灯泡3', '0', '2', null, '1', '2016-03-31 22:16:34', '2016-04-08 15:12:07');
INSERT INTO `hy_devices` VALUES ('27', '009', '灯泡44', '0', '3', null, '1', '2016-04-05 21:47:30', '2016-04-06 20:40:44');
INSERT INTO `hy_devices` VALUES ('28', '009', '灯泡44', '0', '3', null, '1', '2016-05-14 21:56:05', '2016-04-06 20:40:44');
INSERT INTO `hy_devices` VALUES ('29', 'test231', 'test32', '0', '3', null, '1', '2016-05-14 13:22:21', null);

-- ----------------------------
-- Table structure for hy_health
-- ----------------------------
DROP TABLE IF EXISTS `hy_health`;
CREATE TABLE `hy_health` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `health` tinyint(4) DEFAULT NULL COMMENT '健康指数',
  `recommend` varchar(2000) DEFAULT NULL,
  `recordTime` varchar(200) DEFAULT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='健康记录表';

-- ----------------------------
-- Records of hy_health
-- ----------------------------

-- ----------------------------
-- Table structure for hy_home
-- ----------------------------
DROP TABLE IF EXISTS `hy_home`;
CREATE TABLE `hy_home` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(200) DEFAULT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `ownerID` int(11) NOT NULL COMMENT '屋主的id',
  `deviceNum` int(11) DEFAULT NULL COMMENT '设备数量',
  `temperature` varchar(255) DEFAULT NULL COMMENT '温度（度c）',
  `humidity` varchar(255) DEFAULT NULL COMMENT '湿度（RH相对湿度）',
  `recordTime` varchar(200) DEFAULT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  `usreID` int(11) DEFAULT NULL,
  `logoImage` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='房屋表';

-- ----------------------------
-- Records of hy_home
-- ----------------------------
INSERT INTO `hy_home` VALUES ('2', '中国', '广东省-广州市-白云区', '1', '1', '26', '', '2016-02-02 17:25:26', '', '1', '');
INSERT INTO `hy_home` VALUES ('3', '中国', '广东省-广州市-海珠区', '1', '3', null, null, '2016-04-02 16:19:23', null, '1', null);
INSERT INTO `hy_home` VALUES ('4', '中国', '广东省-广州市-市辖区', '1', null, null, null, '2016-05-13 16:20:40', null, '1', null);
INSERT INTO `hy_home` VALUES ('8', '中国', '广东省-广州市-市辖区', '7', null, null, null, '2016-05-13 16:33:20', null, '1', null);
INSERT INTO `hy_home` VALUES ('11', '中国', '广东省-广州市-花都区-什么鬼地区', '7', null, null, null, '2016-05-14 17:25:26', null, '1', null);

-- ----------------------------
-- Table structure for hy_memoradum
-- ----------------------------
DROP TABLE IF EXISTS `hy_memoradum`;
CREATE TABLE `hy_memoradum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) NOT NULL COMMENT '事件主题',
  `startTime` varchar(200) NOT NULL COMMENT '开始提醒时间',
  `endTime` varchar(200) NOT NULL COMMENT '结束时间',
  `contents` varchar(2000) DEFAULT NULL COMMENT '内容',
  `num` int(4) DEFAULT NULL COMMENT '提醒次数 最多10次',
  `recordTime` varchar(200) NOT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  `userID` int(11) NOT NULL,
  `color` varchar(20) DEFAULT NULL COMMENT '事件颜色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='备忘录表';

-- ----------------------------
-- Records of hy_memoradum
-- ----------------------------
INSERT INTO `hy_memoradum` VALUES ('1', '232', '2016-01-21 09:15', '2016-01-22 15:15', '32132', '3', '2016-03-22 15:15:10', '', '1', null);

-- ----------------------------
-- Table structure for hy_permission
-- ----------------------------
DROP TABLE IF EXISTS `hy_permission`;
CREATE TABLE `hy_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(200) NOT NULL,
  `permissionName` varchar(255) NOT NULL,
  `module` varchar(200) DEFAULT NULL COMMENT '模块',
  `recordTime` varchar(200) DEFAULT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of hy_permission
-- ----------------------------
INSERT INTO `hy_permission` VALUES ('1', 'userList', '查询用户', 'user', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('2', 'addUser', '添加用户', 'user', '2016-04-04 00:43:00', '', '1');
INSERT INTO `hy_permission` VALUES ('3', 'updateUser', '更新用户', 'user', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('4', 'deleteUser', '删除用户', 'user', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('5', 'forbidUser', '禁止用户', 'user', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('6', 'deviceList', '查询设备', 'device', '2016-04-04 00:43:002016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('7', 'addDevice', '添加设备', 'device', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('8', 'updateDevice', '更新设备', 'device', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('9', 'deleteDevice', '删除设备', 'device', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('10', 'homeList', '查询住所', 'home', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('11', 'addHome', '添加住所', 'home', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('12', 'updateHome', '更新住所', 'home', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('13', 'deleteHome', '删除住所', 'home', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('14', 'roleList', '查询角色', 'role', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('15', 'saveRole', '添加角色', 'role', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('16', 'updateRole', '更新角色', 'role', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('17', 'deleteRole', '删除角色', 'role', '2016-04-04 00:43:00', null, '1');
INSERT INTO `hy_permission` VALUES ('18', 'setRole', '用户授权', 'role', '2016-04-04 00:43:00', null, '1');

-- ----------------------------
-- Table structure for hy_permission_roles
-- ----------------------------
DROP TABLE IF EXISTS `hy_permission_roles`;
CREATE TABLE `hy_permission_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) NOT NULL,
  `permissionID` int(11) NOT NULL,
  `recordTime` varchar(200) DEFAULT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  `adminID` int(11) NOT NULL COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='权限-角色表';

-- ----------------------------
-- Records of hy_permission_roles
-- ----------------------------
INSERT INTO `hy_permission_roles` VALUES ('1', '1', '1', '2016-04-04 02:22:14', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('2', '2', '2', '2016-04-04 00:44:29', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('35', '7', '6', '2016-04-11 17:31:05', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('36', '7', '7', '2016-04-11 17:31:05', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('37', '7', '8', '2016-04-11 17:31:05', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('38', '7', '9', '2016-04-11 17:31:05', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('42', '9', '1', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('43', '9', '2', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('44', '9', '4', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('45', '9', '5', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('46', '9', '6', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('47', '9', '7', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('48', '9', '9', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('49', '9', '10', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('50', '9', '11', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('51', '9', '12', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('52', '9', '13', '2016-04-21 21:37:50', null, '1');
INSERT INTO `hy_permission_roles` VALUES ('53', '9', '14', '2016-04-21 21:37:50', null, '1');

-- ----------------------------
-- Table structure for hy_resetpwd
-- ----------------------------
DROP TABLE IF EXISTS `hy_resetpwd`;
CREATE TABLE `hy_resetpwd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `emailVer` varchar(20) NOT NULL COMMENT '随机生产的验证码',
  `recordTime` varchar(200) DEFAULT NULL COMMENT '找回时间',
  `status` tinyint(4) NOT NULL COMMENT '是否校验码有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户建议休息表';

-- ----------------------------
-- Records of hy_resetpwd
-- ----------------------------
INSERT INTO `hy_resetpwd` VALUES ('9', '19', 'skReEV', '2016-03-27 22:20:22', '0');
INSERT INTO `hy_resetpwd` VALUES ('10', '1', 'XLTMYZ', '2016-05-11 23:25:31', '0');

-- ----------------------------
-- Table structure for hy_resetsystem
-- ----------------------------
DROP TABLE IF EXISTS `hy_resetsystem`;
CREATE TABLE `hy_resetsystem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `period` tinyint(4) DEFAULT NULL COMMENT '人生阶段值',
  `startTime` varchar(20) DEFAULT NULL,
  `endTime` varchar(20) DEFAULT NULL,
  `recommend` varchar(10) DEFAULT NULL COMMENT '建议睡眠时长',
  `userId` int(11) DEFAULT NULL COMMENT '修改人id',
  `exTime` varchar(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='系统默认休息表';

-- ----------------------------
-- Records of hy_resetsystem
-- ----------------------------
INSERT INTO `hy_resetsystem` VALUES ('1', '1', '20:00', null, '16~20', null, null);
INSERT INTO `hy_resetsystem` VALUES ('2', '2', '20:00', '', '16~18', null, '');
INSERT INTO `hy_resetsystem` VALUES ('3', '3', '20:00', '', '15~16', null, '');
INSERT INTO `hy_resetsystem` VALUES ('4', '4', '20:00', '', '13~18', null, '');
INSERT INTO `hy_resetsystem` VALUES ('5', '5', '20:00', '', '14~16', null, '');
INSERT INTO `hy_resetsystem` VALUES ('6', '6', '20:00', '', '13~16', null, '');
INSERT INTO `hy_resetsystem` VALUES ('7', '7', '20:00', '', '12~15', null, '');
INSERT INTO `hy_resetsystem` VALUES ('8', '8', '20:00', '', '14~17', null, '');
INSERT INTO `hy_resetsystem` VALUES ('9', '9', '20:00', '', '13~14', null, '');
INSERT INTO `hy_resetsystem` VALUES ('10', '10', '20:00', '', '12~13', null, '');
INSERT INTO `hy_resetsystem` VALUES ('11', '11', '20:00', '', '10~12', null, null);
INSERT INTO `hy_resetsystem` VALUES ('12', '12', '21:00', '', '9~11', null, '');
INSERT INTO `hy_resetsystem` VALUES ('13', '13', '23:00', '', '8~9', null, '');
INSERT INTO `hy_resetsystem` VALUES ('14', '14', '23:00', '', '8~9', null, '');
INSERT INTO `hy_resetsystem` VALUES ('15', '15', '22:00', '', '6.5~7', null, '');
INSERT INTO `hy_resetsystem` VALUES ('16', '16', '21:00', '', '6.5~7', null, '');

-- ----------------------------
-- Table structure for hy_restrecord
-- ----------------------------
DROP TABLE IF EXISTS `hy_restrecord`;
CREATE TABLE `hy_restrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `startTime` varchar(20) DEFAULT NULL COMMENT '睡觉时间',
  `endTime` varchar(20) DEFAULT NULL COMMENT '起床时间',
  `recommendRestTime` varchar(20) DEFAULT NULL COMMENT '建议休息时间',
  `recommendWakeTime` varchar(20) DEFAULT NULL COMMENT '建议睡眠时长',
  `recordNum` tinyint(4) DEFAULT NULL COMMENT '记录多少次给出推荐意见',
  `num` tinyint(4) DEFAULT NULL COMMENT '第几次记录',
  `recordTime` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='休息记录表';

-- ----------------------------
-- Records of hy_restrecord
-- ----------------------------
INSERT INTO `hy_restrecord` VALUES ('1', '31', null, null, '20:00', '13~14', null, null, null);
INSERT INTO `hy_restrecord` VALUES ('2', '32', null, null, '20:00', '12~13', null, null, null);
INSERT INTO `hy_restrecord` VALUES ('3', '37', null, null, '20:00', '10~12', null, null, null);
INSERT INTO `hy_restrecord` VALUES ('4', '38', null, null, '20:00', '15~16', null, null, null);
INSERT INTO `hy_restrecord` VALUES ('5', '39', null, null, '20:00', '13~14', null, null, null);
INSERT INTO `hy_restrecord` VALUES ('6', '40', null, null, '20:00', '14~17', null, null, null);

-- ----------------------------
-- Table structure for hy_roles
-- ----------------------------
DROP TABLE IF EXISTS `hy_roles`;
CREATE TABLE `hy_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `recordTime` varchar(200) NOT NULL,
  `exchangeTime` varchar(200) DEFAULT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of hy_roles
-- ----------------------------
INSERT INTO `hy_roles` VALUES ('1', '高级管理员', 'admin', '2016年3月12日 15:27:03', '2016年3月12日 15:27:08', '1');
INSERT INTO `hy_roles` VALUES ('2', '普通管理员', 'sadmin', '2016-04-04 02:21:44', null, '1');
INSERT INTO `hy_roles` VALUES ('7', '设备管理员', '设备管理员', '2016-04-11 16:13:36', '2016-04-23 22:47:15', '1');
INSERT INTO `hy_roles` VALUES ('9', '灰熊专用', '32132', '2016-04-21 21:37:50', null, '1');

-- ----------------------------
-- Table structure for hy_user
-- ----------------------------
DROP TABLE IF EXISTS `hy_user`;
CREATE TABLE `hy_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `loginTime` varchar(100) DEFAULT NULL,
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `registerTime` datetime DEFAULT NULL COMMENT '注册时间',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `birthday` varchar(200) DEFAULT NULL COMMENT '出生年月日',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `period` tinyint(4) DEFAULT NULL COMMENT '处于人生那个阶段',
  `exchangeTime` varchar(200) DEFAULT NULL COMMENT '修改时间',
  `adminID` int(11) DEFAULT NULL COMMENT '管理员id',
  `preloginTime` varchar(100) DEFAULT NULL COMMENT '上一次登录时间',
  `logoImage` varchar(2000) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL,
  `sessionID` varchar(200) DEFAULT NULL,
  `isAdmin` tinyint(4) DEFAULT NULL COMMENT '是否为后台管理员',
  `note` varchar(2000) DEFAULT NULL COMMENT '个人说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of hy_user
-- ----------------------------
INSERT INTO `hy_user` VALUES ('1', 'admin', '4297f44b13955235245b2497399d7a93', '2016-05-09 23:32', '1', '2016-03-26 00:00:00', '659174520@qq.com', '2016-03-08', '0', '3', '2016-05-13 13:06:23', '1', null, 'dist/img/upload/1/20160513130623410.png', '13726229081', '6A89154CAFFD0129F031C0519BB604DA', '1', '你不傻么？是么');
INSERT INTO `hy_user` VALUES ('2', 'adminB', 'c4ca4238a0b923820dcc509a6f75849b', '2016-03-19 11:02', '1', '2016-03-26 00:00:00', null, '2016-03-08', '0', null, null, null, null, 'dist/img/user2-160x160.jpg', null, '270B48E1715D8231EF99BAB5428B555E', '1', null);
INSERT INTO `hy_user` VALUES ('31', 'huige', 'c4ca4238a0b923820dcc509a6f75849b', '2016-04-24 20:39', '1', '2016-04-08 17:27:12', '232@qq.com', '2010-11-11', '6', '11', null, null, null, 'dist/img/user2-160x160.jpg', '15312312321', '', '1', '你不傻么？是么');
INSERT INTO `hy_user` VALUES ('32', 'huige3344ai', '4297f44b13955235245b2497399d7a93', null, '1', '2016-04-09 00:55:52', '34232@qq.com', '2012-12-13', '4', '10', null, null, null, 'dist/img/user2-160x160.jpg', '15723213213', null, null, null);
INSERT INTO `hy_user` VALUES ('37', '321321', '96e79218965eb72c92a549dd5a330112', null, '1', '2016-04-12 12:36:07', '3213@qq.com', '2011-11-11', '5', '11', null, '1', null, 'dist/img/user2-160x160.jpg', '13732132113', null, '0', null);
INSERT INTO `hy_user` VALUES ('38', '32132133', 'e10adc3949ba59abbe56e057f20f883e', null, '1', '2016-05-14 22:01:49', '78923139@qq.com', '2015-02-11', '1', '3', null, '1', null, 'dist/img/user2-160x160.jpg', '13132321321', null, '0', null);
INSERT INTO `hy_user` VALUES ('39', '3213344', '4297f44b13955235245b2497399d7a93', null, '1', '2016-05-14 00:14:23', '333213@qq.com', '2013-11-11', '3', '9', null, '1', null, 'dist/img/user2-160x160.jpg', '', null, '0', null);
INSERT INTO `hy_user` VALUES ('40', 'jiayuyang', '4297f44b13955235245b2497399d7a93', null, '1', '2016-05-14 23:24:43', '5656@qq.com', '2014-11-11', '2', '8', null, null, null, 'dist/img/user2-160x160.jpg', '', null, null, null);

-- ----------------------------
-- Table structure for hy_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `hy_user_roles`;
CREATE TABLE `hy_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL COMMENT '用户id',
  `roleID` int(11) NOT NULL COMMENT '角色id',
  `recordTime` varchar(200) DEFAULT NULL COMMENT '创建时间',
  `exchangeTime` varchar(200) DEFAULT NULL COMMENT '修改时间',
  `adminID` int(11) NOT NULL COMMENT '管理员id',
  PRIMARY KEY (`id`),
  KEY `r_uId` (`userID`),
  KEY `rId` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='人员-角色表';

-- ----------------------------
-- Records of hy_user_roles
-- ----------------------------
INSERT INTO `hy_user_roles` VALUES ('1', '31', '2', null, null, '1');
INSERT INTO `hy_user_roles` VALUES ('5', '31', '7', '2016-04-20 20:14:23', null, '1');
INSERT INTO `hy_user_roles` VALUES ('6', '31', '8', '2016-04-20 20:14:24', null, '1');
INSERT INTO `hy_user_roles` VALUES ('7', '1', '9', '2016-04-21 21:38:08', null, '1');

-- ----------------------------
-- View structure for news_devices
-- ----------------------------
DROP VIEW IF EXISTS `news_devices`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `news_devices` AS SELECT COUNT(d.recordTime) AS dNum,d.recordTime FROM  hy_devices AS d WHERE  DATE_FORMAT(d.recordTime,'%Y-%m-%d')=(SELECT CURDATE()) ;

-- ----------------------------
-- View structure for news_home
-- ----------------------------
DROP VIEW IF EXISTS `news_home`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `news_home` AS SELECT COUNT(h.recordTime) AS hNum,h.recordTime  FROM  hy_home AS h WHERE  DATE_FORMAT(h.recordTime,'%Y-%m-%d')=(SELECT CURDATE()) ;

-- ----------------------------
-- View structure for news_user
-- ----------------------------
DROP VIEW IF EXISTS `news_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `news_user` AS SELECT COUNT(u.registerTime) AS uNum,u.registerTime FROM hy_user AS u WHERE DATE(u.registerTime)=(SELECT CURDATE()) ;

-- ----------------------------
-- View structure for tip_news
-- ----------------------------
DROP VIEW IF EXISTS `tip_news`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `tip_news` AS SELECT u.uNum AS uNum ,h.hNum AS hNum,dNum FROM
news_user as u 
LEFT JOIN news_home as h 
ON DATE_FORMAT(u.registerTime,'%Y-%m-%d')=DATE_FORMAT(h.recordTime,'%Y-%m-%d')
LEFT JOIN news_devices as d
ON DATE_FORMAT(u.registerTime,'%Y-%m-%d')=DATE_FORMAT(d.recordTime,'%Y-%m-%d') ;
