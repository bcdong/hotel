/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-17 09:04:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hotel_tbl
-- ----------------------------
DROP TABLE IF EXISTS `hotel_tbl`;
CREATE TABLE `hotel_tbl` (
  `id` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `manager_id` int(11) unsigned zerofill DEFAULT NULL,
  `state` varchar(32) NOT NULL,
  `today_income` double NOT NULL DEFAULT '0',
  `total_income` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `htl_mngr_fk` (`manager_id`),
  CONSTRAINT `htl_mngr_fk` FOREIGN KEY (`manager_id`) REFERENCES `manager_tbl` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel_tbl
-- ----------------------------
INSERT INTO `hotel_tbl` VALUES ('0000006', '南大快捷酒店', '南京市鼓楼区汉口路66号', '00000000002', 'OPEN', '0', '1055');
INSERT INTO `hotel_tbl` VALUES ('0000007', '风中过客', '上海市徐汇区xx路11号', '00000000004', 'OPEN', '0', '1254');
INSERT INTO `hotel_tbl` VALUES ('0000008', '风沙渡', '杭州西湖边上', '00000000003', 'OPEN', '0', '1140');
INSERT INTO `hotel_tbl` VALUES ('0000010', '张三的酒店', '天安门', '00000000005', 'OPEN', '0', '570');
INSERT INTO `hotel_tbl` VALUES ('0000011', '测试酒店', '测试地址', '00000000007', 'OPEN', '0', '385');

-- ----------------------------
-- Table structure for manager_tbl
-- ----------------------------
DROP TABLE IF EXISTS `manager_tbl`;
CREATE TABLE `manager_tbl` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager_tbl
-- ----------------------------
INSERT INTO `manager_tbl` VALUES ('00000000001', 'harry', '1234qwer', '董本超', 'TOP_MANAGER');
INSERT INTO `manager_tbl` VALUES ('00000000002', 'daixinyan', '1234qwer', '戴新颜', 'MANAGER');
INSERT INTO `manager_tbl` VALUES ('00000000003', 'cuizhongcheng', '1234qwer', '崔忠诚', 'MANAGER');
INSERT INTO `manager_tbl` VALUES ('00000000004', 'daifeng', '1234qwer', '戴峰', 'MANAGER');
INSERT INTO `manager_tbl` VALUES ('00000000005', 'zhangsan', '1234qwer', '张三', 'MANAGER');
INSERT INTO `manager_tbl` VALUES ('00000000006', 'dxy', '1234qwer', '戴新颜', 'MANAGER');
INSERT INTO `manager_tbl` VALUES ('00000000007', 'mm', '1234qwer', 'mm', 'MANAGER');

-- ----------------------------
-- Table structure for order_tbl
-- ----------------------------
DROP TABLE IF EXISTS `order_tbl`;
CREATE TABLE `order_tbl` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `vip_id` int(7) unsigned zerofill DEFAULT NULL COMMENT 'null means not vip',
  `from_time` datetime DEFAULT NULL,
  `to_time` datetime DEFAULT NULL,
  `state` varchar(32) NOT NULL,
  `hotel_id` int(7) unsigned zerofill NOT NULL,
  `room_id` varchar(16) NOT NULL,
  `room_type` varchar(31) DEFAULT NULL,
  `customer` varchar(255) DEFAULT NULL,
  `cost` double NOT NULL,
  `pay_method` varchar(31) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ordr_htl_fk` (`hotel_id`),
  KEY `ordr_vip_fk` (`vip_id`),
  CONSTRAINT `ordr_htl_fk` FOREIGN KEY (`hotel_id`) REFERENCES `hotel_tbl` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ordr_vip_fk` FOREIGN KEY (`vip_id`) REFERENCES `vip_tbl` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_tbl
-- ----------------------------
INSERT INTO `order_tbl` VALUES ('00000000001', '0000001', '2017-03-14 23:33:43', '2017-03-16 23:33:43', 'LEAVE', '0000006', '432', 'T_DOUBLE', 'user1,user2', '380', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000002', '0000001', '2017-03-15 00:00:00', '2017-03-16 00:00:00', 'LEAVE', '0000006', '765', 'SINGLE', 'user1', '95', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000003', '0000001', '2017-03-16 00:00:00', '2017-03-17 00:00:00', 'LEAVE', '0000006', '432', 'SINGLE', 'user1', '95', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000004', '0000002', '2017-03-16 00:00:00', '2017-03-17 00:00:00', 'IN', '0000006', '321', 'T_DOUBLE', 'user1,user2', '190', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000005', '0000003', '2017-03-23 00:00:00', '2017-03-25 00:00:00', 'IN', '0000007', '321', 'T_DOUBLE', 'user3', '570', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000006', '0000003', '2017-03-16 00:00:00', '2017-03-18 00:00:00', 'IN', '0000006', '345', 'TRIPLE', 'user3', '570', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000007', '0000001', '2017-03-25 00:00:00', '2017-03-27 00:00:00', 'LEAVE', '0000007', '345', 'T_DOUBLE', '用户一', '570', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000008', '0000001', '2017-03-30 00:00:00', '2017-04-01 00:00:00', 'LEAVE', '0000010', '234', 'SINGLE', '用户一', '190', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000009', '0000002', '2017-03-21 00:00:00', '2017-03-25 00:00:00', 'IN', '0000008', '123', 'TRIPLE', '用户二', '1140', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000010', '0000002', '2017-04-11 00:00:00', '2017-04-15 00:00:00', 'IN', '0000010', '235', 'SINGLE', '用户二', '380', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000013', '0000003', '2017-03-30 00:00:00', '2017-03-31 00:00:00', 'LEAVE', '0000006', '346', 'SINGLE', '用户三', '95', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000015', '0000006', '2017-03-17 00:00:00', '2017-03-18 00:00:00', 'LEAVE', '0000007', '432', 'SINGLE', 'sadfdsaf', '114', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000016', '0000006', '2017-03-17 00:00:00', '2017-03-19 00:00:00', 'LEAVE', '0000011', '123', 'SINGLE', 'test', '190', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000017', null, '2017-03-17 00:00:00', '2017-03-18 00:00:00', 'LEAVE', '0000006', '321', 'T_DOUBLE', 'yigeren', '200', 'CASH');
INSERT INTO `order_tbl` VALUES ('00000000019', '0000007', '2017-03-24 00:00:00', '2017-03-25 00:00:00', 'LEAVE', '0000011', '123', 'SINGLE', 'test', '95', 'VIP_CARD');
INSERT INTO `order_tbl` VALUES ('00000000020', null, '2017-03-18 00:00:00', '2017-03-19 00:00:00', 'IN', '0000011', '345', 'SINGLE', 'test', '100', 'CASH');

-- ----------------------------
-- Table structure for plan_tbl
-- ----------------------------
DROP TABLE IF EXISTS `plan_tbl`;
CREATE TABLE `plan_tbl` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `hotel_id` int(7) unsigned zerofill NOT NULL,
  `room_type` varchar(32) DEFAULT NULL,
  `room_count` int(11) DEFAULT NULL,
  `room_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pln_htl_fk` (`hotel_id`),
  CONSTRAINT `pln_htl_fk` FOREIGN KEY (`hotel_id`) REFERENCES `hotel_tbl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan_tbl
-- ----------------------------
INSERT INTO `plan_tbl` VALUES ('00000000001', '0000006', 'SINGLE', '11', '100');
INSERT INTO `plan_tbl` VALUES ('00000000002', '0000006', 'T_DOUBLE', '19', '200');
INSERT INTO `plan_tbl` VALUES ('00000000003', '0000006', 'TRIPLE', '29', '300');
INSERT INTO `plan_tbl` VALUES ('00000000004', '0000007', 'SINGLE', '20', '120');
INSERT INTO `plan_tbl` VALUES ('00000000005', '0000007', 'T_DOUBLE', '14', '300');
INSERT INTO `plan_tbl` VALUES ('00000000006', '0000007', 'TRIPLE', '10', '400');
INSERT INTO `plan_tbl` VALUES ('00000000007', '0000008', 'SINGLE', '10', '100');
INSERT INTO `plan_tbl` VALUES ('00000000008', '0000008', 'T_DOUBLE', '10', '200');
INSERT INTO `plan_tbl` VALUES ('00000000009', '0000008', 'TRIPLE', '9', '300');
INSERT INTO `plan_tbl` VALUES ('00000000013', '0000010', 'SINGLE', '9', '100');
INSERT INTO `plan_tbl` VALUES ('00000000014', '0000010', 'T_DOUBLE', '10', '200');
INSERT INTO `plan_tbl` VALUES ('00000000015', '0000010', 'TRIPLE', '10', '300');
INSERT INTO `plan_tbl` VALUES ('00000000016', '0000011', 'SINGLE', '9', '100');
INSERT INTO `plan_tbl` VALUES ('00000000017', '0000011', 'T_DOUBLE', '10', '200');
INSERT INTO `plan_tbl` VALUES ('00000000018', '0000011', 'TRIPLE', '10', '300');

-- ----------------------------
-- Table structure for vip_tbl
-- ----------------------------
DROP TABLE IF EXISTS `vip_tbl`;
CREATE TABLE `vip_tbl` (
  `id` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '1',
  `experience` int(11) NOT NULL DEFAULT '0',
  `score` int(11) NOT NULL DEFAULT '0',
  `state` varchar(32) NOT NULL,
  `bank_id` varchar(31) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `charge_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usrnm_key` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_tbl
-- ----------------------------
INSERT INTO `vip_tbl` VALUES ('0000001', '用户一', '2670', '2', '1330', '1330', 'ACTIVE', '1234567890', 'user1', '1234qwer', '2017-03-16 00:03:01');
INSERT INTO `vip_tbl` VALUES ('0000002', '用户二', '1290', '2', '1710', '1710', 'ACTIVE', '12345678901', 'user2', '1234qwer', '2017-03-15 18:53:46');
INSERT INTO `vip_tbl` VALUES ('0000003', '用户三', '3765', '2', '1235', '1235', 'ACTIVE', '123456789', 'user3', '1234qwer', '2017-03-15 23:28:39');
INSERT INTO `vip_tbl` VALUES ('0000004', '用户四', '0', '1', '0', '0', 'PRE_ACTIVE', '9876543210', 'user4', '1234qwer', null);
INSERT INTO `vip_tbl` VALUES ('0000006', '用户五', '4697', '1', '304', '204', 'ACTIVE', '98765432123', 'user5', '1234qwer', '2017-03-16 14:06:10');
INSERT INTO `vip_tbl` VALUES ('0000007', 'test', '29905', '1', '95', '95', 'STOP', '12345235254353', 'test', '1234qwer', '2017-03-16 20:32:25');
SET FOREIGN_KEY_CHECKS=1;
