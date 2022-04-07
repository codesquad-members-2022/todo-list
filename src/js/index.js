import '../scss/main.scss';
import { icons } from './constants/constant.js';
import { createHeaderTemplate } from './views/header.js';
import { createMainElement } from './views/main.js';
import { createCardTemplate } from './views/card.js';

(() => {
  const headerTemplate = createHeaderTemplate(icons.menu);
  document.body.insertAdjacentHTML('afterbegin', headerTemplate);
  const mainElement = createMainElement(icons);

  // 데이터 처리 로직 테스트
  // [...mainElement.children].forEach(cur => {
  //   const test = cur.querySelector('.title-box__title__count');
  //   test.innerHTML = 10;
  // });

  document.body.appendChild(mainElement);

  const todoColumn = mainElement.querySelector('.todo');
  const todoCardList = todoColumn.querySelector('.task__cards');
  todoCardList.innerHTML = createCardTemplate(
    { header: '헤더', main: 'main', footer: 'footer', datetime: '20220407 11:07am' },
    icons
  );
})();
