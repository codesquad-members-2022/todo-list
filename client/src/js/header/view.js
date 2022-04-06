import { $ } from "../utils/utils.js";
import { toggleActivation } from "../store/model.js";

const createHTML = () => {
  return `<h1 class="header__title">TO-DO LIST</h1>
      <button class="sidebar__menu-button">
        <img src="./icon-menu.svg" alt="icon-delete" />
      </button>`;
};

const render = (parent) => {
  parent.innerHTML = createHTML();
};

const handleSideBarMenuBtn = () => {
  toggleActivation().set();
};

const setEvents = () => {
  const sidebarMenuBtn = $(".sidebar__menu-button");
  sidebarMenuBtn.addEventListener("click", handleSideBarMenuBtn);
};

export const headInit = (parent) => {
  render(parent);
  setEvents();
};
