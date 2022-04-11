import {
  createColumnTemplate,
  createCardTemplate,
  createPostedCardTemplate,
} from './template.js';
import { InitTodos } from './todos';
import { AddCard } from './CRUDClass/addCard';

export const init = () => {
  const initTodos = new InitTodos(createColumnTemplate);
  initTodos.init();

  const addCard = new AddCard(createCardTemplate, createPostedCardTemplate);
  addCard.init();
};
