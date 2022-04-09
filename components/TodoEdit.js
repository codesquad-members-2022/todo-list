import { editLocalStorageById } from '../utils/localStorage.js';
import TodoEditTemplate from './TodoEditTemplate.js';
export default class TodoEdit {
  constructor(editObj, todoRender, todoRun) {
    this.id = editObj.id;
    this.title = editObj.title;
    this.content = editObj.content;
    this.userId = editObj.userId;
    this.todoRender = todoRender;
    this.todoRun = todoRun;
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

  run = () => {
    document.getElementById(this.id).addEventListener('input', this.onEditInputContent);
    document.getElementById(this.id).addEventListener('click', this.onButtons);
  };

  onEditInputContent = ({ target }) => {
    const editFormElement = document.getElementById(this.id);
    const editContentForm = editFormElement.querySelector('.input--update');

    if (target.classList.contains('edit-input-content')) {
      if (target.value.length === 0) {
        editContentForm.disabled = true;
        editContentForm.classList.remove('bg-blue');
        editContentForm.classList.add('bg-sky-blue');
      } else {
        editContentForm.disabled = false;
        editContentForm.classList.remove('bg-sky-blue');
        editContentForm.classList.add('bg-blue');
      }
    }
  };
  onButtons = ({ target }) => {
    if (target.classList.contains('input--cancel')) {
      document.getElementById(this.id).outerHTML = this.todoRender();
      this.todoRun();
    }

    if (target.classList.contains('input--update')) {
      const editFormElement = document.getElementById(this.id);
      const editTitle = editFormElement.querySelector('.edit-input-header').value;
      const editContent = editFormElement.querySelector('.edit-input-content').value;

      const editLocalStorageObj = { title: editTitle, content: editContent };
      editLocalStorageById(editLocalStorageObj, this.id);
      editFormElement.innerHTML = new TodoEditTemplate(editTitle, editContent, this.userId).render();
      document.getElementById(this.id).classList.remove('todo-border');
    }
  };
}
