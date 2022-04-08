import '../scss/main.scss';
import { icons, todoListUrl } from './constants/constant.js';
import { createHeaderElement } from './views/header.js';
import { createMainObj } from './views/main.js';
import { appendElementsToBody } from './utils/util.js';
import { insertColumns } from './views/columns.js';
import { insertAllCardToColumn } from './views/card.js';
import { onAddBtnClick } from './views/newCard.js';

(async () => {
  const headerElement = createHeaderElement(icons.menu);
  const main = await createMainObj(todoListUrl);
  insertColumns(main.element, main.getStore(), icons);
  insertAllCardToColumn(main, icons);
  appendElementsToBody(headerElement, main.element);
  onAddBtnClick();
})();
