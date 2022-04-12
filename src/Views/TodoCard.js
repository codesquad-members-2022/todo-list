import { $, createElement, hasClass } from '../utils/utils';

export default class TodoCard {
  constructor({ id, columnId, columnIdx, title = '', desc = '', author = '', createdAt }) {
    this.id = id;
    this.columnId = columnId; // -> db저장용 key
    this.columnIdx = columnIdx; // -> 카드의 위치 (브라우저에 렌더링 해야하는 위치 정보)
    this.title = title;
    this.desc = desc;
    this.author = author;
    this.createAt = createdAt;
    this.$todoCard = createTodoCard(id, title, desc, author);
    this.addEventHandlers();
    this.listen();
  }

  listen() {
    this.$todoCard.addEventListener('@clickCancelButton', event => {
      this.handleClickCancelButton(event);
    });
  }

  addEventHandlers() {
    this.$todoCard.addEventListener('input', event => {
      if (!hasClass('active-item', event.currentTarget)) return;
      this.handleInput(event);
    });
  }

  handleInput(event) {
    const $registerCard = event.currentTarget;
    const $title = $('.todo-item-title textarea', $registerCard);
    const $desc = $('.todo-item-desc textarea', $registerCard);

    const $registerBtn = $('.register-button', $registerCard);
    if (!($title.value && $desc.value)) {
      $registerBtn.disabled = true;
      return;
    }
    $registerBtn.disabled = false;
  }

  handleClickCancelButton(event) {
    const $todoCard = event.target;
    $todoCard.parentNode.removeChild($todoCard);
  }
}

const createTodoCard = (id, title, desc, author) => {
  const $todoCard = createElement('li', 'todo-item', {
    'data-id': id,
  });

  $todoCard.innerHTML = `
    <div class="todo-form">
      <div class="todo-item-textarea">
        <header class="todo-item-title">
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
