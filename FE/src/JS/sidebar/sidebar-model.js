export default class Model {
  returnLogData(sidebarData) {
    const userId = sidebarData.userId;
    const sidebarCard = sidebarData.workLogList;
    return [userId, sidebarCard];
  }
}
