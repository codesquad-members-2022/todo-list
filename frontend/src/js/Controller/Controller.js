import { createTagTemplate } from '../utils/createTemplate.js';
import AlertView from '../Alert/AlertView.js';

class Controller {
  constructor({ Header, History }) {
    this.header = Header;
    this.history = History;
    this.deleteAlertView = null;
    this.init();
  }

  init() {
    this.renderInit();
    this.header.view.eventInit(this.menuBtnClickHandler.bind(this));
    this.history.view.eventInit(this.menuBtnClickHandler.bind(this));
  }

  renderInit() {
    const body = document.querySelector('body');
    body.insertAdjacentHTML(
      'afterbegin',
      createTagTemplate('main', '', 'main')
    );
    this.header.view.render();
    this.history.view.render();
  }

  initAlert({ target, content }) {
    target = new AlertView(content);

    target.onClickCancel(handleClickCancel);
    target.onClickAccept(handleClickAccept);

    function handleClickCancel() {
      target.render();
    }

    function handleClickAccept() {
      // Todo: 추후 로직 추가
      target.render();
    }
  }

  menuBtnClickHandler() {
    this.header.model.updateStatus();
    const menuStatus = this.header.model.getMenuStatus();
    this.history.view.animation(menuStatus);
  }
}

export default Controller;
