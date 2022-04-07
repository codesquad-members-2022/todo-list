export const createMainElement = icons => {
  const classNames = [{ todo: '해야할 일' }, { doing: '하고 있는 일' }, { done: '완료한 일' }];
  const columns = classNames.map(classNameObj => {
    const [className, title] = Object.entries(classNameObj)[0];
    return createColumnTemplate(className, title, icons);
  });
  const mainElement = document.createElement('main');
  mainElement.classList.add('main');
  columns.forEach(column => {
    mainElement.insertAdjacentHTML('beforeend', column);
  });
  return mainElement;
};

const createColumnTemplate = (className, title, icons) => {
  return `
  <div class="box ${className}">
    <div class="title-box">
      <div class="title-box__title">
        <div class="title-box__title__text">${title}</div>
        <div class="title-box__title__count">0</div>
      </div>
      <div class="title-box__btn">
        <div class="title-box__btn__add">
          ${icons.add}
        </div>
        <div class="title-box__btn__delete">
          ${icons.delete}
        </div>
      </div>
    </div>
    <ul class="task__cards">
    </ul>
  </div>
  `;
};
