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
    this.target.addEventListener('mousedown', (e) =>
      this.mousedownEventHandler(e)
    );
    this.target.addEventListener('mouseup', (e) => this.mouseupEventHandler());
    this.target.addEventListener('mousemove', (e) =>
      this.mousemoveEventHandler(e)
    );
  }

  mousedownEventHandler(e) {
    if (closest('.default', e.target)) {
      this.isDragging = true;
      this.dragCard = closest('.default', e.target);
      this.column = closest('.column-list', e.target);
    }
  }

  mouseupEventHandler() {
    this.isDragging = false;
    this.moveCardState = false;
    if (this.isClone) {
      this.clonedDragCard.remove();
      this.dragCard.classList.replace('place', 'default');
      this.isClone = null;
      this.cardPositionInfo = [];
    }
  }

  mousemoveEventHandler(e) {
    if (!this.isDragging) return;

    if (!this.isClone) {
      this.setCardsPosition();
      this.cloneCard(e);
    }

    const { clientX: mouseX, clientY: mouseY } = e;
    this.clonedDragCard.style.left = mouseX + 'px';
    this.clonedDragCard.style.top = mouseY + 'px';

    const cloneCardPosData = this.clonedDragCard.getBoundingClientRect();
    const columnPosData = this.column.getBoundingClientRect();

    const { x: draggedX, y: draggedY } = cloneCardPosData;

    const moveToLeftColumn =
      columnPosData.x - cloneCardPosData.x > cloneCardPosData.width / 2;
    const moveToRightColumn =
      columnPosData.x + columnPosData.width - cloneCardPosData.x <
      cloneCardPosData.width / 2;

    if (moveToLeftColumn) {
      $(
        '.column-list',
        this.column.parentElement.previousElementSibling
      ).appendChild(this.dragCard);
    } else if (moveToRightColumn) {
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

      const isSameColumn =
        this.dragCard.getBoundingClientRect().x === el.x && !this.moveCardState;

      // 카드가 다른 카드의 측면에서 절반을 넘었는지 판단
      const overHalfSide =
        prevCard.x + prevCard.width - curCard.x > prevCard.width / 2;
      //
      const overHalfTop =
        cloneCardPosData.y < el.y &&
        higherCard.y + higherCard.height - lowerCard.y > lowerCard.height / 2;

      const overHalfBottom =
        cloneCardPosData.y > el.y &&
        higherCard.y + higherCard.height - lowerCard.y > higherCard.height / 2;

      if (isSameColumn) {
        if (overHalfSide) {
          if (overHalfTop) {
            el.positionedCard.insertAdjacentElement('afterend', this.dragCard);
          }

          if (overHalfBottom) {
            el.positionedCard.insertAdjacentElement(
              'beforebegin',
              this.dragCard
            );
          }
        }
      } else {
        this.moveCardState = true;

        if (overHalfSide) {
          if (overHalfTop) {
            el.positionedCard.insertAdjacentElement(
              'beforebegin',
              this.dragCard
            );
          }

          if (overHalfBottom) {
            el.positionedCard.insertAdjacentElement('afterend', this.dragCard);
          }
        }
      }
    });
  }

  setCardsPosition() {
    for (const li of $$('.list_item')) {
      const { x, y, width, height } = li.getBoundingClientRect();
      this.cardPositionInfo.push({
        x,
        y,
        width,
        height,
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
