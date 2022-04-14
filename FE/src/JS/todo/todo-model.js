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

  async addCardData(headerValue) {
    // const newCardData = await fetchRequest('/works', 'POST', headerValue);

    const a = {
      id: 4,
      title: '호눅스 강의 수강',
      content: '멀티 스레드 개념 및 동작 원리 이해',
      createdDateTime: '2022-04-12T18:20:51',
    };

    return a;
  }

  updateCardData() {}
  removeCardData() {}
}
