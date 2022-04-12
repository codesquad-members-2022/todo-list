DELETE FROM user;
alter table user AUTO_INCREMENT = 1;

DELETE FROM work_log;
alter table work_log AUTO_INCREMENT = 1;

DELETE FROM work;
alter table work AUTO_INCREMENT = 1;

DELETE FROM category;
alter table category AUTO_INCREMENT = 1;

INSERT INTO user (user_id, password, name, email) VALUES ('ikjo', '1234', '익조', 'auddlr100@naver.com');

INSERT INTO category (name) VALUES ('TODO');
INSERT INTO category (name) VALUES ('DOING');
INSERT INTO category (name) VALUES ('DONE');

INSERT INTO work (category_id, title, content, user_id, delete_flag, created_datetime)
 VALUES (1, '자바 공부', '클래스와 객체의 차이', 'ikjo', false, NOW());

INSERT INTO work (category_id, title, content, user_id, delete_flag, created_datetime)
 VALUES (2, 'MySQL 공부', '데이터 베이스 설계', 'ikjo', false, NOW());

INSERT INTO work (category_id, title, content, user_id, delete_flag, created_datetime)
 VALUES (3, '깃 공부', 'rebase와 merge의 차이', 'ikjo', false, NOW());

INSERT INTO work_log (user_id, title, action, previous_column, updated_datetime)
 VALUES ('ikjo', '자바 공부', '등록', 'TODO', NOW());

INSERT INTO work_log (user_id, title, action, previous_column, updated_datetime)
 VALUES ('ikjo', 'MySQL 공부', '등록', 'DOING', NOW());

INSERT INTO work_log (user_id, title, action, previous_column, updated_datetime)
 VALUES ('ikjo', '깃 공부', '등록', 'DONE', NOW());
