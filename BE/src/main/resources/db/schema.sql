DROP TABLE IF EXISTS TODO;
CREATE TABLE TODO
(
    id           BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Todo의 고유한 id',
    title        VARCHAR(255) COMMENT '제목',
    contents     VARCHAR(500) COMMENT '본문',
    user         VARCHAR(100) COMMENT 'Todo 생성한 사용자',
    status       VARCHAR(10) COMMENT 'Todo의 상태(todo, doing, done)',
    created_time TIMESTAMP COMMENT 'Todo 생성 시간',
    updated_time TIMESTAMP COMMENT 'Todo 업데이트 시간',
    PRIMARY KEY (id)
);
