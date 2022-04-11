import HistoryCard from './HistoryCard.js';

export default class Histories {
  init() {
    const histories = [1, 2, 3];
    const HistoryCardComponents = histories.map(h => new HistoryCard())
  }

  template() {
    return `
      <div class="history">
        <button class="history__btn--close">x</button>
        ${this.HistoryCardComponents.reduce((prev, h) => {
          prev + h.template();
        }, '')}
      </div>
    `
  }
}
