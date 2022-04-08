export function createItem({ id, title, content }) {
  return `
  <li class="card" data-card="${id}">
    <div class="card__content">
      <input class="card__content--title" required readonly value="${title}" />
      <textarea class="card__content--text">${content}</textarea>
      <span class="card__content--author">author by web</span>
    </div>
    <img class="card--delete-card" src="./asset/icon/icon-card-delete-button-strong.svg" />
  </li>
  `;
}
