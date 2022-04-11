drop table if exists member;

create table member
(
    id       int primary key auto_increment,
    user_id  varchar(500),
    password varchar(500),
    name     varchar(500)
);

drop table if exists task_card;

create table task_card
(
    id           int primary key auto_increment,
    status       int          not null,
    title        varchar(500) not null,
    content      varchar(500),
    user_id      varchar(500) not null,
    device       int          not null,
    created_at   datetime     not null,
    modified_at  datetime,
    deleted_yn   boolean      not null,
    row_position int          not null
);

drop table if exists activity_log;

create table activity_log
(
    id            int primary key auto_increment,
    user_id       varchar(500) not null,
    activity_type varchar(500) not null COMMENT 'ADD, REMOVE, UPDATE, MOVE',
    task_title    varchar(500) not null,
    from_status   varchar(500) COMMENT '변경 전 상태',
    to_status     varchar(500) COMMENT '변경 후 상태',
    created_at    datetime     not null,
    read_yn       boolean      not null COMMENT '읽기 여부'
);
