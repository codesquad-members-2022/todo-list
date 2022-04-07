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
      <input class="card__contents__input--main" type="text" placeholder="내용을 입력하세요" />
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
