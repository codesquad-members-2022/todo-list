import Column from "./components/Column.js";
import Header from "./components/Header.js";
import Menu from "./components/Menu.js";
import Component from "./core/Component.js";

export default class App extends Component {
  setup() {
    this.state = {
      columns: [{ title: "해야할 일" }, { title: "하고 있는 일" }, { title: "완료한 일" }],
      histories: [
        {
          user: "sam",
          todo: "HTML/CSS 공부하기",
          beforePosition: "해야할 일",
          afterPosition: "하고 있는 일",
          action: "이동",
        },
        {
          user: "sam",
          todo: "HTML/CSS 공부하기",
          beforePosition: "",
          afterPosition: "해야할 일",
          action: "등록",
        },
        {
          user: "sam",
          todo: "블로그에 포스팅할 것",
          beforePosition: "",
          afterPosition: "해야할 일",
          action: "등록",
        },
        {
          user: "sam",
          todo: "Github 공부하기",
          beforePosition: "",
          afterPosition: "해야할 일",
          action: "등록",
        },
      ],
    };
  }
  template() {
    return `
      <header></header>
      <div class="menu"></div>
      <main class="flex">
        <div class="column"></div>
        <div class="column"></div>
        <div class="column"></div>
        <div class="aaa"></div>
      </main>
    `;
  }
  mounted() {
    const $header = this.$target.querySelector("header");
    const $menu = this.$target.querySelector(".menu");
    const $columns = this.$target.querySelectorAll(".column");
    new Header($header);
    new Menu($menu, {}, this.state.histories);
    $columns.forEach(($column, index) => {
      new Column($column, null, this.state.columns[index]);
    });
  }
}

new App(document.querySelector(".app"));
