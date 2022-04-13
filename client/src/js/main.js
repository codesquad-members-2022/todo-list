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
  initTodos.init();

  const addCard = new AddCard(createCardTemplate, createPostedCardTemplate);
  addCard.init();

  const deleteCard = new DeleteCard();
  deleteCard.init();

  const dragAndDrop = new DragAndDrop();
  dragAndDrop.init();

  const updateCard = new UpdateCard(
    createUpdateCardTemplate,
    createPostedCardTemplate
  );
  updateCard.init();
  const mainMenu = new MenuBar();
  mainMenu.init();
};
