import { qs, insertElement } from "../utils/helpers.js";
import { createColumn } from "./column/column.js";
import { createItem, createItemForm, createItemDeleteAlert } from "./card/card.js";
import { createHistory } from "./history/history.js";
import { createHeader } from "./header/header.js";
import { createAside } from "./aside/aside.js";
import { createMain } from "./main/main.js";

const todoContainerEl = qs(".todo-container");

export function renderAllColumns(columnList) {
  columnList.forEach(renderColumn);
}

export function renderColumn({ id, title, length }) {
  const columnListEl = qs(".column-list");
  insertElement(columnListEl, "beforeend", createColumn({ id, title, length }));
}

export function renderAllItems(itemList) {
  itemList.forEach(renderItem);
}

export function renderItem({ id, columnId, title, content }) {
  const itemListEl = qs(`[data-column='${columnId}'] .card-list`);
  insertElement(itemListEl, "beforeend", createItem({ id, title, content }));
  renderColumnLength(columnId);
}

export function renderItemForm(columnId) {
  const itemListEl = qs(`[data-column='${columnId}'] .card-list`);
  insertElement(itemListEl, "afterbegin", createItemForm());
}

export function renderItemDeleteAlert() {
  insertElement(todoContainerEl, "afterbegin", createItemDeleteAlert());
}

export function renderAllHistory(historyList) {
  historyList.forEach(renderHistory);
}

export function renderHistory({ username, date, content }) {
  const historyListEl = qs(".history-list");
  insertElement(historyListEl, "afterbegin", createHistory({ username, date, content }));
}

export function renderHeader() {
  insertElement(todoContainerEl, "afterbegin", createHeader());
}

export function renderAside() {
  insertElement(todoContainerEl, "beforeend", createAside());
}

export function renderMain() {
  insertElement(todoContainerEl, "beforeend", createMain());
}

export function renderColumnLength(columnId, boolean = true) {
  const columnLengthEl = qs(`.column[data-column="${columnId}"] .column__header--card-count`);
  boolean ? columnLengthEl.textContent++ : columnLengthEl.textContent--;
}
