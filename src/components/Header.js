import Component from "../core/Component.js";

export default class Header extends Component {
  setup() {}

  setEvent() {
    this.addEvent("click", ".menu-button", () => {
      const $menu = document.querySelector(".menu");
      $menu.classList.remove("hidden-menu");
    });
  }

  template() {
    return /* html */ `
      <header class="header">
        <div class="service-name">TO-DO LIST</div>
        <img class="menu-button" src="../src/style/images/menuButton.png" alt="메뉴버튼" />
      </header>
    `;
  }
}
