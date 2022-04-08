export const insertCardToColumn = (column, icons, parent = document) => {
  const columnElement = parent.querySelector(`.${column.className}`);
  const cardList = columnElement.querySelector('.task__cards');
  const taskCount = columnElement.querySelector('.title-column__title__count');
  taskCount.textContent = column.total;
  column.tasks.forEach(task => {
    cardList.insertAdjacentHTML('beforeend', createCardTemplate(task, icons));
  });
};

// export const createCards = (mainElement, store, icons) => {
//   store.forEach(cur => {
//     const columnClassName = cur.className;
//     const column = mainElement.querySelector(`.${columnClassName}`);
//     const cardList = column.querySelector('.task__cards');
//     const taskCount = column.querySelector('.title-column__title__count');
//     taskCount.textContent = cur.total;
//     cur.tasks.forEach(task => {
//       cardList.insertAdjacentHTML('beforeend', createCardTemplate(task, icons));
//     });
//   });
// };

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

export const createNewCardTemplate = icons => {
  return `
  <li class="task__card new-card">
  <div class="card__contents">
    <header class="card__contents__header">
      <input class="card__contents__input--header" type="text" placeholder="제목을 입력하세요" />
    </header>
    <main class="card__contents__main">
      <textarea class="card__contents__input--main" maxlength="500" placeholder="내용을 입력하세요"></textarea>
    </main>
    <footer class="card__contents__footer footer__buttons">
      <button class="footer__buttons__cancel">취소</button>
      <button class="footer__buttons__save">등록</button>
    </footer>
  </div>
  <div class="card__delete-btn">
    ${icons.delete}
  </div>
</li>
  `;
};
