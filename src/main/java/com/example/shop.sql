/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50726 (5.7.26)
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50726 (5.7.26)
 File Encoding         : 65001

 Date: 28/11/2024 12:42:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auser
-- ----------------------------
DROP TABLE IF EXISTS `auser`;
CREATE TABLE `auser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `apwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auser
-- ----------------------------

-- ----------------------------
-- Table structure for buser
-- ----------------------------
DROP TABLE IF EXISTS `buser`;
CREATE TABLE `buser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `bpwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bname`(`bname`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buser
-- ----------------------------
INSERT INTO `buser` VALUES (3, '张三', '$2a$10$sFZ.7Fv86pvx1evUsVco.eUgdYzeRquC9KVUAUoQCFUnauJJFwo2q');
INSERT INTO `buser` VALUES (4, '王五', '$2a$10$0wAtaaDQVrqd2cNz/RtD2udMUXXE5ou2szObWEPFCaBKtgITuQr5S');
INSERT INTO `buser` VALUES (5, '李四', '$2a$10$OmIaH0nmAI/nLpYKqdc9N.vOFucj7sssj3fmn40FhBgEsHr7BfNYu');
INSERT INTO `buser` VALUES (6, '刘六', '$2a$10$Uo3vHIiS2/3BIdyB0GdcJOhAtltBPOLlzEsiwq.6nxzRIVLzBZdNy');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bustable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  `shoppingnum` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bustable_id`(`bustable_id`) USING BTREE,
  INDEX `goodstable_id`(`goodstable_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (14, 3, 2, 3);
INSERT INTO `cart` VALUES (13, 3, 1, 4);
INSERT INTO `cart` VALUES (15, 3, 3, 3);
INSERT INTO `cart` VALUES (17, 6, 5, 1);

-- ----------------------------
-- Table structure for focus
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bustable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bustable_id`(`bustable_id`) USING BTREE,
  INDEX `goodstable_id`(`goodstable_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of focus
-- ----------------------------
INSERT INTO `focus` VALUES (12, 6, 3);
INSERT INTO `focus` VALUES (11, 6, 2);
INSERT INTO `focus` VALUES (8, 3, 5);
INSERT INTO `focus` VALUES (9, 3, 6);

-- ----------------------------
-- Table structure for goodstable
-- ----------------------------
DROP TABLE IF EXISTS `goodstable`;
CREATE TABLE `goodstable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `goprice` double NOT NULL,
  `grprice` double NOT NULL,
  `gstore` int(11) NOT NULL,
  `gpicture` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `goodstype_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goodstype_id`(`goodstype_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstable
-- ----------------------------
INSERT INTO `goodstable` VALUES (1, 'iPhone', 6000, 5500, 43, 'iPhone.png', 1);
INSERT INTO `goodstable` VALUES (2, 'macbook', 13123, 10000, 43, 'mac.png', 1);
INSERT INTO `goodstable` VALUES (3, '小米手机', 4999, 4599, 41, 'xiaomi.png', 1);
INSERT INTO `goodstable` VALUES (4, '《傲慢与偏见》', 20, 18, 95, 'aoman.png', 4);
INSERT INTO `goodstable` VALUES (5, '羽绒服', 500, 379, 2295, 'yurongfu.png', 2);
INSERT INTO `goodstable` VALUES (6, '椅子', 100, 79, 100, 'yizi.png', 3);

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (1, '电子产品');
INSERT INTO `goodstype` VALUES (2, '服装');
INSERT INTO `goodstype` VALUES (3, '家具');
INSERT INTO `goodstype` VALUES (4, '书籍');

-- ----------------------------
-- Table structure for orderbase
-- ----------------------------
DROP TABLE IF EXISTS `orderbase`;
CREATE TABLE `orderbase`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bustable_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `orderdate` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bustable_id`(`bustable_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderbase
-- ----------------------------
INSERT INTO `orderbase` VALUES (11, 3, 110000, 2, '2024-11-23 14:56:15');
INSERT INTO `orderbase` VALUES (12, 3, 15500, 2, '2024-11-26 10:50:25');
INSERT INTO `orderbase` VALUES (13, 3, 60297, 2, '2024-11-26 10:51:45');
INSERT INTO `orderbase` VALUES (14, 3, 60297, 2, '2024-11-26 10:53:42');
INSERT INTO `orderbase` VALUES (15, 3, 60297, 2, '2024-11-26 10:57:05');
INSERT INTO `orderbase` VALUES (16, 3, 20099, 2, '2024-11-26 16:45:13');
INSERT INTO `orderbase` VALUES (17, 3, 20099, 2, '2024-11-26 16:46:04');
INSERT INTO `orderbase` VALUES (18, 3, 1209, 2, '2024-11-26 21:44:44');
INSERT INTO `orderbase` VALUES (19, 3, 397, 2, '2024-11-26 22:23:21');
INSERT INTO `orderbase` VALUES (20, 6, 18396, 2, '2024-11-28 12:13:20');
INSERT INTO `orderbase` VALUES (21, 6, 31000, 2, '2024-11-28 12:14:17');
INSERT INTO `orderbase` VALUES (22, 6, 379, 2, '2024-11-28 12:39:43');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderbasetable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  `shoppingnum` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `orderbasetable_id`(`orderbasetable_id`) USING BTREE,
  INDEX `goodstable_id`(`goodstable_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES (8, 11, 1, 20);
INSERT INTO `orderdetail` VALUES (9, 12, 2, 1);
INSERT INTO `orderdetail` VALUES (10, 12, 1, 1);
INSERT INTO `orderdetail` VALUES (11, 14, 2, 3);
INSERT INTO `orderdetail` VALUES (12, 14, 1, 3);
INSERT INTO `orderdetail` VALUES (13, 14, 3, 3);
INSERT INTO `orderdetail` VALUES (14, 15, 2, 3);
INSERT INTO `orderdetail` VALUES (15, 15, 1, 3);
INSERT INTO `orderdetail` VALUES (16, 15, 3, 3);
INSERT INTO `orderdetail` VALUES (17, 16, 1, 1);
INSERT INTO `orderdetail` VALUES (18, 16, 2, 1);
INSERT INTO `orderdetail` VALUES (19, 16, 3, 1);
INSERT INTO `orderdetail` VALUES (20, 17, 1, 1);
INSERT INTO `orderdetail` VALUES (21, 17, 2, 1);
INSERT INTO `orderdetail` VALUES (22, 17, 3, 1);
INSERT INTO `orderdetail` VALUES (23, 18, 4, 4);
INSERT INTO `orderdetail` VALUES (24, 18, 5, 3);
INSERT INTO `orderdetail` VALUES (25, 19, 4, 1);
INSERT INTO `orderdetail` VALUES (26, 19, 5, 1);
INSERT INTO `orderdetail` VALUES (27, 20, 3, 4);
INSERT INTO `orderdetail` VALUES (28, 21, 1, 2);
INSERT INTO `orderdetail` VALUES (29, 21, 2, 2);
INSERT INTO `orderdetail` VALUES (30, 22, 5, 1);

SET FOREIGN_KEY_CHECKS = 1;
