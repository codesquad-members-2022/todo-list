import { icons } from '../constants/constant.js';

export const insertCardToColumn = (columnData, parent = document) => {
  const columnElement = parent.querySelector(`.${columnData.className}`);
  const cardList = columnElement.querySelector('.task__cards');
  const taskCount = columnElement.querySelector('.title-column__title__count');
  taskCount.textContent = columnData.total;

  columnData.tasks.forEach(task => {
    cardList.insertAdjacentHTML('beforeend', createCardTemplate(task));
  });
};

export const insertAllCardToColumn = (parent, store) => {
  store.forEach(columnData => {
    insertCardToColumn(columnData, parent);
  });
};

export const createCardTemplate = task => {
  return `
    <li class="task__card" data-datetime=${task.datetime}>
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

export const addNewCardToColumn = (column, taskData, totalCount) => {
  const newTodo = createCardTemplate(taskData);
  const cardList = column.querySelector('.task__cards');
  const cardCount = column.querySelector('.title-column__title__count');
  cardList.insertAdjacentHTML('afterbegin', newTodo);
  cardCount.textContent = totalCount;
};
