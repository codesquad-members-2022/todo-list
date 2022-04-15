import Card from './Card/main';
import Column from './Column/main';
import { request } from '../Utils/api.js';

export default class TodoModel {
  constructor() {
    this.allCardCount = 0;
    this.columns = {};
  }

  async fetchColumns(cardEventHandler) {
    const PATH = '/columns';
    const columnsData = await request(PATH);

    columnsData.forEach(({ columnId, columnName, cards }) => {
      const cardCount = cards ? cards.length : 0;
      this.columns[columnId] = new Column({
        id: columnId,
        title: columnName,
        cardCount,
        cards,
      });
      if (!cards) return;
      cards.forEach((card) => {
        const newCard = new Card(card);
        this.columns[columnId].model.addCardList(newCard);
        if (this.allCardCount < card.cardId) {
          this.allCardCount = card.cardId;
        }
        cardEventHandler(newCard);
      });
    });
  }

  updateCardCount(action = 'add') {
    switch (action) {
      case 'add':
        this.allCardCount += 1;
        break;
      case 'cancelAdd':
        if (this.allCardCount) this.allCardCount -= 1;
        break;
    }
  }

  getAllCardCount() {
    return this.allCardCount;
  }

  getColumns() {
    return this.columns;
  }
}
