export default class Model {
<<<<<<< HEAD
  returnLogData({ userId, workLogList }) {
    const userName = userId;
    const sidebarCard = workLogList;
    return [userName, sidebarCard];
=======
  returnLogData(sidebarData) {
    const userId = sidebarData.userId;
    const sidebarCard = sidebarData.workLogList;
    return [userId, sidebarCard];
>>>>>>> 82fc6f3ad40738e248878b38e477b231cbc2f43c
  }
}
