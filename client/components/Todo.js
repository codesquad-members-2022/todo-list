import TodoEdit from './TodoEdit.js';
import { popupRemoveTitle } from '../constants/modal.js';
import { createNotice, handleNotice } from '../utils/action.js';
import { ONCLICK, DBLCLICK } from '../constants/constants.js';
import { getLocalStorageByKey } from '../utils/localStorage.js';
import TodoDeleteModal from './TodoDeleteModal.js';

export default class Todo {
  constructor(todoData, handleMinusCount) {
    this.todoData = todoData;
    this.todoElement = '';
    this.handleMinusCount = handleMinusCount;
  }

  cacheElement = () => {
    this.todoElement = document.getElementById(`${this.todoData.id}`);
  };

  setTodoData = todoData => {
    this.todoData = todoData;
  };

  onMouseDown = e => {
    if (e.target.classList.contains('card__delete')) {
      this.onModal();
      return;
    }

    const dataDrag = e.currentTarget.getAttribute('data-drag');
    if (dataDrag === 'true' && e.detail === ONCLICK) {
      return;
    }

    if (e.detail === DBLCLICK) {
      e.currentTarget.setAttribute('data-drag', false);
      this.showEditForm();
      return;
    }
  };

  render = () => {
    return /*html*/ `
    <div class="start"></div>
    <article class="card" id =${this.todoData.id} data-drag="true">
        <header>
          <h3 class="card__title">${this.todoData.title}</h3>
          <button class="card__delete">x</button>
        </header>
        <div class="card__content">
          <p class="card__content-text">${this.todoData.content}</p>
        </div>
        <div class="card__author">
          <p class="card__author-text">author by ${this.todoData.userId}</p>
        </div>
      </article>
    `;
  };

  handleEventListener = () => {
    this.cacheElement();
    this.todoElement.addEventListener('mousedown', this.onMouseDown);
    this.todoElement.addEventListener('mouseover', this.onDeleteMouseOver);
    this.todoElement.addEventListener('mouseout', this.onDeleteMouseOut);
  };

  showEditForm = () => {
    const editObj = {
      id: this.todoData.id,
      title: this.todoData.title,
      status: this.todoData.status,
      content: this.todoData.content,
      userId: this.todoData.userId,
    };

    const todoEdit = new TodoEdit(editObj, this.render, this.setTodoData, this.handleEventListener);
    this.todoElement.classList.add('todo-border');
    this.todoElement.innerHTML = todoEdit.render();
    todoEdit.handleEventListener();
  };

  onDeleteMouseOver = ({ target }) => {
    if (target.classList.contains('card__delete')) {
      this.todoElement.classList.add('card__delete--mouseOver');
    }
  };

  onDeleteMouseOut = () => {
    this.todoElement.classList.remove('card__delete--mouseOver');
  };

  onModal = () => {
    const modal = new TodoDeleteModal(this.todoData.id, popupRemoveTitle, this.handleMinusCount, this.handleDeleteBtn);
    modal.showModal();
    modal.handleEventListener();
  };

  handleDeleteBtn = () => {
    const filteredTodos = getLocalStorageByKey('todos').filter(e => e.id !== Number(this.todoData.id));
    localStorage.setItem('todos', JSON.stringify(filteredTodos));

    document.getElementById(`${this.todoData.id}`)?.remove();
    const notice = createNotice(this.todoData, '삭제');
    handleNotice(notice);
  };
}
