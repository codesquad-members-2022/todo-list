INSERT INTO card(id, title, contents, user_id, card_status, deleted, created_at )
VALUES(1, '제목 1', '내용 1', 'damon', 'TODO', false, CURRENT_TIMESTAMP);

INSERT INTO card(id, title, contents, user_id, card_status, deleted, created_at )
VALUES(2, '제목 2', '내용 2', 'ader', 'TODO', true, CURRENT_TIMESTAMP);

INSERT INTO card(id, title, contents, user_id, card_status, deleted, created_at )
VALUES(3, '제목 3', '내용 3', 'honux', 'DONE', false, CURRENT_TIMESTAMP);

INSERT INTO card(id, title, contents, user_id, card_status, deleted, created_at )
VALUES(4, '제목 4', '내용 4', 'backend', 'ONGOING', false, CURRENT_TIMESTAMP);

INSERT INTO history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
VALUES ('MOVE', 'test1', '카드 제목 1', '카드 제목 1', 'TODO', 'ONGOING', CURRENT_TIMESTAMP);

INSERT INTO history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
VALUES ('UPDATE', 'test2', '카드 제목 2', '카드 제목 이전 2', 'DONE', 'DONE', CURRENT_TIMESTAMP);

INSERT INTO history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
VALUES ('DELETE', 'test3', '', '카드 제목 이전 3', 'UNCLASSIFIED', 'ONGOING', CURRENT_TIMESTAMP);

INSERT INTO history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
values ('ADD', 'test4', '카드 제목 4', '', 'ONGOING', 'UNCLASSIFIED', CURRENT_TIMESTAMP);

INSERT INTO column_tbl(title, order_index, deleted, created_at)
VALUES ('할 일', 0.0, false, CURRENT_TIMESTAMP);

INSERT INTO column_tbl(title, order_index, deleted, created_at)
VALUES ('하고 있는 일', 1000.0, false, CURRENT_TIMESTAMP);

INSERT INTO column_tbl(title, order_index, deleted, created_at)
VALUES ('완료한 일', 2000.0, false, CURRENT_TIMESTAMP);
