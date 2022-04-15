import { qs, on, delegate, getParentElementByDataset, createNextId } from "../utils/helpers.js";
import { renderItemForm, renderItem, renderColumnLength } from "../views/renderer.js";

export function bindEvents(store) {
  const Store = store;

  const el = {
    columnList: qs(".column-list"),
    columnAdd: qs(".column-add"),
    historyBar: qs(".history__bar"),
    showHistoryBtn: qs(".header--history-btn"),
    hideHistoryBtn: qs(".history--close-btn"),
  };

  const selector = {
    addItemBtn: ".column__header--add-card",
    removeItemBtn: ".card--delete-card",
    removeColumnBtn: ".column__header--delete-card",
    itemCard: ".card",
    itemForm: ".card__register-form",
    itemFormInput: ".card-box .card__content--title",
    itemFormTextarea: ".card-box .card__content--text",
    // itemFormRegistBtn: ".card-box .card__btn--regist",
    itemFormCancelBtn: ".card-box .card__btn--cancel",
  };

  on(el.columnAdd, "click", addColumn);

  on(el.showHistoryBtn, "click", showHistoryBar);
  on(el.hideHistoryBtn, "click", hideHistoryBar);

  // Item Form Card
  delegate(el.columnList, "submit", selector.itemForm, (event) => registItem(event));
  delegate(el.columnList, "click", selector.addItemBtn, (event) => showItemForm(event));
  delegate(el.columnList, "click", selector.itemFormCancelBtn, removeItemForm);

  // Item Card
  delegate(el.columnList, "click", selector.removeItemBtn, (event) => removeItem(event));
  delegate(el.columnList, "click", selector.removeColumnBtn, (event) => removeColumn(event));

  function showHistoryBar() {
    el.historyBar.classList.toggle("show");
    // 히스토리 상대 날짜 리렌더링
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

    const id = createNextId(Store.getItems());
    const columnId = _getColumnId(event.target);
    const title = qs(selector.itemFormInput).value;
    const content = qs(selector.itemFormTextarea).value;
    const date = new Date();

    const newItem = { id, columnId, title, content, date };

    Store.addItem(newItem);
    renderItem(newItem);
    removeItemForm();
  }

  function removeItem(event) {
    const itemId = _getItemId(event.target);
    Store.removeItem(itemId);

    const columnId = _getColumnId(event.target);
    renderColumnLength(columnId, false);

    const itemEl = getParentElementByDataset(event.target, "card");
    itemEl.remove();
  }

  function addColumn(event) {
    console.log("addColumn");
    console.log(event.target);
  }

  function removeColumn(event) {
    console.log("removeColumn");
    console.log(event.target);
  }

  function _getColumnId(eventTarget) {
    return Number(getParentElementByDataset(eventTarget, "column").dataset.column);
  }

  function _getItemId(eventTarget) {
    return Number(getParentElementByDataset(eventTarget, "card").dataset.card);
  }
}
