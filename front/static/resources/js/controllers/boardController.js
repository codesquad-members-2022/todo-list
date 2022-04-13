import { isObjEmpty } from '../utils/util.js';
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
      const currentTemplate = isObjEmpty(card.props) ? card.writableTemplate() : card.nomalTemplate();

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

  insertWritableCard(target) {
    const columnName = target.dataset.title;

    for (let column of this.columns) {
      if (column.props.title === columnName) {
        column.props.cards = [...column.props.cards, {}];
        this.setCards(column.props.cards);
        column.render(column.props.title, this.createCardsTemplate(column.props.cards));
      }
    }
  }

  async btnClickHandler({ target }) {
    if (target.classList.contains('card-button--add')) {
      this.insertWritableCard(target);
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
