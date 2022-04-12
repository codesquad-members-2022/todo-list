import { getColumnTitles } from "./api.js";
import Column from "./components/Column.js";
import Header from "./components/Header.js";
import Component from "./core/Component.js";

export default class App extends Component {
  async setup() {
    const columnTitles = await getColumnTitles();
    this.state = {
      columnTitles: columnTitles,
    };
    this.render();
  }
  template() {
    return `
      <header></header>
      <main class="flex">
        ${this.state.columnTitles
          ?.map((_, index) => `<div class="column" data-index="${index}"></div>`)
          .join("")}
      </main>
    `;
  }
  mounted() {
    const $header = this.$target.querySelector("header");
    const $columns = this.$target.querySelectorAll(".column");
    new Header($header);
    $columns.forEach(($column, index) => {
      new Column($column, null, this.state.columnTitles[index]);
    });
  }
}

new App(document.querySelector(".app"));
