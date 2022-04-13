import { fetchRequest, HTTP_REQUEST } from '../utils/fetch.js';

class BoardStore {
  constructor() {
    this.boardState;
    this.observers = new Set();
  }

  getInitialData() {
    return fetchRequest('./mockData.json');
  }

  async initState() {
    this.boardState = await this.getInitialData();
  }

  postRequest(data) {
    if (!data) return;

    const requestOption = HTTP_REQUEST.POST(data);
    fetchRequest('http://localhost:8080', requestOption);
  }

  deleteRequest() {
    const requestOption = HTTP_REQUEST.DELETE();
    fetchRequest('http://localhost:8080', requestOption);
  }

  addObserver(observer) {
    this.observers.add(observer);
  }

  async observe() {
    await this.initState();
    this.observers.forEach(observer => {
      observer.notify(this.boardState);
    });
  }

  async init() {
    await this.initState();
  }
}

export { BoardStore };
