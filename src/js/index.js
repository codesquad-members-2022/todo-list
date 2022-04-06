import {icons} from './constants/constant.js';
import {createHeaderTemplate} from './views/header.js';

(() => {
  const header = createHeaderTemplate(icons.menu);
  document.body.insertAdjacentHTML('afterbegin', header);
})();
