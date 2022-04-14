import HistoryCard from "./HistoryCard.js";
import { store } from "../store.js";
import { GET_HISTORY } from "../dummyData.js";
import { $ } from "../utils.js";

export default class Histories {
  constructor(target) {
    this.target = target;
    this.historyCardComponents = GET_HISTORY.map(
      (history) => new HistoryCard(history)
    );
    store.initState("history");
    store.subscribe("history", this.render.bind(this));
    store.setState("history", GET_HISTORY);
  }
  template() {
    return `
        <button class="history__btn--close">x</button>
        ${this.historyCardComponents.reduce(
          (prev, h) => prev + h.template(),
          ""
        )}
    `;
  }
  render() {
    this.target.innerHTML = this.template();
  }
  addEvent() {
    $(".header__menu").addEventListener("click", showHistoryView);
    $(".history__btn--close").addEventListener("click", hideHistoryView);
  }
}

function showHistoryView() {
  $(".history").style.right = "0";
}

function hideHistoryView() {
  $(".history").style.right = "-45.2rem";
}
