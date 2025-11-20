/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : weblog

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 18/11/2025 20:58:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1990719298786979843 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'OAuth2 访问令牌' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth2_access_token
-- ----------------------------
INSERT INTO `oauth2_access_token` VALUES (1990339637187866626, 1, '8b039bb67fa245078312f7dd827b0cde', '2e05623329494de897f43d262441bd85', '2025-11-18 02:42:03', '', '2025-11-17 16:42:02', '', '2025-11-17 16:42:02', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990345457380257795, 1, '3a5200f68e5442058eac7b7cc57f6087', '7b72301f66084d3e998e1e82c9f516a4', '2025-11-18 03:05:11', '', '2025-11-17 17:05:10', '', '2025-11-17 17:05:10', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990349833796743170, 1, '584f8eb33fab483a848f5860b879da07', '5b4ada3b680e4b829db5c7823b15e2c9', '2025-11-18 03:22:34', '', '2025-11-17 17:22:33', '', '2025-11-17 17:22:33', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990354989229699074, 1, 'dc6215a8d3af4cf9b63d1fdb4f942604', '35ecce242fa6482087e496f6ae8662fa', '2025-11-18 03:43:03', '', '2025-11-17 17:43:03', '', '2025-11-17 17:43:03', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990365963630309379, 1, '4b693e3adedd47d69026284d1eb30782', 'e039acdd636344c3a4230160fc68b415', '2025-11-18 04:26:40', '', '2025-11-17 18:26:39', '', '2025-11-17 18:26:39', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990366331168780291, 1, '3b9673ccd3de42db9c38683f3a10b822', '04d0706b4a744e1284bbe5ee2e9ad94a', '2025-11-18 04:28:07', '', '2025-11-17 18:28:07', '', '2025-11-17 18:28:07', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990366386646839299, 1, '1f71bfbec3a547df90f47b5f862c0320', 'f337da784c6a41fa83e74c9e6d0d567f', '2025-11-18 04:28:20', '', '2025-11-17 18:28:20', '', '2025-11-17 18:28:20', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990366391927468034, 1, 'df308a7accb14c79abbf0b350df20096', '1863b0e08d4f42308ecf0fcfb49aded6', '2025-11-18 04:28:22', '', '2025-11-17 18:28:21', '', '2025-11-17 18:28:21', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990366659482120193, 1, '66ea699e34d74b7688fa075696529fcb', 'b915807d998d46c4b986069b5473977f', '2025-11-18 04:29:26', '', '2025-11-17 18:29:25', '', '2025-11-17 18:29:25', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990370664509763586, 1, '9209efca43dc4ba0b80e6f8f3ecee16c', '30068a4368064f21a1674764bf1c099a', '2025-11-18 04:45:20', '', '2025-11-17 18:45:20', '', '2025-11-17 18:45:20', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990370897855672322, 1, '2de2721da08e465ba25a2959a427262a', 'df88696511084c15ab86df8d93bdcd38', '2025-11-18 04:46:16', '', '2025-11-17 18:46:16', '', '2025-11-17 18:46:16', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990371012221759491, 1, '6757a9dfb2e0428593159eec549599b9', '09dc80394eec423cae5567731ea09c53', '2025-11-18 04:46:43', '', '2025-11-17 18:46:43', '', '2025-11-17 18:46:43', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990371227234365443, 1, '2e172589ffa9400680576a9db9183b91', 'ad0406bbf4df43f4a97b18b1c11fa5d5', '2025-11-18 04:47:35', '', '2025-11-17 18:47:34', '', '2025-11-17 18:47:34', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990371314761101314, 1, '75247978f4994266a8aa5a6ea85b196c', 'fb263346e7b24128abd0609d0c829920', '2025-11-18 04:47:55', '', '2025-11-17 18:47:55', '', '2025-11-17 18:47:55', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990674962955268098, 1, '792648b2aa5f4d259504360a851e4650', '58fb476c6acb4403b85b8f5bc8ec8b78', '2025-11-19 00:54:31', '', '2025-11-18 14:54:30', '', '2025-11-18 14:54:30', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990675716520701954, 1, '387a106a2ed74dcf90ca368db004387b', '9e31581ba1ab4a268eca532ac79d3148', '2025-11-19 00:57:30', '', '2025-11-18 14:57:30', '', '2025-11-18 14:57:30', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990679033531494402, 1, '05459eaa98924d61b1a77481e7c76054', '4512d1db72324e6bab48956e9883bb32', '2025-11-19 01:10:41', '', '2025-11-18 15:10:41', '', '2025-11-18 15:10:41', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990718775438503939, 1, '6d1247fdac1749fc980efc3e877110c7', 'ccaca80e8f4b47a6bc5b24a43d5646c7', '2025-11-19 03:48:36', '', '2025-11-18 17:48:36', '', '2025-11-18 17:48:36', b'0');
INSERT INTO `oauth2_access_token` VALUES (1990719298786979842, 1, '46bda521e00b4bcdb4a58a1bc7efe2e7', '68faec116ae94bbc8021dc2c8eef423c', '2025-11-19 03:50:41', '', '2025-11-18 17:50:41', '', '2025-11-18 17:50:41', b'0');

