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
    const newCardData = await fetchRequest('/works', 'POST', headerValue);

    return newCardData;
  }

  async updateCardData(updateInfo, workId) {
    const changedCardData = await fetchRequest(
      `/works/${workId}`,
      'PATCH',
      updateInfo
    );

    return changedCardData;
  }

  removeCardData(removeCardInfo, workId) {
    fetchRequest(`/works/${workId}`, 'POST', removeCardInfo);
  }

  dragCardData(dragInfo) {
    fetchRequest(`/works`, 'PATCH', dragInfo);
  }
}
