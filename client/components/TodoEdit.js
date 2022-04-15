import { editLocalStorageById } from '../utils/localStorage.js';
import { activationForm } from '../utils/handleStyle.js';
import { createNotice, handleNotice } from '../utils/action.js';

export default class TodoEdit {
  constructor(editObj, todoRender, setTodoData, todoHandleEventListener) {
    this.id = editObj.id;
    this.title = editObj.title;
    this.status = editObj.status;
    this.content = editObj.content;
    this.userId = editObj.userId;
    this.todoRender = todoRender;
    this.setTodoData = setTodoData;
    this.todoHandleEventListener = todoHandleEventListener;
    this.editTodoElement = '';
  }

  cacheElement = () => {
    this.editTodoElement = document.getElementById(`${this.id}`);
  };

  render = () => {
    return /*html*/ `
        <article class="edit-input-wrapper">
            <input class="edit-input-header" value =${this.title} placeholder="제목을 입력하세요" />
            <textarea class="edit-input-content" placeholder="내용을 입력하세요" maxlength ='500' >${this.content}</textarea>
            <div class="input-button-wrapper">
                <button class="input__button input--cancel">취소</button>
                <button class="input__button input--update bg-blue">수정</button>
            </div>
        </article>
    `;
  };

  editTemplate = () => {
    return /*html*/ `
      <header>
        <h3 class="card__title">${this.title}</h3>
        <button class="card__delete">x</button>
      </header>
      <div class="card__content">
        <p class="card__content-text">${this.content}</p>
      </div>
      <div class="card__author">
        <p class="card__author-text">author by ${this.userId}</p>
      </div>`;
  };

  handleEventListener = () => {
    this.cacheElement();
    this.editTodoElement.querySelector('.edit-input-header').addEventListener('input', this.onEditInputTitle);
    this.editTodoElement.querySelector('.edit-input-content').addEventListener('input', this.onEditInputContent);
    this.editTodoElement.querySelector('.input--cancel').addEventListener('click', this.onCloseBtn);
    this.editTodoElement.querySelector('.input--update').addEventListener('click', this.onUpdateBtn);
  };

  onEditInputTitle = ({ target }) => {
    this.title = target.value;
  };

  onEditInputContent = ({ target }) => {
    if (target.classList.contains('edit-input-content')) {
      const editUpdateBtn = this.editTodoElement.querySelector('.input--update');
      if (target.value.length === 0) {
        activationForm(editUpdateBtn, true);
      } else {
        this.content = target.value;
        activationForm(editUpdateBtn, false);
      }
    }
  };

  onCloseBtn = () => {
    this.editTodoElement.outerHTML = this.todoRender();
    this.todoHandleEventListener();
  };

  onUpdateBtn = () => {
    const editData = { title: this.title, content: this.content, userId: this.userId };
    editLocalStorageById('todos', editData, this.id);
    this.editTodoElement.classList.remove('todo-border');

    this.todoHandleEventListener();
    this.editTodoElement.innerHTML = this.editTemplate();
    const newTodoData = {
      id: this.id,
      title: this.title,
      content: this.content,
      userId: this.userId,
    };
    this.setTodoData(newTodoData);

    const newNotice = {
      title: this.title,
      status: this.status,
    };

    const notice = createNotice(newNotice, '수정');
    handleNotice(notice);
    this.editTodoElement.setAttribute('data-drag', true);
  };
}
