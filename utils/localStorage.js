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
