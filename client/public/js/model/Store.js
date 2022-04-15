export default class Store {
  constructor({ columns, items, history }) {
    this.columns = columns;
    this.items = items;
    this.history = history;
  }

  getColumns() {
    return this.columns;
  }

  getItems() {
    return this.items;
  }

  addColumn() {}
  removeColumn() {}
  updateColumn() {}

  addItem({ id, columnId, title, content, date }) {
    this.items.push({ id, columnId, title, content, date });
  }

  removeItem(itemId) {
    const itemIndex = this.items.findIndex((item) => item.id === itemId);
    this.items.splice(itemIndex, 1);
  }

  updateItem() {}
}

