export default class Controller {
  constructor(store, { headerView, asideView, columnsView }) {
    this.store = store;

    this.headerView = headerView;
    this.asideView = asideView;
    this.columnsView = columnsView;

    this.subscribeViewEvents();
  }

  subscribeViewEvents() {
    // 뷰에서 발생하는 이벤트를 수신
  }
}
