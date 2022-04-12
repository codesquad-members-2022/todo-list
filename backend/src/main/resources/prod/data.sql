insert into member (user_id, password, name)
values ('userId1', 'password1', 'name1');
insert into member (user_id, password, name)
values ('userId2', 'password2', 'name2');
insert into member (user_id, password, name)
values ('userId3', 'password3', 'name3');

insert into activity_log (user_id, action, task_title, from_status, to_status, created_at, read_yn)
values ('userId1', 'ADD', 'taskTitle1', 'TODO', 'IN_PROGRESS', now(), true);

insert into activity_log (user_id, action, task_title, from_status, to_status, created_at, read_yn)
values ('userId2', 'REMOVE', 'taskTitle2', 'IN_PROGRESS', 'DONE', now(), false);

insert into activity_log (user_id, action, task_title, from_status, to_status, created_at, read_yn)
values ('userId3', 'UPDATE', 'taskTitle3', 'TODO', 'IN_PROGRESS', now(), true);

insert into task_card (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
values (1, 'title1', 'content1', 'userId1', 1, now(), now(), false, 1);
insert into task_card (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
values (1, 'title2', 'content2', 'userId1', 1, now(), now(), false, 2);
insert into task_card (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
values (1, 'title3', 'content3', 'userId1', 1, now(), now(), true, 3);
