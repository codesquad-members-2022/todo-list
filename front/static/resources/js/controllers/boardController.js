import { BoardViewModel } from '../viewModels/boardViewModel.js';
import { Board } from '../views/component/board.js';
import { Card } from '../views/component/card.js';
import { Column } from '../views/component/column.js';

class BoardController {
  constructor() {
    this.board = new Board();
    this.viewModel = new BoardViewModel();
  }

  createCardsTemplate(cardsState) {
    const cardsTemplate = cardsState.reduce((cardsTemplate, cardState) => {
      const card = new Card(cardState);
      const finalTemplate = (cardsTemplate += card.nomalTemplate());

      return finalTemplate;
    }, '');

    return cardsTemplate;
  }

  createColumnsTemplate(boardState) {
    const columnsTemplate = Object.keys(boardState).reduce((columnsTemplate, columnName) => {
      const column = new Column({ title: columnName, cards: boardState[columnName] });
      const cardsTemplate = this.createCardsTemplate(column.props.cards);
      const finalTemplate = (columnsTemplate += column.template(cardsTemplate));

      return finalTemplate;
    }, '');

    return columnsTemplate;
  }

  async init() {
    await this.viewModel.setState();
    const columnsTemplate = this.createColumnsTemplate(this.viewModel.boardState);
    this.board.render(columnsTemplate);
  }
}

export { BoardController };
