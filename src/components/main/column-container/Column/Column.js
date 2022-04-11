import "./Column.scss";
import { Store } from "../../../../stores/ColumnStore.js";
import { Card } from "./card/Card.js";

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
    }else {
      Store.updateAsAddCardState(this.columnID);
    }
  }

  activate() {
    Store.subscribe("column", () => this.renderContent());
  }
}
