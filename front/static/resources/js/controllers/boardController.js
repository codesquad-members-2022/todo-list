import { BoardViewModel } from '../viewModels/boardViewModel.js';
import { Board } from '../views/component/board.js';
import { Card } from '../views/component/card.js';
import { Column } from '../views/component/column.js';

class BoardController {
  constructor() {
    this.viewModel = new BoardViewModel();
    this.viewModel.addObserver(this);
    this.cards = {};
    this.columns = [];
  }

  setCards(cardsState, columnName) {
    const cards = cardsState.map(cardState => {
      const card = new Card(cardState);

      return card;
    });

    this.cards[columnName] = cards;
  }

  setColumns() {
    const columns = Object.keys(this.viewModel.boardState).map(columnName => {
      const column = new Column({ title: columnName, cards: this.viewModel.boardState[columnName] });
      this.setCards(this.viewModel.boardState[columnName], columnName);

      return column;
    });

    this.columns = columns;
  }

  createCardsTemplate(columnName) {
    const cardsTemplate = this.cards[columnName].reduce((cardsTemplate, card) => {
      cardsTemplate += card.normalTemplate();

      return cardsTemplate;
    }, '');

    return cardsTemplate;
  }

  createColumnsTemplate() {
    const columnsTemplate = this.columns.reduce((columnsTemplate, column) => {
      const cardsTemplate = this.createCardsTemplate(column.props.title);
      columnsTemplate += column.template(cardsTemplate);

      return columnsTemplate;
    }, '');

    return columnsTemplate;
  }

  render() {
    this.setColumns();
    const columnsTemplate = this.createColumnsTemplate();
    this.board.render(columnsTemplate);
  }

  reRenderSelectendCard($target) {
    const selectedCard = this.cards[$target.dataset.status][$target.dataset.index];
    selectedCard.reRender($target);
    selectedCard.completion = selectedCard.completion ? false : true;
  }

  getCardNewState($target) {
    const selectedCard = this.cards[$target.dataset.status][$target.dataset.index];
    const title = selectedCard.getTitle($target);
    const contents = selectedCard.getContents($target);
    const cardNewState = {
      cardIndex: selectedCard.props.cardIndex,
      title: title,
      contents: contents,
      cardStatus: selectedCard.props.cardStatus
    };

    return cardNewState;
  }

  observe($target, method) {
    const cardNewState = this.getCardNewState($target);
    this.viewModel.observe(cardNewState, method);
  }

  async init() {
    await this.viewModel.init();
    this.board = new Board();
    this.render();
    this.board.addEvent(this.reRenderSelectendCard.bind(this), this.observe.bind(this));
  }
}

export { BoardController };
