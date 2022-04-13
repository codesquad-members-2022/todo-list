import { getTodos, postTodo } from "../model/cardModel.js";
import { renderColumn, getUpdatedCardContent } from "../view/cardView.js";

async function renderTodos() {
  const todos = await getTodos();
  renderColumn("#have-to-do-column", todos.haveToDoColumn);
  renderColumn("#doing-column", todos.doingColumn);
  renderColumn("#done-column", todos.doneColumn);
}

function addServerCardData(card) {
  const cardData = getUpdatedCardContent(card);
  postTodo(cardData);
}

export { renderTodos, addServerCardData };
