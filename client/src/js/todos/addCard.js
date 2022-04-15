import {
  $,
  closest,
  containClass,
  getURL,
  requestToServer,
  getSequenceData,
} from '../util';

export class AddCard {
  constructor(newCard, postedCard, cardId) {
    this.target = $('main');
    this.newCardTemplate = newCard;
    this.postCardTemplate = postedCard;
    this.cardId = cardId;
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
      : columnList.insertAdjacentHTML(
          'afterbegin',
          this.newCardTemplate(++this.cardId)
        );
  }

  cancelAddCard(columnList) {
    $('.creating', columnList).remove();
  }

  postCard(target) {
    const content = $('.item-content', closest('.list_item', target));
    const maxLength = 200;
    if (content.value.length > maxLength) {
      alert('글자수를 초과하였습니다.');
    } else {
      const cardItem = closest('.list_item', target);
      const title = $('.item-title', cardItem).value;
      const content = $('.item-content', cardItem).value;
      const state = $('.column-title', closest('.column', target)).textContent;
      const cardId = cardItem.dataset.id;
      this.postCardData(title, content, state, cardId);
      this.updateSequence(cardItem);
      this.renderPostedCard(title, content, cardItem);
    }
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

  renderPostedCard(title, content, cardItem) {
    cardItem.classList.remove('creating');
    cardItem.classList.replace('active', 'default');
    cardItem.innerHTML = this.postCardTemplate(title, content);
  }

  postCardData(todoTitle, todoContent, state, cardId) {
    const url = getURL('cards');
    const data = {
      id: cardId,
      title: todoTitle,
      content: todoContent,
      lastTime: new Date(),
      states: state,
    };

    requestToServer(url, 'POST', data);
  }

  async updateSequence(cardItem) {
    const columnName = $(
      '.column-title',
      closest('.column', cardItem)
    ).textContent;

    const sequence = await getSequenceData(columnName);
    sequence.unshift(Number(cardItem.dataset.id));

    const patchData = {};
    patchData[columnName] = sequence;

    const url = getURL('cardSequence');

    requestToServer(url, 'PATCH', patchData);
  }
}
