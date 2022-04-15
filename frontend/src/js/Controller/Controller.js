import { createTagTemplate } from '../utils/createTemplate.js';
import AlertView from '../Alert/AlertView.js';
import { Todo } from '../Todo/main.js';
import Card from '../Todo/Card/main.js';
import { postCard, deleteCard, patchCard } from '../Utils/api.js';

class Controller {
  constructor({ Header, History }) {
    this.header = Header;
    this.history = History;
    this.todo = null;
    this.deleteAlertView = null;
    this.actionCard = null;
    this.cardCurValue = {
      title: null,
      content: null,
    };
    this.hoverCard = null;
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
    this.initHistory();
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
      const targetCard = document.querySelector('.card.delete_hover');
      this.hoverCard.view.changeDeleteMode(targetCard);
      this.deleteAlertView.render();
    }

    async function handleClickAccept() {
      const targetColumn = this.findTodoColumn(
        this.deletedColumn.dataset.columnid
      );
      const deleteCardId = this.deletedCard.dataset.cardid;

      const targetCard = this.findTodoCard(targetColumn, deleteCardId);
      targetCard.view.renderDeleted(this.deletedCard);

      targetColumn.model.deleteCard(deleteCardId);

      const response = await deleteCard({ cardId: deleteCardId });

      this.updateHistory(response);

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
    this.todo.model.fetchColumns(this.carEventInit.bind(this));
    this.todo.view.eventInit({
      ColumnClickHanlder: this.columnClickHanlder.bind(this),
    });
  }

