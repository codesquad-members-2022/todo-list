import Component from "./core/Component.js";

import { Header } from "./core/Header.js";
import { TodoList } from "./core/TodoList.js";

class App extends Component {
  setup() {
    this.state = {
      lists: [
        {
          title: "오늘 할 일",
          todos: [{ title: "코드스쿼드", content: "수업듣기", caption: "" }],
          selected: -1,
        },
      ],
    };
  }

  template() {
    const { lists } = this.state;
    return `<header class="todo-header" >
          </header>
          ${lists
            .map((list, idx) => `<section data-idx=${idx}></section>`)
            .join("")}`;
  }

  mount() {
    const { lists } = this.state;
    new Header(this.select(".todo-header"));
    lists.forEach(
      (list, idx) =>
        new TodoList(this.select(`section[data-idx="${idx}"`), {
          list,
          idx,
        })
    );
  }
}

new App(document.body);
