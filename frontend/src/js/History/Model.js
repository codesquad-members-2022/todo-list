import { request } from '../Utils/api.js';

export default class HistoryModel {
  constructor() {
    this.histories = [];
    this.curHistoriesCount = 0;
  }

  async fetchHistories() {
    const PATH = `/histories?length=${this.histories.length}`;
    const historiesData = await request(PATH);
    this.histories.push(...historiesData.data);

    if (!historiesData.hasNext) return;
    await this.fetchHistories();
  }

  addHistory(history) {
    this.histories.upshift(history);
  }

  getHistories() {
    return this.histories;
  }
}
