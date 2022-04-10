DROP TABLE IF EXISTS work_log;
DROP TABLE IF EXISTS work;
DROP TABLE IF EXISTS category;

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
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE work_log (
    id INT NOT NULL AUTO_INCREMENT,
    work_id INT NOT NULL,
    action VARCHAR(32) NOT NULL COMMENT '등록, 삭제, 변경, 이동',
    previous_column VARCHAR(64) NOT NULL COMMENT '등록/삭제/변경/이동 시에만 존재',
    changed_column VARCHAR(64)  COMMENT '이동 시에만 존재',
    updated_datetime TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (work_id) REFERENCES work(id)
);
