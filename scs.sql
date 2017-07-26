/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.11
Source Server Version : 50719
Source Host           : 192.168.1.11:3306
Source Database       : scs

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-07-25 16:40:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accident
-- ----------------------------
DROP TABLE IF EXISTS `accident`;
CREATE TABLE `accident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `car` bigint(20) DEFAULT NULL COMMENT '车辆',
  `person` bigint(20) DEFAULT NULL COMMENT '肇事人',
  `adate` date DEFAULT NULL COMMENT '肇事日期',
  `addr` varchar(500) DEFAULT NULL COMMENT '肇事地点',
  `ares` bigint(20) DEFAULT NULL COMMENT '事故责任',
  `money` decimal(24,4) DEFAULT NULL COMMENT '应赔金额',
  `rmoney` decimal(24,4) DEFAULT NULL COMMENT '实赔金额',
  `content` text COMMENT '事故经过',
  `pics` varchar(500) DEFAULT NULL COMMENT '现场照片',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事故管理';

-- ----------------------------
-- Records of accident
-- ----------------------------

-- ----------------------------
-- Table structure for apicar
-- ----------------------------
DROP TABLE IF EXISTS `apicar`;
CREATE TABLE `apicar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `busnumber` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `realtime` datetime DEFAULT NULL COMMENT '时间',
  `longitude` decimal(12,8) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(12,8) DEFAULT NULL COMMENT '纬度',
  `gposition` varchar(255) DEFAULT NULL COMMENT '地理位置',
  `police` varchar(100) DEFAULT NULL COMMENT '报警',
  `oilusage` varchar(100) DEFAULT NULL COMMENT '油料使用量',
  `container` varchar(50) DEFAULT NULL COMMENT '货箱',
  `cardoor` varchar(50) DEFAULT NULL COMMENT '车门',
  `fption` varchar(100) DEFAULT NULL COMMENT '油耗',
  `temperature` varchar(100) DEFAULT NULL COMMENT '温度',
  `engine` varchar(100) DEFAULT NULL COMMENT '发动机',
  `mileage` decimal(12,3) DEFAULT NULL COMMENT '里程',
  `direction` char(1) DEFAULT NULL COMMENT '方向',
  `speed` decimal(12,3) DEFAULT NULL COMMENT '速度',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `state1` varchar(100) DEFAULT NULL COMMENT '状态1',
  `state2` varchar(100) DEFAULT NULL COMMENT '状态2',
  `state5` varchar(100) DEFAULT NULL COMMENT '状态5',
  `state6` varchar(100) DEFAULT NULL COMMENT '状态6',
  `state3` varchar(100) DEFAULT NULL COMMENT '状态3',
  `state4` varchar(100) DEFAULT NULL COMMENT '状态4',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆接口';

-- ----------------------------
-- Records of apicar
-- ----------------------------

-- ----------------------------
-- Table structure for apiholdgps
-- ----------------------------
DROP TABLE IF EXISTS `apiholdgps`;
CREATE TABLE `apiholdgps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `imei` varchar(15) DEFAULT NULL COMMENT '15位设备唯一序号',
  `time_begin` datetime DEFAULT NULL COMMENT '发生时间',
  `is_reply` tinyint(1) DEFAULT NULL COMMENT '是否为响应',
  `is_track` tinyint(1) DEFAULT NULL COMMENT '是否追踪',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` decimal(12,8) DEFAULT NULL COMMENT '经度',
  `Lat` decimal(12,8) DEFAULT NULL COMMENT '纬度',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '1' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '1' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿姨机-gps';

-- ----------------------------
-- Records of apiholdgps
-- ----------------------------

-- ----------------------------
-- Table structure for apiholdmsg
-- ----------------------------
DROP TABLE IF EXISTS `apiholdmsg`;
CREATE TABLE `apiholdmsg` (
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `deviceid` varchar(15) DEFAULT NULL COMMENT '15位设备唯一序号',
  `communityid` varchar(100) DEFAULT NULL COMMENT '机构ID',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿姨机-msg';

-- ----------------------------
-- Records of apiholdmsg
-- ----------------------------

-- ----------------------------
-- Table structure for apiholdshutdown
-- ----------------------------
DROP TABLE IF EXISTS `apiholdshutdown`;
CREATE TABLE `apiholdshutdown` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `imei` varchar(15) DEFAULT NULL COMMENT '15位设备唯一序号',
  `time_begin` datetime DEFAULT NULL COMMENT '发生时间',
  `remaining_power` int(11) DEFAULT NULL COMMENT '剩余电量',
  `type` varchar(100) DEFAULT NULL COMMENT '开/关机类型  0开机 2普通  3低电',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿姨机-apiholdshutdown';

-- ----------------------------
-- Records of apiholdshutdown
-- ----------------------------

-- ----------------------------
-- Table structure for apiholdsos
-- ----------------------------
DROP TABLE IF EXISTS `apiholdsos`;
CREATE TABLE `apiholdsos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `imei` varchar(15) DEFAULT NULL COMMENT '15位设备唯一序号',
  `time_begin` datetime DEFAULT NULL COMMENT '发生时间',
  `heartrate` int(11) DEFAULT NULL COMMENT '心率',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` decimal(12,8) DEFAULT NULL COMMENT '经度',
  `Lat` decimal(12,8) DEFAULT NULL COMMENT '纬度',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿姨机-apiholdsos';

-- ----------------------------
-- Records of apiholdsos
-- ----------------------------

-- ----------------------------
-- Table structure for apiholdtumble
-- ----------------------------
DROP TABLE IF EXISTS `apiholdtumble`;
CREATE TABLE `apiholdtumble` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `imei` varchar(15) DEFAULT NULL COMMENT '15位设备唯一序号',
  `time_begin` datetime DEFAULT NULL COMMENT '发生时间',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `lon` decimal(12,8) DEFAULT NULL COMMENT '经度',
  `Lat` decimal(12,8) DEFAULT NULL COMMENT '纬度',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿姨机-apiholdtumble';

-- ----------------------------
-- Records of apiholdtumble
-- ----------------------------

-- ----------------------------
-- Table structure for apistepcounter
-- ----------------------------
DROP TABLE IF EXISTS `apistepcounter`;
CREATE TABLE `apistepcounter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `imei` varchar(15) DEFAULT NULL COMMENT '15位设备唯一序号',
  `time_begin` datetime DEFAULT NULL COMMENT '发生时间',
  `value` int(11) DEFAULT NULL COMMENT '步数',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿姨机-apistepcounter';

-- ----------------------------
-- Records of apistepcounter
-- ----------------------------

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `no` varchar(8) NOT NULL COMMENT '地址代码',
  `type` varchar(2) NOT NULL COMMENT '地址类型：1省级、2市级、3县级',
  `cnm` varchar(50) NOT NULL COMMENT '地址中文名',
  `enm` varchar(50) DEFAULT NULL COMMENT '地址英文名',
  `abbrnm` varchar(50) NOT NULL COMMENT '地址简称',
  `pno` varchar(8) NOT NULL COMMENT '父级地址码',
  `zno` varchar(8) DEFAULT NULL COMMENT '邮编',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idxtype` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区';

-- ----------------------------
-- Records of area
-- ----------------------------

-- ----------------------------
-- Table structure for atd_manage
-- ----------------------------
DROP TABLE IF EXISTS `atd_manage`;
CREATE TABLE `atd_manage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `person` bigint(20) DEFAULT NULL COMMENT '职员',
  `atdtype` tinyint(4) DEFAULT NULL COMMENT '申报类型',
  `ltype` varchar(50) DEFAULT NULL COMMENT '请假类型',
  `ftime` datetime DEFAULT NULL COMMENT '时间1',
  `stime` datetime DEFAULT NULL COMMENT '时间2',
  `ttime` datetime DEFAULT NULL COMMENT '时间3',
  `fotime` datetime DEFAULT NULL COMMENT '时间4',
  `duration` varchar(10) DEFAULT NULL COMMENT '时长',
  `rmk` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '9' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤管理申报';

-- ----------------------------
-- Records of atd_manage
-- ----------------------------

-- ----------------------------
-- Table structure for atd_record
-- ----------------------------
DROP TABLE IF EXISTS `atd_record`;
CREATE TABLE `atd_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `date` date DEFAULT NULL COMMENT '日期',
  `person` bigint(20) DEFAULT NULL COMMENT '职员',
  `shift` bigint(20) DEFAULT NULL COMMENT '班次',
  `btime` datetime DEFAULT NULL COMMENT '上班时间',
  `etime` datetime DEFAULT NULL COMMENT '下班时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤记录';

-- ----------------------------
-- Records of atd_record
-- ----------------------------

-- ----------------------------
-- Table structure for auditschedule
-- ----------------------------
DROP TABLE IF EXISTS `auditschedule`;
CREATE TABLE `auditschedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `budget` bigint(20) DEFAULT NULL COMMENT '预算申报',
  `person` bigint(20) DEFAULT NULL COMMENT '审核人',
  `pass` tinyint(1) DEFAULT NULL COMMENT '是否通过(1表示通过，0表示未通过)',
  `rmk` varchar(500) DEFAULT NULL COMMENT '补充说明',
  `schedule` tinyint(4) DEFAULT NULL COMMENT '当前进度',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='审核进度';

-- ----------------------------
-- Records of auditschedule
-- ----------------------------
INSERT INTO `auditschedule` VALUES ('1', '4', '28', '1', '项目部过了', '1', '28', '2017-07-25 11:19:53', '28', '2017-07-25 11:19:53');
INSERT INTO `auditschedule` VALUES ('2', '4', '29', '1', '事业部也过了', '2', '29', '2017-07-25 11:21:10', '29', '2017-07-25 11:21:10');
INSERT INTO `auditschedule` VALUES ('3', '4', '30', '1', '财务部过了', '3', '30', '2017-07-25 11:22:20', '30', '2017-07-25 11:22:20');
INSERT INTO `auditschedule` VALUES ('4', '4', '31', '1', '那我也过吧', '4', '31', '2017-07-25 11:22:50', '31', '2017-07-25 11:22:50');

-- ----------------------------
-- Table structure for base_code
-- ----------------------------
DROP TABLE IF EXISTS `base_code`;
CREATE TABLE `base_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `no` varchar(12) NOT NULL COMMENT '代码',
  `type` varchar(16) NOT NULL COMMENT '类型',
  `cnm` varchar(50) NOT NULL COMMENT '中文名',
  `attv` varchar(200) DEFAULT NULL COMMENT '附加值',
  `valid` varchar(1) NOT NULL COMMENT '是否有效：0无效、1有效',
  `sort` decimal(8,0) NOT NULL COMMENT '排序号',
  `rmk` varchar(400) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqcdetype` (`no`,`type`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of base_code
-- ----------------------------
INSERT INTO `base_code` VALUES ('1', '0', 'degree', '小学', null, '1', '0', '学历', '0', '2014-11-24 00:00:00', '1', '2017-03-29 16:35:52');
INSERT INTO `base_code` VALUES ('2', '0', 'feedback', '侮辱谩骂', null, '1', '0', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('3', '0', 'nation', '汉族', null, '1', '0', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('4', '0', 'post', '总经理', null, '1', '0', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('5', '0', 'site_order', '默认排序', null, '1', '0', '场馆列表排序', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('6', '0', 'yes_no', '否', null, '1', '1', '是否标志', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('7', '1', 'degree', '初中', null, '1', '1', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('8', '1', 'feedback', '垃圾广告', null, '1', '1', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('9', '1', 'nation', '蒙古族', null, '1', '1', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('10', '1', 'order_type', '网络', null, '1', '1', '订单来源', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('11', '1', 'phone_type', 'Android', null, '1', '1', '手机类型', '0', '2014-11-25 00:00:00', '0', '2014-11-25 00:00:00');
INSERT INTO `base_code` VALUES ('12', '1', 'post', '副总经理', null, '1', '1', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('13', '1', 'resource', '菜单', '', '1', '1', '资源类型', '0', '2015-10-20 00:00:00', '0', '2015-10-20 00:00:00');
INSERT INTO `base_code` VALUES ('14', '1', 'sex', '男', null, '1', '1', '性别', '0', '2015-10-20 00:00:00', '0', '2014-11-13 10:19:03');
INSERT INTO `base_code` VALUES ('15', '1', 'site_order', '价格从低到高', null, '1', '1', '场馆列表排序', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('16', '1', 'yes_no', '是', null, '1', '0', '是否标志', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('17', '10', 'nation', '满族', null, '1', '10', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('18', '10', 'post', '采购主办', null, '1', '10', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('19', '11', 'nation', '侗族', null, '1', '11', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('20', '11', 'post', '采购专员', null, '1', '11', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('21', '12', 'nation', '瑶族', null, '1', '12', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('22', '12', 'post', '库管专员', null, '1', '12', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('23', '13', 'nation', '白族', null, '1', '13', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('24', '13', 'post', '企划主办', null, '1', '13', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('25', '14', 'nation', '土家族', null, '1', '14', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('26', '14', 'post', '企划专员', null, '1', '14', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('27', '15', 'nation', '哈尼族', null, '1', '15', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('28', '15', 'post', '外联专员', null, '1', '15', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('29', '16', 'nation', '哈萨克族', null, '1', '16', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('30', '16', 'post', '经营管理总监', null, '1', '16', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('31', '17', 'nation', '傣族', null, '1', '17', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('32', '17', 'post', '经营管理经理', null, '1', '17', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('33', '18', 'nation', '黎族', null, '1', '18', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('34', '18', 'post', '经营管理副经理', null, '1', '18', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('35', '19', 'nation', '僳僳族', null, '1', '19', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('36', '19', 'post', '经营主管', null, '1', '19', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('37', '2', 'degree', '高中', null, '1', '2', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('38', '2', 'feedback', '色情淫秽', null, '1', '2', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('39', '2', 'nation', '回族', null, '1', '2', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('40', '2', 'order_type', '电话', null, '1', '2', '订单来源', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('41', '2', 'phone_type', 'IPhone', null, '1', '1', '手机类型', '0', '2014-11-25 00:00:00', '0', '2014-11-25 00:00:00');
INSERT INTO `base_code` VALUES ('42', '2', 'post', '总经理助理', null, '1', '2', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('43', '2', 'resource', '按钮', '', '1', '2', '资源类型', '0', '2015-10-20 00:00:00', '0', '2015-10-20 00:00:00');
INSERT INTO `base_code` VALUES ('44', '2', 'sex', '女', null, '1', '2', '性别', '0', '2014-11-13 10:19:34', '0', '2014-11-13 10:57:51');
INSERT INTO `base_code` VALUES ('45', '2', 'site_order', '价格从高到低', null, '1', '2', '场馆列表排序', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('46', '20', 'nation', '佤族', null, '1', '20', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('47', '20', 'post', '经营管理主办', null, '1', '20', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('48', '21', 'nation', '畲族', null, '1', '21', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('49', '21', 'post', '经营管理专员', null, '1', '21', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('50', '22', 'nation', '高山族', null, '1', '22', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('51', '22', 'post', '财务总监', null, '1', '22', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('52', '23', 'nation', '拉祜族', null, '1', '23', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('53', '23', 'post', '财务经理', null, '1', '23', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('54', '24', 'nation', '水族', null, '1', '24', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('55', '24', 'post', '财务副经理', null, '1', '24', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('56', '25', 'nation', '东乡族', null, '1', '25', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('57', '25', 'post', '财务主管', null, '1', '25', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('58', '26', 'nation', '纳西族', null, '1', '26', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('59', '26', 'post', '主办会计', null, '1', '26', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('60', '27', 'nation', '景颇族', null, '1', '27', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('61', '27', 'post', '财务会计', null, '1', '27', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('62', '28', 'nation', '柯尔克孜族', null, '1', '28', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('63', '28', 'post', '财务出纳', null, '1', '28', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('64', '29', 'nation', '土族', null, '1', '29', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('65', '29', 'post', '技术总监', null, '1', '29', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('66', '3', 'degree', '中专', null, '1', '3', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('67', '3', 'feedback', '反动政治', null, '1', '3', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('68', '3', 'nation', '藏族', null, '1', '3', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('69', '3', 'order_type', '现场', null, '1', '3', '订单来源', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('70', '3', 'post', '人事行政总监', null, '1', '3', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('71', '3', 'resource', '接口', '', '1', '3', '资源类型', '0', '2015-10-20 00:00:00', '0', '2015-10-20 00:00:00');
INSERT INTO `base_code` VALUES ('72', '3', 'sex', '保密', null, '1', '3', '性别', '0', '2014-11-13 00:00:00', '0', '2014-11-13 00:00:00');
INSERT INTO `base_code` VALUES ('73', '3', 'site_order', '距离从近到远', null, '1', '3', '场馆列表排序', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('74', '30', 'nation', '达斡尔族', null, '1', '30', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('75', '30', 'post', '技术副总监', null, '1', '30', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('76', '31', 'nation', '仫佬族', null, '1', '31', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('77', '31', 'post', '技术部门经理', null, '1', '31', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('78', '32', 'nation', '羌族', null, '1', '32', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('79', '32', 'post', '技术部门副经理', null, '1', '32', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('80', '33', 'nation', '布朗族', null, '1', '33', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('81', '33', 'post', '技术中心经理', null, '1', '33', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('82', '34', 'nation', '撒拉族', null, '1', '34', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('83', '34', 'post', '项目主管', null, '1', '34', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('84', '35', 'nation', '毛南族', null, '1', '35', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('85', '35', 'post', '项目经理', null, '1', '35', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('86', '36', 'nation', '仡佬族', null, '1', '36', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('87', '36', 'post', '项目管理主办', null, '1', '36', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('88', '37', 'nation', '锡伯族', null, '1', '37', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('89', '37', 'post', '项目管理专员', null, '1', '37', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('90', '38', 'nation', '阿昌族', null, '1', '38', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('91', '38', 'post', '配置管理专员', null, '1', '38', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('92', '39', 'nation', '普米族', null, '1', '39', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('93', '39', 'post', '首席架构师', null, '1', '39', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('94', '4', 'degree', '大专', null, '1', '4', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('95', '4', 'feedback', '暴利暴恐', null, '1', '4', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('96', '4', 'nation', '维吾尔族', null, '1', '4', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('97', '4', 'post', '人事部门经理', null, '1', '4', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('98', '4', 'site_order', '距离从远到近', null, '1', '4', '场馆列表排序', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('99', '40', 'nation', '塔吉克族', null, '1', '40', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('100', '40', 'post', '资深架构师', null, '1', '40', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('101', '41', 'nation', '怒族', null, '1', '41', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('102', '41', 'post', '资深设计师', null, '1', '41', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('103', '42', 'nation', '乌孜别克族', null, '1', '42', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('104', '42', 'post', '开发高级工程师', null, '1', '42', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('105', '43', 'nation', '俄罗斯族', null, '1', '43', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('106', '43', 'post', '设计高级工程师', null, '1', '43', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('107', '44', 'nation', '鄂温克族', null, '1', '44', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('108', '44', 'post', '测试高级工程师', null, '1', '44', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('109', '45', 'nation', '德昂族', null, '1', '45', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('110', '45', 'post', '开发工程师', null, '1', '45', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('111', '46', 'nation', '保安族', null, '1', '46', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('112', '46', 'post', '设计工程师', null, '1', '46', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('113', '47', 'nation', '裕固族', null, '1', '47', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('114', '47', 'post', '测试工程师', null, '1', '47', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('115', '48', 'nation', '京族', null, '1', '48', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('116', '48', 'post', '开发初级工程师', null, '1', '48', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('117', '49', 'nation', '塔塔尔族', null, '1', '49', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('118', '49', 'post', '设计初级工程师', null, '1', '49', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('119', '5', 'degree', '大学', null, '1', '5', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('120', '5', 'feedback', '欺诈骗钱', null, '1', '5', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('121', '5', 'nation', '苗族', null, '1', '5', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('122', '5', 'post', '人事部门副经理', null, '1', '5', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('123', '50', 'nation', '独龙族', null, '1', '50', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('124', '50', 'post', '测试初级工程师', null, '1', '50', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('125', '51', 'nation', '鄂伦春族', null, '1', '51', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('126', '51', 'post', '销售总监', null, '1', '51', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('127', '52', 'nation', '赫哲族', null, '1', '52', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('128', '52', 'post', '部门经理', null, '1', '52', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('129', '53', 'nation', '门巴族', null, '1', '53', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('130', '53', 'post', '区域销售经理', null, '1', '53', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('131', '54', 'nation', '珞巴族', null, '1', '54', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('132', '54', 'post', '部门副经理', null, '1', '54', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('133', '55', 'nation', '基诺族', null, '1', '55', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('134', '55', 'post', '资深销售经理', null, '1', '55', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('135', '56', 'post', '高级销售经理', null, '1', '56', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('136', '57', 'post', '中级销售经理', null, '1', '57', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('137', '58', 'post', '初级销售经理', null, '1', '58', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('138', '59', 'post', '销售专员', null, '1', '59', '职位', '0', '2015-10-20 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('139', '6', 'degree', '硕士', null, '1', '6', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('140', '6', 'feedback', '其它', null, '1', '6', '反馈标签', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('141', '6', 'nation', '彝族', null, '1', '6', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('142', '6', 'post', '行政人事主管', null, '1', '6', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('143', '7', 'degree', '博士', null, '1', '7', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('144', '7', 'nation', '壮族', null, '1', '7', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('145', '7', 'post', '行政人事主办', null, '1', '7', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('146', '8', 'degree', '其他', null, '1', '8', '学历', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('147', '8', 'nation', '布依族', null, '1', '8', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('148', '8', 'post', '行政专员', null, '1', '8', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('149', '9', 'nation', '朝鲜族', null, '1', '9', '民族', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('150', '9', 'phone_type', '其他', null, '1', '1', '手机类型', '0', '2014-11-25 00:00:00', '0', '2014-11-25 00:00:00');
INSERT INTO `base_code` VALUES ('151', '9', 'post', '采购主管', null, '1', '9', '职位', '0', '2014-11-24 00:00:00', '0', '2014-11-24 00:00:00');
INSERT INTO `base_code` VALUES ('152', '0', 'dclass', 'A1', null, '1', '0', '准驾车型', '1', '2017-04-13 18:10:12', '1', '2017-04-13 18:10:12');
INSERT INTO `base_code` VALUES ('153', '1', 'dclass', 'B1', null, '1', '1', '准驾车型', '1', '2017-04-13 18:13:21', '1', '2017-04-13 18:13:21');
INSERT INTO `base_code` VALUES ('154', '2', 'dclass', 'C1', null, '1', '2', '准驾车型', '1', '2017-04-13 18:14:02', '1', '2017-04-13 18:14:02');
INSERT INTO `base_code` VALUES ('155', '0', 'gasunit', '升', null, '1', '0', '加油单位', '1', '2017-04-19 09:39:12', '1', '2017-04-19 09:39:12');
INSERT INTO `base_code` VALUES ('156', '0', 'finsure', '交通事故责任强制保险', null, '1', '0', '强制保险', '1', '2017-04-24 16:38:03', '1', '2017-04-24 16:38:03');
INSERT INTO `base_code` VALUES ('157', '0', 'binsure', '第三者责任险', null, '1', '0', '商业保险', '1', '2017-04-24 16:39:09', '1', '2017-04-24 16:39:09');
INSERT INTO `base_code` VALUES ('158', '1', 'binsure', '车辆损失险', null, '1', '1', '商业保险', '1', '2017-04-24 16:39:55', '1', '2017-04-24 16:39:55');
INSERT INTO `base_code` VALUES ('159', '2', 'binsure', '全车盗抢险', null, '1', '2', '商业保险', '1', '2017-04-24 16:40:31', '1', '2017-04-24 16:40:31');
INSERT INTO `base_code` VALUES ('160', '3', 'binsure', '玻璃单独破碎险', null, '1', '3', '商业保险', '1', '2017-04-24 16:41:08', '1', '2017-04-24 16:41:08');
INSERT INTO `base_code` VALUES ('161', '4', 'binsure', '自燃损失险', null, '1', '4', '商业保险', '1', '2017-04-24 16:41:34', '1', '2017-04-24 16:41:34');
INSERT INTO `base_code` VALUES ('162', '1', 'finsure', 'XX强制保险', null, '1', '1', '强制保险', '1', '2017-04-25 10:16:41', '1', '2017-04-25 10:16:41');
INSERT INTO `base_code` VALUES ('163', '0', 'gastype', '汽油-国V_95#', null, '1', '0', '加油单位', '1', '2017-04-26 09:48:57', '1', '2017-04-26 09:48:57');
INSERT INTO `base_code` VALUES ('164', '1', 'gastype', '汽油-国V_89#', null, '1', '1', '加油类型', '1', '2017-04-26 09:50:44', '1', '2017-04-26 09:50:44');
INSERT INTO `base_code` VALUES ('165', '2', 'gastype', '汽油-国V_98#', null, '1', '2', '加油类型', '1', '2017-04-26 09:51:25', '1', '2017-04-26 09:51:25');
INSERT INTO `base_code` VALUES ('166', '3', 'gastype', '轻柴-国V_0#', null, '1', '3', '加油类型', '1', '2017-04-26 09:52:53', '1', '2017-04-26 09:52:53');
INSERT INTO `base_code` VALUES ('167', '4', 'gastype', '轻柴-国V_1#', null, '1', '4', '加油类型', '1', '2017-04-26 09:53:19', '1', '2017-04-26 09:53:19');
INSERT INTO `base_code` VALUES ('168', '1', 'gasunit', '公升', null, '1', '1', '加油单位', '1', '2017-04-26 10:16:18', '1', '2017-04-26 10:16:18');
INSERT INTO `base_code` VALUES ('169', '0', 'illegaCause', '闯红灯', null, '1', '0', '违章原因', '1', '2017-04-26 14:48:37', '1', '2017-04-26 14:48:37');
INSERT INTO `base_code` VALUES ('170', '1', 'illegaCause', '酒驾', null, '1', '1', '违章原因', '1', '2017-04-26 14:54:03', '1', '2017-04-26 14:54:03');
INSERT INTO `base_code` VALUES ('171', '0', 'smaintain', '更换机油', null, '1', '0', '小保养', '1', '2017-04-26 16:01:21', '1', '2017-04-26 16:01:21');
INSERT INTO `base_code` VALUES ('172', '1', 'smaintain', '机油滤芯器', null, '1', '1', '小保养', '1', '2017-04-26 16:01:55', '1', '2017-04-26 16:01:55');
INSERT INTO `base_code` VALUES ('173', '0', 'bmaintain', '更换机油', null, '1', '0', '大保养', '1', '2017-04-26 16:02:42', '1', '2017-04-26 16:02:42');
INSERT INTO `base_code` VALUES ('174', '1', 'bmaintain', '机油滤芯器', null, '1', '1', '大保养', '1', '2017-04-26 16:03:17', '1', '2017-04-26 16:03:17');
INSERT INTO `base_code` VALUES ('175', '2', 'bmaintain', '空气滤清器', null, '1', '2', '大保养', '1', '2017-04-26 16:03:44', '1', '2017-04-26 16:03:44');
INSERT INTO `base_code` VALUES ('176', '3', 'bmaintain', '汽油滤清器', null, '1', '3', '大保养', '1', '2017-04-26 16:04:10', '1', '2017-04-26 16:04:10');
INSERT INTO `base_code` VALUES ('177', '4', 'bmaintain', '燃油滤清器', null, '1', '4', '大保养', '1', '2017-04-26 16:04:44', '1', '2017-04-26 16:04:44');
INSERT INTO `base_code` VALUES ('178', '0', 'crepaire', '发动机维修', null, '1', '0', '维修明细', '1', '2017-04-27 11:09:16', '1', '2017-04-27 11:09:16');
INSERT INTO `base_code` VALUES ('179', '1', 'crepaire', '底盘检测及维修', null, '1', '1', '维修明细', '1', '2017-04-27 11:09:55', '1', '2017-04-27 11:09:55');
INSERT INTO `base_code` VALUES ('180', '0', 'srepaire', '补胎', null, '1', '0', '常见维修细分', '1', '2017-04-27 11:10:44', '1', '2017-04-27 11:10:44');
INSERT INTO `base_code` VALUES ('181', '1', 'srepaire', '更换轮胎', null, '1', '1', '常见维修细分', '1', '2017-04-27 11:11:28', '1', '2017-04-27 11:11:28');
INSERT INTO `base_code` VALUES ('182', '2', 'srepaire', '四轮定位', null, '1', '2', '常见维修细分', '1', '2017-04-27 11:27:59', '1', '2017-04-27 11:27:59');
INSERT INTO `base_code` VALUES ('183', '3', 'srepaire', '更换刹车片', null, '1', '3', '常见维修细分', '1', '2017-04-27 11:28:53', '1', '2017-04-27 11:28:53');
INSERT INTO `base_code` VALUES ('184', '0', 'caseSource', '市民爆料', null, '1', '1', '事件来源', '1', '2017-05-03 10:43:08', '1', '2017-05-03 10:43:08');
INSERT INTO `base_code` VALUES ('185', '1', 'caseSource', '领导安排', null, '1', '2', '事件来源', '1', '2017-05-03 10:46:20', '1', '2017-05-03 10:46:20');
INSERT INTO `base_code` VALUES ('186', '2', 'caseSource', '数字化城管', null, '1', '4', '事件来源', '1', '2017-05-03 10:49:09', '1', '2017-05-03 10:49:09');
INSERT INTO `base_code` VALUES ('187', '3', 'caseSource', '员工爆料', null, '1', '5', '事件来源', '1', '2017-05-03 10:50:49', '1', '2017-05-03 10:50:49');
INSERT INTO `base_code` VALUES ('188', '4', 'caseSource', '呼叫工单', null, '0', '3', '事件来源', '1', '2017-05-03 10:46:52', '1', '2017-05-03 10:46:52');
INSERT INTO `base_code` VALUES ('189', '0', 'ares', '全部责任', null, '1', '0', '事故责任', '1', '2017-05-08 11:53:20', '1', '2017-05-08 11:53:20');
INSERT INTO `base_code` VALUES ('190', '1', 'ares', '主要责任', null, '1', '1', '事故责任', '1', '2017-05-08 11:53:57', '1', '2017-05-08 11:53:57');
INSERT INTO `base_code` VALUES ('191', '2', 'ares', '次要责任', null, '1', '2', '事故管理', '1', '2017-05-08 11:57:34', '1', '2017-05-08 11:57:34');
INSERT INTO `base_code` VALUES ('192', '0', 'scoretype', '衣冠不整', null, '1', '0', '扣分类型', '1', '2017-05-10 14:54:45', '1', '2017-05-10 14:54:45');
INSERT INTO `base_code` VALUES ('193', '1', 'scoretype', '头发太长', null, '1', '1', '扣分类型', '1', '2017-05-10 14:55:36', '1', '2017-05-10 14:55:36');
INSERT INTO `base_code` VALUES ('194', '2', 'scoretype', '体重超标', null, '1', '2', '扣分类型', '1', '2017-05-10 14:56:25', '1', '2017-05-10 14:56:25');
INSERT INTO `base_code` VALUES ('195', '0', 'score', '1', null, '1', '0', '扣分', '1', '2017-05-10 14:57:47', '1', '2017-05-10 14:57:47');
INSERT INTO `base_code` VALUES ('196', '1', 'score', '2', null, '1', '1', '扣分', '1', '2017-05-10 14:58:06', '1', '2017-05-10 14:58:06');
INSERT INTO `base_code` VALUES ('197', '2', 'score', '3', null, '1', '2', '扣分', '1', '2017-05-10 14:58:22', '1', '2017-05-10 14:58:22');
INSERT INTO `base_code` VALUES ('198', '3', 'score', '4', null, '1', '3', '扣分', '1', '2017-05-10 14:58:37', '1', '2017-05-10 14:58:37');
INSERT INTO `base_code` VALUES ('199', '4', 'score', '5', null, '1', '4', '扣分', '1', '2017-05-10 14:58:56', '1', '2017-05-10 14:58:56');
INSERT INTO `base_code` VALUES ('200', '0', 'biztype', '清扫保洁', null, '1', '0', '业务类型', '1', '2017-05-10 17:11:14', '1', '2017-05-10 17:11:14');
INSERT INTO `base_code` VALUES ('201', '1', 'biztype', '垃圾清运', null, '1', '1', '业务类型', '1', '2017-05-10 17:11:38', '1', '2017-05-10 17:11:38');
INSERT INTO `base_code` VALUES ('202', '2', 'biztype', '综合巡查', null, '1', '2', '业务类型', '1', '2017-05-10 17:12:02', '1', '2017-05-10 17:12:02');
INSERT INTO `base_code` VALUES ('203', '3', 'biztype', '绿化管养', null, '1', '3', '业务类型', '1', '2017-05-10 17:12:44', '1', '2017-05-10 17:12:44');
INSERT INTO `base_code` VALUES ('204', '0', 'devtype', '世纪天元X3', null, '1', '0', '人员终端的设备类型', '1', '2017-05-19 15:49:54', '1', '2017-05-19 15:49:54');
INSERT INTO `base_code` VALUES ('205', '1', 'devtype', '柏颐曲奇', null, '1', '1', '人员终端的设备类型', '1', '2017-05-19 15:50:17', '1', '2017-05-19 15:50:17');
INSERT INTO `base_code` VALUES ('206', '0', 'devfrim', '厂商一', null, '1', '0', '车载终端的设备厂商', '1', '2017-05-19 17:03:22', '1', '2017-05-19 17:03:22');
INSERT INTO `base_code` VALUES ('207', '1', 'devfrim', '厂商二', null, '1', '1', '车载终端的设备厂商', '1', '2017-05-19 17:03:45', '1', '2017-05-19 17:03:45');
INSERT INTO `base_code` VALUES ('208', '0', 'devmodel', '型号一', null, '1', '0', '车载终端的设备型号', '1', '2017-05-19 17:04:25', '1', '2017-05-19 17:04:25');
INSERT INTO `base_code` VALUES ('209', '1', 'devmodel', '型号二', null, '1', '1', '车载终端的设备型号', '1', '2017-05-19 17:04:47', '1', '2017-05-19 17:04:47');
INSERT INTO `base_code` VALUES ('210', '0', 'monitordevfrim', '厂商一', null, '1', '0', '监控设备的设备厂商', '1', '2017-05-20 11:13:39', '1', '2017-05-20 11:13:39');
INSERT INTO `base_code` VALUES ('211', '1', 'monitordevfrim', '厂商二', null, '1', '1', '监控设备的设备厂商', '1', '2017-05-20 11:14:04', '1', '2017-05-20 11:14:04');
INSERT INTO `base_code` VALUES ('212', '0', 'monitordevmodel', '型号一', null, '1', '0', '监控设备的设备型号', '1', '2017-05-20 11:15:00', '1', '2017-05-20 11:15:00');
INSERT INTO `base_code` VALUES ('213', '1', 'monitordevmodel', '型号二', null, '1', '1', '监控设备的设备型号', '1', '2017-05-20 11:15:21', '1', '2017-05-20 11:15:21');
INSERT INTO `base_code` VALUES ('214', '3', 'dclass', 'C2', null, '1', '3', '准驾车型', '1', '2017-05-24 11:20:31', '1', '2017-05-24 11:20:31');
INSERT INTO `base_code` VALUES ('215', '0', 'infosource', '微信市民端', null, '1', '0', '信息来源', '1', '2017-05-24 11:28:28', '1', '2017-05-24 11:28:28');
INSERT INTO `base_code` VALUES ('216', '1', 'infosource', 'PC官网', null, '1', '1', '信息来源', '1', '2017-05-24 11:29:00', '1', '2017-05-24 11:29:00');
INSERT INTO `base_code` VALUES ('217', '0', 'problemtype', 'bug类', null, '1', '0', '问题类型', '1', '2017-05-24 11:30:57', '1', '2017-05-24 11:30:57');
INSERT INTO `base_code` VALUES ('218', '1', 'problemtype', 'XXX 类', null, '1', '1', '问题类型', '1', '2017-05-24 11:31:17', '1', '2017-05-24 11:31:17');
INSERT INTO `base_code` VALUES ('219', '0', 'project', 'XX龙华项目', null, '1', '0', '项目', '1', '2017-05-26 10:40:27', '1', '2017-05-26 10:40:27');
INSERT INTO `base_code` VALUES ('220', '1', 'project', 'XX保安项目', null, '1', '1', '项目', '1', '2017-05-26 10:43:26', '1', '2017-05-26 10:43:26');
INSERT INTO `base_code` VALUES ('221', '2', 'project', 'XX佛山项目', null, '1', '2', '项目', '1', '2017-05-26 10:43:57', '1', '2017-05-26 10:43:57');
INSERT INTO `base_code` VALUES ('277', '2', 'infosource', '微信员工端', null, '1', '2', '信息来源', '1', '2017-06-23 10:21:08', '1', '2017-06-23 10:21:08');
INSERT INTO `base_code` VALUES ('278', '0', 'otype', '总公司', null, '1', '1', '组织类型', '1', '2017-06-23 04:23:37', '1', '2017-06-23 04:23:51');
INSERT INTO `base_code` VALUES ('280', '1', 'otype', '事业部', null, '1', '2', '组织类型', '1', '2017-06-23 04:24:25', '1', '2017-06-23 04:24:25');
INSERT INTO `base_code` VALUES ('281', '2', 'otype', '分公司', null, '1', '3', '组织类型', '1', '2017-06-23 04:24:56', '1', '2017-06-23 04:24:56');
INSERT INTO `base_code` VALUES ('282', '3', 'otype', '项目部', null, '1', '4', '组织类型', '1', '2017-06-23 04:25:27', '1', '2017-06-23 04:25:37');
INSERT INTO `base_code` VALUES ('283', '4', 'otype', '部门', null, '1', '5', '组织类型', '1', '2017-06-23 04:26:02', '1', '2017-06-23 04:26:02');
INSERT INTO `base_code` VALUES ('284', '5', 'otype', '班级', null, '1', '6', '组织类型', '1', '2017-06-23 05:01:51', '1', '2017-06-23 05:01:51');
INSERT INTO `base_code` VALUES ('285', '6', 'otype', '数字化城管', null, '1', '7', '组织类型', '1', '2017-06-23 17:50:32', '1', '2017-06-23 17:50:32');
INSERT INTO `base_code` VALUES ('286', '2', 'cpRadius', '2公里', null, '1', '1', '项目中心半径', '1', '2017-06-24 17:12:18', '1', '2017-06-24 17:12:18');
INSERT INTO `base_code` VALUES ('287', '5', 'cpRadius', '5公里', null, '1', '2', '项目中心半径', '1', '2017-06-24 17:12:40', '1', '2017-06-24 17:12:40');
INSERT INTO `base_code` VALUES ('288', '8', 'cpRadius', '8公里', null, '1', '3', '项目中心半径', '1', '2017-06-24 17:20:33', '1', '2017-06-24 17:20:33');
INSERT INTO `base_code` VALUES ('289', '10', 'cpRadius', '10公里', null, '1', '4', '项目中心位置', '1', '2017-06-24 17:20:55', '1', '2017-06-24 17:20:55');
INSERT INTO `base_code` VALUES ('290', '15', 'cpRadius', '15公里', null, '1', '5', '项目中心半径', '1', '2017-06-24 17:21:17', '1', '2017-06-24 17:21:17');
INSERT INTO `base_code` VALUES ('291', '20', 'cpRadius', '20公里', null, '1', '6', '项目中心位置', '1', '2017-06-24 17:21:39', '1', '2017-06-24 17:21:39');
INSERT INTO `base_code` VALUES ('292', '30', 'cpRadius', '30公里', '', '1', '7', '项目中心位置', '1', '2017-06-24 17:22:00', '1', '2017-06-24 17:22:00');
INSERT INTO `base_code` VALUES ('293', '0', 'newstype', '任务消息', null, '1', '0', '消息类型', '1', '2017-07-03 15:58:15', '1', '2017-07-03 15:58:15');
INSERT INTO `base_code` VALUES ('294', '1', 'newstype', '预警消息', null, '1', '1', '消息类型', '1', '2017-07-03 15:59:10', '1', '2017-07-03 15:59:10');
INSERT INTO `base_code` VALUES ('295', '2', 'newstype', '公告消息', null, '1', '2', '消息类型', '1', '2017-07-03 16:00:34', '1', '2017-07-03 16:00:34');
INSERT INTO `base_code` VALUES ('296', '0', 'businesstype', '事件预警', 'ewtype', '1', '0', '预警业务类型', '1', '2017-07-05 16:58:04', '1', '2017-07-06 10:37:40');
INSERT INTO `base_code` VALUES ('297', '1', 'businesstype', '考勤预警', 'adwi', '1', '1', '预警业务类型', '1', '2017-07-05 16:58:37', '1', '2017-07-06 10:42:04');
INSERT INTO `base_code` VALUES ('298', '2', 'businesstype', '车辆预警', 'vhew', '1', '2', '预警业务类型', '1', '2017-07-05 16:58:59', '1', '2017-07-06 10:42:28');
INSERT INTO `base_code` VALUES ('299', '0', 'oneself', '否', null, '1', '0', '是否推送本人', '1', '2017-07-05 17:26:08', '1', '2017-07-05 17:27:55');
INSERT INTO `base_code` VALUES ('300', '1', 'oneself', '是', null, '1', '1', '是否推送本人', '1', '2017-07-05 17:26:23', '1', '2017-07-05 17:28:03');
INSERT INTO `base_code` VALUES ('301', '0', 'ewtype', '事件派单', null, '1', '0', '事件预警', '1', '2017-07-06 10:26:52', '1', '2017-07-06 10:26:52');
INSERT INTO `base_code` VALUES ('302', '1', 'ewtype', '事件处理', null, '1', '1', '事件预警', '1', '2017-07-06 10:27:30', '1', '2017-07-06 10:27:30');
INSERT INTO `base_code` VALUES ('303', '2', 'ewtype', '复核派单', null, '1', '2', '事件预警', '1', '2017-07-06 10:27:59', '1', '2017-07-06 10:27:59');
INSERT INTO `base_code` VALUES ('304', '3', 'ewtype', '复核处理', null, '1', '3', '事件预警', '1', '2017-07-06 10:28:25', '1', '2017-07-06 10:28:25');
INSERT INTO `base_code` VALUES ('305', '0', 'adwi', '迟到', null, '1', '0', '考勤预警', '1', '2017-07-06 10:31:20', '1', '2017-07-06 10:31:20');
INSERT INTO `base_code` VALUES ('306', '1', 'adwi', '早退', null, '1', '1', '考勤预警', '1', '2017-07-06 10:31:54', '1', '2017-07-06 10:31:54');
INSERT INTO `base_code` VALUES ('307', '2', 'adwi', '滞留', null, '1', '2', '考勤预警', '1', '2017-07-06 10:32:15', '1', '2017-07-06 10:32:15');
INSERT INTO `base_code` VALUES ('308', '3', 'adwi', '越界', null, '1', '3', '考勤预警', '1', '2017-07-06 10:32:38', '1', '2017-07-06 10:32:38');
INSERT INTO `base_code` VALUES ('309', '0', 'vhew', '超速', null, '1', '0', '车辆预警', '1', '2017-07-06 10:34:01', '1', '2017-07-06 10:34:01');
INSERT INTO `base_code` VALUES ('310', '1', 'vhew', '越界', null, '1', '1', '车辆预警', '1', '2017-07-06 10:34:43', '1', '2017-07-06 10:34:43');
INSERT INTO `base_code` VALUES ('311', '2', 'vhew', '滞留', null, '1', '2', '车辆预警', '1', '2017-07-06 10:35:01', '1', '2017-07-06 10:35:01');

-- ----------------------------
-- Table structure for basissetting
-- ----------------------------
DROP TABLE IF EXISTS `basissetting`;
CREATE TABLE `basissetting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `czm` int(10) DEFAULT NULL COMMENT '车辆滞留米',
  `czf` int(10) DEFAULT NULL COMMENT '车辆滞留分钟',
  `cym` int(10) DEFAULT NULL COMMENT '车辆越界米',
  `ccg` int(10) DEFAULT NULL COMMENT '车辆超速公里',
  `kzm` int(10) DEFAULT NULL COMMENT '考勤滞留米',
  `kzf` int(10) DEFAULT NULL COMMENT '考勤滞留分钟',
  `kym` int(10) DEFAULT NULL COMMENT '考勤越界米',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='基础设置';

-- ----------------------------
-- Records of basissetting
-- ----------------------------
INSERT INTO `basissetting` VALUES ('6', '50', '50', '50', '50', '50', '50', '50', '1', '1', '2017-07-21 20:18:31', '1', '2017-07-21 20:18:31');

-- ----------------------------
-- Table structure for bddetail
-- ----------------------------
DROP TABLE IF EXISTS `bddetail`;
CREATE TABLE `bddetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `budget` bigint(20) DEFAULT NULL COMMENT '预算申报',
  `code` varchar(12) DEFAULT NULL COMMENT '代码',
  `pcode` varchar(12) DEFAULT NULL COMMENT '父节点代码',
  `money` decimal(24,4) DEFAULT NULL COMMENT '金额',
  `origin` tinyint(4) DEFAULT NULL COMMENT '当前项来源\r\n            0：初始预算\r\n            1：第一次新增预算\r\n            2：第二次新增预算\r\n            3：第三次新增预算',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='预算明细';

-- ----------------------------
-- Records of bddetail
-- ----------------------------
INSERT INTO `bddetail` VALUES ('1', '1', '9', '1', '1.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('2', '1', '10', '1', '2.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('3', '1', '11', '2', '3.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('4', '1', '16', '14', '4.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('5', '1', '28', '19', '5.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('6', '1', '29', '19', '6.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('7', '1', '20', '18', '7.0000', '0', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `bddetail` VALUES ('9', '3', '5', '1', '1.0000', '0', '1', '2017-07-25 11:11:00', '1', '2017-07-25 11:11:00');
INSERT INTO `bddetail` VALUES ('10', '3', '6', '1', '2.0000', '0', '1', '2017-07-25 11:11:00', '1', '2017-07-25 11:11:00');
INSERT INTO `bddetail` VALUES ('11', '3', '7', '1', '3.0000', '0', '1', '2017-07-25 11:11:00', '1', '2017-07-25 11:11:00');
INSERT INTO `bddetail` VALUES ('12', '4', '8', '1', '4.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('13', '4', '9', '1', '5.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('14', '4', '10', '1', '6.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('15', '4', '11', '2', '7.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('16', '4', '12', '2', '8.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('17', '4', '13', '2', '9.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('18', '4', '16', '14', '10.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('19', '4', '17', '14', '11.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('20', '4', '28', '19', '12.0000', '0', '1', '2017-07-25 11:11:36', '1', '2017-07-25 11:11:36');
INSERT INTO `bddetail` VALUES ('21', '2', '5', '1', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('22', '2', '7', '1', '11.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('23', '2', '8', '1', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('24', '2', '9', '1', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('25', '2', '10', '1', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('26', '2', '11', '2', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('27', '2', '12', '2', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('28', '2', '13', '2', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('29', '2', '16', '14', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('30', '2', '17', '14', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('31', '2', '28', '19', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('32', '2', '29', '19', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('33', '2', '20', '18', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('34', '2', '15', '2', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('35', '2', '21', '3', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('36', '2', '22', '3', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('37', '2', '23', '3', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('38', '2', '25', '24', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('39', '2', '26', '24', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');
INSERT INTO `bddetail` VALUES ('40', '2', '27', '4', '1.0000', '0', '1', '2017-07-25 11:27:23', '1', '2017-07-25 11:27:23');

-- ----------------------------
-- Table structure for bditem
-- ----------------------------
DROP TABLE IF EXISTS `bditem`;
CREATE TABLE `bditem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cnm` varchar(50) DEFAULT NULL COMMENT '中文名',
  `code` varchar(12) DEFAULT NULL COMMENT '代码',
  `pcode` varchar(12) DEFAULT NULL COMMENT '父节点代码',
  `rmk` varchar(400) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='预算明细有哪些项';

-- ----------------------------
-- Records of bditem
-- ----------------------------
INSERT INTO `bditem` VALUES ('1', '营业收入预算', '1', '-1', null, '0', '2017-07-20 11:17:59', '0', '2017-07-20 11:18:02');
INSERT INTO `bditem` VALUES ('2', '主营业务成本预算', '2', '-1', null, '0', '2017-07-20 11:19:21', '0', '2017-07-20 11:19:24');
INSERT INTO `bditem` VALUES ('3', '管理费用预算', '3', '-1', null, '0', '2017-07-20 11:19:43', '0', '2017-07-20 11:19:46');
INSERT INTO `bditem` VALUES ('4', '财务费用预算', '4', '-1', null, '0', '2017-07-20 11:20:12', '0', '2017-07-20 11:20:15');
INSERT INTO `bditem` VALUES ('5', '清扫保洁收入', '5', '1', null, '0', '2017-07-20 11:20:45', '0', '2017-07-20 11:20:49');
INSERT INTO `bditem` VALUES ('6', '综合管理服务费', '6', '1', null, '0', '2017-07-20 11:21:49', '0', '2017-07-20 11:21:52');
INSERT INTO `bditem` VALUES ('7', '物业管理收入', '7', '1', null, '0', '2017-07-20 11:22:11', '0', '2017-07-20 11:22:16');
INSERT INTO `bditem` VALUES ('8', '多种经营收入', '8', '1', null, '0', '2017-07-20 11:22:43', '0', '2017-07-20 11:22:46');
INSERT INTO `bditem` VALUES ('9', '停车费收入', '9', '1', null, '0', '2017-07-20 11:23:09', '0', '2017-07-20 11:23:12');
INSERT INTO `bditem` VALUES ('10', '其他收入', '10', '1', null, '0', '2017-07-20 11:23:28', '0', '2017-07-20 11:23:32');
INSERT INTO `bditem` VALUES ('11', '员工工资', '11', '2', null, '0', '2017-07-20 11:24:11', '0', '2017-07-20 11:24:13');
INSERT INTO `bditem` VALUES ('12', '社保费', '12', '2', null, '0', '2017-07-20 11:24:30', '0', '2017-07-20 11:24:32');
INSERT INTO `bditem` VALUES ('13', '福利费', '13', '2', null, '0', '2017-07-20 11:24:46', '0', '2017-07-20 11:24:48');
INSERT INTO `bditem` VALUES ('14', '低值易耗品', '14', '2', null, '0', '2017-07-20 11:25:14', '0', '2017-07-20 11:25:16');
INSERT INTO `bditem` VALUES ('15', '设备维护费', '15', '2', null, '0', '2017-07-20 11:25:55', '0', '2017-07-20 11:25:58');
INSERT INTO `bditem` VALUES ('16', '清洁用品', '16', '14', null, '0', '2017-07-20 11:28:22', '0', '2017-07-20 11:28:24');
INSERT INTO `bditem` VALUES ('17', '日用品', '17', '14', null, '0', '2017-07-20 11:28:43', '0', '2017-07-20 11:28:45');
INSERT INTO `bditem` VALUES ('18', '劳保用品', '18', '14', null, '0', '2017-07-20 11:29:03', '0', '2017-07-20 11:29:05');
INSERT INTO `bditem` VALUES ('19', '劳保用品1', '19', '18', null, '0', '2017-07-20 11:29:26', '0', '2017-07-20 11:29:28');
INSERT INTO `bditem` VALUES ('20', '劳保用品2', '20', '18', null, '0', '2017-07-20 11:29:48', '0', '2017-07-20 11:29:56');
INSERT INTO `bditem` VALUES ('21', '工资', '21', '3', null, '0', '2017-07-20 11:30:29', '0', '2017-07-20 11:30:32');
INSERT INTO `bditem` VALUES ('22', '社保费', '22', '3', null, '0', '2017-07-20 11:31:11', '0', '2017-07-20 11:31:13');
INSERT INTO `bditem` VALUES ('23', '商业保险费', '23', '3', null, '0', '2017-07-20 11:31:34', '0', '2017-07-20 11:31:36');
INSERT INTO `bditem` VALUES ('24', '职工福利费', '24', '3', null, '0', '2017-07-20 11:32:15', '0', '2017-07-20 11:32:35');
INSERT INTO `bditem` VALUES ('25', '职工食堂费用', '25', '24', null, '0', '2017-07-20 11:33:24', '0', '2017-07-20 11:33:27');
INSERT INTO `bditem` VALUES ('26', '开工补贴', '26', '24', null, '0', '2017-07-20 11:34:18', '0', '2017-07-20 11:34:20');
INSERT INTO `bditem` VALUES ('27', '利息（收入为一）', '27', '4', null, '0', '2017-07-20 11:35:43', '0', '2017-07-20 11:35:46');
INSERT INTO `bditem` VALUES ('28', '这啥啊', '28', '19', null, '0', '2017-07-21 17:22:34', '0', '2017-07-21 17:22:38');
INSERT INTO `bditem` VALUES ('29', '这又是啥啊', '29', '19', null, '0', '2017-07-21 17:22:52', '0', '2017-07-21 17:22:54');

-- ----------------------------
-- Table structure for brknews
-- ----------------------------
DROP TABLE IF EXISTS `brknews`;
CREATE TABLE `brknews` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(50) NOT NULL COMMENT '类型',
  `source` varchar(50) NOT NULL DEFAULT '' COMMENT '来源 数据字典',
  `lat` varchar(20) NOT NULL COMMENT '纬度',
  `lng` varchar(20) NOT NULL COMMENT '经度',
  `adds` varchar(255) NOT NULL COMMENT '最近的地点',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `caseno` varchar(100) NOT NULL COMMENT '案件编号',
  `evaluate` varchar(500) DEFAULT NULL COMMENT '评价',
  `crtid` varchar(50) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` varchar(50) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='爆料事件';

-- ----------------------------
-- Records of brknews
-- ----------------------------
INSERT INTO `brknews` VALUES ('41', '1', '1', '22.580699', '113.505815', '广东省中山市民众镇福一路', '6666666666666666666', null, 'NO.2017070001', null, '25', '2017-07-04 18:17:19', '25', '2017-07-04 18:17:19');
INSERT INTO `brknews` VALUES ('42', '1', '0', '22.556604', '113.438524', '广东省中山市火炬开发区街道下街28号', '瞎扯的', null, 'NO.2017070002', null, '16', '2017-07-06 14:43:54', '16', '2017-07-06 14:43:54');

-- ----------------------------
-- Table structure for budget
-- ----------------------------
DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `person` bigint(20) DEFAULT NULL COMMENT '申报人员',
  `month` varchar(10) DEFAULT NULL COMMENT '月份',
  `project` varchar(12) DEFAULT NULL COMMENT '所属项目',
  `schedule` tinyint(4) DEFAULT NULL COMMENT '进度',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='预算申报';

-- ----------------------------
-- Records of budget
-- ----------------------------
INSERT INTO `budget` VALUES ('1', '1', '2017-06', '1', '1', '1', '2017-07-21 17:41:55', '1', '2017-07-21 17:41:55');
INSERT INTO `budget` VALUES ('2', '1', '2017-06', '1', '1', '1', '2017-07-24 18:14:39', '1', '2017-07-24 18:14:39');
INSERT INTO `budget` VALUES ('3', '1', '2017-07', '2', '1', '1', '2017-07-25 11:11:00', '1', '2017-07-25 11:11:00');
INSERT INTO `budget` VALUES ('4', '1', '2017-07', '0', '5', '1', '2017-07-25 11:11:35', '1', '2017-07-25 11:11:35');

-- ----------------------------
-- Table structure for call_jobsel
-- ----------------------------
DROP TABLE IF EXISTS `call_jobsel`;
CREATE TABLE `call_jobsel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `phone` bigint(20) DEFAULT NULL COMMENT '电话号码',
  `adds` varchar(255) DEFAULT NULL COMMENT '最近的地点',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `crtid` varchar(50) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` varchar(50) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单查询';

-- ----------------------------
-- Records of call_jobsel
-- ----------------------------

-- ----------------------------
-- Table structure for call_record
-- ----------------------------
DROP TABLE IF EXISTS `call_record`;
CREATE TABLE `call_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `calldate` datetime NOT NULL COMMENT '通话时间',
  `calltype` char(1) NOT NULL COMMENT '呼叫类型',
  `phone` bigint(18) NOT NULL COMMENT '手机号码',
  `arttime` bigint(20) NOT NULL COMMENT '通话时长',
  `seatsid` bigint(20) NOT NULL COMMENT '坐席编号',
  `seatsname` varchar(50) NOT NULL COMMENT '坐席姓名',
  `calltapesurl` varchar(255) NOT NULL COMMENT '通话录音url地址',
  `crtid` varchar(50) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` varchar(50) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通话记录表';

-- ----------------------------
-- Records of call_record
-- ----------------------------

-- ----------------------------
-- Table structure for call_seats
-- ----------------------------
DROP TABLE IF EXISTS `call_seats`;
CREATE TABLE `call_seats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `fphone` bigint(20) DEFAULT NULL COMMENT '分机号码',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `xmper` varchar(100) DEFAULT NULL COMMENT '所属项目',
  `zxno` varchar(50) DEFAULT NULL COMMENT '坐席编码(自动生成)',
  `depa` varchar(100) DEFAULT NULL COMMENT '部门',
  `phone` bigint(18) DEFAULT '1' COMMENT '手机号码',
  `sid` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `cdate` date DEFAULT NULL COMMENT '出生日期',
  `jaddes` varchar(255) DEFAULT NULL COMMENT '居住地址',
  `haddes` varchar(255) DEFAULT NULL COMMENT '户籍地址',
  `rtype` varchar(50) DEFAULT NULL COMMENT '人员类型',
  `rem` varchar(255) DEFAULT NULL COMMENT '备注',
  `crtid` varchar(50) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` varchar(50) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='坐席管理';

-- ----------------------------
-- Records of call_seats
-- ----------------------------

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `fleett` bigint(20) DEFAULT NULL COMMENT '所属车队',
  `ctype` bigint(20) DEFAULT NULL COMMENT '车辆类型',
  `cno` varchar(30) DEFAULT NULL COMMENT '车牌号',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片',
  `cin` varchar(100) DEFAULT NULL COMMENT '车架号',
  `engno` varchar(100) DEFAULT NULL COMMENT '发动机号',
  `binddev` varchar(50) DEFAULT NULL COMMENT '绑定设备',
  `binddevrfid` varchar(50) DEFAULT NULL COMMENT '绑定设备(RFID)',
  `person` bigint(20) DEFAULT NULL COMMENT '责任人',
  `phone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `rmk` varchar(150) DEFAULT NULL COMMENT '备注',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='车辆管理';

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('1', '9', '7', 'A002', '24e6f8e46e91f2b1d9d29cf1b88f04e4', 'A002', 'A002', 'A002', 'A002', '8', null, null, '1', '1', '2017-07-03 15:10:48', '1', '2017-07-03 15:10:48');

-- ----------------------------
-- Table structure for carmild
-- ----------------------------
DROP TABLE IF EXISTS `carmild`;
CREATE TABLE `carmild` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `carid` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `mileage` int(50) DEFAULT NULL COMMENT '里程数',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆保养预警';

-- ----------------------------
-- Records of carmild
-- ----------------------------

-- ----------------------------
-- Table structure for carterminal
-- ----------------------------
DROP TABLE IF EXISTS `carterminal`;
CREATE TABLE `carterminal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `devfrim` bigint(20) DEFAULT NULL COMMENT '设备厂商',
  `devmodel` bigint(20) DEFAULT NULL COMMENT '设备型号',
  `devid` varchar(50) DEFAULT NULL COMMENT '设备ID',
  `g2no` varchar(50) DEFAULT NULL COMMENT '2G/SIM卡号',
  `g3no` varchar(50) DEFAULT NULL COMMENT '3G/SIM卡号',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车载终端';

-- ----------------------------
-- Records of carterminal
-- ----------------------------

-- ----------------------------
-- Table structure for cartype
-- ----------------------------
DROP TABLE IF EXISTS `cartype`;
CREATE TABLE `cartype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(20) DEFAULT NULL COMMENT '代码',
  `nm` varchar(20) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `rmk` varchar(150) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='车辆类型';

-- ----------------------------
-- Records of cartype
-- ----------------------------
INSERT INTO `cartype` VALUES ('7', 'A01', '洒水车', '0', '1', '没', '1', '2017-07-03 14:46:13', '1', '2017-07-03 14:46:13');

-- ----------------------------
-- Table structure for centpoint
-- ----------------------------
DROP TABLE IF EXISTS `centpoint`;
CREATE TABLE `centpoint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `proj` bigint(20) NOT NULL COMMENT '项目部',
  `bus` varchar(20) CHARACTER SET latin1 NOT NULL COMMENT '负责业务',
  `des` varchar(500) DEFAULT NULL COMMENT '项目描述',
  `lng` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '维度',
  `radius` int(10) NOT NULL COMMENT '半径',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_proj` (`proj`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='中心点设置';

-- ----------------------------
-- Records of centpoint
-- ----------------------------
INSERT INTO `centpoint` VALUES ('1', '16', '0', '霍山清扫保洁', '116.332951', '31.392786', '2', '1', '2017-07-22 09:51:52', '1', '2017-07-22 09:51:52');

-- ----------------------------
-- Table structure for citizen
-- ----------------------------
DROP TABLE IF EXISTS `citizen`;
CREATE TABLE `citizen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `wechatid` varchar(50) DEFAULT NULL COMMENT '微信id',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `nm` varchar(50) NOT NULL COMMENT '姓名',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `faceimg` varchar(300) DEFAULT NULL COMMENT '头像',
  `addr` varchar(150) DEFAULT NULL COMMENT '地址',
  `crtid` bigint(20) NOT NULL DEFAULT '1' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '1' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='市民';

-- ----------------------------
-- Records of citizen
-- ----------------------------
INSERT INTO `citizen` VALUES ('1', 'o1r6305HNecL-Kn4K14VqJGJMOCk', null, '灵犀一指', '1', 'http://wx.qlogo.cn/mmopen/GsV3RFWvsCNUq16ZhWzYlAUhIaawbdIgWy1sTPwSGrS1cYgk1iavVbicQ5Kh3LpDHK5iaSv9obfxBhFC5ztXKOYG4eibxffN2vMj/0', '中国广东佛山', '1', '2017-04-11 11:11:46', '1', '2017-04-11 11:11:46');
INSERT INTO `citizen` VALUES ('2', 'o1r630yKh4DMNUK82ge4UTMzwRCs', null, 'Alter', '2', 'http://wx.qlogo.cn/mmopen/okSD7Izia6YT9ickIJ2u96MQzPE3leoeGIMxZ8BjxfnBYjsRH17VHCF2G688973FYibMewD04QYgGnQMS4sibAicyrOv4XL8Pw16n/0', '中国广东深圳', '1', '2017-04-11 11:11:46', '1', '2017-04-11 11:11:46');
INSERT INTO `citizen` VALUES ('3', 'o1r6303LJjygnM1L6490aYJWrW6A', null, 'Alter', '1', 'http://wx.qlogo.cn/mmopen/j16amwuuSFZ9icGs7LRyobicKPdXb6IsZu0dxGKgAzSicDcNIiciaF5PJ7uiboOCMUdrg1QE1CoOELWgkhAhd08LDTtZtmq13Ribicsn/0', '中国广东深圳', '1', '2017-04-11 11:11:46', '1', '2017-04-11 11:11:46');
INSERT INTO `citizen` VALUES ('4', 'o1r6301U5l0x4o5_HTloKvnX3Kys', null, 'J.Song', '1', 'http://wx.qlogo.cn/mmopen/QnM5bMcic4Z3ibBEIJXnGIaet36UUmXOkoyzWAdXw6ph0KMeyjPJnXNukV1ia62vM1ptkyKG364fVcVOClpaLB37w/0', '中国广东深圳', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:30:33');
INSERT INTO `citizen` VALUES ('5', 'o1r6308mO5rGx1Zw9l-OXqLJXIBc', null, '罗丁丁', '1', 'http://wx.qlogo.cn/mmopen/j16amwuuSFaQln13OvQDhqKzd1wQeTEqria4HlH5lCI0teIBNKicX2icQhkqrnN1cahP3h18TEXTicCa7QCYGiacIDK76GcSchc4R/0', '中国广东深圳', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:30:33');
INSERT INTO `citizen` VALUES ('6', 'o1r6306kK-7fCvPtgs0XCcb6ZrKI', null, '文青‮', '1', 'http://wx.qlogo.cn/mmopen/GsV3RFWvsCM3uJFylBC4EqvJkIibeyN0tib4xF4h7icdC7LL64m5fbLA5aKPxgVBJr8aISZL5BicWWS1LkhhHswCfUyyaJ9WUDt6/0', '意大利米兰', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:30:33');
INSERT INTO `citizen` VALUES ('7', 'o1r630xXIiAifBZgQ6hkEtEM9E8w', null, 'mickey', '2', 'http://wx.qlogo.cn/mmopen/okSD7Izia6YQz30CQPONGQvjciaUYaV43INn6AcgKyBEVKLGEtgic0erKUQt2sYHm714o0Wmf82uN34EtmT49wFfAqzdH1DU3gw/0', '中国广东深圳', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:30:33');
INSERT INTO `citizen` VALUES ('9', null, '13113113222', 'adafadf', '3', null, '广东省深圳市南山区南山街道桂庙路51号顺天大厦', '1', '2017-06-09 19:36:13', '1', '2017-06-09 19:36:13');
INSERT INTO `citizen` VALUES ('10', null, '13128746585', '我是新市民', '3', null, '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', '1', '2017-06-12 14:43:39', '1', '2017-06-12 14:43:39');
INSERT INTO `citizen` VALUES ('11', null, '13713941700', 'mickey', '3', null, '广东省深圳市宝安区西乡街道宝源二区(兴业路)', '1', '2017-06-12 14:52:22', '1', '2017-06-12 14:52:22');
INSERT INTO `citizen` VALUES ('12', null, '13411780155', '张三', '3', null, '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', '1', '2017-06-12 15:21:07', '1', '2017-06-12 15:21:07');
INSERT INTO `citizen` VALUES ('13', null, '13411780153', '', '3', null, '广东省深圳市宝安区西乡街道宝源二区(兴业路)', '1', '2017-06-12 15:22:35', '1', '2017-06-12 15:22:35');
INSERT INTO `citizen` VALUES ('14', null, '13411780156', '没有绑定的市民', '3', null, '广东省深圳市宝安区西乡街道西乡大道298-12号宝悦酒店', '1', '2017-06-12 16:16:44', '1', '2017-06-12 16:16:44');
INSERT INTO `citizen` VALUES ('15', null, '13100794519', '王女士', '3', null, '广东省深圳市宝安区西乡街道新安四路252号流塘荔景小区', '1', '2017-06-20 15:14:46', '1', '2017-06-20 15:14:46');
INSERT INTO `citizen` VALUES ('16', null, '11111111111', '王二麻子', '3', null, '广东省中山市火炬开发区街道下街28号', '1', '2017-07-06 14:43:54', '1', '2017-07-06 14:43:54');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business` bigint(20) DEFAULT NULL COMMENT '业务板块',
  `classes` varchar(30) DEFAULT NULL COMMENT '班次名称',
  `starttime` time DEFAULT NULL COMMENT '开始时间',
  `emdtime` time DEFAULT NULL COMMENT '结束时间',
  `pjdept` bigint(20) DEFAULT NULL COMMENT '所属项目部',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT NULL COMMENT '修改人',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='班次管理';

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('64', '0', '测试班级1', '09:08:00', '09:10:00', '16', '123123', '1', '2017-06-30 09:09:22', '1', '2017-06-30 09:09:22');
INSERT INTO `classes` VALUES ('65', '0', '班次1', '10:15:00', '10:17:00', '16', '阿什顿飞seb', '1', '2017-07-17 10:16:44', '1', '2017-07-17 10:16:44');
INSERT INTO `classes` VALUES ('66', '2', '16', '02:12:13', '02:12:12', '16', null, '25', '2017-07-17 11:15:33', '25', '2017-07-17 11:15:33');

-- ----------------------------
-- Table structure for cmngroup
-- ----------------------------
DROP TABLE IF EXISTS `cmngroup`;
CREATE TABLE `cmngroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nm` varchar(100) NOT NULL COMMENT '组名',
  `userid` varchar(1000) NOT NULL COMMENT '用户id',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效0:无效',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqnm` (`nm`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='通讯组';

-- ----------------------------
-- Records of cmngroup
-- ----------------------------
INSERT INTO `cmngroup` VALUES ('1', '信息化部通讯组', '1,2,3', '1', '1', '2017-04-06 11:50:57', '1', '2017-04-11 14:23:01');
INSERT INTO `cmngroup` VALUES ('2', '佛山项目一部（清扫保洁）', '1,3', '0', '1', '2017-04-11 15:22:44', '1', '2017-06-09 10:36:40');
INSERT INTO `cmngroup` VALUES ('3', '佛山项目二部（清扫保洁）', '4,2', '1', '1', '2017-05-04 09:11:38', '1', '2017-06-07 17:19:15');
INSERT INTO `cmngroup` VALUES ('4', '佛山项目一部（垃圾清运）', '1', '1', '1', '2017-05-04 14:23:37', '1', '2017-06-07 17:38:13');
INSERT INTO `cmngroup` VALUES ('5', '项目一部2', '1', '1', '1', '2017-05-04 16:03:24', '1', '2017-05-11 15:53:39');

-- ----------------------------
-- Table structure for costrecord
-- ----------------------------
DROP TABLE IF EXISTS `costrecord`;
CREATE TABLE `costrecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ddate` date DEFAULT NULL COMMENT '申报日期',
  `dtype` varchar(12) DEFAULT NULL COMMENT '费用类型（预算明细项bditem中的代码）',
  `money` decimal(24,4) DEFAULT NULL COMMENT '金额',
  `person` bigint(20) DEFAULT NULL COMMENT '申报人员',
  `rmk` varchar(500) DEFAULT NULL COMMENT '费用说明',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='费用记录';

-- ----------------------------
-- Records of costrecord
-- ----------------------------
INSERT INTO `costrecord` VALUES ('2', '2017-06-02', '15', '0.5000', '4', '2333', '1', '2017-06-07 16:23:36', '1', '2017-06-07 16:36:29');
INSERT INTO `costrecord` VALUES ('3', '2017-06-09', '3', '52.0000', '2', '5641964196525616196419+4529615154', '1', '2017-06-07 17:41:00', '1', '2017-06-07 17:41:00');
INSERT INTO `costrecord` VALUES ('4', '2017-07-20', '27', '520.0000', '17', 'st171717ring', '25', '2017-07-20 20:01:36', '25', '2017-07-20 20:01:36');
INSERT INTO `costrecord` VALUES ('5', '2017-07-20', '27', '2.0000', '17', '741852', '25', '2017-07-20 20:22:20', '25', '2017-07-20 20:22:20');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dno` varchar(35) DEFAULT NULL COMMENT '驾照编号',
  `dclass` varchar(100) DEFAULT NULL COMMENT '准驾车型(C1..)',
  `bdate` datetime DEFAULT NULL COMMENT '领证日期',
  `edate` datetime DEFAULT NULL COMMENT '到期日期',
  `driverId` bigint(20) DEFAULT NULL COMMENT '司机',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='司机管理';

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('1', '111', '152', '2017-04-20 00:00:00', '2017-04-21 00:00:00', '2', '1', '0', '2017-04-20 16:14:55', '1', '2017-05-19 10:02:07');
INSERT INTO `driver` VALUES ('2', '222222', '152', '2017-04-01 00:00:00', '2017-04-02 00:00:00', '3', '1', '1', '2017-04-21 17:33:58', '1', '2017-05-03 10:55:12');
INSERT INTO `driver` VALUES ('4', '25546461', '152', '2013-06-15 00:00:00', '2020-06-15 00:00:00', '23', '1', '1', '2017-06-14 20:20:06', '1', '2017-06-14 20:20:06');
INSERT INTO `driver` VALUES ('5', '123', '154', '2017-06-23 00:00:00', '2017-06-22 00:00:00', '8', '1', '1', '2017-06-19 19:44:50', '1', '2017-06-19 19:53:56');
INSERT INTO `driver` VALUES ('6', 'A01', '153', '2017-06-26 00:00:00', '2017-06-28 00:00:00', '15', '1', '1', '2017-06-26 16:29:06', '1', '2017-06-26 16:29:06');
INSERT INTO `driver` VALUES ('7', 'jz001', '154', '2017-06-27 00:00:00', '2017-06-28 00:00:00', '25', '1', '1', '2017-06-27 17:17:20', '1', '2017-06-27 17:17:41');

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `busseg` char(1) NOT NULL DEFAULT '1' COMMENT '业务板块',
  `no` varchar(50) NOT NULL DEFAULT '1' COMMENT '事件编号',
  `sour` char(2) NOT NULL COMMENT '事件来源',
  `qdescribe` varchar(500) NOT NULL COMMENT '问题描述',
  `lng` varchar(20) NOT NULL COMMENT '所属经度',
  `lat` varchar(20) NOT NULL COMMENT '所属纬度',
  `addr` varchar(150) NOT NULL COMMENT '详细地址',
  `qimg` varchar(300) DEFAULT NULL COMMENT '问题图片',
  `handle` bigint(20) DEFAULT NULL COMMENT '处理人',
  `htm` datetime DEFAULT NULL COMMENT '处理时间',
  `auxiliary` bigint(20) DEFAULT NULL COMMENT '辅办人',
  `img` varchar(500) DEFAULT NULL COMMENT '处理图片',
  `estimatetm` datetime DEFAULT NULL COMMENT '预计完成时间',
  `des` varchar(500) DEFAULT NULL COMMENT '工作反馈',
  `dltm` datetime DEFAULT NULL COMMENT '派单时间',
  `current` varchar(20) NOT NULL COMMENT '当前环节',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='案件';

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES ('4', '0', 'NO.2017060002', '0', '小黄车成堆', '113.966833', '22.650422', '广东省深圳市南山区西丽街道羊台山森林公园', null, null, null, null, null, '2017-06-16 11:05:35', 'AAAAAAAAAA', '2017-06-16 11:05:53', 'ycl', '2', '2017-06-09 19:23:31', '1', '2017-06-16 11:05:53');
INSERT INTO `event` VALUES ('5', '0', 'NO.2017060003', '0', '垃圾成堆', '113.921515', '22.524895', '广东省深圳市南山区南山街道桂庙路51号顺天大厦', null, null, null, null, null, null, null, null, 'sjpd', '9', '2017-06-09 19:36:13', '9', '2017-06-09 19:36:13');
INSERT INTO `event` VALUES ('6', '0', 'NO.2017060004', '0', '垃圾成堆alert(垃圾成堆)', '113.41477', '22.428453', '广东省中山市五桂山街道南区街道办事处林场', null, null, null, null, null, null, null, null, 'sjpd', '6', '2017-06-09 19:53:53', '6', '2017-06-09 19:53:53');
INSERT INTO `event` VALUES ('7', '0', 'NO.2017060005', '2', '测试-测试', '114.076696', '22.543922', '广东省深圳市福田区华富街道富荔花园(振华西路)', null, null, null, null, null, null, null, null, 'sjpd', '1', '2017-06-09 20:01:03', '1', '2017-06-09 20:01:03');
INSERT INTO `event` VALUES ('8', '0', 'NO.2017060006', '0', '天气太热，热的---------', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, null, null, null, null, null, null, null, 'sjpd', '9', '2017-06-12 14:42:28', '9', '2017-06-12 14:42:28');
INSERT INTO `event` VALUES ('12', '0', 'NO.2017060010', '1', '小区垃圾清理不干净，几天没有运走垃圾', '113.838436', '22.605205', '广东省深圳市宝安区西乡街道航空路', null, '1', null, '2', '', '2017-06-15 16:28:44', '1111', '2017-06-15 16:29:45', 'sjpd', '2', '2017-06-12 15:16:55', '1', '2017-06-15 16:30:50');
INSERT INTO `event` VALUES ('16', '0', 'NO.2017060014', '0', '西乡大道清洁不到位', '113.859007', '22.570016', '广东省深圳市宝安区西乡街道西乡大道298-12号宝悦酒店', null, null, null, null, null, null, null, null, 'sjpd', '14', '2017-06-12 16:16:44', '14', '2017-06-12 16:16:44');
INSERT INTO `event` VALUES ('17', '1', 'NO.2017060015', '1', '马安山小区的垃圾一个星期没清运', '113.862945', '22.563417', '广东省深圳市宝安区西乡街道马鞍山小区', null, null, null, null, null, null, null, null, 'sjpd', '4', '2017-06-12 16:20:01', '4', '2017-06-12 16:20:01');
INSERT INTO `event` VALUES ('18', '2', 'NO.2017060016', '1', '事件的详细内容', '116.397726', '39.903767', '北京市东城区东华门街道天安门广场', null, '24', null, null, '', '2017-06-19 14:27:10', '也处理了', '2017-06-19 14:27:21', 'fhpd', '24', '2017-06-16 11:03:31', '24', '2017-06-19 14:30:04');
INSERT INTO `event` VALUES ('19', '0', 'NO.2017060017', '1', 'BBBBBBBBB', '116.397477', '39.908692', '北京市东城区东华门街道天安门', null, '24', null, null, '', '2017-06-19 14:26:19', '处理了', '2017-06-19 14:27:04', 'fhpd', '1', '2017-06-19 11:47:21', '24', '2017-06-19 14:28:01');
INSERT INTO `event` VALUES ('20', '0', 'NO.2017060018', '0', '宝安某小区 有大量垃圾无人清理，臭气熏天，能不能及时清理一下，那条路简直没法走了，给我们的生活打来了极大的不变', '113.89019', '22.578857', '广东省深圳市宝安区西乡街道新安四路252号流塘荔景小区', null, '25', null, '15', null, '2017-06-15 15:21:22', '一定要加快速度 加大力度 速度效率的完成', '2017-06-20 15:22:06', 'ycl', '15', '2017-06-20 15:14:46', '1', '2017-06-20 15:22:06');
INSERT INTO `event` VALUES ('21', '1', 'NO.2017060019', '1', '1', '113.398049', '22.52713', '广东省中山市石岐区街道中山康怡医疗保健中心永怡花园南区', null, '25', null, null, '', '2017-06-20 16:30:24', 'dsfdsf', '2017-06-20 16:30:34', 'fhpd', '15', '2017-06-20 16:27:49', '25', '2017-06-20 23:41:40');
INSERT INTO `event` VALUES ('22', '1', 'NO.2017060020', '3', '2', '113.481096', '22.390366', '广东省中山市五桂山街道控虾', null, '25', null, null, '', '2017-06-20 16:29:18', '234234234', '2017-06-20 16:29:28', 'fhpd', '25', '2017-06-20 16:28:21', '25', '2017-06-21 01:17:14');
INSERT INTO `event` VALUES ('23', '3', 'NO.2017060021', '1', '3', '113.36162', '22.462723', '广东省中山市南区街道文笔山', null, '25', null, null, '', '2017-06-20 16:28:46', '123123', '2017-06-20 16:29:13', 'fhpd', '1', '2017-06-20 16:28:39', '25', '2017-06-21 01:18:43');
INSERT INTO `event` VALUES ('24', '1', 'NO.2017060022', '1', '4', '113.389085', '22.439878', '广东省中山市五桂山街道石鼓挞', null, '25', null, null, '', '2017-06-20 16:45:57', '132213', '2017-06-20 16:46:04', 'fhpd', '15', '2017-06-20 16:44:28', '25', '2017-06-20 03:54:27');
INSERT INTO `event` VALUES ('25', '3', 'NO.2017060023', '1', '5', '113.389085', '22.467799', '广东省中山市东区街道古香林', null, '25', null, null, '', '2017-06-20 16:45:45', '123123', '2017-06-20 16:45:53', 'fhpd', '1', '2017-06-20 16:44:46', '25', '2017-06-20 04:20:07');
INSERT INTO `event` VALUES ('26', '0', 'NO.2017060024', '2', '6', '113.34102', '22.491909', '广东省中山市沙溪镇御庭路中海翠林兰溪园', null, '9', null, '14', '', '2017-07-14 16:30:04', 'string', '2017-07-14 16:30:57', 'ycl', '1', '2017-06-20 16:44:58', '25', '2017-07-14 16:30:57');
INSERT INTO `event` VALUES ('27', '3', 'NO.2017060025', '2', '7', '113.417924', '22.522358', '广东省中山市火炬开发区街道孙文东路', null, '25', null, null, '', '2017-06-20 16:45:19', '威风威风', '2017-06-20 16:45:28', 'fhpd', '1', '2017-06-20 16:45:14', '25', '2017-06-20 05:08:56');
INSERT INTO `event` VALUES ('29', '1', 'NO.2017060028', '3', 'aaaaa', '113.417924', '22.522358', 'aaaaa', null, null, null, null, null, null, null, null, 'sjpd', '25', '2017-06-24 14:41:39', '25', '2017-06-24 14:41:39');
INSERT INTO `event` VALUES ('32', '2', 'NO.2017060031', '3', 'EEEEE', '113.492082', '22.590843', '广东省中山市民众镇大来福围', null, null, null, null, null, null, null, null, 'sjpd', '25', '2017-06-24 17:19:24', '25', '2017-06-24 17:19:24');
INSERT INTO `event` VALUES ('33', '1', 'NO.2017060032', '3', 'aaaaa', '113.417924', '22.522358', 'aaaaa', 'aaaaa', '24', null, '26', null, '2017-07-24 15:02:41', 'string', '2017-07-24 15:09:48', 'ycl', '25', '2017-06-24 17:25:16', '24', '2017-07-24 15:09:49');
INSERT INTO `event` VALUES ('35', '3', 'NO.2017060034', '3', '此处绿化带需要修剪 修修渐渐', '113.858002', '22.566542', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '9d2aebe1db44416453be005a35384c8f,daf2b7c7bd7c776fb9dd4788e8242715', '8', null, '15', null, '2017-07-12 11:48:07', '此处绿--事件派单', '2017-07-12 12:00:27', 'ycl', '25', '2017-06-26 01:54:56', '25', '2017-07-12 12:00:27');
INSERT INTO `event` VALUES ('36', '1', 'NO.2017070001', '1', '6666666666666666666', '113.505815', '22.580699', '广东省中山市民众镇福一路', null, '24', null, '22', null, '2017-07-06 11:15:08', '事件派单啦', '2017-07-06 11:15:11', 'ycl', '25', '2017-07-04 18:17:19', '1', '2017-07-06 11:15:11');
INSERT INTO `event` VALUES ('37', '1', 'NO.2017070002', '0', '瞎扯的', '113.438524', '22.556604', '广东省中山市火炬开发区街道下街28号', null, '8', null, '15', null, '2017-07-12 11:48:07', '瞎扯的->事件派单', '2017-07-12 11:48:22', 'ycl', '16', '2017-07-06 14:43:54', '1', '2017-07-12 11:48:22');

-- ----------------------------
-- Table structure for event_log
-- ----------------------------
DROP TABLE IF EXISTS `event_log`;
CREATE TABLE `event_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `busseg` char(1) NOT NULL DEFAULT '1' COMMENT '业务板块',
  `no` varchar(50) NOT NULL DEFAULT '1' COMMENT '事件编号',
  `sour` char(2) NOT NULL COMMENT '事件来源',
  `qdescribe` varchar(500) NOT NULL COMMENT '问题描述',
  `lng` varchar(20) NOT NULL COMMENT '所属经度',
  `lat` varchar(20) NOT NULL COMMENT '所属纬度',
  `addr` varchar(150) NOT NULL COMMENT '详细地址',
  `qimg` varchar(300) DEFAULT NULL COMMENT '问题图片',
  `handle` bigint(20) DEFAULT NULL COMMENT '处理人',
  `htm` datetime DEFAULT NULL COMMENT '处理时间',
  `auxiliary` bigint(32) DEFAULT NULL COMMENT '辅办人',
  `img` varchar(500) DEFAULT NULL COMMENT '处理图片',
  `estimatetm` datetime DEFAULT NULL COMMENT '预计完成时间',
  `des` varchar(500) DEFAULT NULL COMMENT '工作反馈',
  `dltm` datetime DEFAULT NULL COMMENT '派单时间',
  `current` varchar(20) NOT NULL COMMENT '当前环节',
  `flag` char(1) DEFAULT NULL COMMENT '是否下一步',
  `next` varchar(20) DEFAULT NULL COMMENT '下一环节',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='案件日志';

-- ----------------------------
-- Records of event_log
-- ----------------------------
INSERT INTO `event_log` VALUES ('9', '1', 'NO.2017050007', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-05-19 19:02:20', '2', '2017-05-19 19:02:20');
INSERT INTO `event_log` VALUES ('10', '1', 'NO.2017050007', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '3', null, null, null, '2017-05-19 19:02:00', '杀毒', '2017-05-19 19:02:44', 'sjpd', '1', 'ycl', '1', '2017-05-19 19:02:44', '1', '2017-05-19 19:02:44');
INSERT INTO `event_log` VALUES ('11', '1', 'NO.2017050007', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-19 19:04:17', null, 'f34ea4a40d4ffd066cc313ee338935aa', '2017-05-19 19:02:00', '经办人', '2017-05-19 19:02:44', 'ycl', '1', 'fhpd', '1', '2017-05-19 19:04:17', '1', '2017-05-19 19:04:17');
INSERT INTO `event_log` VALUES ('12', '1', 'NO.2017050007', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '4', null, null, null, '2017-05-19 19:09:00', '', '2017-05-19 19:10:04', 'fhpd', '1', 'fhcl', '1', '2017-05-19 19:10:04', '1', '2017-05-19 19:10:04');
INSERT INTO `event_log` VALUES ('14', '1', 'NO.2017050007', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-19 19:12:00', null, '6df74289c32aff1a807e1382b2a17cf6', '2017-05-19 19:09:00', 'oops', '2017-05-19 19:10:04', 'fhcl', '1', 'ja', '1', '2017-05-19 19:12:00', '1', '2017-05-19 19:12:00');
INSERT INTO `event_log` VALUES ('15', '1', 'NO.2017050007', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-19 19:12:00', null, '6df74289c32aff1a807e1382b2a17cf6', '2017-05-19 19:09:00', 'oops', '2017-05-19 19:10:04', 'ja', '1', '999999', '1', '2017-05-19 19:12:00', '1', '2017-05-19 19:12:00');
INSERT INTO `event_log` VALUES ('16', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-05-19 20:50:05', '2', '2017-05-19 20:50:05');
INSERT INTO `event_log` VALUES ('17', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', null, null, null, '2017-05-19 20:50:30', '杀毒', '2017-05-19 20:51:17', 'sjpd', '1', 'ycl', '1', '2017-05-19 20:51:17', '1', '2017-05-19 20:51:17');
INSERT INTO `event_log` VALUES ('18', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-19 20:52:17', null, '', '2017-05-19 20:50:30', '这个病毒太流氓了，杀不死', '2017-05-19 20:51:17', 'ycl', '0', 'sjpd', '1', '2017-05-19 20:52:17', '1', '2017-05-19 20:52:17');
INSERT INTO `event_log` VALUES ('19', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', null, null, null, '2017-05-19 20:52:26', '', '2017-05-19 20:52:41', 'sjpd', '1', 'ycl', '1', '2017-05-19 20:52:41', '1', '2017-05-19 20:52:41');
INSERT INTO `event_log` VALUES ('20', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-19 20:53:30', null, '1652e097718e82c049a70cbb3dcdde5f', '2017-05-19 20:52:26', '已经处理完成，使用360杀毒', '2017-05-19 20:52:41', 'ycl', '1', 'fhpd', '1', '2017-05-19 20:53:30', '1', '2017-05-19 20:53:30');
INSERT INTO `event_log` VALUES ('21', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '4', null, null, null, '2017-05-19 20:57:00', '看是否真实杀死', '2017-05-19 20:56:37', 'fhpd', '1', 'fhcl', '1', '2017-05-19 20:56:37', '1', '2017-05-19 20:56:37');
INSERT INTO `event_log` VALUES ('22', '1', 'NO.2017050009', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-05-20 10:51:51', '2', '2017-05-20 10:51:51');
INSERT INTO `event_log` VALUES ('23', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-05-20 10:52:00', '2', '2017-05-20 10:52:00');
INSERT INTO `event_log` VALUES ('24', '1', 'NO.2017050011', '0', '被病毒干扰4', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-05-20 10:52:04', '2', '2017-05-20 10:52:04');
INSERT INTO `event_log` VALUES ('25', '1', 'NO.2017050009', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '2', null, null, null, '2017-05-20 10:52:30', '', '2017-05-20 10:52:32', 'sjpd', '1', 'ycl', '1', '2017-05-20 10:52:32', '1', '2017-05-20 10:52:32');
INSERT INTO `event_log` VALUES ('26', '1', 'NO.2017050009', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-20 10:52:58', null, '4d3295480b3d716f888c1b6a18c69f04', '2017-05-20 10:52:30', '1234', '2017-05-20 10:52:32', 'ycl', '1', 'fhpd', '1', '2017-05-20 10:52:58', '1', '2017-05-20 10:52:58');
INSERT INTO `event_log` VALUES ('27', '1', 'NO.2017050009', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', null, null, null, '2017-05-25 10:53:19', '1112223334444', '2017-05-20 10:53:30', 'fhpd', '1', 'fhcl', '1', '2017-05-20 10:53:30', '1', '2017-05-20 10:53:30');
INSERT INTO `event_log` VALUES ('28', '1', 'NO.2017050009', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-20 10:54:03', null, 'd69a596fad6dfbb0504e7b9db2add0d7', '2017-05-25 10:53:19', '1234', '2017-05-20 10:53:30', 'fhcl', '1', 'ja', '1', '2017-05-20 10:54:03', '1', '2017-05-20 10:54:03');
INSERT INTO `event_log` VALUES ('29', '1', 'NO.2017050009', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-20 10:54:03', null, 'd69a596fad6dfbb0504e7b9db2add0d7', '2017-05-25 10:53:19', '1234', '2017-05-20 10:53:30', 'ja', '1', '999999', '1', '2017-05-20 10:54:03', '1', '2017-05-20 10:54:03');
INSERT INTO `event_log` VALUES ('30', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', null, null, null, '2017-05-20 10:55:35', '1qaz', '2017-05-20 10:55:45', 'sjpd', '1', 'ycl', '1', '2017-05-20 10:55:45', '1', '2017-05-20 10:55:45');
INSERT INTO `event_log` VALUES ('31', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-20 10:56:02', null, '', '2017-05-20 10:55:35', '不真实', '2017-05-20 10:55:45', 'ycl', '0', 'sjpd', '1', '2017-05-20 10:56:02', '1', '2017-05-20 10:56:02');
INSERT INTO `event_log` VALUES ('32', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', null, null, null, '2017-05-25 10:56:16', '请问', '2017-05-20 10:56:26', 'sjpd', '1', 'ycl', '1', '2017-05-20 10:56:26', '1', '2017-05-20 10:56:26');
INSERT INTO `event_log` VALUES ('34', '1', 'NO.2017050012', '0', '被病毒干扰333', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-05-20 11:05:14', '2', '2017-05-20 11:05:14');
INSERT INTO `event_log` VALUES ('35', '1', 'NO.2017050012', '0', '被病毒干扰333', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, '不真实', '2017-05-20 11:05:34', 'sjpd', '0', 'ja', '1', '2017-05-20 11:05:34', '1', '2017-05-20 11:05:34');
INSERT INTO `event_log` VALUES ('36', '1', 'NO.2017050012', '0', '被病毒干扰333', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, '不真实', '2017-05-20 11:05:34', 'ja', '0', '999999', '1', '2017-05-20 11:05:34', '1', '2017-05-20 11:05:34');
INSERT INTO `event_log` VALUES ('37', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-05-20 11:48:52', null, '', '2017-05-25 10:56:16', '处理不了', '2017-05-20 10:56:26', 'ycl', '0', 'sjpd', '1', '2017-05-20 11:48:52', '1', '2017-05-20 11:48:52');
INSERT INTO `event_log` VALUES ('38', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '4', null, '2', null, null, '假的，没用的，', '2017-05-20 12:02:42', 'sjpd', '0', 'ja', '1', '2017-05-20 12:02:42', '1', '2017-05-25 16:53:14');
INSERT INTO `event_log` VALUES ('39', '1', 'NO.2017050010', '0', '被病毒干扰3', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', null, null, null, null, null, '自动结案', '2017-05-20 12:02:42', 'sjpd', '1', '999999', '1', '2017-05-20 12:02:42', '1', '2017-05-20 12:02:42');
INSERT INTO `event_log` VALUES ('40', '0', 'NO.2017060002', '0', '小黄车成堆', '113.966833', '22.650422', '广东省深圳市南山区西丽街道羊台山森林公园', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-06-09 19:23:31', '2', '2017-06-09 19:23:31');
INSERT INTO `event_log` VALUES ('41', '0', 'NO.2017060003', '0', '垃圾成堆', '113.921515', '22.524895', '广东省深圳市南山区南山街道桂庙路51号顺天大厦', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '9', '2017-06-09 19:36:13', '9', '2017-06-09 19:36:13');
INSERT INTO `event_log` VALUES ('42', '0', 'NO.2017060004', '0', '垃圾成堆alert(垃圾成堆)', '113.41477', '22.428453', '广东省中山市五桂山街道南区街道办事处林场', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '6', '2017-06-09 19:53:53', '6', '2017-06-09 19:53:53');
INSERT INTO `event_log` VALUES ('43', '0', 'NO.2017060005', '2', '测试-测试', '114.076696', '22.543922', '广东省深圳市福田区华富街道富荔花园(振华西路)', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '1', '2017-06-09 20:01:03', '1', '2017-06-09 20:01:03');
INSERT INTO `event_log` VALUES ('44', '0', 'NO.2017060006', '0', '天气太热，热的---------', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '9', '2017-06-12 14:42:28', '9', '2017-06-12 14:42:28');
INSERT INTO `event_log` VALUES ('45', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '10', '2017-06-12 14:43:39', '10', '2017-06-12 14:43:39');
INSERT INTO `event_log` VALUES ('46', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '6', null, null, null, '2017-06-13 14:44:02', '', '2017-06-12 14:44:15', 'sjpd', '1', 'ycl', '1', '2017-06-12 14:44:15', '1', '2017-06-12 15:00:37');
INSERT INTO `event_log` VALUES ('47', '2', 'NO.2017060008', '0', '小区不治安不安全', '113.860949', '22.567405', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '11', '2017-06-12 14:52:22', '11', '2017-06-12 14:52:22');
INSERT INTO `event_log` VALUES ('48', '3', 'NO.2017060009', '1', '宝安新安3区，绿化需护理', '113.89225', '22.568358', '广东省深圳市宝安区新安街道怡景北五巷8号', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '6', '2017-06-12 14:55:18', '6', '2017-06-12 14:55:18');
INSERT INTO `event_log` VALUES ('49', '3', 'NO.2017060009', '1', '宝安新安3区，绿化需护理', '113.89225', '22.568358', '广东省深圳市宝安区新安街道怡景北五巷8号', null, '2', null, '4', null, '2017-06-13 15:01:34', '蝠城卓越加时 大理在a圄土国蝠城一于一直在一直在一直在一直在一直在于一直在一二三城球棒 一直在一直在一直在一直在一直在一直在', '2017-06-12 15:02:07', 'sjpd', '1', 'ycl', '1', '2017-06-12 15:02:07', '1', '2017-06-12 15:02:07');
INSERT INTO `event_log` VALUES ('50', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '1', '2017-06-12 15:03:00', null, '42e8b4a7a4e124f4e12cf6be96a4a748', '2017-06-13 14:44:02', '事情已处理完毕', '2017-06-12 14:44:15', 'ycl', '1', 'fhpd', '1', '2017-06-12 15:03:00', '1', '2017-06-12 15:03:00');
INSERT INTO `event_log` VALUES ('51', '3', 'NO.2017060009', '1', '宝安新安3区，绿化需护理', '113.89225', '22.568358', '广东省深圳市宝安区新安街道怡景北五巷8号', null, '1', '2017-06-12 15:06:04', '4', 'b324ca466bb40e5c73eb2ff2bf08facd,09fd04bb40d203a47c4df74bd27dd60e,9a0c98955dfb334ce141f49325365c59', '2017-06-13 15:01:34', '', '2017-06-12 15:02:07', 'ycl', '1', 'fhpd', '1', '2017-06-12 15:06:04', '1', '2017-06-12 15:06:04');
INSERT INTO `event_log` VALUES ('52', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-06-12 15:09:35', null, 'a890156f63b04603032273d9f867e8d8', '2017-05-19 20:57:00', '', '2017-05-19 20:56:37', 'fhcl', '1', 'ja', '1', '2017-06-12 15:09:35', '1', '2017-06-12 15:09:35');
INSERT INTO `event_log` VALUES ('53', '1', 'NO.2017050008', '0', '被病毒干扰', '113.858707', '22.56675', '友情基地大厦', '3798f1a7653b34fde4b21708ba2dfd93', '1', '2017-06-12 15:09:35', null, 'a890156f63b04603032273d9f867e8d8', '2017-05-19 20:57:00', '自动结案', '2017-05-19 20:56:37', 'ja', '1', '999999', '99999', '2017-06-12 15:09:35', '99999', '2017-06-12 15:09:35');
INSERT INTO `event_log` VALUES ('54', '2', 'NO.2017060008', '0', '小区不治安不安全', '113.860949', '22.567405', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '6', null, '4', null, '2017-06-14 15:12:13', '', '2017-06-12 15:12:26', 'sjpd', '1', 'ycl', '1', '2017-06-12 15:12:26', '1', '2017-06-12 15:12:26');
INSERT INTO `event_log` VALUES ('55', '3', 'NO.2017060009', '1', '宝安新安3区，绿化需护理', '113.89225', '22.568358', '广东省深圳市宝安区新安街道怡景北五巷8号', null, '4', null, '2', null, '2017-06-14 15:12:53', '', '2017-06-12 15:13:03', 'fhpd', '1', 'fhcl', '1', '2017-06-12 15:13:03', '1', '2017-06-12 15:13:03');
INSERT INTO `event_log` VALUES ('56', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '4', null, '2', null, '2017-06-13 15:14:11', '', '2017-06-12 15:14:23', 'fhpd', '1', 'fhcl', '1', '2017-06-12 15:14:23', '1', '2017-06-12 15:14:23');
INSERT INTO `event_log` VALUES ('57', '0', 'NO.2017060010', '1', '小区垃圾清理不干净，几天没有运走垃圾', '113.838436', '22.605205', '广东省深圳市宝安区西乡街道航空路', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '2', '2017-06-12 15:16:55', '2', '2017-06-12 15:16:55');
INSERT INTO `event_log` VALUES ('58', '0', 'NO.2017060010', '1', '小区垃圾清理不干净，几天没有运走垃圾', '113.838436', '22.605205', '广东省深圳市宝安区西乡街道航空路', null, '4', null, '2', null, '2017-06-13 15:17:15', '', '2017-06-12 15:17:20', 'sjpd', '1', 'ycl', '1', '2017-06-12 15:17:20', '1', '2017-06-12 15:17:20');
INSERT INTO `event_log` VALUES ('59', '0', 'NO.2017060011', '0', '垃圾乱摆放', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '12', '2017-06-12 15:21:07', '12', '2017-06-12 15:21:07');
INSERT INTO `event_log` VALUES ('60', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '13', '2017-06-12 15:22:35', '13', '2017-06-12 15:22:35');
INSERT INTO `event_log` VALUES ('61', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '6', null, null, null, '2017-06-14 15:23:08', '', '2017-06-12 15:23:20', 'sjpd', '1', 'ycl', '1', '2017-06-12 15:23:20', '1', '2017-06-12 15:23:20');
INSERT INTO `event_log` VALUES ('62', '0', 'NO.2017060011', '0', '垃圾乱摆放', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, '6', null, '4', null, '2017-06-15 15:24:09', '', '2017-06-12 15:24:20', 'sjpd', '1', 'ycl', '1', '2017-06-12 15:24:20', '1', '2017-06-12 15:24:20');
INSERT INTO `event_log` VALUES ('63', '0', 'NO.2017060011', '0', '垃圾乱摆放', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, '1', '2017-06-12 15:35:46', '4', '44e0ee84cb71f75c5d21f3701262a2ca', '2017-06-15 15:24:09', '已处理完成', '2017-06-12 15:24:20', 'ycl', '1', 'fhpd', '1', '2017-06-12 15:35:46', '1', '2017-06-12 15:35:46');
INSERT INTO `event_log` VALUES ('64', '0', 'NO.2017060013', '0', '垃圾乱放', '113.860342', '22.565083', '广东省深圳市宝安区西乡街道时尚创意园', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '11', '2017-06-12 15:39:48', '11', '2017-06-12 15:39:48');
INSERT INTO `event_log` VALUES ('65', '0', 'NO.2017060013', '0', '垃圾乱放', '113.860342', '22.565083', '广东省深圳市宝安区西乡街道时尚创意园', null, '2', null, null, null, '2017-06-13 15:40:27', '', '2017-06-12 15:40:36', 'sjpd', '1', 'ycl', '1', '2017-06-12 15:40:36', '1', '2017-06-12 15:40:36');
INSERT INTO `event_log` VALUES ('66', '0', 'NO.2017060013', '0', '垃圾乱放', '113.860342', '22.565083', '广东省深圳市宝安区西乡街道时尚创意园', null, '1', '2017-06-12 15:41:28', null, '4fc4ad77ff5058050cbb0c86e9e73f1f', '2017-06-13 15:40:27', '已处理完成3', '2017-06-12 15:40:36', 'ycl', '1', 'fhpd', '1', '2017-06-12 15:41:28', '1', '2017-06-12 15:41:28');
INSERT INTO `event_log` VALUES ('67', '0', 'NO.2017060013', '0', '垃圾乱放', '113.860342', '22.565083', '广东省深圳市宝安区西乡街道时尚创意园', null, '6', null, '2', null, '2017-06-13 15:55:10', '', '2017-06-12 15:55:23', 'fhpd', '1', 'fhcl', '1', '2017-06-12 15:55:23', '1', '2017-06-12 15:55:23');
INSERT INTO `event_log` VALUES ('68', '0', 'NO.2017060013', '0', '垃圾乱放', '113.860342', '22.565083', '广东省深圳市宝安区西乡街道时尚创意园', null, '1', '2017-06-12 15:57:30', '2', 'af2165ae9e35d108b0d899580c8c2414', '2017-06-13 15:55:10', '确定已处理完', '2017-06-12 15:55:23', 'fhcl', '1', 'ja', '1', '2017-06-12 15:57:30', '1', '2017-06-12 15:57:30');
INSERT INTO `event_log` VALUES ('69', '0', 'NO.2017060013', '0', '垃圾乱放', '113.860342', '22.565083', '广东省深圳市宝安区西乡街道时尚创意园', null, '1', '2017-06-12 15:57:30', '2', 'af2165ae9e35d108b0d899580c8c2414', '2017-06-13 15:55:10', '自动结案', '2017-06-12 15:55:23', 'ja', '1', '999999', '99999', '2017-06-12 15:57:30', '99999', '2017-06-12 15:57:30');
INSERT INTO `event_log` VALUES ('70', '0', 'NO.2017060011', '0', '垃圾乱摆放', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, '6', null, null, null, '2017-06-15 15:58:32', '', '2017-06-12 15:58:47', 'fhpd', '1', 'fhcl', '1', '2017-06-12 15:58:47', '1', '2017-06-12 15:58:47');
INSERT INTO `event_log` VALUES ('71', '0', 'NO.2017060011', '0', '垃圾乱摆放', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, '1', '2017-06-12 16:00:09', '4', 'f3e25d4a74a7fc5e1f6e282dc6a4613a', '2017-06-15 15:58:32', '', '2017-06-12 15:58:47', 'fhcl', '1', 'ja', '1', '2017-06-12 16:00:09', '1', '2017-06-12 16:00:09');
INSERT INTO `event_log` VALUES ('72', '0', 'NO.2017060011', '0', '垃圾乱摆放', '113.863431', '22.56819', '广东省深圳市宝安区西乡街道宝源二区(兴业路)前岸国际酒店', null, '1', '2017-06-12 16:00:09', '4', 'f3e25d4a74a7fc5e1f6e282dc6a4613a', '2017-06-15 15:58:32', '自动结案', '2017-06-12 15:58:47', 'ja', '1', '999999', '99999', '2017-06-12 16:00:09', '99999', '2017-06-12 16:00:09');
INSERT INTO `event_log` VALUES ('73', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-12 16:03:07', null, '', '2017-06-14 15:23:08', '事情不属实', '2017-06-12 15:23:20', 'ycl', '0', 'sjpd', '1', '2017-06-12 16:03:07', '1', '2017-06-12 16:03:07');
INSERT INTO `event_log` VALUES ('74', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '4', null, '2', null, '2017-06-13 16:03:43', '事件属实', '2017-06-12 16:04:02', 'sjpd', '1', 'ycl', '1', '2017-06-12 16:04:02', '1', '2017-06-12 16:04:02');
INSERT INTO `event_log` VALUES ('75', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-12 16:07:17', '2', '67cedd2fbfefeb35329c6033915588da', '2017-06-13 16:03:43', '', '2017-06-12 16:04:02', 'ycl', '1', 'fhpd', '1', '2017-06-12 16:07:17', '1', '2017-06-12 16:07:17');
INSERT INTO `event_log` VALUES ('76', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '4', null, '2', null, '2017-06-13 16:08:00', '', '2017-06-12 16:08:07', 'fhpd', '1', 'fhcl', '1', '2017-06-12 16:08:07', '1', '2017-06-12 16:08:07');
INSERT INTO `event_log` VALUES ('77', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-12 16:08:55', '2', 'a44c1984f06c85acf9da561da1931523', '2017-06-13 16:08:00', '审核完成，处理得很好，给5分', '2017-06-12 16:08:07', 'fhcl', '1', 'ja', '1', '2017-06-12 16:08:55', '1', '2017-06-12 16:08:55');
INSERT INTO `event_log` VALUES ('78', '0', 'NO.2017060012', '0', '', '113.859514', '22.565786', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-12 16:08:55', '2', 'a44c1984f06c85acf9da561da1931523', '2017-06-13 16:08:00', '自动结案', '2017-06-12 16:08:07', 'ja', '1', '999999', '99999', '2017-06-12 16:08:55', '99999', '2017-06-12 16:08:55');
INSERT INTO `event_log` VALUES ('79', '0', 'NO.2017060014', '0', '西乡大道清洁不到位', '113.859007', '22.570016', '广东省深圳市宝安区西乡街道西乡大道298-12号宝悦酒店', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '14', '2017-06-12 16:16:44', '14', '2017-06-12 16:16:44');
INSERT INTO `event_log` VALUES ('80', '1', 'NO.2017060015', '1', '马安山小区的垃圾一个星期没清运', '113.862945', '22.563417', '广东省深圳市宝安区西乡街道马鞍山小区', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '4', '2017-06-12 16:20:01', '4', '2017-06-12 16:20:01');
INSERT INTO `event_log` VALUES ('81', '3', 'NO.2017060009', '1', '宝安新安3区，绿化需护理', '113.89225', '22.568358', '广东省深圳市宝安区新安街道怡景北五巷8号', null, '1', '2017-06-15 10:19:30', '2', '3f7b37c654db8900b14acc1334ef5749,2049ffe68c1e12a609212011cedb7944', '2017-06-14 15:12:53', '11111', '2017-06-12 15:13:03', 'fhcl', '1', 'ja', '1', '2017-06-15 10:19:30', '1', '2017-06-15 10:19:30');
INSERT INTO `event_log` VALUES ('82', '3', 'NO.2017060009', '1', '宝安新安3区，绿化需护理', '113.89225', '22.568358', '广东省深圳市宝安区新安街道怡景北五巷8号', null, '1', '2017-06-15 10:19:30', '2', '3f7b37c654db8900b14acc1334ef5749,2049ffe68c1e12a609212011cedb7944', '2017-06-14 15:12:53', '自动结案', '2017-06-12 15:13:03', 'ja', '1', '999999', '99999', '2017-06-15 10:19:30', '99999', '2017-06-15 10:19:30');
INSERT INTO `event_log` VALUES ('83', '0', 'NO.2017060010', '1', '小区垃圾清理不干净，几天没有运走垃圾', '113.838436', '22.605205', '广东省深圳市宝安区西乡街道航空路', null, '1', '2017-06-15 10:30:28', '2', '', '2017-06-13 15:17:15', '111', '2017-06-12 15:17:20', 'ycl', '0', 'sjpd', '1', '2017-06-15 10:30:28', '1', '2017-06-15 10:30:28');
INSERT INTO `event_log` VALUES ('84', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '1', '2017-06-15 11:04:46', '2', '', '2017-06-13 15:14:11', '111', '2017-06-12 15:14:23', 'fhcl', '0', 'fhpd', '1', '2017-06-15 11:04:46', '1', '2017-06-15 11:04:46');
INSERT INTO `event_log` VALUES ('85', '2', 'NO.2017060008', '0', '小区不治安不安全', '113.860949', '22.567405', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-15 14:39:10', '4', '62a184365d0c244ebb2c073790500798,42c21f5e1901b5f2d1b28cc1ce6bb2c2', '2017-06-14 15:12:13', '1111111', '2017-06-12 15:12:26', 'ycl', '1', 'fhpd', '1', '2017-06-15 14:39:10', '1', '2017-06-15 14:39:10');
INSERT INTO `event_log` VALUES ('86', '2', 'NO.2017060008', '0', '小区不治安不安全', '113.860949', '22.567405', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', null, null, null, '2017-06-15 14:41:30', '', '2017-06-15 14:41:37', 'fhpd', '1', 'fhcl', '1', '2017-06-15 14:41:37', '1', '2017-06-15 14:41:37');
INSERT INTO `event_log` VALUES ('87', '2', 'NO.2017060008', '0', '小区不治安不安全', '113.860949', '22.567405', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-15 14:52:03', '4', '7c3e3efc280940a0461428b91f5d9c79,f94bcae7e6c480a01e756fe2643a69ba', '2017-06-15 14:41:30', '22222222222222', '2017-06-15 14:41:37', 'fhcl', '1', 'ja', '1', '2017-06-15 14:52:03', '1', '2017-06-15 14:52:03');
INSERT INTO `event_log` VALUES ('88', '2', 'NO.2017060008', '0', '小区不治安不安全', '113.860949', '22.567405', '广东省深圳市宝安区西乡街道宝源二区(兴业路)', null, '1', '2017-06-15 14:52:03', '4', '7c3e3efc280940a0461428b91f5d9c79,f94bcae7e6c480a01e756fe2643a69ba', '2017-06-15 14:41:30', '自动结案', '2017-06-15 14:41:37', 'ja', '1', '999999', '99999', '2017-06-15 14:52:03', '99999', '2017-06-15 14:52:03');
INSERT INTO `event_log` VALUES ('89', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '1', null, null, null, '2017-06-15 15:17:03', '', '2017-06-15 15:17:05', 'fhpd', '1', 'fhcl', '1', '2017-06-15 15:17:05', '1', '2017-06-15 15:17:05');
INSERT INTO `event_log` VALUES ('90', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '1', '2017-06-15 16:28:11', '2', 'b9cdce193577b85c51a4c7e107d6158f,af6a21a193c0b5b8e614614d0c3157d0', '2017-06-15 15:17:03', '1111111', '2017-06-15 15:17:05', 'fhcl', '1', 'ja', '1', '2017-06-15 16:28:11', '1', '2017-06-15 16:28:11');
INSERT INTO `event_log` VALUES ('91', '0', 'NO.2017060007', '0', '天啦，这里一群傻逼', '113.866046', '22.567722', '广东省深圳市宝安区西乡街道中国邮政储蓄银行(深圳宝源支行)宝安区华丰华源科技创新园A座', null, '1', '2017-06-15 16:28:11', '2', 'b9cdce193577b85c51a4c7e107d6158f,af6a21a193c0b5b8e614614d0c3157d0', '2017-06-15 15:17:03', '自动结案', '2017-06-15 15:17:05', 'ja', '1', '999999', '99999', '2017-06-15 16:28:11', '99999', '2017-06-15 16:28:11');
INSERT INTO `event_log` VALUES ('92', '0', 'NO.2017060010', '1', '小区垃圾清理不干净，几天没有运走垃圾', '113.838436', '22.605205', '广东省深圳市宝安区西乡街道航空路', null, '1', null, null, null, '2017-06-15 16:28:44', '555555', '2017-06-15 16:29:45', 'sjpd', '1', 'ycl', '1', '2017-06-15 16:29:45', '1', '2017-06-15 16:29:45');
INSERT INTO `event_log` VALUES ('93', '0', 'NO.2017060010', '1', '小区垃圾清理不干净，几天没有运走垃圾', '113.838436', '22.605205', '广东省深圳市宝安区西乡街道航空路', null, '1', '2017-06-15 16:30:50', '2', '', '2017-06-15 16:28:44', '1111', '2017-06-15 16:29:45', 'ycl', '0', 'sjpd', '1', '2017-06-15 16:30:50', '1', '2017-06-15 16:30:50');
INSERT INTO `event_log` VALUES ('94', '2', 'NO.2017060016', '1', '事件的详细内容', '116.397726', '39.903767', '北京市东城区东华门街道天安门广场', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '24', '2017-06-16 11:03:31', '24', '2017-06-16 11:03:31');
INSERT INTO `event_log` VALUES ('95', '0', 'NO.2017060002', '0', '小黄车成堆', '113.966833', '22.650422', '广东省深圳市南山区西丽街道羊台山森林公园', null, '24', null, null, null, '2017-06-16 11:05:35', 'AAAAAAAAAA', '2017-06-16 11:05:53', 'sjpd', '1', 'ycl', '1', '2017-06-16 11:05:53', '1', '2017-06-16 11:05:53');
INSERT INTO `event_log` VALUES ('96', '2', 'NO.2017060016', '1', '事件的详细内容', '116.397726', '39.903767', '北京市东城区东华门街道天安门广场', null, '24', null, null, null, '2017-06-16 11:06:48', 'AAAAAAAAAAA', '2017-06-16 11:06:50', 'sjpd', '1', 'ycl', '1', '2017-06-16 11:06:50', '1', '2017-06-16 11:06:50');
INSERT INTO `event_log` VALUES ('97', '0', 'NO.2017060017', '1', 'BBBBBBBBB', '116.397477', '39.908692', '北京市东城区东华门街道天安门', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '1', '2017-06-19 11:47:21', '1', '2017-06-19 11:47:21');
INSERT INTO `event_log` VALUES ('98', '0', 'NO.2017060017', '1', 'BBBBBBBBB', '116.397477', '39.908692', '北京市东城区东华门街道天安门', null, '24', null, null, null, '2017-06-19 11:56:07', '事件派单的备注', '2017-06-19 11:56:32', 'sjpd', '1', 'ycl', '1', '2017-06-19 11:56:32', '1', '2017-06-19 11:56:32');
INSERT INTO `event_log` VALUES ('99', '2', 'NO.2017060016', '1', '事件的详细内容', '116.397726', '39.903767', '北京市东城区东华门街道天安门广场', null, '24', '2017-06-19 14:21:40', null, '', '2017-06-16 11:06:48', '详细内容  撤回撤回撤回撤回', '2017-06-16 11:06:50', 'ycl', '0', 'sjpd', '24', '2017-06-19 14:21:40', '24', '2017-06-19 14:21:40');
INSERT INTO `event_log` VALUES ('100', '0', 'NO.2017060017', '1', 'BBBBBBBBB', '116.397477', '39.908692', '北京市东城区东华门街道天安门', null, '24', '2017-06-19 14:23:54', null, '', '2017-06-19 11:56:07', '也撤回吧', '2017-06-19 11:56:32', 'ycl', '0', 'sjpd', '24', '2017-06-19 14:23:54', '24', '2017-06-19 14:23:54');
INSERT INTO `event_log` VALUES ('101', '0', 'NO.2017060017', '1', 'BBBBBBBBB', '116.397477', '39.908692', '北京市东城区东华门街道天安门', null, '24', null, null, null, '2017-06-19 14:26:19', '派给你', '2017-06-19 14:27:04', 'sjpd', '1', 'ycl', '1', '2017-06-19 14:27:04', '1', '2017-06-19 14:27:04');
INSERT INTO `event_log` VALUES ('102', '2', 'NO.2017060016', '1', '事件的详细内容', '116.397726', '39.903767', '北京市东城区东华门街道天安门广场', null, '24', null, null, null, '2017-06-19 14:27:10', '也派给你', '2017-06-19 14:27:21', 'sjpd', '1', 'ycl', '1', '2017-06-19 14:27:21', '1', '2017-06-19 14:27:21');
INSERT INTO `event_log` VALUES ('103', '0', 'NO.2017060017', '1', 'BBBBBBBBB', '116.397477', '39.908692', '北京市东城区东华门街道天安门', null, '24', '2017-06-19 14:28:01', null, '', '2017-06-19 14:26:19', '处理了', '2017-06-19 14:27:04', 'ycl', '1', 'fhpd', '24', '2017-06-19 14:28:01', '24', '2017-06-19 14:28:01');
INSERT INTO `event_log` VALUES ('104', '2', 'NO.2017060016', '1', '事件的详细内容', '116.397726', '39.903767', '北京市东城区东华门街道天安门广场', null, '24', '2017-06-19 14:30:04', null, '', '2017-06-19 14:27:10', '也处理了', '2017-06-19 14:27:21', 'ycl', '1', 'fhpd', '24', '2017-06-19 14:30:04', '24', '2017-06-19 14:30:04');
INSERT INTO `event_log` VALUES ('105', '0', 'NO.2017060018', '0', '宝安某小区 有大量垃圾无人清理，臭气熏天，能不能及时清理一下，那条路简直没法走了，给我们的生活打来了极大的不变', '113.89019', '22.578857', '广东省深圳市宝安区西乡街道新安四路252号流塘荔景小区', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '15', '2017-06-20 15:14:46', '15', '2017-06-20 15:14:46');
INSERT INTO `event_log` VALUES ('106', '0', 'NO.2017060018', '0', '宝安某小区 有大量垃圾无人清理，臭气熏天，能不能及时清理一下，那条路简直没法走了，给我们的生活打来了极大的不变', '113.89019', '22.578857', '广东省深圳市宝安区西乡街道新安四路252号流塘荔景小区', null, '25', null, '15', null, '2017-06-15 15:21:22', '一定要加快速度 加大力度 速度效率的完成', '2017-06-20 15:22:06', 'sjpd', '1', 'ycl', '1', '2017-06-20 15:22:06', '1', '2017-06-20 15:22:06');
INSERT INTO `event_log` VALUES ('107', '1', 'NO.2017060019', '1', '1', '113.398049', '22.52713', '广东省中山市石岐区街道中山康怡医疗保健中心永怡花园南区', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '15', '2017-06-20 16:27:49', '15', '2017-06-20 16:27:49');
INSERT INTO `event_log` VALUES ('108', '1', 'NO.2017060020', '3', '2', '113.481096', '22.390366', '广东省中山市五桂山街道控虾', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-06-20 16:28:21', '25', '2017-06-20 16:28:21');
INSERT INTO `event_log` VALUES ('109', '3', 'NO.2017060021', '1', '3', '113.36162', '22.462723', '广东省中山市南区街道文笔山', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '1', '2017-06-20 16:28:39', '1', '2017-06-20 16:28:39');
INSERT INTO `event_log` VALUES ('110', '3', 'NO.2017060021', '1', '3', '113.36162', '22.462723', '广东省中山市南区街道文笔山', null, '25', null, null, null, '2017-06-20 16:28:46', '33', '2017-06-20 16:29:13', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:29:13', '1', '2017-06-20 16:29:13');
INSERT INTO `event_log` VALUES ('111', '1', 'NO.2017060020', '3', '2', '113.481096', '22.390366', '广东省中山市五桂山街道控虾', null, '25', null, null, null, '2017-06-20 16:29:18', '22', '2017-06-20 16:29:28', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:29:28', '1', '2017-06-20 16:29:28');
INSERT INTO `event_log` VALUES ('112', '1', 'NO.2017060019', '1', '1', '113.398049', '22.52713', '广东省中山市石岐区街道中山康怡医疗保健中心永怡花园南区', null, '25', null, null, null, '2017-06-20 16:30:24', '11', '2017-06-20 16:30:34', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:30:34', '1', '2017-06-20 16:30:34');
INSERT INTO `event_log` VALUES ('113', '1', 'NO.2017060022', '1', '4', '113.389085', '22.439878', '广东省中山市五桂山街道石鼓挞', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '15', '2017-06-20 16:44:28', '15', '2017-06-20 16:44:28');
INSERT INTO `event_log` VALUES ('114', '3', 'NO.2017060023', '1', '5', '113.389085', '22.467799', '广东省中山市东区街道古香林', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '1', '2017-06-20 16:44:46', '1', '2017-06-20 16:44:46');
INSERT INTO `event_log` VALUES ('115', '0', 'NO.2017060024', '2', '6', '113.34102', '22.491909', '广东省中山市沙溪镇御庭路中海翠林兰溪园', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '1', '2017-06-20 16:44:58', '1', '2017-06-20 16:44:58');
INSERT INTO `event_log` VALUES ('116', '3', 'NO.2017060025', '2', '7', '113.417924', '22.522358', '广东省中山市火炬开发区街道孙文东路', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '1', '2017-06-20 16:45:14', '1', '2017-06-20 16:45:14');
INSERT INTO `event_log` VALUES ('117', '3', 'NO.2017060025', '2', '7', '113.417924', '22.522358', '广东省中山市火炬开发区街道孙文东路', null, '25', null, null, null, '2017-06-20 16:45:19', '77', '2017-06-20 16:45:28', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:45:28', '1', '2017-06-20 16:45:28');
INSERT INTO `event_log` VALUES ('118', '0', 'NO.2017060024', '2', '6', '113.34102', '22.491909', '广东省中山市沙溪镇御庭路中海翠林兰溪园', null, '25', null, null, null, '2017-06-20 16:45:32', '66', '2017-06-20 16:45:40', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:45:40', '1', '2017-06-20 16:45:40');
INSERT INTO `event_log` VALUES ('119', '3', 'NO.2017060023', '1', '5', '113.389085', '22.467799', '广东省中山市东区街道古香林', null, '25', null, null, null, '2017-06-20 16:45:45', '55', '2017-06-20 16:45:53', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:45:53', '1', '2017-06-20 16:45:53');
INSERT INTO `event_log` VALUES ('120', '1', 'NO.2017060022', '1', '4', '113.389085', '22.439878', '广东省中山市五桂山街道石鼓挞', null, '25', null, null, null, '2017-06-20 16:45:57', '44', '2017-06-20 16:46:04', 'sjpd', '1', 'ycl', '1', '2017-06-20 16:46:04', '1', '2017-06-20 16:46:04');
INSERT INTO `event_log` VALUES ('121', '1', 'NO.2017060022', '1', '4', '113.389085', '22.439878', '广东省中山市五桂山街道石鼓挞', null, '25', '2017-06-20 03:54:27', null, '', '2017-06-20 16:45:57', '132213', '2017-06-20 16:46:04', 'ycl', '1', 'fhpd', '25', '2017-06-20 03:54:27', '25', '2017-06-20 03:54:27');
INSERT INTO `event_log` VALUES ('122', '3', 'NO.2017060023', '1', '5', '113.389085', '22.467799', '广东省中山市东区街道古香林', null, '25', '2017-06-20 04:20:07', null, '', '2017-06-20 16:45:45', '123123', '2017-06-20 16:45:53', 'ycl', '1', 'fhpd', '25', '2017-06-20 04:20:07', '25', '2017-06-20 04:20:07');
INSERT INTO `event_log` VALUES ('123', '0', 'NO.2017060024', '2', '6', '113.34102', '22.491909', '广东省中山市沙溪镇御庭路中海翠林兰溪园', null, '25', '2017-06-20 19:27:08', null, '', '2017-06-20 16:45:32', '撤回', '2017-06-20 16:45:40', 'ycl', '0', 'sjpd', '25', '2017-06-20 19:27:08', '25', '2017-06-20 19:27:08');
INSERT INTO `event_log` VALUES ('124', '3', 'NO.2017060025', '2', '7', '113.417924', '22.522358', '广东省中山市火炬开发区街道孙文东路', null, '25', '2017-06-20 05:08:55', null, '', '2017-06-20 16:45:19', '威风威风', '2017-06-20 16:45:28', 'ycl', '1', 'fhpd', '25', '2017-06-20 05:08:55', '25', '2017-06-20 05:08:55');
INSERT INTO `event_log` VALUES ('125', '1', 'NO.2017060019', '1', '1', '113.398049', '22.52713', '广东省中山市石岐区街道中山康怡医疗保健中心永怡花园南区', null, '25', '2017-06-20 23:41:40', null, '', '2017-06-20 16:30:24', 'dsfdsf', '2017-06-20 16:30:34', 'ycl', '1', 'fhpd', '25', '2017-06-20 23:41:40', '25', '2017-06-20 23:41:40');
INSERT INTO `event_log` VALUES ('126', '1', 'NO.2017060020', '3', '2', '113.481096', '22.390366', '广东省中山市五桂山街道控虾', null, '25', '2017-06-21 01:17:14', null, '', '2017-06-20 16:29:18', '234234234', '2017-06-20 16:29:28', 'ycl', '1', 'fhpd', '25', '2017-06-21 01:17:14', '25', '2017-06-21 01:17:14');
INSERT INTO `event_log` VALUES ('127', '3', 'NO.2017060021', '1', '3', '113.36162', '22.462723', '广东省中山市南区街道文笔山', null, '25', '2017-06-21 01:18:43', null, '', '2017-06-20 16:28:46', '123123', '2017-06-20 16:29:13', 'ycl', '1', 'fhpd', '25', '2017-06-21 01:18:43', '25', '2017-06-21 01:18:43');
INSERT INTO `event_log` VALUES ('130', '1', 'NO.2017060028', '3', 'aaaaa', '113.36162', '22.522358', 'aaaaa', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-06-24 14:41:39', '25', '2017-06-24 14:41:39');
INSERT INTO `event_log` VALUES ('133', '2', 'NO.2017060031', '3', 'EEEEE', '113.492082', '22.590843', '广东省中山市民众镇大来福围', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-06-24 17:19:24', '25', '2017-06-24 17:19:24');
INSERT INTO `event_log` VALUES ('134', '1', 'NO.2017060032', '3', 'aaaaa', '113.36162', '22.522358', 'aaaaa', 'aaaaa', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-06-24 17:25:16', '25', '2017-06-24 17:25:16');
INSERT INTO `event_log` VALUES ('135', '1', 'NO.2017060033', '3', '西乡查查查按需垃圾好多啊快来清理啊', '113.858043', '22.566609', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-06-26 01:45:32', '25', '2017-06-26 01:45:32');
INSERT INTO `event_log` VALUES ('136', '3', 'NO.2017060034', '3', '此处绿化带需要修剪 修修渐渐', '113.858002', '22.566542', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '9d2aebe1db44416453be005a35384c8f,daf2b7c7bd7c776fb9dd4788e8242715', null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-06-26 01:54:56', '25', '2017-06-26 01:54:56');
INSERT INTO `event_log` VALUES ('137', '1', 'NO.2017070001', '1', '6666666666666666666', '113.505815', '22.580699', '广东省中山市民众镇福一路', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '25', '2017-07-04 18:17:19', '25', '2017-07-04 18:17:19');
INSERT INTO `event_log` VALUES ('138', '1', 'NO.2017070001', '1', '6666666666666666666', '113.505815', '22.580699', '广东省中山市民众镇福一路', null, '24', null, '22', null, '2017-07-06 11:15:08', '事件派单啦', '2017-07-06 11:15:11', 'sjpd', '1', 'ycl', '1', '2017-07-06 11:15:11', '1', '2017-07-06 11:15:11');
INSERT INTO `event_log` VALUES ('139', '1', 'NO.2017070002', '0', '瞎扯的', '113.438524', '22.556604', '广东省中山市火炬开发区街道下街28号', null, null, null, null, null, null, null, null, 'bl', '1', 'sjpd', '16', '2017-07-06 14:43:54', '16', '2017-07-06 14:43:54');
INSERT INTO `event_log` VALUES ('140', '1', 'NO.2017070002', '0', '瞎扯的', '113.438524', '22.556604', '广东省中山市火炬开发区街道下街28号', null, '8', null, '15', null, '2017-07-12 11:48:07', '瞎扯的->事件派单', '2017-07-12 11:48:22', 'sjpd', '1', 'ycl', '1', '2017-07-12 11:48:22', '1', '2017-07-12 11:48:22');
INSERT INTO `event_log` VALUES ('141', '3', 'NO.2017060034', '3', '此处绿化带需要修剪 修修渐渐', '113.858002', '22.566542', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '9d2aebe1db44416453be005a35384c8f,daf2b7c7bd7c776fb9dd4788e8242715', '8', null, '15', null, '2017-07-12 11:48:07', '此处绿--事件派单', '2017-07-12 12:00:27', 'sjpd', '1', 'ycl', '25', '2017-07-12 12:00:27', '25', '2017-07-12 12:00:27');
INSERT INTO `event_log` VALUES ('142', '1', 'NO.2017060033', '3', '西乡查查查按需垃圾好多啊快来清理啊', '113.858043', '22.566609', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '', null, null, null, null, null, '诶我去多群', '2017-07-14 16:20:27', 'sjpd', '0', 'ja', '25', '2017-07-14 16:20:27', '25', '2017-07-14 16:20:27');
INSERT INTO `event_log` VALUES ('143', '1', 'NO.2017060033', '3', '西乡查查查按需垃圾好多啊快来清理啊', '113.858043', '22.566609', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '', null, null, null, null, null, '自动结案', '2017-07-14 16:20:27', 'ja', '1', '999999', '99999', '2017-07-14 16:20:27', '99999', '2017-07-14 16:20:27');
INSERT INTO `event_log` VALUES ('144', '0', 'NO.2017060024', '2', '6', '113.34102', '22.491909', '广东省中山市沙溪镇御庭路中海翠林兰溪园', null, '9', null, '14', null, '2017-07-14 16:30:04', 'string', '2017-07-14 16:30:57', 'sjpd', '1', 'ycl', '25', '2017-07-14 16:30:57', '25', '2017-07-14 16:30:57');
INSERT INTO `event_log` VALUES ('145', '1', 'NO.2017060032', '3', 'aaaaa', '113.417924', '22.522358', 'aaaaa', 'aaaaa', '24', null, '26', null, '2017-07-24 15:02:41', 'string', '2017-07-24 15:09:48', 'sjpd', '1', 'ycl', '24', '2017-07-24 15:09:48', '24', '2017-07-24 15:09:48');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `infosource` varchar(12) DEFAULT NULL COMMENT '信息来源',
  `problemtype` varchar(12) DEFAULT NULL COMMENT '问题类型',
  `img` varchar(512) DEFAULT NULL COMMENT '图片',
  `content` varchar(500) DEFAULT NULL COMMENT '反馈内容',
  `person` bigint(20) DEFAULT NULL COMMENT '谁反馈的',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='意见反馈';

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '2', '1', '', '你在逗我？', '25', '25', '2017-06-24 11:34:40', '25', '2017-06-24 11:34:40');
INSERT INTO `feedback` VALUES ('2', '2', '0', '', '爱谁谁AS 按时aS', '25', '25', '2017-06-23 20:35:15', '25', '2017-06-23 20:35:15');
INSERT INTO `feedback` VALUES ('3', '2', '0', '0e44a325c3afb7d9cd3f2f987fe42d1c,41aada0e20e4a86d44b01b7b0d5408d3', '爱谁谁AS 按时aS', '25', '25', '2017-06-23 20:35:29', '25', '2017-06-23 20:35:29');

-- ----------------------------
-- Table structure for fleett
-- ----------------------------
DROP TABLE IF EXISTS `fleett`;
CREATE TABLE `fleett` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nm` varchar(50) DEFAULT NULL COMMENT '名称',
  `dept` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `captain` bigint(20) DEFAULT NULL COMMENT '队长',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `rmk` varchar(150) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqnm` (`nm`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='车队管理';

-- ----------------------------
-- Records of fleett
-- ----------------------------
INSERT INTO `fleett` VALUES ('8', '跑跑卡丁车队', '19', '1', '1', 'aaaaa', '1', '2017-06-19 10:32:41', '1', '2017-06-19 20:51:19');
INSERT INTO `fleett` VALUES ('9', '屌丝车队', '16', '25', '1', '咳咳', '1', '2017-06-27 16:53:44', '1', '2017-06-29 11:19:53');

-- ----------------------------
-- Table structure for gas
-- ----------------------------
DROP TABLE IF EXISTS `gas`;
CREATE TABLE `gas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cno` bigint(20) DEFAULT NULL COMMENT '车牌号(关联到车辆表)',
  `gastm` date DEFAULT NULL COMMENT '加油日期',
  `person` bigint(20) DEFAULT NULL COMMENT '办理人员',
  `price` decimal(24,4) DEFAULT NULL COMMENT '加油单价',
  `unit` bigint(20) DEFAULT NULL COMMENT '加油单位(升..)',
  `type` bigint(20) DEFAULT NULL COMMENT '加油类型',
  `num` float DEFAULT NULL COMMENT '数量',
  `cost` decimal(24,4) DEFAULT NULL COMMENT '加油费用',
  `addr` varchar(50) DEFAULT NULL COMMENT '加油地点',
  `bmile` int(11) DEFAULT NULL COMMENT '起里程数',
  `emile` int(11) DEFAULT NULL COMMENT '止里程数',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='加油记录';

-- ----------------------------
-- Records of gas
-- ----------------------------
INSERT INTO `gas` VALUES ('7', '31', '2017-06-15', '22', '5.8000', '155', '163', '35', '203.0000', '深圳市宝安区西乡兴业路', '7000', '7600', '1', '1', '2017-06-14 23:30:14', '1', '2017-06-14 23:30:14');
INSERT INTO `gas` VALUES ('8', '32', '2017-06-15', '23', '6.0000', '155', '163', '33.3', '199.8000', '深圳市宝安区107国道边', '8000', '8600', '1', '1', '2017-06-14 23:34:30', '1', '2017-06-14 23:34:30');
INSERT INTO `gas` VALUES ('9', '4', '2017-06-27', '25', '25.0000', '155', '165', '3', '75.0000', 'xx', '11', '22', '1', '1', '2017-06-27 17:29:55', '1', '2017-06-27 17:30:15');

-- ----------------------------
-- Table structure for griddes
-- ----------------------------
DROP TABLE IF EXISTS `griddes`;
CREATE TABLE `griddes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fid` bigint(20) NOT NULL COMMENT '网格id',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `lon` varchar(20) NOT NULL DEFAULT '' COMMENT '经度',
  `lat` varchar(20) NOT NULL DEFAULT '' COMMENT '维度',
  `crtid` bigint(20) NOT NULL COMMENT '创建人ID',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL COMMENT '修改人ID',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='网格管理详情';

-- ----------------------------
-- Records of griddes
-- ----------------------------
INSERT INTO `griddes` VALUES ('20', '3', '1', '115.670328', '38.875928', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('21', '3', '2', '115.831003', '38.708951', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('22', '3', '3', '116.045237', '38.592048', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('23', '3', '4', '116.27595', '38.639261', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('24', '3', '5', '116.492929', '39.17465', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('25', '3', '6', '115.81727', '39.1459', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('26', '3', '7', '115.984812', '38.911201', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `griddes` VALUES ('27', '2', '1', '116.425809', '39.841891', '1', '2017-06-26 19:40:46', '1', '2017-06-26 19:40:46');
INSERT INTO `griddes` VALUES ('28', '2', '2', '116.346673', '39.847558', '1', '2017-06-26 19:40:46', '1', '2017-06-26 19:40:46');
INSERT INTO `griddes` VALUES ('29', '2', '3', '116.36487', '39.896568', '1', '2017-06-26 19:40:46', '1', '2017-06-26 19:40:46');
INSERT INTO `griddes` VALUES ('30', '2', '4', '116.412248', '39.913687', '1', '2017-06-26 19:40:46', '1', '2017-06-26 19:40:46');
INSERT INTO `griddes` VALUES ('31', '2', '5', '116.458254', '39.882343', '1', '2017-06-26 19:40:46', '1', '2017-06-26 19:40:46');
INSERT INTO `griddes` VALUES ('32', '4', '1', '114.006351', '22.548913', '1', '2017-06-26 22:00:20', '1', '2017-06-26 22:00:20');
INSERT INTO `griddes` VALUES ('33', '4', '2', '113.998884', '22.546218', '1', '2017-06-26 22:00:20', '1', '2017-06-26 22:00:20');
INSERT INTO `griddes` VALUES ('34', '4', '3', '114.018196', '22.540312', '1', '2017-06-26 22:00:20', '1', '2017-06-26 22:00:20');
INSERT INTO `griddes` VALUES ('35', '4', '4', '114.014205', '22.549785', '1', '2017-06-26 22:00:20', '1', '2017-06-26 22:00:20');

-- ----------------------------
-- Table structure for gridmng
-- ----------------------------
DROP TABLE IF EXISTS `gridmng`;
CREATE TABLE `gridmng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '网格ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '网格名称',
  `uid` varchar(1000) NOT NULL DEFAULT '' COMMENT '用户id',
  `dept` bigint(20) NOT NULL COMMENT '所属项目部',
  `type` char(2) NOT NULL DEFAULT '' COMMENT '网格业务类型',
  `crtid` bigint(20) NOT NULL COMMENT '创建人ID',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL COMMENT '修改人ID',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='网格管理';

-- ----------------------------
-- Records of gridmng
-- ----------------------------
INSERT INTO `gridmng` VALUES ('2', '测试1', '8,13,9,22,1,15,24,25', '1', '0', '1', '2017-06-26 19:40:46', '1', '2017-06-26 19:40:46');
INSERT INTO `gridmng` VALUES ('3', '测试2', '8,13,9,22,1,15,24,25', '1', '0', '1', '2017-06-26 19:21:44', '1', '2017-06-26 19:21:44');
INSERT INTO `gridmng` VALUES ('4', '测试3', '8,13,9,22,1,15,24,25', '11', '0', '1', '2017-06-26 22:00:20', '1', '2017-06-26 22:00:20');

-- ----------------------------
-- Table structure for illega
-- ----------------------------
DROP TABLE IF EXISTS `illega`;
CREATE TABLE `illega` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cno` bigint(20) DEFAULT NULL COMMENT '车牌号(关联到车辆表)',
  `illtm` date DEFAULT NULL COMMENT '违章日期',
  `cause` bigint(20) DEFAULT NULL COMMENT '违章原因',
  `person` bigint(20) DEFAULT NULL COMMENT '违章人员',
  `amount` decimal(24,4) DEFAULT NULL COMMENT '处罚金额',
  `score` tinyint(4) DEFAULT NULL COMMENT '扣分',
  `addr` varchar(50) DEFAULT NULL COMMENT '违章地点',
  `unit` varchar(50) DEFAULT NULL COMMENT '处罚单位',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='违章管理';

-- ----------------------------
-- Records of illega
-- ----------------------------
INSERT INTO `illega` VALUES ('1', '21', '2017-04-02', '170', '3', '1000.0000', '6', '深圳市宝安区清湖路223号', '深圳市交警', '1', '1', '2017-04-26 15:14:00', '1', '2017-05-03 15:44:00');
INSERT INTO `illega` VALUES ('2', '21', '2017-04-02', '170', '3', '1000.0000', '12', '深圳市宝安区清湖路223号', '深圳市交警', '1', '1', '2017-05-03 15:43:06', '1', '2017-05-03 15:43:06');
INSERT INTO `illega` VALUES ('3', '4', '2017-06-27', '170', '15', '50.0000', '2', 'yy', 'xx', '1', '1', '2017-06-27 17:33:16', '1', '2017-06-27 17:33:32');

-- ----------------------------
-- Table structure for insdetail
-- ----------------------------
DROP TABLE IF EXISTS `insdetail`;
CREATE TABLE `insdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `insid` bigint(20) DEFAULT NULL COMMENT '保险id',
  `itemtype` varchar(16) DEFAULT NULL COMMENT '投保项类型',
  `itemcode` varchar(12) DEFAULT NULL COMMENT '投保项代码',
  `money` decimal(24,4) DEFAULT NULL COMMENT '金额',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='投保明细';

-- ----------------------------
-- Records of insdetail
-- ----------------------------
INSERT INTO `insdetail` VALUES ('49', '17', 'finsure', '0', '1000.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('50', '17', 'finsure', '1', '500.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('51', '17', 'binsure', '0', '1000.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('52', '17', 'binsure', '1', '500.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('53', '17', 'binsure', '2', '500.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('54', '17', 'binsure', '3', '200.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('55', '17', 'binsure', '4', '1000.0000', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insdetail` VALUES ('56', '18', 'finsure', '0', '1000.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('57', '18', 'finsure', '1', '500.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('58', '18', 'binsure', '0', '1000.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('59', '18', 'binsure', '1', '500.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('60', '18', 'binsure', '2', '500.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('61', '18', 'binsure', '3', '200.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('62', '18', 'binsure', '4', '1000.0000', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insdetail` VALUES ('67', '19', 'finsure', '0', '1.0000', '1', '1', '2017-06-27 17:27:16', '1', '2017-06-27 17:27:16');
INSERT INTO `insdetail` VALUES ('68', '19', 'finsure', '1', '2.0000', '1', '1', '2017-06-27 17:27:16', '1', '2017-06-27 17:27:16');
INSERT INTO `insdetail` VALUES ('69', '19', 'binsure', '0', '3.0000', '1', '1', '2017-06-27 17:27:16', '1', '2017-06-27 17:27:16');
INSERT INTO `insdetail` VALUES ('70', '19', 'binsure', '1', '5.0000', '1', '1', '2017-06-27 17:27:16', '1', '2017-06-27 17:27:16');
INSERT INTO `insdetail` VALUES ('71', '19', 'binsure', '2', '5.0000', '1', '1', '2017-06-27 17:27:16', '1', '2017-06-27 17:27:16');

-- ----------------------------
-- Table structure for insure
-- ----------------------------
DROP TABLE IF EXISTS `insure`;
CREATE TABLE `insure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cno` bigint(20) DEFAULT NULL COMMENT '车牌号(关联到车辆表)',
  `insno` varchar(75) DEFAULT NULL COMMENT '保单号码',
  `insuretm` date DEFAULT NULL COMMENT '投保日期',
  `ninstm` date DEFAULT NULL COMMENT '到期日期',
  `person` bigint(20) DEFAULT NULL COMMENT '办理人员',
  `cost` decimal(24,4) DEFAULT NULL COMMENT '办理费用',
  `addr` varchar(50) DEFAULT NULL COMMENT '投保地点',
  `unit` varchar(50) DEFAULT NULL COMMENT '投保单位',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='保险管理';

-- ----------------------------
-- Records of insure
-- ----------------------------
INSERT INTO `insure` VALUES ('17', '31', '595465415645456', '2017-06-15', '2018-06-14', '22', '4700.0000', '深圳市南山深南大道', '太平洋保险', '1', '1', '2017-06-14 20:29:08', '1', '2017-06-14 20:29:08');
INSERT INTO `insure` VALUES ('18', '32', '2654641121213451', '2017-06-15', '2018-06-15', '23', '4700.0000', '深圳市南山区深南大道', '太平洋保险', '1', '1', '2017-06-14 20:32:07', '1', '2017-06-14 20:32:07');
INSERT INTO `insure` VALUES ('19', '4', 'bd001', '2017-06-27', '2017-06-28', '25', '16.0000', 'yyy', 'xxx', '1', '1', '2017-06-27 17:26:53', '1', '2017-06-27 17:27:16');

-- ----------------------------
-- Table structure for jobwarning
-- ----------------------------
DROP TABLE IF EXISTS `jobwarning`;
CREATE TABLE `jobwarning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `project` bigint(20) DEFAULT NULL COMMENT '项目部',
  `business` bigint(20) DEFAULT NULL COMMENT '业务板块',
  `btype` bigint(20) DEFAULT NULL COMMENT '业务类型',
  `wtype` bigint(20) DEFAULT NULL COMMENT '预警类型',
  `flag` char(1) DEFAULT NULL COMMENT '是否推送本人',
  `intv` bigint(20) DEFAULT NULL COMMENT '时间设置',
  `receive` bigint(20) DEFAULT NULL COMMENT '接收人',
  `crtid` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='jobwarning工作预警';

-- ----------------------------
-- Records of jobwarning
-- ----------------------------

-- ----------------------------
-- Table structure for maillog
-- ----------------------------
DROP TABLE IF EXISTS `maillog`;
CREATE TABLE `maillog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `receiver` varchar(15) NOT NULL COMMENT '接收人',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '发送类型:1系统2人工',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '发送状态：1:成功0:失败',
  `log` varchar(200) NOT NULL COMMENT '发送日志',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='邮件记录';

-- ----------------------------
-- Records of maillog
-- ----------------------------
INSERT INTO `maillog` VALUES ('1', '1', '2', '您好，\n测试邮件发送', '1', 'true', '1', '2017-04-12 10:27:56', '1', '2017-04-12 10:27:56');
INSERT INTO `maillog` VALUES ('2', '3', '2', '您好，\n测试邮件发送', '1', 'true', '1', '2017-04-12 10:27:56', '1', '2017-04-12 10:27:56');
INSERT INTO `maillog` VALUES ('3', '1', '2', '您好，\n请及时参加智慧城市3.0\n地点为会议室\n请及时参加', '1', 'true', '1', '2017-04-12 10:46:10', '1', '2017-04-12 10:46:10');
INSERT INTO `maillog` VALUES ('4', '2', '2', '您好，\n请及时参加智慧城市3.0\n地点为会议室\n请及时参加', '0', '该用户没有绑定邮箱，不能发送邮件。', '1', '2017-04-12 10:46:10', '1', '2017-04-12 10:46:10');
INSERT INTO `maillog` VALUES ('5', '3', '2', '您好，\n请及时参加智慧城市3.0\n地点为会议室\n请及时参加', '1', 'true', '1', '2017-04-12 10:46:10', '1', '2017-04-12 10:46:10');
INSERT INTO `maillog` VALUES ('6', '3', '2', '您好，\n请及时参加11111111111\n地点为2222222222\n3333333333', '1', 'true', '1', '2017-04-12 11:16:36', '1', '2017-04-12 11:16:36');
INSERT INTO `maillog` VALUES ('7', '3', '2', '您好，\n858585856', '1', 'true', '1', '2017-04-12 11:18:41', '1', '2017-04-12 11:18:41');
INSERT INTO `maillog` VALUES ('8', '2', '2', '您好，\n请及时参加afdadfadf\n地点为adfadf\nadfadfadf', '1', 'true', '1', '2017-04-12 17:34:09', '1', '2017-04-12 17:34:09');
INSERT INTO `maillog` VALUES ('9', '1', '2', '您好，\n请及时参加afdadfadf\n地点为adfadf\nadfadfadf', '1', 'true', '1', '2017-04-12 17:34:11', '1', '2017-04-12 17:34:11');
INSERT INTO `maillog` VALUES ('10', '3', '2', '您好，\n请及时参加afdadfadf\n地点为adfadf\nadfadfadf', '1', 'true', '1', '2017-04-12 17:34:11', '1', '2017-04-12 17:34:11');
INSERT INTO `maillog` VALUES ('11', '1', '2', '您好，请及时参加地点为', '1', 'true', '1', '2017-05-04 17:08:52', '1', '2017-05-04 17:08:52');
INSERT INTO `maillog` VALUES ('12', '1', '2', '您好，\n', '1', 'true', '1', '2017-05-05 09:34:06', '1', '2017-05-05 09:34:06');

-- ----------------------------
-- Table structure for mailtemplate
-- ----------------------------
DROP TABLE IF EXISTS `mailtemplate`;
CREATE TABLE `mailtemplate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nm` varchar(100) NOT NULL COMMENT '名称',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效0:无效',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='邮件模板';

-- ----------------------------
-- Records of mailtemplate
-- ----------------------------
INSERT INTO `mailtemplate` VALUES ('1', ' 会议通知', '您好，\n请及时参加{theme}\n地点为{addr}\n{remark}', '1', '1', '2017-04-12 09:48:08', '1', '2017-04-12 09:48:08');
INSERT INTO `mailtemplate` VALUES ('2', '测试数据2', '您好，\n{remark}', '1', '1', '2017-04-12 09:48:44', '1', '2017-04-12 09:48:44');
INSERT INTO `mailtemplate` VALUES ('3', '1', '您好，请及时参加{theme}地点为{addr}{remark}', '1', '1', '2017-05-04 17:08:28', '1', '2017-05-04 17:08:28');

-- ----------------------------
-- Table structure for maintain
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cno` bigint(20) DEFAULT NULL COMMENT '车牌号(关联到车辆表)',
  `mtm` date DEFAULT NULL COMMENT '保养日期',
  `ntm` date DEFAULT NULL COMMENT '预计下次保养',
  `mile` int(11) DEFAULT NULL COMMENT '保养里程',
  `person` bigint(20) DEFAULT NULL COMMENT '保养人员',
  `cost` decimal(24,4) DEFAULT NULL COMMENT '保养费用',
  `addr` varchar(50) DEFAULT NULL COMMENT '保养地点',
  `store` varchar(50) DEFAULT NULL COMMENT '保养门店',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='保养管理';

-- ----------------------------
-- Records of maintain
-- ----------------------------
INSERT INTO `maintain` VALUES ('2', '26', '2017-04-01', '2017-04-02', '20', '2', '123444.0000', '一号店地点', '一号店', '1', '1', '2017-04-26 17:03:47', '1', '2017-05-09 19:00:17');
INSERT INTO `maintain` VALUES ('4', '4', '2017-06-27', '2017-06-29', '52', '25', '16.0000', 'yy', 'xx', '1', '1', '2017-06-27 17:48:34', '1', '2017-06-27 17:52:51');
INSERT INTO `maintain` VALUES ('5', '4', '2017-06-27', '2017-06-28', '123', '25', '10.0000', 'yy', 'xx', '1', '1', '2017-06-27 17:58:34', '1', '2017-06-27 18:07:30');

-- ----------------------------
-- Table structure for monitordev
-- ----------------------------
DROP TABLE IF EXISTS `monitordev`;
CREATE TABLE `monitordev` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `devfrim` bigint(20) DEFAULT NULL COMMENT '设备厂商',
  `devmodel` bigint(20) DEFAULT NULL COMMENT '设备型号',
  `devid` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `addr` varchar(150) DEFAULT NULL COMMENT '监控地点',
  `lat` varchar(50) DEFAULT NULL COMMENT '纬度',
  `lng` varchar(150) DEFAULT NULL COMMENT '经度',
  `pic` varchar(200) DEFAULT NULL COMMENT '现场照片',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='监控设备';

-- ----------------------------
-- Records of monitordev
-- ----------------------------
INSERT INTO `monitordev` VALUES ('2', '210', '212', 'bbbbbb', '北京市东城区东华门街道中山公园', '39.909575', '116.396394', '524546bcb6271cac18633ff747d36319', '1', '2017-05-20 11:50:16', '1', '2017-05-20 15:59:43');
INSERT INTO `monitordev` VALUES ('3', '210', '212', 'aaaa', '北京市东城区东华门街道天安门', '39.908814', '116.3974', '1a772a33759774fe4d71ce6828be7d6e', '1', '2017-05-20 15:50:25', '1', '2017-05-20 15:59:05');
INSERT INTO `monitordev` VALUES ('4', '211', '212', '2542', '北京市西城区广安门内街道宣武门西大街4号楼宣武门外西里', '39.89907', '116.370621', '2ba9835ba425a185339dfcc383f27f6c', '1', '2017-05-20 18:00:23', '1', '2017-05-20 18:00:23');
INSERT INTO `monitordev` VALUES ('5', '211', '213', 'FD98556', '广东省深圳市宝安区西乡街道西乡(地铁站)', '22.575149', '113.863048', '9b64b79eb6243f9556250edde4bd0b45', '1', '2017-06-08 18:17:26', '1', '2017-06-08 18:17:26');
INSERT INTO `monitordev` VALUES ('6', '211', '213', 'A001', '北京市东城区东华门街道正阳门天安门广场', '39.900536', '116.39785', '63ae87d22b313a144327979c99a05d9d', '1', '2017-06-21 20:01:51', '1', '2017-06-21 20:01:51');
INSERT INTO `monitordev` VALUES ('7', '211', '213', 'BBBBB', '广东省深圳市宝安区西乡街道宝源二区(兴业路)友情基地', '22.56673', '113.858593', 'a8b559c772d053961176a353272f9714', '1', '2017-06-21 20:37:55', '1', '2017-06-21 20:37:55');

-- ----------------------------
-- Table structure for msgconfig
-- ----------------------------
DROP TABLE IF EXISTS `msgconfig`;
CREATE TABLE `msgconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dup` int(11) NOT NULL DEFAULT '100' COMMENT '每天发送上限',
  `pup` int(11) NOT NULL DEFAULT '100' COMMENT '每人发送上限',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '类型：1:短信 2:消息 3:邮件',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqname` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='信息基础设置';

-- ----------------------------
-- Records of msgconfig
-- ----------------------------
INSERT INTO `msgconfig` VALUES ('1', '200', '20', '1', '1', '2017-03-24 14:27:03', '1', '2017-07-01 14:57:34');
INSERT INTO `msgconfig` VALUES ('4', '10000', '10000', '2', '1', '2017-03-24 15:21:01', '1', '2017-03-24 15:21:01');
INSERT INTO `msgconfig` VALUES ('5', '1000', '1000', '3', '1', '2017-04-13 09:43:37', '1', '2017-04-13 09:43:37');

-- ----------------------------
-- Table structure for msglog
-- ----------------------------
DROP TABLE IF EXISTS `msglog`;
CREATE TABLE `msglog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `receiver` varchar(15) NOT NULL COMMENT '接收人',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '发送类型:1系统2人工',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `tid` varchar(100) DEFAULT NULL COMMENT '第三方id',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '发送状态：1:成功0:失败',
  `log` varchar(200) NOT NULL COMMENT '发送日志',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='消息记录';

-- ----------------------------
-- Records of msglog
-- ----------------------------
INSERT INTO `msglog` VALUES ('1', '1', '2', 'abc\n工单情况：def\n申请日期：ghi\njkl', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '1', 'ok', '1', '2017-04-11 17:12:54', '1', '2017-04-11 17:12:54');
INSERT INTO `msglog` VALUES ('2', '2', '2', 'abc\n工单情况：def\n申请日期：ghi\njkl', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '0', '该用户没有绑定微信，不能进行消息发送。', '1', '2017-04-11 17:12:54', '1', '2017-04-11 17:12:54');
INSERT INTO `msglog` VALUES ('3', '25', '2', 'abc\n工单情况：def\n申请日期：ghi\njkl', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '1', 'ok', '1', '2017-06-28 17:12:55', '1', '2017-06-29 17:12:55');
INSERT INTO `msglog` VALUES ('4', '1', '2', '9\n\n会议主题：8\n会议时间：7\n会议地点：6\n5', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-04-12 10:26:41', '1', '2017-04-12 10:26:41');
INSERT INTO `msglog` VALUES ('5', '3', '2', '9\n\n会议主题：8\n会议时间：7\n会议地点：6\n5', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-04-12 10:26:41', '1', '2017-04-12 10:26:41');
INSERT INTO `msglog` VALUES ('6', '25', '2', '9\n\n会议主题：8\n会议时间：7\n会议地点：6\n5', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-04-12 10:28:26', '1', '2017-04-12 10:28:26');
INSERT INTO `msglog` VALUES ('7', '1', '2', '9\n\n会议主题：8\n会议时间：7\n会议地点：6\n5', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-04-12 10:28:26', '1', '2017-04-12 10:28:26');
INSERT INTO `msglog` VALUES ('8', '3', '2', '9\n\n会议主题：8\n会议时间：7\n会议地点：6\n5', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-04-12 10:28:26', '1', '2017-04-12 10:28:26');
INSERT INTO `msglog` VALUES ('9', '3', '2', '1\n工单情况：2\n申请日期：3\n4', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '1', 'ok', '1', '2017-04-12 10:46:51', '1', '2017-04-12 10:46:51');
INSERT INTO `msglog` VALUES ('10', '1', '2', '777\n工单情况：888\n申请日期：999\n111', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '1', 'ok', '1', '2017-04-12 10:48:35', '1', '2017-04-12 10:48:35');
INSERT INTO `msglog` VALUES ('11', '3', '2', '777\n工单情况：888\n申请日期：999\n111', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '1', 'ok', '1', '2017-04-12 10:48:35', '1', '2017-04-12 10:48:35');
INSERT INTO `msglog` VALUES ('12', '1', '2', 'adf\n工单情况：dfg\n申请日期：fgh\nghj', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '0', '', '1', '2017-04-12 16:05:09', '1', '2017-04-12 16:05:09');
INSERT INTO `msglog` VALUES ('13', '3', '2', 'adf\n工单情况：dfg\n申请日期：fgh\nghj', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo', '0', '', '1', '2017-04-12 16:05:10', '1', '2017-04-12 16:05:10');
INSERT INTO `msglog` VALUES ('14', '6', '2', 'a\n\n会议主题：b\n会议时间：c\n会议地点：d\ne', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '该用户没有绑定微信，不能进行消息发送。', '1', '2017-06-12 16:08:23', '1', '2017-06-12 16:08:23');
INSERT INTO `msglog` VALUES ('15', '2', '2', 'a\n\n会议主题：b\n会议时间：c\n会议地点：d\ne', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-06-12 16:08:24', '1', '2017-06-12 16:08:24');
INSERT INTO `msglog` VALUES ('16', '3', '2', 'a\n\n会议主题：b\n会议时间：c\n会议地点：d\ne', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-06-12 16:08:24', '1', '2017-06-12 16:08:24');
INSERT INTO `msglog` VALUES ('17', '1', '2', 'a\n\n会议主题：b\n会议时间：c\n会议地点：d\ne', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-06-12 16:08:24', '1', '2017-06-12 16:08:24');
INSERT INTO `msglog` VALUES ('18', '4', '2', 'a\n\n会议主题：b\n会议时间：c\n会议地点：d\ne', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '该用户没有绑定微信，不能进行消息发送。', '1', '2017-06-12 16:08:24', '1', '2017-06-12 16:08:24');
INSERT INTO `msglog` VALUES ('19', '1', '2', '1\n\n会议主题：2\n会议时间：3\n会议地点：4\n5', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU', '0', '', '1', '2017-06-24 10:43:02', '1', '2017-06-24 10:43:02');

-- ----------------------------
-- Table structure for msgtemplate
-- ----------------------------
DROP TABLE IF EXISTS `msgtemplate`;
CREATE TABLE `msgtemplate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nm` varchar(100) NOT NULL COMMENT '名称',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效0:无效',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  `tid` varchar(100) DEFAULT NULL COMMENT '第三方id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='消息模板';

-- ----------------------------
-- Records of msgtemplate
-- ----------------------------
INSERT INTO `msgtemplate` VALUES ('1', '会议提醒', '{{first.DATA}}\n\n会议主题：{{Topic.DATA}}\n会议时间：{{Time.DATA}}\n会议地点：{{Address.DATA}}\n{{remark.DATA}}', '1', '1', '2017-04-07 14:11:47', '1', '2017-04-07 14:11:47', 'HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU');
INSERT INTO `msgtemplate` VALUES ('4', '工单提醒通知', '{{first.DATA}}\n工单情况：{{keynote1.DATA}}\n申请日期：{{keynote2.DATA}}\n{{remark.DATA}}', '1', '1', '2017-04-11 11:45:58', '1', '2017-04-11 11:45:58', '4AXKnr1VeyUyr4LQ276tOH6_qLNhTsGcC-b-MdnLSFo');
INSERT INTO `msgtemplate` VALUES ('5', 'hfh fghgf h', 'hfg hfgf hgf ', '1', '1', '2017-06-09 10:40:25', '1', '2017-06-09 10:40:25', null);

-- ----------------------------
-- Table structure for mtdetail
-- ----------------------------
DROP TABLE IF EXISTS `mtdetail`;
CREATE TABLE `mtdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `mtid` bigint(20) DEFAULT NULL COMMENT '保养id',
  `itemtype` varchar(16) DEFAULT NULL COMMENT '保养项类型',
  `itemcode` varchar(12) DEFAULT NULL COMMENT '保养项代码',
  `money` decimal(24,4) DEFAULT NULL COMMENT '金额',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='保养明细';

-- ----------------------------
-- Records of mtdetail
-- ----------------------------
INSERT INTO `mtdetail` VALUES ('21', '3', 'smaintain', '0', '500.0000', '1', '1', '2017-05-03 15:50:28', '1', '2017-05-03 15:50:28');
INSERT INTO `mtdetail` VALUES ('22', '2', 'bmaintain', '0', '111.0000', '1', '1', '2017-05-09 19:00:17', '1', '2017-05-09 19:00:17');
INSERT INTO `mtdetail` VALUES ('23', '2', 'bmaintain', '1', '1111.0000', '1', '1', '2017-05-09 19:00:17', '1', '2017-05-09 19:00:17');
INSERT INTO `mtdetail` VALUES ('24', '2', 'bmaintain', '2', '11111.0000', '1', '1', '2017-05-09 19:00:17', '1', '2017-05-09 19:00:17');
INSERT INTO `mtdetail` VALUES ('25', '2', 'bmaintain', '3', '111111.0000', '1', '1', '2017-05-09 19:00:17', '1', '2017-05-09 19:00:17');
INSERT INTO `mtdetail` VALUES ('39', '4', 'smaintain', '0', '1.0000', '1', '1', '2017-06-27 17:52:51', '1', '2017-06-27 17:52:51');
INSERT INTO `mtdetail` VALUES ('40', '4', 'smaintain', '1', '2.0000', '1', '1', '2017-06-27 17:52:51', '1', '2017-06-27 17:52:51');
INSERT INTO `mtdetail` VALUES ('41', '4', 'bmaintain', '0', '3.0000', '1', '1', '2017-06-27 17:52:51', '1', '2017-06-27 17:52:51');
INSERT INTO `mtdetail` VALUES ('42', '4', 'bmaintain', '1', '5.0000', '1', '1', '2017-06-27 17:52:51', '1', '2017-06-27 17:52:51');
INSERT INTO `mtdetail` VALUES ('43', '4', 'bmaintain', '2', '5.0000', '1', '1', '2017-06-27 17:52:51', '1', '2017-06-27 17:52:51');
INSERT INTO `mtdetail` VALUES ('51', '5', 'smaintain', '0', '1.0000', '1', '1', '2017-06-27 18:07:30', '1', '2017-06-27 18:07:30');
INSERT INTO `mtdetail` VALUES ('52', '5', 'smaintain', '1', '2.0000', '1', '1', '2017-06-27 18:07:30', '1', '2017-06-27 18:07:30');
INSERT INTO `mtdetail` VALUES ('53', '5', 'bmaintain', '0', '3.0000', '1', '1', '2017-06-27 18:07:30', '1', '2017-06-27 18:07:30');
INSERT INTO `mtdetail` VALUES ('54', '5', 'bmaintain', '1', '4.0000', '1', '1', '2017-06-27 18:07:30', '1', '2017-06-27 18:07:30');

-- ----------------------------
-- Table structure for operationplan
-- ----------------------------
DROP TABLE IF EXISTS `operationplan`;
CREATE TABLE `operationplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `optime` varchar(50) DEFAULT NULL COMMENT '日期',
  `dept` bigint(20) DEFAULT NULL COMMENT '所属项目部',
  `banci` varchar(100) DEFAULT NULL COMMENT '班次',
  `bsment` bigint(20) DEFAULT NULL COMMENT '业务板块',
  `cla` varchar(100) DEFAULT NULL COMMENT '班级',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `crttm` datetime DEFAULT NULL COMMENT '创建日期',
  `updid` bigint(20) DEFAULT NULL COMMENT '修改人',
  `updtm` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`),
  KEY `uniq_odbc` (`optime`,`dept`,`bsment`,`cla`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1 COMMENT='作业计划';

-- ----------------------------
-- Records of operationplan
-- ----------------------------
INSERT INTO `operationplan` VALUES ('32', '2017-07', '16', '64', '0', '25,26', '1', '1', '2017-07-21 10:20:47', '1', '2017-07-21 10:20:47');
INSERT INTO `operationplan` VALUES ('40', '2017-06', '16', '64,65', '0', '25,26', '1', '1', '2017-07-21 11:36:24', '1', '2017-07-21 11:36:24');
INSERT INTO `operationplan` VALUES ('42', '2017-08', '16', '64,65,66', '0', '25,26', '1', '1', '2017-07-24 10:05:10', '1', '2017-07-24 10:05:10');

-- ----------------------------
-- Table structure for operationplandata
-- ----------------------------
DROP TABLE IF EXISTS `operationplandata`;
CREATE TABLE `operationplandata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `oid` bigint(20) DEFAULT NULL COMMENT 'oid',
  `optime` date DEFAULT NULL COMMENT '日期',
  `shift` bigint(20) DEFAULT NULL COMMENT '班次/时段',
  `cla` bigint(20) DEFAULT NULL COMMENT '班级',
  `member` bigint(20) DEFAULT NULL COMMENT '成员',
  `driver` char(1) DEFAULT NULL COMMENT '司机',
  `car` char(2) DEFAULT NULL COMMENT '车辆',
  `status` char(1) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `crttm` datetime DEFAULT NULL COMMENT '创建日期',
  `updid` bigint(20) DEFAULT NULL COMMENT '修改人',
  `updtm` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_oper` (`optime`,`shift`,`cla`,`member`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=latin1 COMMENT='作业计划数据';

-- ----------------------------
-- Records of operationplandata
-- ----------------------------
INSERT INTO `operationplandata` VALUES ('124', '32', '2017-07-01', '64', '25', '9', '1', '1', '1', '1', '2017-07-21 10:20:47', '1', '2017-07-21 10:20:47');
INSERT INTO `operationplandata` VALUES ('144', '40', '2017-06-21', '64', '25', '24', '0', null, '1', '1', '2017-07-21 11:36:24', '1', '2017-07-21 11:36:24');
INSERT INTO `operationplandata` VALUES ('145', '40', '2017-06-21', '64', '26', '22', '0', null, '1', '1', '2017-07-21 11:36:24', '1', '2017-07-21 11:36:24');
INSERT INTO `operationplandata` VALUES ('146', '40', '2017-06-21', '64', '26', '13', '0', null, '1', '1', '2017-07-21 11:36:24', '1', '2017-07-21 11:36:24');
INSERT INTO `operationplandata` VALUES ('148', '32', '2017-07-02', '64', '25', '9', '0', null, '1', '1', '2017-07-21 12:27:50', '1', '2017-07-21 12:27:50');
INSERT INTO `operationplandata` VALUES ('149', '32', '2017-07-21', '64', '25', '9', '0', null, '1', '1', '2017-07-21 12:30:40', '1', '2017-07-21 12:30:40');
INSERT INTO `operationplandata` VALUES ('152', '32', '2017-07-22', '64', '26', '22', '0', null, '1', '1', '2017-07-22 09:53:20', '1', '2017-07-22 09:53:20');
INSERT INTO `operationplandata` VALUES ('153', '32', '2017-07-22', '64', '25', '9', '0', null, '1', '1', '2017-07-22 09:53:20', '1', '2017-07-22 09:53:20');
INSERT INTO `operationplandata` VALUES ('154', '32', '2017-07-22', '64', '26', '13', '0', null, '1', '1', '2017-07-22 09:53:20', '1', '2017-07-22 09:53:20');
INSERT INTO `operationplandata` VALUES ('155', '32', '2017-07-22', '64', '25', '14', '0', null, '1', '1', '2017-07-22 09:53:20', '1', '2017-07-22 09:53:20');
INSERT INTO `operationplandata` VALUES ('156', '32', '2017-07-24', '64', '25', '9', '0', '1', '1', '1', '2017-07-24 10:03:36', '1', '2017-07-24 10:03:36');
INSERT INTO `operationplandata` VALUES ('157', '32', '2017-07-24', '64', '25', '14', '0', '1', '1', '1', '2017-07-24 10:03:36', '1', '2017-07-24 10:03:36');
INSERT INTO `operationplandata` VALUES ('158', '32', '2017-07-24', '64', '25', '24', '0', '1', '1', '1', '2017-07-24 10:03:36', '1', '2017-07-24 10:03:36');
INSERT INTO `operationplandata` VALUES ('162', '42', '2017-08-01', '64', '25', '14', '0', null, '1', '1', '2017-07-24 10:05:51', '1', '2017-07-24 10:05:51');
INSERT INTO `operationplandata` VALUES ('163', '42', '2017-08-01', '65', '26', '13', '1', '0', '1', '1', '2017-07-24 10:05:51', '1', '2017-07-24 10:05:51');
INSERT INTO `operationplandata` VALUES ('164', '42', '2017-08-01', '64', '25', '9', '1', '1', '1', '1', '2017-07-24 10:05:51', '1', '2017-07-24 10:05:51');
INSERT INTO `operationplandata` VALUES ('165', '42', '2017-08-01', '65', '25', '24', '0', '1', '1', '1', '2017-07-24 10:05:51', '1', '2017-07-24 10:05:51');

-- ----------------------------
-- Table structure for party
-- ----------------------------
DROP TABLE IF EXISTS `party`;
CREATE TABLE `party` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nm` varchar(50) NOT NULL COMMENT '名称',
  `contperson` varchar(15) NOT NULL COMMENT '联系人',
  `rmk` varchar(150) DEFAULT NULL COMMENT '备注',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `addr` varchar(150) NOT NULL COMMENT '地址',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqwxid` (`nm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='甲方';

-- ----------------------------
-- Records of party
-- ----------------------------

-- ----------------------------
-- Table structure for personterminal
-- ----------------------------
DROP TABLE IF EXISTS `personterminal`;
CREATE TABLE `personterminal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `devtype` bigint(20) DEFAULT NULL COMMENT '设备类型',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `devno` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `person` bigint(20) DEFAULT NULL COMMENT '使用人',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='人员终端';

-- ----------------------------
-- Records of personterminal
-- ----------------------------
INSERT INTO `personterminal` VALUES ('1', '205', '15626485266', 'A000004F783E255', '1', '1', '2017-05-19 15:58:39', '1', '2017-05-19 16:27:16');
INSERT INTO `personterminal` VALUES ('2', '204', '15626484155', '010000100', '1', '1', '2017-05-20 17:35:18', '1', '2017-05-20 17:35:18');
INSERT INTO `personterminal` VALUES ('4', '205', '13411780155', 'W20156', '4', '1', '2017-06-08 18:07:26', '1', '2017-06-08 18:07:26');
INSERT INTO `personterminal` VALUES ('5', '204', '13411780156', 'WRE69856', '4', '1', '2017-06-08 18:08:09', '1', '2017-06-08 18:08:09');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `lng` varchar(20) NOT NULL COMMENT '经度',
  `lat` varchar(20) NOT NULL COMMENT '纬度',
  `prec` varchar(20) DEFAULT NULL COMMENT '精度',
  `oob` char(1) DEFAULT '0' COMMENT '是否越界',
  `uid` bigint(20) NOT NULL COMMENT '用户',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定位信息';

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '113.858707', '22.56625', '22', '1', '2', '1', '2017-06-27 15:47:36', '1', '2017-06-27 15:47:36');
INSERT INTO `position` VALUES ('2', '113.858757', '22.56645', '22', '1', '1', '1', '2017-06-27 15:47:36', '1', '2017-06-27 15:47:36');

-- ----------------------------
-- Table structure for qltsu
-- ----------------------------
DROP TABLE IF EXISTS `qltsu`;
CREATE TABLE `qltsu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `scoretype` bigint(20) DEFAULT NULL COMMENT '扣分类型',
  `addr` varchar(150) DEFAULT NULL COMMENT '地址',
  `lat` varchar(50) DEFAULT NULL COMMENT '纬度',
  `lng` varchar(150) DEFAULT NULL COMMENT '经度',
  `score` smallint(6) DEFAULT NULL COMMENT '扣分',
  `stime` datetime DEFAULT NULL COMMENT '扣分时间',
  `person` bigint(20) DEFAULT NULL COMMENT '选人',
  `accaddr` varchar(150) DEFAULT NULL COMMENT '精确地址',
  `img` varchar(500) DEFAULT NULL COMMENT '现场照片',
  `expl` varchar(300) DEFAULT NULL COMMENT '扣分说明',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='质量督导';

-- ----------------------------
-- Records of qltsu
-- ----------------------------
INSERT INTO `qltsu` VALUES ('1', '1', '北京市西城区椿树街道铁鸟胡同1号晋江会馆', '39.894065', '116.381264', '4', '2017-07-26 15:14:31', '8', '北京市西城区椿树街道铁鸟胡同1号晋江会馆saaaa', '1252853ff6c90c6767c3748f7e612a90', '扣分说明啊', '0', '1', '2017-07-04 15:17:29', '1', '2017-07-04 15:17:29');
INSERT INTO `qltsu` VALUES ('2', '2', '北京市西城区大栅栏街道欣燕都宾馆(棕树斜街店)', '39.892485', '116.391907', '2', '2017-07-12 15:28:38', '15', '北京市西城区大栅栏街道欣燕都宾馆(棕树斜街店)', '5f5389c41100d5806d38420ebcfefde7', '没有说明', '1', '1', '2017-07-04 15:28:46', '1', '2017-07-04 15:28:46');
INSERT INTO `qltsu` VALUES ('3', '2', 'aaa', 'ddd', 'eee', '1', '2017-07-21 16:13:04', '8', 'aaa', 'ccc', 'bbb', '0', '24', '2017-07-21 16:13:04', '24', '2017-07-21 16:13:04');

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_3', 'DEFAULT', '0 10 0/2 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_4', 'DEFAULT', '0 0 0 1/1 * ? ', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_5', 'DEFAULT', '0 1 0 1/1 * ? ', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('ZhcsScheduler', 'TASK_1', 'DEFAULT', null, 'com.zhcs.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B7870740008746573745461736B74000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307871007E0012740004746573747400047A686373740018E5B8A6E58F82E695B0E79A84E5AE9AE697B6E4BBBBE58AA1737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015AC78A5868787800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('ZhcsScheduler', 'TASK_2', 'DEFAULT', null, 'com.zhcs.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B7870740008746573745461736B74000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C460787371007E0010000000000000000274000574657374327074001BE4B88DE5B8A6E58F82E695B0E79A84E5AE9AE697B6E4BBBBE58AA1737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015AC78A8360787800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('ZhcsScheduler', 'TASK_3', 'DEFAULT', null, 'com.zhcs.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B787074000A53797374656D5461736B74000F3020302F313230202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015B27AF729C787371007E00100000000000000003740018E88EB7E58F96E5BEAEE4BFA16163636573735F746F6B656E70740018E88EB7E58F96E5BEAEE4BFA16163636573735F746F6B656E737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000071007E001271007E00147800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('ZhcsScheduler', 'TASK_4', 'DEFAULT', null, 'com.zhcs.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B787074000A73797374656D5461736B74000E302030203020312F31202A203F207372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015B2881A594787371007E0010000000000000000474000C73796E63557365724C69737470740021E5908CE6ADA5E5BEAEE4BFA1E69C8DE58AA1E58FB7E794A8E688B7E58897E8A1A8737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000071007E001271007E00147800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('ZhcsScheduler', 'TASK_5', 'DEFAULT', null, 'com.zhcs.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B787074000A73797374656D5461736B74000E302031203020312F31202A203F207372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015B5AE8F141787371007E0010000000000000000574001373796E63557365724C697374427941646D696E70740033E5908CE6ADA5E59198E5B7A5E5BEAEE4BFA1E69C8DE58AA1E58FB7E794A8E688B7E58897E8A1A8E588B0E695B0E68DAEE5BA93737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000071007E001271007E00147800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('ZhcsScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('ZhcsScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('ZhcsScheduler', 'abc-PC1495857593164', '1495857626922', '15000');

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1490772600000', '-1', '5', 'PAUSED', 'CRON', '1490771990000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B7870740008746573745461736B74000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307871007E0012740004746573747400047A686373740018E5B8A6E58F82E695B0E79A84E5AE9AE697B6E4BBBBE58AA1737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015AC78A5868787800);
INSERT INTO `QRTZ_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1498212000000', '-1', '5', 'PAUSED', 'CRON', '1498211906000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B7870740008746573745461736B74000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C460787371007E0010000000000000000274000574657374327074001BE4B88DE5B8A6E58F82E695B0E79A84E5AE9AE697B6E4BBBBE58AA1737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015AC78A8360787800);
INSERT INTO `QRTZ_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_3', 'DEFAULT', 'TASK_3', 'DEFAULT', null, '1491019800000', '-1', '5', 'PAUSED', 'CRON', '1491019461000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B787074000A73797374656D5461736B74000E3020313020302F32202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015B27AF7188787371007E0010000000000000000374000E676574416363657373546F6B656E70740018E88EB7E58F96E5BEAEE4BFA16163636573735F746F6B656E737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015B284BDC80787800);
INSERT INTO `QRTZ_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_4', 'DEFAULT', 'TASK_4', 'DEFAULT', null, '1491062400000', '-1', '5', 'PAUSED', 'CRON', '1491033236000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B787074000A73797374656D5461736B74000E302030203020312F31202A203F207372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015B2881A220787371007E0010000000000000000474000C73796E63557365724C69737470740033E5908CE6ADA5E5B882E6B091E5BEAEE4BFA1E69C8DE58AA1E58FB7E794A8E688B7E58897E8A1A8E588B0E695B0E68DAEE5BA93737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015B5AE93E58787800);
INSERT INTO `QRTZ_TRIGGERS` VALUES ('ZhcsScheduler', 'TASK_5', 'DEFAULT', 'TASK_5', 'DEFAULT', null, '1491926460000', '-1', '5', 'PAUSED', 'CRON', '1491878867000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720021636F6D2E7A6863732E656E746974792E5363686564756C654A6F62456E74697479000000000000000102000B4C0003626E6D7400124C6A6176612F6C616E672F537472696E673B4C000763726F6E65787071007E00094C000563727469647400104C6A6176612F6C616E672F4C6F6E673B4C0005637274746D7400104C6A6176612F7574696C2F446174653B4C0002696471007E000A4C00036D6E6D71007E00094C0006706172616D7371007E00094C0003726D6B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B4C0005757064696471007E000A4C0005757064746D71007E000B787074000A73797374656D5461736B74000E302031203020312F31202A203F207372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015B5AE8F038787371007E0010000000000000000574001373796E63557365724C697374427941646D696E70740033E5908CE6ADA5E59198E5B7A5E5BEAEE4BFA1E69C8DE58AA1E58FB7E794A8E688B7E58897E8A1A8E588B0E695B0E68DAEE5BA93737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E00110000000171007E00127371007E001377080000015B5AEA7AC0787800);

-- ----------------------------
-- Table structure for redetail
-- ----------------------------
DROP TABLE IF EXISTS `redetail`;
CREATE TABLE `redetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `reid` bigint(20) DEFAULT NULL COMMENT '维修id',
  `itemtype` varchar(16) DEFAULT NULL COMMENT '维修项类型',
  `itemcode` varchar(12) DEFAULT NULL COMMENT '维修项代码',
  `money` decimal(24,4) DEFAULT NULL COMMENT '金额',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='维修明细';

-- ----------------------------
-- Records of redetail
-- ----------------------------
INSERT INTO `redetail` VALUES ('13', '15', 'crepaire', '0', '3300.0000', '1', '1', '2017-05-03 16:47:02', '1', '2017-05-03 16:47:02');
INSERT INTO `redetail` VALUES ('14', '13', 'crepaire', '0', '3.0000', '1', '1', '2017-05-03 16:52:01', '1', '2017-05-03 16:52:01');
INSERT INTO `redetail` VALUES ('15', '13', 'crepaire', '1', '33.0000', '1', '1', '2017-05-03 16:52:01', '1', '2017-05-03 16:52:01');
INSERT INTO `redetail` VALUES ('16', '13', 'srepaire', '0', '333.0000', '1', '1', '2017-05-03 16:52:01', '1', '2017-05-03 16:52:01');
INSERT INTO `redetail` VALUES ('21', '16', 'crepaire', '0', '1.0000', '1', '1', '2017-06-27 19:23:40', '1', '2017-06-27 19:23:40');
INSERT INTO `redetail` VALUES ('22', '16', 'crepaire', '1', '2.0000', '1', '1', '2017-06-27 19:23:40', '1', '2017-06-27 19:23:40');
INSERT INTO `redetail` VALUES ('23', '16', 'srepaire', '0', '3.0000', '1', '1', '2017-06-27 19:23:40', '1', '2017-06-27 19:23:40');
INSERT INTO `redetail` VALUES ('24', '16', 'srepaire', '1', '4.0000', '1', '1', '2017-06-27 19:23:40', '1', '2017-06-27 19:23:40');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cno` bigint(20) DEFAULT NULL COMMENT '车牌号(关联到车辆表)',
  `rtm` date DEFAULT NULL COMMENT '维修日期',
  `person` bigint(20) DEFAULT NULL COMMENT '维修人员',
  `cost` decimal(24,4) DEFAULT NULL COMMENT '维修费用',
  `addr` varchar(50) DEFAULT NULL COMMENT '维修地点',
  `store` varchar(50) DEFAULT NULL COMMENT '维修门店',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='维修管理';

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('13', '19', '2017-05-01', '2', '369.0000', '深圳市南山嘉进龙汽车城', '凯迪拉克4S店', '1', '1', '2017-04-27 17:28:14', '1', '2017-05-03 16:52:01');
INSERT INTO `repair` VALUES ('15', '21', '2016-05-03', '3', '3300.0000', '深圳市嘉进龙汽车城A区', '丰田4S店', '1', '1', '2017-05-03 16:47:02', '1', '2017-05-03 16:47:02');
INSERT INTO `repair` VALUES ('16', '4', '2017-06-27', '15', '10.0000', 'yy', 'xx', '1', '1', '2017-06-27 19:17:45', '1', '2017-06-27 19:23:40');

-- ----------------------------
-- Table structure for rfidinfo
-- ----------------------------
DROP TABLE IF EXISTS `rfidinfo`;
CREATE TABLE `rfidinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dvcaddr` varchar(50) NOT NULL COMMENT '设备地址',
  `rfidno` bigint(20) NOT NULL COMMENT 'RFID号',
  `swtm` datetime NOT NULL COMMENT '刷卡时间',
  `crtid` bigint(20) NOT NULL DEFAULT '1' COMMENT '创建人',
  `crttm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '1' COMMENT '修改人',
  `updtm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='RFID信息表';

-- ----------------------------
-- Records of rfidinfo
-- ----------------------------
INSERT INTO `rfidinfo` VALUES ('1', '1', '11111', '2017-05-02 22:12:12', '1', '2017-06-20 14:55:26', '1', '2017-06-20 14:55:26');

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bnm` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `mnm` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cronexp` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `rmk` varchar(255) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'zhcs', '0 0/30 * * * ?', '1', '带参数的定时任务', '1', '2016-12-01 23:16:46', '1', '2017-03-13 20:00:17');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '不带参数的定时任务', '1', '2016-12-03 14:55:56', '1', '2017-03-13 20:00:28');
INSERT INTO `schedule_job` VALUES ('3', 'systemTask', 'getAccessToken', null, '0 10 0/2 * * ?', '1', '获取微信access_token', '1', '2017-04-01 12:04:21', '1', '2017-04-01 14:55:12');
INSERT INTO `schedule_job` VALUES ('4', 'systemTask', 'syncUserList', null, '0 0 0 1/1 * ? ', '1', '同步市民微信服务号用户列表到数据库', '1', '2017-04-01 15:53:56', '1', '2017-04-11 10:48:07');
INSERT INTO `schedule_job` VALUES ('5', 'systemTask', 'syncUserListByAdmin', null, '0 1 0 1/1 * ? ', '1', '同步员工微信服务号用户列表到数据库', '1', '2017-04-11 10:47:47', '1', '2017-04-11 10:49:28');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `jobid` bigint(20) NOT NULL COMMENT '任务id',
  `bnm` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `mnm` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idxjobid` (`jobid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('42', '3', 'systemTask', 'getAccessToken', null, '0', null, '799', '1', '2017-05-27 12:12:31', '1', '2017-05-27 12:12:31');
INSERT INTO `schedule_job_log` VALUES ('43', '5', 'systemTask', 'syncUserListByAdmin', null, '0', null, '271', '1', '2017-06-08 18:29:04', '1', '2017-06-08 18:29:04');
INSERT INTO `schedule_job_log` VALUES ('44', '4', 'systemTask', 'syncUserList', null, '0', null, '2275', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:30:33');
INSERT INTO `schedule_job_log` VALUES ('45', '4', 'systemTask', 'syncUserList', null, '0', null, '1808', '1', '2017-06-08 18:31:06', '1', '2017-06-08 18:31:06');
INSERT INTO `schedule_job_log` VALUES ('46', '4', 'systemTask', 'syncUserList', null, '0', null, '1643', '1', '2017-06-08 18:31:28', '1', '2017-06-08 18:31:28');
INSERT INTO `schedule_job_log` VALUES ('47', '5', 'systemTask', 'syncUserListByAdmin', null, '0', null, '2811', '1', '2017-06-08 18:31:30', '1', '2017-06-08 18:31:30');
INSERT INTO `schedule_job_log` VALUES ('48', '1', 'testTask', 'test', 'zhcs', '0', null, '1067', '1', '2017-06-09 16:30:01', '1', '2017-06-09 16:30:01');
INSERT INTO `schedule_job_log` VALUES ('49', '3', 'systemTask', 'getAccessToken', null, '0', null, '787', '1', '2017-06-09 21:23:51', '1', '2017-06-09 21:23:51');
INSERT INTO `schedule_job_log` VALUES ('50', '3', 'systemTask', 'getAccessToken', null, '0', null, '284', '1', '2017-06-10 17:20:11', '1', '2017-06-10 17:20:11');
INSERT INTO `schedule_job_log` VALUES ('51', '3', 'systemTask', 'getAccessToken', null, '0', null, '14', '1', '2017-06-10 17:23:12', '1', '2017-06-10 17:23:12');
INSERT INTO `schedule_job_log` VALUES ('52', '3', 'systemTask', 'getAccessToken', null, '0', null, '110514', '1', '2017-06-10 17:30:28', '1', '2017-06-10 17:30:28');
INSERT INTO `schedule_job_log` VALUES ('53', '3', 'systemTask', 'getAccessToken', null, '0', null, '420010', '1', '2017-06-10 17:38:54', '1', '2017-06-10 17:38:54');
INSERT INTO `schedule_job_log` VALUES ('54', '3', 'systemTask', 'getAccessToken', null, '0', null, '27', '1', '2017-06-12 10:56:33', '1', '2017-06-12 10:56:33');
INSERT INTO `schedule_job_log` VALUES ('55', '3', 'systemTask', 'getAccessToken', null, '0', null, '29', '1', '2017-06-12 11:09:00', '1', '2017-06-12 11:09:00');
INSERT INTO `schedule_job_log` VALUES ('56', '3', 'systemTask', 'getAccessToken', null, '0', null, '744', '1', '2017-06-12 17:56:51', '1', '2017-06-12 17:56:51');
INSERT INTO `schedule_job_log` VALUES ('57', '3', 'systemTask', 'getAccessToken', null, '0', null, '702', '1', '2017-06-16 09:44:15', '1', '2017-06-16 09:44:15');
INSERT INTO `schedule_job_log` VALUES ('58', '3', 'systemTask', 'getAccessToken', null, '0', null, '867', '1', '2017-06-16 10:54:38', '1', '2017-06-16 10:54:38');
INSERT INTO `schedule_job_log` VALUES ('59', '3', 'systemTask', 'getAccessToken', null, '0', null, '744', '1', '2017-06-19 11:01:44', '1', '2017-06-19 11:01:44');
INSERT INTO `schedule_job_log` VALUES ('60', '3', 'systemTask', 'getAccessToken', null, '0', null, '4466', '1', '2017-06-19 19:39:31', '1', '2017-06-19 19:39:31');
INSERT INTO `schedule_job_log` VALUES ('61', '3', 'systemTask', 'getAccessToken', null, '0', null, '85', '1', '2017-06-19 20:10:00', '1', '2017-06-19 20:10:00');
INSERT INTO `schedule_job_log` VALUES ('62', '3', 'systemTask', 'getAccessToken', null, '0', null, '2059', '1', '2017-06-20 09:30:48', '1', '2017-06-20 09:30:48');
INSERT INTO `schedule_job_log` VALUES ('63', '3', 'systemTask', 'getAccessToken', null, '0', null, '85', '1', '2017-06-20 10:10:00', '1', '2017-06-20 10:10:00');
INSERT INTO `schedule_job_log` VALUES ('64', '3', 'systemTask', 'getAccessToken', null, '0', null, '861', '1', '2017-06-20 11:53:16', '1', '2017-06-20 11:53:16');
INSERT INTO `schedule_job_log` VALUES ('65', '3', 'systemTask', 'getAccessToken', null, '0', null, '84', '1', '2017-06-20 12:10:00', '1', '2017-06-20 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('66', '3', 'systemTask', 'getAccessToken', null, '0', null, '81', '1', '2017-06-20 14:10:00', '1', '2017-06-20 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('67', '3', 'systemTask', 'getAccessToken', null, '0', null, '2175', '1', '2017-06-20 15:05:32', '1', '2017-06-20 15:05:32');
INSERT INTO `schedule_job_log` VALUES ('68', '3', 'systemTask', 'getAccessToken', null, '0', null, '83', '1', '2017-06-20 16:10:00', '1', '2017-06-20 16:10:00');
INSERT INTO `schedule_job_log` VALUES ('69', '3', 'systemTask', 'getAccessToken', null, '0', null, '987', '1', '2017-06-20 17:02:38', '1', '2017-06-20 17:02:38');
INSERT INTO `schedule_job_log` VALUES ('70', '3', 'systemTask', 'getAccessToken', null, '0', null, '80', '1', '2017-06-20 18:10:00', '1', '2017-06-20 18:10:00');
INSERT INTO `schedule_job_log` VALUES ('71', '3', 'systemTask', 'getAccessToken', null, '0', null, '2862', '1', '2017-06-20 20:10:03', '1', '2017-06-20 20:10:03');
INSERT INTO `schedule_job_log` VALUES ('72', '3', 'systemTask', 'getAccessToken', null, '0', null, '1207', '1', '2017-06-21 09:14:11', '1', '2017-06-21 09:14:11');
INSERT INTO `schedule_job_log` VALUES ('73', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '17', '1', '2017-06-21 10:10:00', '1', '2017-06-21 10:10:00');
INSERT INTO `schedule_job_log` VALUES ('74', '3', 'systemTask', 'getAccessToken', null, '0', null, '68', '1', '2017-06-21 11:23:29', '1', '2017-06-21 11:23:29');
INSERT INTO `schedule_job_log` VALUES ('75', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '21', '1', '2017-06-21 12:10:00', '1', '2017-06-21 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('76', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '16', '1', '2017-06-21 14:10:00', '1', '2017-06-21 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('77', '3', 'systemTask', 'getAccessToken', null, '0', null, '85', '1', '2017-06-21 16:10:00', '1', '2017-06-21 16:10:00');
INSERT INTO `schedule_job_log` VALUES ('78', '3', 'systemTask', 'getAccessToken', null, '0', null, '3084', '1', '2017-06-21 18:10:04', '1', '2017-06-21 18:10:04');
INSERT INTO `schedule_job_log` VALUES ('79', '3', 'systemTask', 'getAccessToken', null, '0', null, '1199', '1', '2017-06-21 20:10:01', '1', '2017-06-21 20:10:01');
INSERT INTO `schedule_job_log` VALUES ('80', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '12', '1', '2017-06-22 10:10:00', '1', '2017-06-22 10:10:00');
INSERT INTO `schedule_job_log` VALUES ('81', '3', 'systemTask', 'getAccessToken', null, '0', null, '1318', '1', '2017-06-22 10:53:19', '1', '2017-06-22 10:53:19');
INSERT INTO `schedule_job_log` VALUES ('82', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '50', '1', '2017-06-22 12:10:00', '1', '2017-06-22 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('83', '3', 'systemTask', 'getAccessToken', null, '0', null, '82', '1', '2017-06-22 14:10:00', '1', '2017-06-22 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('84', '3', 'systemTask', 'getAccessToken', null, '0', null, '1527', '1', '2017-06-22 14:33:16', '1', '2017-06-22 14:33:16');
INSERT INTO `schedule_job_log` VALUES ('85', '3', 'systemTask', 'getAccessToken', null, '0', null, '243', '1', '2017-06-22 16:10:00', '1', '2017-06-22 16:10:00');
INSERT INTO `schedule_job_log` VALUES ('86', '3', 'systemTask', 'getAccessToken', null, '0', null, '241', '1', '2017-06-22 18:10:00', '1', '2017-06-22 18:10:00');
INSERT INTO `schedule_job_log` VALUES ('87', '3', 'systemTask', 'getAccessToken', null, '0', null, '1831', '1', '2017-06-22 08:10:02', '1', '2017-06-22 08:10:02');
INSERT INTO `schedule_job_log` VALUES ('88', '3', 'systemTask', 'getAccessToken', null, '0', null, '72', '1', '2017-06-22 10:10:00', '1', '2017-06-22 10:10:00');
INSERT INTO `schedule_job_log` VALUES ('89', '3', 'systemTask', 'getAccessToken', null, '0', null, '77', '1', '2017-06-22 12:10:00', '1', '2017-06-22 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('90', '3', 'systemTask', 'getAccessToken', null, '0', null, '76', '1', '2017-06-22 14:10:00', '1', '2017-06-22 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('91', '3', 'systemTask', 'getAccessToken', null, '0', null, '91', '1', '2017-06-22 16:10:00', '1', '2017-06-22 16:10:00');
INSERT INTO `schedule_job_log` VALUES ('92', '3', 'systemTask', 'getAccessToken', null, '0', null, '77', '1', '2017-06-22 18:10:00', '1', '2017-06-22 18:10:00');
INSERT INTO `schedule_job_log` VALUES ('93', '3', 'systemTask', 'getAccessToken', null, '0', null, '3185', '1', '2017-06-23 10:10:03', '1', '2017-06-23 10:10:03');
INSERT INTO `schedule_job_log` VALUES ('94', '3', 'systemTask', 'getAccessToken', null, '0', null, '66', '1', '2017-06-23 10:14:42', '1', '2017-06-23 10:14:42');
INSERT INTO `schedule_job_log` VALUES ('95', '3', 'systemTask', 'getAccessToken', null, '0', null, '84', '1', '2017-06-22 21:10:00', '1', '2017-06-22 21:10:00');
INSERT INTO `schedule_job_log` VALUES ('96', '3', 'systemTask', 'getAccessToken', null, '0', null, '86', '1', '2017-06-22 23:10:00', '1', '2017-06-22 23:10:00');
INSERT INTO `schedule_job_log` VALUES ('97', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '19', '1', '2017-06-23 18:10:00', '1', '2017-06-23 18:10:00');
INSERT INTO `schedule_job_log` VALUES ('98', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '12', '1', '2017-06-23 20:10:00', '1', '2017-06-23 20:10:00');
INSERT INTO `schedule_job_log` VALUES ('99', '3', 'systemTask', 'getAccessToken', null, '0', null, '428', '1', '2017-06-23 07:10:01', '1', '2017-06-23 07:10:01');
INSERT INTO `schedule_job_log` VALUES ('100', '3', 'systemTask', 'getAccessToken', null, '0', null, '75', '1', '2017-06-23 09:10:00', '1', '2017-06-23 09:10:00');
INSERT INTO `schedule_job_log` VALUES ('101', '3', 'systemTask', 'getAccessToken', null, '0', null, '204', '1', '2017-06-23 11:10:00', '1', '2017-06-23 11:10:00');
INSERT INTO `schedule_job_log` VALUES ('102', '3', 'systemTask', 'getAccessToken', null, '0', null, '75', '1', '2017-06-23 13:10:00', '1', '2017-06-23 13:10:00');
INSERT INTO `schedule_job_log` VALUES ('103', '3', 'systemTask', 'getAccessToken', null, '0', null, '70', '1', '2017-06-23 15:10:00', '1', '2017-06-23 15:10:00');
INSERT INTO `schedule_job_log` VALUES ('104', '3', 'systemTask', 'getAccessToken', null, '0', null, '69', '1', '2017-06-23 17:10:00', '1', '2017-06-23 17:10:00');
INSERT INTO `schedule_job_log` VALUES ('105', '3', 'systemTask', 'getAccessToken', null, '0', null, '74', '1', '2017-06-23 19:10:00', '1', '2017-06-23 19:10:00');
INSERT INTO `schedule_job_log` VALUES ('106', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '14', '1', '2017-06-24 12:10:00', '1', '2017-06-24 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('107', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '8', '1', '2017-06-24 14:10:00', '1', '2017-06-24 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('108', '3', 'systemTask', 'getAccessToken', null, '0', null, '1586', '1', '2017-06-24 16:10:02', '1', '2017-06-24 16:10:02');
INSERT INTO `schedule_job_log` VALUES ('109', '3', 'systemTask', 'getAccessToken', null, '0', null, '1258', '1', '2017-06-24 18:10:01', '1', '2017-06-24 18:10:01');
INSERT INTO `schedule_job_log` VALUES ('110', '3', 'systemTask', 'getAccessToken', null, '0', null, '77', '1', '2017-06-24 05:10:00', '1', '2017-06-24 05:10:00');
INSERT INTO `schedule_job_log` VALUES ('111', '3', 'systemTask', 'getAccessToken', null, '0', null, '74', '1', '2017-06-24 07:10:00', '1', '2017-06-24 07:10:00');
INSERT INTO `schedule_job_log` VALUES ('112', '3', 'systemTask', 'getAccessToken', null, '0', null, '73', '1', '2017-06-24 09:10:00', '1', '2017-06-24 09:10:00');
INSERT INTO `schedule_job_log` VALUES ('113', '3', 'systemTask', 'getAccessToken', null, '0', null, '70', '1', '2017-06-24 11:10:00', '1', '2017-06-24 11:10:00');
INSERT INTO `schedule_job_log` VALUES ('114', '3', 'systemTask', 'getAccessToken', null, '0', null, '75', '1', '2017-06-24 13:10:00', '1', '2017-06-24 13:10:00');
INSERT INTO `schedule_job_log` VALUES ('115', '3', 'systemTask', 'getAccessToken', null, '0', null, '70', '1', '2017-06-24 15:10:00', '1', '2017-06-24 15:10:00');
INSERT INTO `schedule_job_log` VALUES ('116', '3', 'systemTask', 'getAccessToken', null, '0', null, '72', '1', '2017-06-24 17:10:00', '1', '2017-06-24 17:10:00');
INSERT INTO `schedule_job_log` VALUES ('117', '3', 'systemTask', 'getAccessToken', null, '0', null, '72', '1', '2017-06-24 19:10:00', '1', '2017-06-24 19:10:00');
INSERT INTO `schedule_job_log` VALUES ('118', '3', 'systemTask', 'getAccessToken', null, '0', null, '80', '1', '2017-06-24 21:10:00', '1', '2017-06-24 21:10:00');
INSERT INTO `schedule_job_log` VALUES ('119', '3', 'systemTask', 'getAccessToken', null, '0', null, '100', '1', '2017-06-24 23:10:00', '1', '2017-06-24 23:10:00');
INSERT INTO `schedule_job_log` VALUES ('120', '3', 'systemTask', 'getAccessToken', null, '0', null, '81', '1', '2017-06-25 01:10:00', '1', '2017-06-25 01:10:00');
INSERT INTO `schedule_job_log` VALUES ('121', '3', 'systemTask', 'getAccessToken', null, '0', null, '70', '1', '2017-06-25 03:10:00', '1', '2017-06-25 03:10:00');
INSERT INTO `schedule_job_log` VALUES ('122', '3', 'systemTask', 'getAccessToken', null, '0', null, '73', '1', '2017-06-25 05:10:00', '1', '2017-06-25 05:10:00');
INSERT INTO `schedule_job_log` VALUES ('123', '3', 'systemTask', 'getAccessToken', null, '0', null, '86', '1', '2017-06-25 07:10:00', '1', '2017-06-25 07:10:00');
INSERT INTO `schedule_job_log` VALUES ('124', '3', 'systemTask', 'getAccessToken', null, '0', null, '86', '1', '2017-06-25 09:10:00', '1', '2017-06-25 09:10:00');
INSERT INTO `schedule_job_log` VALUES ('125', '3', 'systemTask', 'getAccessToken', null, '0', null, '82', '1', '2017-06-25 11:10:00', '1', '2017-06-25 11:10:00');
INSERT INTO `schedule_job_log` VALUES ('126', '3', 'systemTask', 'getAccessToken', null, '0', null, '83', '1', '2017-06-25 13:10:00', '1', '2017-06-25 13:10:00');
INSERT INTO `schedule_job_log` VALUES ('127', '3', 'systemTask', 'getAccessToken', null, '0', null, '86', '1', '2017-06-25 15:10:00', '1', '2017-06-25 15:10:00');
INSERT INTO `schedule_job_log` VALUES ('128', '3', 'systemTask', 'getAccessToken', null, '0', null, '74', '1', '2017-06-25 17:10:00', '1', '2017-06-25 17:10:00');
INSERT INTO `schedule_job_log` VALUES ('129', '3', 'systemTask', 'getAccessToken', null, '0', null, '85', '1', '2017-06-25 19:10:00', '1', '2017-06-25 19:10:00');
INSERT INTO `schedule_job_log` VALUES ('130', '3', 'systemTask', 'getAccessToken', null, '0', null, '1428', '1', '2017-06-26 12:10:02', '1', '2017-06-26 12:10:02');
INSERT INTO `schedule_job_log` VALUES ('131', '3', 'systemTask', 'getAccessToken', null, '0', null, '1306', '1', '2017-06-26 14:10:02', '1', '2017-06-26 14:10:02');
INSERT INTO `schedule_job_log` VALUES ('132', '2', 'testTask', 'test2', null, '0', null, '3', '1', '2017-06-26 14:30:00', '1', '2017-06-26 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('133', '3', 'systemTask', 'getAccessToken', null, '0', null, '1427', '1', '2017-06-26 16:10:02', '1', '2017-06-26 16:10:02');
INSERT INTO `schedule_job_log` VALUES ('134', '3', 'systemTask', 'getAccessToken', null, '0', null, '84', '1', '2017-06-26 03:10:00', '1', '2017-06-26 03:10:00');
INSERT INTO `schedule_job_log` VALUES ('135', '3', 'systemTask', 'getAccessToken', null, '0', null, '86', '1', '2017-06-26 05:10:00', '1', '2017-06-26 05:10:00');
INSERT INTO `schedule_job_log` VALUES ('136', '3', 'systemTask', 'getAccessToken', null, '0', null, '84', '1', '2017-06-26 07:10:00', '1', '2017-06-26 07:10:00');
INSERT INTO `schedule_job_log` VALUES ('137', '3', 'systemTask', 'getAccessToken', null, '0', null, '86', '1', '2017-06-26 09:10:00', '1', '2017-06-26 09:10:00');
INSERT INTO `schedule_job_log` VALUES ('138', '3', 'systemTask', 'getAccessToken', null, '0', null, '78', '1', '2017-06-26 11:10:00', '1', '2017-06-26 11:10:00');
INSERT INTO `schedule_job_log` VALUES ('139', '3', 'systemTask', 'getAccessToken', null, '0', null, '87', '1', '2017-06-26 13:10:00', '1', '2017-06-26 13:10:00');
INSERT INTO `schedule_job_log` VALUES ('140', '3', 'systemTask', 'getAccessToken', null, '0', null, '85', '1', '2017-06-26 15:10:00', '1', '2017-06-26 15:10:00');
INSERT INTO `schedule_job_log` VALUES ('141', '3', 'systemTask', 'getAccessToken', null, '0', null, '87', '1', '2017-06-26 17:10:00', '1', '2017-06-26 17:10:00');
INSERT INTO `schedule_job_log` VALUES ('142', '3', 'systemTask', 'getAccessToken', null, '0', null, '84', '1', '2017-06-26 19:10:00', '1', '2017-06-26 19:10:00');
INSERT INTO `schedule_job_log` VALUES ('143', '3', 'systemTask', 'getAccessToken', null, '0', null, '120', '1', '2017-06-26 21:10:00', '1', '2017-06-26 21:10:00');
INSERT INTO `schedule_job_log` VALUES ('144', '3', 'systemTask', 'getAccessToken', null, '0', null, '91', '1', '2017-06-26 23:10:00', '1', '2017-06-26 23:10:00');
INSERT INTO `schedule_job_log` VALUES ('145', '3', 'systemTask', 'getAccessToken', null, '0', null, '4294', '1', '2017-06-27 15:31:13', '1', '2017-06-27 15:31:13');
INSERT INTO `schedule_job_log` VALUES ('146', '3', 'systemTask', 'getAccessToken', null, '0', null, '4517', '1', '2017-06-27 16:10:05', '1', '2017-06-27 16:10:05');
INSERT INTO `schedule_job_log` VALUES ('147', '3', 'systemTask', 'getAccessToken', null, '0', null, '1594', '1', '2017-06-27 18:10:02', '1', '2017-06-27 18:10:02');
INSERT INTO `schedule_job_log` VALUES ('148', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '14', '1', '2017-06-27 20:10:00', '1', '2017-06-27 20:10:00');
INSERT INTO `schedule_job_log` VALUES ('149', '3', 'systemTask', 'getAccessToken', null, '0', null, '2463', '1', '2017-06-27 20:26:40', '1', '2017-06-27 20:26:40');
INSERT INTO `schedule_job_log` VALUES ('150', '3', 'systemTask', 'getAccessToken', null, '0', null, '1689', '1', '2017-06-27 07:10:02', '1', '2017-06-27 07:10:02');
INSERT INTO `schedule_job_log` VALUES ('151', '3', 'systemTask', 'getAccessToken', null, '0', null, '78', '1', '2017-06-27 09:10:00', '1', '2017-06-27 09:10:00');
INSERT INTO `schedule_job_log` VALUES ('152', '3', 'systemTask', 'getAccessToken', null, '0', null, '568', '1', '2017-06-27 11:10:01', '1', '2017-06-27 11:10:01');
INSERT INTO `schedule_job_log` VALUES ('153', '3', 'systemTask', 'getAccessToken', null, '0', null, '84', '1', '2017-06-27 13:10:00', '1', '2017-06-27 13:10:00');
INSERT INTO `schedule_job_log` VALUES ('154', '3', 'systemTask', 'getAccessToken', null, '0', null, '83', '1', '2017-06-27 15:10:00', '1', '2017-06-27 15:10:00');
INSERT INTO `schedule_job_log` VALUES ('155', '3', 'systemTask', 'getAccessToken', null, '0', null, '85', '1', '2017-06-27 17:10:00', '1', '2017-06-27 17:10:00');
INSERT INTO `schedule_job_log` VALUES ('156', '3', 'systemTask', 'getAccessToken', null, '0', null, '1301', '1', '2017-06-28 10:10:01', '1', '2017-06-28 10:10:01');
INSERT INTO `schedule_job_log` VALUES ('157', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '5249', '1', '2017-06-28 10:36:15', '1', '2017-06-28 10:36:15');
INSERT INTO `schedule_job_log` VALUES ('158', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '12706', '1', '2017-06-28 14:32:53', '1', '2017-06-28 14:32:53');
INSERT INTO `schedule_job_log` VALUES ('159', '3', 'systemTask', 'getAccessToken', null, '0', null, '5036', '1', '2017-06-28 14:33:56', '1', '2017-06-28 14:33:56');
INSERT INTO `schedule_job_log` VALUES ('160', '3', 'systemTask', 'getAccessToken', null, '0', null, '1263', '1', '2017-06-28 15:05:23', '1', '2017-06-28 15:05:23');
INSERT INTO `schedule_job_log` VALUES ('161', '3', 'systemTask', 'getAccessToken', null, '0', null, '70', '1', '2017-06-28 15:06:06', '1', '2017-06-28 15:06:06');
INSERT INTO `schedule_job_log` VALUES ('162', '3', 'systemTask', 'getAccessToken', null, '0', null, '840', '1', '2017-06-28 16:10:02', '1', '2017-06-28 16:10:02');
INSERT INTO `schedule_job_log` VALUES ('163', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '14', '1', '2017-06-28 18:10:00', '1', '2017-06-28 18:10:00');
INSERT INTO `schedule_job_log` VALUES ('164', '3', 'systemTask', 'getAccessToken', null, '0', null, '4998', '1', '2017-06-28 20:10:05', '1', '2017-06-28 20:10:05');
INSERT INTO `schedule_job_log` VALUES ('165', '3', 'systemTask', 'getAccessToken', null, '0', null, '886', '1', '2017-06-29 09:39:45', '1', '2017-06-29 09:39:45');
INSERT INTO `schedule_job_log` VALUES ('166', '3', 'systemTask', 'getAccessToken', null, '0', null, '1559', '1', '2017-06-29 10:10:02', '1', '2017-06-29 10:10:02');
INSERT INTO `schedule_job_log` VALUES ('167', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '16', '1', '2017-06-29 12:10:00', '1', '2017-06-29 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('168', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '11', '1', '2017-06-29 14:10:00', '1', '2017-06-29 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('169', '3', 'systemTask', 'getAccessToken', null, '0', null, '4197', '1', '2017-06-29 16:10:04', '1', '2017-06-29 16:10:04');
INSERT INTO `schedule_job_log` VALUES ('170', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '14', '1', '2017-06-29 18:10:00', '1', '2017-06-29 18:10:00');
INSERT INTO `schedule_job_log` VALUES ('171', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '12', '1', '2017-06-29 20:10:00', '1', '2017-06-29 20:10:00');
INSERT INTO `schedule_job_log` VALUES ('172', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '15', '1', '2017-06-30 10:10:00', '1', '2017-06-30 10:10:00');
INSERT INTO `schedule_job_log` VALUES ('173', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '13', '1', '2017-06-30 12:10:00', '1', '2017-06-30 12:10:00');
INSERT INTO `schedule_job_log` VALUES ('174', '3', 'systemTask', 'getAccessToken', null, '0', null, '1324', '1', '2017-06-30 14:10:01', '1', '2017-06-30 14:10:01');
INSERT INTO `schedule_job_log` VALUES ('175', '3', 'systemTask', 'getAccessToken', null, '0', null, '4449', '1', '2017-06-30 16:10:05', '1', '2017-06-30 16:10:05');
INSERT INTO `schedule_job_log` VALUES ('176', '3', 'systemTask', 'getAccessToken', null, '0', null, '2287', '1', '2017-06-30 18:10:02', '1', '2017-06-30 18:10:02');
INSERT INTO `schedule_job_log` VALUES ('177', '3', 'systemTask', 'getAccessToken', null, '1', 'java.util.concurrent.ExecutionException: com.zhcs.utils.ZHCSException: 执行定时任务失败', '23', '1', '2017-07-01 10:10:00', '1', '2017-07-01 10:10:00');
INSERT INTO `schedule_job_log` VALUES ('178', '3', 'systemTask', 'getAccessToken', null, '0', null, '1018', '1', '2017-07-01 12:10:01', '1', '2017-07-01 12:10:01');
INSERT INTO `schedule_job_log` VALUES ('179', '1', 'testTask', 'test', 'zhcs', '0', null, '1020', '1', '2017-07-01 15:13:17', '1', '2017-07-01 15:13:17');
INSERT INTO `schedule_job_log` VALUES ('180', '3', 'systemTask', 'getAccessToken', null, '0', null, '973', '1', '2017-07-03 15:19:56', '1', '2017-07-03 15:19:56');
INSERT INTO `schedule_job_log` VALUES ('181', '3', 'systemTask', 'getAccessToken', null, '0', null, '967', '1', '2017-07-07 16:40:14', '1', '2017-07-07 16:40:14');
INSERT INTO `schedule_job_log` VALUES ('182', '3', 'systemTask', 'getAccessToken', null, '0', null, '4000', '1', '2017-07-10 15:12:00', '1', '2017-07-10 15:12:00');
INSERT INTO `schedule_job_log` VALUES ('183', '3', 'systemTask', 'getAccessToken', null, '0', null, '889', '1', '2017-07-10 19:22:46', '1', '2017-07-10 19:22:46');
INSERT INTO `schedule_job_log` VALUES ('184', '3', 'systemTask', 'getAccessToken', null, '0', null, '83', '1', '2017-07-12 11:03:05', '1', '2017-07-12 11:03:05');
INSERT INTO `schedule_job_log` VALUES ('185', '3', 'systemTask', 'getAccessToken', null, '0', null, '932', '1', '2017-07-14 10:18:11', '1', '2017-07-14 10:18:11');
INSERT INTO `schedule_job_log` VALUES ('186', '3', 'systemTask', 'getAccessToken', null, '0', null, '1469', '1', '2017-07-14 17:01:10', '1', '2017-07-14 17:01:10');
INSERT INTO `schedule_job_log` VALUES ('187', '3', 'systemTask', 'getAccessToken', null, '0', null, '1281', '1', '2017-07-17 11:34:42', '1', '2017-07-17 11:34:42');
INSERT INTO `schedule_job_log` VALUES ('188', '3', 'systemTask', 'getAccessToken', null, '0', null, '1575', '1', '2017-07-21 15:46:22', '1', '2017-07-21 15:46:22');
INSERT INTO `schedule_job_log` VALUES ('189', '3', 'systemTask', 'getAccessToken', null, '0', null, '983', '1', '2017-07-24 11:22:45', '1', '2017-07-24 11:22:45');
INSERT INTO `schedule_job_log` VALUES ('190', '3', 'systemTask', 'getAccessToken', null, '0', null, '67', '1', '2017-07-24 11:22:52', '1', '2017-07-24 11:22:52');
INSERT INTO `schedule_job_log` VALUES ('191', '3', 'systemTask', 'getAccessToken', null, '0', null, '525', '1', '2017-07-24 15:04:40', '1', '2017-07-24 15:04:40');

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `signImg` varchar(120) NOT NULL COMMENT '招牌照片',
  `content` varchar(50) NOT NULL COMMENT '招牌内容',
  `length` int(11) NOT NULL COMMENT '招牌长度',
  `width` int(11) NOT NULL COMMENT '规格宽度',
  `height` int(11) NOT NULL COMMENT '离地高度',
  `nature` char(1) NOT NULL COMMENT '使用性质',
  `type` char(1) NOT NULL COMMENT '招牌类型',
  `light` char(1) NOT NULL COMMENT '照明方式',
  `term` char(2) NOT NULL COMMENT '申请期限',
  `province` char(2) NOT NULL COMMENT '省',
  `city` char(2) NOT NULL COMMENT '市',
  `county` char(2) NOT NULL COMMENT '县',
  `dtlAddress` varchar(100) NOT NULL COMMENT '详细地址',
  `unm` varchar(30) NOT NULL COMMENT '姓名',
  `mobile` varchar(15) NOT NULL COMMENT '电话',
  `unit` varchar(80) NOT NULL COMMENT '所在单位',
  `blcs` varchar(80) NOT NULL COMMENT '营业执照',
  `openId` varchar(100) NOT NULL COMMENT '微信帐号',
  `crtid` bigint(20) NOT NULL COMMENT '创建人ID',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL COMMENT '修改人ID',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='招牌申请';

-- ----------------------------
-- Records of sign
-- ----------------------------

-- ----------------------------
-- Table structure for smslog
-- ----------------------------
DROP TABLE IF EXISTS `smslog`;
CREATE TABLE `smslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `receiver` varchar(15) NOT NULL COMMENT '接收人',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '发送类型:1系统2人工',
  `tid` varchar(100) DEFAULT NULL COMMENT '第三方id',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '发送状态：1:成功0:失败',
  `log` varchar(200) NOT NULL COMMENT '发送日志',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='短信记录';

-- ----------------------------
-- Records of smslog
-- ----------------------------
INSERT INTO `smslog` VALUES ('1', '1', '2', 'SMS_55705042', '验证码$测试，您正在注册成为$群发消息4用户，感谢您的支持！', '1', '{\"model\":\"106886278290^1109307347196\",\"requestId\":\"E31BE2C5-7226-4139-BD08-A39A7AF31236\"}', '1', '2017-04-11 18:44:19', '1', '2017-04-11 18:44:19');
INSERT INTO `smslog` VALUES ('2', '2', '2', 'SMS_55705042', '验证码$测试，您正在注册成为$群发消息4用户，感谢您的支持！', '0', '该用户没有绑定手机，不能进行消息发送。', '1', '2017-04-11 18:44:19', '1', '2017-04-11 18:44:19');
INSERT INTO `smslog` VALUES ('3', '3', '2', 'SMS_55705042', '验证码$测试，您正在注册成为$群发消息4用户，感谢您的支持！', '1', '{\"model\":\"106886381155^1109307455363\",\"requestId\":\"65DD32E0-5917-4AC7-82E5-C2F63441F546\"}', '1', '2017-04-11 18:44:19', '1', '2017-04-11 18:44:19');
INSERT INTO `smslog` VALUES ('4', '1', '2', 'SMS_55705046', '验证码$bug，您正在进行$bug身份验证，打死不要告诉别人哦！', '1', '{\"model\":\"106892361933^1109314975760\",\"requestId\":\"8D686D6E-2B50-4E0C-A867-6ADC79AB9D5F\"}', '1', '2017-04-12 09:31:44', '1', '2017-04-12 09:31:44');
INSERT INTO `smslog` VALUES ('5', '1', '2', 'SMS_55705042', '验证码bug，您正在注册成为bug2用户，感谢您的支持！', '1', '{\"model\":\"106892515329^1109314868849\",\"requestId\":\"CAB34D13-FEAE-4975-ABD2-BBD3A9CECDDB\"}', '1', '2017-04-12 09:34:01', '1', '2017-04-12 09:34:01');
INSERT INTO `smslog` VALUES ('6', '3', '2', 'SMS_55705042', '验证码4444，您正在注册成为55555用户，感谢您的支持！', '1', '{\"model\":\"106896446224^1109321071226\",\"requestId\":\"5771A67E-CD4D-4DB4-8C3E-A3AAA939F088\"}', '1', '2017-04-12 11:03:52', '1', '2017-04-12 11:03:52');
INSERT INTO `smslog` VALUES ('7', '1', '2', 'SMS_55705046', '验证码13123，您正在进行1111身份验证，打死不要告诉别人哦！', '1', '{\"model\":\"107279154479^1109807094219\",\"requestId\":\"7FABDF91-541F-4DEA-8205-F268B49D5B19\"}', '1', '2017-05-03 10:05:44', '1', '2017-05-03 10:05:44');
INSERT INTO `smslog` VALUES ('8', '1', '2', 'SMS_55705042', '验证码，您正在注册成为用户，感谢您的支持！', '1', '{\"model\":\"107304820990^1109838645918\",\"requestId\":\"4F299FE4-B37A-42C1-B9F3-69E4F246940F\"}', '1', '2017-05-04 16:55:15', '1', '2017-05-04 16:55:15');
INSERT INTO `smslog` VALUES ('9', '3', '2', 'SMS_55705042', '验证码，您正在注册成为用户，感谢您的支持！', '1', '{\"model\":\"107304880911^1109838528237\",\"requestId\":\"CC560EC9-9250-4C16-80CE-DBB8A204E653\"}', '1', '2017-05-04 16:55:15', '1', '2017-05-04 16:55:15');
INSERT INTO `smslog` VALUES ('10', '2', '2', 'SMS_55705042', '验证码，您正在注册成为用户，感谢您的支持！', '0', '该用户没有绑定手机，不能进行消息发送。', '1', '2017-05-04 16:55:15', '1', '2017-05-04 16:55:15');
INSERT INTO `smslog` VALUES ('11', '1', '2', 'SMS_55705047', '休息通知：\n\n按倨国家规定中秋节放假一天', '0', 'InvalidTemplateCode.Malformed : The specified templateCode is wrongly formed.\r\nRequestId : E933B18A-7AC9-4EE4-AA57-375EB7986D3E', '1', '2017-05-04 16:59:44', '1', '2017-05-04 16:59:44');
INSERT INTO `smslog` VALUES ('12', '1', '2', 'SMS_55705042', '验证消息', '0', 'InvalidParamStringTemplate.Malformed : The specified paramString and template is wrongly formed.\r\nRequestId : 172B0C79-4449-41C1-B473-726841CA8184', '1', '2017-05-04 17:01:19', '1', '2017-05-04 17:01:19');
INSERT INTO `smslog` VALUES ('13', '1', '2', 'SMS_55705042', '验证码，您正在注册成为用户，感谢您的支持！', '1', '{\"model\":\"107304798974^1109838628477\",\"requestId\":\"B7270116-FAE2-4F69-878C-A6CCB0301736\"}', '1', '2017-05-04 17:02:46', '1', '2017-05-04 17:02:46');
INSERT INTO `smslog` VALUES ('14', '1', '2', 'SMS_55705042', '	验证码，您正在进行身份验证，打死不要告诉别人哦！	', '1', '{\"model\":\"107305000740^1109838633982\",\"requestId\":\"001F8AD5-D2D7-4AE7-9784-F76616BFCFF6\"}', '1', '2017-05-04 17:05:18', '1', '2017-05-04 17:05:18');
INSERT INTO `smslog` VALUES ('15', '1', '2', 'SMS_55705047', '您好，请及时参加，地点为深圳市宝安区', '0', 'InvalidTemplateCode.Malformed : The specified templateCode is wrongly formed.\r\nRequestId : CFA76384-DCBC-414A-B86D-CEB67BAAF5D7', '1', '2017-05-04 17:24:12', '1', '2017-05-04 17:24:12');
INSERT INTO `smslog` VALUES ('16', '1', '2', 'SMS_55705047', '您好，请及时参加{theme}地点为{addr}{remark}', '0', 'InvalidTemplateCode.Malformed : The specified templateCode is wrongly formed.\r\nRequestId : 37F13D69-D11B-4B6A-8FDD-6C3C19510FF4', '1', '2017-05-04 17:26:10', '1', '2017-05-04 17:26:10');
INSERT INTO `smslog` VALUES ('17', '1', '2', 'SMS_55705047', '	验证码781205，您正在进行手机号身份验证，打死不要告诉别人哦！', '0', 'InvalidTemplateCode.Malformed : The specified templateCode is wrongly formed.\r\nRequestId : DD3183F2-D839-4CDD-87B4-4AB2C1EF596E', '1', '2017-05-04 17:29:29', '1', '2017-05-04 17:29:29');
INSERT INTO `smslog` VALUES ('18', '1', '2', 'SMS_55705042', '验证码1，您正在注册成为2用户，感谢您的支持！', '1', '{\"model\":\"108328431204^1111277606301\",\"requestId\":\"861AE6BD-F4C5-40D2-AEEE-8F0CD9FC4903\"}', '1', '2017-06-24 10:42:31', '1', '2017-06-24 10:42:31');

-- ----------------------------
-- Table structure for smstemplate
-- ----------------------------
DROP TABLE IF EXISTS `smstemplate`;
CREATE TABLE `smstemplate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nm` varchar(100) NOT NULL COMMENT '名称',
  `tid` varchar(100) DEFAULT NULL COMMENT '第三方id',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效0:无效',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='短信模板';

-- ----------------------------
-- Records of smstemplate
-- ----------------------------
INSERT INTO `smstemplate` VALUES ('1', '用户注册验证码', 'SMS_55705042', '验证码${code}，您正在注册成为${product}用户，感谢您的支持！', '1', '1', '2017-04-06 18:19:21', '1', '2017-05-04 11:40:14');
INSERT INTO `smstemplate` VALUES ('2', '身份验证验证码', 'SMS_55705046', '验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！', '1', '1', '2017-04-11 17:44:28', '1', '2017-04-11 17:44:28');
INSERT INTO `smstemplate` VALUES ('7', '98', 'SMS_55705047', '	验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！', '1', '1', '2017-05-04 17:28:29', '1', '2017-05-04 17:28:29');
INSERT INTO `smstemplate` VALUES ('8', 'grthfhfghgf ', null, 'hfg hgfhg hg hgf ', '1', '1', '2017-06-09 10:40:13', '1', '2017-06-09 10:40:13');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `rmk` varchar(500) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqkey` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'config', '1', '1', '系统配置', '1', '2017-03-13 19:48:15', '1', '2017-03-13 19:48:15');

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父部门ID，一级部门为0',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `valid` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否有效：0无效、1有效',
  `sort` decimal(8,0) NOT NULL COMMENT '排序号',
  `otype` char(2) NOT NULL DEFAULT '0' COMMENT '组织类型：来源数据字典',
  `rmk` varchar(400) DEFAULT NULL COMMENT '备注',
  `pesponsible` bigint(32) DEFAULT NULL COMMENT '责任人',
  `crtid` bigint(20) NOT NULL DEFAULT '1' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '1' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('12', '24', '事业一部', '1', '1', '1', '', '26', '1', '2017-06-27 18:27:28', '1', '2017-06-30 16:28:05');
INSERT INTO `sys_department` VALUES ('14', '24', '事业二部', '1', '2', '1', '', '14', '1', '2017-06-27 18:40:08', '1', '2017-06-30 17:17:24');
INSERT INTO `sys_department` VALUES ('15', '24', '事业五部', '1', '3', '1', '', null, '1', '2017-06-27 18:42:17', '1', '2017-06-27 19:37:42');
INSERT INTO `sys_department` VALUES ('16', '12', '霍山项目部', '1', '4', '3', '', null, '1', '2017-06-27 18:47:14', '1', '2017-06-28 14:42:14');
INSERT INTO `sys_department` VALUES ('17', '16', '霍山项目经理', '1', '5', '0', '', null, '1', '2017-06-27 18:48:07', '1', '2017-06-28 14:42:20');
INSERT INTO `sys_department` VALUES ('18', '12', '事业一部负责人', '1', '2', '0', '', null, '1', '2017-06-27 18:52:01', '1', '2017-06-27 18:52:01');
INSERT INTO `sys_department` VALUES ('19', '16', '清扫保洁部', '1', '6', '4', '', null, '1', '2017-06-27 18:52:42', '1', '2017-06-27 19:25:04');
INSERT INTO `sys_department` VALUES ('20', '16', '环城局', '1', '7', '0', '', null, '1', '2017-06-27 18:53:31', '1', '2017-06-27 19:25:11');
INSERT INTO `sys_department` VALUES ('22', '16', '垃圾清运部', '1', '1', '4', '', null, '1', '2017-06-27 19:21:58', '1', '2017-06-27 19:33:58');
INSERT INTO `sys_department` VALUES ('23', '16', '综合巡查', '1', '1', '4', '', null, '1', '2017-06-27 19:30:25', '1', '2017-06-27 19:36:49');
INSERT INTO `sys_department` VALUES ('24', '24', '总公司', '1', '1', '0', '', null, '1', '2017-06-27 19:32:18', '1', '2017-06-27 19:32:54');
INSERT INTO `sys_department` VALUES ('25', '19', '清扫保洁一班', '1', '0', '5', null, null, '1', '2017-06-28 18:05:47', '1', '2017-06-28 18:05:47');
INSERT INTO `sys_department` VALUES ('26', '19', '清扫保洁二班', '1', '1', '5', null, null, '1', '2017-06-28 18:06:59', '1', '2017-06-28 18:06:59');
INSERT INTO `sys_department` VALUES ('27', '12', '佛山项目部', '1', '3', '3', '1', null, '1', '2017-07-20 15:30:55', '1', '2017-07-20 15:30:55');
INSERT INTO `sys_department` VALUES ('28', '27', '清扫保洁', '1', '1', '4', '2', null, '1', '2017-07-20 15:31:48', '1', '2017-07-20 15:31:48');
INSERT INTO `sys_department` VALUES ('29', '14', '123', '1', '123', '4', '123', null, '1', '2017-07-20 16:10:59', '1', '2017-07-20 16:10:59');
INSERT INTO `sys_department` VALUES ('30', '27', '绿化管养', '1', '1', '4', '2', null, '1', '2017-07-20 16:21:40', '1', '2017-07-20 16:21:40');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=447 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-05-27 11:25:29');
INSERT INTO `sys_log` VALUES ('2', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-05-27 11:29:20');
INSERT INTO `sys_log` VALUES ('3', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-05-27 11:29:22');
INSERT INTO `sys_log` VALUES ('4', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-05-27 11:32:40');
INSERT INTO `sys_log` VALUES ('5', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-05-27 11:40:08');
INSERT INTO `sys_log` VALUES ('6', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-05-27 11:40:28');
INSERT INTO `sys_log` VALUES ('7', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"管理员\"', '127.0.0.1', '2017-05-27 11:40:46');
INSERT INTO `sys_log` VALUES ('8', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-05-27 11:41:36');
INSERT INTO `sys_log` VALUES ('9', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:08');
INSERT INTO `sys_log` VALUES ('10', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:11');
INSERT INTO `sys_log` VALUES ('11', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:16');
INSERT INTO `sys_log` VALUES ('12', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:21');
INSERT INTO `sys_log` VALUES ('13', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:25');
INSERT INTO `sys_log` VALUES ('14', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:29');
INSERT INTO `sys_log` VALUES ('15', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:32');
INSERT INTO `sys_log` VALUES ('16', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:36');
INSERT INTO `sys_log` VALUES ('17', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:48');
INSERT INTO `sys_log` VALUES ('18', '超级管理员', '保存定时任务', 'com.zhcs.controller.ScheduleJobController.save()', '{}', '127.0.0.1', '2017-05-27 12:01:57');
INSERT INTO `sys_log` VALUES ('19', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '127.0.0.1', '2017-05-27 12:12:30');
INSERT INTO `sys_log` VALUES ('20', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-05-27 15:22:20');
INSERT INTO `sys_log` VALUES ('21', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-05-27 17:05:02');
INSERT INTO `sys_log` VALUES ('22', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-05-27 17:07:26');
INSERT INTO `sys_log` VALUES ('23', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-05-27 17:09:03');
INSERT INTO `sys_log` VALUES ('24', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-05-27 18:00:37');
INSERT INTO `sys_log` VALUES ('25', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"管理\"', '127.0.0.1', '2017-05-27 18:00:49');
INSERT INTO `sys_log` VALUES ('26', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-01 16:48:14');
INSERT INTO `sys_log` VALUES ('27', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-02 17:25:33');
INSERT INTO `sys_log` VALUES ('28', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-02 17:25:34');
INSERT INTO `sys_log` VALUES ('29', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-02 17:25:35');
INSERT INTO `sys_log` VALUES ('30', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-02 17:57:27');
INSERT INTO `sys_log` VALUES ('31', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.95', '2017-06-05 12:52:18');
INSERT INTO `sys_log` VALUES ('32', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-08 11:49:26');
INSERT INTO `sys_log` VALUES ('33', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-08 11:56:25');
INSERT INTO `sys_log` VALUES ('34', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-08 11:56:49');
INSERT INTO `sys_log` VALUES ('35', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-08 11:56:54');
INSERT INTO `sys_log` VALUES ('36', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-08 11:56:55');
INSERT INTO `sys_log` VALUES ('37', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-08 12:01:10');
INSERT INTO `sys_log` VALUES ('38', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-08 12:01:52');
INSERT INTO `sys_log` VALUES ('39', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.85', '2017-06-08 14:05:26');
INSERT INTO `sys_log` VALUES ('40', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-08 18:22:41');
INSERT INTO `sys_log` VALUES ('41', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-08 18:23:49');
INSERT INTO `sys_log` VALUES ('42', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-08 18:24:31');
INSERT INTO `sys_log` VALUES ('43', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-08 18:25:08');
INSERT INTO `sys_log` VALUES ('44', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[5]', '127.0.0.1', '2017-06-08 18:29:04');
INSERT INTO `sys_log` VALUES ('45', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[4]', '127.0.0.1', '2017-06-08 18:30:30');
INSERT INTO `sys_log` VALUES ('46', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[4]', '127.0.0.1', '2017-06-08 18:31:04');
INSERT INTO `sys_log` VALUES ('47', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[5,4]', '127.0.0.1', '2017-06-08 18:31:27');
INSERT INTO `sys_log` VALUES ('48', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.85', '2017-06-09 10:35:07');
INSERT INTO `sys_log` VALUES ('49', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-09 18:38:58');
INSERT INTO `sys_log` VALUES ('50', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-09 18:39:30');
INSERT INTO `sys_log` VALUES ('51', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-09 18:40:24');
INSERT INTO `sys_log` VALUES ('52', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-09 18:41:19');
INSERT INTO `sys_log` VALUES ('53', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-09 18:41:59');
INSERT INTO `sys_log` VALUES ('54', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-09 18:42:05');
INSERT INTO `sys_log` VALUES ('55', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-09 18:42:09');
INSERT INTO `sys_log` VALUES ('56', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-09 18:42:35');
INSERT INTO `sys_log` VALUES ('57', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-09 18:42:58');
INSERT INTO `sys_log` VALUES ('58', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '127.0.0.1', '2017-06-09 21:23:50');
INSERT INTO `sys_log` VALUES ('59', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-10 17:20:11');
INSERT INTO `sys_log` VALUES ('60', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-10 17:23:11');
INSERT INTO `sys_log` VALUES ('61', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-10 17:23:44');
INSERT INTO `sys_log` VALUES ('62', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-10 17:28:37');
INSERT INTO `sys_log` VALUES ('63', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-10 17:31:54');
INSERT INTO `sys_log` VALUES ('64', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-12 10:56:33');
INSERT INTO `sys_log` VALUES ('65', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-12 11:09:00');
INSERT INTO `sys_log` VALUES ('66', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-12 16:36:24');
INSERT INTO `sys_log` VALUES ('67', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-12 17:56:50');
INSERT INTO `sys_log` VALUES ('68', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:14');
INSERT INTO `sys_log` VALUES ('69', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:20');
INSERT INTO `sys_log` VALUES ('70', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:23');
INSERT INTO `sys_log` VALUES ('71', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:25');
INSERT INTO `sys_log` VALUES ('72', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:27');
INSERT INTO `sys_log` VALUES ('73', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:29');
INSERT INTO `sys_log` VALUES ('74', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:32');
INSERT INTO `sys_log` VALUES ('75', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:08:33');
INSERT INTO `sys_log` VALUES ('76', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:12:07');
INSERT INTO `sys_log` VALUES ('77', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.70', '2017-06-14 18:12:37');
INSERT INTO `sys_log` VALUES ('78', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.70', '2017-06-14 18:13:05');
INSERT INTO `sys_log` VALUES ('79', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.70', '2017-06-14 18:13:21');
INSERT INTO `sys_log` VALUES ('80', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-14 18:16:14');
INSERT INTO `sys_log` VALUES ('81', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 10:15:11');
INSERT INTO `sys_log` VALUES ('82', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.80', '2017-06-14 23:03:38');
INSERT INTO `sys_log` VALUES ('83', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-15 14:39:42');
INSERT INTO `sys_log` VALUES ('84', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-15 14:39:48');
INSERT INTO `sys_log` VALUES ('85', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-15 14:41:16');
INSERT INTO `sys_log` VALUES ('86', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-15 15:07:17');
INSERT INTO `sys_log` VALUES ('87', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-15 15:07:24');
INSERT INTO `sys_log` VALUES ('88', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 15:15:07');
INSERT INTO `sys_log` VALUES ('89', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 15:15:27');
INSERT INTO `sys_log` VALUES ('90', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 15:17:30');
INSERT INTO `sys_log` VALUES ('91', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-15 15:51:57');
INSERT INTO `sys_log` VALUES ('92', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-15 15:53:13');
INSERT INTO `sys_log` VALUES ('93', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-15 15:54:39');
INSERT INTO `sys_log` VALUES ('94', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-15 21:04:44');
INSERT INTO `sys_log` VALUES ('95', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:18:54');
INSERT INTO `sys_log` VALUES ('96', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:19:04');
INSERT INTO `sys_log` VALUES ('97', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:22:20');
INSERT INTO `sys_log` VALUES ('98', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:27:29');
INSERT INTO `sys_log` VALUES ('99', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:28:14');
INSERT INTO `sys_log` VALUES ('100', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:29:23');
INSERT INTO `sys_log` VALUES ('101', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:32:58');
INSERT INTO `sys_log` VALUES ('102', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:33:00');
INSERT INTO `sys_log` VALUES ('103', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:33:07');
INSERT INTO `sys_log` VALUES ('104', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:33:09');
INSERT INTO `sys_log` VALUES ('105', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:33:45');
INSERT INTO `sys_log` VALUES ('106', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:34:32');
INSERT INTO `sys_log` VALUES ('107', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 21:55:02');
INSERT INTO `sys_log` VALUES ('108', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:55:14');
INSERT INTO `sys_log` VALUES ('109', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:55:26');
INSERT INTO `sys_log` VALUES ('110', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:55:29');
INSERT INTO `sys_log` VALUES ('111', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 21:56:25');
INSERT INTO `sys_log` VALUES ('112', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-15 22:14:27');
INSERT INTO `sys_log` VALUES ('113', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 22:14:30');
INSERT INTO `sys_log` VALUES ('114', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-15 22:14:56');
INSERT INTO `sys_log` VALUES ('115', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 09:36:36');
INSERT INTO `sys_log` VALUES ('116', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 09:36:44');
INSERT INTO `sys_log` VALUES ('117', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 09:39:27');
INSERT INTO `sys_log` VALUES ('118', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 09:42:10');
INSERT INTO `sys_log` VALUES ('119', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-16 09:44:15');
INSERT INTO `sys_log` VALUES ('120', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 09:59:18');
INSERT INTO `sys_log` VALUES ('121', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:00:12');
INSERT INTO `sys_log` VALUES ('122', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 10:00:34');
INSERT INTO `sys_log` VALUES ('123', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 10:01:23');
INSERT INTO `sys_log` VALUES ('124', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:08:13');
INSERT INTO `sys_log` VALUES ('125', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-16 10:19:45');
INSERT INTO `sys_log` VALUES ('126', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:37:46');
INSERT INTO `sys_log` VALUES ('127', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:39:21');
INSERT INTO `sys_log` VALUES ('128', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:39:33');
INSERT INTO `sys_log` VALUES ('129', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:50:35');
INSERT INTO `sys_log` VALUES ('130', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 10:52:00');
INSERT INTO `sys_log` VALUES ('131', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-16 10:54:37');
INSERT INTO `sys_log` VALUES ('132', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 10:56:41');
INSERT INTO `sys_log` VALUES ('133', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:00:05');
INSERT INTO `sys_log` VALUES ('134', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:05:17');
INSERT INTO `sys_log` VALUES ('135', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:06:20');
INSERT INTO `sys_log` VALUES ('136', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:15:07');
INSERT INTO `sys_log` VALUES ('137', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:15:59');
INSERT INTO `sys_log` VALUES ('138', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:18:23');
INSERT INTO `sys_log` VALUES ('139', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:19:05');
INSERT INTO `sys_log` VALUES ('140', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:20:06');
INSERT INTO `sys_log` VALUES ('141', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:20:08');
INSERT INTO `sys_log` VALUES ('142', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:20:49');
INSERT INTO `sys_log` VALUES ('143', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:21:06');
INSERT INTO `sys_log` VALUES ('144', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:22:17');
INSERT INTO `sys_log` VALUES ('145', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:24:01');
INSERT INTO `sys_log` VALUES ('146', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:29:15');
INSERT INTO `sys_log` VALUES ('147', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:29:36');
INSERT INTO `sys_log` VALUES ('148', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:33:51');
INSERT INTO `sys_log` VALUES ('149', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:37:59');
INSERT INTO `sys_log` VALUES ('150', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:39:23');
INSERT INTO `sys_log` VALUES ('151', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:41:46');
INSERT INTO `sys_log` VALUES ('152', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:45:47');
INSERT INTO `sys_log` VALUES ('153', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:46:38');
INSERT INTO `sys_log` VALUES ('154', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:47:02');
INSERT INTO `sys_log` VALUES ('155', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:47:09');
INSERT INTO `sys_log` VALUES ('156', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:47:34');
INSERT INTO `sys_log` VALUES ('157', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:47:36');
INSERT INTO `sys_log` VALUES ('158', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:52:24');
INSERT INTO `sys_log` VALUES ('159', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:52:30');
INSERT INTO `sys_log` VALUES ('160', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:52:53');
INSERT INTO `sys_log` VALUES ('161', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 11:54:36');
INSERT INTO `sys_log` VALUES ('162', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 11:59:31');
INSERT INTO `sys_log` VALUES ('163', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 12:02:22');
INSERT INTO `sys_log` VALUES ('164', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 12:05:22');
INSERT INTO `sys_log` VALUES ('165', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 12:05:42');
INSERT INTO `sys_log` VALUES ('166', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 12:06:03');
INSERT INTO `sys_log` VALUES ('167', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 12:16:35');
INSERT INTO `sys_log` VALUES ('168', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 12:17:07');
INSERT INTO `sys_log` VALUES ('169', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:25:29');
INSERT INTO `sys_log` VALUES ('170', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:27:59');
INSERT INTO `sys_log` VALUES ('171', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:35:57');
INSERT INTO `sys_log` VALUES ('172', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 13:36:58');
INSERT INTO `sys_log` VALUES ('173', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 13:37:23');
INSERT INTO `sys_log` VALUES ('174', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:38:52');
INSERT INTO `sys_log` VALUES ('175', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:44:34');
INSERT INTO `sys_log` VALUES ('176', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:49:07');
INSERT INTO `sys_log` VALUES ('177', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 13:59:03');
INSERT INTO `sys_log` VALUES ('178', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 14:04:06');
INSERT INTO `sys_log` VALUES ('179', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 14:05:20');
INSERT INTO `sys_log` VALUES ('180', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:05:53');
INSERT INTO `sys_log` VALUES ('181', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:06:19');
INSERT INTO `sys_log` VALUES ('182', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:06:40');
INSERT INTO `sys_log` VALUES ('183', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:06:51');
INSERT INTO `sys_log` VALUES ('184', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:07:15');
INSERT INTO `sys_log` VALUES ('185', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:07:35');
INSERT INTO `sys_log` VALUES ('186', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:07:45');
INSERT INTO `sys_log` VALUES ('187', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:08:10');
INSERT INTO `sys_log` VALUES ('188', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 14:09:05');
INSERT INTO `sys_log` VALUES ('189', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 14:09:23');
INSERT INTO `sys_log` VALUES ('190', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 14:11:52');
INSERT INTO `sys_log` VALUES ('191', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:12:46');
INSERT INTO `sys_log` VALUES ('192', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:12:50');
INSERT INTO `sys_log` VALUES ('193', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:12:54');
INSERT INTO `sys_log` VALUES ('194', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:17:44');
INSERT INTO `sys_log` VALUES ('195', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:18:00');
INSERT INTO `sys_log` VALUES ('196', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:18:18');
INSERT INTO `sys_log` VALUES ('197', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:18:34');
INSERT INTO `sys_log` VALUES ('198', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:18:35');
INSERT INTO `sys_log` VALUES ('199', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:18:35');
INSERT INTO `sys_log` VALUES ('200', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:18:36');
INSERT INTO `sys_log` VALUES ('201', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:18:56');
INSERT INTO `sys_log` VALUES ('202', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:18:58');
INSERT INTO `sys_log` VALUES ('203', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:19:30');
INSERT INTO `sys_log` VALUES ('204', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:23:00');
INSERT INTO `sys_log` VALUES ('205', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:23:02');
INSERT INTO `sys_log` VALUES ('206', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 15:23:05');
INSERT INTO `sys_log` VALUES ('207', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.70', '2017-06-16 15:24:11');
INSERT INTO `sys_log` VALUES ('208', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.70', '2017-06-16 15:24:26');
INSERT INTO `sys_log` VALUES ('209', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:31:43');
INSERT INTO `sys_log` VALUES ('210', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:34:50');
INSERT INTO `sys_log` VALUES ('211', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 15:34:55');
INSERT INTO `sys_log` VALUES ('212', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 16:37:52');
INSERT INTO `sys_log` VALUES ('213', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-16 17:07:36');
INSERT INTO `sys_log` VALUES ('214', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-16 17:19:17');
INSERT INTO `sys_log` VALUES ('215', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-16 17:29:28');
INSERT INTO `sys_log` VALUES ('216', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 19:56:28');
INSERT INTO `sys_log` VALUES ('217', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 19:57:53');
INSERT INTO `sys_log` VALUES ('218', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 19:58:00');
INSERT INTO `sys_log` VALUES ('219', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 19:58:05');
INSERT INTO `sys_log` VALUES ('220', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 19:58:10');
INSERT INTO `sys_log` VALUES ('221', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 21:10:24');
INSERT INTO `sys_log` VALUES ('222', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-16 21:22:35');
INSERT INTO `sys_log` VALUES ('223', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 21:22:36');
INSERT INTO `sys_log` VALUES ('224', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-16 21:22:39');
INSERT INTO `sys_log` VALUES ('225', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-19 11:01:43');
INSERT INTO `sys_log` VALUES ('226', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 14:54:37');
INSERT INTO `sys_log` VALUES ('227', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-19 14:56:56');
INSERT INTO `sys_log` VALUES ('228', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-19 14:57:17');
INSERT INTO `sys_log` VALUES ('229', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-19 14:57:46');
INSERT INTO `sys_log` VALUES ('230', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-19 14:57:56');
INSERT INTO `sys_log` VALUES ('231', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 15:13:26');
INSERT INTO `sys_log` VALUES ('232', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 15:13:45');
INSERT INTO `sys_log` VALUES ('233', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-19 15:13:49');
INSERT INTO `sys_log` VALUES ('234', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-19 15:13:55');
INSERT INTO `sys_log` VALUES ('235', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 15:23:40');
INSERT INTO `sys_log` VALUES ('236', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 15:23:48');
INSERT INTO `sys_log` VALUES ('237', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 15:58:51');
INSERT INTO `sys_log` VALUES ('238', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-19 16:15:18');
INSERT INTO `sys_log` VALUES ('239', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 17:51:50');
INSERT INTO `sys_log` VALUES ('240', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-19 17:52:11');
INSERT INTO `sys_log` VALUES ('241', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-19 19:39:26');
INSERT INTO `sys_log` VALUES ('242', '超级管理员', '恢复定时任务', 'com.zhcs.controller.ScheduleJobController.resume()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-19 19:39:34');
INSERT INTO `sys_log` VALUES ('243', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-20 09:30:37');
INSERT INTO `sys_log` VALUES ('244', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-20 11:53:15');
INSERT INTO `sys_log` VALUES ('245', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-20 15:05:29');
INSERT INTO `sys_log` VALUES ('246', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-20 17:02:22');
INSERT INTO `sys_log` VALUES ('247', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '127.0.0.1', '2017-06-20 20:01:18');
INSERT INTO `sys_log` VALUES ('248', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', '\"\"', '127.0.0.1', '2017-06-20 20:01:22');
INSERT INTO `sys_log` VALUES ('249', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '127.0.0.1', '2017-06-20 20:01:23');
INSERT INTO `sys_log` VALUES ('250', '超级管理员', '获取所有菜单列表', 'com.zhcs.controller.SysMenuController.list()', 'null', '127.0.0.1', '2017-06-20 20:01:51');
INSERT INTO `sys_log` VALUES ('251', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-21 09:14:10');
INSERT INTO `sys_log` VALUES ('252', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-21 11:23:29');
INSERT INTO `sys_log` VALUES ('253', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-21 18:09:39');
INSERT INTO `sys_log` VALUES ('254', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-21 19:46:02');
INSERT INTO `sys_log` VALUES ('255', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-21 19:46:34');
INSERT INTO `sys_log` VALUES ('256', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-22 10:53:17');
INSERT INTO `sys_log` VALUES ('257', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-22 14:33:03');
INSERT INTO `sys_log` VALUES ('258', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.168', '2017-06-22 16:38:34');
INSERT INTO `sys_log` VALUES ('259', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 16:38:59');
INSERT INTO `sys_log` VALUES ('260', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.168', '2017-06-22 17:08:39');
INSERT INTO `sys_log` VALUES ('261', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:10:14');
INSERT INTO `sys_log` VALUES ('262', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.168', '2017-06-22 17:20:07');
INSERT INTO `sys_log` VALUES ('263', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:21:38');
INSERT INTO `sys_log` VALUES ('264', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:23:33');
INSERT INTO `sys_log` VALUES ('265', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:23:39');
INSERT INTO `sys_log` VALUES ('266', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:23:48');
INSERT INTO `sys_log` VALUES ('267', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:23:55');
INSERT INTO `sys_log` VALUES ('268', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:24:00');
INSERT INTO `sys_log` VALUES ('269', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.168', '2017-06-22 17:26:12');
INSERT INTO `sys_log` VALUES ('270', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-22 19:06:39');
INSERT INTO `sys_log` VALUES ('271', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.63', '2017-06-22 06:15:29');
INSERT INTO `sys_log` VALUES ('272', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-23 10:14:42');
INSERT INTO `sys_log` VALUES ('273', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-23 14:42:51');
INSERT INTO `sys_log` VALUES ('274', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-23 14:42:54');
INSERT INTO `sys_log` VALUES ('275', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-23 14:43:10');
INSERT INTO `sys_log` VALUES ('276', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-23 14:43:19');
INSERT INTO `sys_log` VALUES ('277', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-23 18:01:17');
INSERT INTO `sys_log` VALUES ('278', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-23 19:50:38');
INSERT INTO `sys_log` VALUES ('279', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-24 14:37:33');
INSERT INTO `sys_log` VALUES ('280', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-24 15:28:38');
INSERT INTO `sys_log` VALUES ('281', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-24 16:49:30');
INSERT INTO `sys_log` VALUES ('282', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-27 15:31:08');
INSERT INTO `sys_log` VALUES ('283', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 16:27:37');
INSERT INTO `sys_log` VALUES ('284', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-27 20:26:38');
INSERT INTO `sys_log` VALUES ('285', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 21:43:26');
INSERT INTO `sys_log` VALUES ('286', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 21:51:53');
INSERT INTO `sys_log` VALUES ('287', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 21:53:03');
INSERT INTO `sys_log` VALUES ('288', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 21:55:15');
INSERT INTO `sys_log` VALUES ('289', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 21:57:33');
INSERT INTO `sys_log` VALUES ('290', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-27 21:58:37');
INSERT INTO `sys_log` VALUES ('291', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 17:58:57');
INSERT INTO `sys_log` VALUES ('292', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:09');
INSERT INTO `sys_log` VALUES ('293', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:11');
INSERT INTO `sys_log` VALUES ('294', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:14');
INSERT INTO `sys_log` VALUES ('295', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:16');
INSERT INTO `sys_log` VALUES ('296', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:18');
INSERT INTO `sys_log` VALUES ('297', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:21');
INSERT INTO `sys_log` VALUES ('298', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:24');
INSERT INTO `sys_log` VALUES ('299', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:07:27');
INSERT INTO `sys_log` VALUES ('300', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:21:44');
INSERT INTO `sys_log` VALUES ('301', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:27:32');
INSERT INTO `sys_log` VALUES ('302', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.11', '2017-06-27 18:28:36');
INSERT INTO `sys_log` VALUES ('303', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:30:55');
INSERT INTO `sys_log` VALUES ('304', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.11', '2017-06-27 18:31:13');
INSERT INTO `sys_log` VALUES ('305', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:32:15');
INSERT INTO `sys_log` VALUES ('306', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.11', '2017-06-27 18:32:50');
INSERT INTO `sys_log` VALUES ('307', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:36:54');
INSERT INTO `sys_log` VALUES ('308', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:38:22');
INSERT INTO `sys_log` VALUES ('309', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:38:32');
INSERT INTO `sys_log` VALUES ('310', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:39:13');
INSERT INTO `sys_log` VALUES ('311', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-28 09:39:57');
INSERT INTO `sys_log` VALUES ('312', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:40:13');
INSERT INTO `sys_log` VALUES ('313', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '192.168.1.11', '2017-06-27 18:41:31');
INSERT INTO `sys_log` VALUES ('314', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '192.168.1.11', '2017-06-27 18:42:23');
INSERT INTO `sys_log` VALUES ('315', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-28 10:02:59');
INSERT INTO `sys_log` VALUES ('316', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-28 10:04:18');
INSERT INTO `sys_log` VALUES ('317', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-28 10:04:29');
INSERT INTO `sys_log` VALUES ('318', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-28 10:07:35');
INSERT INTO `sys_log` VALUES ('319', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-28 10:24:26');
INSERT INTO `sys_log` VALUES ('320', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-28 10:34:17');
INSERT INTO `sys_log` VALUES ('321', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-28 10:36:03');
INSERT INTO `sys_log` VALUES ('322', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-28 10:49:53');
INSERT INTO `sys_log` VALUES ('323', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-28 10:49:57');
INSERT INTO `sys_log` VALUES ('324', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-28 10:50:49');
INSERT INTO `sys_log` VALUES ('325', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-28 10:50:53');
INSERT INTO `sys_log` VALUES ('326', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-28 10:50:54');
INSERT INTO `sys_log` VALUES ('327', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-28 10:50:58');
INSERT INTO `sys_log` VALUES ('328', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-28 14:32:40');
INSERT INTO `sys_log` VALUES ('329', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-06-28 14:32:47');
INSERT INTO `sys_log` VALUES ('330', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-06-28 14:33:00');
INSERT INTO `sys_log` VALUES ('331', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-28 14:33:50');
INSERT INTO `sys_log` VALUES ('332', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-28 15:05:21');
INSERT INTO `sys_log` VALUES ('333', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-28 15:06:05');
INSERT INTO `sys_log` VALUES ('334', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-28 16:17:31');
INSERT INTO `sys_log` VALUES ('335', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-06-29 09:39:44');
INSERT INTO `sys_log` VALUES ('336', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-29 12:24:08');
INSERT INTO `sys_log` VALUES ('337', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-29 12:24:13');
INSERT INTO `sys_log` VALUES ('338', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-29 12:24:16');
INSERT INTO `sys_log` VALUES ('339', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-06-29 12:24:37');
INSERT INTO `sys_log` VALUES ('340', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-06-30 15:25:00');
INSERT INTO `sys_log` VALUES ('341', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 10:41:03');
INSERT INTO `sys_log` VALUES ('342', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 10:41:05');
INSERT INTO `sys_log` VALUES ('343', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 10:41:05');
INSERT INTO `sys_log` VALUES ('344', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 11:28:53');
INSERT INTO `sys_log` VALUES ('345', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-07-01 11:29:07');
INSERT INTO `sys_log` VALUES ('346', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 11:32:59');
INSERT INTO `sys_log` VALUES ('347', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-07-01 11:33:17');
INSERT INTO `sys_log` VALUES ('348', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 14:36:42');
INSERT INTO `sys_log` VALUES ('349', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-01 14:37:15');
INSERT INTO `sys_log` VALUES ('350', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '127.0.0.1', '2017-07-01 14:37:22');
INSERT INTO `sys_log` VALUES ('351', '超级管理员', '暂停定时任务', 'com.zhcs.controller.ScheduleJobController.pause()', '[3]', '127.0.0.1', '2017-07-01 15:13:01');
INSERT INTO `sys_log` VALUES ('352', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[1]', '127.0.0.1', '2017-07-01 15:13:16');
INSERT INTO `sys_log` VALUES ('353', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-03 11:43:26');
INSERT INTO `sys_log` VALUES ('354', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-03 15:19:55');
INSERT INTO `sys_log` VALUES ('355', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-04 18:19:12');
INSERT INTO `sys_log` VALUES ('356', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-04 18:25:05');
INSERT INTO `sys_log` VALUES ('357', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-04 18:25:12');
INSERT INTO `sys_log` VALUES ('358', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:02:10');
INSERT INTO `sys_log` VALUES ('359', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:21:42');
INSERT INTO `sys_log` VALUES ('360', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:25:12');
INSERT INTO `sys_log` VALUES ('361', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:36:45');
INSERT INTO `sys_log` VALUES ('362', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:40:02');
INSERT INTO `sys_log` VALUES ('363', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:53:22');
INSERT INTO `sys_log` VALUES ('364', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 15:59:26');
INSERT INTO `sys_log` VALUES ('365', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 16:12:39');
INSERT INTO `sys_log` VALUES ('366', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 16:28:35');
INSERT INTO `sys_log` VALUES ('367', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 16:34:09');
INSERT INTO `sys_log` VALUES ('368', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-07 16:40:13');
INSERT INTO `sys_log` VALUES ('369', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-10 15:11:55');
INSERT INTO `sys_log` VALUES ('370', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-10 19:22:45');
INSERT INTO `sys_log` VALUES ('371', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-12 11:03:04');
INSERT INTO `sys_log` VALUES ('372', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-14 10:18:10');
INSERT INTO `sys_log` VALUES ('373', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-14 17:01:09');
INSERT INTO `sys_log` VALUES ('374', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-14 17:13:01');
INSERT INTO `sys_log` VALUES ('375', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-14 17:24:29');
INSERT INTO `sys_log` VALUES ('376', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-14 17:24:36');
INSERT INTO `sys_log` VALUES ('377', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-17 11:34:40');
INSERT INTO `sys_log` VALUES ('378', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-21 10:16:43');
INSERT INTO `sys_log` VALUES ('379', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '127.0.0.1', '2017-07-21 10:43:52');
INSERT INTO `sys_log` VALUES ('380', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-21 12:32:36');
INSERT INTO `sys_log` VALUES ('381', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-21 15:46:08');
INSERT INTO `sys_log` VALUES ('382', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-21 16:40:08');
INSERT INTO `sys_log` VALUES ('383', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-21 16:40:32');
INSERT INTO `sys_log` VALUES ('384', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-21 17:26:00');
INSERT INTO `sys_log` VALUES ('385', '超级管理员', '保存作业计划(按天排班)', 'com.zhcs.controller.OperationplandataController.savePlan()', '{\"date\":\"2017-07-22\",\"datas\":{\"2017-07-22#64#26#22\":[\"2017-07-22#64#26#22\"],\"2017-07-22#64#25#9\":[\"2017-07-22#64#25#9\"],\"2017-07-22#64#26#13\":[\"2017-07-22#64#26#13\"],\"2017-07-22#64#25#14\":[\"2017-07-22#64#25#14\"]},\"banci\":[64],\"project\":16,\"bsment\":\"0\"}', '0:0:0:0:0:0:0:1', '2017-07-22 09:53:20');
INSERT INTO `sys_log` VALUES ('386', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-22 10:22:34');
INSERT INTO `sys_log` VALUES ('387', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-22 11:16:39');
INSERT INTO `sys_log` VALUES ('388', '超级管理员', '保存作业计划(按天排班)', 'com.zhcs.controller.OperationplandataController.savePlan()', '{\"datas\":{\"2017-07-24#64#25#24#driver#car\":\"1\",\"2017-07-24#64#25#9\":[\"2017-07-24#64#25#9\"],\"2017-07-24#64#25#14\":[\"2017-07-24#64#25#14\"],\"2017-07-24#64#25#24\":[\"2017-07-24#64#25#24\"],\"2017-07-24#64#25#14#driver#car\":\"1\",\"2017-07-24#64#25#9#driver#car\":\"1\"},\"project\":16,\"bsment\":\"0\",\"date\":\"2017-07-24\",\"banci\":[64,65]}', '0:0:0:0:0:0:0:1', '2017-07-24 10:03:35');
INSERT INTO `sys_log` VALUES ('389', '超级管理员', '保存作业计划(按天排班)', 'com.zhcs.controller.OperationplandataController.savePlan()', '{\"datas\":{\"2017-08-01#65#26#13#driver#car\":\"0\",\"2017-08-01#65#26#13\":[\"2017-08-01#65#26#13\"],\"2017-08-01#64#25#9\":[\"2017-08-01#64#25#9\"],\"2017-08-01#64#25#9#driver#car\":\"1\",\"2017-08-01#65#25#24\":[\"2017-08-01#65#25#24\"],\"2017-08-01#65#25#24#driver#car\":\"1\",\"2017-08-01#64#25#9#driver\":[\"2017-08-01#64#25#9#driver\"],\"2017-08-01#65#26#13#driver\":[\"2017-08-01#65#26#13#driver\"]},\"project\":16,\"bsment\":\"0\",\"date\":\"2017-08-01\",\"banci\":[64,65,66]}', '0:0:0:0:0:0:0:1', '2017-07-24 10:05:09');
INSERT INTO `sys_log` VALUES ('390', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:04:05');
INSERT INTO `sys_log` VALUES ('391', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:09:14');
INSERT INTO `sys_log` VALUES ('392', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:12:05');
INSERT INTO `sys_log` VALUES ('393', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-24 11:22:44');
INSERT INTO `sys_log` VALUES ('394', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-24 11:22:52');
INSERT INTO `sys_log` VALUES ('395', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:46:43');
INSERT INTO `sys_log` VALUES ('396', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:50:10');
INSERT INTO `sys_log` VALUES ('397', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:50:51');
INSERT INTO `sys_log` VALUES ('398', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:51:01');
INSERT INTO `sys_log` VALUES ('399', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:52:19');
INSERT INTO `sys_log` VALUES ('400', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:52:22');
INSERT INTO `sys_log` VALUES ('401', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:52:45');
INSERT INTO `sys_log` VALUES ('402', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:52:51');
INSERT INTO `sys_log` VALUES ('403', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:53:09');
INSERT INTO `sys_log` VALUES ('404', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:53:36');
INSERT INTO `sys_log` VALUES ('405', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:54:24');
INSERT INTO `sys_log` VALUES ('406', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:55:09');
INSERT INTO `sys_log` VALUES ('407', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:55:15');
INSERT INTO `sys_log` VALUES ('408', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:55:22');
INSERT INTO `sys_log` VALUES ('409', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:56:01');
INSERT INTO `sys_log` VALUES ('410', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:56:07');
INSERT INTO `sys_log` VALUES ('411', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:56:16');
INSERT INTO `sys_log` VALUES ('412', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 11:56:35');
INSERT INTO `sys_log` VALUES ('413', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:57:10');
INSERT INTO `sys_log` VALUES ('414', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 11:58:23');
INSERT INTO `sys_log` VALUES ('415', '超级管理员', '立即执行任务', 'com.zhcs.controller.ScheduleJobController.run()', '[3]', '0:0:0:0:0:0:0:1', '2017-07-24 15:04:38');
INSERT INTO `sys_log` VALUES ('416', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 15:43:46');
INSERT INTO `sys_log` VALUES ('417', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 15:44:03');
INSERT INTO `sys_log` VALUES ('418', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 15:44:13');
INSERT INTO `sys_log` VALUES ('419', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 15:44:33');
INSERT INTO `sys_log` VALUES ('420', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 15:44:43');
INSERT INTO `sys_log` VALUES ('421', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 15:45:00');
INSERT INTO `sys_log` VALUES ('422', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 15:49:27');
INSERT INTO `sys_log` VALUES ('423', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 16:06:17');
INSERT INTO `sys_log` VALUES ('424', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:06:41');
INSERT INTO `sys_log` VALUES ('425', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:11:32');
INSERT INTO `sys_log` VALUES ('426', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:11:37');
INSERT INTO `sys_log` VALUES ('427', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:12:22');
INSERT INTO `sys_log` VALUES ('428', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:12:33');
INSERT INTO `sys_log` VALUES ('429', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:13:04');
INSERT INTO `sys_log` VALUES ('430', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 16:14:25');
INSERT INTO `sys_log` VALUES ('431', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 17:56:35');
INSERT INTO `sys_log` VALUES ('432', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 17:56:45');
INSERT INTO `sys_log` VALUES ('433', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 17:56:55');
INSERT INTO `sys_log` VALUES ('434', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-24 18:36:34');
INSERT INTO `sys_log` VALUES ('435', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-24 18:36:52');
INSERT INTO `sys_log` VALUES ('436', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-25 11:01:42');
INSERT INTO `sys_log` VALUES ('437', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:02:10');
INSERT INTO `sys_log` VALUES ('438', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', 'null', '0:0:0:0:0:0:0:1', '2017-07-25 11:08:59');
INSERT INTO `sys_log` VALUES ('439', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:14');
INSERT INTO `sys_log` VALUES ('440', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:27');
INSERT INTO `sys_log` VALUES ('441', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:36');
INSERT INTO `sys_log` VALUES ('442', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:46');
INSERT INTO `sys_log` VALUES ('443', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:54');
INSERT INTO `sys_log` VALUES ('444', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:57');
INSERT INTO `sys_log` VALUES ('445', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:09:59');
INSERT INTO `sys_log` VALUES ('446', '超级管理员', '获取角色列表', 'com.zhcs.controller.SysRoleController.list()', '\"\"', '0:0:0:0:0:0:0:1', '2017-07-25 11:10:02');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `crtid` bigint(20) NOT NULL COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', null, '0', 'fa fa-cogs', '0', '1', '2017-03-08 16:55:41', '1', '2017-05-17 11:35:51');
INSERT INTO `sys_menu` VALUES ('2', '49', '员工管理', 'sys/user.html', null, '1', null, '1', '1', '2017-03-08 16:55:41', '1', '2017-03-22 11:48:01');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', null, '1', '1', '2017-03-08 16:55:41', '1', '2017-06-06 20:24:36');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', null, '2', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', null, '3', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'sys/schedule.html', null, '1', null, '4', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms,department:selectDR', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-06-16 10:03:56');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms,department:selectDR', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-06-16 10:04:04');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('28', '1', '代码生成器', 'sys/generator.html', 'sys:generator:list,sys:generator:code', '1', null, '5', '1', '2017-03-08 16:55:41', '1', '2017-03-22 14:25:10');
INSERT INTO `sys_menu` VALUES ('34', '1', '地区管理', 'area/area.html', '', '1', null, '6', '1', '2017-03-08 16:55:41', '1', '2017-06-06 20:24:26');
INSERT INTO `sys_menu` VALUES ('35', '34', '查看', null, 'area:list,area:info', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('36', '34', '新增', null, 'area:save', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('37', '34', '修改', null, 'area:update', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('38', '34', '删除', null, 'area:delete', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-08 16:55:41');
INSERT INTO `sys_menu` VALUES ('39', '1', '数据字典管理', 'basecode/basecode.html', null, '1', null, '7', '1', '2017-03-08 16:55:41', '1', '2017-05-25 20:54:24');
INSERT INTO `sys_menu` VALUES ('40', '39', '查看', null, 'basecode:list,basecode:info', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-13 19:52:12');
INSERT INTO `sys_menu` VALUES ('41', '39', '新增', null, 'basecode:save', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-13 19:52:19');
INSERT INTO `sys_menu` VALUES ('42', '39', '修改', null, 'basecode:update', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-13 19:52:27');
INSERT INTO `sys_menu` VALUES ('43', '39', '删除', null, 'basecode:delete', '2', null, '0', '1', '2017-03-08 16:55:41', '1', '2017-03-13 19:52:33');
INSERT INTO `sys_menu` VALUES ('44', '49', '部门管理', 'sys/department.html', null, '1', null, '2', '1', '2017-03-09 14:48:20', '1', '2017-03-22 11:47:37');
INSERT INTO `sys_menu` VALUES ('45', '44', '查看', null, 'department:list,department:info', '2', null, '0', '1', '2017-03-09 14:49:28', '1', '2017-03-09 14:49:28');
INSERT INTO `sys_menu` VALUES ('46', '44', '新增', null, 'department:save,department:select', '2', null, '0', '1', '2017-03-09 14:49:49', '1', '2017-03-09 15:31:03');
INSERT INTO `sys_menu` VALUES ('47', '44', '修改', null, 'department:update,department:select', '2', null, '0', '1', '2017-03-09 14:50:06', '1', '2017-03-09 15:31:12');
INSERT INTO `sys_menu` VALUES ('48', '44', '删除', null, 'department:delete', '2', null, '0', '1', '2017-03-09 14:50:22', '1', '2017-03-09 14:50:22');
INSERT INTO `sys_menu` VALUES ('49', '0', '用户中心', null, null, '0', 'fa fa-users', '1', '1', '2017-03-22 11:11:49', '1', '2017-03-22 11:11:49');
INSERT INTO `sys_menu` VALUES ('50', '49', '市民管理', 'citizen/citizen.html', null, '1', null, '3', '1', '2017-03-22 11:14:02', '1', '2017-03-22 11:14:02');
INSERT INTO `sys_menu` VALUES ('51', '49', '甲方管理', 'party/party.html', null, '1', null, '4', '1', '2017-03-22 11:14:51', '1', '2017-03-22 11:14:51');
INSERT INTO `sys_menu` VALUES ('52', '50', '查看', null, 'citizen:list,citizen:info', '2', null, '0', '1', '2017-03-22 11:21:21', '1', '2017-03-22 11:21:21');
INSERT INTO `sys_menu` VALUES ('56', '51', '查看', null, 'party:list,party:info', '2', null, '0', '1', '2017-03-22 11:22:34', '1', '2017-03-22 11:22:34');
INSERT INTO `sys_menu` VALUES ('57', '51', '新增', null, 'party:save', '2', null, '0', '1', '2017-03-22 11:22:50', '1', '2017-03-22 11:22:50');
INSERT INTO `sys_menu` VALUES ('58', '51', '修改', null, 'party:update', '2', null, '0', '1', '2017-03-22 11:23:05', '1', '2017-03-22 11:23:05');
INSERT INTO `sys_menu` VALUES ('59', '51', '删除', null, 'party:delete', '2', null, '0', '1', '2017-03-22 11:23:17', '1', '2017-03-22 11:23:17');
INSERT INTO `sys_menu` VALUES ('66', '0', '信息管理', null, null, '0', 'fa fa-comments', '3', '1', '2017-03-24 14:16:58', '1', '2017-03-24 14:16:58');
INSERT INTO `sys_menu` VALUES ('67', '66', '基础设置', 'msgconfig/msgconfig.html', null, '1', null, '1', '1', '2017-03-24 14:18:44', '1', '2017-03-24 14:18:44');
INSERT INTO `sys_menu` VALUES ('68', '67', '查看', null, 'msgconfig:list,msgconfig:info', '2', null, '0', '1', '2017-03-24 14:19:59', '1', '2017-03-24 14:21:22');
INSERT INTO `sys_menu` VALUES ('69', '67', '新增', null, 'msgconfig:save', '2', null, '0', '1', '2017-03-24 14:20:17', '1', '2017-03-24 14:20:17');
INSERT INTO `sys_menu` VALUES ('70', '67', '修改', null, 'msgconfig:update', '2', null, '0', '1', '2017-03-24 14:20:39', '1', '2017-03-24 14:20:39');
INSERT INTO `sys_menu` VALUES ('71', '66', '通讯组', 'cmngroup/cmngroup.html', null, '1', null, '2', '1', '2017-03-24 15:40:03', '1', '2017-03-24 15:40:03');
INSERT INTO `sys_menu` VALUES ('72', '71', '查看', null, 'cmngroup:list,cmngroup:info,department:selectForCmn', '2', null, '0', '1', '2017-03-24 15:41:24', '1', '2017-03-24 18:40:31');
INSERT INTO `sys_menu` VALUES ('73', '71', '新增', null, 'cmngroup:save,department:selectForCmn', '2', null, '0', '1', '2017-03-24 15:44:17', '1', '2017-03-24 18:40:40');
INSERT INTO `sys_menu` VALUES ('74', '71', '修改', null, 'cmngroup:update,department:selectForCmn', '2', null, '0', '1', '2017-03-24 15:44:31', '1', '2017-03-24 18:40:46');
INSERT INTO `sys_menu` VALUES ('75', '71', '删除', null, 'cmngroup:delete', '2', null, '0', '1', '2017-03-24 15:44:46', '1', '2017-03-24 15:44:46');
INSERT INTO `sys_menu` VALUES ('78', '1', 'Cron表达式生成器', 'cron.html', null, '1', null, '8', '1', '2017-04-01 12:27:10', '1', '2017-05-25 20:54:29');
INSERT INTO `sys_menu` VALUES ('79', '49', '微信关注者市民列表', 'wechat/wechat.html', null, '1', null, '5', '1', '2017-04-01 15:13:00', '1', '2017-04-11 11:15:17');
INSERT INTO `sys_menu` VALUES ('80', '79', '查看', null, 'wechat:list,wechat:info', '2', null, '0', '1', '2017-04-01 15:13:55', '1', '2017-04-01 15:13:55');
INSERT INTO `sys_menu` VALUES ('81', '79', '删除', null, 'wechat:delete', '2', null, '0', '1', '2017-04-01 15:14:17', '1', '2017-04-01 15:14:17');
INSERT INTO `sys_menu` VALUES ('87', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0', '1', '2017-04-06 10:27:49', '1', '2017-04-06 10:27:49');
INSERT INTO `sys_menu` VALUES ('88', '66', '短信模板', 'smstemplate/smstemplate.html', null, '1', null, '3', '1', '2017-04-06 16:07:56', '1', '2017-04-06 16:07:56');
INSERT INTO `sys_menu` VALUES ('89', '66', '消息模板', 'msgtemplate/msgtemplate.html', '', '1', null, '4', '1', '2017-04-06 16:10:23', '1', '2017-04-06 16:10:23');
INSERT INTO `sys_menu` VALUES ('90', '66', '邮件模板', 'mailtemplate/mailtemplate.html', null, '1', null, '5', '1', '2017-04-06 16:11:17', '1', '2017-04-06 16:11:17');
INSERT INTO `sys_menu` VALUES ('91', '88', '查看', null, 'smstemplate:list,smstemplate:info', '2', null, '0', '1', '2017-04-06 16:12:25', '1', '2017-04-06 16:12:25');
INSERT INTO `sys_menu` VALUES ('92', '88', '新增', null, 'smstemplate:save', '2', null, '0', '1', '2017-04-06 16:13:20', '1', '2017-04-06 16:13:20');
INSERT INTO `sys_menu` VALUES ('93', '88', '修改', null, 'smstemplate:update', '2', null, '0', '1', '2017-04-06 16:13:36', '1', '2017-04-06 16:13:36');
INSERT INTO `sys_menu` VALUES ('94', '88', '删除', null, 'smstemplate:delete', '2', null, '0', '1', '2017-04-06 16:13:52', '1', '2017-04-06 16:13:52');
INSERT INTO `sys_menu` VALUES ('95', '89', '查看', null, 'msgtemplate:list,msgtemplate:info', '2', null, '0', '1', '2017-04-06 16:35:39', '1', '2017-04-06 16:35:39');
INSERT INTO `sys_menu` VALUES ('96', '89', '新增', null, 'msgtemplate:save', '2', null, '0', '1', '2017-04-06 16:35:56', '1', '2017-04-06 16:35:56');
INSERT INTO `sys_menu` VALUES ('97', '89', '修改', null, 'msgtemplate:update', '2', null, '0', '1', '2017-04-06 16:36:14', '1', '2017-04-06 16:36:14');
INSERT INTO `sys_menu` VALUES ('98', '89', '删除', null, 'msgtemplate:delete', '2', null, '0', '1', '2017-04-06 16:36:29', '1', '2017-04-06 16:36:29');
INSERT INTO `sys_menu` VALUES ('99', '90', '查看', null, 'mailtemplate:list,mailtemplate:info', '2', null, '0', '1', '2017-04-06 16:36:52', '1', '2017-04-06 16:36:52');
INSERT INTO `sys_menu` VALUES ('100', '90', '新增', null, 'mailtemplate:save', '2', null, '0', '1', '2017-04-06 16:37:11', '1', '2017-04-06 16:37:11');
INSERT INTO `sys_menu` VALUES ('101', '90', '修改', null, 'mailtemplate:update', '2', null, '0', '1', '2017-04-06 16:37:27', '1', '2017-04-06 16:37:27');
INSERT INTO `sys_menu` VALUES ('102', '90', '删除', null, 'mailtemplate:delete', '2', null, '0', '1', '2017-04-06 16:38:14', '1', '2017-04-06 16:38:14');
INSERT INTO `sys_menu` VALUES ('103', '66', '发送短信', 'smslog/smslog.html', null, '1', null, '6', '1', '2017-04-07 14:28:43', '1', '2017-04-07 14:28:43');
INSERT INTO `sys_menu` VALUES ('104', '66', '发送消息', 'msglog/msglog.html', '', '1', null, '7', '1', '2017-04-07 14:30:06', '1', '2017-04-07 14:30:06');
INSERT INTO `sys_menu` VALUES ('105', '66', '发送邮件', 'maillog/maillog.html', null, '1', null, '8', '1', '2017-04-07 14:31:13', '1', '2017-04-07 14:31:13');
INSERT INTO `sys_menu` VALUES ('106', '103', '查看', null, 'smslog:list,smslog:info', '2', null, '0', '1', '2017-04-07 14:31:58', '1', '2017-04-07 14:31:58');
INSERT INTO `sys_menu` VALUES ('107', '103', '新增', null, 'smslog:save,smstemplate:getSendTemplate,smstemplate:info2,sys:user:getUserListByMsg', '2', null, '0', '1', '2017-04-07 14:32:13', '1', '2017-04-11 18:02:40');
INSERT INTO `sys_menu` VALUES ('108', '104', '查看', null, 'msglog:list,msglog:info', '2', null, '0', '1', '2017-04-07 14:32:42', '1', '2017-04-07 14:32:42');
INSERT INTO `sys_menu` VALUES ('109', '104', '新增', null, 'msglog:save,msgtemplate:getSendTemplate,msgtemplate:info2,sys:user:getUserListByMsg', '2', null, '0', '1', '2017-04-07 14:33:01', '1', '2017-04-11 11:28:40');
INSERT INTO `sys_menu` VALUES ('110', '105', '查看', null, 'maillog:list,maillog:info', '2', null, '0', '1', '2017-04-07 14:33:26', '1', '2017-04-07 14:33:26');
INSERT INTO `sys_menu` VALUES ('111', '105', '新增', null, 'maillog:save,mailtemplate:getSendTemplate,mailtemplate:info2,sys:user:getUserListByMsg', '2', null, '0', '1', '2017-04-07 14:33:39', '1', '2017-04-07 14:33:39');
INSERT INTO `sys_menu` VALUES ('112', '0', '车辆管理', null, null, '0', 'fa fa-car', '4', '1', '2017-04-07 17:16:06', '1', '2017-04-07 17:16:06');
INSERT INTO `sys_menu` VALUES ('113', '112', '车队管理', 'fleett/fleett.html', null, '1', null, '1', '1', '2017-04-07 17:16:44', '1', '2017-04-07 17:41:46');
INSERT INTO `sys_menu` VALUES ('114', '112', '车辆管理', 'car/car.html', null, '1', null, '2', '1', '2017-04-07 17:19:34', '1', '2017-04-07 17:46:09');
INSERT INTO `sys_menu` VALUES ('115', '112', '司机管理', 'driver/driver.html', null, '1', null, '3', '1', '2017-04-07 17:21:00', '1', '2017-04-07 17:46:38');
INSERT INTO `sys_menu` VALUES ('116', '112', '年审管理', 'yeartrial/yeartrial.html', null, '1', null, '4', '1', '2017-04-07 17:21:38', '1', '2017-04-07 17:47:38');
INSERT INTO `sys_menu` VALUES ('117', '112', '保险管理', 'insure/insure.html', null, '1', null, '5', '1', '2017-04-07 17:22:14', '1', '2017-04-07 17:48:13');
INSERT INTO `sys_menu` VALUES ('118', '112', '加油记录', 'gas/gas.html', null, '1', null, '6', '1', '2017-04-07 17:22:32', '1', '2017-04-07 17:48:44');
INSERT INTO `sys_menu` VALUES ('119', '112', '违章管理', 'illega/illega.html', null, '1', null, '7', '1', '2017-04-07 17:22:50', '1', '2017-04-07 17:49:57');
INSERT INTO `sys_menu` VALUES ('120', '112', '保养管理', 'maintain/maintain.html', null, '1', null, '8', '1', '2017-04-07 17:23:28', '1', '2017-04-07 17:50:27');
INSERT INTO `sys_menu` VALUES ('121', '112', '维修管理', 'repair/repair.html', null, '1', null, '9', '1', '2017-04-07 17:23:48', '1', '2017-04-07 17:50:55');
INSERT INTO `sys_menu` VALUES ('122', '112', '车辆类型', 'cartype/cartype.html', null, '1', null, '10', '1', '2017-04-07 17:24:07', '1', '2017-05-17 11:23:03');
INSERT INTO `sys_menu` VALUES ('123', '113', '新增', null, 'fleett:save', '2', null, '0', '1', '2017-04-07 18:02:35', '1', '2017-04-07 18:02:35');
INSERT INTO `sys_menu` VALUES ('124', '113', '查看', null, 'fleett:list,fleett:info', '2', null, '0', '1', '2017-04-07 18:04:15', '1', '2017-04-07 18:04:15');
INSERT INTO `sys_menu` VALUES ('125', '113', '修改', null, 'fleett:update', '2', null, '0', '1', '2017-04-07 18:05:48', '1', '2017-04-07 18:05:48');
INSERT INTO `sys_menu` VALUES ('126', '113', '删除', null, 'fleett:delete', '2', null, '0', '1', '2017-04-07 18:06:11', '1', '2017-04-07 18:06:11');
INSERT INTO `sys_menu` VALUES ('127', '114', '新增', null, 'car:save', '2', null, '0', '1', '2017-04-07 18:07:02', '1', '2017-04-07 18:07:02');
INSERT INTO `sys_menu` VALUES ('128', '114', '查看', null, 'car:list,car:info', '2', null, '0', '1', '2017-04-07 18:07:52', '1', '2017-04-07 18:07:52');
INSERT INTO `sys_menu` VALUES ('129', '114', '修改', null, 'car:update', '2', null, '0', '1', '2017-04-07 18:08:15', '1', '2017-04-07 18:08:15');
INSERT INTO `sys_menu` VALUES ('130', '114', '删除', null, 'car:delete', '2', null, '0', '1', '2017-04-07 18:08:45', '1', '2017-04-07 18:08:45');
INSERT INTO `sys_menu` VALUES ('131', '115', '新增', null, 'driver:save', '2', null, '0', '1', '2017-04-07 18:09:29', '1', '2017-04-07 18:09:29');
INSERT INTO `sys_menu` VALUES ('132', '115', '查看', null, 'driver:list,driver:info', '2', null, '0', '1', '2017-04-07 18:09:57', '1', '2017-04-07 18:09:57');
INSERT INTO `sys_menu` VALUES ('133', '115', '修改', null, 'driver:update', '2', null, '0', '1', '2017-04-07 18:10:25', '1', '2017-04-07 18:10:25');
INSERT INTO `sys_menu` VALUES ('134', '115', '删除', null, 'driver:delete', '2', null, '0', '1', '2017-04-07 18:10:46', '1', '2017-04-07 18:10:46');
INSERT INTO `sys_menu` VALUES ('135', '116', '新增', null, 'yeartrial:save', '2', null, '0', '1', '2017-04-07 18:11:40', '1', '2017-04-07 18:11:40');
INSERT INTO `sys_menu` VALUES ('136', '116', '查看', null, 'yeartrial:list,yeartrial:info', '2', null, '0', '1', '2017-04-07 18:12:16', '1', '2017-04-07 18:12:16');
INSERT INTO `sys_menu` VALUES ('137', '116', '修改', null, 'yeartrial:update', '2', null, '0', '1', '2017-04-07 18:12:37', '1', '2017-04-07 18:12:37');
INSERT INTO `sys_menu` VALUES ('138', '116', '删除', null, 'yeartrial:delete', '2', null, '0', '1', '2017-04-07 18:13:01', '1', '2017-04-07 18:13:01');
INSERT INTO `sys_menu` VALUES ('139', '117', '新增', null, 'insure:save', '2', null, '0', '1', '2017-04-07 18:13:52', '1', '2017-04-07 18:13:52');
INSERT INTO `sys_menu` VALUES ('140', '117', '查看', null, 'insure:list,insure:info,insdetail:list', '2', null, '0', '1', '2017-04-07 18:14:23', '1', '2017-04-25 17:28:42');
INSERT INTO `sys_menu` VALUES ('141', '117', '修改', null, 'insure:update', '2', null, '0', '1', '2017-04-07 18:14:47', '1', '2017-04-07 18:14:47');
INSERT INTO `sys_menu` VALUES ('142', '117', '删除', null, 'insure:delete', '2', null, '0', '1', '2017-04-07 18:15:08', '1', '2017-04-07 18:15:08');
INSERT INTO `sys_menu` VALUES ('143', '118', '新增', null, 'gas:save', '2', null, '0', '1', '2017-04-07 18:16:20', '1', '2017-04-07 18:16:20');
INSERT INTO `sys_menu` VALUES ('144', '118', '查看', null, 'gas:list,gas:info', '2', null, '0', '1', '2017-04-07 18:16:51', '1', '2017-04-07 18:16:51');
INSERT INTO `sys_menu` VALUES ('145', '118', '修改', null, 'gas:update', '2', null, '0', '1', '2017-04-07 18:17:20', '1', '2017-04-07 18:17:20');
INSERT INTO `sys_menu` VALUES ('146', '118', '删除', null, 'gas:delete', '2', null, '0', '1', '2017-04-07 18:17:45', '1', '2017-04-07 18:17:45');
INSERT INTO `sys_menu` VALUES ('147', '119', '新增', null, 'illega:save', '2', null, '0', '1', '2017-04-07 18:18:49', '1', '2017-04-07 18:18:49');
INSERT INTO `sys_menu` VALUES ('148', '119', '查看', null, 'illega:list,illega:info', '2', null, '0', '1', '2017-04-07 18:19:22', '1', '2017-04-07 18:19:22');
INSERT INTO `sys_menu` VALUES ('149', '119', '修改', null, 'illega:update', '2', null, '0', '1', '2017-04-07 18:19:45', '1', '2017-04-07 18:19:45');
INSERT INTO `sys_menu` VALUES ('150', '119', '删除', null, 'illega:delete', '2', null, '0', '1', '2017-04-07 18:20:07', '1', '2017-04-07 18:20:07');
INSERT INTO `sys_menu` VALUES ('151', '120', '新增', null, 'maintain:save', '2', null, '0', '1', '2017-04-07 18:20:52', '1', '2017-04-07 18:20:52');
INSERT INTO `sys_menu` VALUES ('152', '120', '查看', null, 'maintain:list,maintain:info,mtdetail:list', '2', null, '0', '1', '2017-04-07 18:21:23', '1', '2017-04-26 17:40:05');
INSERT INTO `sys_menu` VALUES ('153', '120', '修改', null, 'maintain:update', '2', null, '0', '1', '2017-04-07 18:21:47', '1', '2017-04-07 18:21:47');
INSERT INTO `sys_menu` VALUES ('154', '120', '删除', null, 'maintain:delete', '2', null, '0', '1', '2017-04-07 18:22:09', '1', '2017-04-07 18:22:09');
INSERT INTO `sys_menu` VALUES ('155', '121', '新增', null, 'repair:save', '2', null, '0', '1', '2017-04-07 18:23:02', '1', '2017-04-07 18:23:02');
INSERT INTO `sys_menu` VALUES ('156', '121', '查看', null, 'repair:list,repair:info,redetail:list', '2', null, '0', '1', '2017-04-07 18:23:33', '1', '2017-04-27 15:47:48');
INSERT INTO `sys_menu` VALUES ('157', '121', '修改', null, 'repair:update', '2', null, '0', '1', '2017-04-07 18:23:56', '1', '2017-04-07 18:23:56');
INSERT INTO `sys_menu` VALUES ('158', '121', '删除', null, 'repair:delete', '2', null, '0', '1', '2017-04-07 18:24:19', '1', '2017-04-07 18:24:19');
INSERT INTO `sys_menu` VALUES ('159', '122', '新增', null, 'cartype:save', '2', null, '0', '1', '2017-04-07 18:25:16', '1', '2017-04-07 18:25:16');
INSERT INTO `sys_menu` VALUES ('160', '122', '查看', null, 'cartype:list,cartype:info', '2', null, '0', '1', '2017-04-07 18:25:47', '1', '2017-04-07 18:25:47');
INSERT INTO `sys_menu` VALUES ('161', '122', '修改', null, 'cartype:update', '2', null, '0', '1', '2017-04-07 18:26:12', '1', '2017-04-07 18:26:12');
INSERT INTO `sys_menu` VALUES ('162', '122', '删除', null, 'cartype:delete', '2', null, '0', '1', '2017-04-07 18:26:36', '1', '2017-04-07 18:26:36');
INSERT INTO `sys_menu` VALUES ('163', '49', ' 微信关注者员工列表', 'wechatadmin/wechatadmin.html', null, '1', null, '6', '1', '2017-04-11 10:34:13', '1', '2017-04-11 10:34:13');
INSERT INTO `sys_menu` VALUES ('164', '163', '查看', null, 'wechatadmin:list,wechatadmin:info', '2', null, '0', '1', '2017-04-11 10:34:54', '1', '2017-04-11 10:34:54');
INSERT INTO `sys_menu` VALUES ('165', '163', '删除', null, 'wechatadmin:delete', '2', null, '0', '1', '2017-04-11 10:35:29', '1', '2017-04-11 10:35:29');
INSERT INTO `sys_menu` VALUES ('166', '0', '清扫保洁', null, null, '0', 'fa fa-recycle', '4', '1', '2017-04-14 16:12:31', '1', '2017-04-24 09:38:06');
INSERT INTO `sys_menu` VALUES ('167', '0', '呼叫中心', null, null, '0', 'fa fa-phone', '6', '1', '2017-04-18 10:08:07', '1', '2017-04-18 10:08:07');
INSERT INTO `sys_menu` VALUES ('168', '167', '坐席管理', 'callseats/callseats.html', 'callseats:list,callseats:info,callseats:save,callseats:update,callseats:delete', '1', null, '4', '1', '2017-04-18 10:11:47', '1', '2017-04-18 10:14:04');
INSERT INTO `sys_menu` VALUES ('169', '167', '工单查询', 'calljobsel/calljobsel.html', 'calljobsel:list,calljobsel:info,calljobsel:save,calljobsel:update,calljobsel:delete', '1', null, '2', '1', '2017-04-19 16:46:29', '1', '2017-04-19 16:49:57');
INSERT INTO `sys_menu` VALUES ('170', '167', '通话记录', 'call/callrecord.html', 'callrecord:list,callrecord:info,callrecord:save,callrecord:update,callrecord:delete', '1', null, '3', '1', '2017-04-20 11:13:29', '1', '2017-04-20 14:15:16');
INSERT INTO `sys_menu` VALUES ('171', '166', '班次管理', 'classes/classes.html', 'classes:list,classes:info,classes:save,classes:update,classes:delete', '1', null, '2', '1', '2017-04-24 14:57:18', '1', '2017-04-28 14:48:42');
INSERT INTO `sys_menu` VALUES ('174', '1', '工作流', 'workflow/workflow.html', null, '1', null, '9', '1', '2017-04-27 16:07:55', '1', '2017-05-25 20:54:36');
INSERT INTO `sys_menu` VALUES ('175', '174', '查看', null, 'workflow:list,workflow:info', '2', null, '0', '1', '2017-04-27 16:09:29', '1', '2017-04-27 16:09:29');
INSERT INTO `sys_menu` VALUES ('176', '174', '新增', null, 'workflow:save,sys:role:select', '2', null, '0', '1', '2017-04-27 16:13:27', '1', '2017-04-27 16:31:20');
INSERT INTO `sys_menu` VALUES ('177', '174', '修改', null, 'workflow:update,sys:role:select', '2', null, '0', '1', '2017-04-27 16:13:43', '1', '2017-04-27 16:31:30');
INSERT INTO `sys_menu` VALUES ('178', '174', '删除', null, 'workflow:delete', '2', null, '0', '1', '2017-04-27 16:13:57', '1', '2017-04-27 16:13:57');
INSERT INTO `sys_menu` VALUES ('179', '166', '网格管理', 'gridmng/cleangridmng.html', null, '1', null, '1', '1', '2017-04-28 14:49:56', '1', '2017-04-28 14:49:56');
INSERT INTO `sys_menu` VALUES ('180', '179', '查看', null, 'cleangridmng:list,cleangridmng:info', '2', null, '0', '1', '2017-04-28 14:50:46', '1', '2017-04-28 14:50:46');
INSERT INTO `sys_menu` VALUES ('181', '179', '新增', null, 'cleangridmng:save,department:selectForCmn', '2', null, '0', '1', '2017-04-28 14:51:03', '1', '2017-05-02 17:52:20');
INSERT INTO `sys_menu` VALUES ('182', '179', '修改', null, 'cleangridmng:update', '2', null, '0', '1', '2017-04-28 14:52:37', '1', '2017-04-28 14:52:37');
INSERT INTO `sys_menu` VALUES ('183', '179', '删除', null, 'cleangridmng:delete', '2', null, '0', '1', '2017-04-28 14:52:51', '1', '2017-04-28 14:52:51');
INSERT INTO `sys_menu` VALUES ('184', '179', '生成网格', null, 'cleangridmng:generateGrid', '2', null, '0', '1', '2017-05-02 15:06:47', '1', '2017-05-02 15:06:47');
INSERT INTO `sys_menu` VALUES ('185', '167', '外呼管理', 'calltel/calltel.html', 'calltel:list,calltel:info,calltel:save,calltel:update,calltel:delete', '1', null, '1', '1', '2017-05-03 16:40:43', '1', '2017-05-03 16:47:13');
INSERT INTO `sys_menu` VALUES ('187', '112', '事故管理', 'accident/accident.html', null, '1', null, '11', '1', '2017-05-08 11:10:04', '1', '2017-05-08 11:10:04');
INSERT INTO `sys_menu` VALUES ('188', '187', '新增', null, 'accident:save', '2', null, '0', '1', '2017-05-08 11:12:53', '1', '2017-05-08 11:12:53');
INSERT INTO `sys_menu` VALUES ('189', '187', '查看', null, 'accident:list,accident:info', '2', null, '0', '1', '2017-05-08 11:13:29', '1', '2017-05-08 11:13:29');
INSERT INTO `sys_menu` VALUES ('190', '187', '修改', null, 'accident:update', '2', null, '0', '1', '2017-05-08 11:13:51', '1', '2017-05-08 11:13:51');
INSERT INTO `sys_menu` VALUES ('191', '187', '删除', null, 'accident:delete', '2', null, '0', '1', '2017-05-08 11:14:16', '1', '2017-05-08 11:14:16');
INSERT INTO `sys_menu` VALUES ('192', '166', '工作记录', 'workrecord/workrecord.html', null, '1', null, '4', '1', '2017-05-08 19:04:49', '1', '2017-05-08 19:04:49');
INSERT INTO `sys_menu` VALUES ('193', '192', '查看', null, 'workrecord:list,workrecord:info', '2', null, '0', '1', '2017-05-08 19:05:15', '1', '2017-05-08 19:05:15');
INSERT INTO `sys_menu` VALUES ('194', '192', '新增', null, 'workrecord:save', '2', null, '0', '1', '2017-05-08 19:05:47', '1', '2017-05-08 19:05:47');
INSERT INTO `sys_menu` VALUES ('195', '192', '修改', null, 'workrecord:update', '2', null, '0', '1', '2017-05-08 19:06:02', '1', '2017-05-08 19:06:02');
INSERT INTO `sys_menu` VALUES ('196', '192', '删除', null, 'workrecord:delete', '2', null, '0', '1', '2017-05-08 19:06:15', '1', '2017-05-08 19:06:15');
INSERT INTO `sys_menu` VALUES ('197', '185', '来电弹屏', null, 'callpopsel:save,callpopsel:calllist,callpopsel:callpop,callpopsel:callname,callpopsel:source,callpopsel:calltype', '2', null, '0', '1', '2017-05-09 15:59:02', '1', '2017-05-12 11:19:04');
INSERT INTO `sys_menu` VALUES ('198', '166', '质量督导', 'qltsu/qltsu.html', null, '1', null, '9', '1', '2017-05-10 14:24:13', '1', '2017-05-10 14:24:13');
INSERT INTO `sys_menu` VALUES ('199', '198', '新增', null, 'qltsu:save', '2', null, '0', '1', '2017-05-10 14:25:36', '1', '2017-05-10 14:25:36');
INSERT INTO `sys_menu` VALUES ('200', '198', '查看', null, 'qltsu:info,qltsu:list', '2', null, '0', '1', '2017-05-10 14:26:07', '1', '2017-05-10 14:26:07');
INSERT INTO `sys_menu` VALUES ('201', '198', '修改', null, 'qltsu:update', '2', null, '0', '1', '2017-05-10 14:26:32', '1', '2017-05-10 14:26:32');
INSERT INTO `sys_menu` VALUES ('202', '198', '删除', null, 'qltsu:delete', '2', null, '0', '1', '2017-05-10 14:26:53', '1', '2017-05-10 14:26:53');
INSERT INTO `sys_menu` VALUES ('203', '0', '指挥中心', null, null, '0', 'fa fa-binoculars', '7', '1', '2017-05-10 14:52:05', '1', '2017-05-10 14:55:39');
INSERT INTO `sys_menu` VALUES ('204', '203', '指挥调度', 'event/cmddisp.html', null, '1', null, '2', '1', '2017-05-10 14:52:49', '1', '2017-06-08 16:27:17');
INSERT INTO `sys_menu` VALUES ('205', '166', '扣分审批', 'qltsuaauditee/qltsuaauditee.html', null, '1', null, '8', '1', '2017-05-11 10:52:02', '1', '2017-05-11 10:52:02');
INSERT INTO `sys_menu` VALUES ('206', '205', '审核', null, 'qltsuaauditee:auditing,qltsuaauditee:list,qltsuaauditee:info', '2', null, '0', '1', '2017-05-11 10:55:13', '1', '2017-05-11 11:03:54');
INSERT INTO `sys_menu` VALUES ('207', '205', '查看', null, 'qltsuaauditee:list', '2', null, '0', '1', '2017-05-11 11:04:40', '1', '2017-05-11 11:04:40');
INSERT INTO `sys_menu` VALUES ('208', '166', '考勤申报', 'atdmanage/atdmanage.html', null, '1', null, '5', '1', '2017-05-11 19:17:48', '1', '2017-06-06 15:43:42');
INSERT INTO `sys_menu` VALUES ('209', '208', '新增', null, 'atdmanage:save', '2', null, '0', '1', '2017-05-11 19:19:06', '1', '2017-05-11 19:19:06');
INSERT INTO `sys_menu` VALUES ('210', '208', '查看', null, 'atdmanage:info,atdmanage:list', '2', null, '0', '1', '2017-05-11 19:20:44', '1', '2017-05-11 19:20:44');
INSERT INTO `sys_menu` VALUES ('211', '208', '修改', null, 'atdmanage:update', '2', null, '0', '1', '2017-05-11 19:21:23', '1', '2017-05-11 19:21:23');
INSERT INTO `sys_menu` VALUES ('212', '208', '删除', null, 'atdmanage:delete', '2', null, '0', '1', '2017-05-11 19:23:08', '1', '2017-05-11 19:23:08');
INSERT INTO `sys_menu` VALUES ('213', '166', '事件管理', null, null, '0', 'fa fa-calendar', '10', '1', '2017-05-12 19:46:10', '1', '2017-05-12 19:49:47');
INSERT INTO `sys_menu` VALUES ('214', '213', '待办事件', 'event/event_upc.html', null, '1', null, '1', '1', '2017-05-12 19:49:11', '1', '2017-06-08 16:26:41');
INSERT INTO `sys_menu` VALUES ('215', '213', '已处理事件', 'event/event_hbp.html', null, '1', null, '2', '1', '2017-05-12 20:00:09', '1', '2017-06-08 16:26:45');
INSERT INTO `sys_menu` VALUES ('216', '213', '复核待办事件', 'event/event_ctdi.html', null, '1', null, '3', '1', '2017-05-12 20:00:39', '1', '2017-06-08 16:26:49');
INSERT INTO `sys_menu` VALUES ('218', '213', '已结案', 'event/event_fulfil.html', null, '1', null, '4', '1', '2017-05-12 20:02:40', '1', '2017-06-08 16:26:54');
INSERT INTO `sys_menu` VALUES ('219', '214', '查看', null, 'event:upcList,event:upcinfo', '2', null, '0', '1', '2017-05-12 20:06:59', '1', '2017-05-12 20:06:59');
INSERT INTO `sys_menu` VALUES ('220', '215', '查看', null, 'event:hbpList,event:hbpinfo', '2', null, '0', '1', '2017-05-12 20:09:06', '1', '2017-05-12 20:09:06');
INSERT INTO `sys_menu` VALUES ('221', '216', '查看', null, 'event:ctdiList,event:ctdiinfo', '2', null, '0', '1', '2017-05-12 20:10:29', '1', '2017-05-12 20:10:29');
INSERT INTO `sys_menu` VALUES ('223', '218', '查看', null, 'event:fulfilList,event:fulfilinfo', '2', null, '0', '1', '2017-05-12 20:12:25', '1', '2017-05-12 20:12:25');
INSERT INTO `sys_menu` VALUES ('224', '166', '考勤审核', 'atdmanageauditee/atdmanageauditee.html', null, '1', null, '6', '1', '2017-05-15 11:15:36', '1', '2017-05-15 11:15:36');
INSERT INTO `sys_menu` VALUES ('225', '224', '审核', null, 'atdmanageauditee:auditing,atdmanageauditee:list,atdmanageauditee:info', '2', null, '0', '1', '2017-05-15 11:20:42', '1', '2017-05-15 11:20:42');
INSERT INTO `sys_menu` VALUES ('226', '166', '出勤查询', 'atdrecord/atdrecord.html', null, '1', null, '7', '1', '2017-05-16 10:26:45', '1', '2017-05-16 10:26:45');
INSERT INTO `sys_menu` VALUES ('227', '226', '查看', null, 'atdrecord:list,atdrecord:info', '2', null, '0', '1', '2017-05-16 10:28:13', '1', '2017-05-16 10:28:13');
INSERT INTO `sys_menu` VALUES ('231', '271', '新增计划', '', 'operationplanpop:ban,operationplanpop:paiban', '2', null, '0', '1', '2017-05-17 11:39:05', '1', '2017-06-09 18:40:05');
INSERT INTO `sys_menu` VALUES ('232', '204', '查看', null, 'event:list', '2', null, '0', '1', '2017-05-17 11:48:47', '1', '2017-05-17 11:48:47');
INSERT INTO `sys_menu` VALUES ('233', '166', '统计分析', null, null, '0', 'fa fa-area-chart', '12', '1', '2017-05-17 20:14:35', '1', '2017-05-17 20:14:35');
INSERT INTO `sys_menu` VALUES ('234', '233', '事件统计', 'eventstatistics/eventstatistics.html', null, '1', null, '2', '1', '2017-05-17 20:19:27', '1', '2017-05-23 19:36:21');
INSERT INTO `sys_menu` VALUES ('235', '214', '撤回', null, 'event:recall', '2', null, '0', '1', '2017-05-18 20:31:20', '1', '2017-05-19 14:58:14');
INSERT INTO `sys_menu` VALUES ('236', '214', '处理', null, 'event:treat', '2', null, '0', '1', '2017-05-18 20:31:42', '1', '2017-05-19 14:58:23');
INSERT INTO `sys_menu` VALUES ('237', '233', '工作记录', 'workrecordstatistics/workrecordstatistics.html', null, '1', null, '3', '1', '2017-05-19 09:47:57', '1', '2017-05-23 19:36:27');
INSERT INTO `sys_menu` VALUES ('238', '233', '扣分统计', 'scorestatistics/scorestatistics.html', null, '1', null, '4', '1', '2017-05-19 10:12:17', '1', '2017-05-23 19:36:34');
INSERT INTO `sys_menu` VALUES ('239', '233', '考勤统计', 'atdstatistics/atdstatistics.html', null, '1', null, '5', '1', '2017-05-19 10:47:28', '1', '2017-05-23 19:36:39');
INSERT INTO `sys_menu` VALUES ('240', '233', '工作状态', 'workstatestatistics/workstatestatistics.html', null, '1', null, '6', '1', '2017-05-19 11:16:17', '1', '2017-05-23 19:36:45');
INSERT INTO `sys_menu` VALUES ('241', '233', '车辆统计', 'carstatistics/carstatistics.html', null, '1', null, '7', '1', '2017-05-19 11:27:20', '1', '2017-05-23 19:36:51');
INSERT INTO `sys_menu` VALUES ('242', '214', '提交', null, 'event:upcsave', '2', null, '0', '1', '2017-05-19 14:58:42', '1', '2017-05-19 14:58:42');
INSERT INTO `sys_menu` VALUES ('243', '1', '设备管理', null, null, '0', 'fa fa-laptop', '11', '1', '2017-05-19 15:29:51', '1', '2017-05-25 20:54:44');
INSERT INTO `sys_menu` VALUES ('244', '243', '人员终端', 'personterminal/personterminal.html', null, '1', null, '1', '1', '2017-05-19 15:30:38', '1', '2017-05-19 15:30:38');
INSERT INTO `sys_menu` VALUES ('245', '244', '新增', null, 'personterminal:save', '2', null, '0', '1', '2017-05-19 15:31:28', '1', '2017-05-19 15:31:28');
INSERT INTO `sys_menu` VALUES ('246', '244', '查看', null, 'personterminal:info,personterminal:list', '2', null, '0', '1', '2017-05-19 15:32:02', '1', '2017-05-19 15:32:02');
INSERT INTO `sys_menu` VALUES ('247', '244', '修改', null, 'personterminal:update', '2', null, '0', '1', '2017-05-19 15:32:26', '1', '2017-05-19 15:32:26');
INSERT INTO `sys_menu` VALUES ('248', '244', '删除', null, 'personterminal:delete', '2', null, '0', '1', '2017-05-19 15:32:45', '1', '2017-05-19 15:32:45');
INSERT INTO `sys_menu` VALUES ('249', '215', '查看', null, 'event:hbpList,event:hbpinfo', '2', null, '0', '1', '2017-05-19 15:54:43', '1', '2017-05-19 15:54:43');
INSERT INTO `sys_menu` VALUES ('250', '243', '车载终端', 'carterminal/carterminal.html', null, '1', null, '2', '1', '2017-05-19 16:40:36', '1', '2017-05-19 16:40:36');
INSERT INTO `sys_menu` VALUES ('251', '250', '新增', null, 'carterminal:save', '2', null, '0', '1', '2017-05-19 16:54:11', '1', '2017-05-19 16:54:11');
INSERT INTO `sys_menu` VALUES ('252', '250', '查看', null, 'carterminal:info,carterminal:list', '2', null, '0', '1', '2017-05-19 16:54:47', '1', '2017-05-19 16:54:47');
INSERT INTO `sys_menu` VALUES ('253', '250', '修改', null, 'carterminal:update', '2', null, '0', '1', '2017-05-19 16:55:12', '1', '2017-05-19 16:55:12');
INSERT INTO `sys_menu` VALUES ('254', '250', '删除', null, 'carterminal:delete', '2', null, '0', '1', '2017-05-19 16:55:39', '1', '2017-05-19 16:55:39');
INSERT INTO `sys_menu` VALUES ('255', '243', '监控设备', 'monitordev/monitordev.html', null, '1', null, '3', '1', '2017-05-20 10:22:00', '1', '2017-05-20 10:22:00');
INSERT INTO `sys_menu` VALUES ('256', '255', '新增', null, 'monitordev:save', '2', null, '0', '1', '2017-05-20 10:22:56', '1', '2017-05-20 10:22:56');
INSERT INTO `sys_menu` VALUES ('257', '255', '查看', null, 'monitordev:info,monitordev:list', '2', null, '0', '1', '2017-05-20 10:23:32', '1', '2017-05-20 10:23:32');
INSERT INTO `sys_menu` VALUES ('258', '255', '修改', null, 'monitordev:update', '2', null, '0', '1', '2017-05-20 10:24:01', '1', '2017-05-20 10:24:01');
INSERT INTO `sys_menu` VALUES ('259', '255', '删除', null, 'monitordev:delete', '2', null, '0', '1', '2017-05-20 10:24:33', '1', '2017-05-20 10:24:33');
INSERT INTO `sys_menu` VALUES ('260', '1', '反馈管理', 'feedback/feedback.html', null, '1', null, '10', '1', '2017-05-23 16:53:20', '1', '2017-05-25 20:54:50');
INSERT INTO `sys_menu` VALUES ('261', '260', '查看', null, 'feedback:list,feedback:info', '2', null, '0', '1', '2017-05-23 16:53:52', '1', '2017-05-23 16:53:52');
INSERT INTO `sys_menu` VALUES ('262', '260', '新增', null, 'feedback:save', '2', null, '0', '1', '2017-05-23 16:54:14', '1', '2017-05-23 16:54:14');
INSERT INTO `sys_menu` VALUES ('263', '260', '修改', null, 'feedback:update', '2', null, '0', '1', '2017-05-23 16:54:29', '1', '2017-05-23 16:54:29');
INSERT INTO `sys_menu` VALUES ('264', '260', '删除', null, 'feedback:delete', '2', null, '0', '1', '2017-05-23 16:54:45', '1', '2017-05-23 16:54:45');
INSERT INTO `sys_menu` VALUES ('265', '203', '历史调度', null, null, '0', 'fa fa-history', '3', '1', '2017-05-23 19:21:31', '1', '2017-06-08 16:27:26');
INSERT INTO `sys_menu` VALUES ('266', '265', '历史调度(事件派单)', 'event/cmddisp_history.html', null, '1', null, '1', '1', '2017-05-23 19:22:10', '1', '2017-05-23 19:39:04');
INSERT INTO `sys_menu` VALUES ('267', '265', '历史调度(复核派单)', 'event/cmddisp_history_re.html', null, '1', null, '2', '1', '2017-05-23 19:22:58', '1', '2017-05-23 19:39:16');
INSERT INTO `sys_menu` VALUES ('268', '266', '查看', null, 'event:historyList', '2', null, '0', '1', '2017-05-23 19:31:28', '1', '2017-05-23 19:40:59');
INSERT INTO `sys_menu` VALUES ('269', '267', '查看', null, 'event:list', '2', null, '0', '1', '2017-05-23 19:31:41', '1', '2017-05-23 19:31:41');
INSERT INTO `sys_menu` VALUES ('270', '266', '改派', null, 'event:modify', '2', null, '0', '1', '2017-05-25 16:41:40', '1', '2017-05-25 16:41:40');
INSERT INTO `sys_menu` VALUES ('271', '166', '作业计划(按月排班)', 'operationplan/operationplan.html', 'operationplan:save,operationplan:paiban,operationplan:ban,operationplan:list,operationplan:info,operationplan:delete,operationplan:update,operationplan:month,operationplan:copy,operationplan:datalist,operationplan:basecode,operationplan:judegtime', '1', null, '3', '1', '2017-05-25 22:16:25', '1', '2017-07-20 17:06:26');
INSERT INTO `sys_menu` VALUES ('272', '0', '费用管理', null, null, '0', 'fa fa-money', '15', '1', '2017-05-26 09:25:13', '1', '2017-05-26 09:25:13');
INSERT INTO `sys_menu` VALUES ('273', '272', '预算申报', 'budget/budget.html', null, '1', null, '0', '1', '2017-05-26 09:26:25', '1', '2017-05-26 09:26:25');
INSERT INTO `sys_menu` VALUES ('274', '273', '新增', null, 'budget:save', '2', null, '0', '1', '2017-05-26 09:29:49', '1', '2017-05-26 09:29:49');
INSERT INTO `sys_menu` VALUES ('275', '273', '查看', null, 'budget:list,budget:info', '2', null, '0', '1', '2017-05-26 09:31:25', '1', '2017-05-26 09:31:25');
INSERT INTO `sys_menu` VALUES ('276', '272', '预算审核', 'budgetaudit/budgetaudit.html', null, '1', null, '2', '1', '2017-06-01 14:28:31', '1', '2017-06-01 14:28:31');
INSERT INTO `sys_menu` VALUES ('277', '276', '查看', null, 'budget:list,budget:info', '2', null, '0', '1', '2017-06-01 14:29:39', '1', '2017-06-01 14:29:39');
INSERT INTO `sys_menu` VALUES ('278', '166', '预警管理', null, null, '0', 'fa fa-bell', '11', '1', '2017-06-02 17:55:23', '1', '2017-06-02 17:55:23');
INSERT INTO `sys_menu` VALUES ('279', '278', '基础设置', 'basissetting/basissetting.html', 'basissetting:list,basissetting:info,basissetting:save,basissetting:update,basissetting:delete,basissetting:getid', '1', null, '0', '1', '2017-06-02 17:57:11', '1', '2017-06-05 15:43:48');
INSERT INTO `sys_menu` VALUES ('281', '272', '费用记录', 'costrecord/costrecord.html', null, '1', null, '3', '1', '2017-06-07 10:43:21', '1', '2017-06-07 10:43:21');
INSERT INTO `sys_menu` VALUES ('282', '281', '新增', null, 'costrecord:save,bditem:list', '2', null, '0', '1', '2017-06-07 10:44:35', '1', '2017-06-07 10:44:35');
INSERT INTO `sys_menu` VALUES ('283', '281', '查看', null, 'costrecord:info,costrecord:list', '2', null, '0', '1', '2017-06-07 10:47:33', '1', '2017-06-07 10:47:33');
INSERT INTO `sys_menu` VALUES ('284', '281', '修改', null, 'costrecord:update', '2', null, '0', '1', '2017-06-07 10:47:55', '1', '2017-06-07 10:47:55');
INSERT INTO `sys_menu` VALUES ('285', '281', '删除', null, 'costrecord:delete', '2', null, '0', '1', '2017-06-07 10:48:12', '1', '2017-06-07 10:48:12');
INSERT INTO `sys_menu` VALUES ('286', '272', '统计分析', 'coststatistics/coststatistics.html', null, '1', null, '4', '1', '2017-06-07 17:52:57', '1', '2017-06-07 17:52:57');
INSERT INTO `sys_menu` VALUES ('287', '203', '新增事件', 'event/event_add.html', null, '1', null, '1', '1', '2017-06-08 16:06:21', '1', '2017-06-08 16:27:07');
INSERT INTO `sys_menu` VALUES ('288', '287', '新增', null, 'event:add', '2', null, '0', '1', '2017-06-08 16:08:56', '1', '2017-06-09 18:38:32');
INSERT INTO `sys_menu` VALUES ('289', '287', '添加', null, 'citizen:getUserTree,department:selectForCmn,party:getPartyTreet', '2', null, '0', '1', '2017-06-09 18:35:47', '1', '2017-06-09 18:35:47');
INSERT INTO `sys_menu` VALUES ('290', '0', '垃圾清运', null, null, '0', 'fa fa-trash-o', '8', '1', '2017-06-21 10:59:05', '1', '2017-06-21 10:59:05');
INSERT INTO `sys_menu` VALUES ('291', '290', '清运作业', null, null, '0', 'fa fa-truck', '10', '1', '2017-06-21 11:16:56', '1', '2017-06-21 11:16:56');
INSERT INTO `sys_menu` VALUES ('292', '291', '垃圾桶标记', 'trashtab/trashtab.html', null, '1', null, '1', '1', '2017-06-21 11:24:43', '1', '2017-06-21 11:24:43');
INSERT INTO `sys_menu` VALUES ('293', '292', '新增', null, 'trashtab:save', '2', null, '0', '1', '2017-06-21 11:30:47', '1', '2017-06-21 11:30:47');
INSERT INTO `sys_menu` VALUES ('294', '292', '查看', null, 'trashtab:info,trashtab:list', '2', null, '0', '1', '2017-06-21 11:31:41', '1', '2017-06-21 11:31:41');
INSERT INTO `sys_menu` VALUES ('295', '1', '中心点设置', 'centpoint/centpoint.html', null, '1', null, '12', '1', '2017-06-24 15:50:19', '1', '2017-06-24 15:50:19');
INSERT INTO `sys_menu` VALUES ('296', '295', '查看', null, 'centpoint:list,centpoint:info', '2', null, '6', '1', '2017-06-24 15:50:19', '1', '2017-06-24 15:50:19');
INSERT INTO `sys_menu` VALUES ('297', '295', '新增', null, 'centpoint:save', '2', null, '6', '1', '2017-06-24 15:50:19', '1', '2017-06-24 15:50:19');
INSERT INTO `sys_menu` VALUES ('298', '295', '修改', null, 'centpoint:update', '2', null, '6', '1', '2017-06-24 15:50:19', '1', '2017-06-24 15:50:19');
INSERT INTO `sys_menu` VALUES ('299', '295', '删除', null, 'centpoint:delete', '2', null, '6', '1', '2017-06-24 15:50:19', '1', '2017-06-24 15:50:19');
INSERT INTO `sys_menu` VALUES ('301', '1', '在线工具', 'http://www.bejson.com/', null, '1', null, '13', '1', '2017-06-27 15:29:02', '1', '2017-06-27 15:30:45');
INSERT INTO `sys_menu` VALUES ('305', '166', '作业计划(按天排班)', 'operationplandata/operationplandata.html', null, '1', null, '5', '1', '2017-07-20 11:18:16', '1', '2017-07-20 19:23:11');
INSERT INTO `sys_menu` VALUES ('306', '305', '查看', null, 'operationplandata:list,operationplandata:info', '2', null, '6', '1', '2017-07-20 11:18:16', '1', '2017-07-20 11:18:16');
INSERT INTO `sys_menu` VALUES ('307', '305', '新增', null, 'operationplandata:save', '2', null, '6', '1', '2017-07-20 11:18:16', '1', '2017-07-20 11:18:16');
INSERT INTO `sys_menu` VALUES ('308', '305', '修改', null, 'operationplandata:update', '2', null, '6', '1', '2017-07-20 11:18:16', '1', '2017-07-20 11:18:16');
INSERT INTO `sys_menu` VALUES ('309', '305', '删除', null, 'operationplandata:delete', '2', null, '6', '1', '2017-07-20 11:18:16', '1', '2017-07-20 11:18:16');
INSERT INTO `sys_menu` VALUES ('310', '305', '排班', null, 'operationplandata:paiban,operationplandata:day', '2', null, '6', '1', '2017-07-20 19:48:15', '1', '2017-07-20 19:48:18');
INSERT INTO `sys_menu` VALUES ('312', '316', '查看', null, 'jobwarning:list,jobwarning:info', '2', null, '6', '1', '2017-07-21 09:52:55', '1', '2017-07-21 09:52:55');
INSERT INTO `sys_menu` VALUES ('313', '316', '新增', null, 'jobwarning:save', '2', null, '6', '1', '2017-07-21 09:52:55', '1', '2017-07-21 09:52:55');
INSERT INTO `sys_menu` VALUES ('314', '316', '修改', null, 'jobwarning:update', '2', null, '6', '1', '2017-07-21 09:52:55', '1', '2017-07-21 09:52:55');
INSERT INTO `sys_menu` VALUES ('315', '316', '删除', null, 'jobwarning:delete', '2', null, '6', '1', '2017-07-21 09:52:55', '1', '2017-07-21 09:52:55');
INSERT INTO `sys_menu` VALUES ('316', '278', '工作预警', 'jobwarning/jobwarning.html', null, '1', null, '0', '1', '2017-07-21 17:59:45', '1', '2017-07-21 17:59:45');
INSERT INTO `sys_menu` VALUES ('317', '273', '调整', null, 'budget:adjust', '2', null, '0', '1', '2017-07-24 15:50:32', '1', '2017-07-24 15:50:32');
INSERT INTO `sys_menu` VALUES ('318', '276', '审批', null, 'budget:doAudit', '2', null, '0', '1', '2017-07-24 16:04:46', '1', '2017-07-24 16:04:46');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级角色',
  `dept` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `rmk` varchar(100) DEFAULT NULL COMMENT '备注',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('12', '清洁工', '0', '22', null, '1', '2017-06-29 12:24:36', '1', '2017-07-24 11:50:59');
INSERT INTO `sys_role` VALUES ('13', '项目经理', '0', '16', null, '1', '2017-07-01 11:33:16', '1', '2017-07-01 11:33:16');
INSERT INTO `sys_role` VALUES ('14', '财务总监', '0', '24', '总公司财务总监，用于费用审核', '1', '2017-07-24 11:58:22', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role` VALUES ('15', '项目部主管', '0', '16', '霍山项目部部门主管，用于费用审批', '1', '2017-07-24 16:11:30', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role` VALUES ('16', '事业部主管', '0', '12', '事业一部主管，用于费用审批', '1', '2017-07-24 16:12:20', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role` VALUES ('17', '副总', '0', '24', '公司副总，用于费用审批', '1', '2017-07-24 16:14:23', '1', '2017-07-25 11:09:45');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menuid` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('28', '15', '49', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('29', '15', '2', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('30', '15', '15', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('31', '15', '272', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('32', '15', '276', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('33', '15', '277', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('34', '15', '318', '1', '2017-07-25 11:02:09', '1', '2017-07-25 11:02:09');
INSERT INTO `sys_role_menu` VALUES ('35', '14', '49', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('36', '14', '2', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('37', '14', '15', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('38', '14', '272', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('39', '14', '276', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('40', '14', '277', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('41', '14', '318', '1', '2017-07-25 11:09:11', '1', '2017-07-25 11:09:11');
INSERT INTO `sys_role_menu` VALUES ('42', '16', '49', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('43', '16', '2', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('44', '16', '15', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('45', '16', '272', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('46', '16', '276', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('47', '16', '277', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('48', '16', '318', '1', '2017-07-25 11:09:35', '1', '2017-07-25 11:09:35');
INSERT INTO `sys_role_menu` VALUES ('49', '17', '49', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');
INSERT INTO `sys_role_menu` VALUES ('50', '17', '2', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');
INSERT INTO `sys_role_menu` VALUES ('51', '17', '15', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');
INSERT INTO `sys_role_menu` VALUES ('52', '17', '272', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');
INSERT INTO `sys_role_menu` VALUES ('53', '17', '276', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');
INSERT INTO `sys_role_menu` VALUES ('54', '17', '277', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');
INSERT INTO `sys_role_menu` VALUES ('55', '17', '318', '1', '2017-07-25 11:09:45', '1', '2017-07-25 11:09:45');

-- ----------------------------
-- Table structure for sys_seriano
-- ----------------------------
DROP TABLE IF EXISTS `sys_seriano`;
CREATE TABLE `sys_seriano` (
  `type` varchar(16) NOT NULL,
  `year` varchar(32) NOT NULL,
  `keyone` varchar(255) NOT NULL,
  `no` decimal(8,0) NOT NULL,
  PRIMARY KEY (`type`,`year`,`keyone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='序号';

-- ----------------------------
-- Records of sys_seriano
-- ----------------------------
INSERT INTO `sys_seriano` VALUES ('case.no', '201703', 'NO.', '85');
INSERT INTO `sys_seriano` VALUES ('case.no', '201704', 'NO.', '4');
INSERT INTO `sys_seriano` VALUES ('event.no', '201705', 'NO.', '13');
INSERT INTO `sys_seriano` VALUES ('event.no', '201706', 'NO.', '35');
INSERT INTO `sys_seriano` VALUES ('event.no', '201707', 'NO.', '3');

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Records of sys_token
-- ----------------------------
INSERT INTO `sys_token` VALUES ('1', '414062ba79724493beebc183c8b0fd78', '2017-07-28 15:48:45', '2017-07-21 15:48:45');
INSERT INTO `sys_token` VALUES ('24', '317304f41ddf4bd7acbefbc0a0e1e554', '2017-07-28 16:35:40', '2017-07-21 16:35:40');
INSERT INTO `sys_token` VALUES ('25', 'a5d981d390944d409771b56734fdd309', '2017-07-31 03:25:11', '2017-07-24 03:25:11');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `wechatid` varchar(50) DEFAULT NULL COMMENT '微信id',
  `realname` varchar(50) NOT NULL COMMENT '真实姓名',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `cardno` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `pwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` char(8) NOT NULL DEFAULT '1111' COMMENT '密码盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqname` (`name`) USING BTREE,
  UNIQUE KEY `uniqmobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'oY9cUv1SSvT33x4s7zDtfDJwvR0M', '超级管理员', 'admin', null, '6312047701f2ed0dea95e46f079c4dd58e1cf999ac43c96251a916c58fb1c011', 'Yk56', '404221903@qq.com', '13113113111', '1', '1', '2016-11-11 11:11:11', '1', '2017-07-24 18:23:30');
INSERT INTO `sys_user` VALUES ('8', null, '何新福', 'hxfu', '44321659685956232', '0a009c377dd40668cda0d9b3bab12f45ce5be13be20ecef572e01e2ad05b50cc', 'hE1j', '265654623@qq.com', '17084933607', '1', '1', '2017-06-14 18:34:16', '1', '2017-07-24 18:23:59');
INSERT INTO `sys_user` VALUES ('9', null, '钟建华', 'zjhua', '44015596892561545456', '437d84d17303fc2f5c7368c3024ca72c2d15a240d901579321aa5247e0d0aafe', 'o0yX', '268926@qq.com', '13798432274', '1', '1', '2017-06-14 19:02:11', '1', '2017-06-19 17:54:02');
INSERT INTO `sys_user` VALUES ('10', null, '古远平', 'gyp', '440596862341424661', 'e8ba8830972b5245d4005d8f235fabd8e3c53a965aa38832230a8f2648f6fadf', '1rRk', '16549261@qq.com', '13823512992', '1', '1', '2017-06-14 19:05:42', '1', '2017-06-14 19:05:42');
INSERT INTO `sys_user` VALUES ('11', null, '张涛', 'ztao', '4502528946132165', 'afb295a0ccef998ccef966321abcc3449203a8ab388a2bff4da80f6ecc5a14d0', 'u5KW', '16546459@qq.com', '15339625200', '1', '1', '2017-06-14 19:06:52', '1', '2017-06-14 19:06:52');
INSERT INTO `sys_user` VALUES ('12', null, '包先勤', 'bxqin', '430598521564665121', 'f55e0041be58804f2d4712eda2030d37c49c861fa5c12f361b427547f512571e', '8Ebt', '9532265@qq.com', '18063027345', '1', '1', '2017-06-14 19:09:38', '1', '2017-06-14 19:09:38');
INSERT INTO `sys_user` VALUES ('13', null, '陈宗胜', 'czsheng', '45042119880229465', 'c6f7d7462c7eaf9fbad19a6a6eb1f35e318472879ea78afc197fa67350020cda', '9Hix', '5648944@qq.com', '15156956732', '1', '1', '2017-06-14 19:11:17', '1', '2017-06-29 10:10:36');
INSERT INTO `sys_user` VALUES ('14', null, '倪克淑', 'nkshu', '440356265985645', 'f16c5623abc5cd91e19056561c701b01ddff9e2ca9dd11280f1f45519db36907', 'So92', '588761231@qq.com', '13470858719', '1', '1', '2017-06-14 19:12:54', '1', '2017-06-29 10:10:21');
INSERT INTO `sys_user` VALUES ('15', null, '张小三', 'hshws', '4504236544554548', '03bdf8ad8253594f98c4cbdba93aaaf1d0be543d0736ec612f7b73dfb9219a40', '2USw', '54984123@qq.com', '13411780155', '1', '1', '2017-06-14 19:14:27', '1', '2017-06-19 16:14:14');
INSERT INTO `sys_user` VALUES ('16', null, '项阳', 'xyang', '450546562589565265', '893d1fc0a12ef688afd32918dbf920072ab8a7fc0a1a1867b1eb332b5be9d2c1', '6bG5', '294716@qq.com', '13965462881', '1', '1', '2017-06-14 19:15:52', '1', '2017-06-27 18:36:48');
INSERT INTO `sys_user` VALUES ('17', null, '方燕', 'fyan', '468985252321231541', '23d49dbe974073314309752e5f21d85f37c81f1b7d13cffa243b96173473cc9a', 'G4tY', '4984612@qq.com', '13411780156', '1', '1', '2017-06-14 19:17:01', '1', '2017-06-14 19:17:01');
INSERT INTO `sys_user` VALUES ('18', null, '王恩生', 'wesheng', '456623569589565', 'd66c0f991c1cdfb98fb7b3b4f0f824e960455cbe0a2437e61b3658810430d67d', '5VYl', '144874@qq.com', '17084931241', '1', '1', '2017-06-14 19:20:21', '1', '2017-06-14 19:20:21');
INSERT INTO `sys_user` VALUES ('19', null, '鲍传霞', 'bcxia', '45046895262156461', 'bb41e98902de3dfe6cb760ce706b05c244105c08213a44c1d1fa5a48e706e4d9', '7ZDv', '489746213@qq.com', '17084933250', '1', '1', '2017-06-14 19:21:46', '1', '2017-06-14 19:21:46');
INSERT INTO `sys_user` VALUES ('20', null, '李衍武', 'lxwu', '45042119885925646', 'c965635ffbbb3db5621afacabbfc152bda9175ffc4151867ca0911e2162ef91a', 'y8LY', '589322@qq.com', '17084936181', '1', '1', '2017-06-14 19:44:17', '1', '2017-07-24 18:33:39');
INSERT INTO `sys_user` VALUES ('21', null, '何祥林', 'hxlin', '42125445184123132', 'aa668e8c8408955fd6ad5ca25dfef4649738244f705757d315635062dc3b6fad', 'rB7O', '156494@qq.com', '17084933755', '1', '1', '2017-06-14 19:57:04', '1', '2017-06-14 19:57:04');
INSERT INTO `sys_user` VALUES ('22', null, '何泽', 'hzei', '4522526594541256456', 'b432200aae77863430a6ccda6de0bdad6d3f8fb7ecfddadb40b85b7a09b4c3b9', '7VdK', '89456312@qq.com', '18675721612', '1', '1', '2017-06-14 20:09:06', '1', '2017-06-20 09:30:52');
INSERT INTO `sys_user` VALUES ('24', 'oY9cUv1xT_DWh0p1QJ2M6fZ8oSOQ', '测试', 'AAA', '111', '4b5b34743bde724995529481e01ce71d4b1fd1e26ecfbb4835dda3167d6a3de4', 'iu1J', 'AAA@AAA.com', '15626489222', '1', '1', '2017-06-16 09:42:32', '1', '2017-07-24 11:59:11');
INSERT INTO `sys_user` VALUES ('25', 'oY9cUvy4H5UST-i18490B1utDRjc', '蒋松', 'jiangs', null, '53e108549b39713db4b25aab8522934d50b0d82855ec7a9bf84d32b50c929021', 'qQ3q', 'jiangs@scs.com', '13113113113', '1', '1', '2017-06-19 17:16:05', '1', '2017-06-19 17:16:05');
INSERT INTO `sys_user` VALUES ('26', null, '文清', 'wenq', '500456123456123', '7e1b60b5eb1b48618d5a6a52c5c04ebc9e17b3f539c382a63139bbe24055ced1', '3jQS', 'wenq@qq.com', '13714253669', '1', '1', '2017-06-28 10:02:24', '1', '2017-06-29 12:25:26');
INSERT INTO `sys_user` VALUES ('28', null, '测试费用之项目部主管', 'aaaa', 'a', '5ffac853e2d59faa3443a2c69f4d1ea70b74e28942b7098f5d832bfb0c2a7932', 'xMz8', 'a@a.com', '15623568526', '1', '1', '2017-07-24 17:54:13', '1', '2017-07-24 17:55:55');
INSERT INTO `sys_user` VALUES ('29', null, '测试费用之事业部主管', 'bbb', 'a', '8f5e081d2eb21d73f7b97aca4c75832986744a5a9a68d739ab89ed4ba1afd2cf', '4KjL', 'b@b.com', '15263415263', '1', '1', '2017-07-24 17:54:57', '1', '2017-07-24 17:56:26');
INSERT INTO `sys_user` VALUES ('30', null, '测试费用之财务总监', 'cccc', 'c', 'ffdc2d566e239e2ebf6431fd4762514555d2118fca27123f87a36d3f7cae349f', 'pIO8', 'c@c.xom', '14252635263', '1', '1', '2017-07-24 17:57:48', '1', '2017-07-24 17:57:48');
INSERT INTO `sys_user` VALUES ('31', null, '测试费用之副总', 'dddd', 'd', 'a0718f45189b5962b634f73a3a5e886ef9fce168592d63c3bfbfe50edc11702b', 'BQn8', 'd@d.com', '14252635645', '1', '1', '2017-07-24 17:58:27', '1', '2017-07-24 17:58:27');

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `deptid` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='用户与部门对应关系';

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES ('11', '15', '4', '1', '2017-06-19 16:14:14', '1', '2017-06-19 16:14:14');
INSERT INTO `sys_user_dept` VALUES ('12', '15', '1', '1', '2017-06-19 16:14:14', '1', '2017-06-19 16:14:14');
INSERT INTO `sys_user_dept` VALUES ('14', '25', '12', '1', '2017-06-19 17:16:05', '1', '2017-06-19 17:16:05');
INSERT INTO `sys_user_dept` VALUES ('19', '9', '25', '1', '2017-06-19 17:54:02', '1', '2017-06-19 17:54:02');
INSERT INTO `sys_user_dept` VALUES ('22', '22', '26', '1', '2017-06-20 09:30:52', '1', '2017-06-20 09:30:52');
INSERT INTO `sys_user_dept` VALUES ('37', '16', '12', '1', '2017-06-27 18:36:48', '1', '2017-06-27 18:36:48');
INSERT INTO `sys_user_dept` VALUES ('50', '14', '25', '1', '2017-06-29 10:10:21', '1', '2017-06-29 10:10:21');
INSERT INTO `sys_user_dept` VALUES ('51', '13', '26', '1', '2017-06-29 10:10:36', '1', '2017-06-29 10:10:36');
INSERT INTO `sys_user_dept` VALUES ('56', '26', '22', '1', '2017-06-29 12:25:26', '1', '2017-06-29 12:25:26');
INSERT INTO `sys_user_dept` VALUES ('63', '24', '24', '1', '2017-07-24 11:59:11', '1', '2017-07-24 11:59:11');
INSERT INTO `sys_user_dept` VALUES ('66', '28', '16', '1', '2017-07-24 17:55:55', '1', '2017-07-24 17:55:55');
INSERT INTO `sys_user_dept` VALUES ('67', '29', '12', '1', '2017-07-24 17:56:26', '1', '2017-07-24 17:56:26');
INSERT INTO `sys_user_dept` VALUES ('68', '30', '24', '1', '2017-07-24 17:57:48', '1', '2017-07-24 17:57:48');
INSERT INTO `sys_user_dept` VALUES ('69', '31', '24', '1', '2017-07-24 17:58:27', '1', '2017-07-24 17:58:27');
INSERT INTO `sys_user_dept` VALUES ('70', '1', '12', '1', '2017-07-24 18:23:30', '1', '2017-07-24 18:23:30');
INSERT INTO `sys_user_dept` VALUES ('71', '1', '27', '1', '2017-07-24 18:23:30', '1', '2017-07-24 18:23:30');
INSERT INTO `sys_user_dept` VALUES ('72', '1', '16', '1', '2017-07-24 18:23:30', '1', '2017-07-24 18:23:30');
INSERT INTO `sys_user_dept` VALUES ('73', '8', '7', '1', '2017-07-24 18:23:59', '1', '2017-07-24 18:23:59');

-- ----------------------------
-- Table structure for sys_user_event
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_event`;
CREATE TABLE `sys_user_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `no` varchar(20) DEFAULT NULL COMMENT '案件编号',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与案件对应关系';

-- ----------------------------
-- Records of sys_user_event
-- ----------------------------
INSERT INTO `sys_user_event` VALUES ('1', '1', 'NO.2017050008', '1', '2017-05-26 17:09:27', '1', '2017-05-26 17:09:27');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `roleid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('146', '9', '13', '1', '2017-06-14 19:02:59', '1', '2017-06-14 19:02:59');
INSERT INTO `sys_user_role` VALUES ('147', '10', '13', '1', '2017-06-14 19:05:42', '1', '2017-06-14 19:05:42');
INSERT INTO `sys_user_role` VALUES ('148', '11', '13', '1', '2017-06-14 19:06:52', '1', '2017-06-14 19:06:52');
INSERT INTO `sys_user_role` VALUES ('149', '12', '13', '1', '2017-06-14 19:09:38', '1', '2017-06-14 19:09:38');
INSERT INTO `sys_user_role` VALUES ('150', '13', '13', '1', '2017-06-14 19:11:17', '1', '2017-06-14 19:11:17');
INSERT INTO `sys_user_role` VALUES ('151', '14', '13', '1', '2017-06-14 19:12:54', '1', '2017-06-14 19:12:54');
INSERT INTO `sys_user_role` VALUES ('154', '17', '13', '1', '2017-06-14 19:17:01', '1', '2017-06-14 19:17:01');
INSERT INTO `sys_user_role` VALUES ('155', '18', '13', '1', '2017-06-14 19:20:21', '1', '2017-06-14 19:20:21');
INSERT INTO `sys_user_role` VALUES ('156', '19', '13', '1', '2017-06-14 19:21:46', '1', '2017-06-14 19:21:46');
INSERT INTO `sys_user_role` VALUES ('158', '21', '13', '1', '2017-06-14 19:57:04', '1', '2017-06-14 19:57:04');
INSERT INTO `sys_user_role` VALUES ('159', '22', '13', '1', '2017-06-14 20:09:06', '1', '2017-06-14 20:09:06');
INSERT INTO `sys_user_role` VALUES ('179', '15', '7', '1', '2017-06-19 16:14:14', '1', '2017-06-19 16:14:14');
INSERT INTO `sys_user_role` VALUES ('180', '15', '6', '1', '2017-06-19 16:14:14', '1', '2017-06-19 16:14:14');
INSERT INTO `sys_user_role` VALUES ('182', '25', '1', '1', '2017-06-19 17:16:05', '1', '2017-06-19 17:16:05');
INSERT INTO `sys_user_role` VALUES ('184', '16', '10', '1', '2017-06-27 18:36:48', '1', '2017-06-27 18:36:48');
INSERT INTO `sys_user_role` VALUES ('190', '26', '12', '1', '2017-06-29 12:25:26', '1', '2017-06-29 12:25:26');
INSERT INTO `sys_user_role` VALUES ('191', '24', '14', '1', '2017-07-24 11:59:11', '1', '2017-07-24 11:59:11');
INSERT INTO `sys_user_role` VALUES ('192', '28', '15', '1', '2017-07-24 17:55:55', '1', '2017-07-24 17:55:55');
INSERT INTO `sys_user_role` VALUES ('193', '29', '16', '1', '2017-07-24 17:56:26', '1', '2017-07-24 17:56:26');
INSERT INTO `sys_user_role` VALUES ('194', '30', '14', '1', '2017-07-24 17:57:48', '1', '2017-07-24 17:57:48');
INSERT INTO `sys_user_role` VALUES ('195', '31', '17', '1', '2017-07-24 17:58:27', '1', '2017-07-24 17:58:27');
INSERT INTO `sys_user_role` VALUES ('196', '1', '9', '1', '2017-07-24 18:23:30', '1', '2017-07-24 18:23:30');
INSERT INTO `sys_user_role` VALUES ('197', '1', '11', '1', '2017-07-24 18:23:30', '1', '2017-07-24 18:23:30');
INSERT INTO `sys_user_role` VALUES ('198', '8', '13', '1', '2017-07-24 18:23:59', '1', '2017-07-24 18:23:59');
INSERT INTO `sys_user_role` VALUES ('199', '20', '13', '1', '2017-07-24 18:33:39', '1', '2017-07-24 18:33:39');

-- ----------------------------
-- Table structure for trashtab
-- ----------------------------
DROP TABLE IF EXISTS `trashtab`;
CREATE TABLE `trashtab` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `street` varchar(300) DEFAULT NULL COMMENT '路段名',
  `no` varchar(200) DEFAULT NULL COMMENT '标签纸编号',
  `lng` varchar(20) DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) DEFAULT NULL COMMENT '纬度',
  `addr` varchar(150) DEFAULT NULL COMMENT '地址',
  `rmk` varchar(500) DEFAULT NULL COMMENT '备注',
  `img` varchar(512) DEFAULT NULL COMMENT '图片',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='垃圾桶标记';

-- ----------------------------
-- Records of trashtab
-- ----------------------------
INSERT INTO `trashtab` VALUES ('1', '街道', 'NO.156156', '56.1562', '15.23', '地址地址', '备注备注备注', '68d1e0e349a1dff6b707805ef878cebb,77011cfed64eed80cb15e646be80e450', '25', '2017-06-20 20:38:23', '25', '2017-06-20 20:38:23');
INSERT INTO `trashtab` VALUES ('4', 'a', 'aa', '87.583514', '43.874183', '新疆维吾尔自治区乌鲁木齐市新市区高新街街道A家风尚酒店(长春路店)', 'aaa', null, '1', '2017-06-21 18:08:18', '1', '2017-06-21 18:08:18');
INSERT INTO `trashtab` VALUES ('5', 'b', 'bb', '120.301041', '31.572527', '江苏省无锡市梁溪区中山路281号中市桥上塘街小区', 'bbb', '201303794efcc422c6458ab3b6532fc6,6be285d33ec0391ab88d5afe585ed9cf', '1', '2017-06-21 18:09:51', '1', '2017-06-21 18:09:51');
INSERT INTO `trashtab` VALUES ('6', '西乡大道', null, '113.858155', '22.566557', '广东省深圳市宝安区西乡街道兴业路景福新邨', '123123123', '', '25', '2017-06-22 23:29:20', '25', '2017-06-22 23:29:20');
INSERT INTO `trashtab` VALUES ('7', '兴业路', null, '113.858155', '22.566557', '广东省深圳市宝安区西乡街道兴业路景福新邨', '收到可没空啊', '3fd1fbe4c8082fcabfb1451dccb02c91,9e224565bbfb7770f6dfe2a394393b6c', '25', '2017-06-22 23:30:04', '25', '2017-06-22 23:30:04');
INSERT INTO `trashtab` VALUES ('8', '楚楚路', 'no120-3991023', '113.858076', '22.566546', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '123123123123123', '0e33915fed18372bcdb5a1e27a73d5d1', '25', '2017-06-22 23:40:27', '25', '2017-06-22 23:40:27');
INSERT INTO `trashtab` VALUES ('9', '12312', '3123', '113.858056', '22.566608', '广东省深圳市宝安区西乡街道西乡大道景福新邨', '123123', '6ea6b28543d21cf5b71d5411333c4183', '25', '2017-06-23 03:10:38', '25', '2017-06-23 03:10:38');
INSERT INTO `trashtab` VALUES ('10', '34242', '34234234', '113.858078', '22.567014', '广东省深圳市宝安区西乡街道西乡大道碧海富通城6期', '324324', '1c67afdf05917edae0324dd804a43cf0', '25', '2017-06-23 03:23:23', '25', '2017-06-23 03:23:23');

-- ----------------------------
-- Table structure for vehiclemn
-- ----------------------------
DROP TABLE IF EXISTS `vehiclemn`;
CREATE TABLE `vehiclemn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project` varchar(50) DEFAULT NULL COMMENT '所属项目',
  `plate` varchar(50) DEFAULT NULL COMMENT '所属板块',
  `business` varchar(50) DEFAULT NULL COMMENT '业务',
  `type` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `dateset` varchar(50) DEFAULT NULL COMMENT '时间设置',
  `datepeople` text COMMENT '时间设置接收人',
  `mileage` varchar(50) DEFAULT NULL COMMENT '里程设置',
  `mileageset` text COMMENT '里程设置接收人',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime DEFAULT NULL COMMENT '创建时间',
  `updid` bigint(20) DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='车辆保养预警';

-- ----------------------------
-- Records of vehiclemn
-- ----------------------------
INSERT INTO `vehiclemn` VALUES ('1', '1234', '123', '123', '123', '123', '123', '123', '123', '1', '1', '2017-06-06 15:06:22', '1', '2017-06-06 15:06:30');

-- ----------------------------
-- Table structure for wechat
-- ----------------------------
DROP TABLE IF EXISTS `wechat`;
CREATE TABLE `wechat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `openid` varchar(50) NOT NULL COMMENT '用户标识',
  `subscribe` char(1) DEFAULT '1' COMMENT '是否订阅(0表示没有关注)',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `sex` char(1) DEFAULT '0' COMMENT '性别(0未知,1男,2,女)',
  `city` varchar(15) DEFAULT NULL COMMENT '所在城市',
  `country` varchar(20) DEFAULT NULL COMMENT '所在国家',
  `province` varchar(20) DEFAULT NULL COMMENT '所在省份',
  `language` varchar(12) DEFAULT 'zh_CN' COMMENT '语言',
  `headimgurl` varchar(300) DEFAULT NULL COMMENT '头像',
  `subscribe_time` varchar(20) NOT NULL COMMENT '关注时间',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqopenid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='微信关注列表';

-- ----------------------------
-- Records of wechat
-- ----------------------------
INSERT INTO `wechat` VALUES ('1', 'o1r6305HNecL-Kn4K14VqJGJMOCk', '1', '灵犀一指', '1', '佛山', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/GsV3RFWvsCNUq16ZhWzYlAUhIaawbdIgWy1sTPwSGrS1cYgk1iavVbicQ5Kh3LpDHK5iaSv9obfxBhFC5ztXKOYG4eibxffN2vMj/0', '1490776460', '1', '2017-04-11 11:11:46', '1', '2017-06-08 18:31:28');
INSERT INTO `wechat` VALUES ('2', 'o1r630yKh4DMNUK82ge4UTMzwRCs', '1', 'Alter', '2', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/okSD7Izia6YT9ickIJ2u96MQzPE3leoeGIMxZ8BjxfnBYjsRH17VHCF2G688973FYibMewD04QYgGnQMS4sibAicyrOv4XL8Pw16n/0', '1491015957', '1', '2017-04-11 11:11:46', '1', '2017-06-08 18:31:28');
INSERT INTO `wechat` VALUES ('3', 'o1r6303LJjygnM1L6490aYJWrW6A', '1', 'Alterจุ๊บ', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/okSD7Izia6YQ53sVnQqL8GSzWJpSWZGRDYibVIficxFfTQzZ7ZdYrufEt8apDuSMYX27aKKrvoBdhJ0o3FNKAwGmlVE78Zmgzia1/0', '1491875975', '1', '2017-04-11 11:11:46', '1', '2017-06-08 18:31:28');
INSERT INTO `wechat` VALUES ('4', 'o1r6301U5l0x4o5_HTloKvnX3Kys', '1', 'J.Song', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/QnM5bMcic4Z3ibBEIJXnGIaet36UUmXOkoyzWAdXw6ph0KMeyjPJnXNukV1ia62vM1ptkyKG364fVcVOClpaLB37w/0', '1496915807', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:31:28');
INSERT INTO `wechat` VALUES ('5', 'o1r6308mO5rGx1Zw9l-OXqLJXIBc', '1', '罗丁丁', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/j16amwuuSFaQln13OvQDhqKzd1wQeTEqria4HlH5lCI0teIBNKicX2icQhkqrnN1cahP3h18TEXTicCa7QCYGiacIDK76GcSchc4R/0', '1496915913', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:31:28');
INSERT INTO `wechat` VALUES ('6', 'o1r6306kK-7fCvPtgs0XCcb6ZrKI', '1', '文青‮', '1', '', '意大利', '米兰', 'zh_CN', 'http://wx.qlogo.cn/mmopen/GsV3RFWvsCM3uJFylBC4EqvJkIibeyN0tib4xF4h7icdC7LL64m5fbLA5aKPxgVBJr8aISZL5BicWWS1LkhhHswCfUyyaJ9WUDt6/0', '1496915811', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:31:28');
INSERT INTO `wechat` VALUES ('7', 'o1r630xXIiAifBZgQ6hkEtEM9E8w', '1', 'mickey', '2', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/okSD7Izia6YQz30CQPONGQvjciaUYaV43INn6AcgKyBEVKLGEtgic0erKUQt2sYHm714o0Wmf82uN34EtmT49wFfAqzdH1DU3gw/0', '1496915824', '1', '2017-06-08 18:30:33', '1', '2017-06-08 18:30:33');

-- ----------------------------
-- Table structure for wechatadmin
-- ----------------------------
DROP TABLE IF EXISTS `wechatadmin`;
CREATE TABLE `wechatadmin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `openid` varchar(50) NOT NULL COMMENT '用户标识',
  `subscribe` char(1) DEFAULT '1' COMMENT '是否订阅(0表示没有关注)',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `sex` char(1) DEFAULT '0' COMMENT '性别(0未知,1男,2,女)',
  `city` varchar(15) DEFAULT NULL COMMENT '所在城市',
  `country` varchar(20) DEFAULT NULL COMMENT '所在国家',
  `province` varchar(20) DEFAULT NULL COMMENT '所在省份',
  `language` varchar(12) DEFAULT 'zh_CN' COMMENT '语言',
  `headimgurl` varchar(300) DEFAULT NULL COMMENT '头像',
  `subscribe_time` varchar(20) DEFAULT NULL COMMENT '关注时间',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqopenid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='微信关注列表管理员';

-- ----------------------------
-- Records of wechatadmin
-- ----------------------------
INSERT INTO `wechatadmin` VALUES ('1', 'oY9cUv3O7oO5FXryu-mjkMSNl8QA', '1', '灵犀一指', '1', '佛山', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/98aepnicDOYWFXBx0CjF7Ro1m4WxxxAcsVFuwYgPaF95QliaNRKzd02qdQKeG2Bnic8D5icCIdxdASTKK2KLOk2sj4wDwTzA7FTd/0', '1490776497', '1', '2017-04-11 10:55:30', '1', '2017-06-08 18:31:29');
INSERT INTO `wechatadmin` VALUES ('2', 'oY9cUv-_fk4UGbxyA6FjzNetXtGo', '1', '罗丁丁', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/SbtGVeibYWqtrQDrSWJ3PB4I6YZc63K2iaDnOvwAMnwTicpaK3qcnMMxrNZKwELSzvJGzhRrwqmib4agA5yh1UDqibY7QTwRKMUGB/0', '1491034249', '1', '2017-04-11 10:55:30', '1', '2017-06-08 18:31:29');
INSERT INTO `wechatadmin` VALUES ('3', 'oY9cUvxGFJExQnVx61cdvSYPzz6g', '1', '文青‮', '1', '', '意大利', '米兰', 'zh_CN', 'http://wx.qlogo.cn/mmopen/98aepnicDOYVPibBhg2j7SPzYfXib3CHJEfkRKJGpu1o9WgnbTgdUzYaagpyWrhjjYVxQibESgMmEYM4M5FJTzu4HaO5N0LobgH3/0', '1491034391', '1', '2017-04-11 10:55:30', '1', '2017-06-08 18:31:29');
INSERT INTO `wechatadmin` VALUES ('4', 'oY9cUvxHwM5IgU1MvVAQwNZDnkwU', '1', 'Alter', '2', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/pB2gIX56wumc8hliaibWcrlFD9AMgQRAWbssCFKQUkxeoScUCne1FwBpTzCubJl2V5EcCmxmwaQ5nQGqYc0HoHBBvnibk78ssicm/0', '1491016103', '1', '2017-04-11 10:55:30', '1', '2017-06-08 18:31:30');
INSERT INTO `wechatadmin` VALUES ('6', 'oY9cUv_Rg4cgZ5M2-TayvNbwhaHs', '1', '练楚楚', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/98aepnicDOYVzfKSoNtMXxAzelYM1lS1yVctvFwxglnnrjG70iaLadCiblQTunKNBaVwcNR8ibfibaWib4Oyx6BPSsibSByQXV2zYG6/0', '1491034615', '1', '2017-04-11 10:55:30', '1', '2017-06-08 18:31:30');
INSERT INTO `wechatadmin` VALUES ('7', 'oY9cUv1SSvT33x4s7zDtfDJwvR0M', '1', 'Alterจุ๊บ', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/pB2gIX56wukOQuRF4GaFFWhJ7lbUKj4Pmb7uc7dlNgCEszNFmSWgmTYPpxCFzkhMibBX0nqP9aFV9d5ebkRtMIY3277D8nv4v/0', '1490858578', '1', '2017-04-11 10:55:30', '1', '2017-06-08 18:31:30');
INSERT INTO `wechatadmin` VALUES ('8', 'oY9cUv-bkU4nULRBYFC1r7ffWJEo', '0', 'mickey', '2', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/pB2gIX56wulx1XGLB764iaHTCQiaLPE3ic2Xb0XTvyL1HLh2ReJCuR0OcWXibLwcJO9ia1TPYucXM6cQqfqMQiayiaOc57Hiaq0THMNI/0', '1496915253', '1', '2017-05-03 10:04:23', '1', '2017-06-08 18:31:30');
INSERT INTO `wechatadmin` VALUES ('9', 'oY9cUvy4H5UST-i18490B1utDRjc', '1', 'J.Song', '1', '深圳', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/y5RpFDuUObwvcvgCHj4l2Soln3u7L5kiaTd0PRfus0GqPicXzlRDSzSiazRjqd6icEHMIy8WuRPHibqhoyTMBXYXPvg/0', '1496915873', '1', '2017-06-08 18:31:29', '1', '2017-06-08 18:31:29');
INSERT INTO `wechatadmin` VALUES ('11', 'oY9cUv1xT_DWh0p1QJ2M6fZ8oSOQ', '1', 'xinxi', '1', '开县', '中国', '重庆', 'zh_CN', 'http://wx.qlogo.cn/mmopen/98aepnicDOYU59b6R8Cibgvdd9Ly3vA1DjItUrqMWCia7nVtRk5ibeaEpfarB0R4K47qQ8bLKXfUmjaPuwhTIAoc44tbtkJqb7zm/0', '1497240444', '1', '2017-06-12 11:56:35', '1', '2017-06-12 12:07:42');

-- ----------------------------
-- Table structure for weixintoken
-- ----------------------------
DROP TABLE IF EXISTS `weixintoken`;
CREATE TABLE `weixintoken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `appid` varchar(50) DEFAULT NULL COMMENT 'appid',
  `secret` varchar(50) DEFAULT NULL COMMENT 'secret',
  `token` varchar(512) DEFAULT NULL COMMENT 'access_token',
  `lutime` bigint(20) DEFAULT NULL COMMENT '最后更新时间',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='微信token';

-- ----------------------------
-- Records of weixintoken
-- ----------------------------
INSERT INTO `weixintoken` VALUES ('1', 'wx6caccadb709a014e', 'ea72defcfd5e6ccf3fe731e1b9cdb696', '04XIPMIrfbohtjQRizD5PxBDM2UUcZr5_Ce7l5-QCm3DF8C5y4rirp0L5aZCbw_koNyjBEH5_HOmS_Fsoe5L4O9BXyg92mOE4XOAwhj8zCbnbDAvNc16bbK9zKvSZDAkIXYdAFAUJK', '1500879880010', '1', '2017-07-24 15:04:40', '1', '2017-07-24 15:04:40');
INSERT INTO `weixintoken` VALUES ('2', 'wxffe43e9023f4e499', 'e1913befffa61e51baa5233540a03bf9', 'FITmdPEho8XVNjXuUm5H1oU-iyhkTZuh-q1bVO3h1ycoDLlwqu8MEQnyWhJF5_v26lXc0I1DEtTNtPIOhSMDD1EUUX2R5dwXpkZmPxIuhVo7heLdRA4h4D_vixoF1Ox1DSUhAGAKZB', '1500879880010', '1', '2017-07-24 15:04:40', '1', '2017-07-24 15:04:40');

-- ----------------------------
-- Table structure for workflow
-- ----------------------------
DROP TABLE IF EXISTS `workflow`;
CREATE TABLE `workflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nodename` varchar(50) NOT NULL COMMENT '节点名称',
  `nodeno` varchar(20) NOT NULL COMMENT '节点编号',
  `nm` varchar(50) NOT NULL COMMENT '流程名称',
  `number` varchar(20) NOT NULL COMMENT '流程编号',
  `prevno` varchar(20) NOT NULL COMMENT '上一节点编号',
  `nextno` varchar(20) NOT NULL COMMENT '下一节点编号',
  `returnno` varchar(20) DEFAULT NULL COMMENT '回退节点编号',
  `roleids` varchar(1000) NOT NULL COMMENT '角色集合',
  `nodetype` char(1) NOT NULL DEFAULT '0' COMMENT '节点类型',
  `vseq` int(11) NOT NULL COMMENT '版本序号',
  `vname` varchar(50) NOT NULL COMMENT '版本名称',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='工作流';

-- ----------------------------
-- Records of workflow
-- ----------------------------
INSERT INTO `workflow` VALUES ('1', '爆料', 'bl', '清扫保洁流程', 'qsbjlc', '999999', 'sjpd', '999999', '1,2', '0', '1', '1.0', '1', '2017-05-17 10:54:53', '1', '2017-06-24 11:50:59');
INSERT INTO `workflow` VALUES ('2', '事件派单', 'sjpd', '清扫保洁流程', 'qsbjlc', 'bl', 'ycl', 'ja', '11', '0', '1', '1.0', '1', '2017-05-17 10:55:38', '1', '2017-05-17 10:55:38');
INSERT INTO `workflow` VALUES ('3', '已处理', 'ycl', '清扫保洁流程', 'qsbjlc', 'sjpd', 'fhpd', 'sjpd', '11', '0', '1', '1.0', '1', '2017-05-17 10:56:40', '1', '2017-05-17 10:56:40');
INSERT INTO `workflow` VALUES ('4', '复核派单', 'fhpd', '清扫保洁流程', 'qsbjlc', 'ycl', 'fhcl', 'ycl', '11', '0', '1', '1.0', '1', '2017-05-17 10:57:26', '1', '2017-05-17 10:57:26');
INSERT INTO `workflow` VALUES ('5', '复核处理', 'fhcl', '清扫保洁流程', 'qsbjlc', 'fhpd', 'ja', 'fhpd', '11', '0', '1', '1.0', '1', '2017-05-17 10:58:06', '1', '2017-05-17 10:58:06');
INSERT INTO `workflow` VALUES ('6', '结案', 'ja', '清扫保洁流程', 'qsbjlc', 'fhcl', '999999', 'fhcl', '11', '0', '1', '1.0', '1', '2017-05-17 10:58:06', '1', '2017-05-17 10:58:06');

-- ----------------------------
-- Table structure for workRecord
-- ----------------------------
DROP TABLE IF EXISTS `workRecord`;
CREATE TABLE `workRecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `busseg` char(1) NOT NULL DEFAULT '1' COMMENT '业务板块',
  `sour` char(2) NOT NULL DEFAULT '1' COMMENT '来源',
  `img` varchar(500) DEFAULT NULL COMMENT '现场照片',
  `addr` varchar(150) DEFAULT NULL COMMENT '详细地址',
  `accaddr` varchar(150) DEFAULT NULL COMMENT '精确地址',
  `lat` varchar(50) DEFAULT NULL COMMENT '纬度',
  `lng` varchar(150) DEFAULT NULL COMMENT '经度',
  `expl` varchar(300) NOT NULL COMMENT '说明',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='工作记录';

-- ----------------------------
-- Records of workRecord
-- ----------------------------
INSERT INTO `workRecord` VALUES ('8', '1', '1', 'f26e57790db3d8436204ff89e06ff7e8', '广东省深圳市宝安区西乡街道兴业路2088号景福新邨', '广东省深圳市宝安区西乡街道兴业路2088号景福新邨', '22.566519', '113.85847', '广东省深圳市宝安区西乡街道兴业路2088号景福新邨', '1', '2017-05-16 19:12:11', '1', '2017-05-16 19:12:11');
INSERT INTO `workRecord` VALUES ('9', '1', '1', '8efc6e7dfa372186a43676bf9d15ca95', '北京市西城区大栅栏街道石头胡同66号', '1', '39.890641', '116.390705', '2', '1', '2017-05-27 11:55:44', '1', '2017-05-27 11:55:44');
INSERT INTO `workRecord` VALUES ('11', '1', '1', 'a1fd201294f3ae5b2bb2dda33fc9140e', '广东省深圳市宝安区新安街道深圳市宝安区交通局', '共乐小区', '22.560229', '113.902498', '广东省深圳市宝安区西乡街道工业路44号广东省深圳市宝安区西乡街道工业路44号广东省深圳市宝安区西乡街道工业路44号广东省深圳市宝安区西乡街道工业路44号广东省深圳市宝安区西乡街道工业路44号广东省深圳市宝安区西乡街道工业路44号广东省深圳市宝安区西乡街道工业路44号', '1', '2017-06-06 15:59:39', '1', '2017-06-06 17:09:45');
INSERT INTO `workRecord` VALUES ('12', '1', '1', 'f44d97aae0cf24026a162c98edf4e793', '广东省深圳市宝安区西乡街道宝安·碧海名园', '碧海名园', '22.581399', '113.865295', '碧海名园', '1', '2017-06-06 16:00:27', '1', '2017-06-06 16:59:30');
INSERT INTO `workRecord` VALUES ('13', '1', '1', 'c4347e41d93fcfaa5bc36aa88dbfc365', '广东省深圳市南山区西丽街道麻磡路26号', '西丽街道麻磡路26号', '22.632791', '113.947159', '西丽街道麻磡路26号', '1', '2017-06-06 16:01:30', '1', '2017-06-06 16:01:30');
INSERT INTO `workRecord` VALUES ('14', '1', '1', '8db80206f263d03f33fd4d196c47dd4b', '广东省深圳市南山区西丽街道麻磡路佳和购物广场', '麻磡路佳和购物广场', '22.634856', '113.950415', '麻磡路佳和购物广场', '1', '2017-06-06 16:04:36', '1', '2017-06-06 16:04:36');
INSERT INTO `workRecord` VALUES ('15', '1', '1', '3128a990d4e730731d98b84a9e890a6d,c5f77c443992a5662aa62519bba0dccb', '广东省深圳市福田区华富街道深圳市第二人民医院', '道深圳市第二人民医院', '22.557046', '114.085469', '广东省深圳市福田区华富街道深圳市第二人民医院广东省深圳市福田区华富街道深圳市第二人民医院广东省深圳市福田区华富街道深圳市第二人民医院广东省深圳市福田区华富街道深圳市第二人民医院广东省深圳市福田区华富街道深圳市第二人民医院广东省深圳市福田区华富街道深圳市第二人民医院广东省深圳市福田区华富', '1', '2017-06-06 16:24:06', '1', '2017-06-06 16:24:06');
INSERT INTO `workRecord` VALUES ('16', '1', '1', 'dbb06ec20cc273b103033aecd211e614,e4bc59520d48438ff9b3fb82a4bc734b,89bef84120bd609347c6f7e11559f4f5,f0cb6f41cf829a956c230b5cd46d0852,07b0d7dace5dc5b7fc6c750f226330ee,0e6f48f315a3056d066b65d7ca75b730', '广东省深圳市福田区园岭街道南天二花园深圳市妇幼保健院(红荔路)', '福田区园岭街道南天二花园', '22.549663', '114.094244', '福田区园岭街道南天二花园', '1', '2017-06-06 16:45:13', '1', '2017-06-06 16:45:13');
INSERT INTO `workRecord` VALUES ('17', '1', '1', 'c43866292a8c04b14c4a317691ddb8ca,3fd3375c47b267e4a1d1c7249c3d5a42,1ab62ece5431b6f847055feff51e9ab1,7e8e40ee12be4a797d798cf98976b496,f71ca7419e9c604c0a2c28dab27e4525,27d0ed565088a54428de73ac84d54124,5df3fd80907da6aef896257b97b614cf,2321e8acd415031c3ae2dd6fffa6b4e7,2fdeb1d53734afa1dfbaf3af6f305160,fcfac4e7ea0b07791b5e1e9a418693b7', '广东省深圳市宝安区新安街道湖滨花园', '新安街道湖滨花园', '22.556774', '113.91457', '新安街道湖滨花园', '1', '2017-06-06 16:52:52', '1', '2017-06-06 16:55:35');
INSERT INTO `workRecord` VALUES ('18', '2', '1', 'aaa', '北京市西城区椿树街道庄胜广场中央办公楼SOGO本馆', '北京市西城区椿树街道庄胜广场中央办公楼SOGO本馆', '1.1', '2.3', 'expl', '25', '2017-07-12 18:07:21', '25', '2017-07-12 18:07:21');
INSERT INTO `workRecord` VALUES ('19', '0', '1', '', '广东省深圳市宝安区西乡街道宝田四路', '广东省深圳市宝安区西乡街道宝田四路', '22.60263', '113.86993', 'qedwed ', '25', '2017-07-14 17:36:54', '25', '2017-07-14 17:36:54');
INSERT INTO `workRecord` VALUES ('20', '3', '1', '95bf98b8b3a62ab4886b6c6c342334b5,ca670148f3b13bf679912a5f3dc9c282', '广东省深圳市宝安区西乡街道九围一路27号', '广东省深圳市宝安区西乡街道九围一路27号', '22.65225', '113.8674', '这里需要清扫保洁，十分的需要', '25', '2017-07-17 09:59:16', '25', '2017-07-17 09:59:16');
INSERT INTO `workRecord` VALUES ('21', '3', '1', '', '广东省深圳市宝安区西乡街道轻铁西九巷9巷', '广东省深圳市宝安区西乡街道轻铁西九巷9巷', '22.56926', '113.86881', '·12·12', '25', '2017-07-25 08:04:38', '25', '2017-07-25 08:04:38');

-- ----------------------------
-- Table structure for yeartrial
-- ----------------------------
DROP TABLE IF EXISTS `yeartrial`;
CREATE TABLE `yeartrial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cno` bigint(20) DEFAULT NULL COMMENT '车牌号(关联到车辆表)',
  `yttm` date DEFAULT NULL COMMENT '年审日期',
  `nyttm` date DEFAULT NULL COMMENT '到期日期',
  `ytno` varchar(75) DEFAULT NULL COMMENT '年检号',
  `person` bigint(20) DEFAULT NULL COMMENT '办理人员',
  `cost` decimal(24,4) DEFAULT NULL COMMENT '办理费用',
  `addr` varchar(50) DEFAULT NULL COMMENT '年审地点',
  `unit` varchar(50) DEFAULT NULL COMMENT '年审单位',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `crtid` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人员',
  `crttm` datetime NOT NULL COMMENT '创建时间',
  `updid` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人员',
  `updtm` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='年审管理';

-- ----------------------------
-- Records of yeartrial
-- ----------------------------
INSERT INTO `yeartrial` VALUES ('5', '31', '2017-06-15', '2018-06-15', '564984651', '22', '5000.0000', '深圳市南山西丽车管所', '太平洋保险', '1', '1', '2017-06-14 20:24:41', '1', '2017-06-14 20:24:41');
INSERT INTO `yeartrial` VALUES ('6', '32', '2017-06-15', '2018-06-15', '25456464', '23', '3600.0000', '深圳市南山区西丽车管所', '太康人寿保险', '1', '1', '2017-06-14 20:26:12', '1', '2017-06-14 20:26:12');
INSERT INTO `yeartrial` VALUES ('7', '4', '2017-06-27', '2017-06-28', 'nj001', '25', '21.0000', 'yyy', 'xxx', '1', '1', '2017-06-27 17:22:35', '1', '2017-06-27 17:22:51');

-- ----------------------------
-- Function structure for CalculatingDistance
-- ----------------------------
DROP FUNCTION IF EXISTS `CalculatingDistance`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `CalculatingDistance`(
	LAT_A FLOAT,
	LON_A FLOAT,
	LAT_B FLOAT,
	LON_B FLOAT
) RETURNS double
    DETERMINISTIC
BEGIN
DECLARE tempCalc DOUBLE;
if LAT_A is null then   
set LAT_A = 0;   
end if; 
if LON_A is null then   
set LON_A = 0;   
end if; 
if LAT_B is null then   
set LAT_B = 0;   
end if; 
if LON_B is null then   
set LON_B = 0;   
end if; 
if LAT_A = 0 then   
set tempCalc = 99999;
RETURN tempCalc;
end if; 
if LON_A = 0 then   
set tempCalc = 99999;
RETURN tempCalc;
end if; 
if LAT_B = 0 then   
set tempCalc = 99999;
RETURN tempCalc;
end if; 
if LON_B = 0 then   
set tempCalc = 99999;
RETURN tempCalc;
end if; 
SET tempCalc = round((2*ATAN2(SQRT(SIN((LAT_A-LAT_B)*PI()/180/2)
		*SIN((LAT_A-LAT_B)*PI()/180/2)+
		COS(LAT_B*PI()/180)*COS(LAT_A*PI()/180)
		*SIN((LON_A-LON_B)*PI()/180/2)
		*SIN((LON_A-LON_B)*PI()/180/2)),
		SQRT(1-SIN((LAT_A-LAT_B)*PI()/180/2)
		*SIN((LAT_A-LAT_B)*PI()/180/2)
		+COS(LAT_B*PI()/180)*COS(LAT_A*PI()/180)
		*SIN((LON_A-LON_B)*PI()/180/2)
		*SIN((LON_A-LON_B)*PI()/180/2))))*6371,2);
RETURN tempCalc;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for queryDept
-- ----------------------------
DROP FUNCTION IF EXISTS `queryDept`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `queryDept`(id_ BIGINT(20)) RETURNS varchar(4000) CHARSET utf8
    COMMENT '查询子集部门'
BEGIN
      DECLARE sTemp VARCHAR(4000);
      DECLARE sTempChd VARCHAR(4000);
      DECLARE iTemp Int;

      SET sTemp = '';
      SET sTempChd = id_;
        SET iTemp = 0;

      WHILE sTempChd is not NULL DO
              IF iTemp = 0 THEN
                SET sTemp = '';
              ELSE
                IF sTemp = '' THEN
                  SET sTemp = sTempChd;
                ELSE
                  SET sTemp = CONCAT(sTemp,',',sTempChd);
                END IF;
              END IF;
            SELECT group_concat(id) INTO sTempChd FROM sys_department where FIND_IN_SET(pid, sTempChd)>0 AND id != pid;
            SET iTemp = iTemp + 1;
      END WHILE;
      return sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for queryDeptLink
-- ----------------------------
DROP FUNCTION IF EXISTS `queryDeptLink`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `queryDeptLink`(id_ BIGINT(20)) RETURNS varchar(4000) CHARSET utf8
    COMMENT '查询部门结点链(子集部门和父级部门)'
BEGIN
      DECLARE sTemp VARCHAR(4000);
      DECLARE sTempPar VARCHAR(4000);
			DECLARE sTempChd VARCHAR(4000);
			DECLARE sTempC VARCHAR(4000);
			DECLARE sTempS VARCHAR(4000);
      DECLARE iTemp Int;

      SET sTemp = '';
      SET sTempPar = id_;
      SET iTemp = 0;

      WHILE sTempPar is not NULL DO
              IF iTemp = 0 THEN
                SET sTemp = '';
              ELSE
                IF sTemp = '' THEN
                  SET sTemp = sTempPar;
                ELSE
                  SET sTemp = CONCAT(sTemp,',',sTempPar);
                END IF;
              END IF;
            SELECT group_concat(pid) INTO sTempPar FROM sys_department where FIND_IN_SET(id, sTempPar)>0 AND id != pid;
            SET iTemp = iTemp + 1;
      END WHILE;

			SET iTemp = 0;
			SET sTempChd = id_;
			SET sTempC = '';
			WHILE sTempChd is not NULL DO
				IF iTemp = 0 THEN
					SET sTempC = '';
				ELSE
					IF sTempC = '' THEN
						SET sTempC = sTempChd;
					ELSE
            SET sTempC = CONCAT(sTempC,',',sTempChd);
          END IF;
				END IF;
				SELECT group_concat(id) INTO sTempChd FROM sys_department where FIND_IN_SET(pid, sTempChd)>0 AND id != pid;
        SET iTemp = iTemp + 1;
			END WHILE;


			SET sTempS = '';

			IF sTempC != '' THEN
				IF sTemp != '' THEN
					SET sTempS = CONCAT(sTemp,',',id_,',',sTempC);
				ELSE
					SET sTempS = CONCAT(id_,',',sTempC);
				END IF;
			ELSE
				IF sTemp != '' THEN
					SET sTempS = CONCAT(sTemp,',',id_);
				ELSE
					SET sTempS = id_;
				END IF;
			END IF;

      return sTempS;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for queryRoles
-- ----------------------------
DROP FUNCTION IF EXISTS `queryRoles`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `queryRoles`(id_ BIGINT(20)) RETURNS varchar(4000) CHARSET utf8
    DETERMINISTIC
    COMMENT '查询子集角色'
BEGIN
      DECLARE sTemp VARCHAR(4000);
      DECLARE sTempChd VARCHAR(4000);
      DECLARE iTemp Int;

      SET sTemp = '';
      SET sTempChd = id_;
        SET iTemp = 0;

      WHILE sTempChd is not NULL DO
              IF iTemp = 0 THEN
                SET sTemp = '';
              ELSE
                IF sTemp = '' THEN
                  SET sTemp = sTempChd;
                ELSE
                  SET sTemp = CONCAT(sTemp,',',sTempChd);
                END IF;
              END IF;
            SELECT group_concat(id) INTO sTempChd FROM sys_role where FIND_IN_SET(pid, sTempChd)>0;
            SET iTemp = iTemp + 1;
      END WHILE;
      return sTemp;
END
;;
DELIMITER ;
