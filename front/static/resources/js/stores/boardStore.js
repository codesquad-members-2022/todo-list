import { fetchRequest } from "../utils/util.js";

class BoardStore {
  constructor() {
    this.boardState;
    this.observers = new Set();
  }

  async getInitialData() {
    return await fetchRequest('./mockData.json');
  }

  async setState() {
    this.boardState = await this.getInitialData();
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  async observe(columnName, value) {
    await this.setColumnState(columnName);
    this.observers.forEach(observer => {
      observer.notify(this.boardState.columnName, value);
    });
  }
}

export { BoardStore };
