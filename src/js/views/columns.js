export const insertColumns = (parentElement, store, storeTargetName, icons) => {
  const thisStore = store.getStore(storeTargetName);
  const columns = thisStore.map(column => {
    return createColumnTemplate(column.className, column.title, icons);
  });
  columns.forEach(column => {
    parentElement.insertAdjacentHTML('beforeend', column);
  });
};

const createColumnTemplate = (className, title, icons) => {
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
