import { axiosRequest } from "../util/util.js";

const cardModel = {
  todos: [],
  haveToDoColumn: [],
  doingColumn: [],
  doneColumn: [],
};

async function getTodos() {
  const todos = await axiosRequest("get", "todos");
  cardModel.todos = todos;
  getAllColumnTodos();
  return cardModel;
}

async function postTodos(card) {
  await axiosRequest("post", "todos", card);
}

function getAllColumnTodos() {
  cardModel.haveToDoColumn = getColumnTodos("have-to-do-column");
  cardModel.doingColumn = getColumnTodos("doing-column");
  cardModel.doneColumn = getColumnTodos("done-column");
  return cardModel;
}

function getColumnTodos(columnName) {
  const todos = cardModel.todos;
  const columnTodos = todos.filter((todo) => todo.column === columnName);
  return columnTodos;
}

export { getTodos, postTodos };
