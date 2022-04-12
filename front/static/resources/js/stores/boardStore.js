import { fetchRequest } from '../utils/util.js';

class BoardStore {
  constructor() {
    this.boardState;
    this.observers = new Set();
  }

  async getInitialData() {
    return await fetchRequest('./mockData.json');
  }

  async initState() {
    this.boardState = await this.getInitialData();
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  async observe() {
    await this.setState();
    this.observers.forEach(observer => {
      observer.notify(this.boardState);
    });
  }

  async init() {
    await this.initState();
  }
}

export { BoardStore };
