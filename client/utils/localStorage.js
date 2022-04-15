export const getLocalStorageByKey = key => {
  return JSON.parse(localStorage.getItem(key));
};

export const editLocalStorageById = (key, editLocalStorageObj, id) => {
  if (!id) return;
  const objIndex = getLocalStorageByKey(key).findIndex(e => e.id === id);
  const todos = getLocalStorageByKey(key);

  for (const [key, value] of Object.entries(editLocalStorageObj)) {
    todos[objIndex][key] = value;
  }

  localStorage.setItem(key, JSON.stringify(todos));
};

export const getLastIdByKey = key => {
  const lastNum = getLocalStorageByKey(key);
  return lastNum.length === 0 ? 0 : lastNum[lastNum.length - 1].id;
};

export const getTodosByStatus = (key, status) => {
  const todos = getLocalStorageByKey(key);
  if (!todos) return;
  return todos.filter(todo => todo.status === status);
};
