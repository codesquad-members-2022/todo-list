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

  setState() {
    this.boardState = this.parseStoreState();
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  observe(cardState, method) {
    this.store.observe(cardState, method);
  }

  notify() {
    this.setState();
    this.observers.forEach(observer => {
      observer.render();
    });
  }

  async init() {
    await this.store.init();
    this.setState();
  }
}

export { BoardViewModel };
