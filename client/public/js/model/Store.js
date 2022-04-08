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
  setColumn() {}

  addItem() {}
  removeItem() {}
  setItem() {}
}

// 컬럼과 아이템 데이터를 관리(가져오기, 생성하기, 삭제하기, 변경하기)
