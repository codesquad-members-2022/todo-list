export function makeCategory(categoryName) {
  return `
  <div id=${categoryName} class="column-item">
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

export function makeCard({ userId }, { workId, title, content }) {
  return `
  <li id="${workId}" class="column-item--card" draggable="true">
    <div class="card-header">
      <h3 class="card-title">${title}</h3>
      <input type="button" class="delete-btn" value="del" />
    </div>
    <span class="card-content">${content}</span>
    <span class="card-author">Author by ${userId}</span>
  </li>`;
}

export function addCardTemplate() {
  return `
  <li class="column-item--card column-addBtn">
    <div class="card-header add-card--header">
      <input class="card-title" type="text" placeholder="제목을 입력하세요." maxlength="14"/>
      <div class="card-content" contenteditable="true" placeholder="내용을 입력하세요.(500자 제한)"></div>
      </div>
    <div>
      <button class="card-addBtn cancelBtn">취소</button>
      <button class="card-addBtn registerBtn">등록</button>
    </div>
  </li>`;
}
