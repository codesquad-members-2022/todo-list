import { icons } from '../constants/constant.js';

export const insertColumns = (parentElement, store) => {
  const columns = store.map(column => {
    return createColumnTemplate(column.className, column.title);
  });
  columns.forEach(column => {
    parentElement.insertAdjacentHTML('beforeend', column);
  });
};

const createColumnTemplate = (className, title) => {
  return `
  <div class="column ${className}">
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
  </div>
  `;
};
