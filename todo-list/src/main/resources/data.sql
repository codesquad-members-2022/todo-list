insert into CARD (title, CARD_INDEX, contents, writer, card_status) values ('테스트1', '0', '테스트입니다1', '얀', 'TODO');
insert into CARD (title, CARD_INDEX, contents, writer, card_status) values ('테스트2', '0', '테스트입니다2', '얀', 'PROGRESS');
insert into CARD (title, CARD_INDEX, contents, writer, card_status) values ('테스트3', '0', '테스트입니다3', '얀', 'DONE');


INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES ( '테스트1', 'TODO', 'DONE', 'MOVE', '2022-04-08T23:04:42.28068');
INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES ( '테스트2', 'PROGRESS', 'DONE', 'MOVE', '2022-04-08T23:04:42.28068');
INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES ( '테스트3', 'DONE', 'PROGRESS', 'UPDATE', '2022-04-08T23:04:42.28068');
