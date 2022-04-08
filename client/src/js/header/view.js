import { $, debounce } from "../utils/utils.js";
import { iconMenu, iconDelete } from "../constants/imagePath.js";
import * as ActivationStore from "../store/activationStore.js";

const createHTML = () => {
  return `<h1 class="header__title">TO-DO LIST</h1>
      <button class="sidebar__menu-button">
        <img src="${iconMenu}" alt="icon-menu" />
      </button>`;
};

const render = (parent) => {
  parent.innerHTML = createHTML();
};

const handleSideBarMenuBtn = () => {
  ActivationStore.update("sidebar");
};

const setEvents = () => {
  const sidebarMenuBtn = $(".sidebar__menu-button");
  sidebarMenuBtn.addEventListener(
    "click",
    debounce(() => handleSideBarMenuBtn(), 250)
  );
};

const notify = (isShow) => {
  const sidebarMenuBtn = $(".sidebar__menu-button img");
  sidebarMenuBtn.src = isShow ? iconDelete : iconMenu;
};

export const headInit = (parent) => {
  render(parent);
  setEvents();
  ActivationStore.subscribe("sidebar", notify);
};
