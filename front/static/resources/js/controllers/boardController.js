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

  #createColumns() {
    const columns = {};
    Object.keys(this.viewModel.boardState).forEach(columnName => {
      const cards = this.#createCards(this.viewModel.boardState[columnName]);
      const column = new Column({ title: columnName, cards: cards });
      columns[columnName] = column;
    });

    return columns;
  }

  #observe(newCardData, method, id) {
    this.viewModel.observe(newCardData, method, id);
  }

  render() {
    const columns = this.#createColumns();
    this.board.setColumns(columns);
    this.board.render();
  }

  async init() {
    await this.viewModel.init();
    this.board = new Board();
    this.render();
    this.popup = new Popup();
    this.popup.render();
    this.board.addEvent([this.#createCard, this.#observe.bind(this), this.popup.show.bind(this.popup)]);
    this.popup.addEvent([this.#observe.bind(this)]);
  }
}

export { BoardController };
