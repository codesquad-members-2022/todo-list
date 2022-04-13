import { iconHistoryProfileImg } from "../../utils/assetURL.js";
import { formatRelativeDate } from "../../utils/helpers.js";

export function createHistory({ username, date, content }) {
  return `
    <li class="history">
      <img class="history--profile-img" src=${iconHistoryProfileImg} alt="">
      <div class="history__item">
        <h3 class="history__item--name">@${username}</h3>
        <p class="history__item--activity">${content}</p>
        <p class="history__item--time-record">${formatRelativeDate(date)}</p>
      </div>
    </li>
  `;
}
