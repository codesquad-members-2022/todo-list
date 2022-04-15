export const ActionContents = {
  tasks: {
    ADD: ({ column, title }) =>
      `<p><b>${column}</b>에 <b>${title}</b>을(를) <b>등록</b>하였습니다.</p>`,
    UPDATE: ({ column, title }) =>
      `<p><b>${column}</b>의 <b>${title}</b>을(를) <b>변경</b>하였습니다.</p>`,
    MOVE: ({ title, prevColumn, column }) =>
      `<p><b>${title}</b>을(를) <b>${prevColumn}</b>에서 <b>${column}</b>(으)로 <b>이동</b>하였습니다.</p>`,
    REMOVE: ({ column, title }) =>
      `<p><b>${column}</b>의 <b>${title}</b>을(를) <b>삭제</b>하였습니다.</p>`,
  },
  columns: {
    ADD: ({ name }) =>
      `<p>새로운 컬럼 <b>${name}</b>을(를) <b>등록</b>하였습니다.</p>`,
    UPDATE: ({ prevName, name }) =>
      `<p><b>${prevName}</b> 컬럼의 이름을 <b>${name}</b>(으)로 <b>변경</b>하였습니다.</p>`,
    REMOVE: ({ name }) =>
      `<p><b>${name}</b> 컬럼을 <b>삭제</b>하였습니다.</p>`,
  },
};
