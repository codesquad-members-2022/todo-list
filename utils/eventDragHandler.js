import { $ } from '../utils/dom.js';
export const onBodyMouseMove = () => {
  document.body.addEventListener('mousemove', e => {
    const copyCardElement = $('.drag');
    if (copyCardElement) {
      Object.assign(copyCardElement.style, {
        left: `${e.clientX}px`,
        top: `${e.clientY}px`,
      });
      checkColumnArea(e.clientX);
    }
  });
};

export const onBodyMouseUp = () => {
  document.body.addEventListener('mouseup', e => {
    const copyCardElement = $('.drag');
    if (copyCardElement) {
      const dataId = copyCardElement.getAttribute('data-id');
      copyCardElement?.remove();
      const card = document.getElementById(`${dataId}`);
      card.classList.remove('spectrum');
    }
  });
};

const checkColumnArea = x => {
  // 복사된 카드가 각 영역 절반정도가 되는거 어떻게 체크?
  const dataId = $('.drag').getAttribute('data-id');
  const copyCardStatus = $('.drag').getAttribute('data-status');

  const columns = ['해야할일', '하고있는일', '완료한일'];
  const todoColumn = $(`.${columns[0]}-wrapper`);
  const ingColumn = $(`.${columns[1]}-wrapper`);
  const completeColumn = $(`.${columns[2]}-wrapper`);

  const card = document.getElementById(`${dataId}`);
  card.classList.remove('spectrum');

  /**
   * TODO 영역별로 이동경로 절반일 시 카드가 움직여야함
   */
  if (x >= todoColumn.getBoundingClientRect().left && x <= todoColumn.getBoundingClientRect().right) {
    todoColumn.appendChild(card);
    return;
  }

  if (x >= ingColumn.getBoundingClientRect().left && x <= ingColumn.getBoundingClientRect().right) {
    ingColumn.appendChild(card);
    return;
  }

  if (x >= completeColumn.getBoundingClientRect().left && x <= completeColumn.getBoundingClientRect().right) {
    completeColumn.appendChild(card);
    return;
  }
  // if (x >= ingColumn.getBoundingClientRect().left + 154 && x <=) {
  //   return;
  // }

  // columnAppendChildCard(x, todoColumn, card);
  // columnAppendChildCard(x, ingColumn, card);
  // columnAppendChildCard(x, completeColumn, card);
};

const columnAppendChildCard = (x, column, card) => {
  const columnWidth = 154;
  console.log(column.getBoundingClientRect().left, column.getBoundingClientRect().right);
  if (x >= column.getBoundingClientRect().left && x <= column.getBoundingClientRect().right) {
    column.appendChild(card);
  }
};

const checkSiblingsCard = () => {};
