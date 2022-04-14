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

    this.addEvent("click", ".action-alert", (e) => {
      e.target.classList.add("hidden-alert");
      const data = {
        0: { user: "sam", todo: "HTML/CSS 공부하기", beforePosition: "하고 있는 일", afterPosition: "해야할 일", action: "이동" },
        1: { user: "sam", todo: "HTML/CSS 공부하기", beforePosition: "해야할 일", afterPosition: "하고 있는 일", action: "이동" },
        2: { user: "sam", todo: "HTML/CSS 공부하기", beforePosition: "", afterPosition: "해야할 일", action: "등록" },
        3: { user: "sam", todo: "블로그에 포스팅할 것", beforePosition: "", afterPosition: "해야할 일", action: "등록" },
        4: { user: "sam", todo: "Github 공부하기", beforePosition: "", afterPosition: "해야할 일", action: "등록" },
      };
      this.setState(data);
    });
  }

  mounted() {
    const $histories = document.querySelectorAll(".history");
    $histories.forEach((historyNode, ind) => {
      new History(historyNode, {}, this.state[ind]);
    });
    return;
  }

  template() {
    return /* html */ `
      <div class="action-alert hidden-alert">새로운 히스토리가 있습니다.</div>
      <img class="close-button" src="../src/style/images/cross.png" alt="끄기버튼" />
      ${'<div class="history"></div>'.repeat(Object.entries(this.state).length)}
      <button class="test">action 발생!</button>
    `;
  }
}
