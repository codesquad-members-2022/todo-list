import "./Menu.scss";
import { hasClassName } from "@/common/util";

export const initMenu = () => {
  renderMenu();
  setMenuBtnEvent();
  setLogCloseBtnEvent();
};

const renderMenu = async () => {
  const header = document.querySelector("header");
  header.insertAdjacentHTML("beforeend", getMenuTemplate());
};

const getMenuTemplate = () => {
  return `
<div class="log-container">
  <div class="log__close-btn"></div>
  <ul class="log-list"></ul>
</div>
  `;
};

/////////

const setMenuBtnEvent = () => {
  const menuBtn = document.querySelector(".header__menu-btn");
  menuBtn.addEventListener("click", menuBtnHandler);
};

const menuBtnHandler = () => {
  toggleLogContainerDisplay();
  renderLogContainer();
};

const getLogData = async () => {
  const logs = await fetch("http://localhost:30000/log", {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  });
  const json = await logs.json();

  return json;
};

const getLogTemplate = (log) => {
  return `
<li class="log">
  <div class="log__emoji">🦖</div>
  <div class="log-contents">
    <div class="log-contents__user">${log.userId}</div>
    <div class="log-contents__description">${log.log}</div>
    <div class="log-contents__time">${getTimeDiffrence(log.createdAt)}</div>
  </div>
</li>`;
};

const getDiffrentMinutesWithJsonDate = (jsonDate) => {
  const now = new Date();
  const convertedDate = new Date(jsonDate);
  const differentMinutes = (now.getTime() - convertedDate.getTime()) / (1000 * 60);

  return differentMinutes >> 0;
};

const getTrimmedMessage = (minutes) => {
  if (minutes < 1) return "1분 전";
  if (minutes < 60) return `${minutes}분 전`;
  if (minutes < 1440) return `${(minutes / 60) >> 0}시간 전`;
  if (minutes >= 1440) return `${(minutes / 1440) >> 0}일 전`;
};

const getTimeDiffrence = (jsonDate) => {
  return getTrimmedMessage(getDiffrentMinutesWithJsonDate(jsonDate));
};

export const renderLogContainer = async () => {
  const logdata = await getLogData();
  const logUl = document.querySelector(".log-list");
  logUl.innerHTML = logdata.reduce((acc, cur) => (acc += getLogTemplate(cur)), "");
};

const toggleLogContainerDisplay = () => {
  const logContainer = document.querySelector(".log-container");
  logContainer.classList.toggle("is-opened");

  const modal = document.querySelector(".modal");
  modal.classList.toggle("log-opened");
};

const setLogCloseBtnEvent = () => {
  const logCloseBtn = document.querySelector(".log__close-btn");
  logCloseBtn.addEventListener("click", logCloseBtnHandler);

  document.body.addEventListener("click", (e) => otherClickHandler(e));
};

const logCloseBtnHandler = () => {
  toggleLogContainerDisplay();
};

const otherClickHandler = (e) => {
  if (hasClassName(e.target, "modal")) {
    toggleLogContainerDisplay();
  }
};
