import { btnPlus, btnX } from "../../utils/assetURL";

export function createColumn({ id, title, length }) {
  return `
    <li class="column" data-column="${id}">
      <div class="column__header">
        <div class="column__header__content">
          <h2 class="column__header--title">${title}</h2>
          <div class="column__header--card-count">${length}</div>
        </div>
        <div class="column__header__btn">
          <img class="column__header--add-card" src=${btnPlus} />
          <img class="column__header--delete-card" src=${btnX} />
        </div>
      </div>
      <ul class="card-list"></ul>
    </li>
  `;
}
