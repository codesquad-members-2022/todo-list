import TodoEdit from './TodoEdit.js';
import Modal from './Modal.js';
import { popupRemoveTitle } from '../constants/modal.js';
import { createNotice, handleNotice } from '../utils/action.js';

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
      this.handleDeleteBtn();
      return;
    }

    const dataDrag = e.currentTarget.getAttribute('data-drag');

    if (dataDrag === 'true' && e.detail === 1) {
      e.currentTarget.classList.add('spectrum');
      this.createCopyTodo();
      return;
    }

    if (e.detail === 2) {
      e.currentTarget.setAttribute('data-drag', false);
      this.showEditForm();
      return;
    }
  };

  createCopyTodo = () => {
    const copytodoElement = document.createElement('div');
    document.body.insertAdjacentElement('beforeend', copytodoElement);
    copytodoElement.classList.add('drag');
    copytodoElement.classList.add('cursor');
    Object.assign(copytodoElement.style, {
      position: 'absolute',
      left: `${this.todoElement.getBoundingClientRect().left}px`,
      top: `${this.todoElement.getBoundingClientRect().top}px`,
    });
    copytodoElement.setAttribute('data-id', this.todoData.id);
    copytodoElement.setAttribute('data-status', this.todoData.status);
    copytodoElement.innerHTML = this.render();
  };

  render = () => {
    return /*html*/ `<div class="card" id =${this.todoData.id} data-drag="true">
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
    </div>`;
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

  handleDeleteBtn = () => {
    const modal = new Modal(this.todoData.id, popupRemoveTitle, this.handleMinusCount);
    modal.showModal();
    modal.handleEventListener();

    const notice = createNotice(this.todoData, '삭제');
    handleNotice(notice);
  };
}
