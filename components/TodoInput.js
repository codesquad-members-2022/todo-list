import Todo from './Todo.js';
import TodoNotice from './TodoNotice.js';
import { activationForm } from '../utils/handleStyle.js';
import { TEXTAREA_DEFAULT_HEIGHT, TEXTAREA_RESIZE_HEIGHT } from '../constants/constants.js';
import { $ } from '../utils/dom.js';

export default class TodoInput {
  constructor(status, setOnInput, handleCount) {
    this.status = status;
    this.title = '';
    this.content = '';
    this.setOnInput = setOnInput;
    this.handleCount = handleCount;
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
  };

  getLastId = () => {
    const lastNum = JSON.parse(localStorage.getItem('todos'));
    return lastNum.length;
  };

  createTodo = () => {
    const todo = {};
    todo.id = this.getLastId() + 1;
    todo.title = this.title;
    todo.content = this.content;
    todo.status = this.status;
    todo.userId = 1;

    const todos = JSON.parse(localStorage.getItem('todos'));
    todos.push(todo);
    localStorage.setItem('todos', JSON.stringify(todos));

    return todo;
  };

  createNotice = () => {
    /** TODO
     * TODO: Notice 부분 작업 예정
     * */
    const notice = {};
    // notice.id
    notice.title = this.title;
    notice.status = this.status;
    notice.userId = 1;
    // localStorage Setting

    return notice;
  };

  onRegisterBtn = () => {
    const newTodo = new Todo(this.createTodo());
    new TodoNotice(this.createNotice()).render();
    $(`.${this.status}`).insertAdjacentHTML('afterend', newTodo.render());
    $(`.input-${this.status}`)?.remove();
    newTodo.handleEventListener();
    this.setOnInput(false);
    this.handleCount();
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
