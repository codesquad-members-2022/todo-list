export function createItem({ id, title, content }) {
  return `
  <li class="card" data-card="${id}">
    <div class="card__content">
      <input class="card__content--title" required readonly value="${title}" />
      <textarea class="card__content--text" autosize readonly rows="1">${content}</textarea>
      <span class="card__content--author">author by web</span>
    </div>
    <img class="card--delete-card" src="./asset/icon/icon-card-delete-button-strong.svg" />
  </li>
  `;
}

// "column__header--add-card" 클릭시 카드박스(등록창) 생성
export function createItemBox() {
  return `
  <li class="card-box" data-card="100">
    <div class="card__content">
      <input class="card__content--title" required spellcheck="false" placeholder="제목을 입력하세요" value="" />
      <textarea class="card__content--text" autosize spellcheck="false" rows="1" placeholder="내용을 입력하세요"></textarea>
    </div>
    <div class="card__btn">
      <button class="card__btn--cancel" >취소</button>
      <button class="card__btn--regist" >등록</button>
    </div>
  </li>
  `;
}
