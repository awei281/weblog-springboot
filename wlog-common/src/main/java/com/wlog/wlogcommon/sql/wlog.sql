/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯119.45.233.151
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 119.45.233.151:13306
 Source Schema         : wlog

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 02/12/2025 18:18:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
  `cover` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章封面',
  `summary` varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '文章摘要',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态',
  `push_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
  `read_num` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '被阅读次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (16, 'vue小打小闹奇幻之旅', 'http://119.45.233.151:19000/wlog/38677ef617da4ff5a6c14b91c5ffb22e.jpg', 'vue小打小闹奇幻之旅\n今天开始就要记录vue的学习过程了', NULL, NULL, '2025-11-29 14:44:24', '2025-11-29 14:44:24', 0, 1);
INSERT INTO `t_article` VALUES (17, '宝塔操作手册', 'http://119.45.233.151:19000/wlog/55492a80cc514cf7b7fd1bd6b9e89dc6.jpg', '宝塔操作', NULL, NULL, '2025-12-02 17:25:26', '2025-12-02 17:25:26', 0, 1);

-- ----------------------------
-- Table structure for t_article_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_category_rel`;
CREATE TABLE `t_article_category_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章id',
  `category_id` bigint(20) UNSIGNED NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_article_id`(`article_id`) USING BTREE,
  INDEX `idx_category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章所属分类关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_article_category_rel
-- ----------------------------
INSERT INTO `t_article_category_rel` VALUES (13, 13, 8);
INSERT INTO `t_article_category_rel` VALUES (14, 14, 5);
INSERT INTO `t_article_category_rel` VALUES (15, 16, 4);
INSERT INTO `t_article_category_rel` VALUES (16, 17, 7);

-- ----------------------------
-- Table structure for t_article_content
-- ----------------------------
DROP TABLE IF EXISTS `t_article_content`;
CREATE TABLE `t_article_content`  (
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '教程正文',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章内容表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_article_content
-- ----------------------------
INSERT INTO `t_article_content` VALUES (13, 13, '请输入内容测试文章');
INSERT INTO `t_article_content` VALUES (14, 14, '#### 请输入内容测试文章测试文章测试文章测试文章测试文章测试文章测试文章测试文章测试文章\n\n测试文章测试文章测试文章测试文章测试文章\n\n\n\n```language\nnew object ()\n```\n\n![](http://119.45.233.151:19000/wlog/d80d3d15f2644aab9dada3adc21b79d7.jpg)\n\n[这是百度连接](![](www.baidu.com)\n)\n\n');
INSERT INTO `t_article_content` VALUES (16, 16, '> 2025年11月29日14:43:58\n\n这是第一次完成前段页面开始...\n\n');
INSERT INTO `t_article_content` VALUES (17, 17, '# 腾讯云宝塔操作手册\n## 安装版本信息\n> 系统：OpenCloudOS 9.4 x86_64(Py3.7.16)\n\n腾讯获取宝塔最新请求地址\n>  /etc/init.d/bt default');

-- ----------------------------
-- Table structure for t_article_tag_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag_rel`;
CREATE TABLE `t_article_tag_rel`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章id',
  `tag_id` bigint(20) UNSIGNED NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_article_id`(`article_id`) USING BTREE,
  INDEX `idx_tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章对应标签关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_article_tag_rel
-- ----------------------------
INSERT INTO `t_article_tag_rel` VALUES (1, 4, 11);
INSERT INTO `t_article_tag_rel` VALUES (2, 5, 12);
INSERT INTO `t_article_tag_rel` VALUES (3, 6, 13);
INSERT INTO `t_article_tag_rel` VALUES (4, 7, 14);
INSERT INTO `t_article_tag_rel` VALUES (5, 7, 15);
INSERT INTO `t_article_tag_rel` VALUES (6, 8, 16);
INSERT INTO `t_article_tag_rel` VALUES (7, 8, 14);
INSERT INTO `t_article_tag_rel` VALUES (8, 8, 11);
INSERT INTO `t_article_tag_rel` VALUES (9, 9, 11);
INSERT INTO `t_article_tag_rel` VALUES (10, 10, 15);
INSERT INTO `t_article_tag_rel` VALUES (11, 11, 17);
INSERT INTO `t_article_tag_rel` VALUES (12, 12, 18);
INSERT INTO `t_article_tag_rel` VALUES (13, 13, 19);
INSERT INTO `t_article_tag_rel` VALUES (14, 14, 20);
INSERT INTO `t_article_tag_rel` VALUES (15, 14, 21);
INSERT INTO `t_article_tag_rel` VALUES (16, 14, 22);
INSERT INTO `t_article_tag_rel` VALUES (17, 14, 23);
INSERT INTO `t_article_tag_rel` VALUES (18, 16, 5);
INSERT INTO `t_article_tag_rel` VALUES (19, 17, 24);

