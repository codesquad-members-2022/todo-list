const database = {
  mansaout: {
    username: "yellow",
    gender: "male",
    email: "mansaout@gmail.com",
    columns: [
      { id: 1, column_title: "해야할 일" },
      { id: 2, column_title: "하고 있는 일" },
      { id: 3, column_title: "완료한 일" },
    ],
    items: [
      {
        id: 1,
        column_id: 1,
        item_title: "GitHub 공부하기",
        content: "add, commit, push",
        date: "2021-04-05",
      },
      {
        id: 2,
        column_id: 1,
        item_title: "블로그에 포스팅할 것",
        content: "* GitHub 공부내용\n* 모던 자바스크립트 1장 공부내용",
        date: "2021-04-05",
      },
      {
        id: 4,
        column_id: 2,
        item_title: "HTML/CSS 공부하기",
        content: "input 태그 실습",
        date: "2021-04-05",
      },
    ],
  },
  sju02048: {
    username: "jwu",
    gender: "male",
    email: "sju02048@gmail.com",
    columns: [
      {
        column_title: "해야할 일",
        items: [
          {
            id: "1",
            item_title: "GitHub 공부하기",
            content: "add, commit, push",
            date: "2021-04-05",
          },
          {
            id: "2",
            item_title: "블로그에 포스팅할 것",
            content: "* GitHub 공부내용\n* 모던 자바스크립트 1장 공부내용",
            date: "2021-04-05",
          },
        ],
      },
      {
        column_title: "하고 있는 일",
        items: [
          {
            id: "4",
            item_title: "HTML/CSS 공부하기",
            content: "input 태그 실습",
            date: "2021-04-05",
          },
        ],
      },
      { column_title: "완료한 일", items: [] },
    ],
  },
};

export default database;
