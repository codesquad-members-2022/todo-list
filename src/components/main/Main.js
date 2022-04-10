import { renderColumnContainer } from "./column-container/ColumnContainer.js";

export const renderMain = (parentEl) => {
  parentEl.insertAdjacentHTML("beforeend", getMainTemplate());
  const mainDOM = parentEl.querySelector(".main");
  mountColumnContainer(mainDOM);
};

const getMainTemplate = () => {
  return `<div class='main'></div>`;
};

const mountColumnContainer = (parentEl) => {
  renderColumnContainer(parentEl);
};
