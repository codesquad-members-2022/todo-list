import Todo from './Todo.js';

export default class TodoInput {
  constructor(title) {
    this.title = title;
    this.content = '';
  }

  onInputContent = event => {
    if (event.target.value.length === 0) {
      document.querySelector(`.input-${this.title} .input--register`).disabled = true;
      document.querySelector(`.input-${this.title} .input--register`).style.background = '#86c6ff';
    } else {
      this.content = event.target.value;
      document.querySelector(`.input-${this.title} .input--register`).disabled = false;
      document.querySelector(`.input-${this.title} .input--register`).style.background = '#0075DE';
    }
  };

  onCloseBtn = () => {
    document.querySelector(`.input-${this.title}`)?.remove();
    this.onInput = false;
  };

  onRegisterBtn = () => {
    const todo = {};
    todo.title = this.title;
    todo.content = this.content;
    todo.status = 'todo';
    todo.userId = 1;
    const todos = JSON.parse(localStorage.getItem('todos'));
    todos.push(todo);
    localStorage.setItem('todos', JSON.stringify(todos));

    const newTodo = new Todo(todo);
    document.querySelector(`.${todo.status}`).insertAdjacentHTML('afterend', newTodo.render());
    document.querySelector(`.input-${todo.title}`)?.remove();
  };

  render = () => {
    return /*html*/ `
        <article class="input-wrapper input-${this.title}">
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
    document.querySelector(`.input-${this.title} .input--cancel`).addEventListener('click', this.onCloseBtn);
    document.querySelector(`.input-${this.title} .input-content`).addEventListener('input', this.onInputContent);
    document.querySelector(`.input-${this.title} .input--register`).addEventListener('click', this.onRegisterBtn);
  };
}
