import { $ } from '../utils/dom.js';
import { editLocalStorageById } from '../utils/localStorage.js';
import { createNotice, handleNotice } from './action.js';

const dragElement = $('.drag');
let copyElement = null; // 마우스를 따라다니는 복사된 카드
let shadowElement = null; // 잔상카드
let firstStatus = null;
let status = null;

const setInitValues = () => {
  copyElement = null;
  shadowElement = null;
  firstStatus = null;
  status = null;
};

const isBefore = (element1, element2) => {
  if (element2.parentNode === element1.parentNode) {
    let cur = element1.previousSibling;
    while (cur) {
      if (cur === element2) return true;
      cur = cur.previousSibling;
    }
  }

  return false;
};

export const onBodyMouseDown = e => {
  document.body.addEventListener('mousedown', e => {
    const targetRemove = e.target.closest("[data-drag='true']");
    if (!targetRemove) return;

    firstStatus = targetRemove.parentNode.getAttribute('data-status');
    status = targetRemove.parentNode.getAttribute('data-status');

    if (e.target.className === 'card__delete' || targetRemove === null || targetRemove.className === 'start') {
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
  document.body.addEventListener('mouseup', e => {
    if (shadowElement) {
      shadowElement.classList.remove('spectrum');
    }

    if (e.target.className !== 'card' || status === firstStatus) {
      copyElement?.remove();
      setInitValues();
      return;
    }

    if (copyElement) {
      // 카드 이동 이후 로컬스토리지 해당 id 업데이트
      const id = Number(copyElement.id);
      const todo = {
        id,
        status,
      };
      editLocalStorageById('todos', todo, id);
      copyElement?.remove();

      const [title, content] = getCardData(copyElement);
      const notice = {
        title,
        status,
        content,
        firstStatus,
      };

      const newNotice = createNotice(notice, '이동');
      handleNotice(newNotice);
      setInitValues();
    }
  });
};

export const onContextMenu = () => {
  document.body.addEventListener('contextmenu', e => {
    if (e.target.className === 'card') {
      e.preventDefault();
      return;
    }
  });
};

const getCardData = () => {
  const title = copyElement.querySelector('.card__title').innerText.trim();
  const content = copyElement.querySelector('.card__content').innerText.trim();
  return [title, content];
};
