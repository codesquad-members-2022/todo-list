import { getHistories } from "../api.js";
import Component from "../core/Component.js";
import History from "./History.js";

export default class Menu extends Component {
  setup() {}

  setEvent() {
    this.addEvent("click", ".close-button", () => {
      this.$target.classList.add("hidden-menu");
    });

    this.addEvent("transitionend", ".menu", () => {
      if (this.$target.classList.contains("hidden-menu")) {
        this.$target.classList.add("display-none");
      }
    });

    this.addEvent("click", ".test", () => {
      const $historyAlert = this.$target.querySelector(".action-alert");
      $historyAlert.classList.remove("hidden-alert");
    });

    this.addEvent("click", ".action-alert", async (e) => {
      e.target.classList.add("hidden-alert");
      const newState = await getHistories();
      this.setState(newState);
    });
  }

  mounted() {
    const $histories = document.querySelectorAll(".history");
    $histories.forEach((historyNode, ind) => {
      new History(historyNode, {}, this.state[$histories.length - ind - 1]);
    });
    return;
  }

  template() {
    return /* html */ `
      <div class="action-alert hidden-alert">새로운 히스토리가 있습니다.</div>
      <img class="close-button" src="../src/style/images/cross.png" alt="끄기버튼" />
      ${'<div class="history"></div>'.repeat(Object.entries(this.state).length)}
    `;
  }
}
