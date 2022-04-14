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

export const insertAllCardToColumn = (store = store.getStore('main'), parent = document.querySelector('.main')) => {
  store.forEach(column => {
    insertCardToColumn(column, parent);
  });
};

export const createCardTemplate = task => {
  return `
    <li class="task__card" data-datetime=${task.datetime} data-id=${task.cardId}>
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

export const onCardDoubleClick = store => {
  const main = document.querySelector('.main');
  main.addEventListener('dblclick', ({ target }) => {
    if (main.querySelector('.edit-card') || !target.closest('.task__card')) return;
    cardDoubleClickHandler(target, store);
  });
};

const cardDoubleClickHandler = (target, store) => {
  const targetCard = target.closest('.task__card');
  if (!targetCard.querySelector('.card__delete-btn')) return;

  const columnName = targetCard.closest('.column').classList[1];
  const columnData = store.getStore('main').find(column => column.className === columnName);
  const cardId = targetCard.dataset.id;
  const todoData = columnData.tasks.find(task => task.cardId === Number(cardId));

  targetCard.style.display = 'none';
  store.notify('edit', targetCard, todoData);

  const currentColumn = document.querySelector(`.${columnName}`);
  const editCardElement = currentColumn.querySelector('.edit-card');
  onCardEditBtnClick(currentColumn, editCardElement, targetCard, todoData, columnData);
};

const onCardEditBtnClick = (currentColumn, editCardElement, targetCard, todoData, columnData) => {
  currentColumn.addEventListener('click', ({ target }) => {
    cardEditBtnClickHandler(target, editCardElement, targetCard, todoData, columnData);
  });
};

const cardEditBtnClickHandler = (target, editCardElement, targetCard, todoData, columnData) => {
  if (!target.closest('.footer__buttons')) return;

  if (target.value === '수정') {
    const editedTitle = editCardElement.querySelector('.card__contents__input--header').value;
    const editedContents = editCardElement.querySelector('.card__contents__input--main').value;

    columnData.tasks.forEach(v => {
      if (v.cardId === todoData.cardId) {
        v.header = editedTitle;
        v.main = editedContents;
      }
    });

    const targetCardTitle = targetCard.querySelector('.card__contents__header');
    const targetCardContents = targetCard.querySelector('.card__contents__main');
    targetCardTitle.innerHTML = editedTitle;
    targetCardContents.innerHTML = editedContents;
  }

  targetCard.style.display = '';
  editCardElement.remove();
};
