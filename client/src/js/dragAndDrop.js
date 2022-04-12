import { closest } from './util';

export class DragAndDrop {
  constructor() {
    this.target = document;

    this.isDragging = null;
    this.isClone = false;

    this.dragCard = null;
    this.clonedDragCard = null;
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
    }
  };

  mouseupEventHandler = (e) => {
    this.isDragging = false;
    if (this.isClone) {
      this.clonedDragCard.remove();
      this.dragCard.classList.replace('place', 'default');
      this.isClone = null;
    }
  };

  mousemoveEventHandler = (e) => {
    if (this.isDragging) {
      if (!this.isClone) {
        this.clonedDragCard = this.dragCard.cloneNode(true);
        closest('.column-list', e.target).appendChild(this.clonedDragCard);
        this.clonedDragCard.classList.add('start-drag');
        this.dragCard.classList.replace('default', 'place');
        this.clonedDragCard.classList.replace('default', 'drag');
        this.isClone = true;
      }

      const mouseX = e.clientX;
      const mouseY = e.clientY;
      this.clonedDragCard.style.left = mouseX + 'px';
      this.clonedDragCard.style.top = mouseY + 'px';
    }
  };
}
