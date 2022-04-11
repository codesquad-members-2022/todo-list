import TodoEdit from './TodoEdit.js';
import { getLocalStorageByKey } from '../utils/localStorage.js';
export default class Todo {
  constructor(todoData) {
    this.todoData = todoData;
  }

  onMouseDown = e => {
    if (e.target.classList.contains('card__delete')) {
      this.deleteBtn(e.target);
      return;
    }

    const dataDrag = e.currentTarget.getAttribute('data-drag');

    if (dataDrag === 'true' && e.detail === 1) {
      e.currentTarget.classList.add('spectrum');
      this.createCopyTodo(e.pageX, e.pageY);
      return;
    }

    if (e.detail === 2) {
      e.currentTarget.setAttribute('data-drag', false);
      this.showEditForm();
      return;
    }
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
    return /*html*/ `<div class="card original-MouseOver" id =${this.todoData.id} data-drag="true">
      <header>
        <h3>${this.todoData.title}</h3>
        <button class="card__delete">x</button>
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
    document.getElementById(this.todoData.id).addEventListener('dbclick', this.showEditForm);
    document.getElementById(this.todoData.id).addEventListener('mousedown', this.onMouseDown);
    document.getElementById(this.todoData.id).addEventListener('mouseover', this.ondeleteOver);
    document.getElementById(this.todoData.id).addEventListener('mouseout', this.ondeleteOut);
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

  ondeleteOver = ({ target }) => {
    const test = document.getElementById(this.todoData.id);
    if (target.classList.contains('card__delete')) {
      test.classList.add('deleteBtn-MouseOver');
      test.classList.remove('original-MouseOver');
    }
  };

  ondeleteOut = ({ target }) => {
    const test = document.getElementById(this.todoData.id);
    if (target.classList.contains('deleteBtn-MouseOver')) {
      target.classList.remove('deleteBtn-MouseOver');
      target.classList.add('original-MouseOver');
    }
  };

  deleteBtn = target => {
    const test = document.getElementById(this.todoData.id);
    const modelEvent = document.querySelector('.deleteEventModal');

    if (target.classList.contains('card__delete')) {
      modelEvent.style.display = 'block';
    }

    document.querySelector('.closeButton').addEventListener('click', function () {
      modelEvent.style.display = 'none';
    });

    document.querySelector('.deletebutton').addEventListener('click', function () {
      const objIndex = getLocalStorageByKey('todos').findIndex(e => e.id === Number(test.id)); //1

      const removeData = getLocalStorageByKey('todos').splice(0, 1);
      console.log(removeData);

      //localStorage.setItem('todos', JSON.stringify(removeData));

      test.remove();
      modelEvent.style.display = 'none';
    });
  };
}
