import {
  getColumns,
  postTodo,
  putUpdatedCardData,
  deleteServerCardData,
} from "../model/cardModel.js";
import {
  renderColumn,
  getUpdatedCardContent,
  addDeleteEvent,
} from "../view/cardView.js";

export async function renderTodos() {
  const columns = await getColumns();
  const columnNames = Object.keys(columns);
  for (const columnName of columnNames) {
    renderColumn(columnName, columns[columnName]);
  }
  addDeleteEvent();
}

export function addServerCardData(card) {
  const cardData = getUpdatedCardContent(card);
  postTodo(cardData);
}

export function updateServerCardData($selectedCard) {
  const cardData = getUpdatedCardContent($selectedCard);
  const dataID = $selectedCard.id.slice(4);
  putUpdatedCardData(cardData, dataID);
}

export function deleteCardData($selectedCard) {
  const dataId = $selectedCard.id.slice(4);
  deleteServerCardData(dataId);
}
