import '../scss/main.scss';
import { icons } from './constants/constant.js';
import { createHeaderElement } from './views/header.js';
import { createMainElement } from './views/main.js';
import { controller } from './controller';

(() => {
  const header = createHeaderElement(icons.menu);
  const main = createMainElement();
  const views = { header, main };
  controller(views);
})();
