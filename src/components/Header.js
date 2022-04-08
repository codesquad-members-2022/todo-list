import Component from "../core/Component.js";

export default class Header extends Component {
  setup() {}

  setEvent() {}

  template() {
    return /* html */ `
      <header class="header">
        <div class="serviceName">TO-DO LIST</div>
        <img class="menuButton" src="../src/style/images/menuButton.png" alt="메뉴버튼" />
      </header>
    `;
  }
}
