## MEMBER
CREATE TABLE member
(
    id               BIGINT      NOT NULL PRIMARY KEY,
    member_login_id  VARCHAR(10) NOT NULL,
    password         VARCHAR(15) NOT NULL,
    email            VARCHAR(20) NOT NULL,
    created_at       DATETIME,
    last_modified_at DATETIME
);

CREATE TABLE card
(
    id               BIGINT       NOT NULL PRIMARY KEY auto_increment,
    title            VARCHAR(255) NOT NULL,
    content          VARCHAR(500) NOT NULL,
    card_type        VARCHAR(50)  NOT NULL,
    created_at       DATETIME,
    last_modified_at DATETIME,
    visible          BIT,
    column_id        BIGINT
);

## COLUMN
CREATE TABLE `column`
(
    id               BIGINT       NOT NULL PRIMARY KEY auto_increment,
    writer           VARCHAR(10),
    created_at       DATETIME,
    last_modified_at DATETIME,
    visible          BIT,
    member_id        BIGINT NOT NULL
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
