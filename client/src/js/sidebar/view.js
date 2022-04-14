import { $ } from "../utils/utils.js";
import { sidebarData } from "../../../../server/data/sidebar.js";
import { profileImage } from "../constants/imagePath.js";
import * as TodoListStore from "../store/todoListStore.js";

const getFinalConsonant = (object) => {
  const koreanUnicode = 44032;
  const finalConsonantCount = 28;
  const finalWord = object.length - 1;
  const finalCharCode = object.charCodeAt(finalWord);
  const finalConsonantCode = (finalCharCode - koreanUnicode) % finalConsonantCount;
  return finalConsonantCode;
};

const hasFinalConsonant = (object) => {
  const finalConsonantCode = getFinalConsonant(object);
  return finalConsonantCode !== 0;
};

const appendEulReul = (object) => {
  return hasFinalConsonant(object) ? "을" : "를";
};

const appendRoEro = (object) => {
  const rieul = 8;
  return hasFinalConsonant(object) //
    ? getFinalConsonant(object) === rieul
      ? "로"
      : "으로"
    : "로";
};

const calcTimeForToday = (timeStampValue) => {
  const today = new Date();
  const timeStamp = new Date(timeStampValue);
  const timeDifference = today.getTime() - timeStamp.getTime();
  const [milliSecond, second, minute, hour, day, year] = [1000, 1, 60, 60, 24, 365];

  const minuteDifference = Math.floor(timeDifference / milliSecond / minute);
  if (minuteDifference < second) return "방금전";
  if (minuteDifference < hour) return `${minuteDifference}분전`;

  const HourDifference = Math.floor(minuteDifference / hour);
  if (HourDifference < day) return `${HourDifference}시간전`;

  const DayDifference = Math.floor(HourDifference / day);
  if (DayDifference < year) return `${DayDifference}일전`;
  return `${DayDifference}년전`;
};

const identifyCategory = (activity) => {
  const { action, category, title } = activity;
  switch (action) {
    case "등록":
      return `${category[0]}에 ${title}${appendEulReul(title)} 
      ${action}하였습니다.`;
    case "이동":
      return `${title}${appendEulReul(title)} ${category[0]}에서 
      ${category[1]}${appendRoEro(category[1])} ${action}하였습니다.`;
    case "삭제":
      return `${category}에서 ${title}${appendEulReul(title)} 
      ${action}하였습니다.`;
    case "변경":
      return `${category}에서 ${title}이 ${action}되었습니다.`;
    default:
      return "동작이 잘못 되었습니다.";
  }
};

const createHTML = () => {
  const sidebarList = sidebarData.reduce(
    (acc, cur) =>
      acc +
      `<li class="sidebar__item">
          <img class="sidebar__item--profile" src="${profileImage}" alt="profile" />
          <section>
            <h3 class="sidebar__item--nickname">@sam</h3>
            <p class="sidebar__item--activity">
               ${identifyCategory(cur)}
            </p>
            <p class="sidebar__item--time-stamp">${calcTimeForToday(cur.timeStamp)}</p>
          </section>
        </li>`,
    ""
  );
  return `<ul class="sidebar__list"><div class="sidebar__scroll">${sidebarList}</div></ul>`;
};

const render = (parent) => {
  parent.innerHTML = createHTML();
};

const notify = () => {
  const sidebar = $("aside");
  sidebar.classList.toggle("show");
};

const sidebarInit = (parent) => {
  render(parent);
  TodoListStore.subscribe("sidebar", notify);
};

export { sidebarInit };
