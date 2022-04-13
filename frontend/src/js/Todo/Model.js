import Column from './Column/main';

export default class TodoModel {
  constructor() {
    this.allCartCount = 0;
    this.columns = {};
  }

  setColumns() {
    this.columns = {
      1: new Column({ id: 1, title: '해야할 일' }),
      2: new Column({ id: 2, title: '하고 있는 일' }),
      3: new Column({ id: 3, title: '완료한 일' }),
    };
  }

  updateCardCount(action = 'add') {
    switch (action) {
      case 'add':
        this.allCartCount += 1;
        break;
      case 'cancelAdd':
        if (this.allCartCount) this.allCartCount -= 1;
        break;
    }
  }

  getAllCardCount() {
    return this.allCartCount;
  }
}
