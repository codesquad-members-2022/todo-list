import { $, closest, containClass, debounce, getURL } from '../util';

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
    fetch(getURL(id), {
      method: 'DELETE',
      headers: { 'content-type': 'application/json' },
    });
  }

  async deleteSequence(cardItem) {
    const columnName = $(
      '.column-title',
      closest('.column', cardItem)
    ).textContent;

    const res = await fetch(getURL('cardSequence'));
    const json = await res.json();
    const sequence = json[columnName];

    const patchData = {};
    patchData[columnName] = sequence.filter(
      (el) => el !== Number(cardItem.dataset.id)
    );

    fetch(getURL('cardSequence'), {
      method: 'PATCH',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(patchData),
    });
  }
}
