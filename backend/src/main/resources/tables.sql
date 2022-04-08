## MEMBER
CREATE TABLE member
(
    id              BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    created_at      DATETIME(6)  NOT NULL,
    member_login_id VARCHAR(10)  NOT NULL,
    password        VARCHAR(15)  NOT NULL,
    nick_name       VARCHAR(255) NOT NULL,
    visible         BIT
);

## CARD
CREATE TABLE card
(
    id               BIGINT       NOT NULL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    content          VARCHAR(500) NOT NULL,
    card_type        VARCHAR(10)  NOT NULL,
    created_at       DATETIME,
    column_id        BIGINT,
    visible          BIT
);

## COLUMN
CREATE TABLE columns
(
    id               BIGINT NOT NULL PRIMARY KEY,
    writer           VARCHAR(10),
    created_at       DATETIME,
    member_id        BIGINT NOT NULL,
    visible          BIT
);


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
)
