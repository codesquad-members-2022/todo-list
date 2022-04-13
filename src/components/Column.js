import { getCards } from "../api.js";
import Component from "../core/Component.js";
import Cards from "./Cards.js";

export default class Column extends Component {
  async setup() {
    const columnIndex = Number(this.$target.dataset.index);
    const cards = await getCards(columnIndex);
    this.setState({
      columnIndex: columnIndex,
      cards: cards.map((card) => ({ ...card, cardState: "default" })),
    });
  }
  template() {
    const { title, cards } = this.state;
    return `
      <div class="column-header">
        <div class="flex">
          <h1 class="column-title">${title}</h1>
          <div class="card-count">${cards?.length}</div>
        </div>
        <div class="flex">
          <div class="column-plus-button"></div>
          <div class="column-delete-button"></div>
        </div>
      </div>
      <div class="card-wrapper"></div>
    `;
  }
  mounted() {
    const { cards } = this;
    const $cardWrapper = this.$target.querySelector(".card-wrapper");
    new Cards($cardWrapper, { cards, undoCreateCard: this.undoCreateCard.bind(this) });
  }
  get cards() {
    const { cards } = this.state;
    return cards;
  }
  setEvent() {
    this.addEvent("click", ".column-plus-button", this.clickPlusButtonHandler.bind(this));
  }
  clickPlusButtonHandler() {
    if (this.state.cards[0]?.cardState === "create") {
      this.undoCreateCard();
    } else {
      this.createCard();
    }
  }
  createCard() {
    const _cards = this.state.cards;
    _cards.unshift({
      title: "",
      content: "",
      author: "나",
      cardState: "create",
    });
    this.setState({ cards: _cards });
  }
  undoCreateCard() {
    const _cards = this.state.cards;
    _cards.shift();
    this.setState({ cards: _cards });
  }
}
