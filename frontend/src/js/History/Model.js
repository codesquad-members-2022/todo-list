export default class HistoryModel {
  constructor() {
    this.histories = [];
    this.curHistoriesCount = 0;
  }

  async fetchHistories() {
    const reponse = await fetch(
      `http://13.124.223.73:8080/histories?length=${this.histories.length}`
    );
    const historiesData = await reponse.json();
    this.histories.push(...historiesData.data);

    if (!historiesData.hasNext) return;
    await this.fetchHistories();
  }

  addHistory(history) {
    this.histories.unshift(history);
  }

  getHistories() {
    return this.histories;
  }
}
