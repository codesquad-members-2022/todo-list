import '../scss/main.scss';
import { icons } from './constants/constant.js';
import { createHeaderTemplate } from './views/header.js';
import { createMainElement } from './views/main.js';
import { createCardTemplate } from './views/card.js';
import { fetchData } from './utils/util';

(() => {
  const headerTemplate = createHeaderTemplate(icons.menu);
  document.body.insertAdjacentHTML('afterbegin', headerTemplate);
  const mainElement = createMainElement(icons);
  document.body.appendChild(mainElement);

  const fetchTasks = async () => {
    try {
      const tasks = await fetchData('http://localhost:3000/tasks');
      tasks.forEach(task => {
        const column = mainElement.querySelector(`.${task.status}`);
        const cardList = column.querySelector('.task__cards');
        cardList.insertAdjacentHTML('beforeend', createCardTemplate(task, icons));
        const taskCount = column.querySelector('.title-box__title__count');
        taskCount.textContent = cardList.children.length;
      }, '');
    } catch (err) {
      console.log(err);
    }
  };

  fetchTasks();
})();
