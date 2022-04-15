import { fetchRequest, HTTP_REQUEST } from '../utils/fetch.js';

class BoardStore {
  constructor() {
    this.boardState;
    this.observers = new Set();
  }

  async getInitialData() {
    return await fetchRequest('http://3.39.96.36:8080/cards');
  }

  async initState() {
    this.boardState = await this.getInitialData();
  }

  async getColumnState(cardState, method, id) {
    const requestOption = HTTP_REQUEST[method](cardState);
    const newBoadState = await fetchRequest(`http://3.39.96.36:8080/cards$/${id ? id : ''}`, requestOption);
    return newBoadState;
  }

  async setState(cardState, method, id) {
    const newBoadState = await this.getColumnState(cardState, method, id);
    this.boardState = newBoadState;
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  async observe(cardState, method, id) {
    await this.setState(cardState, method, id);
    this.observers.forEach(observer => {
      observer.notify();
    });
  }

  async init() {
    await this.initState();
  }
}

export { BoardStore };
