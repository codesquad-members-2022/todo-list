import TodoEdit from './TodoEdit.js';

export default class Todo {
  constructor(todoData) {
    this.todoData = todoData;
  }

  onMouseDown = e => {
    e.currentTarget.classList.add('spectrum');
    this.createCopyTodo(e.pageX, e.pageY);
  };

  createCopyTodo = (x, y) => {
    const copyCardElement = document.createElement('div');
    document.body.insertAdjacentElement('beforeend', copyCardElement);
    copyCardElement.classList.add('drag');
    copyCardElement.style.position = 'absolute';
    copyCardElement.style.left = `${x}px`;
    copyCardElement.style.top = `${y}px`;
    copyCardElement.setAttribute('data-id', this.todoData.id);
    copyCardElement.innerHTML = this.render();
  };

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
    document.getElementById(this.todoData.id).addEventListener('dblclick', this.showEditForm);
    document.getElementById(this.todoData.id).addEventListener('click', this.deleteBtn);
    document.getElementById(this.todoData.id).addEventListener('mousedown', this.onMouseDown);
  };

  showEditForm = () => {
    const editObj = {
      id: this.todoData.id,
      title: this.todoData.title,
      content: this.todoData.content,
      userId: this.todoData.userId,
    };
    const todoEdit = new TodoEdit(editObj, this.render, this.run);
    document.getElementById(this.todoData.id).classList.add('todo-border');
    document.getElementById(this.todoData.id).innerHTML = todoEdit.render();
    todoEdit.run();
  };

  deleteBtn = ({ target }) => {
    const modelEvent = document.querySelector('.deleteEventModal');
    console.log(target.id);

    if (target.classList.contains('column__delete')) {
      modelEvent.style.display = 'block';
    }
    document.querySelector('.closeButton').addEventListener('click', function () {
      modelEvent.style.display = 'none';
    });
    document.querySelector('.deletebutton').addEventListener('click', function () {
      console.log(this.id);
    });
  };
}
