import Todo from './Todo.js';

export default class TodoInput {
  constructor(status) {
    this.status = status;
    this.title = '';
    this.content = '';
  }

  onInputContent = ({ target }) => {
    if (target.value.length === 0) {
      document.querySelector(`.input-${this.status} .input--register`).disabled = true;
      document.querySelector(`.input-${this.status} .input--register`).style.background = '#86c6ff';
    } else {
      this.content = target.value;
      document.querySelector(`.input-${this.status} .input--register`).disabled = false;
      document.querySelector(`.input-${this.status} .input--register`).style.background = '#0075DE';
    }
  };

  onInputHeader = ({ target }) => {
    this.title = target.value;
  };

  onCloseBtn = () => {
    document.querySelector(`.input-${this.status}`)?.remove();
    this.onInput = false;
  };

  onRegisterBtn = () => {
    const todo = {};
    todo.title = this.title;
    todo.content = this.content;
    todo.status = this.status;
    todo.userId = 1;
    const todos = JSON.parse(localStorage.getItem('todos'));
    todos.push(todo);
    localStorage.setItem('todos', JSON.stringify(todos));

    const newTodo = new Todo(todo);
    document.querySelector(`.${todo.status}`).insertAdjacentHTML('afterend', newTodo.render());
    document.querySelector(`.input-${todo.status}`)?.remove();
  };

  render = () => {
    return /*html*/ `
        <article class="input-wrapper input-${this.status}">
            <input class="input-header"placeholder="제목을 입력하세요" />
            <input class="input-content" placeholder="내용을 입력하세요" maxlength ='500' />
            <div class="input-button-wrapper">
                <button class="input__button input--cancel">취소</button>
                <button class="input__button input--register" disabled>등록</button>
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
