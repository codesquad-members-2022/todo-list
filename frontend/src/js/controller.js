export default class Controller {
  constructor({ header }) {
    this.header = header;
    this.init();
  }

  init() {
    this.header.view.eventInit(this.menuBtnClickHandler.bind(this));
  }

  menuBtnClickHandler() {
    this.header.model.updateStatus();
  }
}
