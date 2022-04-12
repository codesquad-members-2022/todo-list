import { createTagTemplate } from '../utils/createTemplate.js';
import AlertView from '../Alert/AlertView.js';
import { Todo } from '../Todo/main.js';
import Card from '../Todo/Card/main.js';

class Controller {
  constructor({ Header, History }) {
    this.header = Header;
    this.history = History;
    this.todo = null;
    this.deleteAlertView = null;
    this.newCard = null;
    this.init();
  }

  init() {
    this.renderInit();
    this.header.view.eventInit(this.menuBtnClickHandler.bind(this));
    this.history.view.eventInit(this.menuBtnClickHandler.bind(this));
  }

  renderInit() {
    const body = document.querySelector('body');
    body.insertAdjacentHTML(
      'afterbegin',
      createTagTemplate('main', '', 'main')
    );
    this.header.view.render();
    this.history.view.render();
    this.initTodo();
  }

  initAlert({ target, content }) {
    target = new AlertView(content);

    target.onClickCancel(handleClickCancel);
    target.onClickAccept(handleClickAccept);

    function handleClickCancel() {
      target.render();
    }

    function handleClickAccept() {
      // Todo: 추후 로직 추가
      target.render();
    }
  }

  initTodo() {
    this.todo = Todo;
    this.todo.view.init();
    this.todo.model.setColumns();
    this.todo.view.eventInit({
      ColumnClickHanlder: this.columnClickHanlder.bind(this),
    });
  }

  columnClickHanlder({ target }) {
    switch (target.className) {
      case 'control_btn add':
        this.addCard(target);
        break;
    }
  }

  addCard(target) {
    const targetColumnBox = target.closest('.todo_column_box');
    const targetColumnID = targetColumnBox.id;
    const targetColumn = this.todo.model.columns[targetColumnID];

    if (!targetColumn.model.updateAddStstue()) {
      this.cancelAddCard(targetColumnBox);
      return;
    }

    const cardId = this.updateCardCount('add');
    this.newCard = new Card({ cardId: cardId });
    this.newCard.view.renderAddCard(targetColumnBox, cardId);
    this.newCard.view.eventInit({
      cardInputHandler: this.cardInputHandler.bind(this),
      cardAddHandler: this.cardAddHandler.bind(this),
    });
  }

  cancelAddCard(targetColumnBox) {
    targetColumnBox.querySelector('.card.write').remove();
    this.updateCardCount('cancelAdd');
  }

  updateCardCount(action) {
    this.todo.model.updateCardCount(action);
    return this.todo.model.getAllCardCount();
  }

  cardInputHandler({ target }) {
    const { titleInput, contentInput, accentBtn } =
      this.getTargetCardInfo(target);

    if (!(titleInput.value && contentInput.value)) {
      accentBtn.setAttribute('disabled', 'false');
      return;
    }
    if (!accentBtn.getAttribute('disabled')) return;
    accentBtn.removeAttribute('disabled');
  }

  cardAddHandler({ target }) {
    const {
      targetColumnBox,
      targetCard,
      titleInput,
      contentInput,
      titleText,
      contentText,
    } = this.getTargetCardInfo(target);
    const titleValue = titleInput.value;
    const contentValue = contentInput.value;

    titleText.innerText = titleValue;
    contentText.innerText = contentValue;
    targetCard.classList.remove('write');

    this.newCard.model.title = titleValue;
    this.newCard.model.content = contentValue;

    const targetColumn = this.todo.model.columns[targetColumnBox.id];
    targetColumn.model.addCardList(this.newCard);
    targetColumn.model.updateAddStstue();
    targetColumn.view.renderCardCount(
      targetColumnBox,
      targetColumn.model.getCardCount()
    );
  }

  getTargetCardInfo(target) {
    const targetColumnBox = target.closest('.todo_column_box');
    const targetCard = target.closest('.card');
    const titleText = targetCard.querySelector('.title_text');
    const contentText = targetCard.querySelector('.content_text');
    const titleInput = targetCard.querySelector('.title_input');
    const contentInput = targetCard.querySelector('.content_input');
    const accentBtn = targetCard.querySelector('.accent_btn');
    return {
      targetColumnBox,
      targetCard,
      titleInput,
      titleText,
      contentInput,
      contentText,
      accentBtn,
    };
  }

  menuBtnClickHandler() {
    this.header.model.updateStatus();
    const menuStatus = this.header.model.getMenuStatus();
    this.history.view.animation(menuStatus);
  }
}

export default Controller;
