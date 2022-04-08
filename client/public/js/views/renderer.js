import { qs, insertElement } from "../utils/helpers.js";
import { createColumn } from "./column/column.js";
import { createItem } from "./card/card.js";

const renderer = {
  allColumns: (columnList) => {
    columnList.forEach(renderer.column);
  },

  allItems: (itemList) => {
    itemList.forEach(renderer.item);
  },

  column: ({ id, title }) => {
    const columnListEl = qs(".column-list");
    insertElement(columnListEl, "beforeend", createColumn({ id, title }));
  },

  item: ({ id, columnId, title, content }) => {
    const columnEl = qs(`[data-column='${columnId}'`);
    insertElement(columnEl, "beforeend", createItem({ id, title, content }));
  },
};

export { renderer };
