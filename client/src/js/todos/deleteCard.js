import {
  $,
  closest,
  containClass,
  debounce,
  getURL,
  requestToServer,
  getSequenceData,
} from '../util';

export class DeleteCard {
  constructor() {
    this.target = $('main');
    this.layer = $('.layer');
    this.currentCard;
  }

  init() {
    document.addEventListener('click', (e) => this.deleteCardEventHandler(e));
    this.target.addEventListener(
      'mouseover',
      debounce((e) => this.mouseEnterHandler(e), 500)
    );
    this.target.addEventListener('mouseout', (e) => this.mouseLeaveHandler(e));
  }

  deleteCardEventHandler(e) {
    if (closest('.card-close-btn', e.target)) {
      this.currentCard = closest('.list_item', e.target);
      this.layer.style.display = 'block';
      this.layer.classList.add('make-black');
    }

    if (containClass(e.target, 'alert-accent-btn')) {
      this.layer.style.display = 'none';
      this.deleteCardData(this.currentCard.dataset.id);
      this.deleteSequence(this.currentCard);
      this.currentCard.remove();
    }

    if (containClass(e.target, 'alert-normal-btn')) {
      this.layer.style.display = 'none';
    }
  }

  mouseEnterHandler(e) {
    if (closest('.card-close-btn', e.target)) {
      const listItem = closest('.list_item', e.target);
      listItem.classList.replace('default', 'delete');
    }
  }

  mouseLeaveHandler(e) {
    if (closest('.card-close-btn', e.target)) {
      const listItem = closest('.list_item', e.target);
      listItem.classList.replace('delete', 'default');
    }
  }

  deleteCardData(id) {
    const url = getURL(`cards/${id}`);
    requestToServer(url, 'DELETE');
  }

  async deleteSequence(cardItem) {
    const columnName = $(
      '.column-title',
      closest('.column', cardItem)
    ).textContent;

    const sequence = await getSequenceData(columnName);
    const patchData = {};
    patchData[columnName] = sequence.filter(
      (el) => el !== Number(cardItem.dataset.id)
    );
    const url = getURL('cardSequence');

    requestToServer(url, 'PATCH', patchData);
  }
}
