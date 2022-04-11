import { editLocalStorageById } from '../utils/localStorage.js';
import { activationForm } from '../utils/handleStyle.js';
export default class TodoEdit {
  constructor(editObj, todoRender, todoHandleEventListener) {
    this.id = editObj.id;
    this.title = editObj.title;
    this.content = editObj.content;
    this.userId = editObj.userId;
    this.todoRender = todoRender;
    this.todoHandleEventListener = todoHandleEventListener;
  }

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

  editTemplate = editData => {
    return /*html*/ `
      <header>
        <h3>${editData.title}</h3>
        <button class="column__delete">x</button>
      </header>
      <div class="card__content">
        <p class="card__content-text">${editData.content}</p>
      </div>
      <div class="card__author">
        <p class="card__author-text">author by ${editData.userId}</p>
      </div>`;
  };

  handleEventListener = () => {
    document.getElementById(this.id).addEventListener('input', this.onEditInputContent);
    document.getElementById(this.id).addEventListener('click', this.onButtons);
  };

  onEditInputContent = ({ target }) => {
    const editFormElement = document.getElementById(this.id);
    const editContentForm = editFormElement.querySelector('.input--update');

    if (target.classList.contains('edit-input-content')) {
      if (target.value.length === 0) {
        activationForm(editContentForm, true);
      } else {
        activationForm(editContentForm, false);
      }
    }
  };

  onButtons = ({ target }) => {
    if (target.classList.contains('input--cancel')) {
      document.getElementById(this.id).outerHTML = this.todoRender();
      this.todoHandleEventListener();
    }

    if (target.classList.contains('input--update')) {
      const editFormElement = document.getElementById(this.id);
      const editTitle = editFormElement.querySelector('.edit-input-header').value;
      const editContent = editFormElement.querySelector('.edit-input-content').value;

      const editData = { title: editTitle, content: editContent, userId: this.userId };
      editLocalStorageById(editData, this.id);

      editFormElement.innerHTML = this.editTemplate(editData);
      document.getElementById(this.id).classList.remove('todo-border');
    }
  };
}
