import { BoardViewModel } from '../viewModels/boardViewModel.js';
import { Board } from '../views/component/board.js';
import { Card } from '../views/component/card.js';
import { Column } from '../views/component/column.js';
import { Popup } from '../views/popup.js';

class BoardController {
  constructor() {
    this.viewModel = new BoardViewModel();
    this.viewModel.addObserver(this);
  }

  #createCard(cardState, completion) {
    return new Card(cardState, completion);
  }

  #createCards(cardsState) {
    const cards = cardsState.map(cardState => {
      const card = this.#createCard(cardState, true);

      return card;
    });

    return cards;
  }

  #createColumn(columnName) {
    const cards = this.#createCards(this.viewModel.boardState[columnName]);
    const column = new Column({ title: columnName, cards: cards });

    return column;
  }

  #createColumns() {
    const columns = {};
    Object.keys(this.viewModel.boardState).forEach(columnName => {
      const column = this.#createColumn(columnName);
      columns[columnName] = column;
    });

    return columns;
  }

  #observe(method, cardData, id) {
    this.viewModel.observe(method, cardData, id);
  }

  render() {
    const columns = this.#createColumns();
    this.board.setColumns(columns);
    this.board.render();
  }

  #addCardEvent() {
    this.board.addEvent([this.#createCard, this.#observe.bind(this), this.popup.show.bind(this.popup)]);
    this.popup.addEvent([this.#observe.bind(this)]);
  }

  async init() {
    await this.viewModel.init();
    this.board = new Board();
    this.render();
    this.popup = new Popup();
    this.popup.render();
    this.#addCardEvent();
  }
}

export { BoardController };
