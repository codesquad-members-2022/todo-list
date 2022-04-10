export default class HeaderModel {
  constructor() {
    this.menuStatus = false;
  }

  updateStatus() {
    this.menuStatus ? (this.menuStatus = false) : (this.menuStatus = true);
  }

  getMenuStatus() {
    return this.menuStatus;
  }
}
