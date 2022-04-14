import ColumnApi from '../api/columnApi.js';
import Store from './store.js';

class ColumnStore extends Store {
  #key = 'column';

  async init() {
    const columns = await ColumnApi.getAllColumns();
    this.setState(this.#key, columns);
  }

  getColumnWithName(name) {
    const columns = this.getAllColumns();
    const targetColumn = columns.find(column => column.name === name);
    return targetColumn;
  }

  getColumnWithId(id) {
    const columns = this.getAllColumns();
    const targetColumn = columns.find(column => column.id === id);
    return targetColumn;
  }

  getAllColumns() {
    return this.getState(this.#key);
  }
}

const columnStore = new ColumnStore();

export default columnStore;
