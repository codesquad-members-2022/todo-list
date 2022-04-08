use todo_list;

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (1, 'jun', '1234', 'jun@gmail.com', '2020-12-31', '2020-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (2, 'bong', '1234', 'bong@gmail.com', '2021-12-31', '2021-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (3, 'kai', '1234', 'kai@gmail.com', '2021-12-31', '2021-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (4, 'pigbag', '1234', 'pigbag@gmail.com', '2021-12-31', '2021-12-31');

INSERT INTO member (id, member_login_id, password, email, created_at, last_modified_at)
VALUES (5, 'hello', '1234', 'hello@gmail.com', '2021-12-31', '2021-12-31');


## COLUMNS DUMMY
INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (1, 'Jun', '2020-12-31', '2020-12-31', 1, 1);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (2, 'Bong', '2020-11-30', '2020-12-31', 1, 3);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (3, 'Kim', '2021-09-29', '2022-12-31', 1, 3);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (4, 'Jang', '2021-09-29', '2022-11-30', 1, 4);

INSERT INTO `column` (id, writer, created_at, last_modified_at, visible, member_id)
VALUES (5, 'Park', '2021-07-29', '2022-12-31', 1, 5);


## CARDS DUMMY
INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Hello World', 'Hello!!!!!!', 'COMPLETED', '2020-12-31', '2020-12-31', 1, 1);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('BongBong World', 'BongBong!!!!!!', 'TODO', '2020-12-31', '2020-12-31', 1, 2);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Hello Kai', 'KaiKai', 'PROGRESSING', '2021-12-31', '2021-12-31', 1, 2);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Pig-bag', 'Pig-bag', 'PROGRESSING', '2022-12-31', '2022-12-31', 1, 3);

INSERT INTO card (title, content, card_type, created_at, last_modified_at, visible, column_id)
VALUES ('Pig-bag', 'Pig-bag', 'TODO', '2022-12-30', '2022-12-31', 1, 4);
