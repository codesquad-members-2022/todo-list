import { getTodos, postTodos } from "../model/cardModel.js";
import { renderColumn, updateCard } from "../view/cardView.js";

async function renderTodos() {
  const todos = await getTodos();
  renderColumn("#have-to-do-column", todos.haveToDoColumn);
  renderColumn("#doing-column", todos.doingColumn);
  renderColumn("#done-column", todos.doneColumn);
}

async function handleClickRegisterBtn(target) {
  const card = updateCard(target);
  await postTodos(card);
}

export { renderTodos, handleClickRegisterBtn };
