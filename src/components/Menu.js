import Component from "../core/Component.js";
import History from "./History.js";

export default class Menu extends Component {
  setup() {}

  setEvent() {}

  mounted() {
    const $histories = document.querySelectorAll(".history");
    $histories.forEach((historyNode, ind) => {
      new History(historyNode, {}, this.state[ind]);
    });
    return;
  }

  template() {
    return /* html */ `
      <img class="close-button" src="../src/style/images/cross.png" alt="끄기버튼" />
      ${'<div class="history"></div>'.repeat(Object.entries(this.state).length)}
    `;
  }
}
