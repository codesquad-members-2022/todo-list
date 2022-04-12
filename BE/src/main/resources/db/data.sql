INSERT INTO TODO (TITLE, CONTENTS, USER, STATUS, CREATED_AT, UPDATED_AT)
VALUES ('Github 공부하기', 'add, commit, push', 'sam', 'doing', '2022-04-06T15:30:00.000+09:00',
        '2022-04-06T15:30:00.000+09:00'),
       ('블로그에 포스팅할 것', '*Github 공부내용 \r\n *모던 자바스크립트 1장 공부내용', 'sam', 'todo',
        '2022-04-07T15:30:00.000+09:00', '2022-04-07T15:30:00.000+09:00'),
       ('HTMl/CSS 공부하기', 'input 태그 실습', 'sam', 'doing', '2022-04-07T20:30:00.000+09:00',
        '2022-04-07T20:30:00.000+09:00'),
       ('여자친구와 데이트', '초밥 먹으러 가기', 'sam', 'done', '2022-04-08T15:30:00.000+09:00',
        '2022-04-08T15:30:00.000+09:00');

INSERT INTO HISTORY (TODO_ID, TODO_TITLE, USER, ACTION, FROM_STATUS, TO_STATUS, CREATED_AT)
VALUES (1, 'Github 공부하기', 'sam', 'add', '', 'todo', '2022-04-06T15:30:00.000+09:00'),
       (1, 'Github 공부하기', 'sam', 'move', 'todo', 'doing', '2022-04-06T16:30:00.000+09:00');
