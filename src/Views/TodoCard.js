import { createElement } from '../utils/utils';

export default class TodoCard {
  constructor(cardData) {
    const { id, columnId, title, desc, author, createdAt } = cardData;
    this.id = id;
    this.columnId = columnId;
    this.title = title;
    this.desc = desc;
    this.author = author;
    this.createAt = createdAt;
    this.$todoCard = createTodoCard(id, title, desc, author);
  }
}

const createTodoCard = (id, title, desc, author) => {
  const $todoCard = createElement('li', 'todo-item', {
    'data-id': id,
  });

  $todoCard.innerHTML = `
    <div class="todo-form">
      <div class="todo-item-textarea">
        <header class="todo
        -item-title">
          <textarea placeholder="제목을 입력하세요" rows="1">${title}</textarea>
        </header>
        <div class="todo-item-desc">
          <textarea placeholder="내용을 입력하세요" rows="1">${desc}</textarea>
        </div>
        <footer class="todo-item-author">
          author by
          <span class="author">${author}</span>
        </footer>
      </div>
      <button type="button" class="delete-button">
        <i class="ic-close"></i>
      </button>
    </div>
    <footer class="todo-buttons">
      <button type="button" class="cancel-button button-normal">취소</button>
      <button type="button" class="register-button button-accent" disabled="disabled">등록</button>
    </footer>
  `;

  return $todoCard;
};
