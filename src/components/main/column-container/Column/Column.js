import "./Column.scss";
import { Store } from "../../../../stores/ColumnStore.js";
import { renderCard } from "./card/Card.js";

export const renderColumn = (parentEl, columnID) => {
  const columnData = Store.state[columnID];
  const columnNode = getColumnNode(columnID, columnData);
  parentEl.appendChild(columnNode);

  const cardListEl = columnNode.querySelector(".card-list");
  mountCard(cardListEl, columnID, columnData);
};

const getColumnNode = (columnID, columnData) => {
  const columnNode = document.createElement("div");
  columnNode.className = "column";
  columnNode.dataset.id = columnID;
  columnNode.innerHTML = getColumnNodeInnerTemplate(columnData);
  return columnNode;
};

const getColumnNodeInnerTemplate = (columnData) => {
  return `
    ${getHeaderTemplate(columnData)}
    ${getCardListTemplate()}
  `;
};

const getHeaderTemplate = (columnData) => {
  return `
    <div class="column-header">
      <div class="column-header__info">
        <div class="column-header__title">${columnData.title}</div>
        <div class="column-header__count">${Object.keys(columnData.cards).length}</div>
      </div>
      <div class="column-header__util">
        <div class="column-header__add-btn"></div>
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

const mountCard = (cardListEl, columnID, columnData) => {
  const cardOrder = columnData.cardOrder;
  cardOrder.forEach((cardID) => renderCard(cardListEl, columnID, cardID));
};
