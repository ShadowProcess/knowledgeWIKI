-- 创建数据库
create database if not exists `oauth2_client_db` default character set utf8 collate utf8_general_ci;
use oauth2_client_db;
-- 创建client端用户，这里可以与授权服务登录用户名和密码不一致
create table if not exists client_user
(
    id bigint auto_increment primary key,
    username              varchar(32) not null,
    `password`            varchar(64) not null,
    access_token          varchar(256) default null,
    validate_token_expire datetime,
    refresh_token         varchar(256) default null
);
-- 每次启动清空上次用户表，仅用于测试
truncate table client_user;
-- 插入一个与授权服务器密码不一致的用户
insert into client_user values (null,'hellxz','$2a$10$iAEfWxvrLKt6bhBdkNsdCeMn6JEqrS8c6MF/pbbeAeCxVrgkH92Re', null, null, null);
