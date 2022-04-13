import { icons } from '../constants/constant.js';

export const insertCardToColumn = (column, parent = document) => {
  const columnElement = parent.querySelector(`.${column.className}`);
  const cardList = columnElement.querySelector('.task__cards');
  const taskCount = columnElement.querySelector('.title-column__title__count');
  taskCount.textContent = column.total;
  column.tasks.forEach(task => {
    cardList.insertAdjacentHTML('beforeend', createCardTemplate(task));
  });
};

export const insertAllCardToColumn = (store = store.getStore('main'), parent = document.querySelector('.main')) => {
  store.forEach(column => {
    insertCardToColumn(column, parent);
  });
};

export const createCardTemplate = task => {
  return `
    <li class="task__card" data-datetime=${task.datetime} data-id=${task.id}>
    <div class="card__contents">
      <header class="card__contents__header">${task.header}</header>
      <main class="card__contents__main">${task.main}</main>
      <footer class="card__contents__footer">${task.footer}</footer>
    </div>
    <div class="card__delete-btn">
      ${icons.delete}
    </div>
  </li>
    `;
};
