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
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodityNo` varchar(255) DEFAULT NULL,
  `commodityName` varchar(255) DEFAULT NULL,
  `commodityType` varchar(255) DEFAULT NULL,
  `commodityPrice` varchar(255) DEFAULT NULL,
  `commodityDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
