import { v4 as uuidv4 } from 'uuid';
import db from './webdb';

class Store {
  constructor() {
    this.state = db.getColumns();
  }

  getAllColumns() {
    return this.state;
  }

  addTodoCard(columnIdx, columnId, title, desc, callback) {
    const newCard = {
      id: uuidv4(),
      columnId,
      columnIdx,
      title,
      desc,
      author: 'web',
      createAt: Date.now(),
    };

    const column = db.getData(columnId);
    const oldCards = column.cards;
    column.cards = [newCard, ...oldCards];
    db.setData(columnId, column);

    if (callback) callback(db.getData(columnId));
  }
}

const store = new Store();

export default store;
