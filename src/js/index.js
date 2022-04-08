import '../scss/main.scss';
import { icons } from './constants/constant.js';
import { createHeaderElement } from './views/header.js';
import { createMainElement } from './views/main.js';
import { appendElementsToBody, fetchData } from './utils/util';
import { addNewCard } from './handler.js';
import { insertColumns } from './views/columns.js';
import { createCards, createCard } from './views/card.js';

(async () => {
  const data = await fetchData('http://localhost:3000/result');
  const headerElement = createHeaderElement(icons.menu);
  const main = createMainElement(data);
  insertColumns(main.element, main.getStore(), icons);
  main.getStore().forEach(column => {
    createCard(column, icons, main.element);
  });
  // createCards(main.element, column, icons);
  appendElementsToBody(headerElement, main.element);
  addNewCard();
})();
