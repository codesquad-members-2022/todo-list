import { $, $$, throttle } from '../utility/util.js';

export default class Drag {

  addDragEvent() {
    const cardLists = $$('.column-item--card');
    const containers = $$('.container');

    cardLists.forEach((cardList) => {
      cardList.addEventListener(
        'dragstart',
        this.toggleDraggingClass(cardList)
      );

      cardList.addEventListener('dragend', this.toggleDraggingClass(cardList));
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

  toggleDraggingClass(cardList) {
    return () => {
      cardList.classList.toggle('dragging');
    };
  }

  dragoverEventHandler(e, container) {
    e.preventDefault();
    const selectedCard = $('.dragging');
    const targetElement = this.getDragtargetElement(container, e.clientY);

    this.changeCurCard(targetElement, container, selectedCard);
  }

  getDragtargetElement(container, y) {
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
  }
}
