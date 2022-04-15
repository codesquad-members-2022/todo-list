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
    this.updateCardClickEvent();
  }

  findCurrentContainer = (categoryName) => $(`.container${categoryName}`);

  changeCategoryCount({ category }, container) {
    $(`.itemCount${category}`).innerHTML = container.childElementCount;
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
    const deleteBtnList = $$('.delete-btn');

    deleteBtnList.forEach((deleteBtn) => {
      deleteBtn.addEventListener('click', this.showCheckBox);
    });
  }

  showCheckBox = ({ target }) => {
    this.toggleDeletingClass(target);
    Swal.fire(
      {
        title: '정말 취소 하시겠습니까?',
        text: '다시 되돌릴 수 없습니다.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '확인',
        cancelButtonText: '취소',
      },
      target
    ).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          '리스트가 삭제되었습니다',
          '확인 버튼을 눌러주세요',
          'success'
        );
        this.getRemoveValue(target);
      } else {
        this.toggleDeletingClass(target);
      }
    });
  };

  toggleDeletingClass(target) {
    const selected_li = target.closest('.column-item--card');
    selected_li.classList.toggle('deleting');
  }

  updateCardClickEvent() {
    const cards = $$('.column-item--card');

    cards.forEach((card) => {
      card.addEventListener('dblclick', this.changeUpdateForm);
    });
  }

  changeUpdateForm = ({ target }) => {
    const selected_li = target.closest('.column-item--card');
    selected_li.style.display = 'none';
    const isUpdate = this.getCardTextContent(selected_li);

    this.showUpdateForm(target, isUpdate);
    this.addUpdateCardBtnEvent(selected_li);
  };

  getCardTextContent(selected_li) {
    const cardTitle = selected_li.querySelector('.card-title').textContent;
    const cardContent = selected_li.querySelector('.card-content').textContent;

    return { cardTitle, cardContent };
  }

  showUpdateForm(target, isUpdate) {
    const targetElement = target.closest('.column-item--card');
    targetElement.insertAdjacentHTML('Beforebegin', addCardTemplate());

    const updateForm = targetElement.previousElementSibling;

    updateForm.querySelector('.card-title').value = isUpdate.cardTitle;
    updateForm.querySelector('.card-content').innerHTML = isUpdate.cardContent;
    updateForm.querySelector('.registerBtn').innerHTML = '수정';
  }

  addUpdateCardBtnEvent(selected_li) {
    $('.registerBtn').addEventListener('click', ({ target }) =>
      this.getUpdateValue(target, selected_li.id)
    );

    $('.cancelBtn').addEventListener('click', ({ target }) =>
      this.removeUpdateForm(target, selected_li)
    );
  }

  removeUpdateForm(target, selected_li) {
    target.closest('.column-addBtn').remove();
    selected_li.style.display = 'flex';
  }

  renderUpdateCard(target, { title, content }) {
    const updateFormElement = target.closest('.column-item--card');
    const targetElement = updateFormElement.nextElementSibling;
    const cardTitle = targetElement.querySelector('.card-title');
    const cardContent = targetElement.querySelector('.card-content');

    this.removeUpdateForm(updateFormElement, targetElement);

    cardTitle.innerHTML = title;
    cardContent.innerHTML = content;
  }
}
