INSERT INTO member (id, name, password) VALUES ('vans', '반스', '1111');
INSERT INTO section (id, name) VALUES (1, 'to do');
INSERT INTO section (id, name) VALUES (2, 'in progress');
INSERT INTO section (id, name) VALUES (3, 'done');
INSERT INTO section (id, name) VALUES (4, 'test');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 1, '할 일', '운동+닭가슴살', 1000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 2, '하는 중', '팔꿈치재활훈련', 1000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 1, '뭐 하지?', '운동1', 2000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 1, '뭐할까?', '훈련1', 3000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 2, '뭐 하고있었더라?', '운동2', 2000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 2, '뭐하는중?', '훈련2', 3000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
INSERT INTO card (member_id, section_id, subject, contents, order_index, created_at, updated_at) VALUES ('vans', 3, '뭐했더라?', '끝', 1000, '2022-04-06 16:00:00', '2022-04-06 16:00:00');
