import Store from "../model/Store.js";
import { qs, delegate, on } from "../utils/helpers.js";
import * as renderer from "../views/renderer.js";

export function bindEvents() {
  const el = {
    columnList: qs(".column-list"),
    columnAdd: qs(".column-add"),
  };

  const selector = {
    addItemBtn: ".column__header--add-card",
    registItemBtn: ".card__btn--regist",
    removeItemBtn: ".card--delete-card",
    cancelItemFormBtn: ".card__btn--cancel",
    removeColumnBtn: ".column__header--delete-card",
  };

  on(el.columnAdd, "click", addColumn);

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

function registItem(event) {
  console.log("registItem");
  console.log(event.target);
}

function removeItem(event) {
  console.log("removeItem");
  console.log(event.target);
}

function cancelItemForm(event) {
  console.log("cancelItemForm");
  console.log(event.target);
}

function addColumn(event) {
  console.log("addColumn");
  console.log(event.target);
}

function removeColumn(event) {
  console.log("removeColumn");
  console.log(event.target);
}
