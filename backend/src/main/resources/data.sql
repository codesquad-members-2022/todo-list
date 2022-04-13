INSERT INTO CARD (title, content, author_system, column_name, order_index)
VALUES
('테스트 글 제목1', '테스트 내용', 'iOS', 'to_do', SELECT count(*) FROM CARD WHERE column_name = 'to_do'),
('테스트 글 제목2', '테스트 내용', 'iOS', 'in_progress', SELECT count(*) FROM CARD WHERE column_name = 'in_progress'),
('테스트 글 제목3', '테스트 내용', 'iOS', 'to_do', SELECT count(*) FROM CARD WHERE column_name = 'to_do'),
('테스트 글 제목4', '테스트 내용', 'iOS', 'to_do', SELECT count(*) FROM CARD WHERE column_name = 'to_do'),
('테스트 글 제목5', '테스트 내용', 'iOS', 'done', SELECT count(*) FROM CARD WHERE column_name = 'done');


