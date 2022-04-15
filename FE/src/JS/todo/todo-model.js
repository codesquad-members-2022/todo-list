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

    const newCardData = {
      workId: 4,
      title: '호눅스 강의 수강',
      content: '멀티 스레드 개념 및 동작 원리 이해',
      createdDateTime: '2022-04-12T18:20:51',
    };

    return newCardData;
  }

  async updateCardData(updateInfo, workId) {
    // const changedCardData = await fetchRequest(
    //   `/works/${workId}`,
    //   'PATCH',
    //   updateInfo
    // );

    const changedCardData = {
      title: '자바스크립트 공부',
      content: 'setTimeOut 메서드 공부',
    };

    return changedCardData;
  }

  removeCardData(removeCardInfo, workId) {
    // fetchRequest(`/works/${workId}`, 'POST', removeCardInfo);
    console.log('삭제완료');
  }

  dragCardData(dragInfo) {
    // fetchRequest(`/works`, 'PATCH', dragInfo);
    console.log('drag & drop');
  }
}
