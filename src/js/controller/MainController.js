import { renderEmptyCard } from "../view/EmptyCardView.js";
import { initColumn, onClickAdd } from "../view/ColumnView.js";

function addCard2Column(event, store) {
  const { target } = event;
  const parentColumn = target.closest(".column__zone");
  const hasEmptyCard = parentColumn.querySelector(".testNewCard");
  if (hasEmptyCard) return;
  renderEmptyCard(parentColumn, createCardState, store);
}

function init(store) {
  const columnZone = document.querySelector(".column__zone");
  initColumn();
  onClickColumnAddBtn(columnZone, addCard2Column, store);
}

const createColumnController = (store) => {
  const columnStore = store;
  init(columnStore);
};

export { createColumnController };
