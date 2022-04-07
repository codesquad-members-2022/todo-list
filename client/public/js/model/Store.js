export default class Store {
  constructor(userData) {
    this.storage = userData;
    this.logs = [];
  }

  getColumnList() {
    return this.storage.columns;
  }
  getItemList() {
    return this.storage.items;
  }

  addColumn() {}
  removeColumn() {}

  additem() {}
  removeitem() {}
}

// 컬럼과 아이템 데이터를 관리(가져오기, 생성하기, 삭제하기, 변경하기)
