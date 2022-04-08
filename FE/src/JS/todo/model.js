import { fetchRequest } from '../utility/fetchRequest.js';

export default class Model {
  constructor(workListData) {
    this.userId = workListData.userId;
    this.workList = {};
    this.categoryId= {};
    this.init(workListData);
  }

  init(workListData) {
    for (let category of workListData.workList) {
      this.workList[category.categoryName] = category.works;
      this.categoryId[category.categoryName] = category.categoryId;
    }
    console.log(this.workList)
    console.log(this.categoryId)
  }
}

