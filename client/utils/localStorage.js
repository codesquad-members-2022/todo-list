export const getLocalStorageByKey = key => {
  return JSON.parse(localStorage.getItem(key));
};

export const editLocalStorageById = (editLocalStorageObj, id) => {
  if (!id) return;
  const objIndex = getLocalStorageByKey('todos').findIndex(e => e.id === id);
  const todos = getLocalStorageByKey('todos');

  for (const [key, value] of Object.entries(editLocalStorageObj)) {
    todos[objIndex][key] = value;
  }

  localStorage.setItem('todos', JSON.stringify(todos));
};

export const getLastIdByKey = key => {
  const lastNum = getLocalStorageByKey(key);
  return lastNum.length === 0 ? 0 : lastNum[lastNum.length - 1].id;
};

export const getTodosByStatus = status => {
  const todos = getLocalStorageByKey('todos');
  if (!todos) return;
  return todos.filter(todo => todo.status === status);
};
