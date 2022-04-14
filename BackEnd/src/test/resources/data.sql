insert into activity_log
    (activity_log_action, title, now_status, before_status) values
    ("ADD", "title1", "TODO", null),
    ("ADD", "title2", "TODO", null),
    ("REMOVE", "title3", "IN_PROGRESS", null),
    ("REMOVE", "title4", "IN_PROGRESS", null),
    ("UPDATE", "title5", "DONE", null),
    ("UPDATE", "title6", "DONE", null),
    ("MOVE", "title7", "TODO", "IN_PROGRESS"),
    ("MOVE", "title8", "IN_PROGRESS", "DONE");

insert into card
    (title, contents, card_status, author) values
    ("title1", "contents1", "TODO", "ANDROID"),
    ("title2", "contents2", "TODO", "ANDROID"),
    ("title3", "contents3", "IN_PROGRESS", "ANDROID"),
    ("title4", "contents4", "IN_PROGRESS", "ANDROID"),
    ("title5", "contents5", "DONE", "ANDROID"),
    ("title6", "contents6", "DONE", "ANDROID");
