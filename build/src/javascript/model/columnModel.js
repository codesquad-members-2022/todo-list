import { axiosRequest } from "../API/api.js";

const columns = {
  "have-to-do-column": [],
  "doing-column": [],
  "done-column": [],
};

export async function getColumns() {
  const todos = await axiosRequest("get", "todos");
  getAllColumnTodos(todos);
  return columns;
}

function getAllColumnTodos(todos) {
  const columnNames = Object.keys(columns);
  for (const columnName of columnNames) {
    columns[columnName] = getColumnTodos(todos, columnName);
  }
  return columns;
}

function getColumnTodos(todos, columnName) {
  const columnTodos = todos.filter((todo) => todo.column === columnName);
  return columnTodos;
}
