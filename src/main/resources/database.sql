-- 创建数据库
CREATE DATABASE IF NOT EXISTS mybatis
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;
-- 创建员工表
CREATE TABLE `employee` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` VARCHAR(96) NOT NULL DEFAULT '' COMMENT '密码',
  `sex` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '性别(0-女, 1-男)',
  `birthday` INT(11) NOT NULL DEFAULT 0 COMMENT '生日',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_username` (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '员工表';