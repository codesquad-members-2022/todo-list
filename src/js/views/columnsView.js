import { icons, TODO_LIST_URL } from '../constants/constant.js';
import { $, rerender, saveToDB } from '../utils/util.js';

export const insertColumns = (store, parentElement) => {
  if (!parentElement) {
    parentElement = $('.main');
  }
  const columns = getAllColumns(store);
  columns.forEach(column => {
    parentElement.appendChild(column);
  });
};

const getAllColumns = store => {
  return store.map(column => {
    return createColumnElement(column.className, column.title);
  });
};

const createColumnElement = (className, title) => {
  const columnElement = document.createElement('div');
  columnElement.classList.add('column', className);
  columnElement.innerHTML = createColumnTemplate(className, title);
  return columnElement;
};

const createColumnTemplate = (className, title) => {
  return `
    <div class="title-column">
      <div class="title-column__title">
        <div class="title-column__title__text">${title}</div>
        <div class="title-column__title__count">0</div>
      </div>
      <div class="title-column__btn">
        <div class="title-column__btn__add" data-classname=${className}>
          ${icons.add}
        </div>
        <div class="title-column__btn__delete">
          ${icons.delete}
        </div>
      </div>
    </div>
    <ul class="task__cards">
    </ul>
  `;
};

export const onDeleteAllCardsInColumn = store => {
  const mainElement = $('main');
  mainElement.addEventListener('click', ({ target }) => {
    deleteAllCardsHandler(target, store);
  });
};

const deleteAllCardsHandler = (target, store) => {
  const targetBtn = target.closest('.title-column__btn__delete');
  if (!targetBtn) {
    return;
  }
  const targetColumn = target.closest('.column');
  const targetColumnTitle = [...targetColumn.classList].find(className => className !== 'column');
  const todoListData = store.getStore('main');
  const targetColumnData = todoListData.find(column => column.className === targetColumnTitle);
  targetColumnData.total = 0;
  targetColumnData.tasks = [];
  const url = TODO_LIST_URL + '/' + targetColumnData.id;
  saveToDB(url, targetColumnData);
  rerender(store);
};
