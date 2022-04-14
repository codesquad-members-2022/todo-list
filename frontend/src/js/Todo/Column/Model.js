export default class ColumnModel {
  constructor(id, title, cardCount) {
    this.id = id;
    this.title = title;
    this.cardCount = cardCount;
    this.cardList = {};
    this.addStatus = false;
  }

  updateAddStstue() {
    return this.addStatus ? (this.addStatus = false) : (this.addStatus = true);
  }

  addCardList(card) {
    this.cardList[card.model.id] = card;
    this.updateCardCount();
  }

  deleteCard(cardId) {
    delete this.cardList[cardId];
  }

  updateCardCount() {
    this.cardCount = Object.keys(this.cardList).length;
  }

  getCardCount() {
    return this.cardCount;
  }
}
