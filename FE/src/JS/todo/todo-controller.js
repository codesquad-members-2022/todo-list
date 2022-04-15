export default class Controller {
  constructor(model, view, drag) {
    this.model = model;
    this.view = view;
    this.drag = drag;
    this.init();
    this.totalCategoryId = this.model.categoryId;
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
    this.drag.getDragValue = this.getDragValue;
  }

  parseCategoryData(workListData) {
    for (const categoryName in workListData) {
      this.view.renderTodoCategory(categoryName);
    }
  }

  parseCardData(workListData) {
    const todoInfo = {
      userId: this.model.userId,
      category: null,
    };

    Object.entries(workListData).map((vl) =>
      vl.map((categoryName) => {
        if (typeof categoryName === 'string') {
          todoInfo.category = categoryName;
        }
        if (Array.isArray(categoryName))
          categoryName.map((data) => {
            this.view.renderTodoCard(todoInfo, data);
          });
      })
    );

    // for (const categoryName in workListData) {
    //   todoInfo.category = categoryName;
    //   for (const data of workListData[categoryName]) {
    //     this.view.renderTodoCard(todoInfo, data);
    //   }
    // }
  }

  getHeaderValue = ({ target }) => {
    const cardHeader = target.closest('.column-addBtn').firstElementChild;
    const columnName = target.closest('.column-item').id;
    const id = this.model.userId;

    const headerValue = {
      userId: id,
      categoryId: this.findCategoryId(columnName),
      title: cardHeader.firstElementChild.value,
      content: cardHeader.lastElementChild.textContent,
    };

    target.closest('.column-addBtn').remove();
    this.makeNewCard(headerValue, id, columnName);
  };

  findCategoryId(columnName) {
    for (const id in this.totalCategoryId) {
      if (id === columnName) {
        return this.totalCategoryId[id];
      }
    }
  }

  async makeNewCard(headerValue, id, columnName) {
    const newCardData = await this.model.addCardData(headerValue);
    const newCard = true;

    const todoInfo = {
      userId: id,
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
    const columnName = target.closest('.column-item').id;
    const selectedCard = target.closest('.column-item--card');
    const workId = selectedCard.id;

    const removeCardInfo = {
      userId: this.model.userId,
      currentCategoryId: this.findCategoryId(columnName),
      title: target.previousElementSibling.textContent,
    };

    selectedCard.remove();
    this.model.removeCardData(removeCardInfo, workId);

    const container = this.view.findCurrentContainer(columnName);
    this.view.changeCategoryCount({ category: columnName }, container);
  };

  getUpdateValue = (target, workId) => {
    const columnName = target.closest('.column-item').id;
    const targetCard = target.closest('.column-addBtn');

    const cardTitle = targetCard.querySelector('.card-title').value;
    const cardContent = targetCard.querySelector('.card-content').textContent;

    const updateInfo = {
      userId: this.model.userId,
      currentCategoryId: this.findCategoryId(columnName),
      title: cardTitle,
      content: cardContent,
    };

    this.makeUpdateInfo(target, updateInfo, workId);
  };

  async makeUpdateInfo(target, updateInfo, workId) {
    const changeUpdateData = await this.model.updateCardData(
      updateInfo,
      workId
    );

    this.view.renderUpdateCard(target, changeUpdateData);
  }

  getDragValue = ({ previousContainer, currentContainer, selectedCard }) => {
    const previousColumn = previousContainer.closest('.column-item');
    const currentColumn = currentContainer.closest('.column-item');
    const cardTitle = selectedCard.querySelector('.card-title').textContent;

    const dragInfo = {
      userId: this.model.userId,
      workId: selectedCard.id,
      previousCategoryId: this.findCategoryId(previousColumn.id),
      currentCategoryId: this.findCategoryId(currentColumn.id),
      title: cardTitle,
    };

    this.model.dragCardData(dragInfo);
    this.changeCardCounter(previousColumn, currentColumn);
  };

  changeCardCounter(previousColumn, currentColumn) {
    const previousContainer = previousColumn.lastElementChild;
    const currentContainer = currentColumn.lastElementChild;

    this.view.changeCategoryCount(
      { category: previousColumn.id },
      previousContainer
    );
    this.view.changeCategoryCount(
      { category: currentColumn.id },
      currentContainer
    );
  }
}
