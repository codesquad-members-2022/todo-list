import { $ } from '../../utils/dom.js';
import { popupCloseText, popupOkText } from '../../constants/modal.js';
export default class Modal {
  constructor(id, title) {
    this.id = id;
    this.title = title;
  }

  render = () => {
    return /*html */ `
      <div class="modal">
        <p class="modal__title">${this.title}</p>
        <div class="modal-button__wrapper">
          <button class="modal__button modal--close">${popupCloseText}</button>
          <button class="modal__button modal--remove">${popupOkText}</button>
        </div>
      </div>
      <div class="modalBackground no-display"></div>
      `;
  };

  showModal = () => {
    document.body.insertAdjacentHTML('beforeend', this.render());
    $('.modalBackground').classList.remove('no-display');
  };

  closeButton = () => {
    $('.modal').remove();
    $('.modalBackground').classList.add('no-display');
  };

  handleEventListener = () => {
    $('.modal--close').addEventListener('click', this.closeButton);
  };
}
