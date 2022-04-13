import { fetchRequest, HTTP_REQUEST } from '../utils/fetch.js';
import { isEmptyInput } from '../utils/util.js';
import { BoardViewModel } from '../viewModels/boardViewModel.js';
import { Board } from '../views/component/board.js';
import { Card } from '../views/component/card.js';
import { Column } from '../views/component/column.js';

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
    target.setAttribute('disabled', true);
    column.props.cards = [...column.props.cards, { cardStatus: column.props.title }];
  }

  removeWritableCard(column) {
    column.toggleDisableAttribute(column.props.title);
    column.props.cards = column.props.cards.slice(0, -1);
  }

  toggleWritableCard(target, column) {
    const btnType = target.classList.contains('button--add') ? 'add' : 'cancle';

    if (btnType === 'add') {
      this.addWritableCard(target, column);
    } else if (btnType === 'cancle') {
      this.removeWritableCard(column);
    }
    this.setCards(column.props.cards);
  }

  updateColumnToWritable(target) {
    const columnName = target.dataset.status;
    const column = this.getColumn(columnName);
    this.toggleWritableCard(target, column);

    const cardsTemplate = this.createCardsTemplate(column.props.cards);
    column.render(column.props.title, cardsTemplate);
  }

  newCardInfo($form, columnName) {
    const title = $form.querySelector('.card__title').value;
    const contents = $form.querySelector('.card__content').value;
    if (isEmptyInput(title) || isEmptyInput(contents)) {
      alert('내용을 입력해주세요.');
      return;
    }

    return {
      title: title,
      contents: contents,
      writer: '도니',
      cardStatus: columnName,
      cardIndex: this.columns.length
    };
  }

  createNewCard(target) {
    const columnName = target.dataset.status;
    const writableCard = this.cards[this.cards.length - 1];
    const $form = writableCard.getCardFormElement(columnName);
    const cardInfo = this.newCardInfo($form, columnName);
    return cardInfo;
  }

  postNewCard(newCard) {
    if (!newCard) return;

    const requestOption = HTTP_REQUEST.POST(newCard);
    fetchRequest('http://localhost:8080', requestOption);
  }

  btnClickHandler({ target }) {
    if (target.classList.contains('card-button--add') || target.classList.contains('card__button--cancle')) {
      this.updateColumnToWritable(target);
    }
    if (target.classList.contains('card__button--submit')) {
      const newCard = this.createNewCard(target);
      this.postNewCard(newCard);
      this.updateColumnToWritable(target);
    }
  }

  async init() {
    await this.viewModel.init();
    this.setColumns();
    const columnsTemplate = this.createColumnsTemplate();
    this.board = new Board();
    this.board.render(columnsTemplate);
    this.board.addEvent(this);
  }
}

export { BoardController };
