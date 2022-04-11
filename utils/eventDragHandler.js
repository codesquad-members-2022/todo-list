export const onBodyMouseMove = () => {
  document.body.addEventListener('mousemove', e => {
    const copyCardElement = document.querySelector('.drag');
    if (copyCardElement) {
      copyCardElement.style.left = `${e.pageX}px`;
      copyCardElement.style.top = `${e.pageY}px`;
      const dataId = copyCardElement.getAttribute('data-id');
      checkColumnArea(e.pageX, dataId);
    }
  });
};

export const onBodyMouseUp = () => {
  document.body.addEventListener('mouseup', e => {
    const copyCardElement = document.querySelector('.drag');
    if (copyCardElement) {
      const dataId = copyCardElement.getAttribute('data-id');
      copyCardElement?.remove();
      const card = document.getElementById(`${dataId}`);
      card.classList.remove('spectrum');
    }
  });
};

export const checkColumnArea = (x, id) => {
  const columns = ['todo', 'ing', 'complete'];
  const todoColumn = document.querySelector(`.${columns[0]}-wrapper`);
  const ingColumn = document.querySelector(`.${columns[1]}-wrapper`);
  const completeColumn = document.querySelector(`.${columns[2]}-wrapper`);

  const card = document.getElementById(`${id}`);
  card.classList.remove('spectrum');
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
};
