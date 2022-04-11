import { getLocalStorageByKey } from '../utils/localStorage.js';
import { $ } from '../utils/dom.js';
export default class Modal {
  constructor(id) {
    this.id = id;
  }

  render = () => {
    return /*html */ `
      <div class="modal">
        <p>정말 삭제하시겠습니까?</p>
        <button class="modal--close">취소</button>
        <button class="modal--remove">삭제</button>
      </div>`;
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
    const filteredTodos = getLocalStorageByKey('todos').filter(e => e.id !== Number(this.id));
    localStorage.setItem('todos', JSON.stringify(filteredTodos));

    document.getElementById(`${this.id}`)?.remove();
    this.closeButton();
  };

  handleEventListener = () => {
    $('.modal--close').addEventListener('click', this.closeButton);
    $('.modal--remove').addEventListener('click', this.deleteButton);
  };
}
