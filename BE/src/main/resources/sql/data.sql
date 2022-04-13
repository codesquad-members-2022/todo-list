-- INSERT INTO card(id, title, contents, user_id, column_id, deleted, order_index, created_at )
-- VALUES(1, '제목 1', '내용 1', 'damon', 1, false, 0.0, CURRENT_TIMESTAMP);
--
-- INSERT INTO card(id, title, contents, user_id, column_id, deleted, order_index, created_at )
-- VALUES(2, '제목 2', '내용 2', 'ader', 1, true, 1000.0, CURRENT_TIMESTAMP);
--
-- INSERT INTO card(id, title, contents, user_id, column_id, deleted, order_index, created_at )
-- VALUES(3, '제목 3', '내용 3', 'honux', 3, false, 0.0, CURRENT_TIMESTAMP);
--
-- INSERT INTO card(id, title, contents, user_id, column_id, deleted, order_index, created_at )
-- VALUES(4, '제목 4', '내용 4', 'backend', 2, false, 0.0, CURRENT_TIMESTAMP);
--
-- INSERT INTO history(card_action, user_id, card_title, card_title_before, column_title,
--                     column_title_before, created_at)
-- VALUES ('MOVE', '', '카드 제목 1', '카드 제목 1', '할 일', '하고 있는 일', CURRENT_TIMESTAMP);
--
-- INSERT INTO history(card_action, user_id, card_title, card_title_before, column_title,
--                     column_title_before, created_at)
-- VALUES ('UPDATE', '', '카드 제목 2', '카드 제목 이전 2', '완료한 일', '완료한 일', CURRENT_TIMESTAMP);
--
-- INSERT INTO history(card_action, user_id, card_title, card_title_before, column_title,
--                     column_title_before, created_at)
-- VALUES ('DELETE', '', null, '카드 제목 이전 3', null, '하고 있는 일', CURRENT_TIMESTAMP);
--
-- INSERT INTO history(card_action, user_id, card_title, card_title_before, column_title,
--                     column_title_before, created_at)
-- values ('ADD', '', '카드 제목 4', null, '하고 있는 일', null, CURRENT_TIMESTAMP);

INSERT INTO column_tbl(title, order_index, deleted, created_at)
VALUES ('할 일', 0.0, false, CURRENT_TIMESTAMP);

INSERT INTO column_tbl(title, order_index, deleted, created_at)
VALUES ('하고 있는 일', 1000.0, false, CURRENT_TIMESTAMP);

INSERT INTO column_tbl(title, order_index, deleted, created_at)
VALUES ('완료한 일', 2000.0, false, CURRENT_TIMESTAMP);
