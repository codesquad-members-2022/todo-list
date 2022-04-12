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
    const newCard = new Card(cardId);
    newCard.view.renderAddCard(targetColumnBox, cardId);
  }

  cancelAddCard(targetColumnBox) {
    targetColumnBox.querySelector('.card.write').remove();
    this.updateCardCount('cancelAdd');
  }

  updateCardCount(action) {
    this.todo.model.updateCardCount(action);
    return this.todo.model.getAllCardCount();
  }

  menuBtnClickHandler() {
    this.header.model.updateStatus();
    const menuStatus = this.header.model.getMenuStatus();
    this.history.view.animation(menuStatus);
  }
}

export default Controller;
