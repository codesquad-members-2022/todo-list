import { qs, insertElement } from "../utils/helpers.js";
import { createColumn } from "./column/column.js";
import { createItem } from "./card/card.js";
import { createHistory } from "./aside/history.js";

const renderer = {
  allColumns: (columnList) => {
    columnList.forEach(renderer.column);
  },

  column: ({ id, title }) => {
    const columnListEl = qs(".column-list");
    insertElement(columnListEl, "beforeend", createColumn({ id, title }));
  },

  allItems: (itemList) => {
    itemList.forEach(renderer.item);
  },

  item: ({ id, columnId, title, content }) => {
    const itemListEl = qs(`[data-column='${columnId}'] .card-list`);
    insertElement(itemListEl, "beforeend", createItem({ id, title, content }));
  },

  allHistory: (historyList) => {
    historyList.forEach(renderer.history);
  },

  history: ({ username, date, content }) => {
    const historyListEl = qs(".history-list");
    insertElement(historyListEl, "afterbegin", createHistory({ username, date, content }));
  },
};

export { renderer };
