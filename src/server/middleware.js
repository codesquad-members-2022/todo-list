const getContents = (newBody, url) => {
  let contents;
  const { title, prevColumn } = newBody;
  const column = newBody.column.name;

  switch (url) {
    case 'tasks': {
      contents = { column, title };
      if (prevColumn) contents.prevColumn = prevColumn.name;
      break;
    }
    case 'columns': {
      contents = { name: column };
      if (prevColumn) contents.prevName = prevColumn.name;
      break;
    }
  }
  return contents;
};

const getAction = (path, method, prevColumn) => {
  if (method === 'POST') return 'ADD';
  if (method === 'DELETE') return 'REMOVE';
  if (['columns', 'tasks'].includes(path) && !prevColumn) return 'UPDATE';
  return 'MOVE';
};

const findInfoById = infos => id => {
  return infos.value().find(task => task.id === Number(id));
};

const addAction = router => (req, res, next) => {
  next();

  const { method, path, body } = req;
  const [_, url, id = 0] = path.split('/');
  if (method === 'GET' || url === 'users') return;

  const columnsInfo = router.db.get('columns');
  const actionsInfo = router.db.get('actions');
  const findColumnById = findInfoById(columnsInfo);
  const infoByUrl = router.db.get(url);
  const prevData = findInfoById(infoByUrl)(id);
  const newBody = { ...prevData, ...body };

  if (url === 'columns' && method === 'PATCH') newBody.prevName = prevData.name;
  if (url === 'tasks') newBody.column = findColumnById(newBody.columnId);
  if (prevData && body.order)
    newBody.prevColumn = findColumnById(prevData.columnId);

  actionsInfo
    .push({
      id: actionsInfo.value().length + 1,
      userId: newBody.userId,
      type: url,
      contents: getContents(newBody, url),
      action: getAction(url, method, newBody.prevColumn),
      executedTime: new Date(),
    })
    .write();
};

const updateOrderOtherColumn = (tasks, taskInfo) => {
  const { prevColumnId, prevOrder, columnId, order } = taskInfo;
  tasks
    .each(task => {
      if (task.columnId === columnId && task.order >= order) task.order++;
      if (task.columnId === prevColumnId && task.order > prevOrder)
        task.order--;
    })
    .write();
};

const updateOrderSameColumn = (tasks, taskInfo) => {
  const { prevOrder, columnId, order } = taskInfo;
  if (order > prevOrder) {
    tasks
      .each(task => {
        if (
          task.columnId === columnId &&
          task.order > prevOrder &&
          task.order <= order
        )
          task.order--;
      })
      .write();
  }

  if (order < prevOrder) {
    tasks
      .each(task => {
        if (
          task.columnId === columnId &&
          task.order < prevOrder &&
          task.order >= order
        )
          task.order++;
      })
      .write();
  }
};

const updateOrderByPatch = (tasks, task, body) => {
  const { columnId, order } = body;
  const prevOrder = task.order;
  const prevColumnId = task.columnId;
  const taskInfo = { prevOrder, prevColumnId, order, columnId };
  columnId !== prevColumnId
    ? updateOrderOtherColumn(tasks, taskInfo)
    : updateOrderSameColumn(tasks, taskInfo);
};

const updateOrderByDelete = (tasks, task) => {
  const { order, columnId } = task;
  tasks
    .each(task => {
      if (task.columnId === columnId && task.order > order) task.order--;
    })
    .write();
};

const updateOrder = router => (req, res, next) => {
  next();

  const { path, method, body } = req;
  const tasks = router.db.get('tasks');
  const [_, url, id] = path.split('/');
  const task = findInfoById(tasks)(id);

  if (method === 'DELETE') updateOrderByDelete(tasks, task);
  if (method === 'PATCH') updateOrderByPatch(tasks, task, body);
};

export { addAction, updateOrder };