-- ----------------------------
-- Table structure for oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_refresh_token`;
CREATE TABLE `oauth2_refresh_token`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1990719298786979842 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'OAuth2 刷新令牌' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth2_refresh_token
-- ----------------------------
INSERT INTO `oauth2_refresh_token` VALUES (1990339637187866625, 1, '2e05623329494de897f43d262441bd85', '2025-11-21 20:42:03', '', '2025-11-17 16:42:02', '', '2025-11-17 16:42:02', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990345457380257794, 1, '7b72301f66084d3e998e1e82c9f516a4', '2025-11-21 21:05:11', '', '2025-11-17 17:05:10', '', '2025-11-17 17:05:10', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990349833796743169, 1, '5b4ada3b680e4b829db5c7823b15e2c9', '2025-11-21 21:22:34', '', '2025-11-17 17:22:33', '', '2025-11-17 17:22:33', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990354989229699073, 1, '35ecce242fa6482087e496f6ae8662fa', '2025-11-21 21:43:03', '', '2025-11-17 17:43:03', '', '2025-11-17 17:43:03', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990365963630309378, 1, 'e039acdd636344c3a4230160fc68b415', '2025-11-21 22:26:40', '', '2025-11-17 18:26:39', '', '2025-11-17 18:26:39', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990366331168780290, 1, '04d0706b4a744e1284bbe5ee2e9ad94a', '2025-11-21 22:28:07', '', '2025-11-17 18:28:07', '', '2025-11-17 18:28:07', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990366386646839298, 1, 'f337da784c6a41fa83e74c9e6d0d567f', '2025-11-21 22:28:20', '', '2025-11-17 18:28:20', '', '2025-11-17 18:28:20', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990366391927468033, 1, '1863b0e08d4f42308ecf0fcfb49aded6', '2025-11-21 22:28:22', '', '2025-11-17 18:28:21', '', '2025-11-17 18:28:21', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990366659415011329, 1, 'b915807d998d46c4b986069b5473977f', '2025-11-21 22:29:26', '', '2025-11-17 18:29:25', '', '2025-11-17 18:29:25', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990370664509763585, 1, '30068a4368064f21a1674764bf1c099a', '2025-11-21 22:45:20', '', '2025-11-17 18:45:20', '', '2025-11-17 18:45:20', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990370897855672321, 1, 'df88696511084c15ab86df8d93bdcd38', '2025-11-21 22:46:16', '', '2025-11-17 18:46:16', '', '2025-11-17 18:46:16', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990371012221759490, 1, '09dc80394eec423cae5567731ea09c53', '2025-11-21 22:46:43', '', '2025-11-17 18:46:43', '', '2025-11-17 18:46:43', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990371227234365442, 1, 'ad0406bbf4df43f4a97b18b1c11fa5d5', '2025-11-21 22:47:35', '', '2025-11-17 18:47:34', '', '2025-11-17 18:47:34', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990371314761101313, 1, 'fb263346e7b24128abd0609d0c829920', '2025-11-21 22:47:55', '', '2025-11-17 18:47:55', '', '2025-11-17 18:47:55', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990674962888159234, 1, '58fb476c6acb4403b85b8f5bc8ec8b78', '2025-11-22 18:54:31', '', '2025-11-18 14:54:30', '', '2025-11-18 14:54:30', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990675716520701953, 1, '9e31581ba1ab4a268eca532ac79d3148', '2025-11-22 18:57:30', '', '2025-11-18 14:57:30', '', '2025-11-18 14:57:30', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990679033531494401, 1, '4512d1db72324e6bab48956e9883bb32', '2025-11-22 19:10:41', '', '2025-11-18 15:10:41', '', '2025-11-18 15:10:41', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990718775438503938, 1, 'ccaca80e8f4b47a6bc5b24a43d5646c7', '2025-11-22 21:48:36', '', '2025-11-18 17:48:36', '', '2025-11-18 17:48:36', b'0');
INSERT INTO `oauth2_refresh_token` VALUES (1990719298786979841, 1, '68faec116ae94bbc8021dc2c8eef423c', '2025-11-22 21:50:41', '', '2025-11-18 17:50:41', '', '2025-11-18 17:50:41', b'0');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'wsw', '$2a$10$KGH/YLeBnywMMTMqfh7pNu24dOFESxfw1wtdApNr6pRrO1ipTeq4m', '阿威小天才', '17788888888', NULL, 1, '', NULL, '', '2025-11-17 16:31:55', '', '2025-11-17 16:31:55', b'0', '备注');

SET FOREIGN_KEY_CHECKS = 1;
