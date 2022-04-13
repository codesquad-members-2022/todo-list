DROP TABLE IF EXISTS card;
CREATE TABLE card
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title       VARCHAR(50)                       NOT NULL,
    contents    VARCHAR(500)                      NOT NULL,
    user_id     VARCHAR(20),
    card_status VARCHAR(10)                       NOT NULL,
    deleted     BOOLEAN                           NOT NULL,
    created_at  TIMESTAMP                         NOT NULL
);

DROP TABLE IF EXISTS history;
CREATE TABLE history
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    card_action        VARCHAR(20)                       NOT NULL,
    user_id            VARCHAR(20),
    card_title         VARCHAR(50),
    card_title_before  VARCHAR(50),
    card_status        VARCHAR(20),
    card_status_before VARCHAR(20),
    created_at         TIMESTAMP                         NOT NULL
);

DROP TABLE IF EXISTS column_tbl;
CREATE TABLE column_tbl
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title       VARCHAR(50)                       NOT NULL,
    order_index DOUBLE                            NOT NULL,
    deleted     BOOLEAN                           NOT NULL,
    created_at  TIMESTAMP                         NOT NULL
);
