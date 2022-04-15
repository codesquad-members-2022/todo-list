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

  getHistory() {
    return this.history;
  }

  addColumn() {}
  removeColumn() {}
  updateColumn() {}

  addItem({ id, columnId, title, content, date }) {
    const newItem = { id, columnId, title, content, date };
    this.items.push(newItem);
    return newItem;
  }

  removeItem(itemId) {
    const itemIndex = this.items.findIndex((item) => item.id === itemId);
    this.items[itemIndex].state = false;
  }

  updateItem() {}
}
