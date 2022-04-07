set foreign_key_checks = 0;

drop table if exists `user` cascade;
create table `user`
(
    user_id       bigint(10) not null auto_increment primary key comment '사용자 식별 번호',
    user_name     char(200)  not null comment '사용자 이름',
    profile_image mediumblob comment '사용자 프로필 사진',
    deleted       boolean    not null default false comment 'soft delete 여부'
);

drop table if exists `column` cascade;
create table `column`
(
    column_id   bigint(10) not null auto_increment primary key comment '컬럼 식별 번호',
    user_id     bigint(10) not null comment '컬럼을 어떤 사용자가 만들었는지 식별하기 위한 FK',
    column_name char(200)  not null comment '컬럼 이름',
    deleted     boolean    not null default false comment 'soft delete 여부',
    FOREIGN KEY (user_id) references `user` (user_id)
);

drop table if exists `card` cascade;
create table `card`
(
    card_id      bigint(10) not null auto_increment primary key comment '카드 식별 번호',
    column_id    bigint(10) not null comment '카드가 어떤 컬럼에 속해있는지 식별하기 위한 FK',
    author       char(200)  not null comment '카드를 만든 사용자 이름',
    created_date timestamp  not null comment '카드 생성 날짜, 시간',
    title        char(200)  not null comment '카드 제목',
    content      char(255) comment '카드 본문',
    card_order   int(10)    not null comment '컬럼 내에서 카드의 순서 번호',
    deleted      boolean    not null default false comment 'soft delete 여부',
    FOREIGN KEY (column_id) references `column` (column_id)
);

drop table if exists history cascade;
create table history
(
    history_id   bigint(10) not null auto_increment primary key comment '변경내역 식별 번호',
    user_id      bigint(10) not null comment '어떤 사용자에 의해 변경이 발생했는지 식별하기 위한 FK',
    created_date timestamp  not null comment '변경내역 발생 날짜, 시간',
    title        char(200) comment '카드 관련 변경내역인 경우 해당 카드의 제목',
    column_name  char(200) comment '컬럼 관련 변경내역인 경우 해당 컬럼의 이름',
    field        char(100) comment '변경이 적용된 필드(카드 제목, 컬럼 이름 등)',
    old_value    char(200) comment '변경이 적용되기 이전의 해당 필드의 값',
    action       char(20)   not null comment '변경 내용(이동,삭제 등)',
    FOREIGN KEY (user_id) references user (user_id)
);
set foreign_key_checks = 1;
