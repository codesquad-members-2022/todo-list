INSERT INTO MEMBER (user_id, password, name)
VALUES ('userId1', 'password1', 'name1');
INSERT INTO MEMBER (user_id, password, name)
VALUES ('userId2', 'password2', 'name2');
INSERT INTO MEMBER (user_id, password, name)
VALUES ('userId3', 'password3', 'name3');

INSERT INTO ACTIVITY_LOG (user_id, action, task_title, from_status, to_status, created_at, read_yn)
VALUES ('userId1', 1, 'taskTitle1', 1, 2, SYSDATE, true);

INSERT INTO ACTIVITY_LOG (user_id, action, task_title, from_status, to_status, created_at, read_yn)
VALUES ('userId2', 2, 'taskTitle2', 2, 3, SYSDATE, false);

INSERT INTO ACTIVITY_LOG (user_id, action, task_title, from_status, to_status, created_at, read_yn)
VALUES ('userId3', 3, 'taskTitle3', 2, 2, SYSDATE, true);

INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title1', 'content1', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 1);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title2', 'content2', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', FALSE, 2);
INSERT INTO TASK_CARD (status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position)
VALUES (1, 'title3', 'content3', 'userId1', 1, '2022-04-10 00:00:00', '2022-04-10 00:00:00', TRUE, 3);

