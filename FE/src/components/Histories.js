import HistoryCard from './HistoryCard.js';
import { historyStore } from '../store/HistoryStore.js';

export default class Histories {
  constructor(target) {
    this.target = target;
    const histories = [
      {
          "cardAction": "MOVE",
          "userId": "test1",
          "cardTitle": "카드 제목 1",
          "cardTitleBefore": "카드 제목 1",
          "cardStatus": "TODO",
          "cardStatusBefore": "ONGOING",
          "createdAt": "2022-04-11T10:52:29.248343"
      },
      {
          "cardAction": "UPDATE",
          "userId": "test2",
          "cardTitle": "카드 제목 2",
          "cardTitleBefore": "카드 제목 이전 2",
          "cardStatus": "DONE",
          "cardStatusBefore": "DONE",
          "createdAt": "2022-04-11T10:52:29.24877"
      },
    ];
    this.historyCardComponents = histories.map((history) => new HistoryCard(history));
    historyStore.initState('histories');
    historyStore.subscribe('histories', this.render.bind(this));
    historyStore.setState('histories', histories);
  }
  init() {
    
  }

  template() {
    return `
        <button class="history__btn--close">x</button>
        ${this.historyCardComponents.reduce((prev, h) =>prev + h.template(), '')}
    `
  }
  render() {
    this.target.innerHTML = this.template();
  }
}