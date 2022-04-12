import "./Column.scss";
import { Store } from "../../../../stores/ColumnStore.js";
import { Card } from "./card/Card.js";

export const initColumn = (parentNode, columnState) => {
  const columnNode = makeColumnNode(columnState);
  appendColumnNode(columnNode, parentNode);
  renderColumn(columnNode, columnState);
  setEvents(columnNode);
  subscribeState();
};

const makeColumnNode = (columnState) => {
  const columnNode = document.createElement("div");
  columnNode.className = "column";
  columnNode.dataset.id = columnState.id;
  return columnNode;
};

const appendColumnNode = (columnNode, parentNode) => {
  parentNode.append(columnNode);
};

const renderColumn = (columnNode, columnState) => {
  columnNode.innerHTML = makeColumnInnerTemplate(columnState);
  mountCards(columnNode, columnState);
};

const makeColumnInnerTemplate = (columnState) => {
  return `
    ${getHeaderTemplate(columnState)}
    ${getCardListTemplate()}
  `;
};

const getHeaderTemplate = (columnState) => {
  return `
    <div class="column-header">
      <div class="column-header__info">
        <div class="column-header__title">${columnState.title}</div>
        <div class="column-header__count">${columnState.cardOrder.length}</div>
      </div>
      <div class="column-header__util">
        <div class="column-header__add-btn${columnState.addBtnActivated ? " activated" : ""}"></div>
        <div class="column-header__delete-btn"></div>
      </div>
    </div>
  `;
};

const getCardListTemplate = () => {
  return `
    <ul class='card-list'></ul>
    `;
};

const mountCards = (columnNode, columnState) => {
  const columnID = columnNode.dataset.id;
  const cardOrder = columnState.cardOrder;
  const cardListNode = columnNode.querySelector(".card-list");
  cardListNode.innerHTML = "";
  cardOrder.forEach((cardID) => new Card(columnID, cardID));
};

const setEvents = (columnNode) => {
  setAddBtnEvent(columnNode);
};

const setAddBtnEvent = (columnNode) => {
  const addBtn = columnNode.querySelector(".column-header__add-btn");
  addBtn.addEventListener("click", () => handleAddBtnClick(columnNode, addBtn));
};

const handleAddBtnClick = (columnNode, addBtn) => {
  const columnID = columnNode.dataset.id;
  if (addBtn.classList.contains("activated")) {
    Store.unsetAddingCardState(columnID);
  } else {
    Store.setAddingCardState(columnID);
  }
};

const subscribeState = () => {
  Store.subscribe("column", reRenderColumn);
};

const reRenderColumn = (columnState) => {
  const columnNode = findColumnNode(columnState.id);
  renderColumn(columnNode, columnState);
  setEvents(columnNode);
};

const findColumnNode = (columnID) => {
  return document.querySelector(`[data-id="${columnID}"]`);
};