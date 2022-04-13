import { getTodos, postTodo } from "../model/cardModel.js";
import { renderColumn, getUpdatedCardContent } from "../view/cardView.js";
import { handleAlertEvent } from "../view/deleteCardView.js";
async function renderTodos() {
  const todos = await getTodos();
  renderColumn("#have-to-do-column", todos.haveToDoColumn);
  renderColumn("#doing-column", todos.doingColumn);
  renderColumn("#done-column", todos.doneColumn);
  handleAlertEvent();
}

function addServerCardData(card) {
  const cardData = getUpdatedCardContent(card);
  postTodo(cardData);
}

export { renderTodos, addServerCardData };
