import { axiosRequest } from "../API/api.js";

const cardModel = {
  todos: [],
  haveToDoColumn: [],
  doingColumn: [],
  doneColumn: [],
};

export async function getTodos() {
  const todos = await axiosRequest("get", "todos");
  cardModel.todos = todos;
  getAllColumnTodos();
  return cardModel;
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

export function postTodo(cardData) {
  axiosRequest("post", "todos", cardData);
}

export function putUpdatedCardData(cardData, dataID) {
  axiosRequest("put", "todos", cardData, dataID);
}

export function deleteServerCardData(dataId) {
  axiosRequest("delete", "todos", {}, dataId);
}
