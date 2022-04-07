import {icons} from './constants/constant.js';
import {createHeaderTemplate} from './views/header.js';
import {createMainTemplate} from './views/main.js';

(() => {
  const headerTemplate = createHeaderTemplate(icons.menu);
  document.body.insertAdjacentHTML('afterbegin', headerTemplate);
  const mainNode = createMainTemplate(icons);

  // 데이터 처리 로직 테스트
  // [...mainNode.children].forEach(cur => {
  //   const test = cur.querySelector('.title-box__title__count');
  //   test.innerHTML = 10;
  // });

  document.body.appendChild(mainNode);
})();
