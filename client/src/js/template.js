const createColumnTemplate = (title) => {
  return ` 
  <div class="column ${title}">
    <div class="column-header">
      <div>
        <h2 class="column-title">${title}</h2>
        <span class="item-count">0</span>
      </div>
      <div>
        <button class="plus-btn"><span class="icon-plus"></span></button>
        <button class="close-btn"><span class="icon-close"></span></button>
      </div>
    </div>
    <ul class="column-list"></ul>
  </div>
  `;
};

const createCardTemplate = () => {
  return `
  <li class="list_item active">
    <form class="item-info">
      <input class="item-title" placeholder="제목을 입력하세요"></input>
      <textarea class="item-content" placeholder="내용을 입력하세요"></textarea>
      <div class="active-btns">
        <button type="button" class="normal-btn">취소</button>
        <button type="submit" class="accent-btn" disabled="true">등록</button>
      </div>
    </form>
  </li>
  `;
};

const createPostedCardTemplate = ({ title, content }) => {
  return `
    <div class="item-info">
      <h3 class="item-title">${title}</h3>
      <p class="item-content">${content}</p>
    </div>
    <button class="close-btn card-close-btn">
      <span class="icon-close"></span>
    </button>
  `;
};

export { createColumnTemplate, createCardTemplate, createPostedCardTemplate };
