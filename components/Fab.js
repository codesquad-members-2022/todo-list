import { getLocalStorageByKey } from '../utils/localStorage.js';
export default class Fab {
  constructor() {
    this.id = 0;
    this.status = '';
    this.count = 0;
    this.isDeleted = false;
  }
  insertColumn = () => {
    return /* html */ `
    <article class="column-list ${this.status}-wrapper" data-status="${this.status}">
        <nav class="column ${this.status}">
            <div class="column__left">
                <span class="column__title">${this.status}</span>
                <div class="column__count">${this.count}</div>
            </div>
            <div class="column__right">
            <button class="column__add">+</button>
            <button class="column__delete">x</button>
            </div>
        </nav>
    </article>
      `;
  };

  fabModal = () => {
    return /*html*/ `
        <article class="input-wrapper todo-border input-fab">
            <input class="input-header"placeholder="칼럼을 추가하세요" maxlength ='500' /> 
            <div class="input-button-wrapper">
                <button class="input__button fab--cancel">취소</button>
                <button class="input__button fab--register sky-blue">등록</button>
            </div>
        </article>
        <div class="modalBackground"></div>
    `;
  };

  register = () => {
    this.status = document.querySelector('.input-header').value;
    const columns = getLocalStorageByKey('column') ?? [];
    columns.push(this.status);

    localStorage.setItem('column', JSON.stringify(columns));

    this.cancelBtn();
    const columnsWrapper = document.querySelector('.column-section');
    columnsWrapper.insertAdjacentHTML('beforeend', this.insertColumn());
  };

  cancelBtn = () => {
    console.log('gg');
    document.querySelector('.input-fab').remove();
    document.querySelector('.modalBackground').remove();
  };

  showModal = () => {
    document.body.insertAdjacentHTML('beforeend', this.fabModal());
    document.querySelector('.input-fab .fab--cancel').addEventListener('click', this.cancelBtn);
    document.querySelector('.input-fab .fab--register').addEventListener('click', this.register);
  };

  onHandle = () => {
    document.querySelector('.fabBtn').addEventListener('click', this.showModal);
  };
}
