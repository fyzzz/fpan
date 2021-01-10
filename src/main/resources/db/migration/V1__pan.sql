DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(50) DEFAULT NULL COMMENT '文件路径',
  `file_upload_name` varchar(50) DEFAULT NULL COMMENT '文件上传名',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='文件信息';
alter table file_info
	add user_id int null comment '归属人id' after id;


--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(256) DEFAULT NULL COMMENT '账号',
  `user_password` varchar(256) DEFAULT NULL COMMENT '用户密码',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除;0-未删除;1-已删除',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
INSERT INTO `user_info` (`id`, `user_name`, `user_password`, `is_delete`, `last_login_time`, `create_time`, `update_time`) VALUES (1,'fyzzz','$2a$10$Qo//yxxqnSsqBBUupz6fMOkjY4o3RsiOC3NLNvYE92f4IsBYFX7/q',0,'2019-05-17 20:00:49','2019-05-17 20:00:49','2019-05-17 20:00:49');
UNLOCK TABLES;


