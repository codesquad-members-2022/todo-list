import { $, closest, containClass } from '../util';

export class DeleteCard {
  constructor() {
    this.target = $('main');
    this.layer = $('.layer');
    this.currentCard;
  }
  init() {
    document.addEventListener('click', this.deleteCardEventHandler);
    this.target.addEventListener('mouseover', this.mouseEnterHandler);
    this.target.addEventListener('mouseout', this.mouseLeaveHandler);
  }

  deleteCardEventHandler = (e) => {
    if (closest('.card-close-btn', e.target)) {
      this.currentCard = closest('.list_item', e.target);
      this.layer.style.display = 'block';
      this.layer.classList.add('make-black');
    }

    if (containClass(e.target, 'alert-accent-btn')) {
      this.layer.style.display = 'none';
      this.currentCard.remove();
    }

    if (containClass(e.target, 'alert-normal-btn')) {
      this.layer.style.display = 'none';
    }
  };

  mouseEnterHandler = (e) => {
    if (closest('.card-close-btn', e.target)) {
      const listItem = closest('.list_item', e.target);
      listItem.classList.replace('default', 'delete');
    }
  };

  mouseLeaveHandler = (e) => {
    if (closest('.card-close-btn', e.target)) {
      const listItem = closest('.list_item', e.target);
      listItem.classList.replace('delete', 'default');
    }
  };
}
