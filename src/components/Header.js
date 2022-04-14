import Component from "../core/Component.js";

export default class Header extends Component {
  setup() {}

  setEvent() {
    this.addEvent("click", ".menu-button", () => {
      const $menu = document.querySelector(".menu");
      $menu.classList.remove("display-none");
      setTimeout(() => $menu.classList.remove("hidden-menu"), 0);
    });
    document.addEventListener("scroll", () => {
      document.querySelector("html").scrollTop > 98 ? this.$target.classList.add("fixed-header") : this.$target.classList.remove("fixed-header");
    });
  }

  template() {
    return /* html */ `
      <div class="service-name">TO-DO LIST</div>
      <img class="menu-button" src="../src/style/images/menuButton.png" alt="메뉴버튼" />
    `;
  }
}
