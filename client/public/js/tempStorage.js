const database = [
  {
    id: "mansaout",
    username: "yellow",
    gender: "male",
    email: "mansaout@gmail.com",
    columns: [
      { id: 1, title: "해야할 일" },
      { id: 2, title: "하고 있는 일" },
      { id: 3, title: "완료한 일" },
    ],
    items: [
      {
        id: 1,
        columnId: 1,
        title: "GitHub 공부하기",
        content: "add, commit, push",
        date: "2021-04-05",
      },
      {
        id: 2,
        columnId: 1,
        title: "블로그에 포스팅할 것",
        content: "* GitHub 공부내용\n* 모던 자바스크립트 1장 공부내용",
        date: "2021-04-05",
      },
      {
        id: 3,
        columnId: 2,
        title: "HTML/CSS 공부하기",
        content: "input 태그 실습",
        date: "2021-04-05",
      },
    ],
    history: [
      {
        date: new Date("2022-04-06"),
        username: "yellow",
        action: "add",
        content: "<strong>해야할 일</strong>에 <strong>GitHub 공부하기</strong>를 <strong>등록</strong>하였습니다.",
      },
      {
        date: new Date("2022-04-07"),
        username: "yellow",
        action: "add",
        content:
          "<strong>해야할 일</strong>에 <strong>블로그에 포스팅할 것</strong>를 <strong>등록</strong>하였습니다.",
      },
      {
        date: new Date("2022-04-08"),
        username: "yellow",
        action: "add",
        content:
          "<strong>하고 있는 일</strong>에 <strong>HTML/CSS 공부하기</strong>를 <strong>등록</strong>하였습니다.",
      },
    ],
  },
  {
    id: "sju02048",
    username: "jwu",
    gender: "male",
    email: "sju02048@gmail.com",
    columns: [
      { id: 1, title: "해야할 일" },
      { id: 2, title: "하고 있는 일" },
      { id: 3, title: "완료한 일" },
    ],
    items: [
      {
        id: 1,
        columnId: 1,
        title: "GitHub 공부하기",
        content: "add, commit, push",
        date: "2021-04-05",
      },
      {
        id: 2,
        columnId: 1,
        title: "블로그에 포스팅할 것",
        content: "* GitHub 공부내용\n* 모던 자바스크립트 1장 공부내용",
        date: "2021-04-05",
      },
      {
        id: 4,
        columnId: 2,
        title: "HTML/CSS 공부하기",
        content: "input 태그 실습",
        date: "2021-04-05",
      },
    ],
    history: [],
  },
];

export default database;
