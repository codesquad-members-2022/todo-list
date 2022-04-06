import { $ } from "../utils/utils.js";
import { toggleActivation } from "../store/model.js";
import { sidebarData } from "../../../../server/data/sidebar.js";

const getFinalConsonant = (object) => {
  const finalWord = object.length - 1;
  const fianlCharCode = object.charCodeAt(finalWord);
  const finalConsonantCode = (fianlCharCode - 44032) % 28;
  return finalConsonantCode;
};

const hasFinalConsonant = (object) => {
  const finalConsonantCode = getFinalConsonant(object);
  return finalConsonantCode !== 0;
};

const appendEulReul = (object) => {
  return hasFinalConsonant(object) ? "을" : "를";
};

const appenRoEro = (object) => {
  const rieul = 8;
  return hasFinalConsonant(object) //
    ? getFinalConsonant(object) === rieul
      ? "로"
      : "으로"
    : "로";
};

const calcTimeForToday = (writeTimeValue) => {
  const today = new Date();
  const writeTime = new Date(writeTimeValue);
  const timeDifference = today.getTime() - writeTime.getTime();

  const minuteDifference = Math.floor(timeDifference / 1000 / 60);
  if (minuteDifference < 1) return "방금전";
  else if (minuteDifference < 60) return `${minuteDifference}분전`;

  const HourDifference = Math.floor(minuteDifference / 60);
  if (HourDifference < 24) return `${HourDifference}시간전`;

  const DayDifference = Math.floor(HourDifference / 60 / 24);
  if (DayDifference < 365) return `${DayDifference}일전`;
};

const identifyCategory = (activity) => {
  switch (activity.action) {
    case "등록":
      return `${activity.category[0]}에 ${activity.title}${appendEulReul(activity.title)} 
      ${activity.action}하였습니다.`;
    case "이동":
      return `${activity.title}${appendEulReul(activity.title)} ${activity.category[0]}에서 
      ${activity.category[1]}${appenRoEro(activity.category[1])} ${activity.action}하였습니다.`;
    case "삭제":
      return `${activity.category}에서 ${activity.title}${appendEulReul(activity.title)} 
      ${activity.action}하였습니다.`;
    case "변경":
      return `${activity.category}에서 ${activity.title}이 ${action}되었습니다.`;
    default:
      return "동작이 잘못 되었습니다.";
  }
};

const createHTML = () => {
  const sidebarList = sidebarData.reduce(
    (acc, cur) =>
      acc +
      `<li class="sidebar__item">
          <img class="sidebar__item--profile" src="profile.svg" alt="profile" />
          <section>
            <h3 class="sidebar__item--nickname">@sam</h3>
            <p class="sidebar__item--activity">
               ${identifyCategory(cur)}
            </p>
            <p class="sidebar__item--time-stamp">${calcTimeForToday(cur.writeTime)}</p>
          </section>
        </li>`,
    ""
  );
  return `<ul class="sidebar__list">${sidebarList}</ul>`;
};

const render = (parent) => {
  parent.innerHTML = createHTML();
};

const toggleSidebar = () => {
  const sidebar = $("aside");
  const sidebarMenuBtn = $(".sidebar__menu-button img");
  sidebar.classList.toggle("show");
  sidebarMenuBtn.src = sidebar.classList.contains("show") ? "./icon-delete.svg" : "./icon-menu.svg";
};

const bindModel = () => {
  toggleActivation.toggleSidebar = toggleSidebar;
};

export const sidebarInit = (parent) => {
  render(parent);
  bindModel();
};
