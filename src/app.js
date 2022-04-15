import { getColumns, getHistories } from "./api.js";
import Column from "./components/Column.js";
import Header from "./components/Header.js";
import Menu from "./components/Menu.js";
import Component from "./core/Component.js";
import {} from "./drag.js";

export default class App extends Component {
  async setup() {
    const columns = await getColumns();
    const histories = await getHistories();
    this.state = {
      columns: columns,
      histories: histories,
    };
    this.render();
  }
  template() {
    return `
      <header class="header"></header>
      <div class="menu hidden-menu display-none"></div>
      <main class="flex">
        ${this.state.columns
          ?.map((column) => `<div class="column" data-index="${column.id}"></div>`)
          .join("")}
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
