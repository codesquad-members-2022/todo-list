import { fetchRequest } from '../utility/fetchRequest.js';

export default class Model {
  constructor(workListData) {
    this.userId = workListData.userId;
    this.workList = {};
    this.categoryId = {};
    this.init(workListData);
  }

  init(workListData) {
    for (const category of workListData.workList) {
      this.workList[category.categoryName] = category.works;
      this.categoryId[category.categoryName] = category.categoryId;
    }
  }
}
