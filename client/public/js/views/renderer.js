import { qs, insertElement } from "../utils/helpers.js";
import { createColumn } from "./column/column.js";
import { createItem, createItemBox, createItemDeleteAlert } from "./card/card.js";
import { createHistory } from "./history/history.js";
import { createHeader } from "./header/header.js";
import { createAside } from "./aside/aside.js";

const renderer = {
  allColumns: (columnList) => {
    columnList.forEach(renderer.column);
  },

  column: ({ id, title, length }) => {
    const columnListEl = qs(".column-list");
    insertElement(columnListEl, "beforeend", createColumn({ id, title, length }));
  },

  allItems: (itemList) => {
    itemList.forEach(renderer.item);
  },

  item: ({ id, columnId, title, content }) => {
    const itemListEl = qs(`[data-column='${columnId}'] .card-list`);
    insertElement(itemListEl, "beforeend", createItem({ id, title, content }));
  },

  itemBox: (columnId) => {
    const itemListEl = qs(`[data-column='${columnId}'] .card-list`);
    insertElement(itemListEl, "afterbegin", createItemBox());
  },

  itemDeleteAlert: () => {
    const todoContainerEl = qs(".todo-container");
    insertElement(todoContainerEl, "afterbegin", createItemDeleteAlert());
  },

  allHistory: (historyList) => {
    historyList.forEach(renderer.history);
  },

  history: ({ username, date, content }) => {
    const historyListEl = qs(".history-list");
    insertElement(historyListEl, "afterbegin", createHistory({ username, date, content }));
  },

  header: () => {
    const todoContainerEl = qs(".todo-container");
    insertElement(todoContainerEl, "afterbegin", createHeader());
  },

  aside: () => {
    const todoContainerEl = qs(".todo-container");
    insertElement(todoContainerEl, "beforeend", createAside());
  },
};

export { renderer };
