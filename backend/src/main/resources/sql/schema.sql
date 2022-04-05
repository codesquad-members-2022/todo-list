DROP TABLE IF EXISTS CARD, ACTION_LOG;
CREATE TABLE CARD (
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    content varchar(500),
    author_system varchar(20) NOT NULL,
    status ENUM('to_do', 'in_progress', 'done'),
    deleted tinyint(1) DEFAULT 0,
    order_index int NOT NULL
);
CREATE TABLE ACTION_LOG
(
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    cur_status ENUM('to_do', 'in_progress', 'done'),
    prev_status ENUM('to_do', 'in_progress', 'done') default NULL,
    action ENUM('add', 'remove', 'update', 'move'),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO CARD (title, content, author_system, status, order_index) VALUES (
'테스트 글 제목1', '테스트 내용', 'iOS', 'to_do', (SELECT count(*) FROM CARD WHERE status = 'to_do')
);
INSERT INTO CARD (title, content, author_system, status, order_index) VALUES (
'테스트 글 제목2', '테스트 내용', 'iOS', 'in_progress', (SELECT count(*) FROM CARD WHERE status = 'in_progress')
);
INSERT INTO CARD (title, content, author_system, status, order_index) VALUES (
'테스트 글 제목3', '테스트 내용', 'iOS', 'to_do', (SELECT count(*) FROM CARD WHERE status = 'to_do')
);
INSERT INTO CARD (title, content, author_system, status, order_index) VALUES (
'테스트 글 제목4', '테스트 내용', 'iOS', 'to_do', (SELECT count(*) FROM CARD WHERE status = 'to_do')
);
INSERT INTO CARD (title, content, author_system, status, order_index) VALUES (
'테스트 글 제목5', '테스트 내용', 'iOS', 'done', (SELECT count(*) FROM CARD WHERE status = 'done')
);
INSERT INTO ACTION_LOG (title, cur_status, action) VALUES (
'테스트1', 'to_do', 'add'
);
INSERT INTO ACTION_LOG (title, cur_status, prev_status, action) VALUES (
'테스트2', 'in_progress', 'to_do', 'add'
);
