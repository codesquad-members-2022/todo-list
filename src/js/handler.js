import { icons } from './constants/constant.js';
import { createNewCardTemplate } from './views/card.js';

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

export const addNewCard = () => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('click', ({ target }) => {
    if (!target.closest(`.title-column__btn__add`)) {
      return;
    }
    const className = target.closest(`.title-column__btn__add`).dataset.classname;
    const column = mainElement.querySelector(`.${className}`);
    const cardList = column.querySelector('.task__cards');

    toggleNewCard(cardList);
  });
};
