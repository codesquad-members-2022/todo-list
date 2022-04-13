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

INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title1', 'content1', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 1);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title2', 'content2', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 2);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title3', 'content3', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 0);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title4', 'content4', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 0);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title5', 'content5', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 3);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title6', 'content6', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 4);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title7', 'content7', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 5);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title8', 'content8', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 6);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title9', 'content9', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 7);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title10', 'content10', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 8);

INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title11', 'content11', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 3);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title12', 'content12', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 2);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title13', 'content13', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 0);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title14', 'content14', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 0);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title15', 'content15', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 1);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title16', 'content16', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 4);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title17', 'content17', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 5);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title18', 'content18', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 6);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title19', 'content19', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 7);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (2, 'title20', 'content20', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 8);

INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title21', 'content21', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 8);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title22', 'content22', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 7);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title23', 'content23', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 0);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title24', 'content24', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 0);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title25', 'content25', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 6);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title26', 'content26', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 5);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title27', 'content27', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 4);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title28', 'content28', 'userId1', 2, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 3);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title29', 'content29', 'userId1', 3, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 2);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (3, 'title30', 'content30', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 1);
