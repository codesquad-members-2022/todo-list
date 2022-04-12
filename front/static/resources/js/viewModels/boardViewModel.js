import { BoardStore } from '../stores/boardStore.js';

class BoardViewModel {
  constructor() {
    this.store = new BoardStore();
    this.store.addObserver(this);
    this.observers = new Set();
    this.boardState;
  }

  parseStoreState() {
    const columnState = this.store.boardState.reduce((parsedData, card) => {
      if (parsedData[card.cardStatus] === undefined) {
        parsedData[card.cardStatus] = [];
      }
      parsedData[card.cardStatus].push(card);
      return parsedData;
    }, {});

    return columnState;
  }

  initState() {
    this.boardState = this.parseStoreState();
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  notify() {
    this.setState();
    this.observers.forEach(observer => {
      observer.notify();
    });
  }

  async init() {
    await this.store.init();
    this.initState();
  }
}

export { BoardViewModel };
