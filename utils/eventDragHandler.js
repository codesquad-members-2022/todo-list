import { $ } from '../utils/dom.js';
import { editLocalStorageById } from '../utils/localStorage.js';

const dragElement = $('.drag');
let copyElement = null; // 마우스를 따라다니는 복사된 카드
let shadowElement = null; // 잔상카드
let status = null;

function isBefore(element1, element2) {
  if (element2.parentNode === element1.parentNode) {
    let cur = element1.previousSibling;
    while (cur) {
      if (cur === element2) return true;
      cur = cur.previousSibling;
    }
  }

  return false;
}

export const onBodyMouseDown = () => {
  document.body.addEventListener('mousedown', e => {
    const targetRemove = e.target.closest('.card');

    if (targetRemove === null || targetRemove.className === 'start') {
      return;
    }

    shadowElement = targetRemove;
    copyElement = targetRemove.cloneNode(true);
    shadowElement.classList.add('spectrum');

    dragElement.appendChild(copyElement);

    dragElement.style.left = e.pageX - dragElement.offsetWidth / 2 + 'px';
    dragElement.style.top = e.pageY - dragElement.offsetHeight / 2 + 'px';
  });
};

export const onBodyMouseMove = () => {
  document.body.addEventListener('mousemove', e => {
    if (!copyElement) return;

    const { pageX, pageY } = e;
    dragElement.hidden = true;
    const elemBelow = document.elementFromPoint(pageX, pageY);
    const card = elemBelow.closest('.card');
    const columnList = elemBelow.closest('.column-list');

    dragElement.hidden = false;
    dragElement.style.left = pageX - dragElement.offsetWidth / 2 + 'px';
    dragElement.style.top = pageY - dragElement.offsetHeight / 2 + 'px';

    if (columnList) {
      status = columnList.getAttribute('data-status');
    } else return;

    if (!card) {
      columnList.appendChild(shadowElement);
      return;
    }

    if (isBefore(shadowElement, card) && card.className !== 'start') {
      card.parentNode.insertBefore(shadowElement, card);
    } else if (card.parentNode) {
      card.parentNode.insertBefore(shadowElement, card.nextSibling);
    }
  });
};

export const onBodyMouseUp = () => {
  document.body.addEventListener('mouseup', () => {
    // 카드 status Update, 로컬스토리지 해당 id 업데이트

    if (shadowElement) {
      shadowElement.classList.remove('spectrum');
    }

    if (copyElement) {
      const id = Number(copyElement.id);
      const todo = {};
      todo.id = id;
      todo.status = status;
      editLocalStorageById(todo, id);
      copyElement?.remove();
    }

    copyElement = null;
    shadowElement = null;
  });
};
