insert into user(user_name, profile_image)
values ('Miller', NULL);

insert into `column` (user_id, column_name)
values (1, '해야할 일');
insert into `column` (user_id, column_name)
values (1, '하고 있는 일');
insert into `column` (user_id, column_name)
values (1, '완성한 일');

insert into card (column_id, author, created_date, title, content, next_id)
values (1, '작성자', '2022-04-06 00:00:00', 'TODO LIST 만들기', '매주 1시간 동안 TODO LIST 작성하기', null);
insert into card (column_id, author, created_date, title, content, next_id)
values (1, '작성자', '2022-04-06 00:00:00', 'JavaScript 공부하기', '매주 1시간 동안 JavaScript 책 읽기', 1);
insert into card (column_id, author, created_date, title, content, next_id)
values (2, '작성자', '2022-04-06 00:00:00', 'SQL 공부하기', '매주 1시간 동안 SQL 책 읽기', null);
insert into card (column_id, author, created_date, title, content, next_id)
values (2, '작성자', '2022-04-06 00:00:00', 'HTML/CSS 공부하기', '매주 1시간 동안 HTML/CSS 책 읽기', 3);
insert into card (column_id, author, created_date, title, content, next_id)
values (2, '작성자', '2022-04-06 00:00:00', 'Java 공부하기', '매주 1시간 동안 Java 책 읽기', 4);

insert into history (card_id, created_date, action)
values (1, '2022-04-06 00:00:00', 'CREATE');
insert into history (card_id, created_date, action)
values (1, '2022-04-06 00:00:00', 'UPDATE');
insert into history (card_id, created_date, action)
values (2, '2022-04-06 00:00:00', 'CREATE');
insert into history (card_id, created_date, action)
values (2, '2022-04-06 00:00:00', 'DELETE');
insert into history (card_id, created_date, action)
values (3, '2022-04-06 00:00:00', 'CREATE');
insert into history (card_id, created_date, action)
values (3, '2022-04-06 00:00:00', 'MOVE');
insert into history (card_id, created_date, action)
values (3, '2022-04-06 00:00:00', 'UPDATE');
insert into history (card_id, created_date, action)
values (4, '2022-04-06 00:00:00', 'CREATE');
insert into history (card_id, created_date, action)
values (4, '2022-04-06 00:00:00', 'MOVE');
insert into history (card_id, created_date, action)
values (5, '2022-04-06 00:00:00', 'CREATE');
insert into history (card_id, created_date, action)
values (5, '2022-04-06 00:00:00', 'MOVE');
insert into history (card_id, created_date, action)
values (5, '2022-04-06 00:00:00', 'DELETE');

insert into modified_field (history_id, field, old_value, new_value)
values (2, 'TITLE', '3분 회고 작성하기', 'TODO LIST 만들기');
insert into modified_field (history_id, field, old_value, new_value)
values (2, 'CONTENT', '하루 3분 동안 3분 회고 작성하기', '매주 1시간 동안 TODO LIST 작성하기');
insert into modified_field (history_id, field, old_value, new_value)
values (6, 'COLUMN', 1, 2);
insert into modified_field (history_id, field, old_value, new_value)
values (7, 'TITLE', '네트워크 공부하기', 'SQL 공부하기');
insert into modified_field (history_id, field, old_value, new_value)
values (7, 'AUTHOR', '하루 1시간 동안 네트워크 공부하기', '매주 1시간 동안 SQL 책 읽기');
insert into modified_field (history_id, field, old_value, new_value)
values (9, 'COLUMN', 1, 2);
insert into modified_field (history_id, field, old_value, new_value)
values (11, 'COLUMN', 1, 2);
