export const createMainTemplate = icons => {
  const classNames = [{todo: '해야할 일'}, {doing: '하고 있는 일'}, {done: '완료한 일'}];
  const columns = classNames.map(classNameObj =>
    createColumnTemplate(Object.keys(classNameObj)[0], Object.values(classNameObj)[0], icons)
  );
  const mainNode = document.createElement('main');
  mainNode.classList.add('main');
  columns.forEach(column => {
    mainNode.insertAdjacentHTML('beforeend', column);
  });
  return mainNode;
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
