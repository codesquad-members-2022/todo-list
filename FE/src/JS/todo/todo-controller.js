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
    this.view.addCardTemplateEvent();
    this.view.getHeaderValue = this.getHeaderValue;
    this.view.getRemoveValue = this.getRemoveValue;
    this.view.getUpdateValue = this.getUpdateValue;
    this.drag.addDragEvent();
    this.drag.makeDrag = this.makeDrag;
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

  getHeaderValue = ({ target }) => {
    const cardHeader = target.closest('.column-addBtn').firstElementChild;
    const columnName = target.closest('.column-item').id;

    const totalCategory = this.model.categoryId;
    const id = this.model.userId;

    const headerValue = {
      userId: id,
      categoryId: null,
      title: cardHeader.firstElementChild.value,
      content: cardHeader.lastElementChild.textContent,
    };

    for (const id in totalCategory) {
      if (id === columnName) {
        headerValue.categoryId = totalCategory[id];
        break;
      }
    }

    target.closest('.column-addBtn').remove();
    this.makeNewCard(headerValue, id, columnName);
  };

  async makeNewCard(headerValue, id, columnName) {
    const newCardData = await this.model.addCardData(headerValue);
    const newCard = true;

    const todoInfo = {
      id: id,
      category: columnName,
    };

    this.view.renderTodoCard(todoInfo, newCardData, newCard);
    this.startDragEvent();
  }

  startDragEvent() {
    this.drag.removeDragEvent();
    this.drag.addDragEvent();
  }

  getRemoveValue = (target) => {
    console.log(target);
  };
  getUpdateValue = () => {};
  changeDraggingCard() {}
}