-- ----------------------------
-- Table structure for t_blog_settings
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_settings`;
CREATE TABLE `t_blog_settings`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `logo` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '博客Logo',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '博客名称',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者名',
  `introduction` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '介绍语',
  `avatar` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者头像',
  `github_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
  `csdn_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'CSDN 主页访问地址',
  `gitee_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'Gitee 主页访问地址',
  `zhihu_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '知乎主页访问地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客设置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_settings
-- ----------------------------

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志位：0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('前端', 4, '2025-11-26 22:14:05', '2025-11-26 22:14:05', 0);
INSERT INTO `t_category` VALUES ('后端', 5, '2025-11-26 22:14:10', '2025-11-26 22:14:10', 0);
INSERT INTO `t_category` VALUES ('设计', 6, '2025-11-26 22:14:16', '2025-11-26 22:14:16', 0);
INSERT INTO `t_category` VALUES ('运维', 7, '2025-11-26 22:14:21', '2025-11-26 22:14:21', 0);
INSERT INTO `t_category` VALUES ('日记', 8, '2025-11-26 22:14:25', '2025-11-26 22:14:25', 0);

-- ----------------------------
-- Table structure for t_oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `t_oauth2_access_token`;
CREATE TABLE `t_oauth2_access_token`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问令牌',
  `refresh_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_access_token`(`access_token`) USING BTREE,
  INDEX `idx_refresh_token`(`refresh_token`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1995774720149549058 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'OAuth2 访问令牌' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_oauth2_access_token
-- ----------------------------
INSERT INTO `t_oauth2_access_token` VALUES (2, 1993683976065159169, '4156b782f53a4dbeb46c2f9a836d29a4', 'e57afe0261a04d0f87afe4c47c4c9bb3', '2025-11-27 08:11:15', '', '2025-11-26 22:11:15', '', '2025-11-26 22:11:15', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1993986523178340353, '188a8db2e43e4d5bbe041ba932c7f393', 'bc569276a1854ae1abb331904d453295', '2025-11-28 04:13:28', '', '2025-11-27 18:13:33', '', '2025-11-27 18:13:33', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1994056639765905410, '06f053a792144ba38be5b1e065438c4f', '859ff3b0dc784932a25b00e92446ce37', '2025-11-28 08:52:05', '', '2025-11-27 22:52:05', '', '2025-11-27 22:52:05', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1994242334366556161, '13d9994e8dcd4cd1bccb0ad04b0ead01', '61efa9cb9658440fadc84908861048ee', '2025-11-28 21:09:58', '', '2025-11-28 11:10:00', '', '2025-11-28 11:10:00', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1994242405279653890, 'd773c5a15f56415f9b470d9cffc5c0eb', 'b57f75c1ec3b4fed8c267b6ac6dd03a1', '2025-11-28 21:10:15', '', '2025-11-28 11:10:17', '', '2025-11-28 11:10:17', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1994591978439204866, 'c82492df67c74eb68a20d27a9fd6a80a', '14bb8a5ddea840e280c17726b96977bb', '2025-11-29 20:19:20', '', '2025-11-29 10:19:24', '', '2025-11-29 10:19:24', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1995373528554610689, '05beaddd332b46e486236f4dbdb843db', '16f9c40d24f4493a900f1f91ff33cecf', '2025-12-02 00:04:56', '', '2025-12-01 14:04:56', '', '2025-12-01 14:04:56', b'0');
INSERT INTO `t_oauth2_access_token` VALUES (2, 1995774720149549057, '883f5d35f153425e9656228981e6182e', 'ed78dd01906c47b8a0df76c234906ceb', '2025-12-03 02:39:08', '', '2025-12-02 16:39:08', '', '2025-12-02 16:39:08', b'0');

-- ----------------------------
-- Table structure for t_oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `t_oauth2_refresh_token`;
CREATE TABLE `t_oauth2_refresh_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `refresh_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1995774719977582594 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'OAuth2 刷新令牌' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_oauth2_refresh_token
-- ----------------------------
INSERT INTO `t_oauth2_refresh_token` VALUES (1993683975830278145, 2, 'e57afe0261a04d0f87afe4c47c4c9bb3', '2025-12-01 02:11:15', '', '2025-11-26 22:11:15', '', '2025-11-26 22:11:15', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1993986522863767554, 2, 'bc569276a1854ae1abb331904d453295', '2025-12-01 22:13:28', '', '2025-11-27 18:13:33', '', '2025-11-27 18:13:33', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1994056639560384513, 2, '859ff3b0dc784932a25b00e92446ce37', '2025-12-02 02:52:05', '', '2025-11-27 22:52:04', '', '2025-11-27 22:52:04', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1994242334186201089, 2, '61efa9cb9658440fadc84908861048ee', '2025-12-02 15:09:58', '', '2025-11-28 11:10:00', '', '2025-11-28 11:10:00', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1994242405015412738, 2, 'b57f75c1ec3b4fed8c267b6ac6dd03a1', '2025-12-02 15:10:15', '', '2025-11-28 11:10:17', '', '2025-11-28 11:10:17', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1994591978271432705, 2, '14bb8a5ddea840e280c17726b96977bb', '2025-12-03 14:19:20', '', '2025-11-29 10:19:24', '', '2025-11-29 10:19:24', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1995373528374255618, 2, '16f9c40d24f4493a900f1f91ff33cecf', '2025-12-05 18:04:56', '', '2025-12-01 14:04:56', '', '2025-12-01 14:04:56', b'0');
INSERT INTO `t_oauth2_refresh_token` VALUES (1995774719977582593, 2, 'ed78dd01906c47b8a0df76c234906ceb', '2025-12-06 20:39:08', '', '2025-12-02 16:39:08', '', '2025-12-02 16:39:08', b'0');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `deleted` tinyint(2) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志位：0：未删除 1：已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (4, 'java', '2025-11-26 22:15:15', '2025-11-26 22:15:14', 0);
INSERT INTO `t_tag` VALUES (5, 'vue', '2025-11-26 22:15:19', '2025-11-26 22:15:19', 0);
INSERT INTO `t_tag` VALUES (6, 'js', '2025-11-26 22:15:23', '2025-11-26 22:15:23', 0);
INSERT INTO `t_tag` VALUES (7, 'docke', '2025-11-26 22:15:38', '2025-11-26 22:15:38', 0);
INSERT INTO `t_tag` VALUES (8, 'mysql', '2025-11-26 22:15:46', '2025-11-26 22:15:45', 0);
INSERT INTO `t_tag` VALUES (9, 'css', '2025-11-26 22:16:25', '2025-11-26 22:16:24', 0);
INSERT INTO `t_tag` VALUES (19, '4', '2025-11-29 10:36:11', '2025-11-29 10:36:14', 1);
INSERT INTO `t_tag` VALUES (20, '5', '2025-11-29 14:19:13', '2025-11-29 14:19:13', 1);
INSERT INTO `t_tag` VALUES (21, '7', '2025-11-29 14:19:13', '2025-11-29 14:19:13', 1);
INSERT INTO `t_tag` VALUES (22, '8', '2025-11-29 14:19:13', '2025-11-29 14:19:13', 1);
INSERT INTO `t_tag` VALUES (23, '6', '2025-11-29 14:19:13', '2025-11-29 14:19:13', 1);
INSERT INTO `t_tag` VALUES (24, '宝塔', '2025-12-02 17:25:26', '2025-12-02 17:25:26', 0);

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_type` int(4) NULL DEFAULT NULL COMMENT '1001-计划 1002-进行中 1003-已完成 1004-搁置',
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `task_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `fulfil_date` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `urgency_type` int(4) NULL DEFAULT NULL COMMENT '紧急程度',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES (2, 11003, '完成后台文件上传', '2025-11-26 22:11:51', '2025-11-27 22:52:12', '2', '2025-11-26 22:11:51', '', '2025-11-27 22:52:12', b'0', 11012, NULL);
INSERT INTO `t_task` VALUES (3, 11003, '对接文章相关接口', '2025-11-26 22:12:10', '2025-11-27 22:52:11', '2', '2025-11-26 22:12:10', '', '2025-11-27 22:52:11', b'0', 11013, NULL);
INSERT INTO `t_task` VALUES (4, 11003, '完成文章的联调', '2025-11-28 11:10:27', '2025-12-01 14:05:00', '2', '2025-11-28 11:10:27', '', '2025-12-01 14:05:00', b'0', 11014, NULL);
INSERT INTO `t_task` VALUES (5, 11004, '28号中午背单词', '2025-11-28 11:10:47', NULL, '2', '2025-11-28 11:10:47', '', '2025-11-29 10:19:30', b'0', 11013, NULL);
INSERT INTO `t_task` VALUES (6, 11003, '1201单词复习加20个新单词', '2025-12-01 14:05:23', '2025-12-01 15:28:08', '2', '2025-12-01 14:05:23', '', '2025-12-01 15:28:08', b'0', 11013, NULL);
INSERT INTO `t_task` VALUES (7, 11002, '完成11模块的开发', '2025-12-01 14:06:21', NULL, '2', '2025-12-01 14:06:21', '', '2025-12-01 14:06:27', b'0', 11011, NULL);
INSERT INTO `t_task` VALUES (8, 11002, '现有的代码部署到云服务器', '2025-12-02 16:40:59', NULL, '2', '2025-12-02 16:40:59', '', '2025-12-02 16:41:02', b'0', 11014, NULL);

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`  (
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '性别',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '启用状态（0正常 1停用）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('wsw', 2, '$2a$10$NnY0PISqP0vTI3b8i1wmcuUAiYa7bIIG9HLyp3ksqHzAJvgN7l89q', '地锅鸡king', '17712345678', NULL, 1, '', NULL, '', '2025-11-26 22:11:02', '', '2025-11-28 11:10:08', b'0', '备注');

SET FOREIGN_KEY_CHECKS = 1;
