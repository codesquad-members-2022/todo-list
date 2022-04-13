import modal from './Modal';
import {
  $,
  emit,
  hasClass,
  createElement,
  replaceBrElementWithLinebreak,
  replaceLinebreakWithBrElement,
} from '../utils/utils';

export default class TodoCard {
  constructor({ id, columnId, columnIdx, title = '', desc = '', author = 'web', createdAt }) {
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

  static replaceParagraphWithTextarea($todoCard) {
    const $titleParagraph = $('.todo-item-title p', $todoCard);
    const $descParagraph = $('.todo-item-desc p', $todoCard);

    const $titleTextarea = createElement('textarea', undefined, {
      placeholder: '제목을 입력하세요',
      rows: 1,
    });
    const $descTextarea = createElement('textarea', undefined, {
      placeholder: '내용을 입력하세요',
      rows: 1,
    });

    const title = $titleParagraph.innerHTML;
    const desc = $descParagraph.innerHTML;

    $titleTextarea.value = replaceBrElementWithLinebreak(title);
    $descTextarea.value = replaceBrElementWithLinebreak(desc);

    $titleParagraph.replaceWith($titleTextarea);
    $descParagraph.replaceWith($descTextarea);
  }

  static replaceTextareaWithParagraph($todoCard) {
    const $titleTextarea = $('.todo-item-title textarea', $todoCard);
    const $descTextarea = $('.todo-item-desc textarea', $todoCard);

    const $titleParagraph = createElement('p');
    const $descParagraph = createElement('p');

    const title = $titleTextarea.value;
    const desc = $descTextarea.value;

    $titleParagraph.innerHTML = replaceLinebreakWithBrElement(title);
    $descParagraph.innerHTML = replaceLinebreakWithBrElement(desc);

    $titleTextarea.replaceWith($titleParagraph);
    $descTextarea.replaceWith($descParagraph);
  }

  listen() {
    this.$todoCard.addEventListener('@clickCancelButton', event => {
      this.handleClickCancelButton(event);
    });
    this.$todoCard.addEventListener('@clickDeleteCardButton', event => {
      this.handleClickDeleteButton(event);
    });
  }

  addEventHandlers() {
    const $registerButton = $('.register-button', this.$todoCard);

    $registerButton.addEventListener('click', () => {
      this.handleClickRegisterButton();
    });

    this.$todoCard.addEventListener('input', event => {
      if (!hasClass('active-item', event.currentTarget)) return;
      this.handleInput(event);
    });
  }

  /* event handler */
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

  handleClickDeleteButton() {
    modal.setContents({ title: '선택한 카드를 삭제할까요?', accentText: '삭제' });
    modal.openModal();
  }

  handleClickRegisterButton() {
    const $title = $('.todo-item-title textarea', this.$todoCard);
    const title = $title.value;

    const $desc = $('.todo-item-desc textarea', this.$todoCard);
    const desc = $desc.value;

    const $todoColumn = this.$todoCard.closest('.todo-column');
    emit($todoColumn, '@submitNewTodoCard', { title, desc });
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
          <p>${replaceLinebreakWithBrElement(title)}</p>
        </header>
        <div class="todo-item-desc">
          <p>${replaceLinebreakWithBrElement(desc)}</p>          
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
