import { btnX } from "../../utils/assetURL";

export function createItem({ id, title, content }) {
  return `
  <li class="card" data-card="${id}">
    <div class="card__content">
      <input class="card__content--title" required readonly value="${title}" />
      <textarea class="card__content--text" autosize readonly rows="1">${content}</textarea>
      <span class="card__content--author">author by web</span>
    </div>
    <img class="card--delete-card" src=${btnX} />
  </li>
  `;
}

// "column__header--add-card" 클릭시 카드박스(등록창) 생성
// data-card가 있어야할까
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

//TODO 생성시 todo-container에 어두운 색을 줌.
export function createItemDeleteAlert() {
  return `
  <div class="card__alert">
    <span class="card__alert--content">선택한 카드를 삭제할까요?</span>
    <div class="card__btn">
      <button class="card__btn--cancel" >취소</button>
      <button class="card__btn--delete" >삭제</button>
    </div>
  </div>
  `;
}
