import {
  createColumnTemplate,
  createCardTemplate,
  createPostedCardTemplate,
  createUpdateCardTemplate,
} from './todos/template.js';
import { InitTodos } from './todos/initTodos';
import { AddCard } from './todos/addCard.js';
import { DeleteCard } from './todos/deleteCard.js';
import { UpdateCard } from './todos/updateCard.js';
import { DragAndDrop } from './todos/dragAndDrop.js';
import { MenuBar } from './todos/menuBar.js';

export const init = () => {
  const initTodos = new InitTodos(createColumnTemplate);
  const addCard = new AddCard(createCardTemplate, createPostedCardTemplate);
  const deleteCard = new DeleteCard();
  const dragAndDrop = new DragAndDrop();
  const updateCard = new UpdateCard(
    createUpdateCardTemplate,
    createPostedCardTemplate
  );
  const mainMenu = new MenuBar();

  initTodos.init();
  addCard.init();
  deleteCard.init();
  dragAndDrop.init();
  updateCard.init();
  mainMenu.init();
};
