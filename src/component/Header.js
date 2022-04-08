import Component from "../core/Component";

export default class Header extends Component{
  setup() {}

  setEvent() {}

  template() {
    return /* html */`
      <header class="header">
        <div class="serviceName">TO-DO LIST</div>
        <img class="menuButton" src="../src/style/imgs/menuButton.png" alt="메뉴버튼" />
      </header>
    `;
  }
}