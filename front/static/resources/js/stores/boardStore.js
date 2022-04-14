import { fetchRequest, HTTP_REQUEST } from '../utils/fetch.js';

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

  async getColumnState(cardState, method) {
    const requestOption = HTTP_REQUEST[method](cardState);
    const newBoadState = await fetchRequest('http://localhost:8080', requestOption);
    return newBoadState;
  }

  async setState(cardState, method) {
    const newBoadState = await this.getColumnState(cardState, method);
    this.boardState = newBoadState;
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  async observe(cardState, method) {
    await this.setState(cardState, method);
    this.observers.forEach(observer => {
      observer.notify();
    });
  }

  async init() {
    await this.initState();
  }
}

export { BoardStore };
