import { checkBtnType, isEmptyInput } from '../utils/util.js';
import { BoardViewModel } from '../viewModels/boardViewModel.js';
import { Board } from '../views/component/board.js';
import { Card } from '../views/component/card.js';
import { Column } from '../views/component/column.js';
import { Popup } from '../views/popup.js';

class BoardController {
  constructor() {
    this.viewModel = new BoardViewModel();
    this.viewModel.addObserver(this);
    this.cards = [];
    this.columns = [];
  }

  setCards(cardsState) {
    const cards = cardsState.map(cardState => {
      const card = new Card(cardState);

      return card;
    });

    this.cards = cards;
  }

  setColumns() {
    const columns = Object.keys(this.viewModel.boardState).map(columnName => {
      const column = new Column({ title: columnName, cards: this.viewModel.boardState[columnName] });
      this.setCards(this.viewModel.boardState[columnName]);

      return column;
    });

    this.columns = columns;
  }

  createCardsTemplate() {
    const cardsTemplate = this.cards.reduce((cardsTemplate, card) => {
      const currentTemplate = card.props.hasOwnProperty('id') ? card.nomalTemplate() : card.writableTemplate();

      cardsTemplate = currentTemplate + cardsTemplate;

      return cardsTemplate;
    }, '');

    return cardsTemplate;
  }

  createColumnsTemplate() {
    const columnsTemplate = this.columns.reduce((columnsTemplate, column) => {
      const cardsTemplate = this.createCardsTemplate(column.props.cards);
      columnsTemplate += column.template(cardsTemplate);

      return columnsTemplate;
    }, '');

    return columnsTemplate;
  }

  getColumn(columnName) {
    for (let column of this.columns) {
      if (column.props.title === columnName) {
        return column;
      }
    }
  }

  addWritableCard(target, column) {
    column.disableAddBtn(target);
    column.props.cards = [...column.props.cards, { cardStatus: column.props.title }];
  }

  removeWritableCard(column) {
    const $addBtn = column.getAddBtn(column.props.title);
    const isDisabledBtn = column.isDisabledBtn($addBtn);
    if (isDisabledBtn) {
      column.activateAddBtn($addBtn);
    }
    column.props.cards = column.props.cards.slice(0, -1);
  }

  updateColumnState(target, column) {
    const btnType = checkBtnType(target);
    if (btnType === 'add') {
      this.addWritableCard(target, column);
    } else if (btnType === 'cancle') {
      this.removeWritableCard(column);
    }
    this.setCards(column.props.cards);
  }

  renderSingleColumn(column) {
    const cardsTemplate = this.createCardsTemplate(column.props.cards);
    column.render(column.props.title, cardsTemplate);
  }

  toggleWritableCard(target) {
    const columnName = target.dataset.status;
    const column = this.getColumn(columnName);
    this.updateColumnState(target, column);
    this.renderSingleColumn(column);
  }

  getCardFormData(writableCard, columnName) {
    const $form = writableCard.getFormElement(columnName);
    const title = writableCard.getTitle($form);
    const contents = writableCard.getContent($form);
    return { title, contents };
  }

  getCardInfo(writableCard, columnName) {
    const { title, contents } = this.getCardFormData(writableCard, columnName);
    if (isEmptyInput(title) || isEmptyInput(contents)) {
      alert('내용을 입력해주세요.');
      return;
    }

    return {
      cardIndex: this.columns.length,
      title: title,
      contents: contents,
      writer: '도니',
      cardStatus: columnName,
    };
  }

  createNewCard(target) {
    const columnName = target.dataset.status;
    const writableCard = this.cards[this.cards.length - 1];
    const cardInfo = this.getCardInfo(writableCard, columnName);
    return cardInfo;
  }

  addCardEvent(target) {
    if (target.classList.contains('card-button--add') || target.classList.contains('card__button--cancle')) {
      this.toggleWritableCard(target);
    }
    if (target.classList.contains('card__button--submit')) {
      const newCard = this.createNewCard(target);
      this.viewModel.store.postRequest(newCard);
      this.viewModel.store.observe();
      this.viewModel.notify();
    }
  }

  deleteCardEvent(target) {
    if (target.classList.contains('card__button--delete')) {
      this.popup.show();
    }
    if (target.classList.contains('popup-button--cancle')) {
      this.popup.hidden();
    }
    if (target.classList.contains('popup-button--confirm')) {
      this.viewModel.store.deleteRequest();
      this.viewModel.store.observe();
      this.viewModel.notify();
      this.popup.hidden();
    }
  }

  btnClickHandler({ target }) {
    this.addCardEvent(target);
    this.deleteCardEvent(target);
  }

  addEvent() {
    this.board.addEvent(this);
  }

  async init() {
    await this.viewModel.init();
    this.setColumns();
    const columnsTemplate = this.createColumnsTemplate();
    this.board = new Board();
    this.board.render(columnsTemplate);

    this.popup = new Popup();
    this.popup.render();

    this.addEvent();
  }
}

export { BoardController };
