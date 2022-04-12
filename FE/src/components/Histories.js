import HistoryCard from "./HistoryCard.js";
import { historyStore } from "../store/HistoryStore.js";
import { GET_HISTORY } from "../dummyData.js";

export default class Histories {
  constructor(target) {
    this.target = target;
    this.historyCardComponents = GET_HISTORY.map(
      (history) => new HistoryCard(history)
    );
    historyStore.initState("history");
    historyStore.subscribe("history", this.render.bind(this));
    historyStore.setState("history", GET_HISTORY);
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
