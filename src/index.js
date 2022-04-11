import { $, eventDelegate, createElement } from './utils/utils.js';

const $todoColumns = $('.todo-columns');

const template = () => {
  return `
      <div class="todo-form">
        <div class="todo-item-textarea">
          <header class="todo-item-title">
            <textarea placeholder="제목을 입력하세요" rows="1" ></textarea>
          </header>
          <div class="todo-item-desc">
            <textarea placeholder="내용을 입력하세요" rows="1" ></textarea>
          </div>
          <footer class="todo-item-author">
            author by
            <span class="author">web</span>
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
};

const handleClick = event => {
  const html = template();
  const $todoColumn = event.target.closest('.todo-column');
  const $todoList = $('.todo-list', $todoColumn);
  const $li = createElement('li', ['todo-item', 'active-item']);
  $li.innerHTML = html;
  $todoList.prepend($li);
};

eventDelegate({
  target: $todoColumns,
  eventName: 'click',
  selector: '.add-button',
  handler: handleClick,
});