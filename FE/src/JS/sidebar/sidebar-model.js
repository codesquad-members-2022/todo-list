export default class Model {
  returnLogData({ userId, workLogList }) {
    const userName = userId;
    const sidebarCard = workLogList;
    return [userName, sidebarCard];
  }
}
