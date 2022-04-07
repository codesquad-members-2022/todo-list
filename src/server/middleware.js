const contents = {
  tasks: {
    ADD: ({ column, title }) =>
      `<p><b>${column}</b>에 <b>${title}</b>을(를) <b>등록</b>하였습니다.</p>`,
    UPDATE: ({ column, title }) =>
      `<p><b>${column}</b>의 <b>${title}</b>을(를) <b>변경</b>하였습니다.</p>`,
    MOVE: ({ title, prevColumn, column }) =>
      `<p><b>${title}</b>을(를) <b>${prevColumn}</b>에서 <b>${column}</b>(으)로 <b>등록</b>하였습니다.</p>`,
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

const createActionContents = (path, method, body) => {
  let action = method === 'POST' ? 'ADD' :
    method === 'DELETE' ? 'REMOVE' :
      path === 'columns' && !body.prevColumn ? 'UPDATE' :
        path === 'tasks' && !body.prevColumn ? 'UPDATE' : 'MOVE';

  return contents[path][action](body);
};

const getColumnName = (router, id) => router.db.get('columns').find({ id }).value().name;

const addAction = (router) => ({ method, path, body }, res, next) => {
  next();

  const [_, url, id = 0] = path.split('/');
  let newBody = { ...body };

  if (method === 'GET' || url === 'users') return;

  if (method === 'POST') url === 'tasks' ? newBody.column = getColumnName(router, body.column) : '';

  else {
    const prevData = router.db
      .get(url)
      .find({ id: parseInt(id) })
      .value();

    newBody = {
      ...prevData,
      ...body,
    }

    if (url === 'columns' && method === 'PATCH') newBody.prevName = prevData.name;
    if (url === 'tasks') newBody.column = getColumnName(router, newBody.column);
    if (body.column) newBody.prevColumn = getColumnName(router, prevData.column);
  }

  router.db
    .get('action')
    .push({
      user: 1,
      contents: createActionContents(url, method, newBody),
      executedTime: new Date(),
    })
    .write();
};

export default addAction;
