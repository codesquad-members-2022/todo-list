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

export const onDeleteAllCardInnerColumn = store => {
  const mainElement = $('main');
  mainElement.addEventListener('click', e => {
    deleteColumnAllCard(e, store);
  });
};

const deleteColumnAllCard = (e, store) => {
  const target = e.target;
  const targetBtn = target.closest('.title-column__btn__delete');
  if (!targetBtn) {
    return;
  }
  const targetColumn = targetBtn.parentElement.parentElement.parentElement;
  const targetColumnTitle = [...targetColumn.classList].find(className => className !== 'column');
  const columns = store.getStore('main');
  columns.forEach(column => {
    if (column.className !== targetColumnTitle) {
      return;
    }
    column.total = 0;
    column.tasks = [];
    const url = TODO_LIST_URL + '/' + column.id;
    saveToDB(url, column);
  });
  rerender(store);
};
