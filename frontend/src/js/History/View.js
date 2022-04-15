import { historyTemplate, historyCardTemplate } from '../utils/template.js';

export default class HistoryView {
  constructor() {
    this.historyContainer;
  }

  render() {
    const main = document.querySelector('main');
    const historyContainer = historyTemplate();
    main.insertAdjacentHTML('afterbegin', historyContainer);
    this.historyContainer = document.querySelector('.history_container');
  }

  renderInitHistoryCard({ userName, content, time }) {
    const historyHTML = historyCardTemplate({ userName, content, time });
    this.historyContainer
      .querySelector('.history_list')
      .insertAdjacentHTML('beforeend', historyHTML);
  }

  renderAddHistoryCard({ userName, content, time }) {
    const historyHTML = historyCardTemplate({ userName, content, time });
    this.historyContainer
      .querySelector('.history_list')
      .insertAdjacentHTML('afterbegin', historyHTML);
  }

  eventInit(closeBtnHandler) {
    const closeBtn = document.querySelector('.history_container .close_btn');

    closeBtn.addEventListener('click', closeBtnHandler);
  }

  animation(menuStatus) {
    const body = document.querySelector('body');
    const dim = document.querySelector('.dim');
    if (menuStatus) {
      body.classList.add('scroll_none');
      dim.classList.add('show');
      this.historyContainer.classList.add('show');
      return;
    }
    body.classList.remove('scroll_none');
    dim.classList.remove('show');
    this.historyContainer.classList.remove('show');
  }
}
