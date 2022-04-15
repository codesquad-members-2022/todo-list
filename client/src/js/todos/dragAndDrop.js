import {
  $,
  $$,
  closest,
  getURL,
  requestToServer,
  getSequenceData,
} from '../util.js';

export class DragAndDrop {
  constructor() {
    this.target = document;

    this.isDragging = null;
    this.isClone = false;

    this.dragCard = null;
    this.clonedDragCard = null;
    this.cardPositionInfo = [];
    this.moveCardState = false;
    this.movedDragCard = null;
    this.columnName = null;
  }

  init() {
    this.target.addEventListener('mousedown', (e) =>
      this.mousedownEventHandler(e)
    );
    this.target.addEventListener('mouseup', () => this.mouseupEventHandler());
    this.target.addEventListener('mousemove', (e) =>
      this.mousemoveEventHandler(e)
    );
  }

  mousedownEventHandler(e) {
    if (closest('.default', e.target)) {
      this.isDragging = true;
      this.dragCard = closest('.default', e.target);
      this.columnList = closest('.column-list', e.target);
      this.columnName = $(
        '.column-title',
        closest('.column', e.target)
      ).textContent;
    }
  }

  mouseupEventHandler() {
    this.isDragging = false;
    this.moveCardState = false;
    if (this.isClone) {
      const columnName = $(
        '.column-title',
        closest('.column', this.movedDragCard)
      ).textContent;
      this.updateCardState(columnName);
      this.updateSequence(columnName);
      this.deleteSequence();

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
    const columnPosData = this.columnList.getBoundingClientRect();

    const { x: draggedX, y: draggedY, width: draggedWidth } = cloneCardPosData;

    const moveToLeftColumn = columnPosData.x - draggedX > draggedWidth / 2;
    const moveToRightColumn =
      columnPosData.x + columnPosData.width - draggedX < draggedWidth / 2;

    if (moveToLeftColumn) {
      const leftColumn = this.columnList.parentElement.previousElementSibling;
      $('.column-list', leftColumn).appendChild(this.dragCard);
      this.movedDragCard = $('.column-list', leftColumn).firstElementChild;

      if (!$('.column-list', leftColumn).children.length) {
        return;
      }
    } else if (moveToRightColumn) {
      const rightColumn = this.columnList.parentElement.nextElementSibling;
      $('.column-list', rightColumn).appendChild(this.dragCard);
      this.movedDragCard = $('.column-list', rightColumn).firstElementChild;

      if (!$('.column-list', rightColumn).children.length) {
        return;
      }
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
        this.movedDragCard = el.positionedCard;
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

        this.movedDragCard = el.positionedCard;
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

  async deleteSequence() {
    const sequence = await getSequenceData(this.columnName);
    const patchData = {};
    patchData[this.columnName] = sequence.filter(
      (el) => el !== Number(this.dragCard.dataset.id)
    );

    const url = getURL('cardSequence');
    requestToServer(url, 'PATCH', patchData);
  }

  async updateSequence(columnName) {
    const columnList = closest('.column', this.movedDragCard).lastElementChild;

    const patchData = {};
    patchData[columnName] = Array.prototype.slice
      .call($$('.list_item', columnList))
      .map((el) => Number(el.dataset.id));

    const url = getURL('cardSequence');
    requestToServer(url, 'PATCH', patchData);
  }

  async updateCardState(columnName) {
    const url = getURL(`cards/${this.dragCard.dataset.id}`);
    const data = {
      states: columnName,
      lastTime: new Date(),
    };

    requestToServer(url, 'PATCH', data);
  }
}
