import { qs, on, delegate, getParentElementByDataset } from "../utils/helpers.js";

import { renderItemForm, renderColumn } from "../views/renderer.js";

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
    itemForm: ".card__register-form",
    itemFormInput: ".card-box .card__content--title",
    itemFormTextarea: ".card-box .card__content--text",
    // itemFormRegistBtn: ".card-box .card__btn--regist",
    itemFormCancelBtn: ".card-box .card__btn--cancel",
  };

  on(el.columnAddBtn, "click", addColumn);
  on(el.showHistoryBtn, "click", showHistoryBar);
  on(el.hideHistoryBtn, "click", hideHistoryBar);

  delegate(el.columnList, "submit", selector.itemForm, (event) => registItem(event));
  delegate(el.columnList, "click", selector.addItemBtn, (event) => showItemForm(event));
  delegate(el.columnList, "click", selector.itemFormCancelBtn, removeItemForm);
  delegate(el.columnList, "click", selector.removeItemBtn, (event) => removeItem(event));

  // column
  delegate(el.columnList, "dblclick", selector.columnTitle, modifyColumnTitle);
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
    const columnEl = getParentElementByDataset(event.target, "column");
    const columnId = columnEl.dataset.column;
    renderItemForm(columnId);
    qs(selector.itemFormInput).focus();
  }

  function removeItemForm() {
    const existingItemForm = qs(".card-box");
    if (existingItemForm) existingItemForm.remove();
  }

  function registItem(event) {
    event.preventDefault();
    console.log("registItem");
    console.log(event.target);
    // 타이틀, 내용, 작성 날짜, 칼럼 넘버 가져오기
    // 스토어에 데이터 추가(아이템 아이디 생성)
    // input에
    // 아이템 렌더링
    // 폼 삭제
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
    console.log("removeColumn");
    console.log(event.target);
  }
}
