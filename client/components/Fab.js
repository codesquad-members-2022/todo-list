import { getLocalStorageByKey } from '../utils/localStorage.js';
import TodoColumn from './TodoColumn.js';
import { $ } from '../utils/dom.js';
export default class Fab {
  constructor() {
    this.id = 0;
    this.status = '';
    this.count = 0;
    this.isDeleted = false;
  }

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
    this.status = $('.input-header').value;
    const columns = getLocalStorageByKey('column') ?? [];
    columns.push(this.status);

    localStorage.setItem('column', JSON.stringify(columns));

    this.cancelBtn();
    const columnsWrapper = $('.column-section');
    const newColumn = new TodoColumn(this.status);
    columnsWrapper.insertAdjacentHTML('beforeend', newColumn.render());
  };

  cancelBtn = () => {
    $('.input-fab').remove();
    $('.modalBackground').remove();
  };

  showModal = () => {
    document.body.insertAdjacentHTML('beforeend', this.fabModal());
    $('.input-fab .fab--cancel').addEventListener('click', this.cancelBtn);
    $('.input-fab .fab--register').addEventListener('click', this.register);
  };

  handleEventListener = () => {
    $('.fabBtn').addEventListener('click', this.showModal);
  };
}
