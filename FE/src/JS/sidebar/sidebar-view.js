import { $ } from '../utility/util.js';

export default class View {
  constructor() {
    this.$menuIcon = $('#menu-icon');
  }

  addClickEvent() {
    this.$menuIcon.addEventListener('change', this.checKInputCheckbox);
  }

  checKInputCheckbox = () => {
    if (!this.$menuIcon.checked) return;

    this.getLogData();
  };

  renderSidebar(userId, info) {
    const $sidebarContainer = $('.sidebar-container');

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
      <span class="sidebar-card--minute">1분전</span>
    </li>`;
  }

  checkAction({ title, action, previousColumn, changedColumn }) {
    if (action === '이동') {
      return `
      <b>${title}</b>를 <b>${previousColumn}</b>에서<b>${changedColumn}</b>으로<b>${action}</b>하였습니다
      `;
    }

    return `
    <b>${previousColumn}</b>에 <b>${title}</b>를 <b>${action}</b>하였습니다
    `;
  }
}
