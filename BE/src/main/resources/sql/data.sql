insert into history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
values ('MOVE', 'test1', '카드 제목 1', '카드 제목 1', 'TODO', 'ONGOING', CURRENT_TIMESTAMP);

insert into history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
values ('UPDATE', 'test2', '카드 제목 2', '카드 제목 이전 2', 'DONE', 'DONE', CURRENT_TIMESTAMP);

insert into history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
values ('DELETE', 'test3', '', '카드 제목 이전 3', 'UNCLASSIFIED', 'ONGOING', CURRENT_TIMESTAMP);

insert into history(card_action, user_id, card_title, card_title_before, card_status,
                    card_status_before, created_at)
values ('ADD', 'test4', '카드 제목 4', '', 'ONGOING', 'UNCLASSIFIED', CURRENT_TIMESTAMP);
