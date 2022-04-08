import { getTodos } from "../model/cardModel.js";
import { renderColumn } from "../view/cardView.js";

async function renderTodos() {
  const todos = await getTodos();
  renderColumn("#have-to-do-column", todos.haveToDoColumn);
  renderColumn("#doing-column", todos.doingColumn);
  renderColumn("#done-column", todos.doneColumn);
}

export { renderTodos };
