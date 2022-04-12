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
      cardsTemplate += card.nomalTemplate();

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

  async init() {
    await this.viewModel.init();
    this.board = new Board(this.viewModel.boardState);
    this.setColumns();
    const columnsTemplate = this.createColumnsTemplate();
    this.board.render(columnsTemplate);
    this.board.addEvent(this);
  }
}

export { BoardController };
