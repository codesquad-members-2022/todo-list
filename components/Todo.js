import TodoEdit from './TodoEdit.js';

export default class Todo {
  constructor(todoData) {
    this.todoData = todoData;
  }
  render = () => {
    return /*html*/ `<div class="card" id =${this.todoData.id}>
      <header>
        <h3>${this.todoData.title}</h3>
        <div class="column__delete">x</div>
      </header>
      <div class="card__content">
        <p class="card__content-text">${this.todoData.content}</p>
      </div>
      <div class="card__author">
        <p class="card__author-text">author by ${this.todoData.userId}</p>
      </div>
    </div>`;
  };

  run = () => {
    document.getElementById(this.todoData.id).addEventListener('dblclick', this.makeEdit);
  };

  makeEdit = () => {
    //수정폼 판별...

    const todoEdit = new TodoEdit(this.todoData.id, this.todoData.content);
    //document.getElementById(this.todoData.id).remove();
    document.getElementById(this.todoData.id).classList.add('todo-border');
    document.getElementById(this.todoData.id).innerHTML = todoEdit.render();
  };
}
