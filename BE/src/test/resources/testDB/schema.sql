DROP TABLE IF EXISTS TODO CASCADE;
CREATE TABLE TODO
(
    id           BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Todo의 고유한 id',
    title        VARCHAR(255)                   COMMENT '제목',
    contents     VARCHAR(500)                   COMMENT '본문',
    user         VARCHAR(100)                   COMMENT 'Todo 생성한 사용자',
    status       VARCHAR(10)                    COMMENT 'Todo의 상태(todo, doing, done)',
    created_at   TIMESTAMP                      COMMENT 'Todo 생성 시간',
    updated_at   TIMESTAMP                      COMMENT 'Todo 업데이트 시간',
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS HISTORY;
CREATE TABLE HISTORY
(
    id           BIGINT NOT NULL AUTO_INCREMENT COMMENT 'History의 고유한 id',
    todo_id      BIGINT                         COMMENT '외래키 : Todo의 고유한 id',
    todo_title   VARCHAR(255)                   COMMENT 'todoId에 해당하는 title',
    user         VARCHAR(100)                   COMMENT 'Todo 생성한 사용자',
    action       VARCHAR(10)                    COMMENT 'Todo의 상태(todo, doing, done)',
    from_status  VARCHAR(10)                    COMMENT 'todo_id의 action (add, remove, update, move)',
    to_status    VARCHAR(10)                    COMMENT 'Todo의 상태(todo, doing, done)',
    created_at   TIMESTAMP                      COMMENT 'Todo 생성 시간',
    PRIMARY KEY  (id),
    FOREIGN KEY  (todo_Id) REFERENCES TODO(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
