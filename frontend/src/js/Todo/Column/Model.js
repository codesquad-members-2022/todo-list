export default class ColumnModel {
  constructor(id, title, cardCount) {
    this.id = id;
    this.title = title;
    this.cardCount = cardCount;
    this.addStatus = false;
  }

  updateAddStstue() {
    return this.addStatus ? (this.addStatus = false) : (this.addStatus = true);
  }
}
