import db from './webdb';

class Store {
  constructor() {
    this.state = db.getColumns();
  }

  getAllColumns() {
    return this.state;
  }

  setColumnState(columnIdx, newColumnState, callback) {
    const { id: columnId } = this.state[columnIdx];
    db.setData(columnId, newColumnState);
    this.state[columnIdx] = db.getData(columnId);
    callback();
  }
}

const store = new Store();

export default store;
