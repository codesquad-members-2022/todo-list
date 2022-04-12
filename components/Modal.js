import { getLocalStorageByKey } from '../utils/localStorage.js';
import { $ } from '../utils/dom.js';
import { popupCloseText, popupOkText } from '../constants/modal.js';
export default class Modal {
  constructor(id, title, handleMinusCount) {
    this.id = id;
    this.title = title;
    this.onOk = false;
    this.handleMinusCount = handleMinusCount;
  }

  render = () => {
    return /*html */ `
      <div class="modal">
        <p class="modal__title">${this.title}</p>
        <div class="modal-button__wrapper">
          <button class="modal__button modal--close">${popupCloseText}</button>
          <button class="modal__button modal--remove">${popupOkText}</button>
        </div>
      </div>`;
  };

  getOnOK = () => {
    return this.onOk;
  };

  showModal = () => {
    document.body.insertAdjacentHTML('beforeend', this.render());
    $('.modalBackground').classList.remove('no-display');
  };

  closeButton = () => {
    $('.modal').remove();
    $('.modalBackground').classList.add('no-display');
  };

  deleteButton = () => {
    this.onOk = true;
    /** TODO
     * TODO: onOk 속성으로 아래 코드는 모달 클래스가 아닌 상위 클래스에서 해주도록 변경필요
     * */
    const filteredTodos = getLocalStorageByKey('todos').filter(e => e.id !== Number(this.id));
    localStorage.setItem('todos', JSON.stringify(filteredTodos));
    this.handleMinusCount();

    document.getElementById(`${this.id}`)?.remove();
    this.closeButton();
  };

  handleEventListener = () => {
    $('.modal--close').addEventListener('click', this.closeButton);
    $('.modal--remove').addEventListener('click', this.deleteButton);
  };
}
