import { getLocalStorageByKey } from '../utils/localStorage.js';
import TodoColumn from './TodoColumn.js';
import { $ } from '../utils/dom.js';
export default class Fab {
  constructor() {}

  render() {
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
  }

  onRegisterBtn() {
    const value = $('.input-header').value;
    const columns = getLocalStorageByKey('column') ?? [];
    columns.push(value);

    localStorage.setItem('column', JSON.stringify(columns));

    this.onCancelBtn();
    const columnsWrapper = $('.column-section');
    const newColumn = new TodoColumn(value);
    columnsWrapper.insertAdjacentHTML('beforeend', newColumn.render());
  }

  onCancelBtn() {
    $('.input-fab').remove();
    $('.modalBackground').remove();
  }

  showModal = callback => {
    document.body.insertAdjacentHTML('beforeend', this.render());
    callback();
  };

  handleEventListener() {
    $('.fabBtn').addEventListener('click', () =>
      this.showModal(() => {
        $('.input-fab .fab--cancel').addEventListener('click', this.onCancelBtn);
        $('.input-fab .fab--register').addEventListener('click', this.onRegisterBtn);
      })
    );
  }
}
