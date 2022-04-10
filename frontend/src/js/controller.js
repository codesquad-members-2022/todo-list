export default class Controller {
  constructor({ header, history }) {
    this.header = header;
    this.history = history;
    this.init();
  }

  init() {
    this.header.view.eventInit(this.menuBtnClickHandler.bind(this));
    this.history.view.eventInit(this.menuBtnClickHandler.bind(this));
  }

  menuBtnClickHandler() {
    this.header.model.updateStatus();
    const menuStatus = this.header.model.getMenuStatus();
    this.history.view.animation(menuStatus);
  }
}
