import "./Column.scss";
import { Store } from "../../../../stores/ColumnStore.js";
import { renderCard } from "./card/Card.js";

export const renderColumn = (parentEl, columnID) => {
  const columnData = Store.state[columnID];
  parentEl.innerHTML = getColumnTemplate(columnID, columnData);
  const cardListEl = parentEl.querySelector(".card-list");
  mountCard(cardListEl, columnID, columnData);
};

const getColumnTemplate = (columnID, columnData) => {
  return `
    <div class='column' data-id=${columnID}>
      ${getHeaderTemplate(columnData)}
      ${getCardListTemplate()}
    </div>`;
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
