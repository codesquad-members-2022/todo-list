import { $, $$ } from '../utility/util.js';
import {
  makeCategory,
  makeCard,
  addCardTemplate,
} from './todo-view-template.js';

export default class View {
  constructor() {
    this.columnList = $('.column-list');
  }

  renderTodoCategory(categoryName) {
    this.columnList.insertAdjacentHTML('beforeend', makeCategory(categoryName));
  }

  renderTodoCard(todoInfo, data, newCard = false) {
    const container = this.findCurrentContainer(todoInfo.category);

    if (newCard) {
      container.insertAdjacentHTML('afterBegin', makeCard(todoInfo, data));
    } else {
      container.insertAdjacentHTML('beforeEnd', makeCard(todoInfo, data));
    }

    this.changeCategoryCount(todoInfo, container);
    this.removeCardBtnEvent();
  }

  findCurrentContainer = (categoryName) => $(`.container${categoryName}`);

  changeCategoryCount(todoInfo, container) {
    $(`.itemCount${todoInfo.category}`).innerHTML = container.childElementCount;
  }

  addCardTemplateEvent() {
    const addBtn = $$('.addBtn');
    addBtn.forEach((el) => el.addEventListener('click', this.showCardTemplate));
  }

  showCardTemplate = ({ target }) => {
    const columnName = target.closest('.column-item').id;
    const column = this.findCurrentContainer(columnName);
    column.insertAdjacentHTML('afterBegin', addCardTemplate());
    this.addCardBtnEvent();
  };

  addCardBtnEvent() {
    const registerBtn = $('.registerBtn');
    registerBtn.addEventListener('click', this.getHeaderValue);
    const cancelBtn = $('.cancelBtn');
    cancelBtn.addEventListener('click', this.cancelCardTemplate);
  }

  cancelCardTemplate = ({ target }) => {
    target.closest('.column-addBtn').remove();
  };

  removeCardBtnEvent() {
    const deleteBtn = $('.delete-btn');
    deleteBtn.addEventListener('click', this.showCheckBox);
  }

  showCheckBox() {
    Swal.fire({
      title: '정말 취소 하시겠습니까?',
      text: '다시 되돌릴 수 없습니다. 신중하세요.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '확인',
      cancelButtonText: '취소',
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          '리스트가 삭제되었습니다',
          '확인 버튼을 눌러주세요',
          'success'
        );
      }
    });
  }
}
