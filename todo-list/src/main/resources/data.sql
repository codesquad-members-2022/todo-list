insert into card (title, CARD_INDEX, contents, writer, card_status) values ('테스트1', '1', '테스트입니다1', '얀', 'TODO');
insert into card (title, CARD_INDEX, contents, writer, card_status) values ('테스트2', '2', '테스트입니다2', '얀', 'PROGRESS');
insert into card (title, CARD_INDEX, contents, writer, card_status) values ('테스트3', '3', '테스트입니다3', '얀', 'DONE');
insert into card (title, CARD_INDEX, contents, writer, card_status) values ('테스트4', '4', '테스트입니다4', '얀', 'DONE');

INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES ( '테스트1', 'TODO', 'DONE', 'MOVE', '2022-04-08T23:04:42.28068');
INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES ( '테스트2', 'PROGRESS', 'DONE', 'MOVE', '2022-04-08T23:04:42.28068');
INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES ( '테스트3', 'TODO', 'DONE', 'UPDATE', '2022-04-08T23:04:42.28068');
