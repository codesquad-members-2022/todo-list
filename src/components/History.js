import Component from "../core/Component.js";

export default class History extends Component {
  template() {
    const { user, todo, beforePosition, afterPosition, action } = this.state;
    const behavior =
      beforePosition === ""
        ? `
      <span class="user-state">${afterPosition}</span>에
      <span class="user-state">${todo}</span>를(을) ${action}하였습니다.
    `
        : `
      <span class="user-state">${todo}</span>를(을)
      <span class="user-state">${beforePosition}</span>에서
      <span class="user-state">${afterPosition}</span>로 ${action}하였습니다.
    `;

    return /* html */ `
        <div class="emoji">🥳</div>
        <div class="user-behavior">
          <div class="user">@${user}</div>
          <div class="behavior">
            ${behavior}
          </div>
          <div class="time">1분 전</div>
        </div>
    `;
  }
}
