export function createColumn({ id, title }) {
  return `
    <li class="column" data-column="${id}">
      <div class="column__header">
        <div class="column__header__content">
          <h2 class="column__header--title">${title}</h2>
          <div class="column__header--card-count">${2}</div>
        </div>
        <div class="column__header__btn">
          <img class="column__header--add-card" src="./asset/icon/icon-card-add-button.svg" />
          <img class="column__header--delete-card" src="./asset/icon/icon-card-delete-button.svg" />
        </div>
      </div>
      <ul class="card-list"></ul>
    </li>
  `;
}
