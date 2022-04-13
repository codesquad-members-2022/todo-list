<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 6ab2caa6612fa739a98b8c511841880b8bf9d3b4
drop table if exists card;
drop table if exists history;
drop table if exists member;

create table card
<<<<<<< HEAD
=======
=======
drop table if exists member;
drop table if exists card;
drop table if exists `column`;
drop table if exists history;

## MEMBER
CREATE TABLE member
>>>>>>> main
>>>>>>> 6ab2caa6612fa739a98b8c511841880b8bf9d3b4
(
    id               bigint       not null
        primary key auto_increment,
    writer           varchar(10)  null,
    position         bigint       not null,
    visible          bit          null,
    title            varchar(255) not null,
    content          blob         not null,
    card_type        varchar(25)  not null,
    created_at       datetime     not null,
    last_modified_at datetime     null,
    member_id        bigint       not null
);

create table history
(
    id         bigint auto_increment
        primary key auto_increment,
    content    varchar(255) not null,
    created_at datetime(6)  null,
    action     varchar(10)  not null,
    member_id  bigint       not null,
    card_id    bigint       not null,
    font       varchar(10)  not null,
    visible    bit          null
);

create table member
(
    id              bigint auto_increment
        primary key auto_increment,
    created_at      datetime(6)  not null,
    member_login_id varchar(10)  not null,
    password        varchar(15)  not null,
    nick_name       varchar(255) not null,
    visible         bit          null
);

INSERT INTO card (writer, position, visible, title, content, card_type, created_at, last_modified_at, member_id)
VALUES ('Bong', 1, true, '', '', 'progressingItems', '2022-04-12 18:16:53', null, 0);
INSERT INTO card (writer, position, visible, title, content, card_type, created_at, last_modified_at, member_id)
VALUES ('Kai', 2, true, '', '', 'todoItems', '2022-04-12 18:16:53', null, 0);
INSERT INTO card (writer, position, visible, title, content, card_type, created_at, last_modified_at, member_id)
VALUES ('Terry', 2, true, '', '', 'completedItems', '2022-04-12 18:16:56', null, 0);
<<<<<<< HEAD

INSERT INTO member (created_at, member_login_id, password, nick_name, visible)
VALUES ('2022-04-03 14:52:44', 'bongbong', '1234', 'BongBong', true);
INSERT INTO member (created_at, member_login_id, password, nick_name, visible)
VALUES ('2022-04-02 14:52:44', 'kaikai', '1234', 'Kai', true);

=======

<<<<<<< HEAD
INSERT INTO member (created_at, member_login_id, password, nick_name, visible)
VALUES ('2022-04-03 14:52:44', 'bongbong', '1234', 'BongBong', true);
INSERT INTO member (created_at, member_login_id, password, nick_name, visible)
VALUES ('2022-04-02 14:52:44', 'kaikai', '1234', 'Kai', true);

>>>>>>> 6ab2caa6612fa739a98b8c511841880b8bf9d3b4
INSERT INTO history (content, created_at, action, member_id, card_id, font, visible)
VALUES ('hello', '2022-04-12 18:08:12.474219', 'DELETE', 1, 1, 'Helvetica', true);
INSERT INTO history (content, created_at, action, member_id, card_id, font, visible)
VALUES ('hello', '2022-04-12 21:10:19.311098', 'DELETE', 1, 1, 'Helvetica', true);
INSERT INTO history (content, created_at, action, member_id, card_id, font, visible)
VALUES ('hello', '2022-04-12 21:15:23.385740', 'DELETE', 1, 1, 'Helvetica', true);
INSERT INTO history (content, created_at, action, member_id, card_id, font, visible)
VALUES ('hello', '2022-04-12 21:15:35.878344', 'DELETE', 1, 1, 'Helvetica', true);
INSERT INTO history (content, created_at, action, member_id, card_id, font, visible)
VALUES ('hello', '2022-04-12 21:15:36.531356', 'DELETE', 1, 1, 'Helvetica', true);
<<<<<<< HEAD
=======
=======
## HISTORY
CREATE TABLE history
(
    id         bigint       NOT NULL PRIMARY KEY,
    content    VARCHAR(255) NOT NULL,
    created_at datetime(6)  NULL,
    writer     VARCHAR(10)  NOT NULL,
    action     VARCHAR(10)  NOT NULL,
    member_id  bigint       NOT NULL,
    font       VARCHAR(10)  NOT NULL,
    visible    BIT
);

use todo_list;

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (1, 'jun', '1234', 'jun@gmail.com', '2020-12-31', '2020-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (2, 'bong', '1234', 'bong@gmail.com', '2021-12-31', '2021-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (3, 'kai', '1234', 'kai@gmail.com', '2021-12-31', '2021-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (4, 'pigbag', '1234', 'pigbag@gmail.com', '2021-12-31', '2021-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (5, 'hello', '1234', 'hello@gmail.com', '2021-12-31', '2021-12-31');


## COLUMNS DUMMY
INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (1, 'Jun', '2020-12-31', '2020-12-31', 1, 1);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (2, 'Bong', '2020-11-30', '2020-12-31', 1, 3);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (3, 'Kim', '2021-09-29', '2022-12-31', 1, 3);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (4, 'Jang', '2021-09-29', '2022-11-30', 1, 4);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (5, 'Park', '2021-07-29', '2022-12-31', 1, 5);


## CARDS DUMMY
INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Hello World', 'Hello!!!!!!', 'COMPLETED', '2020-12-31', '2020-12-31', 1, 1);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('BongBong World', 'BongBong!!!!!!', 'TODO', '2020-12-31', '2020-12-31', 1, 2);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Hello Kai', 'KaiKai', 'PROGRESSING', '2021-12-31', '2021-12-31', 1, 2);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Pig-bag', 'Pig-bag', 'PROGRESSING', '2022-12-31', '2022-12-31', 1, 3);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Pig-bag', 'Pig-bag', 'TODO', '2022-12-30', '2022-12-31', 1, 4);

>>>>>>> main
>>>>>>> 6ab2caa6612fa739a98b8c511841880b8bf9d3b4
