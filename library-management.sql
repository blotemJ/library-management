/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : library-management

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 18/02/2024 13:19:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_key`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '86fcb4c0551ea48ede7df5ed9626eee7', '13675988277', '136@qq.com', '2023-10-02 14:44:53', '2023-10-04 22:54:52', 1);
INSERT INTO `admin` VALUES (2, 'admin1', '86fcb4c0551ea48ede7df5ed9626eee7', '13076554776', '1772390822@qq.com', '2023-10-04 18:23:47', '2023-10-04 22:55:14', 1);
INSERT INTO `admin` VALUES (4, 'admin2', '86fcb4c0551ea48ede7df5ed9626eee7', '13697564235', '13994@qq.com', '2023-10-05 00:24:40', '2023-10-05 00:24:54', 1);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `publish_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版日期',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版社',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `book_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准码',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `cover` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `score` int(0) NULL DEFAULT NULL COMMENT '积分',
  `nums` int(0) NULL DEFAULT 0 COMMENT '图书数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_key`(`book_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (3, '三等', '微软', '2023-10-09', '2', '343', '历史文化 > 唐朝文化', '31', '2023-10-06 15:53:05', '2023-10-06 22:11:05', 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', 12, 10);
INSERT INTO `book` VALUES (29, '三体', '一本好书', '2023-10-18', '网络', '3の', '体育 > 运动健康', 'IBSN123', '2023-10-07 21:27:44', '2023-10-07 21:48:58', 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', 36, 11);
INSERT INTO `book` VALUES (30, '了呢了', '一本好书', '2023-10-10', '中国人', '北京', '体育 > 运动健康', 'ISBN123456', '2023-10-08 16:08:05', '2023-10-10 13:51:11', 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', 13, 0);
INSERT INTO `book` VALUES (32, '阿斯顿', '轻微', '2023-10-19', '23', '4方法', '体育 > 运动健康', 'ISBN196', '2023-10-10 14:17:15', NULL, 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', 10, 100);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '书号',
  `user_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户会员号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '借书时间',
  `updatetime` datetime(0) NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL COMMENT '借书积分',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已借出' COMMENT '借书状态',
  `days` int(0) NULL DEFAULT 1 COMMENT '借书天数',
  `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (15, '三等', '31', '1555546786', '菲菲', '14725836912', '2023-10-15 22:58:20', NULL, 24, '已归还', 2, '2023-10-17 00:00:00');
INSERT INTO `borrow` VALUES (16, '了呢了', 'ISBN123456', '1555546786', '菲菲', '14725836912', '2023-10-15 23:00:18', NULL, 26, '已归还', 2, '2023-10-17 00:00:00');
INSERT INTO `borrow` VALUES (17, '三等', '31', '1556546780', '李四', '12345678912', '2023-10-15 23:01:43', NULL, 24, '已归还', 2, '2023-10-17 00:00:00');
INSERT INTO `borrow` VALUES (18, '了呢了', 'ISBN123456', '1555546789', '阿里郎', '12345678912', '2023-10-17 15:20:45', NULL, 13, '已借出', 1, '2023-10-18 00:00:00');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `pid` int(0) NULL DEFAULT NULL COMMENT '父级id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updatetime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '历史文化', '历史类图书', NULL, '2023-10-05 02:04:46', '2023-10-05 14:06:26');
INSERT INTO `category` VALUES (2, '体育', '体育类图书', NULL, '2023-10-05 14:07:41', NULL);
INSERT INTO `category` VALUES (3, '唐朝文化', '唐朝的历史图书', 1, '2023-10-05 14:45:06', NULL);
INSERT INTO `category` VALUES (4, '现代文化', NULL, 1, '2023-10-05 15:15:08', NULL);
INSERT INTO `category` VALUES (5, '运动健康', '健康的图书', 2, '2023-10-05 15:19:56', NULL);

-- ----------------------------
-- Table structure for retur
-- ----------------------------
DROP TABLE IF EXISTS `retur`;
CREATE TABLE `retur`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '书号',
  `user_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户会员号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '借书时间',
  `updatetime` datetime(0) NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL COMMENT '借书积分',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已借出' COMMENT '借书状态',
  `days` int(0) NULL DEFAULT 1 COMMENT '借书天数',
  `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还日期',
  `real_date` datetime(0) NULL DEFAULT NULL COMMENT '实际归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of retur
-- ----------------------------
INSERT INTO `retur` VALUES (14, '三等', '31', '1555546786', '菲菲', '14725836912', '2023-10-15 22:58:33', NULL, 24, '已借出', 2, '2023-10-17 00:00:00', '2023-10-15 00:00:00');
INSERT INTO `retur` VALUES (15, '了呢了', 'ISBN123456', '1555546786', '菲菲', '14725836912', '2023-10-15 23:00:33', NULL, 26, '已借出', 2, '2023-10-17 00:00:00', '2023-10-15 00:00:00');
INSERT INTO `retur` VALUES (16, '三等', '31', '1556546780', '李四', '12345678912', '2023-10-15 23:01:50', NULL, 24, '已归还', 2, '2023-10-17 00:00:00', '2023-10-15 00:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `account` int(0) NULL DEFAULT NULL COMMENT '账户积分余额',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '禁用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_index`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '菲菲', '1555546786', 21, '女', '14725836912', '北京', '2023-09-25 16:55:35', '2023-10-15 22:52:46', 77, 1);
INSERT INTO `user` VALUES (2, '阿里郎', '1555546789', 21, '男', '12345678912', '上海', '2023-09-25 16:55:35', '2023-10-15 22:50:25', 14, 1);
INSERT INTO `user` VALUES (3, '张三', '1555546980', 18, '男', '14725836912', '北京', '2023-09-25 16:55:35', '2023-10-15 22:50:22', 47, 1);
INSERT INTO `user` VALUES (4, '李四', '1556546780', 18, '男', '12345678912', '上海', '2023-09-25 16:55:35', '2023-10-15 22:49:45', 182, 1);
INSERT INTO `user` VALUES (5, '张三', '1555546790', 18, '男', '14725836912', '北京', '2023-09-25 16:55:35', '2023-09-25 16:55:35', 88, 1);
INSERT INTO `user` VALUES (6, '张章', '1555565780', 18, '男', '12345678912', '上海', '2023-09-25 16:55:35', '2023-09-26 16:55:35', 88, 1);

SET FOREIGN_KEY_CHECKS = 1;
