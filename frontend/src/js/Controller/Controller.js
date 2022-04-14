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
    this.cardCurValue = {
      title: null,
      content: null,
    };
    this.deletedCard = null;
    this.deletedColumn = null;
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

  initAlert() {
    this.deleteAlertView = new AlertView({
      title: '선택한 카드를 삭제할까요?',
      cancel: '취소',
      accept: '삭제',
    });
    this.deleteAlertView.onClickCancel(handleClickCancel.bind(this));
    this.deleteAlertView.onClickAccept(handleClickAccept.bind(this));

    function handleClickCancel() {
      this.deleteAlertView.render();
    }

    function handleClickAccept() {
      const targetColumn = this.todo.model.columns[this.deletedColumn.id];
      const targetCard = targetColumn.model.cardList[this.deletedCard.id];
      targetCard.view.renderDeleted(this.deletedCard);

      targetColumn.model.deleteCard(this.deletedCard.id);
      targetColumn.model.updateCardCount();
      targetColumn.view.renderCardCount(
        this.deletedColumn,
        targetColumn.model.cardCount
      );
      this.deleteAlertView.render();
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

  columnClickHanlder({ target, type }) {
    switch (type) {
      case 'click':
        if (target.className === 'control_btn add') {
          this.addCard(target);
          return;
        }
        if (target.className === 'btn normal_btn') {
          this.cardCancelHandler(target);
          return;
        }
        break;
      case 'dblclick':
        if (target.closest('.write')) return;
        if (target.closest('.card')) this.cardEditHanler(target);
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
      cardDeleteHandler: this.cardDeleteHandler.bind(this),
    });
  }

  cancelAddCard(targetColumnBox) {
    const cancelCard = targetColumnBox.querySelector('.card.write');
    this.newCard.view.renderDeleted(cancelCard);
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
      accentBtn.setAttribute('disabled', 'true');
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

  cardCancelHandler(target) {
    const { targetColumnBox, targetCard, titleInput, contentInput } =
      this.getTargetCardInfo(target);
    const targetColumn = this.todo.model.columns[targetColumnBox.id];
    const targetCardInfo = targetColumn.model.cardList[targetCard.id];

    if (targetCard.classList.contains('edit')) {
      targetCardInfo.view.cancelEditMode({
        targetCard,
        titleInput,
        contentInput,
        value: this.cardCurValue,
      });
      return;
    }

    this.cancelAddCard(targetColumnBox);
    targetColumn.model.updateAddStstue();
  }

  cardEditHanler(target) {
    const { targetColumnBox, targetCard } = this.getTargetCardInfo(target);
    const targetColumnId = targetColumnBox.id;
    const targetColumn = this.todo.model.columns[targetColumnId];
    const editCard = targetColumn.model.cardList[targetCard.id];
    let targetText = null;
    if (
      target.className === 'title_text' ||
      target.className === 'content_text'
    ) {
      targetText = target;
    }

    this.cardCurValue = {
      title: editCard.model.title,
      content: editCard.model.content,
    };
    editCard.view.changeEditMode(targetCard, targetText);
  }

  cardDeleteHandler({ target }) {
    const { targetColumnBox, targetCard } = this.getTargetCardInfo(target);
    this.deletedColumn = targetColumnBox;
    this.deletedCard = targetCard;

    targetCard.classList.add('delete_hover');
    this.deleteAlertView.$alert_container.classList.remove('hidden');
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
