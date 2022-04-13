drop table if exists card;
drop table if exists history;
drop table if exists member;

create table card
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
        primary key,
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
        primary key,
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

INSERT INTO member (created_at, member_login_id, password, nick_name, visible)
VALUES ('2022-04-03 14:52:44', 'bongbong', '1234', 'BongBong', true);
INSERT INTO member (created_at, member_login_id, password, nick_name, visible)
VALUES ('2022-04-02 14:52:44', 'kaikai', '1234', 'Kai', true);

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
