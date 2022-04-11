import { icons } from '../constants/constant.js';

export const createNewCardTemplate = () => {
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
      <input type=button class="footer__buttons__cancel" value="취소">
      <input type=button class="footer__buttons__save" value="등록" disabled/>
    </footer>
  </div>
  <div class="card__delete-btn">
    ${icons.delete}
  </div>
</li>
  `;
};

const onNewCardInput = newCard => {
  newCard.addEventListener('keyup', () => {
    changeSaveBtnStatus();
  });
};

const changeSaveBtnStatus = () => {
  const titleInput = document.querySelector('.card__contents__input--header');
  const textArea = document.querySelector('.card__contents__input--main');
  const saveBtn = document.querySelector('.footer__buttons__save');
  if (textArea.value && titleInput.value) {
    saveBtn.disabled = false;
    saveBtn.style.backgroundColor = '#0175de';
    saveBtn.style.cursor = 'pointer';
  } else {
    saveBtn.disabled = true;
    saveBtn.style.backgroundColor = '#86c5ff';
    saveBtn.style.cursor = '';
  }
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
    const newCardTemplate = createNewCardTemplate();
    cardList.insertAdjacentHTML('afterbegin', newCardTemplate);
    const newCard = document.querySelector('.card__contents');
    const textArea = cardList.querySelector('.card__contents__input--main');
    onNewCardInput(newCard);
    onResizeTextArea(textArea);
    onSaveBtnClick();
  }
};
