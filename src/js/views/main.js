export const createMainTemplate = () => {
  const classNames = [{todo: '해야할 일'}, {doing: '하고 있는 일'}, {done: '완료한 일'}];
  const columns = classNames.map(classNameObj =>
    createColumnTemplate(Object.keys(classNameObj)[0], Object.values(classNameObj)[0])
  );
  const mainNode = document.createElement('main');
  mainNode.classList.add('main');
  columns.forEach(column => {
    mainNode.insertAdjacentHTML('beforeend', column);
  });
  return mainNode;
};

const createColumnTemplate = (className, title) => {
  const icons = {
    add: `
    <svg width="14" height="14" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path
      d="M0.105709 7.53033L0.105709 6.46967H6.46967V0.105713H7.53033V6.46967H13.8943V7.53033H7.53033V13.8943H6.46967V7.53033H0.105709Z"
      fill="#bdbdbd"
    />
    </svg>
    `,
    delete: `<svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path
      d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
      fill="#bdbdbd"
    />
  </svg>`,
  };

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
