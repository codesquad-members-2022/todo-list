insert into user(user_name, profile_image)
values ('Miller', NULL);

insert into `column` (user_id, column_name)
values (1, '해야할 일');
insert into `column` (user_id, column_name)
values (1, '하고 있는 일');
insert into `column` (user_id, column_name)
values (1, '완성한 일');

insert into card (column_id, author, created_date, title, content, card_order)
values (1, 'Miller', '2022-04-06 00:00:00', 'todo_list', 'todolist 만들기', 1);
insert into card (column_id, author, created_date, title, content, card_order)
values (2, 'Miller', '2022-04-06 00:00:00', 'sql,', 'sql 공부하기', 1);

insert into history (card_id, created_date, action)
values (1, '2022-04-06 00:00:00', 'DELETE')