  carEventInit(card) {
    card.view.eventInit({
      cardId: card.model.id,
      cardInputHandler: this.cardInputHandler.bind(this),
      cardAddHandler: this.cardAddHandler.bind(this),
      cardDeleteHandler: this.cardDeleteHandler.bind(this),
      hoverHandler: this.cardDeleteHoverHandeler.bind(this),
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
    const targetColumn = this.findTodoColumn(targetColumnBox.dataset.columnid);
    if (!targetColumn.model.updateAddStstue()) {
      this.cancelAddCard(targetColumnBox);
      return;
    }

    const cardId = this.updateCardCount('add');
    this.actionCard = new Card({ cardId: cardId });
    this.actionCard.view.renderAddCard(targetColumnBox, cardId);
    this.actionCard.view.eventInit({
      cardId,
      cardInputHandler: this.cardInputHandler.bind(this),
      cardAddHandler: this.cardAddHandler.bind(this),
      cardDeleteHandler: this.cardDeleteHandler.bind(this),
      hoverHandler: this.cardDeleteHoverHandeler.bind(this),
    });
  }

  cancelAddCard(targetColumnBox) {
    const cancelCard = targetColumnBox.querySelector('.card.write');
    this.actionCard.view.renderDeleted(cancelCard);
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

  updateHistory({ history }) {
    const { userName, createDateTime } = history;

    this.history.model.addHistory(history);
    const calcTime = this.calcHistoryTime(createDateTime);
    this.history.view.renderAddHistoryCard({
      userName,
      content: this.creatHistoryContent(history),
      time: calcTime,
    });
  }

  async cardAddHandler({ target }) {
    const {
      targetColumnBox,
      targetColumnID,
      targetCard,
      titleInput,
      contentInput,
      titleText,
      contentText,
    } = this.getTargetCardInfo(target);
    const titleValue = titleInput.value;
    const contentValue = contentInput.value;
    const targetColumn = this.findTodoColumn(targetColumnID);

    if (!targetCard.classList.contains('edit')) {
      targetColumn.model.updateAddStstue();
      const author = 'web';
      const columnId = targetColumnID;

      const response = await postCard({
        card: {
          author,
          columnId,
          content: contentValue,
          title: titleValue,
        },
      });
      this.updateHistory(response);
    } else {
      const response = await patchCard({
        card: {
          author: 'web',
          content: contentValue,
          title: titleValue,
        },
        cardId: targetCard.dataset.cardid,
      });

      this.updateHistory(response);
    }

    titleText.innerText = titleValue;
    contentText.innerText = contentValue;
    this.actionCard.model.title = titleValue;
    this.actionCard.model.content = contentValue;

    targetCard.classList.remove('write', 'edit');
    targetColumn.model.addCardList(this.actionCard);

    targetColumn.view.renderCardCount(
      targetColumnBox,
      targetColumn.model.getCardCount()
    );
  }

  cardDeleteHoverHandeler({ type, target }) {
    const alert = document.querySelector('.alert_container.hidden');
    if (type === 'mouseout' && alert === null) {
      return;
    }
    const { targetColumnBox, targetColumnID, targetCard, targetCardId } =
      this.getTargetCardInfo(target);
    const targetColumn = this.findTodoColumn(targetColumnID);
    this.hoverCard = this.findTodoCard(targetColumn, targetCardId);
    this.hoverCard.view.changeDeleteMode(targetCard);
  }

  cardCancelHandler(target) {
    const {
      targetColumnBox,
      targetColumnID,
      targetCard,
      targetCardId,
      titleInput,
      contentInput,
    } = this.getTargetCardInfo(target);
    const targetColumn = this.findTodoColumn(targetColumnID);
    const targetCardInfo = this.findTodoCard(targetColumn, targetCardId);

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
    const { targetColumnID, targetCard, targetCardId } =
      this.getTargetCardInfo(target);
    const targetColumn = this.findTodoColumn(targetColumnID);
    const editCard = this.findTodoCard(targetColumn, targetCardId);
    this.actionCard = editCard;
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

    this.deleteAlertView.$alert_container.classList.remove('hidden');
  }

  getTargetCardInfo(target) {
    const targetColumnBox = target.closest('.todo_column_box');
    const targetColumnID = targetColumnBox.dataset.columnid;
    const targetCard = target.closest('.card');
    const titleText = targetCard.querySelector('.title_text');
    const contentText = targetCard.querySelector('.content_text');
    const titleInput = targetCard.querySelector('.title_input');
    const contentInput = targetCard.querySelector('.content_input');
    const accentBtn = targetCard.querySelector('.accent_btn');
    const targetCardId = targetCard.dataset.cardid;
    return {
      targetColumnBox,
      targetColumnID,
      targetCard,
      targetCardId,
      titleInput,
      titleText,
      contentInput,
      contentText,
      accentBtn,
    };
  }

  findTodoColumn(columnId) {
    return this.todo.model.columns[columnId];
  }
  findTodoCard(targetColumn, cardId) {
    return targetColumn.model.cardList[cardId];
  }

  async initHistory() {
    this.history.view.render();
    await this.history.model.fetchHistories();
    const histories = await this.history.model.getHistories();
    histories.forEach((history) => {
      const content = this.creatHistoryContent(history);
      const calcTime = this.calcHistoryTime(history.createDateTime);
      this.history.view.renderInitHistoryCard({
        userName: history.userName,
        content,
        time: calcTime,
      });
    });
  }

  calcHistoryTime(historyTime) {
    const nowTime = new Date();
    const timeValue = new Date(historyTime);

    const betweenTime = Math.floor(
      (nowTime.getTime() - timeValue.getTime()) / 1000 / 60
    );
    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
      return `${betweenTime}분전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
      return `${betweenTimeHour}시간전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay < 365) {
      return `${betweenTimeDay}일전`;
    }
  }

  creatHistoryContent(history) {
    const actionDic = {
      CREATE: '등록',
      UPDATE: '수정',
      DELETE: '삭제',
      MOVE: '이동',
    };
    let historyContent = '';
    switch (history.action) {
      case 'CREATE':
        historyContent = `
        <strong>${history.columnName}</strong>에 <strong>${
          history.title
        }</strong>를
        <strong>${actionDic[history.action]}</strong>하였습니다.
        `;
        break;
      case 'UPDATE':
        historyContent = `
        <strong>${history.fields[0].oldValue}</strong>를 <strong>${
          history.fields[0].newValue
        }</strong>로
        <strong>${actionDic[history.action]}</strong>하였습니다.
        `;
        break;
      case 'DELETE':
        historyContent = `
        <strong>${history.columnName}</strong>에 <strong>${
          history.title
        }</strong>를
        <strong>${actionDic[history.action]}</strong>하였습니다.
        `;
        break;
      case 'MOVE':
        historyContent = `
        <strong>${history.title}</strong>을 <strong>${
          history.fields[0].oldValue
        }</strong>에서 <strong>${history.columnName}</strong>로 
        <strong>${actionDic[history.action]}</strong>하였습니다.
        `;
        break;
    }

    return historyContent;
  }

  menuBtnClickHandler() {
    this.header.model.updateStatus();
    const menuStatus = this.header.model.getMenuStatus();
    this.history.view.animation(menuStatus);
  }
}

export default Controller;
