drop database if exists bot_base;
create database if not exists bot_base;
use bot_base;
create table if not exists bot_user
(
    id           int primary key auto_increment,
    user_chat_id bigint       not null,
    user_name    varchar(200) not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

create table if not exists bot_user_application
(
    id               int primary key auto_increment,
    application_text varchar(1500) not null,
    date_application datetime      not null,
    chat_id          bigint        not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;