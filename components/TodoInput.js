import Todo from './Todo.js';
import TodoNotice from './TodoNotice.js';
import { TEXTAREA_DEFAULT_HEIGHT, TEXTAREA_RESIZE_HEIGHT } from '../constants/constants.js';

export default class TodoInput {
  constructor(status) {
    this.status = status;
    this.title = '';
    this.content = '';
  }

  onInputContent = ({ target }) => {
    target.style.height = TEXTAREA_DEFAULT_HEIGHT + 'px';
    target.style.height = TEXTAREA_RESIZE_HEIGHT + target.scrollHeight + 'px';

    const inputRegisterElement = document.querySelector(`.input-${this.status} .input--register`);
    if (target.value.length === 0) {
      inputRegisterElement.disabled = true;
      inputRegisterElement.classList.remove('bg-blue');
      inputRegisterElement.classList.add('bg-sky-blue');
    } else {
      this.content = target.value;
      inputRegisterElement.disabled = false;
      inputRegisterElement.classList.remove('bg-sky-blue');
      inputRegisterElement.classList.add('bg-blue');
    }
  };

  onInputHeader = ({ target }) => {
    this.title = target.value;
  };

  onCloseBtn = () => {
    document.querySelector(`.input-${this.status}`)?.remove();
    this.onInput = false;
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
    document.querySelector(`.${this.status}`).insertAdjacentHTML('afterend', newTodo.render());

    document.querySelector(`.input-${this.status}`)?.remove();
    newTodo.run();
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

  run = () => {
    document.querySelector(`.input-${this.status} .input--cancel`).addEventListener('click', this.onCloseBtn);
    document.querySelector(`.input-${this.status} .input-header`).addEventListener('input', this.onInputHeader);
    document.querySelector(`.input-${this.status} .input-content`).addEventListener('input', this.onInputContent);
    document.querySelector(`.input-${this.status} .input--register`).addEventListener('click', this.onRegisterBtn);
  };
}
