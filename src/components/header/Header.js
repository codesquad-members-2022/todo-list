import "./Header.scss";
import { initMenu } from "./menu/Menu";

const renderHeader = () => {
  document.body.insertAdjacentHTML("beforeend", getHeaderTemplate());
};

const getHeaderTemplate = () => {
  return `
    <header>
      <h1 class="header__title">TO-DO LIST</h1>
      <div class="header__menu-btn"></div>
    </header>
  `;
};

export const initHeader = () => {
  renderHeader();
  initMenu();
};
