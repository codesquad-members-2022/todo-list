import { $ } from '../utils/dom.js';
export const onBodyMouseMove = () => {
  document.body.addEventListener('mousemove', e => {
    const copyCardElement = $('.drag');
    if (copyCardElement) {
      Object.assign(copyCardElement.style, {
        left: `${e.pageX}px`,
        top: `${e.pageY}px`,
      });
      const dataId = copyCardElement.getAttribute('data-id');
      checkColumnArea(e.pageX, dataId);
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

const checkColumnArea = (x, id) => {
  const columns = ['todo', 'ing', 'complete'];
  const todoColumn = $(`.${columns[0]}-wrapper`);
  const ingColumn = $(`.${columns[1]}-wrapper`);
  const completeColumn = $(`.${columns[2]}-wrapper`);

  const card = document.getElementById(`${id}`);
  card.classList.remove('spectrum');

  columnAppendChildCard(x, todoColumn, card);
  columnAppendChildCard(x, ingColumn, card);
  columnAppendChildCard(x, completeColumn, card);
};

const columnAppendChildCard = (x, column, card) => {
  if (x >= column.getBoundingClientRect().left && x <= column.getBoundingClientRect().right) column.appendChild(card);
};
