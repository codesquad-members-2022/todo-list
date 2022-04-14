import { $ } from "../utils/utils.js";
import { List } from "./list.js";
import * as TodoListStore from "../store/todoListStore.js";

const createTodoList = async () => {
  const todoListData = await TodoListStore.getTodoListData();
  const parent = $(".column__list");
  for (const list of todoListData) {
    new List(parent, [list.title, list.task]);
  }
};

const createHTML = () => {
  return `<ul class="column__list"></ul>`;
};

const render = (parent) => {
  parent.innerHTML = createHTML();
};

const mainInit = (parent) => {
  render(parent);
  createTodoList();
};

export { mainInit };
