DROP TABLE IF EXISTS work_log;
DROP TABLE IF EXISTS work;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS category;

CREATE TABLE user (
    user_id VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    name VARCHAR(64),
    email VARCHAR(64),
    PRIMARY KEY (user_id)
);

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE work (
    id INT NOT NULL AUTO_INCREMENT,
    category_id INT NOT NULL,
    user_id VARCHAR(64) NOT NULL,
    title VARCHAR(255),
    content VARCHAR(255),
    delete_flag TINYINT(1) NOT NULL DEFAULT 0 COMMENT '삭제유무 삭제 안됨 0, 삭제 : 1',
    created_datetime TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    INDEX index_datetime (created_datetime)
);

CREATE TABLE work_log (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(64) NOT NULL,
    title VARCHAR(255),
    action VARCHAR(32) NOT NULL COMMENT '등록, 삭제, 변경, 이동',
    previous_column VARCHAR(64) NOT NULL COMMENT '등록/삭제/변경/이동 시에만 존재',
    changed_column VARCHAR(64)  COMMENT '이동 시에만 존재',
    updated_datetime TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    INDEX index_datetime (updated_datetime)
);
