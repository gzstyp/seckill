/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.66
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.1.66:3306
 Source Schema         : seckill-redis

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/05/2020 01:30:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_seckill
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill`;
CREATE TABLE `t_seckill`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sku` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `num` int(0) UNSIGNED NULL DEFAULT NULL,
  `price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_seckill
-- ----------------------------
INSERT INTO `t_seckill` VALUES (1, '1024', 100, 20.00);

SET FOREIGN_KEY_CHECKS = 1;
