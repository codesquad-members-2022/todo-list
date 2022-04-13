import { $, $$, closest } from '../util.js';

export class DragAndDrop {
  constructor() {
    this.target = document;

    this.isDragging = null;
    this.isClone = false;

    this.dragCard = null;
    this.clonedDragCard = null;
    this.cardPositionInfo = [];
    this.moveCardState = false;
  }

  init() {
    this.target.addEventListener('mousedown', this.mousedownEventHandler);
    this.target.addEventListener('mouseup', this.mouseupEventHandler);
    this.target.addEventListener('mousemove', this.mousemoveEventHandler);
  }

  mousedownEventHandler = (e) => {
    if (closest('.default', e.target)) {
      this.isDragging = true;
      this.dragCard = closest('.default', e.target);
      this.column = closest('.column-list', e.target);
    }
  };

  mouseupEventHandler = (e) => {
    this.isDragging = false;
    this.moveCardState = false;
    if (this.isClone) {
      this.clonedDragCard.remove();
      this.dragCard.classList.replace('place', 'default');
      this.isClone = null;
      this.cardPositionInfo = [];
    }
  };

  mousemoveEventHandler = (e) => {
    if (!this.isDragging) return;

    if (!this.isClone) {
      this.setCardsPosition();
      this.cloneCard(e);
    }

    const mouseX = e.clientX;
    const mouseY = e.clientY;
    this.clonedDragCard.style.left = mouseX + 'px';
    this.clonedDragCard.style.top = mouseY + 'px';

    const cloneCardPosData = this.clonedDragCard.getBoundingClientRect();
    const columnPosData = this.column.getBoundingClientRect();

    const draggedX = cloneCardPosData.x;
    const draggedY = cloneCardPosData.y;

    if (columnPosData.x - cloneCardPosData.x > cloneCardPosData.width / 2) {
      $(
        '.column-list',
        this.column.parentElement.previousElementSibling
      ).appendChild(this.dragCard);
    } else if (
      columnPosData.x + columnPosData.width - cloneCardPosData.x <
      cloneCardPosData.width / 2
    ) {
      $(
        '.column-list',
        this.column.parentElement.nextElementSibling
      ).appendChild(this.dragCard);
    }

    this.cardPositionInfo.forEach((el) => {
      const prevCard = el.x - draggedX > 0 ? cloneCardPosData : el;
      const curCard = el.x - draggedX > 0 ? el : cloneCardPosData;

      const higherCard = el.y - draggedY > 0 ? cloneCardPosData : el;
      const lowerCard = el.y - draggedY > 0 ? el : cloneCardPosData;

      if (
        this.dragCard.getBoundingClientRect().x === el.x &&
        !this.moveCardState
      ) {
        if (prevCard.x + prevCard.width - curCard.x > prevCard.width / 2) {
          if (
            cloneCardPosData.y < el.y &&
            higherCard.y + higherCard.height - lowerCard.y >
              lowerCard.height / 2
          ) {
            el.positionedCard.insertAdjacentElement('afterend', this.dragCard);
          }

          if (
            cloneCardPosData.y > el.y &&
            higherCard.y + higherCard.height - lowerCard.y >
              higherCard.height / 2
          ) {
            el.positionedCard.insertAdjacentElement(
              'beforebegin',
              this.dragCard
            );
          }
        }
      } else {
        this.moveCardState = true;

        if (prevCard.x + prevCard.width - curCard.x > prevCard.width / 2) {
          if (
            cloneCardPosData.y < el.y &&
            higherCard.y + higherCard.height - lowerCard.y >
              lowerCard.height / 2
          ) {
            el.positionedCard.insertAdjacentElement(
              'beforebegin',
              this.dragCard
            );
          }

          if (
            cloneCardPosData.y > el.y &&
            higherCard.y + higherCard.height - lowerCard.y >
              higherCard.height / 2
          ) {
            el.positionedCard.insertAdjacentElement('afterend', this.dragCard);
          }
        }
      }
    });
  };

  setCardsPosition() {
    for (const li of $$('.list_item')) {
      this.cardPositionInfo.push({
        x: li.getBoundingClientRect().x,
        y: li.getBoundingClientRect().y,
        width: li.getBoundingClientRect().width,
        height: li.getBoundingClientRect().height,
        positionedCard: li,
      });
    }
  }

  cloneCard(e) {
    this.clonedDragCard = this.dragCard.cloneNode(true);
    closest('.column-list', e.target).appendChild(this.clonedDragCard);
    this.clonedDragCard.classList.add('start-drag');
    this.clonedDragCard.classList.replace('default', 'drag');
    this.dragCard.classList.replace('default', 'place');

    this.isClone = true;
  }
}
