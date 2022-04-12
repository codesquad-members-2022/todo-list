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

  async setState() {
    await this.store.setState();
    this.boardState = await this.parseStoreState();
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  notify(columns) {
    this.observers.forEach(observer => {
      observer.render(columns);
    });
  }
}

export { BoardViewModel };
