import { $, $$, delay, reflectStore, rerender } from '../utils/util';

export const onDragEvent = store => {
  const mainElement = $('.main');
  let dragFlag = false;
  mainElement.addEventListener('mousedown', async e => {
    const target = e.target;
    const cardDeleteBtn = target.closest('.card__delete-btn');
    const cardElement = target.closest('.task__card:not(.new-card)');
    if (cardDeleteBtn || !cardElement) {
      return;
    }
    e.preventDefault();
    dragFlag = false;
    await delay(150);
    if (dragFlag) {
      return;
    }
    dragFlag = true;
    cardElement.classList.add('dragOriginal');
  });
  mainElement.addEventListener('dblclick', e => {
    e.preventDefault();
    const target = e.target;
    const cardElement = target.closest('.task__card:not(.new-card)');
    if (!cardElement) {
      return;
    }
    if (!dragFlag) {
      dragFlag = true;
      return;
    }
  });

  mainElement.addEventListener('mousemove', e => {
    e.preventDefault();
    const dragOriginal = $('.dragOriginal');
    if (!dragOriginal) {
      return;
    }
    const dragging = $('.dragging') || dragOriginal.cloneNode(true);
    if (![...dragging.classList].includes('dragging')) {
      dragging.classList.add('dragging');
      dragging.classList.remove('dragOriginal');
      const mainElement = $('.main');
      mainElement.appendChild(dragging);
    }
    decidePositionDraggingElement(e, dragging);
    const closestColumn = getClosestColumn(e, mainElement);
    const prevCardElement = getClosestCard(e, closestColumn);
    dragOriginal.remove();
    if (prevCardElement) {
      const parentElement = prevCardElement.parentElement;
      parentElement.insertBefore(dragOriginal, prevCardElement);
    } else {
      closestColumn.appendChild(dragOriginal);
    }
  });

  mainElement.addEventListener('mouseup', e => {
    finishDragEvents(e);
  });

  const finishDragEvents = e => {
    e.preventDefault();
    if (!dragFlag) {
      dragFlag = true;
      return;
    }
    const target = e.target;
    const dragOriginal = $('.dragOriginal');
    const dragging = target.closest('.dragging');
    if (!dragging) {
      return;
    }
    dragOriginal.classList.remove('dragOriginal');
    dragging.remove();
    reflectStore(store);
    rerender(store, 'main');
  };
};

const decidePositionDraggingElement = (e, dragging) => {
  const dragRectInfo = dragging.getBoundingClientRect();
  const xPos = e.clientX - dragRectInfo.width / 2;
  const yPos = e.clientY - dragRectInfo.height / 2;
  dragging.style.top = yPos + 'px';
  dragging.style.left = xPos + 'px';
};

const getClosestColumn = (e, mainElement) => {
  const columns = [...$$('.column', mainElement)];
  return columns.reduce(
    (closest, columnElement) => {
      const columnRectInfo = columnElement.getBoundingClientRect();
      const diff = e.clientX - (columnRectInfo.left + columnRectInfo.width / 2);
      if (diff < 0 && diff > closest.diff) {
        return { diff, element: columnElement };
      }
      if (diff > 0 && diff < closest.diff) {
        return { diff, element: columnElement };
      }
      return closest;
    },
    { diff: -Infinity }
  ).element;
};

const getClosestCard = (e, columnElement) => {
  const cardElements = columnElement
    ? [...$$('.task__card:not(.dragOriginal):not(.new-card)', columnElement)]
    : undefined;
  const closestCard = cardElements.reduce(
    (closest, cardElement) => {
      const cardRectInfo = cardElement.getBoundingClientRect();
      const diff = e.clientY - (cardRectInfo.top + cardRectInfo.height / 2);
      if (diff < 0 && diff > closest.diff) {
        return { diff, element: cardElement };
      }
      if (diff > 0 && diff < closest.diff) {
        return { diff, element: cardElement };
      }
      return closest;
    },
    { diff: -Infinity }
  );
  if (closestCard.diff <= 0) {
    return closestCard.element;
  }
  if (closestCard.element.nextsibling) {
    return closestCard.element.nextsibling;
  }
};
