import { $ } from '../utility/util.js';

export default class View {
  constructor() {
    this.$menuIcon = $('#menu-icon');
    this.timer = null;
  }

  addClickEvent() {
    this.$menuIcon.addEventListener('change', this.checKInputCheckbox);
  }

  checKInputCheckbox = () => {
    if (!this.$menuIcon.checked) {
      clearInterval(this.timer);
      return;
    }

    this.getLogData();
  };

  startSidebarTimer(userId, info) {
    const $sidebarContainer = $('.sidebar-container');

    if (!this.timer) {
      this.renderSidebar(info, userId, $sidebarContainer);
    }

    const ONE_MINUTE_MS = 60000;
    this.timer = setInterval(() => {
      this.renderSidebar(info, userId, $sidebarContainer);
    }, ONE_MINUTE_MS);
  }

  renderSidebar(info, userId, $sidebarContainer) {
    const sidebarDataTemplate = info.reduce(
      (pre, curData) => (pre += this.sidebarActionLog(userId, curData)),
      ''
    );
    $sidebarContainer.innerHTML = sidebarDataTemplate;
  }

  sidebarActionLog(userId, info) {
    return `
    <li class="sidebar-card">
      <span class="sidebar-card--id">@${userId}</span>
      <span class="side-card--content">${this.checkAction(info)}</span>
      <span class="sidebar-card--minute">${this.calcTwoTimeDifference(
        info
      )}</span>
    </li>`;
  }

  checkAction({ title, action, previousCategory, currentCategory }) {
    if (action === '이동') {
      return `
      <b>${title}</b>를 <b>${previousCategory}</b>에서<b>${currentCategory}</b>으로<b>${action}</b>하였습니다
      `;
    }

    return `
    <b>${currentCategory}</b>에 <b>${title}</b>를 <b>${action}</b>하였습니다
    `;
  }

  calcTwoTimeDifference({ updatedDateTime }) {
    const currentTime = new Date();
    const updateTime = new Date(updatedDateTime);
    const diffMinutes = currentTime.getTime() - updateTime.getTime();
    const result = Math.abs(diffMinutes / (1000 * 60));

    if (result < 60) {
      return `${result.toFixed()}분전`;
    }
    return `${(result / 60).toFixed()}시간 ${(result % 60).toFixed()}분전`;
  }
}
