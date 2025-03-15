-- 添加注册IP字段
ALTER TABLE `user` ADD COLUMN `register_ip` VARCHAR(64) COMMENT '注册IP地址' AFTER `last_login_ip`;

-- 为已有的记录设置空值
UPDATE `user` SET `register_ip` = '' WHERE `register_ip` IS NULL; 