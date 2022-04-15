import { historyTemplate, historyCardTemplate } from '../utils/template.js';

export default class HistoryView {
  constructor() {
    this.historyContainer;
    this.historyListBox;
  }

  render() {
    const main = document.querySelector('main');
    const historyContainer = historyTemplate();
    main.insertAdjacentHTML('afterbegin', historyContainer);
    this.historyContainer = document.querySelector('.history_container');
    this.historyListBox = this.historyContainer.querySelector('.history_list');
  }

  renderInitHistoryCard({ userName, content, time }) {
    const historyHTML = historyCardTemplate({ userName, content, time });
    this.historyListBox.insertAdjacentHTML('beforeend', historyHTML);
  }

  renderAddHistoryCard({ userName, content, time }) {
    const historyHTML = historyCardTemplate({ userName, content, time });
    this.historyListBox.insertAdjacentHTML('afterbegin', historyHTML);
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

  resetHistoryTime(time, idx) {
    const historyCard = this.historyListBox.querySelector(
      `.history_card:nth-child(${idx + 1})`
    );
    historyCard.querySelector('.history_time').innerText = time;
  }
}
