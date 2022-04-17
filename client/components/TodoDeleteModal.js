import Modal from './modal/Modal.js';
import { $ } from '../utils/dom.js';

export default class TodoDeleteModal extends Modal {
  constructor(id, title, handleMinusCount, handleDeleteBtn) {
    super(id, title);
    this.handleMinusCount = handleMinusCount;
    this.handleDeleteBtn = handleDeleteBtn;
  }

  deleteButton = () => {
    this.handleDeleteBtn();
    this.handleMinusCount();
    this.closeButton();
  };

  handleEventListener = () => {
    $('.modal--close').addEventListener('click', this.closeButton);
    $('.modal--remove').addEventListener('click', this.deleteButton);
  };
}
