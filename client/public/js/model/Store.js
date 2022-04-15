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

  addColumn() {
    const columnIds = this.columns.map(({ id }) => id);
    const lastColumnId = Math.max(...columnIds);
    const newColumn = {
      id: this.columns.length ? lastColumnId + 1 : 1,
      title: "",
      length: 0,
    };
    this.columns.push(newColumn);
    return newColumn;
  }

  removeColumn() {}

  updateColumn(columnId, newTitle) {
    const newColumn = this.columns.find(({ id }) => id === columnId);
    newColumn.title = newTitle;
  }

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

  addHistory({ date, username = "yellow", action, content }) {
    this.history.push({ date, username, action, content });
  }
}
