import { v4 as uuidv4 } from 'uuid';
import { createElement } from '../utils/utils.js';

export class TodoItem{
  constructor(id = uuidv4(), columnId, title, desc, author = 'web') {
    this.$element;
    this.id = id;
    this.columnId = columnId;
    this.title = title;
    this.desc = desc;
    this.author = author;
    this.createdAt = Date.now();
  }

  init(cardData) {
    const { id, columnId, title, desc, author, createAt} = cardData;
    this.id = id;
    this.columnId = columnId;
    this.title = title;
    this.desc = desc;
    this.author = author;
    this.createAt = createAt;
    this.$element = createTodoItem(id, title, desc, author);
  }
}

const createTodoItem = (id, title, desc, author) => {
  const $todoItem = createElement('li', 'todo-item', {
    'data-id': id,
  });

  $todoItem.innerHTML = `
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
  `


  return $todoItem;
};
`
<li class="todo-item active-item">

</li>
`