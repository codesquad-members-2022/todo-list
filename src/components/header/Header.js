import "./Header.scss";

export const renderHeader = (parentEl) => {
  parentEl.insertAdjacentHTML("beforeend", getHeaderTemplate());
};

const getHeaderTemplate = () => {
  return `
    <header>
      <h1 class="header__title">TO-DO LIST</h1>
      <div class="header__menu-btn"></div>
    </header>
  `;
};
