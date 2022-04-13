import { $, closest, containClass } from '../util';

export class AddCard {
  constructor(newCard, postedCard) {
    this.target = $('main');
    {
      this.newCardTemplate = newCard;
      this.postCardTemplate = postedCard;
    }
  }

  init() {
    this.target.addEventListener('click', this.addCardEventHandler);
    this.target.addEventListener('input', this.inputFocusHandler);
  }

  addCardHandler = (e) => {
    const columnList = $('.column-list', closest('.column', e.target));
    $('.active', columnList)
      ? columnList.firstElementChild.remove()
      : columnList.insertAdjacentHTML('afterbegin', this.newCardTemplate());
  };

  cancelAddHandler = (e) => {
    const columnList = $('.column-list', closest('.column', e.target));
    columnList.firstElementChild.remove();
  };

  postCardHandler = (target) => {
    const content = $('.item-content', closest('.list_item', target));
    const maxLength = 200;
    content.value.length > maxLength
      ? alert('글자수를 초과하였습니다.')
      : this.renderPostedCard(target);
  };

  inputFocusHandler = (e) => {
    const accentBtn = $('.accent-btn', closest('.list_item', e.target));
    const thisItem = closest('.list_item', e.target);
    const title = $('.item-title', thisItem).value;
    const content = $('.item-content', thisItem).value;
    title && content
      ? accentBtn.removeAttribute('disabled')
      : (accentBtn.disabled = 'true');
  };

  renderPostedCard = (target) => {
    const thisItem = closest('.list_item', target);
    const title = $('.item-title', thisItem).value;
    const content = $('.item-content', thisItem).value;
    thisItem.classList.replace('active', 'default');
    thisItem.innerHTML = this.postCardTemplate({
      title,
      content,
    });
  };

  addCardEventHandler = (e) => {
    if (closest('.plus-btn', e.target)) {
      this.addCardHandler(e);
    }

    if (containClass(e.target, 'cancel-add-btn')) {
      this.cancelAddHandler(e);
    }

    if (containClass(e.target, 'accent-btn')) {
      e.preventDefault();
      this.postCardHandler(e.target);
    }
  };
}
