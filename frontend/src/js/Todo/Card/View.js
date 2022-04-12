import { targetQuerySelector } from '../../utils/createTemplate';
import { cardTemplate } from '../../utils/template';

export default class CardView {
  constructor() {}

  renderAddCard(targetColumn, cardId) {
    const cardHtml = cardTemplate({ cardId });
    const targetList = targetQuerySelector({
      target: targetColumn,
      className: 'card_list',
    });
    targetList.insertAdjacentHTML('afterbegin', cardHtml);
  }
}
