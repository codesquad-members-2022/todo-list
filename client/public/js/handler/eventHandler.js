import { qs, on, delegate, getParentElementByDataset, createNextId } from "../utils/helpers.js";
import { renderItemForm, renderColumn, renderItem, renderColumnLength, renderAllHistory } from "../views/renderer.js";

export function bindEvents(store) {
  const el = {
    body: qs("body"),
    columnList: qs(".column-list"),
    columnAddBtn: qs(".column--add-btn"),
    historyBar: qs(".history__bar"),
    showHistoryBtn: qs(".header--history-btn"),
    hideHistoryBtn: qs(".history--close-btn"),
  };

  const selector = {
    addItemBtn: ".column__header--add-card",
    removeItemBtn: ".card--delete-card",
    removeColumnBtn: ".column__header--delete-card",
    columnTitle: ".column__header--title",
    itemCard: ".card",
    itemForm: ".card__register-form",
    itemFormInput: ".card-box .card__content--title",
    itemFormTextarea: ".card-box .card__content--text",
    // itemFormRegistBtn: ".card-box .card__btn--regist",
    itemFormCancelBtn: ".card-box .card__btn--cancel",
  };

  on(el.columnAddBtn, "click", addColumn);
  on(el.showHistoryBtn, "click", showHistoryBar);
  on(el.hideHistoryBtn, "click", hideHistoryBar);

  // Item Form Card
  delegate(el.columnList, "submit", selector.itemForm, (event) => registItem(event));
  delegate(el.columnList, "click", selector.addItemBtn, (event) => showItemForm(event));
  delegate(el.columnList, "click", selector.itemFormCancelBtn, removeItemForm);

  // Item Card
  delegate(el.columnList, "click", selector.removeItemBtn, (event) => removeItem(event));

  // column
  delegate(el.columnList, "dblclick", selector.columnTitle, modifyColumnTitle);
  delegate(el.columnList, "click", selector.removeColumnBtn, (event) => removeColumn(event));

  function showHistoryBar() {
    el.historyBar.classList.toggle("show");
    renderAllHistory(store.history);
  }

  function hideHistoryBar() {
    el.historyBar.classList.toggle("show");
  }

  function showItemForm(event) {
    removeItemForm();

    const columnId = _getColumnId(event.target);
    renderItemForm(columnId);
    qs(selector.itemFormInput).focus();
  }

  function removeItemForm() {
    const existingItemForm = qs(".card-box");
    if (existingItemForm) existingItemForm.remove();
  }

  function registItem(event) {
    event.preventDefault();

    const id = createNextId(store.getItems());
    const columnId = _getColumnId(event.target);
    const title = qs(selector.itemFormInput).value;
    const content = qs(selector.itemFormTextarea).value;
    const date = new Date();

    const newItem = { id, columnId, title, content, date };

    renderItem(store.addItem(newItem));
    removeItemForm();

    const columnTitle = _getColumnTitle(columnId);
    const historyContent = `<strong>${columnTitle}</strong>에 <strong>${title}</strong>를 <strong>등록</strong>하였습니다.`;
    store.addHistory({ date, action: "add", content: historyContent });

    renderAllHistory(store.history);
  }

  function removeItem(event) {
    const itemId = _getItemId(event.target);
    const itemTitle = _getItemTitle(itemId);
    const itemEl = getParentElementByDataset(event.target, "card");

    const columnId = _getColumnId(event.target);
    const columnTitle = _getColumnTitle(columnId);

    const historyContent = `<strong>${columnTitle}</strong>에서 <strong>${itemTitle}</strong>를 <strong>삭제</strong>하였습니다.`;

    store.addHistory({ date: new Date(), action: "remove", content: historyContent });
    store.removeItem(itemId);
    renderColumnLength(columnId, false);
    itemEl.remove();

    renderAllHistory(store.history);
  }

  function addColumn(event) {
    renderColumn(store.addColumn());
  }

  function modifyColumnTitle(event) {
    event.target.setAttribute("contenteditable", "true");
    event.target.focus();
    const controller = on(el.body, "click", (registEvent) => registColumnTitle(event, registEvent, controller));
  }

  function registColumnTitle(event, registEvent, controller) {
    if (event.target === registEvent.target) return;

    event.target.removeAttribute("contenteditable");
    controller.abort();

    const columnEl = getParentElementByDataset(event.target, "column");
    const columnId = Number(columnEl.dataset.column);
    const newTitle = event.target.innerHTML.trim();
    store.updateColumn(columnId, newTitle);
  }

  function removeColumn(event) {
    const columnEl = getParentElementByDataset(event.target, "column");
    const columnId = _getColumnId(event.target);
    const columnTitle = _getColumnTitle(columnId);

    const historyContent = `<strong>${columnTitle}</strong>을 <strong>삭제</strong>하였습니다.`;

    store.addHistory({ date: new Date(), action: "remove", content: historyContent });
    columnEl.remove();

    renderAllHistory(store.history);
  }

  function _getColumnId(eventTarget) {
    return Number(getParentElementByDataset(eventTarget, "column").dataset.column);
  }

  function _getItemId(eventTarget) {
    return Number(getParentElementByDataset(eventTarget, "card").dataset.card);
  }

  function _getColumnTitle(columnId) {
    return qs(`[data-column="${columnId}"] .column__header--title`).textContent;
  }

  function _getItemTitle(itemId) {
    return qs(`[data-card="${itemId}"] .card__content--title`).value;
  }
}
