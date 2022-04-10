import { historyTemplate } from '../Utils/template.js';

export default class HistoryView {
  constructor() {
    this.historyContainer;

    this.render();
  }

  render() {
    const main = document.querySelector('main');
    const historyContainer = historyTemplate();
    main.insertAdjacentHTML('afterbegin', historyContainer);
    this.historyContainer = document.querySelector('.history_container');
  }

  eventInit(closeBtnHandler) {
    const closeBtn = document.querySelector('.history_container .close_btn');

    closeBtn.addEventListener('click', closeBtnHandler);
  }
}
