import {
  getTodos,
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
  const todos = await getTodos();
  renderColumn("#have-to-do-column", todos.haveToDoColumn);
  renderColumn("#doing-column", todos.doingColumn);
  renderColumn("#done-column", todos.doneColumn);
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
