DELETE FROM work;
alter table work AUTO_INCREMENT = 1;

DELETE FROM category;
alter table category AUTO_INCREMENT = 1;

INSERT INTO category (name) VALUES ('TODO');
INSERT INTO category (name) VALUES ('DOING');
INSERT INTO category (name) VALUES ('DONE');

INSERT INTO work (category_id, title, content, user_id, created_date)
 VALUES (1, '자바 공부', '클래스와 객체의 차이', 'ikjo', NOW());

INSERT INTO work (category_id, title, content, user_id, created_date)
 VALUES (2, 'MySQL 공부', '데이터 베이스 설계', 'ikjo', NOW());

INSERT INTO work (category_id, title, content, user_id, created_date)
 VALUES (3, '깃 공부', 'rebase와 merge의 차이', 'ikjo', NOW());
