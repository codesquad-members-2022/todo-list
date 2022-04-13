import { qs, on, delegate } from "../utils/helpers.js";
import * as renderer from "../views/renderer.js";

export function bindEvents() {
  const el = {
    columnList: qs(".column-list"),
    historyBar: qs(".history__bar"),
    showHistoryBtn: qs(".header--history-btn"),
    hideHistoryBtn: qs(".history--close-btn"),
  };

  const selector = {
    addItemBtn: ".column__header--add-card",
    registItemBtn: ".card__btn--regist",
    removeItemBtn: ".card--delete-card",
    cancelItemFormBtn: ".card__btn--cancel",
    removeColumnBtn: ".column__header--delete-card",
  };

  on(el.showHistoryBtn, "click", showHistoryBar);
  on(el.hideHistoryBtn, "click", hideHistoryBar);

  delegate(el.columnList, "click", selector.addItemBtn, (event) => showItemForm(event));
  delegate(el.columnList, "click", selector.registItemBtn, (event) => registItem(event));
  delegate(el.columnList, "click", selector.removeItemBtn, (event) => removeItem(event));
  delegate(el.columnList, "click", selector.cancelItemFormBtn, (event) => cancelItemForm(event));
  delegate(el.columnList, "click", selector.removeColumnBtn, (event) => removeColumn(event));
}

function showItemForm(event) {
  console.log("showItemForm");
  console.log(event.target);
}
  function toggleHistoryBar() {
  function showHistoryBar() {
    el.historyBar.classList.toggle("show");
    // 히스토리 상대 날짜 리렌더링
  }

function registItem(event) {
  console.log("registItem");
  console.log(event.target);
}
  function hideHistoryBar() {
    el.historyBar.classList.toggle("show");
  }

function removeItem(event) {
  console.log("removeItem");
  console.log(event.target);
}

function cancelItemForm(event) {
  console.log("cancelItemForm");
  console.log(event.target);
}

function removeColumn(event) {
  console.log("removeColumn");
  console.log(event.target);
  function toggleHistoryBar() {
    el.historyBar.classList.toggle("show");
  }

}
