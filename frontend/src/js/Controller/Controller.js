import AlertView from '../Alert/AlertView.js';

class Controller {
  constructor() {
    this.deleteAlertView = null;
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
}

export default Controller;
