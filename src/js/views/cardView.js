export const insertCardToColumn = (column, icons, parent = document) => {
  const columnElement = parent.querySelector(`.${column.className}`);
  const cardList = columnElement.querySelector('.task__cards');
  const taskCount = columnElement.querySelector('.title-column__title__count');
  taskCount.textContent = column.total;
  column.tasks.forEach(task => {
    cardList.insertAdjacentHTML('beforeend', createCardTemplate(task, icons));
  });
};

export const insertAllCardToColumn = (parent, store, storeTargetName, icons) => {
  const thisStore = store.getStore(storeTargetName);
  thisStore.forEach(column => {
    insertCardToColumn(column, icons, parent);
  });
};

export const createCardTemplate = (task, icons) => {
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
