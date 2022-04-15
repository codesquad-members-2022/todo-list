import { $, closest, containClass } from '../util';

export class UpdateCard {
  constructor(updateCard, postedCard) {
    this.target = $('main');
    this.updateCardTemplate = updateCard;
    this.postCardTemplate = postedCard;
    this.beforeUpdateCard = null;
    this.currCard;
  }

  init() {
    this.target.addEventListener('dblclick', (e) =>
      this.updateCardDblclickHanlder(e)
    );
    this.target.addEventListener('click', (e) =>
      this.updateCardClickHandler(e)
    );
  }

  updateCardDblclickHanlder(e) {
    if (!closest('.default', e.target)) return;
    if (closest('.item-info', e.target)) {
      this.beforeUpdateCard = closest('.list_item', e.target).innerHTML;

      this.currCard = closest('.list_item', e.target);
      const title = $('.item-info', this.currCard).firstElementChild.innerText;
      const content = $('.item-info', this.currCard).lastElementChild.innerText;

      this.currCard.classList.replace('default', 'active');
      this.currCard.innerHTML = this.updateCardTemplate(title, content);
    }
  }

  updateCardClickHandler(e) {
    if (containClass(e.target, 'update-delete-btn')) {
      this.cancelUpdateHandler(e.target);
    }

    if (containClass(e.target, 'update-btn')) {
      e.preventDefault();
      this.updateCardHandler(e.target);
    }
  }

  cancelUpdateHandler(target) {
    this.currCard = closest('.list_item', target);
    this.currCard.classList.replace('active', 'default');
    this.currCard.innerHTML = this.beforeUpdateCard;
  }

  updateCardHandler(target) {
    this.currCard = closest('.list_item', target);
    const title = $('.item-title', this.currCard).value;
    const content = $('.item-content', this.currCard).value;
    this.currCard.classList.replace('active', 'default');
    this.currCard.innerHTML = this.postCardTemplate(title, content);
    this.updateCardData(title, content, this.currCard.dataset.id);
  }

  updateCardData(todoTitle, todoContent, cardId) {
    const lastTime = new Date();
    const data = {
      title: todoTitle,
      content: todoContent,
      lastTime: lastTime,
    };

    fetch(url(`cards/${cardId}`), {
      method: 'PATCH',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(data),
    });
  }
}
