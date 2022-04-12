import "./Column.scss";
import { Store } from "../../../../stores/ColumnStore.js";
import { Card } from "./card/Card.js";

export const initColumn = (parentNode, columnState) => {
  const columnNode = makeColumnNode(parentNode, columnState);
  renderColumn(columnNode, columnState);
};

const makeColumnNode = (parentNode, columnState) => {
  const columnNode = document.createElement("div");
  columnNode.className = "column";
  columnNode.dataset.columnid = columnState.id;
  parentNode.append(columnNode);
  return columnNode;
};

const renderColumn = (columnNode, columnState) => {
  const columnInnerTemplate = makeColumnInnerTemplate(columnState);
  columnNode.innerHTML = columnInnerTemplate;
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
        <div class="column-header__add-btn${columnData.addBtnActivated ? " activated" : ""}"></div>
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

const reRenderColumn = (columnState) => {
  const columnNode = findColumnNode(columnState.id);
  render(columnNode, columnState);
};

const findColumnNode = (columnID) => {
  return document.querySelector(`[data-columnid="${columnID}"]`);
};

export class Column {
  constructor(columnID) {
    this.columnID = columnID;
    this.setNode();
    this.renderContent();
    this.activate();
  }

  setNode() {
    const columnContainerDOM = document.querySelector(".column-container");
    const columnNode = this.makeColumnNode();
    columnContainerDOM.append(columnNode);
    this.columnNode = columnNode;
  }

  makeColumnNode() {
    const columnNode = document.createElement("div");
    columnNode.className = "column";
    columnNode.dataset.columnid = this.columnID;
    return columnNode;
  }

  renderContent() {
    const columnData = Store.state[this.columnID];
    this.columnNode.innerHTML = this.getContentTemplate(columnData);
    this.mountCards(columnData);
    this.setEvents();
  }

  getContentTemplate(columnData) {
    return `
      ${this.getHeaderTemplate(columnData)}
      ${this.getCardListTemplate()}
    `;
  }

  getHeaderTemplate(columnData) {
    return `
      <div class="column-header">
        <div class="column-header__info">
          <div class="column-header__title">${columnData.title}</div>
          <div class="column-header__count">${Object.keys(columnData.cards).length}</div>
        </div>
        <div class="column-header__util">
          <div class="column-header__add-btn${columnData.addBtnActivated ? " activated" : ""}"></div>
          <div class="column-header__delete-btn"></div>
        </div>
      </div>
    `;
  }

  getCardListTemplate() {
    return `
      <ul class='card-list'></ul>
      `;
  }

  mountCards(columnData) {
    const cardOrder = columnData.cardOrder;
    const cardListEl = this.columnNode.querySelector(".card-list");
    cardListEl.innerHTML = "";
    cardOrder.forEach((cardID) => new Card(this.columnID, cardID));
  }

  setEvents() {
    this.setAddBtnEvent();
  }

  setAddBtnEvent() {
    const addBtn = this.columnNode.querySelector(".column-header__add-btn");
    addBtn.addEventListener("click", () => this.handleAddBtnClick(addBtn));
  }

  handleAddBtnClick(addBtn) {
    if (addBtn.classList.contains("activated")) {
      Store.exitFromAddCardState(this.columnID);
    } else {
      Store.setAddingCardState(this.columnID);
    }
  }

  activate() {
    Store.subscribe("column", () => this.renderContent());
  }
}
