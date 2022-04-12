import { icons } from '../constants/constant.js';

export const insertColumns = (store, parentElement = document.querySelector('.main')) => {
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

const renderColumn = () => {};
