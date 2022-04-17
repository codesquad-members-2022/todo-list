DELETE FROM user;
alter table user AUTO_INCREMENT = 1;

DELETE FROM user_log;
alter table user_log AUTO_INCREMENT = 1;

DELETE FROM work;
alter table work AUTO_INCREMENT = 1;

DELETE FROM category;
alter table category AUTO_INCREMENT = 1;

INSERT INTO user (user_id, password, name, email) VALUES ('ikjo', '1234', '익조', 'auddlr100@naver.com');

INSERT INTO category (user_id, name) VALUES ('ikjo', 'TODO');
INSERT INTO category (user_id, name) VALUES ('ikjo', 'DOING');
INSERT INTO category (user_id, name) VALUES ('ikjo', 'DONE');

INSERT INTO work (category_id, title, content, delete_flag, created_datetime)
 VALUES (1, '자바 공부', '클래스와 객체의 차이', false, NOW());

INSERT INTO work (category_id, title, content, delete_flag, created_datetime)
 VALUES (2, 'MySQL 공부', '데이터 베이스 설계', false, NOW());

INSERT INTO work (category_id, title, content, delete_flag, created_datetime)
 VALUES (3, '깃 공부', 'rebase와 merge의 차이', false, NOW());

INSERT INTO user_log (user_id, title, action, current_category, updated_datetime)
 VALUES ('ikjo', '자바 공부', '등록', 'TODO', NOW());

INSERT INTO user_log (user_id, title, action, current_category, updated_datetime)
 VALUES ('ikjo', 'MySQL 공부', '등록', 'DOING', NOW());

INSERT INTO user_log (user_id, title, action, current_category, updated_datetime)
 VALUES ('ikjo', '깃 공부', '등록', 'DONE', NOW());
