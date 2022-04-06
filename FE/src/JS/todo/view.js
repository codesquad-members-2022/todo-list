import { $ } from '../utility/util.js';

export default class View {
  constructor() {}

  renderTodoItem(categoryName, works, userId) {
    const columnList = $('.column-list');
    const container = $('container');
  }

  makeCategory(categoryName) {
    return `<div class="column-item">
<div class="column-item--header">
  <h2>
    <span>${categoryName}</span>
    <span class="itemCount">1</span>
  </h2>
  <div class="column-item--buttons">
    <input type="button" class="addBtn" value="add" />
    <input type="button" class="deleteBtn" value="del" />
  </div>
</div>
<ul class="container"></ul>
</div>`;
  }

  makeCard({ title, content }, userId) {
    return `<li class="column-item--card" draggable="true">
    <div class="card-header">
      <h3>${title}</h3>
      <input type="button" class="delete-btn" value="del" />
    </div>
    <span class="card-content">${content}</span>
    <span class="card-author">Author by ${userId}</span>
  </li>`;
  }
}
