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

  addItem() {}
  removeItem() {}
  updateItem() {}
}

// 컬럼과 아이템 데이터를 관리(가져오기, 생성하기, 삭제하기, 변경하기)
