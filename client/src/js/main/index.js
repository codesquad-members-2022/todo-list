import { $ } from "../utils/utils.js";
import { List } from "./list.js";
import { Task } from "./task.js";
import * as TodolistStore from "../store/todolistStore.js";

const createTodoList = async () => {
  const todolistData = await TodolistStore.getTodolistData();
  const parent = $(".column__list");
  for (const list of todolistData) {
    for (const title in list) {
      const task = new Task(list[title]);
      new List(parent, title, task);
    }
  }
};

const createHTML = () => {
  return `<ul class="column__list"></ul>`;
};

const render = (parent) => {
  parent.innerHTML = createHTML();
};

export const mainInit = (parent) => {
  render(parent);
  createTodoList();
};
