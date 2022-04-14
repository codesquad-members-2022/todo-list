import { $, closest, containClass } from '../util';

export class AddCard {
  constructor(newCard, postedCard) {
    this.target = $('main');
    this.newCardTemplate = newCard;
    this.postCardTemplate = postedCard;
  }

  init() {
    this.target.addEventListener('click', (e) => this.addCardEventHandler(e));
    this.target.addEventListener('input', (e) => this.inputFocusHandler(e));
  }

  addCardEventHandler(e) {
    const columnList = $('.column-list', closest('.column', e.target));
    if (closest('.plus-btn', e.target)) {
      this.addCard(columnList);
    }

    if (containClass(e.target, 'cancel-add-btn')) {
      this.cancelAddCard(columnList);
    }

    if (containClass(e.target, 'post-btn')) {
      e.preventDefault();
      this.postCard(e.target);
    }
  }

  addCard(columnList) {
    $('.active', columnList) && $('.creating', columnList)
      ? $('.creating', columnList).remove()
      : columnList.insertAdjacentHTML('afterbegin', this.newCardTemplate());
  }

  cancelAddCard(columnList) {
    $('.creating', columnList).remove();
  }

  postCard(target) {
    const content = $('.item-content', closest('.list_item', target));
    const maxLength = 200;
    content.value.length > maxLength
      ? alert('글자수를 초과하였습니다.')
      : this.renderPostedCard(target);
  }

  inputFocusHandler(e) {
    const accentBtn = $('.accent-btn', closest('.list_item', e.target));
    const cardItem = closest('.list_item', e.target);
    const title = $('.item-title', cardItem).value;
    const content = $('.item-content', cardItem).value;
    title && content
      ? accentBtn.removeAttribute('disabled')
      : (accentBtn.disabled = 'true');
  }

  renderPostedCard(target) {
    const cardItem = closest('.list_item', target);
    const title = $('.item-title', cardItem).value;
    const content = $('.item-content', cardItem).value;
    cardItem.classList.remove('creating');
    cardItem.classList.replace('active', 'default');
    cardItem.innerHTML = this.postCardTemplate(title, content);
  }
}
