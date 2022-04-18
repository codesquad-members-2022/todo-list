import { getCards } from "../api.js";
import Component from "../core/Component.js";
import Cards from "./Cards.js";

export default class Column extends Component {
  async setup() {
    const columnIndex = Number(this.$target.dataset.index);
    this.setState({
      columnIndex,
    });
    this.setCards(columnIndex);
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
    new Cards($cardWrapper, {
      cards,
      undoCreateCard: this.undoCreateCard.bind(this),
      deactivatePlusButton: this.deactivatePlusButton.bind(this),
      setCards: this.setCards.bind(this),
      notifyNewHistoryData: this.notifyNewHistoryData,
    });
  }
  get cards() {
    const { cards } = this.state;
    return cards;
  }
  async setCards(columnIndex) {
    const cards = await getCards(columnIndex);
    this.setState({
      cards: cards.map((card) => ({ ...card, cardState: "default" })),
    });
  }
  async notifyNewHistoryData() {
    const $menu = document.querySelector(".menu");
    const $actionAlert = $menu.querySelector(".action-alert");
    $actionAlert.classList.remove("hidden-alert");
  }
  setEvent() {
    this.addEvent("click", ".column-plus-button", this.clickPlusButtonHandler.bind(this));
  }
  clickPlusButtonHandler() {
    if (this.state.cards[0]?.cardState === "create") {
      this.undoCreateCard();
      this.deactivatePlusButton();
    } else {
      this.createCard();
      this.activatePlusButton();
    }
  }
  activatePlusButton() {
    const $plusButton = this.$target.querySelector(".column-plus-button");
    $plusButton.classList.add("column-plus-button--active");
  }
  deactivatePlusButton() {
    const $plusButton = this.$target.querySelector(".column-plus-button");
    $plusButton.classList.remove("column-plus-button--active");
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
