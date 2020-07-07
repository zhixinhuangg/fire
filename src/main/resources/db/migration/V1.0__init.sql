/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-11-30 16:35:34
*/

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_no` varchar(255) DEFAULT NULL,
  `commodity_name` varchar(255) DEFAULT NULL,
  `commodity_type` varchar(255) DEFAULT NULL,
  `commodity_price` varchar(255) DEFAULT NULL,
  `commodity_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sysLog`;
CREATE TABLE `sysLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
