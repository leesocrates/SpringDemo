CREATE TABLE menu (
name varchar(200) NOT NULL,
menu_desc varchar(1000) NOT NULL,
user_id varchar(200) NOT NULL,
id bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
img_url varchar(200),
create_time date,
update_time date,
PRIMARY KEY (`id`),
KEY `idx_update_time` (`update_time`),
KEY `idx_user_id` (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单数据';