import { $, closest, containClass } from '../util';

export class UpdateCard {
  constructor(updateCard, postedCard) {
    this.target = $('main');
    {
      this.updateCardTemplate = updateCard;
      this.postCardTemplate = postedCard;
    }

    this.beforeUpdateCard = null;
  }

  init() {
    this.target.addEventListener('dblclick', this.updateCardDblclickHanlder);
    this.target.addEventListener('click', this.updateCardClickHandler);
  }

  updateCardDblclickHanlder = (e) => {
    if (!closest('.default', e.target)) return;
    if (closest('.item-info', e.target)) {
      this.beforeUpdateCard = closest('.list_item', e.target).innerHTML;

      const currCard = closest('.list_item', e.target);
      const title = closest('.item-info', e.target).firstElementChild.innerText;
      const content = closest('.item-info', e.target).lastElementChild
        .innerText;

      currCard.classList.replace('default', 'active');
      currCard.innerHTML = this.updateCardTemplate(title, content);
    }
  };

  updateCardClickHandler = (e) => {
    if (containClass(e.target, 'update-delete-btn')) {
      this.cancelUpdateHandler(e.target);
    }

    if (containClass(e.target, 'update-btn')) {
      e.preventDefault();
      this.updateCardHandler(e.target);
    }
  };

  cancelUpdateHandler = (target) => {
    const currCard = closest('.list_item', target);
    currCard.classList.replace('active', 'default');
    currCard.innerHTML = this.beforeUpdateCard;
  };

  updateCardHandler = (target) => {
    const thisItem = closest('.list_item', target);
    const title = $('.item-title', thisItem).value;
    const content = $('.item-content', thisItem).value;
    thisItem.classList.replace('active', 'default');
    thisItem.innerHTML = this.postCardTemplate({
      title,
      content,
    });
  };
}
