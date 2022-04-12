import { getColumnTitles, getHistories } from "./api.js";
import Column from "./components/Column.js";
import Header from "./components/Header.js";
import Menu from "./components/Menu.js";
import Component from "./core/Component.js";

export default class App extends Component {
  async setup() {
    const columnTitles = await getColumnTitles();
    const histories = await getHistories();
    this.state = {
      columnTitles: columnTitles,
      histories: histories,
    };
    this.render();
  }
  template() {
    return `
      <header></header>
      <div class="menu"></div>
      <main class="flex">
        ${'<div class="column"></div>'.repeat(this.state.columnTitles?.length)}
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
      new Column($column, null, this.state.columnTitles[index]);
    });
  }
}

new App(document.querySelector(".app"));
