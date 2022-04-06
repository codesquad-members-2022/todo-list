export default class Controller {
  constructor(model, view) {
    this.model = model;
    this.view = view;
    this.init();
  }

  init() {
    const workListData = this.model.setData();
    console.log(workListData);
    this.parseData(workListData);
  }

  parseData(workListData) {
    const userId = workListData.userId;

    for (const a of workListData.workList) {
      this.view.renderTodoItem(a.categoryName, a.works, userId);
    }
  }
}
