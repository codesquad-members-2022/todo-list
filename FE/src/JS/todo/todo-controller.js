export default class Controller {
  constructor(model, view, drag) {
    this.model = model;
    this.view = view;
    this.drag = drag;
    this.init();
  }

  init() {
    const workListData = this.model.workList;
    this.parseCategoryData(workListData);
    this.parseCardData(workListData);
    this.drag.addDragEvent();
    this.view.addCardTemplateEvent();
  }

  parseCategoryData(workListData) {
    for (const categoryName in workListData) {
      this.view.renderTodoCategory(categoryName);
    }
  }

  parseCardData(workListData) {
    const todoInfo = {
      id: this.model.userId,
      category: null,
    };

    for (const categoryName in workListData) {
      todoInfo.category = categoryName;
      for (const data of workListData[categoryName]) {
        this.view.renderTodoCard(todoInfo, data);
      }
    }
  }
}
