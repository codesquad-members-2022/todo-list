import { icons } from '../constants/constant.js';

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

export const onAddBtnClick = () => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('click', ({ target }) => {
    handleAddBtnClick(mainElement, target);
  });
};

const handleAddBtnClick = (mainElement, target) => {
  if (!target.closest(`.title-column__btn__add`)) {
    return;
  }
  const className = target.closest(`.title-column__btn__add`).dataset.classname;
  const column = mainElement.querySelector(`.${className}`);
  const cardList = column.querySelector('.task__cards');
  toggleNewCard(cardList);
};

const resizeTextArea = textArea => {
  textArea.style.height = 'auto';
  textArea.style.height = textArea.scrollHeight + 'px';
};

const onResizeTextArea = textArea => {
  textArea.addEventListener('keyup', () => {
    resizeTextArea(textArea);
  });
  textArea.addEventListener('keydown', () => {
    resizeTextArea(textArea);
  });
};

const toggleNewCard = cardList => {
  const newCard = cardList.querySelector('.new-card');
  if (newCard) {
    newCard.remove();
  } else {
    const newCardTemplate = createNewCardTemplate(icons);
    cardList.insertAdjacentHTML('afterbegin', newCardTemplate);
    const textArea = cardList.querySelector('.card__contents__input--main');
    onResizeTextArea(textArea);
  }
};
