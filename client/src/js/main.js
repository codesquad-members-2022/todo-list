import {
  createColumnTemplate,
  createCardTemplate,
  createPostedCardTemplate,
} from './template.js';
import { InitTodos } from './todos';
import { AddCard } from './CRUDClass/addCard';
import { DeleteCard } from './CRUDClass/deleteCard';
import { DragAndDrop } from './dragAndDrop.js';

export const init = () => {
  const initTodos = new InitTodos(createColumnTemplate);
  initTodos.init();

  const addCard = new AddCard(createCardTemplate, createPostedCardTemplate);
  addCard.init();

  const deleteCard = new DeleteCard();
  deleteCard.init();

  const dragAndDrop = new DragAndDrop();
  dragAndDrop.init();
};
