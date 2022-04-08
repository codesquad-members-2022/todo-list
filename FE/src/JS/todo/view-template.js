export function makeCategory(categoryName) {
  return `<div id=${categoryName} class="column-item">
<div class="column-item--header">
<h2>
  <span>${categoryName}</span>
  <span class="itemCount itemCount${categoryName}">0</span>
</h2>
<div class="column-item--buttons">
  <input type="button" class="addBtn" value="add" />
  <input type="button" class="deleteBtn" value="del" />
</div>
</div>
<ul class="container container${categoryName}"></ul>
</div>`;
}

export function makeCard(todoInfo, data) {
  return `<li class="column-item--card" draggable="true">
  <div class="card-header">
    <h3>${data.title}</h3>
    <input type="button" class="delete-btn" value="del" />
  </div>
  <span class="card-content">${data.content}</span>
  <span class="card-author">Author by ${todoInfo.id}</span>
</li>`;
}
