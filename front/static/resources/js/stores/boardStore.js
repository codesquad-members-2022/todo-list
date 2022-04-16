import { fetchRequest, FETCH_OPTION } from '../utils/fetch.js';

class BoardStore {
  constructor() {
    this.boardState;
    this.observers = new Set();
  }

  async #getBoadData() {
    return await fetchRequest('http://3.39.96.36:8080/cards');
  }

  async #postCardData(cardState) {
    const requestOption = FETCH_OPTION.POST(cardState);
    const newBoadState = await fetchRequest('http://3.39.96.36:8080/cards', requestOption);

    return newBoadState;
  }

  async #deleteCardData(id) {
    const requestOption = FETCH_OPTION.DELETE();
    const newBoadState = await fetchRequest(`http://3.39.96.36:8080/cards/${id}`, requestOption);

    return newBoadState;
  }

  async #putCardData(cardState, id) {
    const requestOption = FETCH_OPTION.PUT(cardState);
    const newBoadState = await fetchRequest(`http://3.39.96.36:8080/cards/${id}`, requestOption);

    return newBoadState;
  }

  async #setState(method, cardState, id) {
    switch (method) {
      case 'GET':
        this.boardState = await this.#getBoadData();
        break;
      case 'POST':
        this.boardState = await this.#postCardData(cardState);
        break;
      case 'DELETE':
        this.boardState = await this.#deleteCardData(id);
        break;
      case 'PUT':
        this.boardState = await this.#putCardData(cardState, id);
    }
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  async observe(method, cardState, id) {
    await this.#setState(method, cardState, id);
    this.observers.forEach(observer => {
      observer.notify();
    });
  }

  async init() {
    await this.#setState('GET');
  }
}

export { BoardStore };
