INSERT INTO MEMBER (user_id, password, name) VALUES ('userId1', 'password1', 'name1');
INSERT INTO MEMBER (user_id, password, name) VALUES ('userId2', 'password2', 'name2');
INSERT INTO MEMBER (user_id, password, name) VALUES ('userId3', 'password3', 'name3');

INSERT INTO ACTIVITY_LOG (user_id, activity_type, from_status, to_status, created_at, read_yn)
VALUES ('userId1', 1, 1, 2, SYSDATE, true);

INSERT INTO ACTIVITY_LOG (user_id, activity_type, from_status, to_status, created_at, read_yn)
VALUES ('userId2', 2, 2, 3, SYSDATE, false);

INSERT INTO ACTIVITY_LOG (user_id, activity_type, from_status, to_status, created_at, read_yn)
VALUES ('userId3', 3, 1, 2, SYSDATE, true);
