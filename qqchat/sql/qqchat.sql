/*
Navicat MySQL Data Transfer

Source Server         : maple
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : qqchat

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2022-02-13 22:26:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `role` varchar(255) DEFAULT NULL COMMENT '角色',
  `avatar_image` varchar(500) DEFAULT '/profile/upload/2021/10/28/088d4925-7411-4447-90d8-3239eaee68f2.gif' COMMENT '头像',
  `creator_id` bigint(20) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `age` int(11) DEFAULT '18' COMMENT '年龄',
  `sex` varchar(10) DEFAULT '男' COMMENT '性别',
  `clock_frequency` int(11) DEFAULT '0' COMMENT '总打卡次数',
  `status` varchar(20) DEFAULT 'valid' COMMENT '状态 ''UNKNOWN'': 未知 ''valid''：有效，''invalid''：无效',
  `user_type` int(11) DEFAULT '0' COMMENT '0:C端，1：后台',
  `is_deleted` tinyint(2) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', 'maple', '$2a$10$kZ6Sah0iC5Ycg3I2zOFnWOSRHJO4b/sVZAl72/MXxMwgMYAmQkLT6', '2496155694@qq.com', 'consumer', '/profile/upload/2021/12/05/31659405-f2a1-43d9-aac4-6421042b103c.jpeg', '0', '2021-11-01 19:21:20', '2021-12-05 13:44:11', '18', '男', '0', 'valid', '0', '0');
INSERT INTO `user` VALUES ('14', 'maple2', '$2a$10$53fK/YBE9xHbwuaC2Oh5ZeFMsxuTTuQJwp58jD7f.4Gbsfgp.etFq', 'zhuzhihong@homer-life.com', 'consumer', '/profile/upload/2022/02/12/37c8ce33-b380-4725-943d-0c85abd4e8fa.png', '0', '2021-12-02 22:26:35', '2022-02-12 20:56:43', '18', '男', '0', 'valid', '0', '0');
INSERT INTO `user` VALUES ('15', 'maple3', '$2a$10$53fK/YBE9xHbwuaC2Oh5ZeFMsxuTTuQJwp58jD7f.4Gbsfgp.etFq', 'zhuzhihong3@homer-life.com', 'consumer', '/profile/upload/2022/02/12/37c8ce33-b380-4725-943d-0c85abd4e8fa.png', '0', '2021-12-02 22:26:35', '2022-02-12 20:56:43', '23', '男', '0', 'valid', '0', '0');
