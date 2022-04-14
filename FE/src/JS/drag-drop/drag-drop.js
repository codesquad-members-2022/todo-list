import { $, $$, throttle } from '../utility/util.js';

export default class Drag {
  removeDragEvent() {
    const cardLists = $$('.column-item--card');
    const containers = $$('.container');

    cardLists.forEach((cardList) => {
      cardList.removeEventListener(
        'dragstart',
        this.addDraggingClass(cardList)
      );

      cardList.removeEventListener(
        'dragend',
        this.removeDraggingClass(cardList)
      );
    });

    const MIN_TIME = 500;

    containers.forEach((container) => {
      container.removeEventListener(
        'dragover',
        throttle((e) => {
          this.dragoverEventHandler(e, container);
        }, MIN_TIME)
      );
    });
  }

  addDragEvent() {
    const cardLists = $$('.column-item--card');
    const containers = $$('.container');

    cardLists.forEach((cardList) => {
      cardList.addEventListener('dragstart', this.addDraggingClass(cardList));

      cardList.addEventListener('dragend', this.removeDraggingClass(cardList));
    });

    const MIN_TIME = 500;

    containers.forEach((container) => {
      container.addEventListener(
        'dragover',
        throttle((e) => {
          this.dragoverEventHandler(e, container);
        }, MIN_TIME)
      );
    });
  }

  //이벤트 재등록시 toggle이 동작하지 않아 현재 사용X
  toggleDraggingClass(cardList) {
    return () => {
      cardList.classList.toggle('dragging');
    };
  }

  addDraggingClass(cardList) {
    return () => {
      cardList.classList.add('dragging');
    };
  }

  removeDraggingClass(cardList) {
    return () => {
      cardList.classList.remove('dragging');
    };
  }

  dragoverEventHandler(e, container) {
    e.preventDefault();
    const selectedCard = $('.dragging');
    const targetElement = this.getDragTargetElement(container, e.clientY);

    this.changeCurCard(targetElement, container, selectedCard);
  }

  getDragTargetElement(container, y) {
    const unselectedCardEl = [
      ...container.querySelectorAll('.column-item--card:not(.dragging)'),
    ];

    return unselectedCardEl.reduce(
      (closest, child) => {
        const box = child.getBoundingClientRect();
        const offset = y - box.top - box.height / 2;

        return this.getCurLocation(offset, closest, child);
      },
      { offset: Number.NEGATIVE_INFINITY }
    ).element;
  }

  getCurLocation(offset, closest, child) {
    const curLocation =
      offset < 0 && offset > closest.offset
        ? { offset: offset, element: child }
        : closest;

    return curLocation;
  }

  changeCurCard(afterElement, container, selectedCard) {
    if (!selectedCard) return;

    if (!afterElement) {
      container.appendChild(selectedCard);
    } else {
      container.insertBefore(selectedCard, afterElement);
    }

    //서버에게 바뀐 카드 알려주기
    this.changeDraggingCard();
  }
}
