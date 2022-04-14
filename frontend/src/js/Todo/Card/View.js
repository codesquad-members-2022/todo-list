import { targetQuerySelector } from '../../utils/createTemplate';
import { cardTemplate } from '../../utils/template';

export default class CardView {
  constructor() {}

  eventInit({
    cardInputHandler,
    cardAddHandler,
    cardDeleteHandler,
    hoverHandler,
  }) {
    const writeCard = document.querySelector('.card.write');
    const accentBtn = writeCard.querySelector('.accent_btn');
    const deleteBtn = writeCard.querySelector('.delete_btn');
    writeCard.addEventListener('input', cardInputHandler);
    accentBtn.addEventListener('click', cardAddHandler);
    deleteBtn.addEventListener('click', cardDeleteHandler);
    deleteBtn.addEventListener('mouseover', hoverHandler);
    deleteBtn.addEventListener('mouseout', hoverHandler);
  }

  renderAddCard(targetColumn, cardId) {
    const cardHtml = cardTemplate({ cardId });
    const targetList = targetQuerySelector({
      target: targetColumn,
      className: 'card_list',
    });
    targetList.insertAdjacentHTML('afterbegin', cardHtml);
  }

  changeEditMode(card, targetText) {
    card.classList.add('write', 'edit');
    card.querySelector('.accent_btn').innerText = '수정';
    if (!targetText) return;
    targetText.nextElementSibling.focus();
  }

  cancelEditMode({ targetCard, titleInput, contentInput, value }) {
    targetCard.classList.remove('write', 'edit');
    titleInput.value = value.title;
    contentInput.value = value.content;
  }

  renderDeleted(card) {
    card.remove();
  }

  changeDeleteMode(card) {
    if (card.classList.contains('delete_hover')) {
      card.classList.remove('delete_hover');
      return;
    }
    card.classList.add('delete_hover');
  }
}
