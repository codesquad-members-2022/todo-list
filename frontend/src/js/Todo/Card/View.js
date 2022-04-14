import { targetQuerySelector } from '../../utils/createTemplate';
import { cardTemplate } from '../../utils/template';

export default class CardView {
  constructor() {}

  eventInit({
    cardInputHandler,
    cardAddHandler,
    cardCancelHandler,
    cardDeleteHandler,
  }) {
    const writeCard = document.querySelector('.card.write');
    const accentBtn = writeCard.querySelector('.accent_btn');
    const cancelBtn = writeCard.querySelector('.normal_btn');
    const deleteBtn = writeCard.querySelector('.delete_btn');
    writeCard.addEventListener('input', cardInputHandler);
    accentBtn.addEventListener('click', cardAddHandler);
    cancelBtn.addEventListener('click', cardCancelHandler);
    deleteBtn.addEventListener('click', cardDeleteHandler);
  }

  renderAddCard(targetColumn, cardId) {
    const cardHtml = cardTemplate({ cardId });
    const targetList = targetQuerySelector({
      target: targetColumn,
      className: 'card_list',
    });
    targetList.insertAdjacentHTML('afterbegin', cardHtml);
  }

  renderDeleted(card) {
    card.remove();
  }
}
