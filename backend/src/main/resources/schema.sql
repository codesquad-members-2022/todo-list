set foreign_key_checks = 0;

drop table if exists `user` cascade;
create table `user`
(
    user_id       bigint(10) not null auto_increment primary key,
    user_name     char(200)  not null,
    profile_image mediumblob,
    deleted       boolean    not null default false
);

drop table if exists `column` cascade;
create table `column`
(
    column_id    bigint(10) not null auto_increment primary key,
    user_id      bigint(10) not null,
    column_name  char(200)  not null,
    column_order int(3)     not null,
    deleted      boolean    not null default false,
    FOREIGN KEY (user_id) references `user` (user_id)
);

drop table if exists `card` cascade;
create table `card`
(
    card_id      bigint(10) not null auto_increment primary key,
    column_id    bigint(10) not null,
    author       char(200)  not null,
    created_date timestamp  not null,
    title        char(200)  not null,
    content      char(255),
    card_order   int(10)    not null,
    deleted      boolean    not null default false,
    FOREIGN KEY (column_id) references `column` (column_id)
);

drop table if exists history cascade;
create table history
(
    history_id   bigint(10) not null auto_increment primary key,
    user_id      bigint(10) not null,
    created_date timestamp  not null,
    title        char(200),
    column_name  char(200),
    field        char(100),
    old_value    char(200),
    action       char(20)   not null,
    FOREIGN KEY (user_id) references user (user_id)
);
set foreign_key_checks = 1;
