import Todo from './Todo.js';
import { activationForm } from '../../utils/handleStyle.js';
import { TEXTAREA_DEFAULT_HEIGHT, TEXTAREA_RESIZE_HEIGHT } from '../constants/constants.js';
import { getLocalStorageByKey, getLastIdByKey } from '../../utils/localStorage.js';
import { $ } from '../../utils/dom.js';
import { createNotice, handleNotice } from '../../utils/action.js';

export default class TodoInput {
  constructor(status, setOnInput, handleAddCount, handleMinusCount) {
    this.status = status;
    this.title = '';
    this.content = '';
    this.setOnInput = setOnInput;
    this.handleAddCount = handleAddCount;
    this.handleMinusCount = handleMinusCount;
  }

  onInputContent = ({ target }) => {
    target.style.height = TEXTAREA_DEFAULT_HEIGHT + 'px';
    target.style.height = TEXTAREA_RESIZE_HEIGHT + target.scrollHeight + 'px';

    const inputRegisterElement = $(`.input-${this.status} .input--register`);
    if (target.value.length === 0) {
      activationForm(inputRegisterElement, true);
    } else {
      this.content = target.value;
      activationForm(inputRegisterElement, false);
    }
  };

  onInputHeader = ({ target }) => {
    this.title = target.value;
  };

  onCloseBtn = () => {
    $(`.input-${this.status}`)?.remove();
    this.setOnInput(false);
  };

  createTodo = () => {
    const todo = {};
    todo.id = getLastIdByKey('todos') + 1;
    todo.title = this.title;
    todo.content = this.content;
    todo.status = this.status;
    todo.userId = 1;

    const localTodos = getLocalStorageByKey('todos');
    localTodos.push(todo);
    localStorage.setItem('todos', JSON.stringify(localTodos));

    return todo;
  };

  onRegisterBtn = () => {
    if (!this.title || !this.content) {
      alert('값이 비워져 있습니다....');
      return;
    }
    const todo = this.createTodo();
    const newTodo = new Todo(todo, this.handleMinusCount);
    $(`.${this.status}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.handleEventListener();
    $(`.input-${this.status}`)?.remove();
    this.setOnInput(false);

    const newNotice = {};
    newNotice.title = this.title;
    newNotice.status = this.status;

    const notice = createNotice(newNotice, '등록');
    handleNotice(notice);

    this.handleAddCount();
  };

  render = () => {
    return /*html*/ `
        <article class="input-wrapper todo-border input-${this.status}">
            <input class="input-header"placeholder="제목을 입력하세요" />
            <textarea class="input-content" placeholder="내용을 입력하세요" maxlength ='500' ></textarea>
            <div class="input-button-wrapper">
                <button class="input__button input--cancel">취소</button>
                <button class="input__button input--register bg-sky-blue" disabled>등록</button>
            </div>
        </article>
    `;
  };

  handleEventListener = () => {
    $(`.input-${this.status} .input--cancel`).addEventListener('click', this.onCloseBtn);
    $(`.input-${this.status} .input-header`).addEventListener('input', this.onInputHeader);
    $(`.input-${this.status} .input-content`).addEventListener('input', this.onInputContent);
    $(`.input-${this.status} .input--register`).addEventListener('click', this.onRegisterBtn);
  };
}
