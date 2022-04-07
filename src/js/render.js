import { icons } from './constants/constant.js';
import { fetchData } from './utils/util.js';
import { createCardTemplate } from './views/card.js';

export const renderTasks = async mainElement => {
  try {
    const tasks = await fetchData('http://localhost:3000/tasks');
    tasks.forEach(task => {
      const column = mainElement.querySelector(`.${task.status}`);
      const cardList = column.querySelector('.task__cards');
      cardList.insertAdjacentHTML('beforeend', createCardTemplate(task, icons));
      const taskCount = column.querySelector('.title-column__title__count');
      taskCount.textContent = cardList.children.length;
    }, '');
  } catch (err) {
    console.log(err);
  }
};
