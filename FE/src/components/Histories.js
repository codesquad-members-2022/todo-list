import HistoryCard from "./HistoryCard.js";
import { store } from "../store.js";
import { GET_HISTORY } from "../dummyData.js";

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
  init() {}

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
}
