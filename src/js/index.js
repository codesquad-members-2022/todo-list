import '../scss/main.scss';
import { createHeaderElement } from './views/headerView.js';
import { createMainElement } from './views/mainView.js';
import { controller } from './controller/controller.js';

(() => {
  const headerView = createHeaderElement();
  const mainView = createMainElement();
  const views = { headerView, mainView };
  controller(views);
})();
