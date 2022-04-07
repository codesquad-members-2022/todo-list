import '../scss/main.scss';
import { icons } from './constants/constant.js';
import { createHeaderElement } from './views/header.js';
import { createMainElement } from './views/main.js';
import { appendElementsToBody } from './utils/util';
import { renderTasks } from './render.js';
import { addNewCard } from './handler.js';

(() => {
  const headerElement = createHeaderElement(icons.menu);
  const mainElement = createMainElement(icons);
  appendElementsToBody(headerElement, mainElement);

  renderTasks(mainElement);
  addNewCard();
})();
